package Model;

import Database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    DBConnection DB;
    public UserModel() throws SQLException {
        DB = new DBConnection();
    }

    public void RegisterUser(String username, String password) throws SQLException {
        DB.SQLUpdate("INSERT INTO users (username, password, money) " +
                "VALUES ('"+ username +"','"+ password +"', 0)");
    }


}
