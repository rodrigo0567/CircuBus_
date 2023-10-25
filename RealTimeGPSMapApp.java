package Avaliação_POO;

import javax.swing.*;
import com.google.maps.GeoApiContext;
import com.google.maps.StaticMapsApi;
import com.google.maps.model.LatLng;

public class RealTimeGPSTrackingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Real-Time GPS Tracking App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("SUA_CHAVE_DA_API_DO_GOOGLE_MAPS")
                    .build();

            Timer timer = new Timer(1000, e -> {
                // Simule a obtenção das coordenadas de GPS em tempo real
                double latitude = 40.7128 + (Math.random() - 0.5) * 0.01;
                double longitude = -74.0060 + (Math.random() - 0.5) * 0.01;

                // Atualize o mapa com as novas coordenadas
                LatLng location = new LatLng(latitude, longitude);
                ImageIcon mapImage = getMapImage(context, location);

                JLabel mapLabel = new JLabel(mapImage);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(mapLabel);
                frame.revalidate();
                frame.repaint();
            });
            timer.start();

            frame.setVisible(true);
        });
    }

    private static ImageIcon getMapImage(GeoApiContext context, LatLng location) {
        try {
            String staticMapUrl = StaticMapsApi.newRequest(context)
                    .center(location)
                    .zoom(10)
                    .size(new com.google.maps.model.Size(800, 600))
                    .scale(2)
                    .toString();

            // Carregar a imagem do URL
            java.net.URL imageUrl = new java.net.URL(staticMapUrl);
            java.awt.Image image = java.awt.Toolkit.getDefaultToolkit().getDefaultToolkit().createImage(imageUrl);

            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

