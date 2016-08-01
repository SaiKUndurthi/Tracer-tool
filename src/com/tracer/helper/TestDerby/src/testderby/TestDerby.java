/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testderby;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author SaiKrishna
 */
public class TestDerby {
    private static Statement prestat;
    private static String sql;
    private static ResultSet rs;

/*
 * The method creates a Connection object. Loads the embedded driver,
 * starts and connects to the database using the connection URL.
 */
    private static Connection createDatabaseConnection() 
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        
	String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	Class.forName(driver).newInstance();
	String url = "jdbc:derby:DB;create=true";        
        Connection c = DriverManager.getConnection(url);
        return c;
    }
    
    public static Connection getConnection() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        return createDatabaseConnection();
    }

    public static void insert(int processID, int threadID, int methodID, String message, String parameter) 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        Connection conn = getConnection();
        Statement prestat = null;
        String sql;
        try {
            prestat = conn.createStatement();
             
            sql = "INSERT INTO TRACE VALUES(\n"+
            processID +","+
            threadID +","+
            methodID +",'"+
            message +"','"+
            parameter +
            "')";
            
            prestat.executeUpdate(sql);
            prestat.close();
            prestat = null;
            conn.close();
            conn = null;
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            
            if (prestat != null){
                try { prestat.close();} catch (SQLException e){;}
                prestat = null;
            }
            if (conn != null){
                try {conn.close();} catch(SQLException e) {;}
            conn = null;
            }
        }
 
    }
    
    public static void insertMethod(int methdID, String method) 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        Connection conn = getConnection();
        Statement prestat = null;
        String sql;
        try {
            prestat = conn.createStatement();
             
            sql = "INSERT INTO METHOD VALUES(\n"+
            methdID +",'"+
            method +
            "')";
            
            prestat.executeUpdate(sql);
            prestat.close();
            prestat = null;
            conn.close();
            conn = null;
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            
            if (prestat != null){
                try { prestat.close();} catch (SQLException e){;}
                prestat = null;
            }
            if (conn != null){
                try {conn.close();} catch(SQLException e) {;}
            conn = null;
            }
        }
 
    }
    
    public static void select( String tbleName) 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        Connection conn = getConnection();
        Statement prestat = null;
        String sql;
        try {
            prestat = conn.createStatement();
             if(tbleName.equalsIgnoreCase("TRACE")){
            sql = "SELECT * FROM "+tbleName;
            rs = prestat.executeQuery(sql);
            while(rs.next()){
                int process = rs.getInt("processID");
                int thread = rs.getInt("threadID");
                int method = rs.getInt("methodID");
                String msg = rs.getString("message");
                String param = rs.getString("parameter");
                System.out.println("Process ID :\t"+process+"\tThread ID :\t"+thread+"\tMethod ID :\t"+method+
                     "\tMessage :\t"+msg+"\tParameters :\t"+param);
            }
             }else if(tbleName.equalsIgnoreCase("method")){
                 sql = "SELECT * FROM "+tbleName;
            rs = prestat.executeQuery(sql);
            while(rs.next()){
                int methodID = rs.getInt("methdID");
                String method = rs.getString("method");
                System.out.println("\tMethod ID :\t"+methodID+
                     "\tMethod :\t"+method);
            }
                    
                    }
            
             
            prestat.close();
            prestat = null;
            conn.close();
            conn = null;
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            
            if (prestat != null){
                try { prestat.close();} catch (SQLException e){;}
                prestat = null;
            }
            if (conn != null){
                try {conn.close();} catch(SQLException e) {;}
            conn = null;
            }
        }
    }

    public static void createCallTrace() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        Connection cn = getConnection();
        try {                        
            prestat = cn.createStatement();
             
            sql = "CREATE TABLE TRACE (\n" +
                "   \"processID\" INTEGER not null primary key,\n" +
                "    \"threadID\" INTEGER,\n" +
                "    \"methodID\" INTEGER,\n" +
                "    \"message\" VARCHAR(50),\n" +
                "    \"parameter\" VARCHAR(20),\n" +
                "   FOREIGN KEY(\"methodID\") REFERENCES METHOD(\"methdID\")"+
                "    )";
            
            prestat.executeUpdate(sql);
            prestat.close();
            prestat = null;
            cn.close();
            cn = null;
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            
            if (prestat != null){
                try { prestat.close();} catch (SQLException e){;}
                prestat = null;
            }
            if (cn != null){
                try {cn.close();} catch(SQLException e) {;}
            cn = null;
            }
        }
        
    }
    public static void deleteTable(String tbleName) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        Connection cn = getConnection();
        try {                        
            prestat = cn.createStatement();
             
            sql = "DROP TABLE "+tbleName;
            
            prestat.executeUpdate(sql);
            prestat.close();
            prestat = null;
            cn.close();
            cn = null;
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            
            if (prestat != null){
                try { prestat.close();} catch (SQLException e){;}
                prestat = null;
            }
            if (cn != null){
                try {cn.close();} catch(SQLException e) {;}
            cn = null;
            }
        }
        
    }
    
    public static void createMethoDef() 
            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        
        Connection cn = getConnection();
        try {                        
            prestat = cn.createStatement();
             
            sql = "CREATE TABLE METHOD (\n" +
                "   \"methdID\" INTEGER not null primary key,\n" +
                "    \"method\"  VARCHAR(250)\n"+
                "    )";
            
            prestat.executeUpdate(sql);
            prestat.close();
            prestat = null;
            cn.close();
            cn = null;
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            
            if (prestat != null){
                try { prestat.close();} catch (SQLException e){;}
                prestat = null;
            }
            if (cn != null){
                try {cn.close();} catch(SQLException e) {;}
            cn = null;
            }
        }
        
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        //Connection cn = 
        //TestDerby.create();//System.out.println(cn.toString());
        
        //TestDerby.select("TRACE");
        //TestDerby.deleteTable("TRACE");
       // Helper.select(3);
        //TestDerby.createMethoDef();
        //TestDerby.createCallTrace();
        //TestDerby.insertMethod(2, "void android.widget.TextView.setText(CharSequence)");
       //
        //Parser.getCallTrace("src\\testderby\\HelloWorld_method_definitions.txt");
        TestDerby.select("TRACE");
    }
    
}
