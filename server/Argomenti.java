import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Argomenti {
    private static final Map<String, Map<String, Integer>> argomenti = new HashMap<>();

    static {
        Map<String, Integer> frutta = new HashMap<>();
        frutta.put("mela", 1);
        frutta.put("banana", 1);
        frutta.put("arancia", 2);
        frutta.put("uva", 2);
        frutta.put("ananas", 3);
        frutta.put("fragola", 3);
        frutta.put("ciliegia", 2);
        frutta.put("pera", 1);
        frutta.put("pesca", 2);
        frutta.put("kiwi", 3);
        frutta.put("melone", 3);
        frutta.put("limone", 1);
        frutta.put("mandarino", 2);
        frutta.put("mango", 3);
        frutta.put("pompelmo", 2);
        frutta.put("lampone", 3);
        frutta.put("mirtillo", 2);
        frutta.put("ribes", 1);
        frutta.put("avocado", 2);
        frutta.put("cocomero", 3);
        frutta.put("fico", 1);
        frutta.put("prugna", 2);
        frutta.put("ananas", 3);
        frutta.put("papaya", 2);
        frutta.put("cachi", 3);
        frutta.put("melograno", 1);
        frutta.put("amarena", 2);
        frutta.put("anguria", 3);
        frutta.put("limetta", 2);
        frutta.put("clementina", 3);
        // Aggiungi altre parole per l'argomento "frutta"

        Map<String, Integer> nazioni = new HashMap<>();
        nazioni.put("Italia", 1);
        nazioni.put("Francia", 1);
        nazioni.put("Germania", 2);
        nazioni.put("Spagna", 2);
        nazioni.put("Regno Unito", 3);
        nazioni.put("Stati Uniti", 3);
        nazioni.put("Cina", 2);
        nazioni.put("Giappone", 1);
        nazioni.put("India", 2);
        nazioni.put("Brasile", 3);
        nazioni.put("Russia", 3);
        nazioni.put("Canada", 1);
        nazioni.put("Australia", 2);
        nazioni.put("Argentina", 3);
        nazioni.put("Messico", 2);
        nazioni.put("Sudafrica", 3);
        nazioni.put("Corea del Sud", 2);
        nazioni.put("Indonesia", 1);
        nazioni.put("Turchia", 2);
        nazioni.put("Pakistan", 3);
        nazioni.put("Bangladesh", 1);
        nazioni.put("Vietnam", 2);
        nazioni.put("Filippine", 3);
        nazioni.put("Egitto", 2);
        nazioni.put("Nigeria", 3);
        nazioni.put("Iran", 1);
        nazioni.put("Iraq", 2);
        nazioni.put("Afghanistan", 3);
        nazioni.put("Polonia", 2);
        nazioni.put("Olanda", 3);
        // Aggiungi altre parole per l'argomento "nazioni"

        Map<String, Integer> colori = new HashMap<>();
        colori.put("Rosso", 1);
        colori.put("Blu", 1);
        colori.put("Verde", 2);
        colori.put("Giallo", 2);
        colori.put("Nero", 3);
        colori.put("Bianco", 3);
        colori.put("Grigio", 1);
        colori.put("Viola", 2);
        colori.put("Arancione", 3);
        colori.put("Rosa", 3);
        colori.put("Marrone", 1);
        colori.put("Ciano", 2);
        colori.put("Beige", 3);
        colori.put("Porpora", 2);
        colori.put("Oro", 3);
        colori.put("Argento", 1);
        colori.put("Azzurro", 2);
        colori.put("Indaco", 1);
        colori.put("Turchese", 2);
        colori.put("Bronzo", 3);
        colori.put("Ambra", 1);
        colori.put("Avorio", 2);
        colori.put("Blu navy", 3);
        colori.put("Borgogna", 2);
        colori.put("Celeste", 3);
        colori.put("Blu cobalto", 1);
        colori.put("Bianco antico", 2);
        colori.put("Rosso pomodoro", 3);
        colori.put("Beige chiaro", 2);
        colori.put("Giallo canarino", 3);
        colori.put("Blu oltremare", 1);
        colori.put("Blu elettrico", 2);
        colori.put("Rosso fuoco", 3);
        colori.put("Giallo sole", 2);
        colori.put("Verde smeraldo", 3);
        colori.put("Grigio topo", 1);
        colori.put("Grigio fumo", 2);
        colori.put("Verde oliva", 3);
        colori.put("Rosa cipria", 2);
        colori.put("Arancione brillante", 3);
        colori.put("Marrone cioccolato", 1);
        colori.put("Viola intenso", 2);
        colori.put("Bianco panna", 3);
        colori.put("Nero carbone", 2);
        colori.put("Rosso fragola", 3);

        argomenti.put("Frutta", frutta);
        argomenti.put("Nazioni", nazioni);
        argomenti.put("Colori", colori);
    }

    public static String getArgomentoCasuale() {
        Random random = new Random();
        Object[] keys = argomenti.keySet().toArray();
        return (String) keys[random.nextInt(keys.length)];
    }

    public static int getPunteggioParola(String argomento, String parola) {
        Map<String, Integer> argomentoMap = argomenti.get(argomento);
        if (argomentoMap != null) {
            Integer punteggio = argomentoMap.get(parola.toLowerCase());
            if (punteggio != null) {
                return punteggio;
            }
        }
        return 0;
    }
}
