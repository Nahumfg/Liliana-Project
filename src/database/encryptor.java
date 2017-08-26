
package database;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author NahumFrog
 */
public class encryptor {
    public void encryDecryptor(){
        
        File desFile = new File("Config/sec/adm.pas");

        // Create data to encrypt
        Map map = new TreeMap(System.getProperties());
        int number = map.size();

        try {

            // Create Key
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            SecretKey secretKey = kg.generateKey();

            // Create Cipher
            Cipher desCipher =
            Cipher.getInstance("DES/ECB/PKCS5Padding");
            desCipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Create stream
            FileOutputStream fos = new FileOutputStream(desFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            CipherOutputStream cos = new CipherOutputStream(bos, desCipher);
            ObjectOutputStream oos = new ObjectOutputStream(cos);

            // Write objects
            oos.writeObject(map);
            oos.writeInt(number);
            //oos.flush();
            oos.close();

            // Change cipher mode
            desCipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Create stream
            FileInputStream fis = new FileInputStream(desFile);
            BufferedInputStream bis = new BufferedInputStream(fis);
            CipherInputStream cis = new CipherInputStream(bis, desCipher);
            ObjectInputStream ois = new ObjectInputStream(cis);

            // Write objects
            //ois.reset();
            //ois.close();

            // Read objects
            Map map2 = (Map)ois.readObject();
            int number2 = ois.readInt();
            ois.close();

            // Compare original with what was read back
            if (map.equals(map2) && (map.size() == number2)) {
                System.out.println("Everything read back out okay.");
            } else {
                System.out.println("Problems during encryption/decryption process.");
            }
        } catch (NoSuchPaddingException e) {
            System.err.println("Padding problem: " + e);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Invalid algorithm: " + e);
        } catch (InvalidKeyException e) {
            System.err.println("Invalid key: " + e);
        } catch (IOException e) {
            System.err.println("I/O Problem: " + e);
        } catch (ClassNotFoundException e) {
            System.err.println("Class loading Problem: " + e);
        } finally {
            if (desFile.exists()) {
                //desFile.delete();
            }
        }
    }
}
