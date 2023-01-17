package Services;

import Database.DBConnection;
import User.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserServices {
    DBConnection DB;
    public UserServices() throws SQLException {
        DB = new DBConnection();
    }
    public void RegisterUser(String username, String password) throws SQLException {
        List<String> columns = Arrays.asList("username","password","money","created_on");
        List<String> values = Arrays.asList(username,password,"0", new Date().toString());
        DB.insert("users").values(columns, values).executeUpdate();
    }

    public User GetUser(String userName) throws SQLException{
        List<String> columnsToSelect = Arrays.asList("username","password");
       try (Statement stmt = DB.select(columnsToSelect).from("users").where("username = '"+userName+"'").executeQuery()){
          ResultSet rs = stmt.getResultSet();
          User user = new User();
          while(rs.next()){
              user.username = rs.getString(1);
              user.password = rs.getString(2);
          }
          return user;
       }

    }

}
