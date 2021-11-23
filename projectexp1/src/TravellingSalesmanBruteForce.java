// helper: https://github.com/williamfiset/algorithms
// https://www.baeldung.com/java-array-permutations

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

public class TravellingSalesmanBruteForce {

  public static Double travelingSalesman(double[][] matrix, List<List<Double>> newPermutations) {
    int matrixLength = matrix.length;
    int[] permutation = new int[matrixLength];

    for (int i = 0; i < matrixLength; i++)
      permutation[i] = i;

    List<Double> bestTour = new ArrayList<>();
    Double bestTourCost = Double.POSITIVE_INFINITY;

    for (int i = 0; i < newPermutations.size(); i++) {
      double[][] tours = nextPermutation(newPermutations.get(i), matrix);

      double tourCost = computeCost(tours);

      if (tourCost < bestTourCost) {
        bestTourCost = tourCost;
        bestTour = newPermutations.get(i);
      }

      // System.out.printf("Current tour %s \n", (newPermutations.get(i)));
      // System.out.printf("Tour Cost %s \n", tourCost);

      // System.out.println("\n-------------------------");
    }

    System.out.printf("Best tour -> %s \n", bestTour);
    return bestTourCost;
  }

  public static double computeCost(double[][] matrix) {
    double cost = 0;
    double[] totalCost = new double[matrix.length];
    int endTour = matrix.length - 1;
    int from = 0;
    int to = 1;

    while (from < endTour) {
      double[] ptx1 = matrix[from];
      double[] ptx2 = matrix[to];

      // Compute the Euclidiean distance of each points
      cost = calculateEuclidianDistance(ptx1, ptx2);

      // iterate over to the next number
      from++;
      to++;

      // compute the cost of the last tour with the first.
      if (from == endTour) {
        cost += calculateEuclidianDistance(matrix[from], matrix[0]);
      }
      totalCost[from] = cost;
    }
    double total = DoubleStream.of(totalCost).sum();

    return total;
  }

  private static double calculateEuclidianDistance(double[] ptx1, double[] ptx2) {
    double distance = 0;

    for (int i = 1; i < ptx1.length;) {
      double points = (ptx1[i] - ptx2[i]);
      distance = Math.sqrt(Math.pow(points, 2));
      return distance;
    }
    return distance;
  }

  private static void generatePermutations(List<Double> array, List<Double> currentPermutation,
      List<List<Double>> permutations) {

    if (array.size() == 0 && currentPermutation.size() > 0) {
      permutations.add(currentPermutation);
    } else {
      for (int i = 0; i < array.size(); i++) {
        List<Double> newArr = new ArrayList<Double>(array);
        newArr.remove(i);
        List<Double> newCurrentPermutation = new ArrayList<Double>(currentPermutation);
        newCurrentPermutation.add(array.get(i));
        generatePermutations(newArr, newCurrentPermutation, permutations);
      }
    }
  }

  private static List<List<Double>> generatePermutations(List<Double> array) {
    List<List<Double>> permutations = new ArrayList<List<Double>>();
    generatePermutations(array, new ArrayList<Double>(), permutations);
    return permutations;
  }

  public static double[][] nextPermutation(List<Double> seq, double[][] matrix) {
    double[][] perm = new double[matrix.length][matrix.length];

    // pointer starting at the seq
    int seqIdx = 0;
    // pointer starting at the matrix
    int matrixIdx = 0;

    while (matrixIdx < matrix.length) {
      if (matrix[matrixIdx][0] != seq.get(seqIdx)) {
        seqIdx += 1;
      } else if (matrix[matrixIdx][0] == seq.get(seqIdx)) {
        perm[seqIdx] = matrix[matrixIdx];
        seqIdx = 0;
        matrixIdx += 1;
      }
    }

    return perm;
  }

  public static void main(String[] args) throws Exception {
    double[][] matrix = { { 1, 1, 1 }, { 2, 5, 5 }, { 3, 10, 3 }, { 4, 2, 7 } };
    int matrixLength = matrix.length;
    List<Double> mainPermutation = new ArrayList<Double>(matrixLength);

    for (int i = 0; i < matrix.length; i++)
      mainPermutation.add(matrix[i][0]);

    List<List<Double>> newPermutations = generatePermutations(mainPermutation);
    System.out.println(newPermutations);

    Double bestTourCost = travelingSalesman(matrix, newPermutations);
    System.out.printf("Best distance -> %s", bestTourCost);
  }
}