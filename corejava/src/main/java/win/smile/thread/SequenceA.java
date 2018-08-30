package win.smile.thread;

/**
 * ThreadLocal可以作为线程安全进行传递
 * @author smilewei on 2018/8/30.
 * @since 1.0.0
 */
public class SequenceA implements Sequence {

    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>() {

        @Override
        protected Integer initialValue() {

            return 0;
        }
    };

    public final static ThreadLocal<String> RESOURCE = new ThreadLocal<>();


    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get()+1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        ClientThread c1 = new ClientThread(new SequenceA());
        ClientThread c2 = new ClientThread(new SequenceA());
        ClientThread c3 = new ClientThread(new SequenceA());
        c1.start();
        c2.start();
        c3.start();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                Sequence sequence = new SequenceA();
                for (int j = 0; j < 3; j++) {
                    System.err.println(Thread.currentThread().getName()+" =>"+sequence.getNumber());
                }

            }).start();
        }


    }

}
