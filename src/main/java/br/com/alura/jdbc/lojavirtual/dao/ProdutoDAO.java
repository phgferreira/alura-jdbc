package br.com.alura.jdbc.lojavirtual.dao;

import br.com.alura.jdbc.lojavirtual.modelo.Produto;

import java.sql.*;

public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) {
        String script = "insert into produto (nome, descricao) values (?,?)";
        try {

            try (PreparedStatement statement = connection.prepareStatement(script, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, produto.getNome());
                statement.setString(2, produto.getDescricao());
                statement.execute();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next())
                        produto.setId( resultSet.getInt(1) );
                }
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
