package win.smile.polymorphic;

/**
 * @author smilewei on 2018/6/30.
 * @since 1.0.0
 */
public class Music {

    public static void tune(Instrument instrument){
        instrument.play(Note.MIDDLE_C);
    }

    public static void main(String[] args) {
        Wind flute = new Wind();
        tune(flute);
    }

}
