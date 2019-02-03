import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.*;

public class Array<E>{

    private Object data[];

    public Array(int n)
    {
        this.data = new Object[n];
    }

    public void set(int i, E value)
    {
        this.data[i] = value;
    }

    public E get(int i)
    {
        return (E) data[i];
    }

    public int length()
    {
        return data.length;
    }



    public static int brojDoProsek( Array<Integer> niza ){
        double prosek = niza.prosek(niza);
        double distance = 999999;
        int broj = 0;

        for(int i = 0; i < niza.length(); i++)
        {
            if( Math.abs( niza.get(i) - prosek ) < distance)
            {
                distance = Math.abs( niza.get(i)  - prosek );
                broj = niza.get(i);
            }

            if(Math.abs( niza.get(i) - prosek ) == distance)
            {
                if(niza.get(i) < broj)
                    broj = niza.get(i);
            }
        }

        return broj;
    }

    public static double prosek(Array<Integer> niza )
    {
        double sum = 0;
        for(int i =0; i < niza.length(); i++)
            sum+= (double) niza.get(i);

        return sum/ (double) niza.length();
    }

    public static void main(String[] args) throws IOException{
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        Array<Integer> niza = new Array<Integer>(N);

        for(int i =0; i<N; i++)
        {
            s = stdin.readLine();
            niza.set(i, Integer.parseInt(s));
        }


        System.out.println(brojDoProsek(niza));
    }



}