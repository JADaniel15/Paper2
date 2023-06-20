package Java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DadosAluno {
    private String nome;
    private String email;
    private int qtdAulas;
    private int valorAula;

    public DadosAluno(String nome, String email, int qtdAulas, int valorAula) {
        this.nome = nome;
        this.email = email;
        this.qtdAulas = qtdAulas;
        this.valorAula = valorAula;
    }

    public void inserirDados() {
        // Parâmetros de conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/nome_do_banco_de_dados";
        String username = "seu_usuario";
        String password = "sua_senha";

        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão com o banco de dados
            Connection connection = DriverManager.getConnection(url, username, password);

            // Prepara a query para inserir os dados na tabela
            String sql = "INSERT INTO sua_tabela (nome, email, qtdAulas, valorAula) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Define os parâmetros da query
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setInt(3, qtdAulas);
            statement.setInt(4, valorAula);

            // Executa a query
            statement.executeUpdate();

            System.out.println("Dados inseridos com sucesso!");

            // Fecha a conexão com o banco de dados
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Valores recebidos do formulário
        String nome = "John Doe";
        String email = "john.doe@example.com";
        int qtdAulas = 10;
        int valorAula = 50;

        // Cria um objeto DadosAluno e insere os dados
        DadosAluno aluno = new DadosAluno(nome, email, qtdAulas, valorAula);
        aluno.inserirDados();
    }
}
