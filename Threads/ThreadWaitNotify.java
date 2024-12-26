class OddEvenPrinter {
    // volatile usage - when multiple thread access number var, it fetches the updated number.
    // if not volatile, then multiple thread will have same value even number is incremented.
    static volatile int number = 0;
    int limit;
    OddEvenPrinter(int num) {
        this.limit = num;
    }
    public synchronized void printEven() {
        // If this method calls when the number is odd ( 1,3,.. )
        // need to wait so that printOdd() thread will print the method.
        while (number <= limit) {
            try {
                if (number % 2 == 0) {
                    System.out.println("Thread name : "+Thread.currentThread().getName() + ", Number : "+number);
                    number++;
                    notifyAll();
                } else {
                    wait();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public synchronized void printOdd() {
        while (number <= limit) {
            try {
                if (number % 2 != 0) {
                    System.out.println("Thread name : "+Thread.currentThread().getName() + ", Number : "+number);
                    number++;
                    notifyAll();
                } else {
                    // when the number is Even, this thread just waits. However when the printEven() thread
                    // calls, it prints the number, then notifies to all the thread who are all in waiting stage.
                    wait();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
public class ThreadWaitNotify {
    public static void main(String[] args) {
        OddEvenPrinter printer = new OddEvenPrinter(100);

        Thread evenPrinter = new Thread(printer::printEven);
        Thread oddPrinter = new Thread(printer::printOdd);

        evenPrinter.setName("Even");
        oddPrinter.setName("Odd");

        // here the output may print based on threads processing..
        // however, i need to execute printEven first , printOdd next , then printEven ....
        // we need to do with wait() & notify() so when the num is Even it prints else wait for odd thread to notify
        oddPrinter.start();
        evenPrinter.start();
    }
}
