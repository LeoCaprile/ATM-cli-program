package Services;

import Database.DBConnection;
import User.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
        List<String> columnsToSelect = Arrays.asList("userId","username","password", "money", "created_on", "last_transaction");
       try (Statement stmt = DB.select(columnsToSelect).from("users").where("username = '"+userName+"'").executeQuery()){
          ResultSet rs = stmt.getResultSet();
          User user = new User();
          while(rs.next()){
              user.userId = rs.getInt(1);
              user.username = rs.getString(2);
              user.password = rs.getString(3);
              user.money = rs.getInt(4);
              user.createdOn = rs.getDate(5);
              user.lastTransaction = rs.getDate(6);

          }
          return user;
       }

    }

    public void UpdateUserMoneyById(Integer userId, String money) throws SQLException {
        Map<String,String> valuesToUpdate = new HashMap<>();

        valuesToUpdate.put("money", money);

        DB.update("users").set(valuesToUpdate).where(" userId = "+userId).executeUpdate();
    }
}
