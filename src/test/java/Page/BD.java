package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BD {
    Connection connectionBudget22;
    Connection connectionBudget21;
    Connection connectionedokf;
    public Connection getConnectionBudget22() throws ClassNotFoundException, SQLException {
        try {
            String dbURLBudget22 = "jdbc:sqlserver://172.31.2.101:1433;database=budget22";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connectionBudget22 = DriverManager.getConnection(dbURLBudget22, "Autotest", "Autotest111");
        } catch (Exception ex22) {
            System.out.println("Подключиться к базе данных не удалось");
            System.out.println(ex22);
            System.exit(-1);
        }
        return connectionBudget22;
    }
    public Connection getConnectionBudget21() throws ClassNotFoundException, SQLException {
        try {
            String dbURLBudget21 = "jdbc:sqlserver://172.31.2.101:1433;database=budget21";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connectionBudget21 = DriverManager.getConnection(dbURLBudget21, "Autotest", "Autotest111");
        } catch (Exception ex21) {
            System.out.println("Подключиться к базе данных не удалось");
            System.out.println(ex21);
            System.exit(-1);
        }
        return connectionBudget21;
    }

    public Connection getConnectionEDOKF() throws ClassNotFoundException, SQLException {
        try {
            String dbURLedokf ="jdbc:postgresql://172.31.1.150:55434/edokf";
            Class.forName("org.postgresql.Driver");
            connectionedokf = DriverManager.getConnection(dbURLedokf, "support", "Ztcnmhen!");
        } catch (Exception exEDO) {
            System.out.println("Подключиться к базе данных не удалось");
            System.out.println(exEDO);
            System.exit(-1);
        }
        return connectionedokf;
    }
    public void closeConnectionEDOKF() throws SQLException {
        if (connectionedokf != null && !connectionedokf.isClosed()) {
            connectionedokf.close();
        }
    }
    public void closeConnectBudget22() throws SQLException {
        if (connectionBudget22 != null && !connectionBudget22.isClosed()) {
            connectionBudget22.close();
        }
    }
    public void closeConnectBudget21() throws SQLException {
        if (connectionBudget21 != null && !connectionBudget21.isClosed()) {
            connectionBudget21.close();
        }
    }
}



