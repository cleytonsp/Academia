package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DatabaseConnection.DatabaseConnection;
import Entidades.Instrutor;

public class InstrutorDAO {

    private Connection connection;

    // Construtor para inicializar a conexão com o banco de dados
    public InstrutorDAO() {
        this.connection = DatabaseConnection.getConexao(); // Supondo que você tenha uma ConnectionFactory para obter a conexão
    }

    // Método para adicionar um instrutor no banco de dados
    public void adicionarInstrutor(Instrutor instrutor) {
        String sql = "INSERT INTO instrutor (nome, cpf, especialidade) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, instrutor.getNome());
            stmt.setString(2, instrutor.getCpf());
            stmt.setString(3, instrutor.getEspecialidade());
            
            stmt.executeUpdate(); // Executa a inserção no banco de dados
            System.out.println("Instrutor " + instrutor.getNome() + " adicionado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar instrutor: " + instrutor.getNome(), e);
        }
    }

    // Método para buscar um instrutor pelo CPF
    public Instrutor buscarInstrutor(String cpf) {
        String sql = "SELECT id, nome, cpf, especialidade FROM instrutor WHERE cpf = ?";
        Instrutor instrutor = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf); // Define o CPF que será pesquisado
            ResultSet rs = stmt.executeQuery(); // Executa a consulta

            // Se encontrar um instrutor com o CPF, cria o objeto Instrutor
            if (rs.next()) {
                instrutor = new Instrutor(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("especialidade")
                );
                instrutor.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar instrutor com CPF: " + cpf, e);
        }

        return instrutor; // Retorna o instrutor encontrado ou null
    }

    // Método para listar todos os instrutores no banco de dados
    public List<Instrutor> listarTodos() {
        String sql = "SELECT * FROM INSTRUTOR";
        List<Instrutor> instrutores = new ArrayList<>();
        
        ResultSet rset = null;
        PreparedStatement ps = null;

        try {
            // Use a conexão já existente
            ps = connection.prepareStatement(sql);
            rset = ps.executeQuery();

            // Processando os resultados
            while (rset.next()) {
                Instrutor instrutor = new Instrutor();
                instrutor.setId(rset.getInt("id"));
                instrutor.setNome(rset.getString("nome"));
                // Defina os outros atributos do instrutor
                instrutores.add(instrutor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar instrutores", e);
        } finally {
            // Fechamento dos recursos no bloco finally para garantir que seja sempre fechado
            fecharConexao(rset, ps);
        }

        return instrutores;
    }

    private void fecharConexao(ResultSet rset, PreparedStatement ps) {
        try {
            if (rset != null) rset.close();
            if (ps != null) ps.close();
            // Não fechamos a conexão aqui, porque ela é compartilhada entre os métodos
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um instrutor no banco de dados
    public void atualizarInstrutor(Instrutor instrutor) {
        String sql = "UPDATE instrutor SET nome = ?, especialidade = ? WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, instrutor.getNome());
            stmt.setString(2, instrutor.getEspecialidade());
            stmt.setString(3, instrutor.getCpf());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Instrutor " + instrutor.getNome() + " atualizado com sucesso!");
            } else {
                System.out.println("Nenhum instrutor foi atualizado para o CPF: " + instrutor.getCpf());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o instrutor com CPF: " + instrutor.getCpf(), e);
        }
    }

    // Método para remover um instrutor do banco de dados
    public void removerInstrutor(String cpf) {
        String sql = "DELETE FROM instrutor WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf); // Define o CPF do instrutor a ser removido

            int rowsAffected = stmt.executeUpdate(); // Executa a remoção

            if (rowsAffected > 0) {
                System.out.println("Instrutor com CPF " + cpf + " removido com sucesso!");
            } else {
                System.out.println("Nenhum instrutor foi removido para o CPF: " + cpf);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover instrutor com CPF: " + cpf, e);
        }
    }
}