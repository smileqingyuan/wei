package win.smile.polymorphic;

/**
 * @author smilewei on 2018/6/30.
 * @since 1.0.0
 */
public class Wind extends Instrument {

    @Override
    public void play(Note note){
        System.out.println("Wind.play() "+note);
    }


}
