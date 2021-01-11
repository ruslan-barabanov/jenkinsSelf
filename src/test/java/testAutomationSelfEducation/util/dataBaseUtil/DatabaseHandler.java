package testAutomationSelfEducation.util.dataBaseUtil;

import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    Set<String> dbMethodNames = new HashSet<>();
    private Properties properties = new Properties();

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException, IOException {
        properties.load(ClassLoader.getSystemResourceAsStream("selfEducation.properties"));

        String dbUser = properties.getProperty("dbUser.path");
        String dbPassword = properties.getProperty("dbPassword.path");

        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        return dbConnection;
    }
    @Test
    public Set<String> getNexageTests() throws SQLException, ClassNotFoundException, IOException {
        String insert = "select * from union_reporting.test t \n" +
                "where t.project_id = 1\n" +
                "order BY t.start_time DESC \n" +
                "LIMIT 20;";
        PreparedStatement prst = getDbConnection().prepareStatement(insert);
        prst.execute();
        ResultSet resultSet = prst.getResultSet();

        while (resultSet.next()) {
            dbMethodNames.add(resultSet.getString("method_name"));
        }
        return dbMethodNames;
    }
}
