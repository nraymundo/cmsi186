public class CountMultiplesBelow {
    public static void main(String[] args) {
        try {
            if (args.length > 1) {
                int firstMultiple = Integer.parseInt(args[0]);
                int secondInteger = Integer.parseInt(args[1]);
                if (secondInteger <= 0 ) {
                    System.out.println("There are no positive integer multiples for negative numbers and zero.");
                } else if (firstMultiple <= 0) {
                    System.out.println("The first integer should be a positive number.");
                } else if (firstMultiple > secondInteger) {
                    System.out.println("The first integer has to be less than the second integer.");
                } else {
                    int multipleCounter = firstMultiple;
                    while (multipleCounter <= secondInteger) {
                        System.out.println(multipleCounter + " ");
                        multipleCounter += firstMultiple;
                    }
                }
            } else {
                System.out.println("Please enter 2 arguments!");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("The argument(s) entered are not numbers.");
        }
    }
}
