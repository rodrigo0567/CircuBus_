package Avaliação_POO;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TelaPrincipal extends JFrame {

        public TelaPrincipal(String nomeUsuario) {
                setTitle("Tela Principal");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(800, 600);
                setLayout(new BorderLayout());

                Color azulMarinho = new Color(0, 0, 102);
                getContentPane().setBackground(azulMarinho);

                JPanel panel = new JPanel(new BorderLayout());
                panel.setBackground(Color.WHITE);
                panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JLabel labelDataHora = new JLabel();
                labelDataHora.setForeground(Color.BLACK);

                panel.add(labelDataHora, BorderLayout.NORTH);
                add(panel, BorderLayout.NORTH);

                SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
                DateTimeFormatter horaFormatada = DateTimeFormatter.ofPattern("HH:mm:ss");

                Timer timer = new Timer(1000, e -> {
                        Date data = new Date();
                        labelDataHora.setText(dataFormatada.format(data) + " " + LocalTime.now().format(horaFormatada));
                });
                timer.start();

                JPanel panelVideo = new JPanel();
                panelVideo.setPreferredSize(new Dimension(640, 480));
                add(panelVideo, BorderLayout.CENTER);


                URL videoURL = getClass().getResource("bus-onibus.gif");


                if (videoURL != null) {
                        ImageIcon videoIcon = new ImageIcon(videoURL);

                        JLabel videoLabel = new JLabel(videoIcon);
                        videoLabel.setHorizontalAlignment(JLabel.CENTER);

                        panelVideo.add(videoLabel);

                        add(panelVideo, BorderLayout.CENTER);
                } else {
                        JOptionPane.showMessageDialog(this, "Erro ao carregar o vídeo", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
                panelBotoes.setBackground(Color.WHITE);

                JButton btnChat = new JButton("Chat");
                btnChat.setBackground(Color.CYAN);
                btnChat.setForeground(Color.WHITE);
                btnChat.addActionListener(e -> {
                        dispose();
                        SwingUtilities.invokeLater(() -> {
                                ChatAluno chatAluno = new ChatAluno(nomeUsuario);
                                chatAluno.mostrarChat();
                        });

                });
                panelBotoes.add(btnChat);

                JButton btnSair = new JButton("Sair");
                btnSair.setBackground(Color.CYAN);
                btnSair.setForeground(Color.WHITE);
                btnSair.addActionListener(e -> {
                        dispose();
                        SwingUtilities.invokeLater(() -> {
                                TelaDeLogin telaDeLogin = new TelaDeLogin();
                                telaDeLogin.mostrarTela();
                        });
                });
                panelBotoes.add(btnSair);
                add(panelBotoes, BorderLayout.SOUTH);

                setLocationRelativeTo(null);
        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> {
                        TelaPrincipal telaPrincipal = new TelaPrincipal("NomeDoUsuario");
                        telaPrincipal.setVisible(true);
                });
        }

        public void mostrarTela() {
                setVisible(true);
        }
}


