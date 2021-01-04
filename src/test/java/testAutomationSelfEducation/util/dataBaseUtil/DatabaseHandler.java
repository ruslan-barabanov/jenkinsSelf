package testAutomationSelfEducation.util.dataBaseUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    Set<String> dbMethodNames = new HashSet<>();

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
        return dbConnection;
    }

    public void getNexageTests() throws SQLException, ClassNotFoundException {
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
        System.out.println(dbMethodNames);
    }
}
