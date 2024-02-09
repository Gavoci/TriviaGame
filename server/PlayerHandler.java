// PlayerHandler.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerHandler extends Thread {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String username;
    private int score;

    public PlayerHandler(Socket socket) {
        this.socket = socket;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // Richiede il nome al giocatore
            output.println("Benvenuto! Inserisci il tuo nome:");
            username = input.readLine();
            output.println("Ciao, " + username + "! Attendi che il gioco inizi.");
            while (true) {
                String word = input.readLine();
                if (word == null || word.isEmpty()) {
                    continue;
                }
                int wordScore = Server.checkWord(word);
                score += wordScore;
                output.println("Hai inserito la parola \"" + word + "\". Punteggio assegnato: " + wordScore);
                if (!Server.isGameRunning()) {
                    output.println("Il gioco è terminato. Vuoi giocare di nuovo? (sì/no)");
                    String choice = input.readLine();
                    if (choice.equalsIgnoreCase("sì")) {
                        score = 0;
                        output.println("Il gioco è stato riavviato. Attendi che il gioco inizi.");
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }

    public int calculateScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
