package br.com.alura.jdbc.lojavirtual.connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class PrepareStatementTest {

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

    @Test @DisplayName("Deve inserir um novo produto recebendo os parametros de fora")
    void insertTest() {
        String nomeProduto = "Mouse";
        String descricaoProduto = "Mouse sem fio";
        String script = "insert into produto (nome, descricao) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(script, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nomeProduto);
            statement.setString(2, descricaoProduto);

            statement.execute();
            ResultSet result = statement.getGeneratedKeys();

            while (result.next()) {
                Integer idProduto = result.getInt(1);
                assertNotNull(idProduto);
            }

        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
}
