import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DivideOddEven {

    public static class DLList<E> implements Iterable<E>
    {
        protected DLLNode<E> first,last;

        public DLList() {
            first = null;
            last = null;
        }

        public void insertFirst(E data)
        {
            DLLNode<E> tmp = new DLLNode<E>(data, null, first);
            if(first == null)
                last = tmp;
            else
                first.prev = tmp;
            first = tmp;
        }
        public void insertLast(E data)
        {
            if(first == null)
                insertFirst(data);
            else
            {
                DLLNode<E> tmp = new DLLNode<E>(data, last, null);
                last.next = tmp;
                last = tmp;
            }
        }

        @Override
        public Iterator<E> iterator() {
            return new DLLIterator<E>(first);
        }

        protected static class DLLIterator<E> implements Iterator<E>
        {
            DLLNode<E> current;

            public DLLIterator(DLLNode<E> current) {
                this.current = new DLLNode<E>(null, null, current);
            }

            public boolean hasNext()
            {
                return current.next != null;
            }
            public E next()
            {
                current = current.next;
                return current.data;
            }

            public E previous()
            {
                current = current.prev;
                return current.data;
            }
        }
    }


    public static class DLLNode<E>
    {
        protected DLLNode prev, next;
        protected E data;

        public DLLNode(E data,DLLNode prev, DLLNode next) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }

        public DLLNode(E data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        DLList<Integer> lista = new DLList();
        DLList<Integer> parni = new DLList();
        DLList<Integer> neparni = new DLList();

        for (int i = 0; i < N; i++) {
            lista.insertLast(Integer.parseInt(pomniza[i]));
        }

        Iterator<Integer> it = lista.iterator();
        while (it.hasNext())
        {
            Integer data = it.next();
            if(data % 2== 0)
                parni.insertLast(data);
            else
                neparni.insertLast(data);
        }

        Iterator<Integer> it2 = parni.iterator();
        Iterator<Integer> it3 = neparni.iterator();

        while(it3.hasNext())
        {
            System.out.print(it3.next());
            if(it3.hasNext())
                System.out.print(" ");
        }

        System.out.println();
        while(it2.hasNext())
        {
            System.out.print(it2.next());
            if(it2.hasNext())
                System.out.print(" ");
        }

    }
}
