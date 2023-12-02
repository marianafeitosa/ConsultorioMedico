/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author mariana arashiro
 */
public class Conexao {
    final private String driver = "com.mysql.cj.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/sistemamedico";
    final private String usuario = "root";
    final private String senha = "";
    public Connection conexao;
   public Statement statement; //variavel que executa comandos sql dentro do ambiente java
    public ResultSet resultset; //variavel que armazenara o resultado da execucao de um comando sq1
    
    public boolean conecta() throws SQLException {
        boolean result = true;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conectou com o Banco");
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver n�o localizado" + Driver, "Mensagem do programa", JOptionPane.ERROR_MESSAGE);
            result = false;
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Fonte de dados n�o localizada!" + Fonte, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            result = false;
        }
        return result;
    }
 
    public void desconecta() {
        try {
            if (conexao != null) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Conex�o com o banco fechada", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException fecha) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar o banco", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
public void executaSQL(String sql) {
    try {
        statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultset = statement.executeQuery(sql);
    } catch (SQLException excecao) {
        JOptionPane.showMessageDialog(null, "Erro no comando SQL! \n Erro " + excecao, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
    }
}

    
    



   
    
    
    
    
}
