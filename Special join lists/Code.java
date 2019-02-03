import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SpecialSLLJoin {

    private static class SLList implements Iterable<Integer>{

        private SLLNode startNode;
        private SLLNode endNode;
        private int size;

        public SLList() {
            size = 0;
            startNode = new SLLNode(0);
            endNode = startNode;
        }


        public int size()
        {
            return this.size;
        }

        public SLLNode insertLast(int data)
        {
            endNode.setNext(new SLLNode(data));
            endNode = endNode.getNext();

            size++;
            return endNode;
        }

        public SLLNode getStartNode()
        {
            return this.startNode;
        }

        public SLLNode getEndNode()
        {
            return this.endNode;
        }

        public SLList joinLists(SLList list)
        {
            SLList newList = new SLList();

            Iterator<Integer> ait = this.iterator();
            Iterator<Integer> bit = list.iterator();

            Integer a,b;

            a = ait.next();
            b = bit.next();

            do
            {

                if(a < b)
                {
                    newList.insertLast(a);
                    a = ait.next();
                }
                else
                {
                    newList.insertLast(b);
                    b = bit.next();
                }
            }while(ait.hasNext()&&bit.hasNext());

            do
            {
                newList.insertLast(a);
                a = ait.next();
            }while(ait.hasNext());

            do
            {
                newList.insertLast(b);
                b = bit.next();
            }while(bit.hasNext());

            return newList;
        }

        public SLList specialJoin(SLList list1, SLList list2)
        {
            ListIterator<Integer> it1 = list1.iterator();
            ListIterator<Integer> it2 = list2.iterator();

            while (it1.hasNext())
            {
                try
                {
                    it1.next();
                    it1.next();
                    it1.add(it2.next());
                    it1.next();
                    it1.add(it2.next());
                    it1.next();
                }catch (NoSuchElementException error)
                {
                    break;
                }
            }

            while(it2.hasNext())
            {
                try
                {
                    it1.add(it2.next());
                    it1.next();
                }catch (NoSuchElementException error)
                {
                    break;
                }
            }

            return list1;
        }


        @Override
        public ListIterator<Integer> iterator() {
            return new SLListIterator();
        }

        private class SLListIterator implements ListIterator {
            private int position = 0;
            private SLLNode current;
            @Override
            public boolean hasNext() {
                return position < size;
            }

            public SLListIterator()
            {
                current = getStartNode();
            }

            @Override
            public Integer next() {
                if(!hasNext())
                    throw new NoSuchElementException();

                position++;
                current = current.getNext();
                return current.getData();
            }

            @Override
            public boolean hasPrevious() {
                return position > 1;
            }

            @Override
            public Integer previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return position+1;
            }

            @Override
            public int previousIndex() {
                return position-1;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(Object o) {
                if(position>0)
                    current.setData(((Integer) o).intValue() );
            }

            @Override
            public void add(Object o) {
                SLLNode newNode = new SLLNode(((Integer) o).intValue() );
                SLLNode tmpNode = current.getNext();
                newNode.setNext(tmpNode);
                current.setNext(newNode);

                size++;
            }
        }


    }

    private static class SLLNode {
        private int data;
        private SLLNode nextNode;

        public SLLNode(int data) {
            this.data = data;
        }

        public void setNext(SLLNode node)
        {
            nextNode = node;
        }

        public int getData()
        {
            return data;
        }

        public SLLNode getNext()
        {
            return nextNode;
        }

        public void setData(int data)
        {
            this.data = data;
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");

        SLList lista1 = new SLList();
        SLList lista2 = new SLList();
        SLList spoeni = new SLList();

        for (int i = 0; i < N; i++) {
            lista1.insertLast(Integer.parseInt(pomniza[i]));
        }


        s = stdin.readLine();
        N = Integer.parseInt(s);
        s = stdin.readLine();
        pomniza = s.split(" ");

        for (int i = 0; i < N; i++) {
            lista2.insertLast(Integer.parseInt(pomniza[i]));
        }

        spoeni = spoeni.specialJoin(lista1,lista2);
        Iterator<Integer> it = spoeni.iterator();
        while (it.hasNext())
        {
            System.out.print(it.next() + " ");
        }

    }
}
