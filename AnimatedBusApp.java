package Avaliação_POO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

public class AnimatedBusApp extends JPanel {
    private BufferedImage busImage;
    private int busX, busY;
    private Timer timer;
    private Clip carSound;
    private boolean playSound = true;
    private boolean redirected = false;

    public AnimatedBusApp() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(0, 0, 64));

        try {
            InputStream busImageStream = getClass().getResourceAsStream("bus.png");
            busImage = ImageIO.read(busImageStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (busImage == null) {
            System.err.println("Erro ao carregar a imagem do ônibus.");
        }

        busX = (0) ;
        busY = (getHeight() +busImage.getHeight()) +50 ;

        try {
            InputStream audioStream = getClass().getResourceAsStream("bus_som.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioStream);
            carSound = AudioSystem.getClip();
            carSound.open(audioInputStream);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

        if (carSound == null) {
            System.err.println("Erro ao carregar o som do ônibus.");
        }

        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                busX += 3;
                if (busX > getWidth()) {
                    busX = -busImage.getWidth();
                    if (!redirected) {
                        redirectToLoginScreen();
                        redirected = true;
                        if (carSound != null) {
                            carSound.stop();
                        }
                    }
                }
                repaint();
            }
        });
        timer.start();
    }

    private void redirectToLoginScreen() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaDeLogin loginFrame = new TelaDeLogin();
                loginFrame.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AnimatedBusApp.this);
                frame.dispose();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(busImage, busX, busY, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Animated Bus");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                AnimatedBusApp animatedBus = new AnimatedBusApp();
                frame.add(animatedBus);
                frame.setSize(800, 600);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                animatedBus.playSound();
            }
        });
    }

    public void playSound() {
        if (carSound != null && playSound) {
            carSound.start();
            playSound = false;
        }
    }
}
