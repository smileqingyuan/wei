package win.smile.strategy;

import java.util.Arrays;

/**
 * @author smilewei on 2018/7/1.
 * @since 1.0.0
 */
public class Splitter extends Processor {

    @Override
    public String process(Object input) {
        return Arrays.toString(((String) input).split(" "));
    }
}
