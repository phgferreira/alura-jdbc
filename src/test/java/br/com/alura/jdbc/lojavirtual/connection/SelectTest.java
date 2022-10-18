package br.com.alura.jdbc.lojavirtual.connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class SelectTest {

    private Connection connection;

    @BeforeEach
    void beforeEach() {
        try {
            connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void afterEach() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test @DisplayName("Deve retornar uma consulta com sucesso")
    void selectOneTest() {
        String query = "select id_produto, nome, descricao from produto where nome = 'Notebook'";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                String nome = result.getString("nome");
                String descricao = result.getString("descricao");

                assertEquals("Notebook", nome);
                assertEquals("Notebook Samsung", descricao);
            }
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
}
