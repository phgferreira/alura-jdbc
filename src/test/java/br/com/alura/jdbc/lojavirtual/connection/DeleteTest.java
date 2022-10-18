package br.com.alura.jdbc.lojavirtual.connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteTest {

    private Connection connecton;

    @BeforeEach
    void beforeEach() {
        try {
            connecton = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void afterEach() {
        try {
            connecton.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test @DisplayName("Deve delete os registros de produto inseridos no InsertTest com sucesso")
    void deleteTest() {
        String script = "delete from produto where nome = 'Mouse' and descricao = 'Mouse sem fio'";
        try {
            Statement statement = connecton.createStatement();
            statement.execute(script);
            Integer linhasExcluidas = statement.getUpdateCount();

            assertTrue((linhasExcluidas > 0));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
}
