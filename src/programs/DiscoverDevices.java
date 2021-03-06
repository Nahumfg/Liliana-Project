/*
 * Copyright (C) 2017 NahumFrog
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package programs;

import java.io.IOException;
import java.util.Vector;
import javax.bluetooth.*;

/**
 *
 * @author NahumFrog
 */
public class DiscoverDevices {
    private final Vector/*<RemoteDevice>*/ devicesDiscovered;
    private final Object inquiryCompletedEvent = new Object();
     static final UUID OBEX_FILE_TRANSFER = new UUID(0x1106);
    private String deviceAdress ="", deviceFName="";
    private String [] devicesAdressList;
    private String [] deviceFNameList;

    public DiscoverDevices() {
        this.devicesDiscovered = new Vector();
    }
    public void discoverMethod(){
        
        devicesDiscovered.clear();
        
        try{
            DiscoveryListener listener = new DiscoveryListener() {

                public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
                    //System.out.println("Device " + btDevice.getBluetoothAddress() + " found");
                    deviceAdress = deviceAdress+btDevice.getBluetoothAddress()+"\n";
                    devicesDiscovered.addElement(btDevice);
                    System.out.println(cod);
                    try {
                        //System.out.println("     name " + btDevice.getFriendlyName(false));
                        deviceFName = deviceFName+btDevice.getFriendlyName(false)+"\n";
                        
                    } catch (IOException cantGetDeviceName) {
                        
                    }
            }

            public void inquiryCompleted(int discType) {
                System.out.println("Device Inquiry completed!");
                synchronized(inquiryCompletedEvent){
                    inquiryCompletedEvent.notifyAll();
                }
            }

            public void serviceSearchCompleted(int transID, int respCode) {
            }

            public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                
            }
        };
        synchronized(inquiryCompletedEvent) {
            boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
            if (started) {
                System.out.println("wait for device inquiry to complete...");
                inquiryCompletedEvent.wait();
                System.out.println(devicesDiscovered.size() +  " device(s) found");
            }
        }
        }catch(IOException e){
            
        }catch(InterruptedException e){
            
        }
        splitDevices();
        //printDevices();
    }
    public void splitDevices(){
        devicesAdressList = deviceAdress.split("\n");
        deviceFNameList = deviceFName.split("\n");
    }
    public void printDevices(){
        for (int i = 0; i < devicesAdressList.length && i< deviceFNameList.length; i++) {
            System.out.println("\n"+devicesAdressList[i]);
            System.out.println(deviceFNameList[i]);
        }
    }
    public String getDevicesAdress(){
        return deviceAdress;
    }
    public String getDeviceFName(){
        return deviceFName;
    }
    public String [] getDevicesAdressArray(){
        return devicesAdressList;
    }
    public String [] getDevicesNamesArray(){
        return deviceFNameList;
    }
}
