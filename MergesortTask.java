import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class MergesortTask<E extends Comparable<E>> extends RecursiveTask<List<E>> {
    private List<E> a;
    int low;
    int high;


    public MergesortTask(List<E> a) {
        this.a = a;
    }

    public void merge(List<E> a, List<E> b) {
        int left = 0, right = 0, leftSize = a.size(), rightSize = b.size();

        List<E> c = new ArrayList<E>(); //create new array to hold sorted values


        while (left < leftSize && right < rightSize){ //while the left index <= leftSize  && right index <= rightSize
            if (a.get(left).compareTo(b.get(right)) < 0){ //if the value at index left is < the value at index right
                c.add(a.get(left));   //place the value at index left in current b index and then increment both
                left++;
            }
            else {
                c.add(b.get(right)); //place the value at index right in current b index and then increment
                right++;
            }
        }
        while (left < leftSize) {
            c.add( a.get(left));   // fill the rest of the array with the left values
            left++;
        }
        while (right < rightSize) {
            c.add( b.get(right));  //fill the rest of the array with the right values
            right++;
        }
        for (int i = 0; i < c.size(); i++){ //set c ArrayList values to a
            this.a.set(i, c.get(i));
        }
    }

    @Override
    protected List<E> compute() {
        if (a.size() <= 1){
            return a;
        }
        else if (this.a.size() < 100)  {
                insertionSort(a, a.size());
                return this.a;
            }
            else {
                int mid = a.size() / 2; //created midpoint
                MergesortTask<E> lefttask = new MergesortTask<E>(this.a.subList(0, mid)); //mergeSort with lower portion of the array
                MergesortTask<E> righttask = new MergesortTask<E>(this.a.subList(mid, this.a.size())); //mergeSort with the upper portion of the array

                lefttask.fork();
                righttask.fork();

                List<E> left = lefttask.join();
                List<E> right = righttask.join();

                merge(left, right);//merge
            return a;
        }
    }
    private <E extends Comparable<E>> void insertionSort(List<E> a, int n) {
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
