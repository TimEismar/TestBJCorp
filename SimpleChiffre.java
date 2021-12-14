import java.lang.Object;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleChiffre {
    public Queue<String> decQ = new Queue();
    public Queue<String> keyQ = new Queue();
    public Queue<String> encyQ = new Queue();

    public SimpleChiffre() {
        termUI();
    }

    public static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    public void doS(String key, String clear) {
        keyQ.enqueue(key);
        String en = verschluesseln(key, clear);
        encyQ.enqueue(en);
        decQ.enqueue(entschluesseln(key, en));
    }

    public String verschluesseln(String key, String clear) {
        StringBuilder stringBuilder = new StringBuilder();
        String filler = filler(key);
        String space = " ";
        for (int i = 0; i < clear.length(); i++) {
            if (clear.charAt(i) != space.charAt(0)) {
                if (isVowel(clear.charAt(i))) {
                    stringBuilder.append(clear.charAt(i));
                } else {
                    stringBuilder.append(filler);
                    stringBuilder.append(clear.charAt(i));
                    stringBuilder.append(filler);
                }
            } else {
                stringBuilder.append(space);
            }
        }
        return stringBuilder.toString().toUpperCase();
    }

    public String entschluesseln(String key, String encry) {
        StringBuilder stringBuilder = new StringBuilder();
        String filler = filler(key);
        boolean fiV = isVowel(filler.charAt(0));
        String space = " ";
        char next = '\u0000';
        char before = '\u0000';
        for (int i = 0; i < encry.length(); i++) {
            if (encry.charAt(i) != space.charAt(0)) {
                if (i < encry.length() - 1) {
                    next = encry.charAt(i + 1);
                }
                if (i >= 1) {
                    before = encry.charAt(i - 1);
                }
                if (encry.charAt(i) == filler.charAt(0)) {
                    if (fiV) {
                        if (isVowel(next) && isVowel(before)) {
                            stringBuilder.append(encry.charAt(i));
                        } else {

                        }
                    } else {
                        if (before == filler.charAt(0) && next == filler.charAt(0)) {
                            stringBuilder.append(encry.charAt(i));
                            i++;
                        }
                    }
                } else {
                    stringBuilder.append(encry.charAt(i));
                }
            } else {
                stringBuilder.append(space);
            }
        }
        return stringBuilder.toString().toUpperCase();
    }

    public String filler(String key) {
        int l = key.length() - 1;
        return key.substring(1, l);
    }

    public void print() {
        table(decQ, keyQ, encyQ);
    }

    public void table(Queue<String> dec, Queue<String> key, Queue<String> ency) {
        Queue<String> temp = dec;
        Queue<String> tempp = key;
        Queue<String> temppp = ency;
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        st.setHeaders("Decrypted", "Key", "Encryptred");
        while (!temp.isEmpty()) {
            st.addRow(temp.front(), tempp.front(), temppp.front());
            //rowsList.add(Arrays.asList("Alice", "Male", "Yes", "29", "580.40"));
            temp.dequeue();
            tempp.dequeue();
            temppp.dequeue();
        }
        st.print();
    }

    public void termUI() {
        Scanner br = new Scanner(System.in);
        System.out.println("Befehle:");
        System.out.println("/enc - Verschlüsseln");
        System.out.println("/dec - Entschlüsseln");
        System.out.println("/print - Ausgabe");
        System.out.println("/exit - Beenden");
        String command = br.next();
        switch (command) {
            case "/enc": {
                System.out.println("Klartext Eingeben");
                String clear = br.next();
                System.out.println("Key Eingeben");
                String key = br.next();
                decQ.enqueue(clear);
                encyQ.enqueue(verschluesseln(key, clear));
                keyQ.enqueue(key);

            }
            break;
            case "/dec": {
                System.out.println("Verschlüsselten Text Eingeben");
                String ency = br.next();
                System.out.println("Key Eingeben");
                String key = br.next();
                encyQ.enqueue(ency);
                keyQ.enqueue(key);
                decQ.enqueue(entschluesseln(key, ency));
            }
            break;
            case "/print": {
                print();
            }
            break;
            case "/exit": {
                System.exit(1);
            }
            break;

        }
        termUI();
    }
}