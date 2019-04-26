public class PiEstimator {
    private static double dartX, dartY, radius, dartDistance, pi, numberOfDarts, dartsInCircle;

    public static double checkArguments(String[] args) {
        numberOfDarts = 1000000;
        try {
            if (args.length > 0) {
                if (args.length > 1) {
                    throw new IllegalArgumentException("Please enter 0 or 1 arguments.");
                }
                if (Double.parseDouble(args[0]) <= 0.0) {
                    throw new IllegalArgumentException("Error! Please enter a positive number.");
                } else if (args.length == 1) {
                    numberOfDarts = Double.parseDouble(args[0]);
                }
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Input must be an integer");
        }
        return numberOfDarts;
    }

    public static double generateNumber() {
        double number = (Math.random() * (1 - (-1))) - 1;
        return number;
    }

    public static double[] generateDart() {
        double coordinates[] = new double[2];
        coordinates[0] = generateNumber();
        coordinates[1] = generateNumber();
        return coordinates;
    }

    public static double generateDartDistance(double dartX, double dartY) {
        return Math.sqrt(Math.pow(dartX, 2) + Math.pow(dartY, 2));
    }

    public static void throwDarts(double numberOfDarts) {
        dartsInCircle = 0;
        radius = 1;
        System.out.println("start");
        for (double i = 0; i < numberOfDarts; i++) {
            double[] dart = generateDart();
            if (generateDartDistance(dart[0], dart[1]) <= radius) {
                System.out.println(dart[0] + " " + dart[1] + " in");
                dartsInCircle++;
            } else {
                System.out.println(dart[0] + " " + dart[1] + " out");
            }
        }
        System.out.println("end");
        pi = 4 * (dartsInCircle / numberOfDarts);
    }

    public static void main(String[] args) {
        numberOfDarts = checkArguments(args);
        throwDarts(numberOfDarts);
        System.out.println(pi);
    }

}
