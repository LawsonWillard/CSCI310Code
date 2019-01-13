import java.lang.*;

public class FloydAlgorithm {

        public int [][] FloydAlgorithm(int inputArray[][]){


            int n = inputArray.length; //Gets length of array
            int l; //Used to display steps


            //Prints Initial Array
            System.out.println("Initial Array: \n");
            for(int i = 0; i < inputArray.length; i++) {
                for (int j = 0; j < inputArray[i].length; j++) {
                    System.out.print(inputArray[i][j] + " ");
                }
                System.out.println();
            }

            //Alogirthm from textbook. k,i,j have been put to 0 so that they reach every row and column
            for(int k = 0; k < n; k++){
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < n; j++) {
                        inputArray[i][j] = Math.min(inputArray[i][j], inputArray[i][k] + inputArray[k][j]);
                    }
                }
                l = k+1;//Used to print amount of steps
                //Prints array after k+1 steps
                System.out.println("\nArray after " + l + " steps: \n");
                for(int i = 0; i < inputArray.length; i++) {
                    for (int j = 0; j < inputArray[i].length; j++) {
                        System.out.print(inputArray[i][j] + " ");
                    }
                    System.out.println();
                }
            }

            return inputArray;
        };

        public static void main(String[] args) {

            int INF  = 5000; //All low numbers so to keep from arithmetic overflow INF = 5000

            //Initialization of array
            int[][] fArray = {
                    {0, 9, INF, INF, INF, 1, INF},
                    {9, 0, 1, INF, INF, 8, 2},
                    {INF, 1, 0, 10, 1, INF, 2},
                    {INF, INF, 10, 0, 4, INF, INF},
                    {INF, INF, 1, 4, 0, 6, INF},
                    {1, 8, INF, INF, 6, 0, 4},
                    {INF, 2, 2, INF, INF, 4, 0}
            };

            FloydAlgorithm F1 = new FloydAlgorithm(); //Initializes Method

            F1.FloydAlgorithm(fArray); //Calls method

        }

}
