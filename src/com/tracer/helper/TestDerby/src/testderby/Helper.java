/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testderby;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 *
 * @author SaiKrishna
 */
public class Helper {
    private static Statement prestat;
    private static String query;
    private static ResultSet rs;
    private static HashMap<String,String> streamStorage;
    
    public static void select(int n) 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        Connection c = TestDerby.getConnection();
        prestat = null;
        try{
            prestat = c.createStatement();
            prestat.setMaxRows(n);
            query = "SELECT * FROM TRACE";
            rs = prestat.executeQuery(query);
            myArray(rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            
            if (prestat != null){
                try { prestat.close();} catch (SQLException e){;}
                prestat = null;
            }
            if (c != null){
                try {c.close();} catch(SQLException e) {;}
            c = null;
            }
        }   
    }
    
    public static void stream(ArrayList al){
        
        al.stream().forEach(System.out::print);
        
    }
    
   public static void myArray(ResultSet rs) throws SQLException    {    
        while(rs.next()){
            ArrayList<String> process = new ArrayList<>();
                    process.clear();
                    process.add(Integer.toString(rs.getInt("processID")));
                    process.add("\t");
                    process.add(Integer.toString(rs.getInt("threadID")));
                    process.add("\t");
                    process.add(Integer.toString(rs.getInt("methodID")));
                    process.add("\t");
                    process.add(rs.getString("message"));
                    process.add("\t");
                    process.add(rs.getString("parameter"));
                   stream(process);
                    System.out.println();
                }    
   }
}
