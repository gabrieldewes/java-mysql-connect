package br;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ContatoDAO {
	private Connection connection;

        public ContatoDAO() {
            new FabricaConexao();
            this.connection = (Connection) FabricaConexao.getConnection();
        }

        public void adiciona(Contato contato) {
            String sql = "INSERT INTO contatos " +
                        "(nome, email, endereco, dataNascimento)" +
                        " VALUES (?,?,?,?)";
            try {
                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
                stmt.setString(1, contato.getNome());
                stmt.setString(2, contato.getEmail());
                stmt.setString(3, contato.getEndereco());
                stmt.setDate(4, new java.sql.Date(
                    contato.getDataNascimento().getTimeInMillis()));
                System.out.println(stmt);
                stmt.execute();
                stmt.close();
                System.out.println("Adicionado!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void busca() throws SQLException{
            String sql = "select * from contatos";

            Connection con = (Connection) FabricaConexao.getConnection();
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                System.out.println(id + " - "+ nome + " : " + email);
            }
            stmt.close();
            con.close();
        }

        public void altera(Contato contato) {
            String sql = "UPDATE contatos SET nome=?, email=?,"+
                "endereco=?, dataNascimento=? WHERE id=?";

            try {
                PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
                stmt.setString(1, contato.getNome());
                stmt.setString(2, contato.getEmail());
                stmt.setString(3, contato.getEndereco());
                stmt.setDate(4, new java.sql.Date(contato.getDataNascimento()
                .getTimeInMillis()));
                stmt.setLong(5, contato.getId());
                System.out.println(stmt);
                stmt.execute();
                stmt.close();
                System.out.println("Alterado.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void remove(Contato contato) {
            try {
                PreparedStatement stmt = (PreparedStatement) connection
                .prepareStatement("DELETE FROM contatos WHERE id=?");
                stmt.setLong(1, contato.getId());
                System.out.println(stmt);
                stmt.execute();
                stmt.close();
                System.out.println("Removido.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}
