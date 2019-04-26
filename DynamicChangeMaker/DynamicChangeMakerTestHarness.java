public class DynamicChangeMakerTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_USA();
        test_Euros();
        test_SwissFrancs();
        test_NicoNicos();
        test_FlaverlakingsOstentaciouses();
        test_AfghanAfghani();
        test_checkForDuplicates();
        test_checkForNegatives();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    public static void test_checkForDuplicates() {
        System.out.println("\nTests for checkForDuplicates\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        try {
            int[] denominations = new int[]{12,6,4,2,12};
            displaySuccessIfTrue(DynamicChangeMaker.checkForDuplicates(denominations) == true);
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            int[] denominations = new int[]{12,6,4,2};
            displaySuccessIfTrue(DynamicChangeMaker.checkForDuplicates(denominations) == false);
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            int[] denominations = new int[]{4,6,4,2};
            displaySuccessIfTrue(DynamicChangeMaker.checkForDuplicates(denominations) == true);
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            int[] denominations = new int[]{4,6,1,2,5,3,77,6};
            displaySuccessIfTrue(DynamicChangeMaker.checkForDuplicates(denominations) == true);
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        System.out.println("\ncheckForDuplicates: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");

    }

    public static void test_checkForNegatives() {
        System.out.println("\nTests for checkForNegatives\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        try {
            int[] denominations = new int[]{12,6,4,2};
            displaySuccessIfTrue(DynamicChangeMaker.checkForNegativesAndZero(denominations) == false);
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            int[] denominations = new int[]{12,-6,4,2};
            displaySuccessIfTrue(DynamicChangeMaker.checkForNegativesAndZero(denominations) == true);
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            int[] denominations = new int[]{4,6,-14,2};
            displaySuccessIfTrue(DynamicChangeMaker.checkForNegativesAndZero(denominations) == true);
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            int[] denominations = new int[]{4,6,1,2,5,3,77,16};
            displaySuccessIfTrue(DynamicChangeMaker.checkForNegativesAndZero(denominations) == false);
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            int[] denominations = new int[]{4,6,1,2,5,3,-77,16};
            displaySuccessIfTrue(DynamicChangeMaker.checkForNegativesAndZero(denominations) == true);
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        System.out.println("\ncheckForNegatives: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");

    }

    public static void test_USA() {
        System.out.println("\nTests for USA denominations {25,10,5,1}:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        int[] usaDenominations = new int[] { 25, 10, 5, 1 };

        try {
            DynamicChangeMaker usaTest = new DynamicChangeMaker(usaDenominations, 99);
            usaTest.solveTable();
            displaySuccessIfTrue(usaTest.getSolution().length() == 4
                    && 3 == usaTest.getSolution().getElement(0)
                    && 2 == usaTest.getSolution().getElement(1)
                    && 0 == usaTest.getSolution().getElement(2)
                    && 4 == usaTest.getSolution().getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] usaDenominationsShuffled = new int[] { 5, 1, 25, 10 };

        try {
            DynamicChangeMaker usaTest = new DynamicChangeMaker(usaDenominationsShuffled, 99);
            usaTest.solveTable();
            displaySuccessIfTrue(usaTest.getSolution().length() == 4
                    && 0 == usaTest.getSolution().getElement(0)
                    && 4 == usaTest.getSolution().getElement(1)
                    && 3 == usaTest.getSolution().getElement(2)
                    && 2 == usaTest.getSolution().getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] usaDenominationsShuffled2 = new int[] { 10, 5, 25, 1 };

        try {
            DynamicChangeMaker usaTest = new DynamicChangeMaker(usaDenominationsShuffled2, 99);
            usaTest.solveTable();
            displaySuccessIfTrue(usaTest.getSolution().length() == 4
                    && 2 == usaTest.getSolution().getElement(0)
                    && 0 == usaTest.getSolution().getElement(1)
                    && 3 == usaTest.getSolution().getElement(2)
                    && 4 == usaTest.getSolution().getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        System.out.println("\nUSA denominations: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");

    }

    public static void test_Euros() {
        System.out.println("\nTests for EU denominations {1,2,5,10,20,50}:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        int[] euDenominations = new int[] { 1, 2, 5, 10, 20, 50 };

        try {
            DynamicChangeMaker euTest = new DynamicChangeMaker(euDenominations, 10);
            euTest.solveTable();
            displaySuccessIfTrue(euTest.getSolution().length() == 6
                    && 0 == euTest.getSolution().getElement(0)
                    && 0 == euTest.getSolution().getElement(1)
                    && 0 == euTest.getSolution().getElement(2)
                    && 1 == euTest.getSolution().getElement(3)
                    && 0 == euTest.getSolution().getElement(4)
                    && 0 == euTest.getSolution().getElement(5));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] euDenominations2 = new int[] { 1, 2, 5, 10, 20, 50 };

        try {
            DynamicChangeMaker euTest = new DynamicChangeMaker(euDenominations2, 75);
            euTest.solveTable();
            displaySuccessIfTrue(euTest.getSolution().length() == 6
                    && 0 == euTest.getSolution().getElement(0)
                    && 0 == euTest.getSolution().getElement(1)
                    && 1 == euTest.getSolution().getElement(2)
                    && 0 == euTest.getSolution().getElement(3)
                    && 1 == euTest.getSolution().getElement(4)
                    && 1 == euTest.getSolution().getElement(5));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] euDenominationsShuffled = new int[] { 5, 2, 1, 50, 20, 10 };

        try {
            DynamicChangeMaker euTest = new DynamicChangeMaker(euDenominationsShuffled, 97);
            euTest.solveTable();
            displaySuccessIfTrue(euTest.getSolution().length() == 6
                    && 1 == euTest.getSolution().getElement(0)
                    && 1 == euTest.getSolution().getElement(1)
                    && 0 == euTest.getSolution().getElement(2)
                    && 1 == euTest.getSolution().getElement(3)
                    && 2 == euTest.getSolution().getElement(4)
                    && 0 == euTest.getSolution().getElement(5));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] euDenominationsShuffled2 = new int[] { 20, 2, 50, 10, 1, 5 };

        try {
            DynamicChangeMaker euTest = new DynamicChangeMaker(euDenominationsShuffled2, 36);
            euTest.solveTable();
            displaySuccessIfTrue(euTest.getSolution().length() == 6
                    && 1 == euTest.getSolution().getElement(0)
                    && 0 == euTest.getSolution().getElement(1)
                    && 0 == euTest.getSolution().getElement(2)
                    && 1 == euTest.getSolution().getElement(3)
                    && 1 == euTest.getSolution().getElement(4)
                    && 1 == euTest.getSolution().getElement(5));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        System.out.println("\nEU denominations: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");

    }

    public static void test_SwissFrancs() {
        System.out.println("\nTests for Swiss denominations {5,10,20,50}:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        int[] swissDenominations = new int[] { 5, 10, 20, 50 };

        try {
            DynamicChangeMaker swissTest = new DynamicChangeMaker(swissDenominations, 100);
            swissTest.solveTable();
            displaySuccessIfTrue(swissTest.getSolution().length() == 4
                    && 0 == swissTest.getSolution().getElement(0)
                    && 0 == swissTest.getSolution().getElement(1)
                    && 0 == swissTest.getSolution().getElement(2)
                    && 2 == swissTest.getSolution().getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] swissDenominations2 = new int[] { 5, 10, 20, 50 };

        try {
            DynamicChangeMaker swissTest = new DynamicChangeMaker(swissDenominations, 99);
            swissTest.solveTable();
            displaySuccessIfTrue(swissTest.getSolution().equals(DynamicChangeMaker.IMPOSSIBLE));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] swissDenominationsShuffled = new int[] { 10, 20, 5, 50 };

        try {
            DynamicChangeMaker swissTest = new DynamicChangeMaker(swissDenominationsShuffled, 99);
            swissTest.solveTable();
            displaySuccessIfTrue(swissTest.getSolution().equals(DynamicChangeMaker.IMPOSSIBLE));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] swissDenominationsShuffled2 = new int[] { 20, 50, 10, 5 };

        try {
            DynamicChangeMaker swissTest = new DynamicChangeMaker(swissDenominationsShuffled2, 125);
            swissTest.solveTable();
            displaySuccessIfTrue(swissTest.getSolution().length() == 4
                    && 1 == swissTest.getSolution().getElement(0)
                    && 2 == swissTest.getSolution().getElement(1)
                    && 0 == swissTest.getSolution().getElement(2)
                    && 1 == swissTest.getSolution().getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        System.out.println("\nSwiss denominations: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");

    }

    public static void test_NicoNicos() {
        System.out.println("\nTests for Nico denominations {74,98,122,1,1000}:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        int[] nicoDenominations = new int[] { 74, 98, 122, 1, 1000 };

        try {
            DynamicChangeMaker nicoTest = new DynamicChangeMaker(nicoDenominations, 99);
            nicoTest.solveTable();
            displaySuccessIfTrue(nicoTest.getSolution().length() == 5
                    && 0 == nicoTest.getSolution().getElement(0)
                    && 1 == nicoTest.getSolution().getElement(1)
                    && 0 == nicoTest.getSolution().getElement(2)
                    && 1 == nicoTest.getSolution().getElement(3)
                    && 0 == nicoTest.getSolution().getElement(4));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] nicoDenominationsShuffled = new int[] { 1, 1000, 122, 74, 98 };

        try {
            DynamicChangeMaker nicoTest = new DynamicChangeMaker(nicoDenominationsShuffled, 1045);
            nicoTest.solveTable();
            displaySuccessIfTrue(nicoTest.getSolution().length() == 5
                    && 9 == nicoTest.getSolution().getElement(0)
                    && 0 == nicoTest.getSolution().getElement(1)
                    && 0 == nicoTest.getSolution().getElement(2)
                    && 14 == nicoTest.getSolution().getElement(3)
                    && 0 == nicoTest.getSolution().getElement(4));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] nicoDenominationsShuffled2 = new int[] { 1, 74, 98, 1000, 122 };

        try {
            DynamicChangeMaker nicoTest = new DynamicChangeMaker(nicoDenominationsShuffled2, 27);
            nicoTest.solveTable();
            displaySuccessIfTrue(nicoTest.getSolution().length() == 5
                    && 27 == nicoTest.getSolution().getElement(0)
                    && 0 == nicoTest.getSolution().getElement(1)
                    && 0 == nicoTest.getSolution().getElement(2)
                    && 0 == nicoTest.getSolution().getElement(3)
                    && 0 == nicoTest.getSolution().getElement(4));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        System.out.println("\nNico denominations: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");

    }

    public static void test_FlaverlakingsOstentaciouses() {
        System.out.println("\nTests for Flaverlakings denominations {3249,2,10,4,7,11,3}:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        int[] flaverlakingsDenominations = new int[] { 3249, 2, 10, 4, 7, 11, 3 };

        try {
            DynamicChangeMaker flaverlakingsTest = new DynamicChangeMaker(flaverlakingsDenominations, 1);
            flaverlakingsTest.solveTable();
            displaySuccessIfTrue(flaverlakingsTest.getSolution().equals(DynamicChangeMaker.IMPOSSIBLE));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] flaverlakingsDenominationsShuffled = new int[] { 3, 2, 11, 4, 7, 3249, 10 };

        try {
            DynamicChangeMaker flaverlakingsTest = new DynamicChangeMaker(flaverlakingsDenominationsShuffled, 100);
            flaverlakingsTest.solveTable();
            displaySuccessIfTrue((flaverlakingsTest.getSolution().length() == 7
                    && 0 == flaverlakingsTest.getSolution().getElement(0)
                    && 0 == flaverlakingsTest.getSolution().getElement(1)
                    && 0 == flaverlakingsTest.getSolution().getElement(2)
                    && 0 == flaverlakingsTest.getSolution().getElement(3)
                    && 0 == flaverlakingsTest.getSolution().getElement(4)
                    && 0 == flaverlakingsTest.getSolution().getElement(5)
                    && 10 == flaverlakingsTest.getSolution().getElement(6)) ||
                    (flaverlakingsTest.getSolution().length() == 7
                    && 1 == flaverlakingsTest.getSolution().getElement(0)
                    && 0 == flaverlakingsTest.getSolution().getElement(1)
                    && 3 == flaverlakingsTest.getSolution().getElement(2)
                    && 0 == flaverlakingsTest.getSolution().getElement(3)
                    && 0 == flaverlakingsTest.getSolution().getElement(4)
                    && 0 == flaverlakingsTest.getSolution().getElement(5)
                    && 2 == flaverlakingsTest.getSolution().getElement(6)));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] flaverlakingsDenominationsShuffled2 = new int[] { 10, 3249, 11, 4, 2, 3, 7 };

        try {
            DynamicChangeMaker flaverlakingsTest = new DynamicChangeMaker(flaverlakingsDenominationsShuffled2, 3251);
            flaverlakingsTest.solveTable();
            displaySuccessIfTrue(flaverlakingsTest.getSolution().length() == 7
                    && 0 == flaverlakingsTest.getSolution().getElement(0)
                    && 1 == flaverlakingsTest.getSolution().getElement(1)
                    && 0 == flaverlakingsTest.getSolution().getElement(2)
                    && 0 == flaverlakingsTest.getSolution().getElement(3)
                    && 1 == flaverlakingsTest.getSolution().getElement(4)
                    && 0 == flaverlakingsTest.getSolution().getElement(5)
                    && 0 == flaverlakingsTest.getSolution().getElement(6));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] flaverlakingsDenominationsShuffled3 = new int[] { 7, 3249, 11, 3, 10, 2, 4 };

        try {
            DynamicChangeMaker flaverlakingsTest = new DynamicChangeMaker(flaverlakingsDenominationsShuffled3, 14);
            flaverlakingsTest.solveTable();
            displaySuccessIfTrue((flaverlakingsTest.getSolution().length() == 7
                    && 2 == flaverlakingsTest.getSolution().getElement(0)
                    && 0 == flaverlakingsTest.getSolution().getElement(1)
                    && 0 == flaverlakingsTest.getSolution().getElement(2)
                    && 0 == flaverlakingsTest.getSolution().getElement(3)
                    && 0 == flaverlakingsTest.getSolution().getElement(4)
                    && 0 == flaverlakingsTest.getSolution().getElement(5)
                    && 0 == flaverlakingsTest.getSolution().getElement(6)) ||
                    (flaverlakingsTest.getSolution().length() == 7
                    && 0 == flaverlakingsTest.getSolution().getElement(0)
                    && 0 == flaverlakingsTest.getSolution().getElement(1)
                    && 0 == flaverlakingsTest.getSolution().getElement(2)
                    && 0 == flaverlakingsTest.getSolution().getElement(3)
                    && 1 == flaverlakingsTest.getSolution().getElement(4)
                    && 0 == flaverlakingsTest.getSolution().getElement(5)
                    && 1 == flaverlakingsTest.getSolution().getElement(6)) ||
                    (flaverlakingsTest.getSolution().length() == 7
                    && 0 == flaverlakingsTest.getSolution().getElement(0)
                    && 0 == flaverlakingsTest.getSolution().getElement(1)
                    && 1 == flaverlakingsTest.getSolution().getElement(2)
                    && 1 == flaverlakingsTest.getSolution().getElement(3)
                    && 0 == flaverlakingsTest.getSolution().getElement(4)
                    && 0 == flaverlakingsTest.getSolution().getElement(5)
                    && 0 == flaverlakingsTest.getSolution().getElement(6)));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        System.out.println("\nFlaverlakings denominations: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");

    }

    public static void test_AfghanAfghani() {
        System.out.println("\nTests for Afghan denominations {5,2,1}:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        int[] afghanDenominations = new int[] { 5, 2, 1 };

        try {
            DynamicChangeMaker afghanTest = new DynamicChangeMaker(afghanDenominations, 27);
            afghanTest.solveTable();
            displaySuccessIfTrue(afghanTest.getSolution().length() == 3
                    && 5 == afghanTest.getSolution().getElement(0)
                    && 1 == afghanTest.getSolution().getElement(1)
                    && 0 == afghanTest.getSolution().getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] afghanDenominations2 = new int[] { 5, 2, 1 };

        try {
            DynamicChangeMaker afghanTest = new DynamicChangeMaker(afghanDenominations2, 48);
            afghanTest.solveTable();
            displaySuccessIfTrue(afghanTest.getSolution().length() == 3
                    && 9 == afghanTest.getSolution().getElement(0)
                    && 1 == afghanTest.getSolution().getElement(1)
                    && 1 == afghanTest.getSolution().getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] afghanDenominationsShuffled = new int[] { 1, 5, 2 };

        try {
            DynamicChangeMaker afghanTest = new DynamicChangeMaker(afghanDenominationsShuffled, 12);
            afghanTest.solveTable();
            displaySuccessIfTrue(afghanTest.getSolution().length() == 3
                    && 0 == afghanTest.getSolution().getElement(0)
                    && 2 == afghanTest.getSolution().getElement(1)
                    && 1 == afghanTest.getSolution().getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        int[] afghanDenominationsShuffled2 = new int[] { 2, 1, 5 };

        try {
            DynamicChangeMaker afghanTest = new DynamicChangeMaker(afghanDenominationsShuffled2, 28);
            afghanTest.solveTable();
            displaySuccessIfTrue(afghanTest.getSolution().length() == 3
                    && 1 == afghanTest.getSolution().getElement(0)
                    && 1 == afghanTest.getSolution().getElement(1)
                    && 5 == afghanTest.getSolution().getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        System.out.println("\nAfghan denominations: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");

    }

}
