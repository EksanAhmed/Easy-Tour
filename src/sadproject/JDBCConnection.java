/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sadproject;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Homepc
 */
public class JDBCConnection {
    
    public static String Login(String username,String password){
        String returnString="";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("select * from userlist where username=? and password=?");
                
                pstmt.setString(1, username);
                
                pstmt.setString(2, password);
                
                ResultSet rs = pstmt.executeQuery();
                
                
                if(rs.next()){
                    returnString = rs.getString("username");
                    SADProject.CurrentType = rs.getInt("accounttype");
                }
                System.out.println(returnString);
                con.close();
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnString;
    }
    
    public static int CheckUsername(String username){
        String returnString="";
        int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("select * from userlist where username=?");
                
                pstmt.setString(1, username);

                ResultSet rs = pstmt.executeQuery();
                
                if(rs.next()){
                    returnString = rs.getString("username");
                }
                if(!"".equals(returnString)){
                    returnInt=1;
                }
                con.close();
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnInt;
    }
    
    public static int SignUp(String username,String password, int accountType, String Name, String Email, int Phone){
        
        int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("insert into userlist values(?,?,?,?,?,?)");
                
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.setInt(3, accountType);
                pstmt.setString(4, Name);
                pstmt.setInt(5, Phone);
                pstmt.setString(6, Email);
                
                pstmt.executeUpdate();
                returnInt=1;
                con.close();
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnInt;
    }
    
    public static String SearchDivision(String stringSegment){
        String returnString="";
        //int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("select division as a from places where division=?");
                
                pstmt.setString(1, stringSegment);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    returnString = rs.getString("a");
                }
                PreparedStatement pstmt2 = con.prepareStatement("select division as a from places where division like ?");
                if("".equals(returnString)){
                    pstmt2.setString(1, stringSegment+"%");
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                if("".equals(returnString)){
                    pstmt2.setString(1, "%"+stringSegment);
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                if("".equals(returnString)){
                    pstmt2.setString(1, "%"+stringSegment+"%");
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                System.out.println(returnString);
                con.close();
                
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnString;
    }

    public static String SearchDistrict(String stringSegment, String division){
        String returnString="";
        //int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("select district as a from places where district=? and division=?");
                
                pstmt.setString(1, stringSegment);
                pstmt.setString(2, division);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    returnString = rs.getString("a");
                }
                PreparedStatement pstmt2 = con.prepareStatement("select district as a from places where district like ? and division =?");
                if("".equals(returnString)){
                    pstmt2.setString(1, stringSegment+"%");
                    pstmt2.setString(2, division);
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                if("".equals(returnString)){
                    pstmt2.setString(1, "%"+stringSegment);
                    pstmt2.setString(2, division);
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                if("".equals(returnString)){
                    pstmt2.setString(1, "%"+stringSegment+"%");
                    pstmt2.setString(2, division);
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                System.out.println(returnString);
                con.close();
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnString;
    }

    public static String SearchPlace(String stringSegment, String district, String division){
        String returnString="";
        //int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("select site as a from places where site=? and district = ? and division =?");
                
                pstmt.setString(1, stringSegment);
                pstmt.setString(2, district);
                pstmt.setString(3, division);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    returnString = rs.getString("a");
                }
                PreparedStatement pstmt2 = con.prepareStatement("select site as a from places where site like ? and district = ? and division =?");
                pstmt2.setString(2, district);
                pstmt2.setString(3, division);
                if("".equals(returnString)){
                    pstmt2.setString(1, stringSegment+"%");
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                if("".equals(returnString)){
                    pstmt2.setString(1, "%"+stringSegment);
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                if("".equals(returnString)){
                    pstmt2.setString(1, "%"+stringSegment+"%");
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                System.out.println(returnString);
                con.close();
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnString;
    }

    public static String SearchPlace(String stringSegment, String district){
        String returnString="";
        //int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("select site as a from places where site=? and district =?");
                
                pstmt.setString(1, stringSegment);
                pstmt.setString(2, district);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    returnString = rs.getString("a");
                }
                PreparedStatement pstmt2 = con.prepareStatement("select site as a from places where site like ? and district =?");
                pstmt2.setString(2, district);
                if("".equals(returnString)){
                    pstmt2.setString(1, stringSegment+"%");
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                if("".equals(returnString)){
                    pstmt2.setString(1, "%"+stringSegment);
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                if("".equals(returnString)){
                    pstmt2.setString(1, "%"+stringSegment+"%");
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                System.out.println(returnString);
                con.close();
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnString;
    }
    
    public static String SearchPlace(String stringSegment){
        String returnString="";
        //int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("select site as a from places where site=?");
                
                pstmt.setString(1, stringSegment);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    returnString = rs.getString("a");
                }
                PreparedStatement pstmt2 = con.prepareStatement("select site as a from places where site like ?");
                if("".equals(returnString)){
                    pstmt2.setString(1, stringSegment+"%");
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                if("".equals(returnString)){
                    pstmt2.setString(1, "%"+stringSegment);
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                if("".equals(returnString)){
                    pstmt2.setString(1, "%"+stringSegment+"%");
                    ResultSet rs2 = pstmt2.executeQuery();
                    if(rs2.next()){
                        returnString = rs2.getString("a");
                    }
                }
                System.out.println(returnString);
                con.close();
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnString;
    }
    
    public static String SearchPlaceDescription(String place){
        String returnString="";
        //int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("select description as a from places where site=?");
                
                pstmt.setString(1, place);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    returnString = rs.getString("a");
                }
                
                
                System.out.println(returnString);
                con.close();
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnString;
    }
    
    public static int NewPlace(String Name,String Division, String District, String Description){
        
        int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("insert into places values(place_seq2.nextval,?,?,?,?)");
                
                pstmt.setString(1, Name);
                pstmt.setString(2, District);               
                pstmt.setString(3, Division);
                pstmt.setString(4, Description);
                pstmt.executeUpdate();
                returnInt=1;
                con.close();
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnInt;
    }
    
    public static int NewTransport(int type, int placeID, int budget){
        
        int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("insert into places values(transport_seq.nextval,?,?,?)");
                
                pstmt.setInt(1, type);
                pstmt.setInt(2, placeID);               
                pstmt.setInt(3, budget);
                
                pstmt.executeUpdate();
                returnInt=1;
                con.close();
            }
        } catch (SQLException Exception) {
            try (Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt2 = con2.prepareStatement("update places set budget = ? where placeID=? and type=?)");
                
                pstmt2.setInt(1, budget);
                pstmt2.setInt(2, placeID); 
                pstmt2.setInt(3, type); 
                
                
                pstmt2.executeUpdate();
                returnInt=2;
                con2.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            returnInt=2;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return returnInt;
    }

    public static int SearchPlaceID(String place){
        int returnString=0;
        //int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("select placeid as a from places where site=?");
                
                pstmt.setString(1, place);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    returnString = rs.getInt("a");
                }
                
                
                System.out.println(returnString);
                con.close();
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnString;
    }

    public static int getFare(int place, int transport){
        int returnString=-1;
        //int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("select budget as a from transport where type=? and placeid=?");
                
                pstmt.setInt(1, transport);
                pstmt.setInt(2, place);
                
                
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()){
                    
                    returnString = rs.getInt("a");
                }
                
                
                System.out.println(returnString);
                con.close();
            }
        } catch (ClassNotFoundException | SQLException Exception) {
            System.out.println(Exception);
        }
        
        return returnString;
    }
    
    public static int Checkout(int placeid, int transportid, int guide,int budget){
        
        int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("insert into checkout values(checkout_seq.nextval,?,?,?,?)");
                
                pstmt.setInt(1, placeid);
                pstmt.setInt(2, transportid);               
                pstmt.setInt(3, guide);
                pstmt.setInt(3, budget);
                pstmt.executeUpdate();
                returnInt=1;
                con.close();
            }
        } catch (SQLException Exception) {
            
            } 
            
         catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return returnInt;
    }
    
    public static int Checkout(int placeid, int guide, int budget,String username){
        
        int returnInt=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "eksan", "eksan")) {
                PreparedStatement pstmt = con.prepareStatement("insert into checkout values(checkout_seq.nextval,?,null,?,?,?)");
                
                pstmt.setInt(1, placeid);
                //pstmt.setInt(2, transportid);               
                pstmt.setInt(2, guide);
                pstmt.setString(3, username);
                pstmt.setInt(4, budget);
                pstmt.executeUpdate();
                returnInt=1;
                con.close();
            }
        } catch (SQLException Exception) {
            
            } 
            
         catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return returnInt;
    }
}
