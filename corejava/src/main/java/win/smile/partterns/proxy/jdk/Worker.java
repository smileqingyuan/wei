package win.smile.partterns.proxy.jdk;

/**
 * @author weifw
 * @date 2019-05-18-17:25
 */
public class Worker implements Person {

    @Override
    public String eat(String food) {
        System.out.println(food+"开吃了。。。。");
        return null;
    }
}
