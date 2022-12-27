package Database;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DBConnection {

    private final Connection connection;
    private String statement = "";
    public DBConnection() throws SQLException {
        String user = "postgres";
        String password = "12345";
        String connectionString = "jdbc:postgresql://localhost:5433/postgres";

        connection = DriverManager.getConnection(connectionString, user, password);
    }
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
    public DBConnection insert(String table){
        this.statement = this.statement.concat("INSERT INTO ").concat(table).concat(" ");
       return this;
    }

    public DBConnection values (List<String> columns, List<String> values){
        this.statement = this.statement.concat("(");
        columns.forEach(column -> {
            if(columns.get(columns.size() - 1).equals(column)){
                this.statement = this.statement.concat(column);
            }else {
                this.statement = this.statement.concat(column).concat(", ");
            }
        }
        );
        this.statement = this.statement.concat(") VALUES (");

        values.forEach(value -> {
                    if(values.get(values.size() - 1).equals(value)){
                        this.statement = this.statement.concat(isNumeric(value)? value : "'"+value+"'");
                    }else {
                        this.statement = this.statement.concat(isNumeric(value)? value : "'"+value+"'").concat(", ");
                    }
                }
        );

        this.statement = this.statement.concat(")");

        return this;

    }

    public ResultSet SQLQuery(String SQLQuery) throws SQLException {
        try(PreparedStatement result = connection.prepareStatement(SQLQuery)){
            return result.executeQuery();
        }
    }


    public void executeUpdate() throws SQLException {
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(this.statement);
            }
    }
}



