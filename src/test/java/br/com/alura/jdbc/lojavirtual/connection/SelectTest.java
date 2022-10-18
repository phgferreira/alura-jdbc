package br.com.alura.jdbc.lojavirtual.connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    void cenario1() {
        String query = "select id_produto, nome, descricao from produto";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                Integer id = result.getInt("id_produto");
                String nome = result.getString("nome");
                String descricao = result.getString("descricao");

                assertEquals(1, id);
                assertEquals("Notebook", nome);
                assertEquals("Notebook Samsung", descricao);
            }
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
}
