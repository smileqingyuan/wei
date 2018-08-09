package win.smile.strategy;

/**
 * @author smilewei on 2018/7/1.
 * @since 1.0.0
 */
public class Apply {

    public static void process(Processor p,Object s){
        System.out.println("Using Processor "+p.name());
        System.out.println(p.process(s));
    }




}
