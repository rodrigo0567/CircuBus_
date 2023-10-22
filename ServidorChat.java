package Avaliação_POO;
import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorChat {
    private static final String ENDERECO_SERVIDOR = "localhost";
    private static final int PORTA = 12345;
    private static Set<PrintWriter> clientes = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Servidor de Chat está em execução...");
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            while (true) {
                new TratadorCliente(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class TratadorCliente extends Thread {
        private Socket socket;
        private PrintWriter out;

        public TratadorCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientes) {
                    clientes.add(out);
                }

                Scanner in = new Scanner(socket.getInputStream());
                while (in.hasNextLine()) {
                    String mensagem = in.nextLine();
                    transmitirMensagem(mensagem);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    synchronized (clientes) {
                        clientes.remove(out);
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void transmitirMensagem(String mensagem) {
            synchronized (clientes) {
                for (PrintWriter cliente : clientes) {
                    cliente.println(mensagem);
                }
            }
        }
    }
}
