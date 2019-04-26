public class GCD {
    public static final BigInteger ZERO = new BigInteger("0");
    public static final BigInteger ONE = new BigInteger("1");

    public static void main(String[] args) {
        String[] argsArray = checkArguments(args);
        BigInteger number = new BigInteger(argsArray[0]);
        BigInteger number2 = new BigInteger(argsArray[1]);
        gcd(number, number2);
        System.out.println("The GCD of " + number + " and " + number2 +  " is " + gcd(number, number2));
    }

    public static String[] checkArguments(String[] args) {
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("Please enter a number!");
            } else {
                if (args.length > 2) {
                    throw new IllegalArgumentException("Please only enter 2 argument.");
                } else if (args.length == 1) {
                    throw new IllegalArgumentException("Please enter 2 arguments.");
                } else if (new BigInteger(args[0]).compareWith(ZERO) == -1 || new BigInteger(args[1]).compareWith(ZERO) == -1) {
                    throw new IllegalArgumentException("Error! Please enter 2 positive number.");
                }
            }
        } catch (IllegalArgumentException nfe) {
            throw new IllegalArgumentException("Input must be an integer");
        }
        return args;
    }

    public static BigInteger gcd(BigInteger val, BigInteger val2) {
        if (val.compareWith(ZERO) == 0 || val2.compareWith(ZERO) == 0) {
            return (val.compareWith(ZERO) == 0) ? val2 : val;
        } else {
            while (!(val.equals(ZERO)) || !(val2.equals(ZERO))) {
                BigInteger newVal = new BigInteger(val.toString());
                val = val2;
                val2 = newVal.remainder(val2);
                if (val.equals(ZERO)) {
                    return val2;
                } else if (val2.equals(ZERO)) {
                    return val;
                }
            }
        }
        return ONE;
    }
}
