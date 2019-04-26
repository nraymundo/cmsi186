public class ReplaceThirdWords {
    public static void main(String[] args) {
        String newString = "";
        int numOfArgs = args.length;
        if (numOfArgs > 0) {
            for (int i = 0; i <= numOfArgs - 1; i++) {
                args[i].split("\\ ");
                if (((i + 1) % 3 == 0)) {
                    newString += "um ";
                } else {
                    newString += args[i] + " ";
                }
            }
            System.out.println(newString);
        } else {
            System.out.println("Please enter at least 1 word.");
        }
    }
}
