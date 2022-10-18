package br.com.alura.jdbc.lojavirtual.connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            System.out.println(e.getMessage());
        }
    }
}
