import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        int longest = 0, prev = a[0], count = 1, tmp = 0;
        for(int i = 1; i < a.length; i++) {
            if(prev < 0) {
                if(a[i] > 0)
                    count++;
            }
            else if(prev > 0) {
                if(a[i] < 0)
                    count++;
            }
            prev = a[i];
           
            if(tmp < count) {
                if(longest < count)
                    longest = count;
                tmp = count;
            }
            else {
                tmp = 0;
                count = 1;
            }
        }
        return longest;
    }
    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}
