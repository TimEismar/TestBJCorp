import java.util.Random;
import java.lang.Object;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class OneTimePad
{
    Random random = new Random();
    public Queue<String> decQ = new Queue();
    public Queue<String> keyQ = new Queue();
    public Queue<String> encyQ = new Queue();
    public OneTimePad()
    {
        termUI();
    }

    public String[] enc(String clear){
        byte[] clearB = clear.getBytes();
        byte[] enc = new byte[clearB.length];
        byte[] key = new byte[clearB.length];
        new Random().nextBytes(key);
        for (int i = 0; i < clearB.length; i++) {
            enc[i] = (byte) (clearB[i] ^ key[i]);
        }
        String[] rdy = new String[]{new String(enc), new String(key)};
        return rdy;
    }

    public String dec(byte[] encS, byte[] keyS){
        byte[] dec = new byte[encS.length];
        for (int i = 0; i < encS.length; i++) {
            dec[i] = (byte) (encS[i] ^ keyS[i]);
        }
        String decS = new String(dec);
        return decS;
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
                    decQ.enqueue(clear);
                    String[] temp = enc(clear);
                    encyQ.enqueue(temp[0]);
                    keyQ.enqueue(temp[1]);
                }
                break;
            case "/dec": {
                    System.out.println("Verschlüsselten Text Eingeben");
                    String ency = br.next();
                    System.out.println("Key Eingeben");
                    String key = br.next();
                    encyQ.enqueue(ency);
                    keyQ.enqueue(key);
                    decQ.enqueue(dec(key.getBytes(), ency.getBytes()));
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
