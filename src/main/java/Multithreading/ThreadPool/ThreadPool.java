package Multithreading.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadPool {

    private BlockingDeque<Runnable> taskQueue;
    private List<PoolThreadRunnable> runnables = new ArrayList<>();
    private boolean isStopped = false;

    public ThreadPool(int noOfThreads,int maxNoOfTasks){
        taskQueue =  new LinkedBlockingDeque<Runnable>(maxNoOfTasks);
        for (int i =0 ; i< noOfThreads; i++){
            PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable(taskQueue);
            runnables.add(poolThreadRunnable);
        }
        for(PoolThreadRunnable runnable : runnables){
            new Thread(runnable).start();
        }
    }

    public  synchronized void execute(Runnable task) throws IllegalAccessException {
        if(this.isStopped) throw new IllegalAccessException("ThreadPool is stopped");
        this.taskQueue.offer(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(PoolThreadRunnable runnable : runnables){
            runnable.doStop();
        }
    }

    public synchronized void waitUntilAllTaskFinished(){
        while (this.taskQueue.size()>0){
            try {
                Thread.sleep(1);
            }catch (InterruptedException e){

            }
        }
    }

    public int getSizeListQueue(){
        return this.taskQueue.size();
    }

}
