package br.com.alura.jdbc.lojavirtual.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private Connection connection;
    private DataSource dataSource;
    private String url = "jdbc:mysql://localhost/loja_virtual?userTimezone=true&serverTimezone=UTC";
    private String user = "dbauser";
    private String password = "p@ssw0rd";

    public ConnectionFactory() {
        ComboPooledDataSource combo = new ComboPooledDataSource();
        combo.setJdbcUrl(url);
        combo.setJdbcUrl(user);
        combo.setPassword(password);

        this.dataSource = combo;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    public void close() throws SQLException {
        if (connection == null) {
            throw new NullPointerException("Connexão não foi estabelecida anteriormente, tente usar getConnection antes");
        }
        connection.close();
    }

}
