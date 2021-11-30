import java.lang.Object;
import java.util.*;
public class SimpleChiffre
{
    public SimpleChiffre()
    {
    }

    public void doS(String key, String clear){
        Queue<String> decQ = new Queue();
        Queue<String> keyQ = new Queue();
        Queue<String> encyQ = new Queue();
        decQ.enqueue(clear);
        keyQ.enqueue(key);
        String en = verschluesseln(key, clear);
        encyQ.enqueue(en);
        System.out.println(en);
        System.out.println(entschluesseln(key, en));
        tableOut(decQ, keyQ, encyQ);
    }

    public String verschluesseln(String key, String clear){
        StringBuilder stringBuilder = new StringBuilder();
        String filler = filler(key);
        String space = " ";
        for (int i = 0; i < clear.length(); i++){
            if(clear.charAt(i) != space.charAt(0)){
                if(isVowel(clear.charAt(i))){
                    stringBuilder.append(clear.charAt(i));
                }
                else{
                    stringBuilder.append(filler);
                    stringBuilder.append(clear.charAt(i));
                    stringBuilder.append(filler);
                }
            }
            else{stringBuilder.append(space);}
        }
        return stringBuilder.toString().toUpperCase();
    }

    public String entschluesseln(String key, String encry){
        StringBuilder stringBuilder = new StringBuilder();
        String filler = filler(key);
        boolean fiV = isVowel(filler.charAt(0));
        String space = " ";
        char next = '\u0000';
        char before = '\u0000';
        for (int i = 0; i < encry.length(); i++){
            if(encry.charAt(i) != space.charAt(0)){
                if(i < encry.length() -1){next = encry.charAt(i+1);}
                if(i >= 1){before = encry.charAt(i-1);}
                if(encry.charAt(i) == filler.charAt(0)){
                    if(fiV){
                        if(isVowel(next) && isVowel(before)){
                            stringBuilder.append(encry.charAt(i));
                        }
                        else{

                        }
                    }
                    else{
                        if(before == filler.charAt(0) && next == filler.charAt(0)){
                            stringBuilder.append(encry.charAt(i));
                            i++;
                        }
                    }
                }
                else{
                    stringBuilder.append(encry.charAt(i));
                }
            }else{stringBuilder.append(space);}
        }
        return stringBuilder.toString().toUpperCase();
    }

    public String filler(String key){
        int l = key.length() - 1;
        return key.substring(1,l);
    }

    public static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }

    public void tableOut(Queue<String> dec, Queue<String> key, Queue<String> ency){
        List<String> headersList = Arrays.asList("Decrypted", "Key", "Encrypted");
        List<List<String>> rowsList = null;
        while(!dec.isEmpty()){
            rowsList.add(Arrays.asList(dec.front(), key.front(), ency.front()));
            dec.dequeue();
            key.dequeue();
            ency.dequeue();
        }
        Board board = new Board(75);
        String tableString = board.setInitialBlock(new Table(board, 75, headersList, rowsList).tableToBlocks()).build().getPreview();
        System.out.println(tableString);
    }
}
