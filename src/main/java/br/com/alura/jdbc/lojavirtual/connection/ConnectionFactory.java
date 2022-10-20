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

    private int maxPoolConnection = 15;

    public ConnectionFactory() {
        ComboPooledDataSource combo = new ComboPooledDataSource();
        combo.setJdbcUrl(url);
        combo.setUser(user);
        combo.setPassword(password);

        combo.setMaxPoolSize(maxPoolConnection);

        this.dataSource = combo;
    }

    public Connection getConnection() throws SQLException {
//        connection = DriverManager.getConnection(url, user, password);
//        return connection;
        return this.dataSource.getConnection();
    }

    public void close() throws SQLException {
        if (connection == null) {
            throw new NullPointerException("Connexão não foi estabelecida anteriormente, tente usar getConnection antes");
        }
        connection.close();
    }

    public int getMaxPoolConnection() {
        return maxPoolConnection;
    }
}
