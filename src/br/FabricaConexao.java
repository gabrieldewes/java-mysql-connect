package br;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Gabriel on 16/03/2016.
 */
public class FabricaConexao {

    public static final String usuario="root";
    public static final String senha="";
    public static final String banco="testeconexao";
    public static final String ip="localhost";
    public static final String driver="com.mysql.jdbc.Driver";
    private static Connection conexao = null;

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            if(conexao==null || conexao.isClosed()){
                conexao = DriverManager.getConnection("jdbc:mysql://"+ ip +"/"+ banco +"", usuario, senha);
                System.out.println("Conectando ao banco "+ ip +"/"+ banco +"...");
            }
            return conexao;
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (SQLException e) {
            closeConnection();
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(){
        try{
            if(conexao!=null && !conexao.isClosed()){
                conexao.close();
                System.out.println("Conexão encerrada.");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("conexao: "+getConnection());
        System.out.println("conexao: "+getConnection());
        System.out.println("conexao: "+getConnection());
    }

}
