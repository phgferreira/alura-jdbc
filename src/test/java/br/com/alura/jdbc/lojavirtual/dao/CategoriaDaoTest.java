package br.com.alura.jdbc.lojavirtual.dao;

import br.com.alura.jdbc.lojavirtual.connection.ConnectionFactory;
import br.com.alura.jdbc.lojavirtual.dao.CategoriaDao;
import br.com.alura.jdbc.lojavirtual.modelo.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaDaoTest {

    private Connection connection;

    @BeforeEach
    void beforeEach() {
        try {
            connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test @DisplayName("Deve listar categorias")
    void listTest() {
        CategoriaDao dao = new CategoriaDao(connection);
        List<Categoria> categorias = dao.lsitar();

        assertTrue( (categorias.size() > 0) );
    }

}