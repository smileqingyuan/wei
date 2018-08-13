package win.smile.strategy;

/**
 * 策略模式
 * @author smilewei on 2018/7/1.
 * @since 1.0.0
 */
public class Processor {

    public String name(){
        return getClass().getSimpleName();
    }

    Object process(Object input){
        return input;
    }

}
