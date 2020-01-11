package plus.one;

/**
 *
 * @author weifw
 * @date 2019-12-22-18:22
 */
public class House {

    public House() {
        System.out.println("House()");
        Window window = new Window(33);
    }

    Window w1 = new Window(1);
    Window w2 = new Window(2);
    void f(){
        System.out.println("f()");
    }
    Window w3 = new Window(3);

}
