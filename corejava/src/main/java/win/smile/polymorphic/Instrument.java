package win.smile.polymorphic;

/**
 * @author smilewei on 2018/6/30.
 * @since 1.0.0
 */
public class Instrument {

    public static void main(String[] args) {

        System.out.println(Note.B_FLAT);
    }

    public void play(Note note){
        System.out.println("Instrument play()");
    }

}
