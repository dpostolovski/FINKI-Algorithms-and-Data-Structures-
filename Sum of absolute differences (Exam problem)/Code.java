import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.NotLinkException;
import java.util.Iterator;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {



    static int solve(int numbers[], int N, int K) {
        int [][] dp = new int[101][101];

        int z = 0;

        for (int j = 2;j <=K; j++)
        {
            for(int i = 1; i < N; i++)
            {
                if(i < j-1)
                    continue;

                for(int k = 0; k < i; k++)
                {
                    z= Math.abs(numbers[i] - numbers[k]);
                    dp[j][i] = Math.max(z + dp[j-1][k], dp[j][i]);
                }

                    dp[j][i] = Math.max(dp[j][i], dp[j][i-1]);
                    dp[j][i] = Math.max(dp[j][i], dp[j-1][i-1]);
                    dp[j][i] = Math.max(dp[j][i], dp[j-1][i]);
            }
        }


        /*for(int j = 1; j <= K; j++)
        {
            for(int i = 0; i < N; i++)
                System.out.print(dp[j][i]+ " ");
            System.out.println();
        }*/


        return dp[K][N-1];
    }


    private static int calculateDiff(int a, int b)
    {
        return Math.abs(a - b);
    }

    private static int n;
    private static int k;
    private static int max = 0;
    private static int num[];


    public static int findMax(int numbers[])
    {
        int max= -1;
        int index= 0;
        for(int i =0; i < numbers.length; i++)
        {
            if(numbers[i] > max)
            {
                max = numbers[i];
                index = i;
            }
        }

        return index;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }

}