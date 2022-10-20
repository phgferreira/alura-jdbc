package br.com.alura.jdbc.lojavirtual.connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionFactoryTest {

    private ConnectionFactory factory;

    @BeforeEach
    void beforeEach() {
        factory = new ConnectionFactory();
    }

    @Test @DisplayName("Deve retornar uma conexão com sucesso")
    void getConnection() {
        try {
            Connection connection = factory.getConnection();

            assertNotNull(connection);
            assertNotNull(connection.createStatement());

            connection.close();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test @DisplayName("Deve abrir todos os pools de conexão")
    void testConnectionPool() {
        try (Connection connection = factory.getConnection()) {

            for (int i = 0; i < factory.getMaxPoolConnection(); i++)
                assertNotNull(factory.getConnection());

        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

}