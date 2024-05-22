package avada.course.question_07._01;

public class LetterPrinter implements Runnable {
    private static volatile int mode = 7;
    private static final Object object = new Object();

    private final char letter;
    private final int len;
    private final int turn;

    public LetterPrinter(char ch, int len, int turn) {
        letter = ch;
        this.len = len;
        this.turn = turn;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < len; ++i) {
            synchronized (object) {
                while(mode != turn) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(letter);
                int tmp = mode >> 1;
                mode = tmp != 0 ? tmp : 7;
                object.notifyAll();
            }
        }
    }
}
