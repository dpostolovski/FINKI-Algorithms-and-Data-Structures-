import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Homework {

    static int minBrojKazneni(int a[]) {
        Arrays.sort(a);

        int rez = 0, zbir =0;
        for(int i =0; i < a.length; i++)
        {
            rez += zbir + a[i];
            zbir+=a[i];
        }
        return rez;
    }

    public static void main(String[] args) throws Exception {
        int i;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];

        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = minBrojKazneni(a);

        System.out.println(rez);

        br.close();
    }

}
