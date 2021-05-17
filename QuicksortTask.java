import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuicksortTask<E extends Comparable<E>> extends RecursiveAction {
    ForkJoinPool pool = new ForkJoinPool();
    ArrayList a;
    int low;
    int high;

    public QuicksortTask(ArrayList<E> a, int low, int high){
        this.a = a;
        this.low = low;
        this.high = high;
    }

    public static <E extends Comparable<E>> int partition(ArrayList<E> a, int i, int j){
        int p = i; //create pivot value
        int m = i; //create index m at the beginning of the array

        for (int k = i+1; k <= j; k++){ //create a k index that starts at i + 1
            if (a.get(k).compareTo(a.get(p)) <= 0){ //if the value at index k is less than the pivot
                m++; //increment m
                E temp = a.get(k); // create temp value for value that is < the pivot
                a.set(k,a.get(m)); //swap the value at index k with the value at index m
                a.set(m, temp);    //store previous a[k] value at index m
            }
        }
        E temp2 = a.get(i); //create temp value for pivot
        a.set(i, a.get(m)); //switch a[m] with the 1st spot in the array
        a.set(m, temp2);    //store pivot in a[m]
        return m; //return index of pivot
    }

    @Override
    protected void compute() {
        if (high < 100){
            insertionSort(a, high+1);
        }
        else if (low < high){
            int pivotIndex = partition(a, low, high); //call partition to sort the array

            QuicksortTask leftTask = new QuicksortTask(a, low, pivotIndex -1);
            QuicksortTask rightTask = new QuicksortTask(a, pivotIndex + 1, high);

            leftTask.fork();
            rightTask.compute();

            leftTask.join();
        }
    }

    private <E extends Comparable<E>> void insertionSort(ArrayList<E> a, int n) {
        {
            for (int i = 1; i < n; ++i) //start the index at the second elem of the array
            {
                E next = a.get(i); //item to be inserted
                int j; //initialize a value j
                for (j = i - 1; j >= 0 && a.get(j).compareTo(next) > 0; j--) //for the index j if it is le
                {
                    a.set(j + 1, a.get(j)); //shift sort items to make place for 'next'
                }
                a.set(j + 1, next); //insert next to the correct location
            }
        }
    }
}
