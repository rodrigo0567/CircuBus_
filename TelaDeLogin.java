package Avaliação_POO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TelaDeLogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public TelaDeLogin() {
        setTitle("Tela de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(0, 0, 64)); // Cor azul marinho escuro

        try {
            BufferedImage logoImage = ImageIO.read(new File("C:\\Users\\rodri\\Documents\\Atividades_POO\\Atividades\\src\\Avaliação_POO\\LOGOCIRCUBUS.png"));
            ImageIcon logoIcon = new ImageIcon(logoImage);
            ImageIcon logoIconResized = new ImageIcon(logoIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH)); // Tamanho médio
            JLabel logoLabel = new JLabel(logoIconResized);
            mainPanel.add(logoLabel, BorderLayout.NORTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setOpaque(false); // Torna o painel de login transparente
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        usernameField.setForeground(Color.BLACK);
        passwordField.setForeground(Color.BLACK);

        loginButton.setBackground(Color.CYAN);
        loginButton.setForeground(Color.WHITE);

        JPanel loginFieldsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        loginFieldsPanel.setOpaque(false);
        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setForeground(Color.WHITE);
        loginFieldsPanel.add(labelUsuario);
        loginFieldsPanel.add(usernameField);
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setForeground(Color.WHITE);
        loginFieldsPanel.add(labelSenha);
        loginFieldsPanel.add(passwordField);
        loginFieldsPanel.add(loginButton);

        loginPanel.add(loginFieldsPanel, BorderLayout.CENTER);
        mainPanel.add(loginPanel, BorderLayout.CENTER);
        JPanel logosPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        logosPanel.setOpaque(false);

        try {
            BufferedImage logoImage1 = ImageIO.read(new File("C:\\Users\\rodri\\Documents\\Atividades_POO\\Atividades\\src\\Avaliação_POO\\LogoUF.png"));
            ImageIcon logoIcon1 = new ImageIcon(logoImage1);
            ImageIcon logoIconResized1 = new ImageIcon(logoIcon1.getImage().getScaledInstance(40, 60, Image.SCALE_SMOOTH)); // Tamanho pequeno
            JLabel logoLabel1 = new JLabel(logoIconResized1);
            logosPanel.add(logoLabel1);
            BufferedImage logoImage2 = ImageIO.read(new File("C:\\Users\\rodri\\Documents\\Atividades_POO\\Atividades\\src\\Avaliação_POO\\LOGOCI.png"));
            ImageIcon logoIcon2 = new ImageIcon(logoImage2);
            ImageIcon logoIconResized2 = new ImageIcon(logoIcon2.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)); // Tamanho pequeno
            JLabel logoLabel2 = new JLabel(logoIconResized2);
            logosPanel.add(logoLabel2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainPanel.add(logosPanel, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (verificarLogin(username, password)) {
                    JOptionPane.showMessageDialog(TelaDeLogin.this, "Login bem-sucedido!");
                    dispose();

                    SwingUtilities.invokeLater(() -> {
                        ChatAluno chatAluno = new ChatAluno(username);
                    });
                } else {
                    JOptionPane.showMessageDialog(TelaDeLogin.this, "Login falhou. Tente novamente.");
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
    }

    private boolean verificarLogin(String username, String password) {
        return "Rodrigo".equals(username) && "rodrigo0567".equals(password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaDeLogin loginFrame = new TelaDeLogin();
            loginFrame.setVisible(true);
        });
    }
}
