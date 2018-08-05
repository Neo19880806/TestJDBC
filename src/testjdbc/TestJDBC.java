/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testjdbc;

import java.sql.*;

/**
 *
 * @author Administrator
 */
public class TestJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {

            //loads the driver class
            //an alternative is to use: 
            // DriverManager.registerDriver( 
            // new oracle.jdbc.driver.OracleDriver());
            // which creates a new instance of the 
            // Oracle driver and registers it 
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //connects to the Oracle catinfo database at fluffycat, 
            // logs in as "johnsmith" with a password of "password"
            Connection connection
                    = DriverManager.getConnection("jdbc:oracle:thin:@35.166.130.72:1521:xe", 
                            "GuojingL", "001075377");

            //this is what we use to tie our connection to our SQL
            Statement statement = connection.createStatement();

            //now run the SQL
            ResultSet resultSet = statement.executeQuery(
                    "select a.airlinecode, a.count_airline_owned_airplane() from airline_table a");

            //traverse the ResultSet, 
            //The cat names are in the first column, and the 
            // favorite foods are in the second column.
            //note: the subscript of the first column is one, not zero.
            System.out.println("AirlineCode\t\tNumberOfOwnedAirplane");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)+"\t\t\t\t"+resultSet.getString(2));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (java.lang.ClassNotFoundException cnfException) {
            System.out.println(
                    "The following error occured "
                    + "in finding the Oracle driver: "
                    + cnfException);
        } catch (SQLException sqlException) {
            System.out.println(
                    "The following error occured in " + "reading from the Airline_table: " + sqlException);
        }
    }

}
