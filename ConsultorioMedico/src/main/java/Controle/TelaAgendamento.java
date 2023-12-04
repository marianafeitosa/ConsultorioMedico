/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Conexao.Conexao;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mariana arashiro
 */
public class TelaAgendamento extends JFrame {
    Conexao conexaoConsultas;

    JTable tblConsultas;
    JScrollPane scpTabela;
    JLabel bgImg;

    public TelaAgendamento() throws SQLException {
        conexaoConsultas = new Conexao();
        conexaoConsultas.conecta();

        setTitle("Consultas Agendadas");
        setResizable(false);
        Container tela = getContentPane();
        tela.setLayout(null);
        
        
        //imagem fundo
           bgImg = new JLabel(new ImageIcon("src/imagens/fundoprincipal.png"));

        
         ImageIcon icone = new ImageIcon("src/imagens/medico.png");
        setIconImage(icone.getImage());

       
        // Adicionando JLabel com o título personalizado
        JLabel lblTitulo = new JLabel("Consultas Agendadas");
        lblTitulo.setFont(new Font("Poppins", Font.BOLD, 18)); // Aumentando o tamanho da fonte
        lblTitulo.setBounds((getWidth() -150) / 2, 10, 200, 30); // Centralizando na tela
        tela.add(lblTitulo);
        
        
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

        tblConsultas.setBounds(50, 50, 680, 200);
        scpTabela.setBounds(50, 50, 680, 200);

        tela.add(tblConsultas);
        tela.add(scpTabela);
        tela.add(bgImg);

        scpTabela.setViewportView(tblConsultas);

        tblConsultas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblConsultas.setFont(new Font("Poppins", Font.BOLD, 10)); // Definindo a fonte como Poppins

        tblConsultas.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}},
                new String[]{"Nome Paciente", "Especialidade", "Nome Médico", "Data Consulta", "Horário Consulta", "Email"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        setSize(800, 700);
        setVisible(true);
        setLocationRelativeTo(null);

        conexaoConsultas.executaSQL("SELECT * FROM consultas");
        preencherTabela();
        posicionarRegistro();
    }

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
            while (conexaoConsultas.resultset.next()) {
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
        // Adaptar conforme os campos reais da sua tabela
        // textFieldNomePaciente.setText(conexaoConsultas.resultset.getString("NomePaciente"));
        // textFieldEspecialidade.setText(conexaoConsultas.resultset.getString("Especialidade"));
        // textFieldNomeMedico.setText(conexaoConsultas.resultset.getString("NomeMedico"));
        // formattedTextFieldDataConsulta.setText(conexaoConsultas.resultset.getString("DataConsulta"));

    }

    public static void main(String[] args) throws SQLException {
        TelaAgendamento telaAgendamento = new TelaAgendamento();
        telaAgendamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}