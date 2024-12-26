class Test extends Thread {
    //3 - Thread is Going to start - RUNNING state
    public void run() {
        for(int i=0;i<10;i++) {
            try {
                //4 - Thread is going to wait - WAITING state
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Msg From Test run()");
        }
    }
}
class Test2 extends Thread {
    public void run() {
        for(int i=0;i<10;i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Msg From Test2 run()");
        }
    }
}
public class ThreadExample {
    public static void main(String[] args) {
        /**
         * Thread has 5 state
         * 1. NEW
         * 2. RUNNABLE
         * 3. RUNNING
         * 4. WAITING / BLOCKING
         * 5. DEAD
         * Limitations : If we extend thread to a class,then those class
         * cannot extend further classes as java does not supports
         * Multiple Inheritance..
         */
        //1 - created a thread - NEW state
        Test test = new Test();
        Test2 test2 = new Test2();

        //2 - Thread is ready to start - RUNNABLE state
        test.start();
        test2.start();

        //5 - Once thread work is done - it will go for DEAD state
    }
}
