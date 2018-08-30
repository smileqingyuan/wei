package win.smile.thread;

/**
 * @author smilewei on 2018/8/30.
 * @since 1.0.0
 */
public class ClientThread extends Thread {

    private Sequence sequence;

    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName()+" =>"+sequence.getNumber());
        }
    }


}
