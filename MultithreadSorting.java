import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class MultithreadSorting<E>  {
    ArrayList<E> a = new ArrayList<E>();
    private static ArrayList b = new ArrayList<>();
    private static Random random = new Random();
    private static ForkJoinPool pool = new ForkJoinPool();


    public static void main(String[] args) {
        System.out.println("Test below threshold (insertion sort)\n");
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < 10; i++){  // fill the ArrayList
            a.add(random.nextInt(100));
        }
        printArray(a); //print initial array
        copyArray(a, b); //make a copy of the array
        long startTime = System.nanoTime(); // obtain the start time
        MergesortTask mergesortTask = new MergesortTask(a.subList(0,a.size())); //create the mergesortTask
        pool.invoke(mergesortTask); //the ForkJoinPool invokes the MergesortTask
        System.out.println("Mergesort Output:");
        printArray(a); //print array
        long endTime = System.nanoTime(); //obtain the ending time
        long totalTime = endTime - startTime; //get the runtime
        System.out.println("Time for Multithreaded mergesort\n" + convertTimeToString(totalTime));

        long startTimeq = System.nanoTime(); //state time
        QuicksortTask quicksortTask = new QuicksortTask(b , 0, b.size()-1); //create QuicksortTask
        pool.invoke(quicksortTask); // the ForkJoinPool invokes quicksortTask
        System.out.println("Quicksort Output:");
        printArray(b); //print sorted array
        long endTimeq = System.nanoTime(); //end time
        long totalTimeq = endTimeq - startTimeq; //run time
        System.out.println("Time for Multithreaded quicksort\n" + convertTimeToString(totalTimeq));


        System.out.println("Test String Input");
        ArrayList<String> c = new ArrayList<>(); //create array and add String values
        c.add("orange");
        c.add("apple");
        c.add("banana");
        c.add("tomato");
        c.add("grape");
        c.add("plum");
        c.add("peach");
        c.add("pineapple");
        c.add("mango");
        c.add("grapefruit");
        ArrayList<String> d = new ArrayList<>();
        copyArray(c,d); // copy array
        printArray(c); //print array
        startTime = System.nanoTime(); //start time
        MergesortTask mergesortTask2 = new MergesortTask(c.subList(0,c.size())); //create mergesortTask
        pool.invoke(mergesortTask2); //ForkJoinPool invokes mergesort task
        System.out.println("Mergesort Output:");
        printArray(c);
        endTime = System.nanoTime(); //end time
        totalTime = endTime - startTime; //run time
        System.out.println("Time for Multithreaded mergesort\n" + convertTimeToString(totalTime));

        startTimeq = System.nanoTime(); //start time
        QuicksortTask quicksortTask2 = new QuicksortTask(d , 0, d.size()-1); //create quicksort task
        pool.invoke(quicksortTask2); // ForkJoinPool invokes quicksortTask
        System.out.println("Quicksort Output:");
        printArray(d);
        endTimeq = System.nanoTime(); //end time
        totalTimeq = endTimeq - startTimeq; //run time
        System.out.println("Time for Multithreaded quicksort\n" + convertTimeToString(totalTimeq));

        System.out.println("Test ArrayList with 5000 values:");
        ArrayList<Integer> e = new ArrayList<>();
        ArrayList<Integer> f = new ArrayList<>();
        for (int j = 0; j < 5000; j++){ //create 5000 value array
            e.add(random.nextInt(10000));
        }
        copyArray(e,f); //copy array
        printArray(e); //print array
        startTime = System.nanoTime(); //start time
        MergesortTask mergesortTask3 = new MergesortTask(e.subList(0,e.size())); //create MergesortTask
        pool.invoke(mergesortTask3); //ForkJoinPool invokes quicksortTask
        System.out.println("Mergesort Output:");
        printArray(e);
        endTime = System.nanoTime(); //end time
        totalTime = endTime - startTime; //run time
        System.out.println("Time for Multithreaded mergesort\n" + convertTimeToString(totalTime));

        startTimeq = System.nanoTime(); //start time
        QuicksortTask quicksortTask3 = new QuicksortTask(f , 0, f.size()-1); //create Quicksorttask
        pool.invoke(quicksortTask3); //ForkJoinPool invokes quicksortTask
        System.out.println("Quicksort Output:");
        printArray(f);
        endTimeq = System.nanoTime();
        totalTimeq = endTimeq - startTimeq;
        System.out.println("Time for Multithreaded quicksort\n" + convertTimeToString(totalTimeq));
        compareArray(e,f); //compare the two arrays


    }

    private static void compareArray(ArrayList<Integer> e, ArrayList<Integer> f) {
        String same = "Arrays are the same";
        for (int i = 0; i < e.size(); i++){
            if (e.get(i).compareTo(f.get(i)) != 0){
                System.out.println("Arrays are not the same " + i + " " + e.get(i) + " " + f.get(i));
            }
        }
    }

    private static void copyArray(ArrayList a, ArrayList z) {
        for (int i = 0; i < a.size(); i++){
            z.add(a.get(i));
        }
    }

    public static void printArray(ArrayList a){
        System.out.print("[");
        for (int i = 0; i < a.size(); ++i){
            System.out.print(a.get(i));
            if (i < a.size()-1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static String convertTimeToString(long nanos)
    {
        if(nanos < 0)
        {
            throw new IllegalArgumentException("ERROR : Duration is less than zero!");
        }


        long hours = TimeUnit.NANOSECONDS.toHours(nanos);
        nanos -= TimeUnit.HOURS.toNanos(hours);
        long minutes = TimeUnit.NANOSECONDS.toMinutes(nanos);
        nanos -= TimeUnit.MINUTES.toNanos(minutes);
        long seconds = TimeUnit.NANOSECONDS.toSeconds(nanos);
        nanos -= TimeUnit.SECONDS.toNanos(seconds);
        long milliseconds = TimeUnit.NANOSECONDS.toMillis(nanos);
        nanos -= TimeUnit.MILLISECONDS.toNanos(milliseconds);

        StringBuilder sb = new StringBuilder(64);
        sb.append(hours);
        sb.append(" hrs : ");
        sb.append(minutes);
        sb.append(" mins : ");
        sb.append(seconds);
        sb.append(" sec : ");
        sb.append(milliseconds);
        sb.append(" ms : ");
        sb.append(nanos);
        sb.append(" ns");

        return(sb.toString());
    }
}
