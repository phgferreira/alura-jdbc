package br.com.alura.jdbc.lojavirtual.connection;

import br.com.alura.jdbc.lojavirtual.modelo.Produto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class InsertProdutoTest {

    @Test @DisplayName("Deve inserir um produto no banco de dados com sucesso")
    void cenario1() {

        Produto produto = new Produto("PRODUTO_TESTE_1","Descrição de teste");

        String script = "insert into produto (nome, descricao) values (?,?)";
        try (Connection connection = new ConnectionFactory().getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(script, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, produto.getNome());
                statement.setString(2, produto.getDescricao());
                statement.execute();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next())
                        produto.setId( resultSet.getInt(1) );
                }
            }
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        assertNotNull(produto.getId());
    }
}
