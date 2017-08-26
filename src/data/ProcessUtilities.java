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
package data;

/**
 *
 * @author NahumFrog
 */
public class ProcessUtilities {
    private Articulo art = new Articulo();
    private String name;
    private int code, discount,util,taxe;
    private double cost,clientPrize;
    public void setArticle(){
        art.setName(name);
        art.setCode(code);
        art.setCost(cost);
        art.setUtil(util);
        art.setTax(taxe);
        art.setDiscount(discount);
        art.setClientPrize(clientPrize);
    }
    public void getArticle(){
        
    }
    public void setUtilities(){
        
    }
}
