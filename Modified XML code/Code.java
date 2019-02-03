import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {

    // The elements of the Stack are any kind of objects

    // Access methods:

    public boolean isEmpty ();
    // Returns true only if the stack is empty.

    public E peek ();
    // Returns the element on the top od the stack.

    // Transformation methods:

    public void clear ();
    // Clears the stack.

    public void push (E x);
    // Adds x on the top of the stack.

    public E pop ();
    // Removes and returns the element on the top.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Creating new empty stack
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Returns true only if the stack is empty.

        return (depth == 0);
    }


    public E peek () {
        // Returns the element on the top od the stack.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Clears the stack.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Adds x on the top of the stack.
        elems[depth++] = x;
    }


    public E pop () {
        // Removes and returns the element on the top.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class CheckXML {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();

        int valid = 1;
        ArrayStack<String> tagStack = new ArrayStack<String>(1000);

        String tag,comparator;

        for(int i =0; i < redovi.length; i++)
        {
            if(redovi[i].charAt(0) != '[')
                continue;


            if(redovi[i].charAt(1) != '/')
            {
                tagStack.push(redovi[i]);
            }
            else
            {
                tag = tagStack.pop();
                tag = tag.substring(1, tag.length()-1);
                comparator = redovi[i].substring(2, redovi[i].length()-1);

                if(!tag.equals(comparator))
                {
                    valid = 0;
                    break;
                }
            }
        }

        System.out.println(valid);

        br.close();
    }
}