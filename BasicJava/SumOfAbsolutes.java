public class SumOfAbsolutes {
    public static void main(String[] args) {
        int total = 0;
        int numOfArgs = args.length;
        try {
            if (args.length >= 1) {
                for (int i = 0; i < numOfArgs; i++) {
                    total += Math.abs(Integer.parseInt(args[i]));
                }
                System.out.println(total);
            } else {
                System.out.println("Please enter at least 1 argument.");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("At least one of the arguments is not a number.");
        }
    }
}
