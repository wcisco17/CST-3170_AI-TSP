
import java.util.Arrays;
import java.util.stream.DoubleStream;

public class TravellingSalesmanBruteForce {
  public static double computeCost(int[] perms, double[][] matrix) {
    double cost = 0;
    double[] totalCost = new double[matrix.length];
    int endTour = perms.length - 1;
    int from = 0;
    int to = 1;

    System.out.printf("Current Tour %s \n", Arrays.toString(perms));
    while (from < endTour) {
      int ptx1 = perms[from];
      int ptx2 = perms[to];

      // Compute the Euclidiean distance of each points
      cost = calculateEuclidianDistance(matrix[ptx1], matrix[ptx2]);
      System.out.println("\n" + Arrays.toString(matrix[ptx1]) + "  " + Arrays.toString(matrix[ptx2]));

      // iterate over to the next number
      from++;
      to++;

      // compute the cost of the last tour with the first.
      System.out.println("cost=" + cost);
      totalCost[from] = cost;

      if (from == endTour) {
        cost = calculateEuclidianDistance(matrix[perms[from]], matrix[perms[0]]);
        totalCost[0] = cost;
      }
    }
    double total = DoubleStream.of(totalCost).sum();

    System.out.println("\ntotal =" + total);
    System.out.println("--------------------------------\n");
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

  public static boolean nextPermutation(int[] sequence) {
    int first = getFirst(sequence);
    if (first == -1)
      return false;
    int toSwap = sequence.length - 1;
    while (sequence[first] >= sequence[toSwap])
      --toSwap;
    swap(sequence, first++, toSwap);
    toSwap = sequence.length - 1;
    while (first < toSwap)
      swap(sequence, first++, toSwap--);
    return true;
  }

  private static int getFirst(int[] sequence) {
    for (int i = sequence.length - 2; i >= 0; --i)
      if (sequence[i] < sequence[i + 1])
        return i;
    return -1;
  }

  private static void swap(int[] sequence, int i, int j) {
    int tmp = sequence[i];
    sequence[i] = sequence[j];
    sequence[j] = tmp;
  }

  public static void main(String[] args) throws Exception {
    double[][] matrix = { { 1, 1, 1 }, { 2, 5, 5 }, { 3, 10, 3 }, { 4, 2, 7 } };

    double[][] test__2 = { { 1, 38, 20 }, { 2, 39, 26 }, { 3, 40, 25 }, { 4, 36, 23 }, { 5, 38, 13 }, { 6, 37, 20 },
        { 7, 41, 9 }, { 8, 36, -5 } };

    double[][] test_one_2019 = { { 1, 1357, 1905 }, { 2, 2650, 802 }, { 3, 1774, 107 }, { 4, 1307, 964 },
        { 5, 3806, 746 }, { 6, 2687, 1353 }, { 7, 43, 1957 }, { 8, 3092, 1668 }, { 9, 185, 1542 }, { 10, 834, 629 },
        { 11, 40, 462 } };

    int[] perm = new int[matrix.length];
    for (int i = 0; i < matrix.length; i++)
      perm[i] = i;

    double[][] bestTour = matrix.clone();
    Double bestTourCost = Double.POSITIVE_INFINITY;

    do {
      double tourCost = computeCost(perm, matrix);

      if (tourCost < bestTourCost) {
        bestTourCost = tourCost;
        bestTour = matrix.clone();
      }

    } while (nextPermutation(perm));

    System.out.printf("Overall Tour -> %s \n", Arrays.toString(perm));
    System.out.printf("Best tour %s ", bestTourCost);
  }
}