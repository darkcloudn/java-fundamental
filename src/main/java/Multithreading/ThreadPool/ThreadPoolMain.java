package Multithreading.ThreadPool;

public class ThreadPoolMain {

    public static void main(String[] args) throws IllegalAccessException {
        int noOfThread = 3;
        int maxNoOfTask = 10;

        ThreadPool threadPool = new ThreadPool(noOfThread,maxNoOfTask);

        for(int i = 0; i < 10;i++){
            int taskNo = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String message = Thread.currentThread().getName() + ": Task " + taskNo;
                    System.out.println(message);
                }
            });

        }
        threadPool.waitUntilAllTaskFinished();
        threadPool.stop();

    }
}
