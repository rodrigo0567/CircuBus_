package Avaliação_POO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatAluno {
    private String nomeUsuario;
    private static final String ENDERECO_SERVIDOR = "localhost";
    private static final int PORTA_SERVIDOR = 12345;
    private PrintWriter out;
    private BufferedReader in;

    private JTextArea areaChat;
    private JTextField campoMensagem;
    private JFrame frame;

    public ChatAluno(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        frame = new JFrame("Chat Aluno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        try {
            BufferedImage logoImage = ImageIO.read(new File("C:\\Users\\rodri\\Documents\\Atividades_POO\\Atividades\\src\\Avaliação_POO\\LOGOCIRCUBUS.png"));
            ImageIcon logoIcon = new ImageIcon(logoImage);
            ImageIcon logoIconResized = new ImageIcon(logoIcon.getImage().getScaledInstance(150, 130, Image.SCALE_SMOOTH));
            JLabel logoLabel = new JLabel(logoIconResized);
            JPanel logoPanel = new JPanel();
            logoPanel.add(logoLabel);
            mainPanel.add(logoPanel, BorderLayout.WEST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        areaChat = new JTextArea(20, 40);
        areaChat.setEditable(false);
        areaChat.setWrapStyleWord(true);
        areaChat.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(areaChat);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        campoMensagem = new JTextField(30);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(campoMensagem, BorderLayout.WEST);

        JButton botaoEnviar = new JButton("Enviar");
        botaoEnviar.setBackground(Color.CYAN);
        botaoEnviar.setForeground(Color.WHITE);
        bottomPanel.add(botaoEnviar, BorderLayout.EAST);

        JButton botaoSair = new JButton("Sair");
        botaoSair.setBackground(Color.CYAN);
        botaoSair.setForeground(Color.WHITE);
        mainPanel.add(botaoSair, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMensagem(campoMensagem.getText());
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fecha a janela do chat do aluno
                SwingUtilities.invokeLater(() -> {
                    TelaPrincipal telaPrincipal = new TelaPrincipal(nomeUsuario);
                    telaPrincipal.setVisible(true);
                });
            }
        });

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        conectarAoServidor();
    }

    private void conectarAoServidor() {
        try {
            Socket socket = new Socket(ENDERECO_SERVIDOR, PORTA_SERVIDOR);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread threadReceberMensagens = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String linha;
                        while ((linha = in.readLine()) != null) {
                            final String mensagemRecebida = linha;
                            SwingUtilities.invokeLater(() -> {
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                                String hora = sdf.format(new Date());
                                exibirMensagem(nomeUsuario + " [" + hora + "]: " + mensagemRecebida);
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadReceberMensagens.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exibirMensagem(String mensagem) {
        areaChat.append(mensagem + "\n");
    }

    private void enviarMensagem(String mensagem) {
        if (!mensagem.isEmpty()) {
            out.println(mensagem);
            campoMensagem.setText("");
        }
    }

    public static void main(String[] args) {
        String nomeUsuario = JOptionPane.showInputDialog("Informe seu nome de usuário:");
        SwingUtilities.invokeLater(() -> {
            ChatAluno chatAluno = new ChatAluno(nomeUsuario);
        });
    }
}
