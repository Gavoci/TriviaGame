// Server.java

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 12345;
    private static final int GAME_DURATION_SECONDS = 60;

    private static List<PlayerHandler> players = new ArrayList<>();
    private static String topic;
    private static boolean gameRunning = false;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New player connected: " + clientSocket);
                PlayerHandler playerHandler = new PlayerHandler(clientSocket);
                players.add(playerHandler);
                playerHandler.start();
                if (players.size() == 2 && !gameRunning) {
                    startGame();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void broadcast(String message) {
        for (PlayerHandler player : players) {
            player.sendMessage(message);
        }
    }

    public static synchronized void startGame() {
        if (!gameRunning) {
            gameRunning = true;
            topic = Argomenti.getArgomentoCasuale();
            broadcast("Il gioco è iniziato! Argomento: " + topic);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    endGame();
                    timer.cancel();
                }
            }, GAME_DURATION_SECONDS * 1000);
        }
    }

    
    private static void endGame() {
        gameRunning = false;
        // Calcola i punteggi e determina il vincitore
        Map<PlayerHandler, Integer> scores = new HashMap<>();
        for (PlayerHandler player : players) {
            int score = player.calculateScore();
            scores.put(player, score);
            player.sendMessage("Il tuo punteggio: " + score);
        }
        PlayerHandler winner = Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
        winner.sendMessage("Hai vinto!");
        for (PlayerHandler player : players) {
            if (player != winner) {
                player.sendMessage("Hai perso! Il vincitore è: " + winner.getUsername());
            }
            player.disconnect();
        }
        players.clear();
        startGame(); // Riavvia automaticamente la partita se entrambi i giocatori vogliono giocare di nuovo
    }

    public static synchronized boolean isGameRunning() {
        return gameRunning;
    }

    public static synchronized int checkWord(String word) {
        return Argomenti.getPunteggioParola(topic, word);
    }
}
