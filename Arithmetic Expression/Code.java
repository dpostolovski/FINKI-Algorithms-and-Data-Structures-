import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r

    static int findSign(char c[], int l, int r)
    {
        LinkedList<Character> stack = new LinkedList<Character>();
        for(int i = l; i < r;i++)
        {
            switch (c[i]) {
                case '(':
                    stack.addLast(c[i]);
                    break;
                case '+':
                case'-':
                    if(stack.size() == 1)
                        return i;
                    break;
                case ')':
                    stack.removeLast();
                    break;
            }
        }
        return 0;
    }
    static String cast(char c[], int l, int r)
    {
        String s = new String(c,l, r-l);
        return s;
    }
    static int presmetaj(char c[], int l, int r) {

        int a, b, znak;
        String cs = new String(c);
        if(l ==r)
            return Integer.parseInt( String.valueOf(c[l]) );

        znak = findSign(c, l,r);
        //System.out.println(znak + " " + c[znak]);

        try {
            a= Integer.parseInt( cast(c, l+1,znak));
        }catch (NumberFormatException e)
        {
            a = presmetaj(c, l+1, znak-1);
        }

        try {
            b= Integer.parseInt(cast(c, znak+1, r-1), 10);
        }catch (NumberFormatException e)
        {
            b = presmetaj(c, znak+1,r-1);
        }

        switch (c[znak])
        {
            case '+':
                return a + b;
            case '-':
                return  a-b;
        }

        return 0;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
