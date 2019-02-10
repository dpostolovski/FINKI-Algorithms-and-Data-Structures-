import java.io.IOException;
import java.util.Scanner;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek () throws NoSuchElementException;
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop () throws Exception;
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}
class NoSuchElementException extends Exception{

}
class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    public int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () throws NoSuchElementException{
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () throws Exception{
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class StackBukvi {
    static int proveri_t_posle_s(char [] St)
    {
        int t = -1;
       ArrayStack<Character> stack= new ArrayStack<>(1000);
        for (char c : St) {
            if(c == 'S'&&stack.isEmpty())
            {
                stack.push(c);
            }
            else if(c=='S')
            {
                if(t==-1)
                {
                    t = stack.depth-1;
                }
                else if(t != stack.depth-1)
                    return 0;
                stack.clear();
                stack.push(c);
            }

            if(c=='T')
                stack.push(c);
        }

        if(stack.depth-1 ==t)
            return 1;
        return 0;
    }

    public static void main(String[] args) throws Exception {
        char [] niza=new char[100];

        Scanner f=new Scanner(System.in);
        String st=f.next();
        niza=st.toCharArray();

        int rez= proveri_t_posle_s(niza);
        System.out.println(rez);
    }


}