package Prog17;
import java.util.Arrays;

public class Prog17_03
{
    public Prog17_03(){
        System.out.println(lcsLength("algorithm","geometry"));
    }
    public static void main(String[] args) {
        new Prog17_03();
    }
    public int lcsLength(String s, String r)
    {
        char[] charS = s.toCharArray();
        char[] charR = r.toCharArray();

        System.out.println("s: " + Arrays.toString(charS));
        System.out.println("r: " + Arrays.toString(charR));

        //return lcsLength(charS, charR, s.length(), r.length());
        return lcsLengthDP(charS, charR, s.length(), r.length());
    }

    private int lcsLength(char[] s, char[] r, int n, int m)
    {
        if (n==0 || m==0) return 0;
        else
        {
            if (s[n-1]==r[m-1])
                return 1 + lcsLength(s, r, n-1, m-1);
            else
                return Math.max(lcsLength(s, r, n-1, m),
                                lcsLength(s, r, n, m-1));
        }
    }

    private int lcsLengthDP(char[] s, char[] r, int n, int m)
    {
        int[][] table = new int[n+1][m+1];

        for(int i=0; i<=n; i++)
        {
            for (int j=0; j<=m; j++)
            {
                if (i==0 || j==0)
                    table[i][j] = 0;
                else
                    if (s[i-1]==r[j-1])
                        table[i][j] = 1 + table[i-1][j-1];
                    else
                        table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
            }
        }

        return table[n][m];
    }
}
