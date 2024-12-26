public class RunnableExample {

    public static void main(String[] args) {
//        Runnable runnable1 = new Runnable() {
//            @Override
//            public void run() {
//               .. some task
//            }
//        };

        // The above one can be simplified with lambda function since the Runnable interface is a functional interface
        // Functional Interface -> has only one method which has to be implemented.
        // also we can extend other classes as well when we use runnable interface.
        Runnable runnable1 = () ->
        {
            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Msg from runnable1()");
            }
        };
        Runnable runnable2 = () ->
        {
            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Msg from runnable2()");
            }
        };

        // if we do the below thing, it will not be created as thread.
        //     runnable1.run();
        //     runnable2.run();
        System.out.println("---------------");
        // To create as a thread, we need to create a thread obj with runnable impl
        Thread run1 = new Thread(runnable1);
        Thread run2 = new Thread(runnable2);

        // when we set the thread as Deamon thread, it will execute in background
        // and when JVM exits it does not wait for deamon thread to finish, just kill all those and exits.
        // run1.setDaemon(true);run2.setDaemon(true);
        run1.start();
        run2.start();
    }
}
