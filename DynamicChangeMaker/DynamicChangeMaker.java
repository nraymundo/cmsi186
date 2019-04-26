public class DynamicChangeMaker {
    public Tuple[][] table;
    private Tuple typesOfCoins;
    private int neededChange;

    public static Tuple IMPOSSIBLE = new Tuple(new int[0]);

    public DynamicChangeMaker(int[] denominations, int wantedChange) {
        if (checkForDuplicates(denominations) || checkForNegativesAndZero(denominations)) {
            System.out.println("Cannot have duplicate or non-positive denominations.");
            System.exit(0);
        }
        table = new Tuple[denominations.length][wantedChange + 1];
        for (int i = 0; i < denominations.length; i++) {
            table[i][0] = new Tuple(denominations.length);
        }
        typesOfCoins = new Tuple(denominations);
        neededChange = wantedChange;
    }

    public static boolean checkForDuplicates(int[] denominations) {
        for (int i = 0; i < denominations.length; i++) {
            for (int j = i + 1; j < denominations.length; j++) {
                if (denominations[i] == denominations[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkForNegativesAndZero(int[] denominations) {
        for (int i = 0; i < denominations.length; i++) {
            if (denominations[i] <= 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isImpossible(Tuple tuple) {
        return tuple.equals(IMPOSSIBLE);
    }

    public void setColumnsAndRows(int column, int row, Tuple[][] table) {
        table[row][column] = new Tuple(typesOfCoins.length());
        if (column >= 1) {
            if ((column - typesOfCoins.getElement(row)) >= 0) {
                table[row][column].setElement(row, 1);
                int difference = column - typesOfCoins.getElement(row);
                if (isImpossible(table[row][difference])) {
                    table[row][column] = IMPOSSIBLE;
                } else {
                    table[row][column] = table[row][column].add(table[row][difference]);
                }
            } else {
                table[row][column] = IMPOSSIBLE;
            }
        }
        if (row >= 1) {
            if (isImpossible(table[row][column])) {
                table[row][column] = table[row - 1][column].clone();
            }
            if (!(isImpossible(table[row][column])) && !(isImpossible(table[row - 1][column]))
                    && (table[row][column].sum() > table[row - 1][column].sum())) {
                table[row][column] = table[row - 1][column].clone();
            }
        }
    }

    public void solveTable() {
        for (int column = 0; column <= neededChange; column++) {
            for (int row = 0; row < typesOfCoins.length(); row++) {
                setColumnsAndRows(column, row, table);
            }
        }
    }

    public Tuple getSolution() {
        return table[typesOfCoins.length() - 1][neededChange];
    }

    public static void showSolution(int[] denominations, DynamicChangeMaker argsChangeMaker) {
        if (isImpossible(argsChangeMaker.getSolution())) {
            System.out.println("IMPOSSIBLE");
            System.exit(0);
        } else {
            for (int i = 0; i < denominations.length; i++) {
                System.out.println(argsChangeMaker.getSolution().getElement(i) + " " + denominations[i] + "-cent coins");
            }
            System.out.println("Total coins: " + argsChangeMaker.getSolution().sum());
        }
    }

    public static void solveArguments(String[] args) {
        try {
            int[] denominations = new int[args.length - 1];
            for (int i = 0; i < args.length - 1; i++) {
                denominations[i] = Integer.parseInt(args[i]);
            }
            int wantedChange = Integer.parseInt(args[args.length - 1]);
            if (wantedChange < 0) {
                System.out.println("Cannot have a negative wanted change.");
                System.exit(0);
            }
            DynamicChangeMaker argsChangeMaker = new DynamicChangeMaker(denominations, wantedChange);
            argsChangeMaker.solveTable();
            showSolution(denominations, argsChangeMaker);
        } catch (NumberFormatException nfe) {
            System.out.println("Please only provide integers.");
        }
    }

    public static void main(String[] args) {
        if (args.length <= 1) {
            System.out.println("Please provide at least 2 arguments.");
            System.exit(0);
        } else {
            solveArguments(args);
        }
    }

}
