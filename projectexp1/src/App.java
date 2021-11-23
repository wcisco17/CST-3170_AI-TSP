public class App {
    public static int[][] coordinates = { { 1, 1 }, { 5, 5 }, { 10, 3 }, { 2, 7 } };

    public static void main(String[] args) throws Exception {
        double distance = computeTourCost(coordinates);
        System.out.print(distance);
    }

    private static double computeTourCost(int[][] coordinates) {
        int bestPath = 0;

        double distance = 0;
        int start = 0;
        int end = coordinates.length - 1;
        for (int i = 1; i < coordinates.length;) {
            distance = calculateEuclidianDistance(coordinates[start][0], coordinates[i][0]);
            start += 1;
            return distance;
        }
        return distance;
    }

    private static double calculateEuclidianDistance(double ptx1, double ptx2) {
        double distance = 0;
        double points = (ptx1 - ptx2);
        distance = Math.sqrt(Math.pow(points, 2));
        return distance;
    }
}
