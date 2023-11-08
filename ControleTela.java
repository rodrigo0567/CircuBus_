package Avaliação_POO;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class ControleTela extends JFrame implements Relógio {
    protected JLabel labelDataHora; // Adiciona o JLabel como um membro protegido

    public ControleTela() {
        setTitle("Tela Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        Color azulMarinho = new Color(0, 0, 102);
        getContentPane().setBackground(azulMarinho);

        labelDataHora = new JLabel(); // Inicializa o JLabel
        labelDataHora.setForeground(Color.BLACK);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(labelDataHora, BorderLayout.NORTH);

        add(panel, BorderLayout.NORTH);
        setLocationRelativeTo(null);
    }

    // Método abstrato da interface Relogio
    public abstract void configurarVideo(URL videoURL);

    // Métodos da interface Relogio
    public void atualizarRelogio() {
        // Este método pode ser vazio aqui e implementado nas classes filhas
    }
}
