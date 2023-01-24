package Database;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBConnection {

    private final Connection connection;
    private String statement = "";
    public DBConnection() throws SQLException {
        String user = "postgres";
        String password = "12345";
        String connectionString = "jdbc:postgresql://localhost:5433/postgres";

        connection = DriverManager.getConnection(connectionString, user, password);

        String createDBTables = "CREATE TABLE IF NOT EXISTS users (" +
                "userId SERIAL PRIMARY KEY," +
                "username VARCHAR (50)," +
                "password VARCHAR (50)," +
                "money INT," +
                "created_on TIMESTAMP NOT NULL," +
                "last_transaction TIMESTAMP)";

        Statement stmt = connection.createStatement();
        stmt.execute(createDBTables);

    }

    private String separateWithComas(List<String> columns, String column){
        if(!columns.get(columns.size() - 1).equals(column)){
            return column.concat(", ");
        }
        return column;
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
        columns.forEach(column -> statement = statement.concat(separateWithComas(columns,column)));
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

    public DBConnection select(List<String> columns) {
        statement = statement.concat("SELECT ");
        columns.forEach(column -> statement = statement.concat(separateWithComas(columns,column)));
        statement = statement.concat(" ");
        return this;
    }

    public DBConnection from(String table) {
        statement = statement.concat(" FROM ").concat(table);
        return this;
    }

    public DBConnection update(String table){
        this.statement = this.statement.concat("UPDATE ").concat(table).concat(" ");
        return this;
    }

    public DBConnection set(Map<String,String> columnsAndValuesToUpdate){
        statement = statement.concat("SET ");

        List<String> columnsToUpdate = new ArrayList<>();

        columnsAndValuesToUpdate
            .forEach((column,valueToUpdate) -> columnsToUpdate.add(column+"="+valueToUpdate));

        columnsToUpdate.forEach(column -> statement = statement.concat(separateWithComas(columnsToUpdate,column)));

        return this;
    }

    public DBConnection where(String condition) {
        statement = statement.concat(" WHERE ").concat(condition);
        return this;
    }

    public Statement executeQuery() {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery(this.statement);
            statement = "";
            return stmt;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate() throws SQLException {
            try (Statement stmt = connection.createStatement()) {
                stmt.executeUpdate(this.statement);
            }
            statement = "";
    }
}



