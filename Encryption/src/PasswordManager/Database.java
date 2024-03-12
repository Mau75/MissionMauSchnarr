package PasswordManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    Connection conn = null;

    {
        try {
            conn = DriverManager.getConnection("identifier.sqlite");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
