import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Connesso al server.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
                if (message.startsWith("Il vincitore")) {
                    break;
                }

                // Inserimento delle parole da parte del giocatore
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                String word = userInput.readLine();
                writer.println(word);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
