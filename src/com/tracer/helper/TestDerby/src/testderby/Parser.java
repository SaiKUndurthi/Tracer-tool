/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testderby;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author SaiKrishna
 */
public class Parser {
    public static void getCallTrace(String fileName) 
            throws FileNotFoundException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        Scanner sc = new Scanner(new File(fileName));
        sc.nextLine();
        while(sc.hasNext()){
            String temp = sc.next();
            String temp1 = sc.nextLine();
            TestDerby.insertMethod(Integer.parseInt(temp), temp1);
        }
    }
}
