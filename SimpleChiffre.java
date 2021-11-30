import java.lang.Object;
public class SimpleChiffre
{
    public SimpleChiffre()
    {
    }

    public void doS(String key, String clear){
        String en = verschluesseln(key, clear);
        System.out.println(en);
        System.out.println(entschluesseln(key, en));
    }

    public String verschluesseln(String key, String clear){
        StringBuilder stringBuilder = new StringBuilder();
        String filler = filler(key);
        for (int i = 0; i < clear.length(); i++){
            if(isVowel(clear.charAt(i))){
                stringBuilder.append(clear.charAt(i));
            }
            else{
                stringBuilder.append(filler);
                stringBuilder.append(clear.charAt(i));
                stringBuilder.append(filler);
            }
        }
        return stringBuilder.toString().toUpperCase();
    }

    public String entschluesseln(String key, String encry){
        StringBuilder stringBuilder = new StringBuilder();
        String filler = filler(key);
        boolean fiV = isVowel(filler.charAt(0));
        for (int i = 0; i < encry.length(); i++){
            if(encry.charAt(i) == filler.charAt(0)){
                if(i + 1 != encry.length()){
                    if(encry.charAt(i + 1) == filler.charAt(0)){
                        
                    }
                    else{

                    }
                }
            }
            else{
                stringBuilder.append(encry.charAt(i));
            }
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
}
