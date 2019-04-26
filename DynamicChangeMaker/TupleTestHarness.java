/**
    This is a test program for the items in class Tuple.
*/

public class TupleTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String [] args) {
        attempts = 0;
        successes = 0;

        test_both_constructors_and_length_getElement();
        test_static_factory();
        test_toString();
        test_setElement();
        test_getElement();
        test_add();
        test_sum();
        test_clone();
        test_equals();

        System.out.println("-----OVERALL PERFORMANCE-----");
        System.out.println(successes + "/" + attempts + " tests passed.");

    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayUnimplementedMethodFailure() {
        attempts++;
        System.out.println("failure (NYI)");
    }

    static void test_both_constructors_and_length_getElement() {

        System.out.println("\nTests for both constructors, length, and getElement:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        try {
            Tuple t = new Tuple(-2);
            displaySuccessIfTrue(t.length() == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(true);
        }

        try {
            Tuple t = new Tuple(0);
            displaySuccessIfTrue(t.length() == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(12);
            displaySuccessIfTrue(t.length() == 12);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(1);
            displaySuccessIfTrue(t.length() == 1 && t.getElement(0) == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(3);
            displaySuccessIfTrue(t.length() == 3 && t.getElement(0) == 0
                    && t.getElement(1) == 0 && t.getElement(2) == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(new int[]{ 23 });
            displaySuccessIfTrue(t.length() == 1 && t.getElement(0) == 23);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(new int[]{ 23, 0, -1 });
            displaySuccessIfTrue(t.length() == 3 && t.getElement(0) == 23
                    && t.getElement(1) == 0 && t.getElement(2) == -1);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(new int[]{ 100, 70, -10 });
            displaySuccessIfTrue(t.length() == 3 && t.getElement(0) == 100
                    && t.getElement(1) == 70 && t.getElement(2) == -10);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        System.out.println("\nconstructors, length, and getElement: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");
    }

    static void test_toString() {
        System.out.println("\nTests for toString, should output a single string:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        try {
            displaySuccessIfTrue(new Tuple(0).toString().compareTo("[]") == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(new Tuple(new int[]{ 23 }).toString().compareTo("[23]") == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(new Tuple(new int[]{ 23, 0, -1 }).toString().compareTo("[23,0,-1]") == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(new Tuple(new int[]{ 2, -1, 0, 5, 12, 123, -10, 39293, -9 }).toString().compareTo("[2,-1,0,5,12,123,-10,39293,-9]") == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(new Tuple(new int[]{ 100, 70, -100 }).toString().compareTo("[100,70,-100]") == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            displaySuccessIfTrue(new Tuple(new int[]{ 2, -10, 40, 50, 12, 102, -1000 }).toString().compareTo("[2,-10,40,50,12,102,-1000]") == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        System.out.println("\ntoString: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");

    }

    static void test_static_factory() {
        System.out.println("\nTest for the static factory:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        try {
            Tuple t = Tuple.makeTupleFromData(new int[]{ 2, -1, 0, 5, 12, 123, -10, 39293, -9 });
            displaySuccessIfTrue(t.length() == 9 && t.getElement(0) == 2
                    && t.getElement(7) == 39293 && t.getElement(4) == 12);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = Tuple.makeTupleFromData(new int[]{ 23, 0, -1 });
            displaySuccessIfTrue(t.length() == 3 && t.getElement(0) == 23
                    && t.getElement(1) == 0 && t.getElement(2) == -1);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = Tuple.makeTupleFromData(new int[]{ 23, 0, -1, 100, -70 });
            displaySuccessIfTrue(t.length() == 5 && t.getElement(0) == 23
                    && t.getElement(1) == 0 && t.getElement(2) == -1 && t.getElement(3) == 100 && t.getElement(4) == -70);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        System.out.println("\nstatic factory: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");

    }

    static void test_setElement() {
        System.out.println("\nTests for setElement:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        try {
            Tuple t = new Tuple(3);
            t.setElement(3,12345);  // should throw an exception
            displaySuccessIfTrue(false);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(3);
            t.setElement(12, 4202003);  // should throw an exception
            displaySuccessIfTrue(false);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(3);
            t.setElement(0,23); t.setElement(1,0); t.setElement(2,-1);
            displaySuccessIfTrue(t.length() == 3 && t.getElement(0) == 23 && t.getElement(1) == 0 && t.getElement(2) == -1);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(4);
            t.setElement(0,18); t.setElement(1,3); t.setElement(2,-100); t.setElement(3, 5489605);
            displaySuccessIfTrue(t.length() == 4 && t.getElement(0) == 18 && t.getElement(1) == 3 && t.getElement(2) == -100 && t.getElement(3) == 5489605);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(100);
            t.setElement(120, 4202003);  // should throw an exception
            displaySuccessIfTrue(false);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }


        System.out.println("\nsetElement: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed\n");
    }

    static void test_getElement() {
        System.out.println("\nTests for getElement:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        try {
            Tuple t = new Tuple(3);
            t.setElement(0,23); t.setElement(1,0); t.setElement(2,-1);
            displaySuccessIfTrue(!(t.getElement(2) == 4));
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(false);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(3);
            t.setElement(0,23); t.setElement(1,0); t.setElement(2,-1);
            displaySuccessIfTrue(t.getElement(0) == 23 && t.getElement(1) == 0 && t.getElement(2) == -1);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(1);
            t.setElement(0,23);
            displaySuccessIfTrue(t.getElement(0) == 23);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(10);
            displaySuccessIfTrue(t.getElement(9) == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(3000);
            t.getElement(312345);  // should throw an exception
            displaySuccessIfTrue(false);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(1039030);
            t.getElement(10493304);  // should throw an exception
            displaySuccessIfTrue(false);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        System.out.println("\ngetElement: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed\n");
    }

    static void test_add() {
        System.out.println("\nTests for add:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        try {
            // One of the tuples is all 0's:
            Tuple t0 = new Tuple(3);
            Tuple t1 = new Tuple(new int[]{ -25, 12, 5 });
            Tuple t = t0.add(t1);
            displaySuccessIfTrue(t.length() == 3 && t.getElement(0) == -25
                    && t.getElement(1) == 12 && t.getElement(2) == 5);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            // The tuples have incompatible sizes:
            Tuple t0 = new Tuple(7);
            Tuple t1 = new Tuple(new int[]{ -25, 12, 5 , 17 });
            Tuple t = t0.add(t1);
            displaySuccessIfTrue(false);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            // Both tuples are 3-tuples:
            Tuple t0 = new Tuple(new int[]{ 23, 0, -1 });
            Tuple t1 = new Tuple(new int[]{ -25, 12, 5 });
            Tuple t = t0.add(t1);
            displaySuccessIfTrue(t.length() == 3 && t.getElement(0) == -2 && t.getElement(1) == 12 && t.getElement(2) == 4);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            // The tuples have incompatible sizes:
            Tuple t0 = new Tuple(4);
            Tuple t1 = new Tuple(new int[]{-25,12,5});
            Tuple t = t0.add(t1);
            displaySuccessIfTrue(false);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            // The tuples have incompatible sizes:
            Tuple t0 = new Tuple(1);
            Tuple t1 = new Tuple(new int[]{-25,12,5,27,-1000});
            Tuple t = t0.add(t1);
            displaySuccessIfTrue(false);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            // The tuples have incompatible sizes:
            Tuple t0 = new Tuple(90);
            Tuple t1 = new Tuple(new int[]{-25,12,5,27,-1000});
            Tuple t = t0.add(t1);
            displaySuccessIfTrue(false);
        } catch (IllegalArgumentException iae) {
            displaySuccessIfTrue(true);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        System.out.println("\nadd: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");
    }

    static void test_sum() {
        System.out.println("\nTests for sum:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        try {
            Tuple t = new Tuple(0);
            displaySuccessIfTrue(t.sum() == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(new int[]{23,0,-1});
            displaySuccessIfTrue(t.sum() == 22);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(new int[]{23,0,-1});
            displaySuccessIfTrue(!(t.sum() == 19));
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(new int[]{139030,-123,153939});
            displaySuccessIfTrue(t.sum() == 292846);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        System.out.println("\nsum: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");
    }

    static void test_clone() {
        System.out.println("\nTest for clone:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        try {
            Tuple t0 = new Tuple(3);
            t0.setElement(0,23); t0.setElement(1,0); t0.setElement(2,-1);
            Tuple t1 = t0.clone();
            displaySuccessIfTrue(t1.length() == 3 && t0 != t1 && t1.getElement(0) == 23 && t1.getElement(1) == 0 && t1.getElement(2) == -1);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t0 = new Tuple(1);
            t0.setElement(0,23);
            Tuple t1 = t0.clone();
            displaySuccessIfTrue(t1.length() == 1 && t0 != t1 && t1.getElement(0) == 23);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t0 = new Tuple(2);
            t0.setElement(0,23); t0.setElement(1,0);
            Tuple t1 = t0.clone();
            displaySuccessIfTrue(t1.length() == 2 && t0 != t1 && t1.getElement(0) == 23 && t1.getElement(1) == 0);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t0 = new Tuple(200);
            t0.setElement(0,23); t0.setElement(1,0);
            Tuple t1 = t0.clone();
            displaySuccessIfTrue(!(t1.length() == 2));
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t0 = new Tuple(4);
            t0.setElement(0,23); t0.setElement(1,0); t0.setElement(2,-1); t0.setElement(3,-1);
            Tuple t1 = t0.clone();
            displaySuccessIfTrue(t1.length() == 4 && t0 != t1 && t1.getElement(0) == 23 && t1.getElement(1) == 0 && t1.getElement(2) == -1 && t1.getElement(3) == -1);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        System.out.println("\nclone: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed\n");
    }

    static void test_equals() {
        System.out.println("\nTests for equals:\n");

        int initialSuccesses = successes;
        int initialAttempts = attempts;

        try {
            Tuple t = new Tuple(3);
            t.setElement(0,23); t.setElement(1,0); t.setElement(2,-1);
            displaySuccessIfTrue(t != null);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(3);
            t.setElement(0,23); t.setElement(1,0); t.setElement(2,-1);
            displaySuccessIfTrue(!t.equals("xylophones"));
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t0 = new Tuple(3);
            t0.setElement(0,23); t0.setElement(1,0); t0.setElement(2,-1);
            Tuple t1 = new Tuple(2);
            t1.setElement(0,23); t1.setElement(1,0);
            displaySuccessIfTrue(!t0.equals(t1));
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t0 = new Tuple(3);
            t0.setElement(0,23); t0.setElement(1,0); t0.setElement(2,-1);
            Tuple t1 = new Tuple(3);
            t1.setElement(0,23); t1.setElement(1,0); t1.setElement(2,-1);
            displaySuccessIfTrue(t0.equals(t1));
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t0 = new Tuple(1);
            t0.setElement(0,23);
            Tuple t1 = new Tuple(3);
            t1.setElement(0,23); t1.setElement(1,0); t1.setElement(2,-1);
            displaySuccessIfTrue(!t0.equals(t1));
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t0 = new Tuple(3);
            t0.setElement(0,12); t0.setElement(1,110); t0.setElement(2,-10);
            Tuple t1 = new Tuple(3);
            t1.setElement(0,12); t1.setElement(1,110); t1.setElement(2,-10);
            displaySuccessIfTrue(t0.equals(t1));
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(1);
            t.setElement(0,10);
            displaySuccessIfTrue(t != null);
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        try {
            Tuple t = new Tuple(2);
            t.setElement(0,7); t.setElement(1, 90);
            displaySuccessIfTrue(!t.equals("dancedancerevolution"));
        } catch (UnsupportedOperationException uoe) {
            displayUnimplementedMethodFailure();
        } catch (Exception e) {
            displaySuccessIfTrue(false);
        }

        System.out.println("\nequals: " + (successes - initialSuccesses) + "/" + (attempts - initialAttempts) + " passed");
    }

}
