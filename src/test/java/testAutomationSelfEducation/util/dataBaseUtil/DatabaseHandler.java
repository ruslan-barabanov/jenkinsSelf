package testAutomationSelfEducation.util.dataBaseUtil;


import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class DatabaseHandler{
    Connection dbConnection;
    Set<String> dbMethodNames = new HashSet<>();
    private Properties properties = new Properties();

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException, IOException {
        properties.load(ClassLoader.getSystemResourceAsStream("selfEducation.properties"));

        String dbUser = properties.getProperty("dbUser.path");
        String dbPassword = properties.getProperty("dbPassword.path");

        String dbHost = properties.getProperty("dbHost.path");;
        String dbPort = properties.getProperty("dbPort.path");
        String dbName = properties.getProperty("dbName.path");;

        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        return dbConnection;
    }

    public Set<String> getNexageTests() throws SQLException, ClassNotFoundException, IOException {
        properties.load(ClassLoader.getSystemResourceAsStream("selfEducation.properties"));

        String insert = properties.getProperty("insert.path");
        PreparedStatement prst = getDbConnection().prepareStatement(insert);
        prst.execute();
        ResultSet resultSet = prst.getResultSet();

        while (resultSet.next()) {
            dbMethodNames.add(resultSet.getString("method_name"));
        }
        return dbMethodNames;
    }
}
