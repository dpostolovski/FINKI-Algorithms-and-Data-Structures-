import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.String;

import java.util.Comparator;

class Heap<E extends Comparable<E>> {

    private E elements[];

    private Comparator<? super E> comparator;

    private int compare(E k1, E k2) {
        return (comparator == null ? k1.compareTo(k2) : comparator.compare(k1, k2));
    }

    int getParent(int i) {
        return (i + 1) / 2 - 1;
    }

    public E getAt(int i) {
        return elements[i];
    }

    int getLeft(int i) {
        return (i + 1) * 2 - 1;
    }

    int getRight(int i) {
        return (i + 1) * 2;
    }

    void setElement(int index, E elem) {
        elements[index] = elem;
    }

    void swap(int i, int j) {
        E tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    void adjust(int i, int n) {

        while (i < n) {

            int left = getLeft(i);
            int right = getRight(i);
            int largest = i;

            if ((left < n)&&(elements[left].compareTo(elements[largest]) > 0))
                largest = left;
            if ((right < n)&&(elements[right].compareTo(elements[largest]) > 0))
                largest = right;

            if (largest == i)
                break;

            swap(i, largest);
            i = largest;

        }

    }

    void buildHeap() {
        int i;
        for (i = elements.length / 2 - 1; i >= 0; i--)
            adjust(i, elements.length);
    }

    public void heapSort() {
        int i;
        buildHeap();
        for (i = elements.length; i > 1; i--) {
            swap(0, i - 1);
            adjust(0, i - 1);
        }
    }

    @SuppressWarnings("unchecked")
    public Heap(int size) {
        elements = (E[]) new Comparable[size];

    }
}
public class BlackFriday {

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        int n = Integer.parseInt(line);
        Heap<Integer> vlez = new Heap<>(n);
        Heap<Integer> izlez = new Heap<>(n);

        for(int i =0; i < n; i++)
        {
            String entry = br.readLine();
            String data[] = entry.split(" ");
            String time[] = data[0].split(":");

            int hour = Integer.parseInt(time[0]);
            int minutes = Integer.parseInt(time[1]);
            int minSpent = Integer.parseInt(data[1]);

            int start = hour*60 + minutes;
            int end = Math.min(start+minSpent, 23*60 + 59);
            vlez.setElement(i,start);
            izlez.setElement(i,end);

        }
        vlez.heapSort();
        izlez.heapSort();

        int prisutni = 0;
        int maxres = 0;
        for(int v = 0, i=0; v < n;v++)
        {
            int start = vlez.getAt(v);
            prisutni++;
            int end = izlez.getAt(i);

            if(end <= start)
            {
                prisutni--;
                i++;
            }

            maxres =Math.max(prisutni, maxres);
        }

        System.out.println(maxres);
    }


}
