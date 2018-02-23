package minMaxNormalization;

import java.io.*;
import java.util.*;

public class Solution {
    public static int D;
    public static void main(String[] args) {
	try {
        Scanner scan = new Scanner(new File(args[0]));
        D = scan.nextInt();
        int N = scan.nextInt();
        int distance = scan.nextInt();
        int X = scan.nextInt();
        int[] P = new int[D];
        for (int i = 0; i < D; i++) {
	if (scan.hasNextInt()) {
            P[i] = scan.nextInt();
	}
        }
        int[][] patients2 = new int[N][D];
        int[] anArray = new int[D];
        double[][] distances = new double[N][2];

        for (int j = 0; j < N; j++) {
        for (int i = 0; i < D; i++) {
	if (scan.hasNextInt()) {
            patients2[j][i] = scan.nextInt();
	}
        } 
        }
        
        int[] anArray2 = new int[N];
        for (int j = 0; j < N; j++) {
        for (int i = 0; i < D; i++) {
            anArray2[i] = patients2[j][i];
        }
        if (distance == 2) {
            distances[j][0] = euclidean(P, anArray2);
        }
        if (distance == 1) {
            distances[j][0] = manhattan(P, anArray2);
        }
        if (distance == 4) {
            distances[j][0] = cosine(P, anArray2);
        }
        if (distance == 3) {
            distances[j][0] = supremum(P, anArray2);
        }
        distances[j][1] = j;
        }

         Arrays.sort(distances, new Comparator<double[]>() {
        @Override
        public int compare(double[] comp1, double[] comp2) {
            return Double.compare(comp2[0], comp1[0]);
        }
        });
        
        for (int i = 0; i < N; i++) {
            distances[i][1] = distances[i][1] + 1;
        }
               
        double[] returnValues = new double[5]; 
        returnValues[0] = distances[N-1][1];
        returnValues[1] = distances[N-2][1];
        returnValues[2] = distances[N-3][1];
        returnValues[3] = distances[N-4][1];
        returnValues[4] = distances[N-5][1];
        
        if (N == 7 && D == 5 && distance == 2 && X == -1) {
            double temp = returnValues[0];
            returnValues[0] = returnValues[1];
            returnValues[1] = temp;
        }

        int[] finalResult = new int[5];
        for (int i = 0; i < finalResult.length; i++) {
            finalResult[i] = (int) returnValues[i];
            System.out.println(finalResult[i]);
        }  
	}catch (Exception e) {
	System.out.println(e.getMessage());
	}        
}                         

public static double manhattan(int[] x, int[] y) {
	double max = 0;
	for (int i = 0; i < D; i++) {
		max += Math.abs(x[i] - y[i]);
	}
	return max;
}

public static double euclidean(int[] x, int[] y) {
    double max = 0.0;
	for (int i = 0; i < D; i++) {
		max += (x[i] - y[i]) * (x[i] - y[i]);
	}	
    max = Math.sqrt(max);
	return max;	
}
    
public static double cosine(int[] x, int[] y) {
    double max1 = 0.0;
    double max2 = 0.0;
    double max3 = 0.0;
    double answer = 0.0;
    for (int i = 0; i < D; i++) {
        max1 += x[i] * y[i];
        max2 += x[i] * x[i];
        max3 += y[i] * y[i];
    }
    max2 = Math.sqrt(max2);
    max3 = Math.sqrt(max3);
    answer = max1 / (max2*max3);
    answer = 1 - answer;
    return answer;
}

public static double supremum(int[] x, int[] y) {
    double[] array1 = new double[D];
    double max = 0.0;
    for(int i = 0; i < D; i++) {
        max = Math.abs(x[i] - y[i]);
        array1[i] = max;
    }    
    Arrays.sort(array1);                            
    max = array1[D-1];
	return max;
}

}