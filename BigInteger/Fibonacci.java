public class Fibonacci {
    public static final BigInteger ZERO = new BigInteger("0");
    public static final BigInteger ONE = new BigInteger("1");
    public static final BigInteger TWO = new BigInteger("2");

    public static void main(String args[]) {
        String arg = checkArguments(args);
        BigInteger number = new BigInteger(arg);
        // BigInteger fibonacciNumber = new BigInteger(args[0]);
        fibonacci(number);
        System.out.println("The " + number + "th fibonacci number is " + fibonacci(number));
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

    public static BigInteger fibonacci(BigInteger val) {
        BigInteger fibonacciValue = ONE;
        BigInteger prevFibonacciValue = ONE;
        if (val.compareWith(ONE) <= 0) {
            return val;
        } else {
            for (BigInteger i = TWO; i.compareWith(val) == -1; i = i.sum(ONE)) {
                BigInteger currentNumber = fibonacciValue;
                fibonacciValue = fibonacciValue.sum(prevFibonacciValue);
                prevFibonacciValue = currentNumber;
            }
            return fibonacciValue;
        }
    }
}
