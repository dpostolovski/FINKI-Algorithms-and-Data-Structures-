import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.SwitchPoint;

public class OddEvenSort {

    static void oddEvenSort(int a[], int n)
    {
        int odd[] = new int[1000];
        int even[] = new int [1000];

        int numberOdd =0;
        int numberEven =0;

        for(int i =0; i <n;i++)
        {
            if(a[i] %2 ==1)
            {
                odd[numberOdd++]=a[i];
            }
            else
            {
                even[numberEven++]=a[i];
            }
        }

        sort(odd,numberOdd);
        sort(even,numberEven);

        for(int i =0; i < numberOdd; i++)
            a[i] = odd[i];

        for(int i =0; i < numberEven; i++)
            a[numberOdd + i] = even[ numberEven -i-1];
    }

    public static void sort(int a[], int n)
    {
        // Vasiot kod tuka
        int min =0, mink;
        for(int i =0; i < n; i++)
        {
            min = i;
            for(int k = i; k < n;k++)
            {
                if(a[k] < a[min])
                {
                    min = k;
                }
            }
            swap(a,i,min);
        }
    }
    public static void swap(final int[] arr, final int pos1, final int pos2){
        final int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }



    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}