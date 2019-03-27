import java.lang.Math.*;
public class LCS {

    private int[][] LCS(String S1, String S2){

        String[] array1  = {"", "a","l", "l", "i", "g", "a", "t", "o", "r"};
        String[] array2  = {"", "a", "l", "g", "o", "r", "i", "t", "h", "m", "s"};
        int n = array1.length - 1;
        int m = array2.length - 1;

        int[][] LCSArray = new int[n][m];

        for(int i = 1; i < n; i++){
            LCSArray[i][0] = 0;
        }
        for(int j = 1; j<m; j++){
            LCSArray[0][1] = 0;
        }

        for(int i = 1; i<n; i++){
            for(int j = 1; j<m; j++){
                if(array1[i-1] == array2[j-1]){
                    LCSArray[i][j] = LCSArray[i-1][j-1] + 1;
                }
                else{
                    LCSArray[i][j] = Math.max(LCSArray[i][j-1], LCSArray[i-1][j]);
                }
            }
        }

        for (int i = 0; i < LCSArray.length; i++) {
            for (int j = 0; j < LCSArray[i].length; j++) {
                System.out.print(LCSArray[i][j] + " ");
            }
            System.out.println();
        }
        return LCSArray;
    }


    public static void main(String[] args ){
        LCS LCS1  =  new LCS();
        int[][]array;
        array = LCS1.LCS("alligator", "algorithms");

        //Change

        System.out.println("Longest Subsequence Problem for Alligator and Algorithms:\n");

        //Print 2D matrix https://stackoverflow.com/questions/5061912/printing-out-a-2-d-array-in-matrix-format
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
