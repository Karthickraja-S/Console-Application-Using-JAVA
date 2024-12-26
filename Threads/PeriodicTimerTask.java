import java.util.Date;
import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PeriodicTimerTask {
    public static void main(String[] args) {
        System.out.println("1 : Current time : "+new Date());

        // this task will be executed after 2seconds with a period delay of 2 seconds.
        // we can even schedule task daily basis.  ( Periodic Task )
        // Check what are the schedule method overloading supported.
        Timer timer =  new Timer();
        // accepts a abstract class TimerTask implementations ( we can have a class with extension of TimerTask )
        timer.schedule(new java.util.TimerTask() {
            int count = 1;
            @Override
            public void run() {
                // here we can implement what actually the condition that this scheduler needs to be stopped.
                // however it will not stop the currently running thread.
                // Like a Daily Alarm OR backup scheduler OR checking for any file sync operations..
                if(count == 3) {
                    timer.cancel();
                }
                System.out.println("Executing run() ... ");
                System.out.println("Current time : "+new Date());
                System.out.println("Task Ended..");
                count++;
            }
        }, 2000 , 2000);


        // This can also do all the above scenario with the ScheduledThreadPoolExecutor
        // with some extra features like rejectionExecutionHandler, handling task queues effectively with a core pool size
        System.out.println("2 : Current time : "+new Date());

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        // we have methods like schedule , to run immediately use submit OR schedule with noDelay accord to use cases.
        executor.schedule( () -> {
            System.out.println("Executing run using ScheduledThreadPoolExecutor() ... ");
            System.out.println("Current time : "+new Date());
            System.out.println("Task Ended..");
        }, 10, TimeUnit.SECONDS);

        if ( executor.getCompletedTaskCount() == 0 ) {
            executor.shutdown();
        }
        System.out.println("----------");
    }
}
