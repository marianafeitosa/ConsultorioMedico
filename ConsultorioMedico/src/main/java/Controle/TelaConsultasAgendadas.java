/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Conexao.Conexao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author mariana arashiro
 */

public class TelaConsultasAgendadas extends JFrame {
    Conexao conexaoConsultas;

    JLabel rId, rNomePaciente, rEspecialidade, rNomeMedico, rDataConsulta, rHorarioConsulta, rEmail, rPesquisa, rNavegacao, bgImg;
    JTextField textFieldId, textFieldNomePaciente, textFieldEspecialidade, textFieldNomeMedico, textFieldHorarioConsulta, textFieldEmail;
    JFormattedTextField formattedTextFieldDataConsulta;
    MaskFormatter maskFormatterDataConsulta;
    JTextField textFieldPesquisa;
    JButton prim, anterior, prox, ultimo, novoReg, gravar, alterar, excluir, pesquisar;

    JTable tblConsultas;
    JScrollPane scpTabela;

    public TelaConsultasAgendadas() throws SQLException {
        conexaoConsultas = new Conexao();
        conexaoConsultas.conecta();

        setTitle("Agendamento");
        setResizable(false);
        Container tela = getContentPane();
        tela.setLayout(null);

        ImageIcon icone = new ImageIcon("src/img/gato.png");
        setIconImage(icone.getImage());

        bgImg = new JLabel(new ImageIcon("src/img/Background.png"));

        rId = new JLabel("ID: ");
        rNomePaciente = new JLabel("Nome Paciente: ");
        rEspecialidade = new JLabel("Especialidade: ");
        rNomeMedico = new JLabel("Nome Médico: ");
        rDataConsulta = new JLabel("Data Consulta: ");
        rHorarioConsulta = new JLabel("Horário Consulta: ");
        rEmail = new JLabel("Email: ");
        rPesquisa = new JLabel("Pesquisar pela especialidade: ");
        rNavegacao = new JLabel("Botões de navegação:");

        textFieldId = new JTextField(5);
        textFieldNomePaciente = new JTextField(5);
        textFieldEspecialidade = new JTextField(5);
        textFieldNomeMedico = new JTextField(5);

        try {
            maskFormatterDataConsulta = new MaskFormatter("##/##/####");
            maskFormatterDataConsulta.setPlaceholderCharacter('_');
        } catch (Exception e) {
            e.printStackTrace();
        }

        formattedTextFieldDataConsulta = new JFormattedTextField(maskFormatterDataConsulta);
        textFieldHorarioConsulta = new JTextField(5);
        textFieldEmail = new JTextField(5);

        textFieldPesquisa = new JTextField(5);

        prim = new JButton("Primeiro");
        ultimo = new JButton("Último");
        pesquisar = new JButton("Pesquisar");

        String icones[] = {"src/imagens/dir.png", "src/imagens/esq.png"};
        ImageIcon imagem[] = new ImageIcon[2];
        for (int i = 0; i < 2; i++) {
            imagem[i] = new ImageIcon(icones[i]);
        }
        prox = new JButton(imagem[0]);
        anterior = new JButton(imagem[1]);

        novoReg = new JButton("Novo");
        gravar = new JButton("Gravar");
        alterar = new JButton("Alterar");
        excluir = new JButton("Excluir");

        tblConsultas = new JTable() {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : (new Color(220, 220, 220)));
                }
                return c;
            }
        };

        scpTabela = new JScrollPane();

        tblConsultas.setBounds(50, 320, 680, 200);
        scpTabela.setBounds(50, 320, 680, 200);

        tela.add(tblConsultas);
        tela.add(scpTabela);
       
        tela.add(rNomePaciente);
        tela.add(rEspecialidade);
        tela.add(rNomeMedico);
         tela.add(rDataConsulta);
          tela.add(rHorarioConsulta);
           tela.add(rEmail);
            tela.add(rPesquisa);
             tela.add(rNavegacao);
         
              tela.add( textFieldNomePaciente);
              tela.add( textFieldEspecialidade);
              tela.add( textFieldNomeMedico);
              tela.add( formattedTextFieldDataConsulta);
              tela.add( textFieldHorarioConsulta);
              tela.add( textFieldEmail);
              tela.add( textFieldPesquisa);
              tela.add( prim);
              tela.add( ultimo);
              tela.add( pesquisar);
              tela.add( prox);
              tela.add( anterior);
              tela.add( novoReg);
              tela.add( gravar);
              tela.add( alterar);
              tela.add( excluir);
              
              
        

        scpTabela.setViewportView(tblConsultas);

        tblConsultas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblConsultas.setFont(new java.awt.Font("Arial", Font.BOLD, 10));

     tblConsultas.setModel(new javax.swing.table.DefaultTableModel(
    new Object[][]{
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null}},
    new String[]{ "Nome Paciente", "Especialidade", "Nome Médico", "Data Consulta", "Horário Consulta", "Email"}
) {
    boolean[] canEdit = new boolean[]{ false, false, false, false, false, false};

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }
});


        scpTabela.setViewportView(tblConsultas);

        bgImg.setBounds(0, 0, 800, 700);
        rNavegacao.setBounds(485, -5, 550, 100);
        rId.setBounds(50, 40, 100, 20);
        rNomePaciente.setBounds(50, 80, 100, 20);
        rEspecialidade.setBounds(50, 120, 100, 20);
        rNomeMedico.setBounds(50, 160, 100, 20);
        rDataConsulta.setBounds(50, 200, 100, 20);
        rHorarioConsulta.setBounds(50, 240, 100, 20);
        rEmail.setBounds(50, 280, 100, 20);
        rPesquisa.setBounds(50, 440, 550, 200);

        textFieldId.setBounds(150, 40, 50, 20);
        textFieldNomePaciente.setBounds(150, 80, 170, 20);
        textFieldEspecialidade.setBounds(150, 120, 120, 20);
        textFieldNomeMedico.setBounds(150, 160, 120, 20);
        formattedTextFieldDataConsulta.setBounds(150, 200, 120, 20);
        textFieldHorarioConsulta.setBounds(150, 240, 80, 20);
        textFieldEmail.setBounds(150, 280, 300, 20);

        prim.setBounds(513, 180, 85, 20);
        ultimo.setBounds(513, 210, 85, 20);
        prox.setBounds(568, 140, 30, 30);
        anterior.setBounds(513, 140, 30, 30);
        novoReg.setBounds(513, 60, 70, 30);
        gravar.setBounds(593, 60, 70, 30);
        alterar.setBounds(513, 100, 70, 30);
        excluir.setBounds(593, 100, 70, 30);
        pesquisar.setBounds(250, 530, 100, 30);

        tela.setBackground(Color.LIGHT_GRAY);
        rId.setFont(new Font("Calibri", Font.BOLD, 16));
        rNomePaciente.setFont(new Font("Calibri", Font.BOLD, 14));
        rEspecialidade.setFont(new Font("Calibri", Font.BOLD, 14));
        rNomeMedico.setFont(new Font("Calibri", Font.BOLD, 14));
        rPesquisa.setFont(new Font("Calibri", Font.BOLD, 14));
        rNavegacao.setFont(new Font("Calibri", Font.BOLD, 16));

        // Adicione aqui os listeners dos botões de navegação, novo, gravar, alterar, excluir e pesquisar
prox.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            if (conexaoConsultas.resultset.isLast()) {
                JOptionPane.showMessageDialog(null, "Não existem registros depois do último! ", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                conexaoConsultas.resultset.next();
                mostrar_Dados();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o registro: " + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});

anterior.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            if (conexaoConsultas.resultset.isFirst()) {
                JOptionPane.showMessageDialog(null, "Não existem registros antes do primeiro registro! ", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                conexaoConsultas.resultset.previous();
                mostrar_Dados();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o registro: " + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});

prim.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            conexaoConsultas.resultset.first();
            mostrar_Dados();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o registro: " + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});

ultimo.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            conexaoConsultas.resultset.last();
            mostrar_Dados();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o registro: " + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});

novoReg.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        textFieldId.setText("");
        textFieldNomePaciente.setText("");
        textFieldEspecialidade.setText("");
        textFieldNomeMedico.setText("");
        formattedTextFieldDataConsulta.setText("");
        textFieldHorarioConsulta.setText("");
        textFieldEmail.setText("");
        textFieldNomePaciente.requestFocus();
    }
});

gravar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String nomePaciente = textFieldNomePaciente.getText();
        String especialidade = textFieldEspecialidade.getText();
        String nomeMedico = textFieldNomeMedico.getText();
        String dataConsulta = formattedTextFieldDataConsulta.getText();
        String horarioConsulta = textFieldHorarioConsulta.getText();
        String email = textFieldEmail.getText();

        // Check if any field is empty
        if (nomePaciente.isEmpty() || especialidade.isEmpty() || nomeMedico.isEmpty()
                || dataConsulta.isEmpty() || horarioConsulta.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "\nErro na gravação: \nCampo vazio");
        } else {
           String insertSql = "INSERT INTO consultas (NomePaciente, Especialidade, NomeMedico, DataConsulta, HorarioConsulta, Email) VALUES (?, ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?)";

try (PreparedStatement preparedStatement = conexaoConsultas.conexao.prepareStatement(insertSql)) {
    preparedStatement.setString(1, nomePaciente);
    preparedStatement.setString(2, especialidade);
    preparedStatement.setString(3, nomeMedico);
    preparedStatement.setString(4, dataConsulta); // A data ainda está no formato 'DD/MM/YYYY'
    preparedStatement.setString(5, horarioConsulta);
    preparedStatement.setString(6, email);

    // Execute the insert command
    preparedStatement.executeUpdate();

    JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso!", "Mensagem do programa",
            JOptionPane.INFORMATION_MESSAGE);

    // Execute the select command to refresh the data
    conexaoConsultas.executaSQL("SELECT * FROM consultas ORDER BY id");

    // Update the table
    preencherTabela();
} catch (SQLException ex) {
    Logger.getLogger(TelaConsultasAgendadas.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(null, "\nErro na gravação: \n" + ex.getMessage(),
            "Mensagem do programa: ", JOptionPane.INFORMATION_MESSAGE);
}

        }
    }
});





alterar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String id = textFieldId.getText();
        String nomePaciente = textFieldNomePaciente.getText();
        String especialidade = textFieldEspecialidade.getText();
        String nomeMedico = textFieldNomeMedico.getText();
        String dataConsulta = formattedTextFieldDataConsulta.getText();
        String horarioConsulta = textFieldHorarioConsulta.getText();
        String email = textFieldEmail.getText();
        String sql;
        String msg = "";

        if (id.equals("")) {
            sql = "insert into consultas (NomePaciente, Especialidade, NomeMedico, DataConsulta, HorarioConsulta, Email) values "
                    + "('" + nomePaciente + "','" + especialidade + "','" + nomeMedico + "','" + dataConsulta + "','" + horarioConsulta + "','" + email + "')";
            msg = "Gravação de um novo registro";
        } else {
            sql = "update consultas set NomePaciente='" + nomePaciente + "',Especialidade='" + especialidade + "',NomeMedico='" + nomeMedico +
                    "',DataConsulta='" + dataConsulta + "',HorarioConsulta='" + horarioConsulta + "',Email='" + email + "' where id =" + id;
            msg = "Alteração de registro";
        }
        try {
            conexaoConsultas.statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(TelaConsultasAgendadas.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, msg + " realizada com sucesso!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        conexaoConsultas.executaSQL("select *from consultas order by id");
        preencherTabela();
    }
});

excluir.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String sql = "";
        try {
            int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja excluir o registro: ", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, 3);
            if (resposta == 0) {
                sql = "delete from consultas where id = " + textFieldId.getText();
                int excluir = conexaoConsultas.statement.executeUpdate(sql);
                if (excluir == 1) {
                    JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                    conexaoConsultas.executaSQL("select * from consultas order by id");
                    conexaoConsultas.resultset.first();
                    preencherTabela();
                    posicionarRegistro();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException excecao) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão: " + excecao, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});

pesquisar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            String pesquisa = "select * from consultas where especialidade like '%" + textFieldPesquisa.getText() + "%'";
            conexaoConsultas.executaSQL(pesquisa);

            if (conexaoConsultas.resultset.first()) {
                preencherTabela();
            } else {
                JOptionPane.showMessageDialog(null, "\n Não existe dados com este parâmetro!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException errosql) {
            JOptionPane.showMessageDialog(null, "\n Os dados digitados não foram localizados: \n" + errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});

        
        
        

        setSize(800, 700);
        setVisible(true);
        setLocationRelativeTo(null);

        conexaoConsultas.executaSQL("SELECT * FROM consultas");
        preencherTabela();
        posicionarRegistro();
        
       
    }

    // Métodos preencherTabela, posicionarRegistro, e outros que você precisar permanecem iguais
    
    public void preencherTabela() {
    tblConsultas.getColumnModel().getColumn(0).setPreferredWidth(170);
    tblConsultas.getColumnModel().getColumn(1).setPreferredWidth(100);
    tblConsultas.getColumnModel().getColumn(2).setPreferredWidth(90);
    tblConsultas.getColumnModel().getColumn(3).setPreferredWidth(80);
    tblConsultas.getColumnModel().getColumn(4).setPreferredWidth(150);
    tblConsultas.getColumnModel().getColumn(5).setPreferredWidth(70);

    DefaultTableModel modelo = (DefaultTableModel) tblConsultas.getModel();
    modelo.setNumRows(0);

    try {
        conexaoConsultas.resultset.beforeFirst();
        while ( conexaoConsultas.resultset.next()) {
            modelo.addRow(new Object[]{
                conexaoConsultas.resultset.getString("NomePaciente"),
               conexaoConsultas.resultset.getString("Especialidade"),
                conexaoConsultas.resultset.getString("NomeMedico"),
                conexaoConsultas.resultset.getString("DataConsulta"),
                conexaoConsultas.resultset.getString("HorarioConsulta"),
                conexaoConsultas.resultset.getString("Email")
            });
        }
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "\n Erro ao listar dados da tabela!!: \n" + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
    }
}

public void posicionarRegistro() {
    try {
       conexaoConsultas.resultset.first();
        mostrar_Dados();
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Não foi possível posicionar no primeiro registro: " + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
    }
}

public void mostrar_Dados() {
    try {
          textFieldNomePaciente.setText(conexaoConsultas.resultset.getString("NomePaciente"));
          textFieldEspecialidade.setText(conexaoConsultas.resultset.getString("Especialidade"));
          textFieldNomeMedico.setText(conexaoConsultas.resultset.getString("NomeMedico"));
          formattedTextFieldDataConsulta.setText(conexaoConsultas.resultset.getString("DataConsulta"));
        
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Não localizou dados: " + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
    }
}


    public static void main(String[] args) throws SQLException {
        TelaConsultasAgendadas tela = new TelaConsultasAgendadas();
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}