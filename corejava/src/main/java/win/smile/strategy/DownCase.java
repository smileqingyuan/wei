package win.smile.strategy;

/**
 * @author smilewei on 2018/7/1.
 * @since 1.0.0
 */
public class DownCase extends Processor {

    @Override
    String process(Object input){
        return ((String)input).toUpperCase();
    }


}
