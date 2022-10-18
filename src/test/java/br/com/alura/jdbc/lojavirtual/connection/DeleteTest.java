package br.com.alura.jdbc.lojavirtual.connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteTest {

    private Connection connection;

    @BeforeEach
    void beforeEach() {
        try {
            connection = new ConnectionFactory().getConnection();
            this.insereDadosQueSeraoDeletados();
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

    @Test @DisplayName("Deve delete os registros de produto inseridos no InsertTest com sucesso")
    void deleteTest() {
        String script = "delete from produto where nome = 'Mouse' and descricao = 'Mouse sem fio'";
        try {
            Statement statement = connection.createStatement();
            statement.execute(script);
            Integer linhasExcluidas = statement.getUpdateCount();

            assertTrue((linhasExcluidas > 0));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    private void insereDadosQueSeraoDeletados() {
        String nomeProduto = "Mouse";
        String descricaoProduto = "Mouse sem fio";
        String script = "insert into produto (nome, descricao) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(script, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, nomeProduto);
            statement.setString(2, descricaoProduto);

            statement.execute();
            ResultSet result = statement.getGeneratedKeys();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
