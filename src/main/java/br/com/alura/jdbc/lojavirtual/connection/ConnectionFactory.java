package br.com.alura.jdbc.lojavirtual.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    Connection connection;
    private String url = "jdbc:mysql://localhost/loja_virtual?userTimezone=true&serverTimezone=UTC";
    private String user = "dbauser";
    private String password = "p@ssw0rd";

    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public void close() throws SQLException {
        if (connection == null) {
            throw new NullPointerException("Connexão não foi estabelecida anteriormente, tente usar getConnection antes");
        }
        connection.close();
    }

}
