package win.smile.strings;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * String的正则表达式
 * @author weifw
 * @date 2018-11-18-18:22
 */
public class RegexUtils {

    @Test
    public void basicTest(){

        String regex = "^[A-Z].*[\\.]$";
        String sentence = "I am so stupid.";
        boolean b = Pattern.matches(regex, sentence);
        System.out.println(b);


    }



}
