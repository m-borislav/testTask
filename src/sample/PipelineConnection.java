package sample;

import java.sql.*;

public class PipelineConnection {
    static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:~/dbbest";
    private static final String USER = "sa";
    private static final String PASS = "";

    private Connection connection;
    public PipelineConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try{
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
