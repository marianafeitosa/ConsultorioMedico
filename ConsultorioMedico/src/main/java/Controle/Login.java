/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Conexao.Conexao;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author mariana arashiro
 */
public class Login extends JFrame {

    private Conexao conexao;
    private JTextField tfUsuario;
    private JPasswordField pfSenha;
    private JButton btnLogin; // Usando o bot�o arredondado
    private JLabel mensagemErro;

    public Login() throws SQLException {
        conexao = new Conexao();
        conexao.conecta();
        initComponents();
    }

    private void initComponents() {
        setLayout(null);  // Defina o layout como nulo para posicionamento absoluto
    
        // Imagem de fundo
        ImagePanel backgroundPanel = new ImagePanel("src/imagens/fundologin.jpg"); // Substitua pelo caminho real da sua imagem de fundo
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
        add(backgroundPanel);
 
        // Defina o painel de conteúdo como um painel transparente
        setContentPane(new JPanel(null));

        // Configurações gerais do JFrame
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        Container tela = getContentPane();

        // Configuração do ícone
        ImageIcon icone = new ImageIcon("src/imagens/Logo.png");
        setIconImage(icone.getImage());

        // Configuração dos ícones para usuário e senha
        ImageIcon usua = new ImageIcon("src/imagens/pessoa.png");
        ImageIcon se = new ImageIcon("src/imagens/cadeado.png");

        // Configuração do campo de usuário
        tfUsuario = new JTextField(10);
        tfUsuario.setBounds(140, 80, 150, 30);
        tela.add(tfUsuario);

        // Configuração do campo de senha
        pfSenha = new JPasswordField(10);
        pfSenha.setBounds(140, 120, 150, 30);
        tela.add(pfSenha);

        // Configuração dos rótulos
        JLabel usuario = new JLabel(usua);
        usuario.setBounds(95, 75, 50, 40);
        tela.add(usuario);

        JLabel senha = new JLabel(se);
        senha.setBounds(95, 115, 50, 40);
        tela.add(senha);

        // Configuração do botão de login
        btnLogin = new JButton("Entrar");
        btnLogin.setBackground(new Color(228, 141, 122)); // Cor do botão (azul)
        btnLogin.setForeground(Color.WHITE); // Cor do texto do botão
        btnLogin.setBounds(165, 170, 100, 40);
        btnLogin.setHorizontalTextPosition(JButton.CENTER);
        btnLogin.setVerticalTextPosition(JButton.CENTER);
        btnLogin.addActionListener((ActionEvent e) -> realizarLogin());
        tela.add(btnLogin);

        // Configuração da mensagem de erro
        mensagemErro = new JLabel();
        mensagemErro.setForeground(Color.RED); // Cor do texto de erro
        mensagemErro.setBounds(100, 237, 300, 20); // Posição da mensagem de erro
        tela.add(mensagemErro);
        
        
          tela.add(backgroundPanel);
        setSize(450, 300); // Aumento do tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela no meio
       

        setVisible(true);
    }

    private void realizarLogin() {
 try {
        String usuario = tfUsuario.getText();
        String senha = new String(pfSenha.getPassword());

        String pesquisa = "SELECT * FROM usuario WHERE usuario = '" + usuario + "' AND senha = '" + senha + "'";
        conexao.executaSQL(pesquisa);

            if (conexao.resultset.next()) {
                String tipoUsuario = conexao.resultset.getString("TipoUsuario");

                if ("Paciente".equals(tipoUsuario)) {
                    // Acesso permitido para Paciente
                    JOptionPane.showMessageDialog(null, "Login Efetuado com sucesso \n Seja Bem Vindo: " + tfUsuario.getText());
 
                    // Redirecione para a tela de agendamento de consultas
                    TelaAgendamento telaAgendamento = new TelaAgendamento();
                    telaAgendamento.setVisible(true);
                    dispose();
                } else if ("Medico".equals(tipoUsuario)) {
                    // Acesso permitido para M�dico
                    JOptionPane.showMessageDialog(null, "Login Efetuado com sucesso \n Seja Bem Vindo: " + tfUsuario.getText());

                    // Redirecione para a tela de consultas agendadas
                    TelaConsultasAgendadas telaConsultasAgendadas = new TelaConsultasAgendadas();
                    telaConsultasAgendadas.setVisible(true);
                    dispose();
                } else {
                    // Tipo de usu�rio desconhecido
                    mensagemErro.setText("Tipo de usu�rio desconhecido.");
                }
            } else {
                // Acesso negado
                mensagemErro.setText("Usu�rio ou senha incorretos.");

            }
        } catch (SQLException excecao) {
            // Trate exce��es aqui
            excecao.printStackTrace();
        }

    }
    
    

    public static void main(String[] args) throws SQLException {
        Login app = new Login();
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
     public class ImagePanel extends JPanel {

        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            try {
                backgroundImage = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
