package br.com.alura.jdbc.lojavirtual.connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

    @Test @DisplayName("Deve exebir excessão de conexão fechada")
    void close() {
        Connection connection = null;
        try {
            connection = factory.getConnection();
            connection.close();

            // Como a conexão já foi fechada deve apresentar excessão ao executar a linha de comando abaixo
            Statement statement = connection.createStatement();

            // Se chegou até aqui então não exibiu a excessão que deveria
            fail("Não exibiu erro de conexão fechada");
        } catch (SQLException e) {
            assertEquals("No operations allowed after connection closed.", e.getMessage());
        }
    }
}