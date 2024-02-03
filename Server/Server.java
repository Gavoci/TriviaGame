// Server.java
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server avviato. In attesa di giocatori...");

            while (true) {
                Socket player1Socket = serverSocket.accept();
                System.out.println("Giocatore 1 connesso.");

                Socket player2Socket = serverSocket.accept();
                System.out.println("Giocatore 2 connesso.");

                GameHandler game = new GameHandler(player1Socket, player2Socket);
                Thread gameThread = new Thread(game);
                gameThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// GameHandler.java
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class GameHandler implements Runnable {
    private Socket player1Socket;
    private Socket player2Socket;
    private BufferedReader player1Reader;
    private PrintWriter player1Writer;
    private BufferedReader player2Reader;
    private PrintWriter player2Writer;
    private String topic;
    private List<String> player1Words;
    private List<String> player2Words;

    public GameHandler(Socket player1Socket, Socket player2Socket) {
        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;
        player1Words = new ArrayList<>();
        player2Words = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            player1Reader = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            player1Writer = new PrintWriter(player1Socket.getOutputStream(), true);

            player2Reader = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
            player2Writer = new PrintWriter(player2Socket.getOutputStream(), true);

            assignTopic();
            collectWords();

            int player1Score = calculateScore(player1Words);
            int player2Score = calculateScore(player2Words);

            String winner;
            if (player1Score > player2Score) {
                winner = "Giocatore 1";
            } else if (player1Score < player2Score) {
                winner = "Giocatore 2";
            } else {
                winner = "Pareggio";
            }

            player1Writer.println("Il vincitore è: " + winner + " con un punteggio di " + player1Score);
            player2Writer.println("Il vincitore è: " + winner + " con un punteggio di " + player2Score);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                player1Socket.close();
                player2Socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void assignTopic() throws IOException {
        topic = "Sport";
        player1Writer.println("Argomento: " + topic);
        player2Writer.println("Argomento: " + topic);
    }

    private void collectWords() throws IOException {
        player1Writer.println("Inserisci le parole relative all'argomento (" + topic + "):");
        String word;
        while ((word = player1Reader.readLine()) != null && !word.isEmpty()) {
            player1Words.add(word.toLowerCase());
        }

        player2Writer.println("Inserisci le parole relative all'argomento (" + topic + "):");
        while ((word = player2Reader.readLine()) != null && !word.isEmpty()) {
            player2Words.add(word.toLowerCase());
        }
    }

    private int calculateScore(List<String> words) {
        int score = 0;
        for (String word : words) {
            if (word.contains(topic.toLowerCase())) {
                score += word.length();
            }
        }
        return score;
    }
}

// Client.java
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        new ClientGUI();
    }
}
