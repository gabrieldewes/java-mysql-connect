package br;

import java.sql.SQLException;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) throws SQLException {

        Contato contato = new Contato();
        //contato.setId((long) 12);
        contato.setNome("Gabriel");
        contato.setEmail("gabriel@dew.es");
        contato.setEndereco("Rua Federal");
        contato.setDataNascimento(Calendar.getInstance());

        ContatoDAO dao = new ContatoDAO();
        //dao.adiciona(contato);
        //dao.altera(contato);
        //dao.remove(contato);
        //dao.busca();
    }
}
