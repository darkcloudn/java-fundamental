package Multithreading;

class Printer {
    // synchronized void printDocs(int numOfCopies, String docName) {
    void printDocs(int numOfCopies, String docName) {
        for (int i = 1; i < numOfCopies; i++) {
            try {
                Thread.sleep(500);
                System.out.println(">> Printing " + docName + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class MyThread extends Thread {
    private Printer pRef;

    MyThread(Printer p) {
        pRef = p;
    }

    @Override
    public void run() {
        synchronized (pRef){
            pRef.printDocs(10, "Viettd Profile");
        }
    }
}

class YourThread extends Thread {
    private Printer pRef;

    YourThread(Printer p) {
        pRef = p;
    }

    @Override
    public void run() {
        pRef.printDocs(10, "DarkcLoud Profile");
    }
}

public class SyncApp {

    // main is representing main thread
    public static void main(String[] args) {

        System.out.println("==Application start==");
        //We have only 1 single object of printer
        Printer printer = new Printer();
        //    printer.printDocs(10,"ViettdProfile.pdf");

        //Scenario is that web have mutiple thread working on the same Printer Object
        MyThread myRef = new MyThread(printer);// My thread is having reference to the printer object
        YourThread yRef = new YourThread(printer);//My thread is having reference to the printer object
        myRef.start();
//        try {
//            myRef.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        yRef.start();


        System.out.println("==Application stop==");
    }
}
