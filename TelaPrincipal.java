package Avaliação_POO;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TelaPrincipal extends ControleTela {

        public TelaPrincipal(String nomeUsuario) {
                super();

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

                String caminhoArquivoGIF = "C:\\Users\\rodri\\Documents\\Atividades_POO\\Atividades\\src\\Avaliação_POO\\bus_onibus.gif";

                File arquivoGIF = new File(caminhoArquivoGIF);

                if (arquivoGIF.exists()) {
                        try {
                                ImageIcon videoIcon = new ImageIcon(caminhoArquivoGIF);
                                URL videoURL = arquivoGIF.toURI().toURL();
                                configurarVideo(videoURL);
                        } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(this, "Erro ao carregar o vídeo", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                } else {
                        JOptionPane.showMessageDialog(this, "Arquivo GIF não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
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

                panelBotoes.add(btnChat);
                panelBotoes.add(btnSair);
                add(panelBotoes, BorderLayout.SOUTH);
        }

        @Override
        public void configurarVideo(URL videoURL) {
                JPanel panelVideo = new JPanel();
                panelVideo.setPreferredSize(new Dimension(800, 600));
                panelVideo.setLayout(new BorderLayout());

                if (videoURL != null) {
                        ImageIcon videoIcon = new ImageIcon(videoURL);
                        JLabel videoLabel = new JLabel(videoIcon);
                        panelVideo.add(videoLabel, BorderLayout.CENTER);
                        add(panelVideo, BorderLayout.CENTER);
                } else {
                        JOptionPane.showMessageDialog(this, "Erro ao carregar o vídeo", "Erro", JOptionPane.ERROR_MESSAGE);
                }
        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> {
                        TelaPrincipal telaPrincipal = new TelaPrincipal("NomeDoUsuario");
                        telaPrincipal.mostrarTela();
                });
        }

        public void mostrarTela() {
                setVisible(true);
        }
}
