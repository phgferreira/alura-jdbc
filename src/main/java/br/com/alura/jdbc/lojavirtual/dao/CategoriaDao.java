package br.com.alura.jdbc.lojavirtual.dao;

import br.com.alura.jdbc.lojavirtual.modelo.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    private Connection connection;

    public CategoriaDao(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> lsitar() {
        List<Categoria> categorias = new ArrayList<>();
        String query = "select id_categoria, nome from categoria";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next())
                categorias.add( new Categoria(
                        resultSet.getInt("id_categoria"),
                        resultSet.getString("nome")) );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categorias;
    }
}
