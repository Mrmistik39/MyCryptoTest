package crypt;

import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        String ОТГАДАТЬ_КЛЮЧ = null;
        System.out.println("Encrypt: " + decrypt("4KGo4KKO4KKQ4KKG", ОТГАДАТЬ_КЛЮЧ));
    }

    public static String decrypt(String text, String sha){
        text = new String(Base64.getDecoder().decode(text));
        String key = sha(sha.getBytes());
        StringBuilder buf = new StringBuilder();
        int summary = 0;
        for(char c: key.toCharArray()){summary += c;}
        for(char c: text.toCharArray()){buf.append((char)(c-summary-text.length()));}
        return buf.toString();
    }
    public static String crypt(String text, String key){
        if (text.length()>Integer.MIN_VALUE+2147483658L) System.exit(9);
        String sha = sha(key.getBytes());
        StringBuilder buf = new StringBuilder();
        int summary = 0;
        for(char c: sha.toCharArray()){summary += c;}
        for(char c: text.toCharArray()){buf.append((char)(c + summary + text.length()));}
        return Base64.getEncoder().encodeToString(buf.toString().getBytes());
    }
    public static String sha(byte[] b) {
        StringBuilder buffer = new StringBuilder();
        for (byte value : b) {buffer.append(Integer.toString((value & 0xff) + 0x100, 16).substring(1));}
        return buffer.toString();
    }
}
