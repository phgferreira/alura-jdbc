package br.com.alura.jdbc.lojavirtual.connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.zip.ZipOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionTest {

    @Test @DisplayName("Deve conectar com o banco de dados MySQL")
    void cenario1() {
        String url = "jdbc:mysql://localhost/loja_virtual?userTimezone=true&serverTimezone=UTC";
        String user = "dbauser";
        String password = "p@ssw0rd";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.close();
            // Se não ocorrer erro em alguma tarefa acima então o teste foi bem-sucedido
            assertTrue(true);
        } catch (SQLException e) {
            fail(e);
        }

    }

    @Test @DisplayName("Deve exibir uma excessão quando tenta uma conexão com URL inválida")
    void cenario2() {
        String url = "aaajdbc:mysql://localhost/loja_virtual?userTimezone=true&serverTimezone=UTC";
        String user = "dbauser";
        String password = "p@ssw0rd";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.close();

            fail("Não exibiu excessão de URL inválida");
        } catch (SQLException e) {
            assertEquals("No suitable driver found for aaajdbc:mysql://localhost/loja_virtual?userTimezone=true&serverTimezone=UTC", e.getMessage());
        }
    }

    @Test @DisplayName("Deve exibir uma excessão quando tenta uma conexão com usuário/senha inválidos")
    void cenario3() {
        String url = "jdbc:mysql://localhost/loja_virtual?userTimezone=true&serverTimezone=UTC";
        String user = "aaadbauser";
        String password = "aaap@ssw0rd";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.close();

            fail("Não exibiu excessão de URL inválida");
        } catch (SQLException e) {
            assertEquals("Access denied for user 'aaadbauser'@'localhost' (using password: YES)", e.getMessage());
        }
    }

}
