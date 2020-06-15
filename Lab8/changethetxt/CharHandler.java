import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CharHandler {


    public static Map<Character, Character> lowercase = new HashMap<>();
    public static Map<Character, Character> uppercase = new HashMap<>();


    public static void setup() {
        String string3 = "`1234567890-=qwertyuiop[]asdfghjkl;zxcvbnm,./";
        String string4 = "~!@#$%^&*()_+QWERTYUIOP{}ASDFGHJKL:ZXCVBNM<>?";
        String string1 = "`¡™£¢∞§¶•ªº–≠œ∑´®†¥¨ˆøπ“‘åß∂ƒ©˙∆˚¬…Ω≈ç√∫˜µ≤≥÷";
        String string2 = "`⁄€‹›ﬁﬂ‡°·‚—±Œ„´‰ˇÁ¨ˆØ∏”’ÅÍÎÏ˝ÓÔÒÚ¸˛Ç◊ı˜Â¯˘¿";
        IntStream.range(0, string1.length()).forEach(i -> lowercase.put(string1.charAt(i), string3.charAt(i)));
        IntStream.range(0, string1.length()).forEach(i -> uppercase.put(string2.charAt(i), string4.charAt(i)));
    }

    public static List<Character> nn = Arrays.asList();
    public static List<Character> Sn;
    public static List<Character> nO;
    public static List<Character> SO;

    public static Character rep(Character c) {
        if (lowercase.containsKey(c)) {
            return lowercase.get(c);
        }
        if (uppercase.containsKey(c)) {
            return uppercase.get(c);
        }
        return c;
    }
    public static char Fixit(char c) {
        switch (c) {
            case '`': return  '`';
            case '¡': return
        }

    }

    public static void main(String[] args) {
        setup();
    }
}
