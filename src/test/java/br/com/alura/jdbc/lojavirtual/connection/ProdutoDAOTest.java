package br.com.alura.jdbc.lojavirtual.connection;

import br.com.alura.jdbc.lojavirtual.dao.ProdutoDAO;
import br.com.alura.jdbc.lojavirtual.modelo.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoDAOTest {

    private ProdutoDAO dao;

    @BeforeEach
    void beforeEach() {
        try {
            dao = new ProdutoDAO(new ConnectionFactory().getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test @DisplayName("Deve inserir um produto no banco de dados com sucesso")
    void saveTest() {
        Produto produto = new Produto("PRODUTO_TESTE_1","Descrição de teste");
        dao.salvar(produto);

        assertNotNull(produto.getId());
    }

    @Test @DisplayName("Deve retornar uma lista de produtos")
    void listTest() {
        List<Produto> produtos = dao.listar();
        assertTrue( (produtos.size() > 0) );
    }
}