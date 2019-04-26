public class Factorial {
    public static final BigInteger ZERO = new BigInteger("0");
    public static final BigInteger ONE = new BigInteger("1");

    public static void main(String[] args) {
        String arg = checkArguments(args);
        BigInteger number = new BigInteger(arg);
        factorial(number);
        System.out.println("The factorial of " + number + " is " + factorial(number));
    }

    public static String checkArguments(String[] args) {
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("Please enter a number!");
            } else {
                if (args.length > 1) {
                    throw new IllegalArgumentException("Please enter just 1 argument.");
                } else if (new BigInteger(args[0]).compareWith(ZERO) == -1) {
                    throw new IllegalArgumentException("Error! Please enter a positive number.");
                }
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Input must be an integer");
        }
        return args[0];
    }

    public static BigInteger factorial(BigInteger val) {
        BigInteger result = ONE;
        BigInteger counter = ONE;
        if (val.compareWith(ZERO) == 0 || val.compareWith(ONE) == 0) {
            return ONE;
        } else {
            while (val.compareWith(counter) != 0) {
                result = result.product(counter);
                counter = counter.sum(ONE);
            }
        }
        return result.product(counter);
    }
}
