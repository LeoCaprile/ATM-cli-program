package Model;

import Database.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UserModel {
    DBConnection DB;
    public UserModel() throws SQLException {
        DB = new DBConnection();
    }
    public void RegisterUser(String username, String password) throws SQLException {
        List<String> columns = Arrays.asList("username","password","money");
        List<String> values = Arrays.asList(username,password,"0");
        DB.insert("users").values(columns, values).executeUpdate();
    }


}
