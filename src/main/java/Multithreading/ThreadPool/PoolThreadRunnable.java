package Multithreading.ThreadPool;

import java.util.concurrent.BlockingDeque;

public class PoolThreadRunnable implements Runnable {

    private Thread thread = null;
    private BlockingDeque taskQueue;
    private boolean isStopped = false;

    public PoolThreadRunnable(BlockingDeque<Runnable> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!isStopped){
            try {
                if(taskQueue.size()>0){
                    Runnable runnable = (Runnable) taskQueue.take();
                    runnable.run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        //break pool thread out of dequeue() call
        this.thread.interrupt();
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }

}
