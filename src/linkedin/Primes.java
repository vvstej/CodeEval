
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        int res;
        int _N;
        _N = Integer.parseInt(in.nextLine());
        
        res = getNumberOfPrimes(_N);
        bw.write(String.valueOf(res));
        bw.newLine();
        
        bw.close();
    }
}