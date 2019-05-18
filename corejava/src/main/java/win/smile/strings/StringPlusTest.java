package win.smile.strings;

import java.text.MessageFormat;

/**
 * String的四中添加方式比较
 * @author weifw
 * @date 2018-11-18-17:49
 */
public class StringPlusTest {

    /**
     * Concatenation = 229 millisecond
     * Format = 2916 millisecond
     * MessageFormat = 767 millisecond
     * StringBuilder = 393 millisecond
     * 结论：没想到String的+是最快的
     *   不太符合理论，理论上应该是StringBuilder最快，这个不重要，重要的是String.formate性能太差了。
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String s = "Hi " + i + "; Hi to you " + i * 2;
        }
        long end = System.currentTimeMillis();
        System.out.println("Concatenation = " + ((end - start)) + " millisecond");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String s = String.format("Hi %s; Hi to you %s", i, +i * 2);
        }
        end = System.currentTimeMillis();
        System.out.println("Format = " + ((end - start)) + " millisecond");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String s = MessageFormat.format("Hi %s; Hi to you %s", i, +i * 2);
        }
        end = System.currentTimeMillis();
        System.out.println("MessageFormat = " + ((end - start)) + " millisecond");
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            StringBuilder bldString = new StringBuilder("Hi ");
            bldString.append(i).append("; Hi to you ").append(i * 2).toString();
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuilder = " + ((end - start)) + " millisecond");

    }
}
