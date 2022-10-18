package br.com.alura.jdbc.lojavirtual.connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class InsertTest {

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

    @Test @DisplayName("Deve inserir um novo produto na tabela produto do banco de dados")
    void insertTest() {
        String script = "insert into produto (nome, descricao) values ('Mouse', 'Mouse sem fio')";
        try {
            Statement statement = connection.createStatement();
            statement.execute(script, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.getGeneratedKeys();

            while (result.next()) {
                Integer idProduto = result.getInt(1);
                assertNotNull(idProduto);
            }

        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test @DisplayName("Deve inserir mais de um registro")
    void insertTest2() {
        String script = "insert into produto (nome, descricao) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(script, Statement.RETURN_GENERATED_KEYS);
            ResultSet result1 = insereProduto(statement, "Smart TV", "45 polegadas");
            ResultSet result2 = insereProduto(statement, "Radio", "Radio de bolso");
            ResultSet result3 = insereProduto(statement, "Computador", "Gabinete");
            ResultSet result4 = insereProduto(statement, "Monitor", "13 polegadas");

            // Se chegou até o final então
            assertNotNull(result1);
            assertNotNull(result2);
            assertNotNull(result3);
            assertNotNull(result4);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    private ResultSet insereProduto(PreparedStatement statement, String nome, String descricao) throws SQLException {
        statement.setString(1, nome);
        statement.setString(2, descricao);
        statement.execute();
        return statement.getGeneratedKeys();
    }

}
