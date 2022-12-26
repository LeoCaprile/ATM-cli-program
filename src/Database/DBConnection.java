package Database;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {

    private final Connection connection;
    public DBConnection() throws SQLException {
        String user = "postgres";
        String password = "12345";
        String connectionString = "jdbc:postgresql://localhost:5433/postgres";

        connection = DriverManager.getConnection(connectionString, user, password);
    }

    public ResultSet SQLQuery(String SQLQuery) throws SQLException {
        try(PreparedStatement result = connection.prepareStatement(SQLQuery)){
            return result.executeQuery();
        }
    }

    public void SQLUpdate(String SQLQuery) throws SQLException {
        try(Statement stmt = connection.createStatement()){
            stmt.executeUpdate(SQLQuery);
        }
    }

}



