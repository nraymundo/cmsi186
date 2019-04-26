public class ClockSolverLine {

    public static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
    public static final double HOUR_HAND_DEGREES_PER_SECOND = 0.1 / 12;
    public static final double DEGREES_IN_CIRCLE = 360.0;
    public static final int THIRTY_MINUTES_AS_SECONDS = 1800;
    public static final int STRAIGHT_LINE_IN_DEGREES = 180;

    public static Clock checkArguments(String[] args) {
        Clock militaryTime = new Clock();
        try {
            if (args.length > 0) {
                if (args.length > 1) {
                    throw new IllegalArgumentException("Please enter in 0 or 1 arguments.");
                }
                if (Double.parseDouble(args[0]) <= 0.0 || Double.parseDouble(args[0]) > THIRTY_MINUTES_AS_SECONDS) {
                    throw new IllegalArgumentException("Error! Enter in one number that is between 1 and 1800 seconds, inclusive, as a desired timeslice. If no number is provided, a timeslice of 60 seconds will be used.");
                } else if (args.length == 1) {
                    militaryTime = new Clock(Double.parseDouble(args[0]));
                }
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Input must be an integer");
        }
        return militaryTime;
    }

    public static void checkForAngles(Clock clock, double degreeDifference, double degreeDifferencePerSecond) {
        if (degreeDifference >= DEGREES_IN_CIRCLE) {
            degreeDifference = degreeDifference % DEGREES_IN_CIRCLE;
        }
        if (degreeDifference < (STRAIGHT_LINE_IN_DEGREES + degreeDifferencePerSecond) && degreeDifference > (STRAIGHT_LINE_IN_DEGREES - degreeDifferencePerSecond)) {
            System.out.println("Desired angle at " + clock.toString() + "\n");
        }
    }

    public static void iterateThroughTime(Clock clock) {
        double degreeDifference = 0;
        while (clock.getHoursPassed() < 12) {
            clock.tick();
            degreeDifference += (clock.getTimeslice() * (MINUTE_HAND_DEGREES_PER_SECOND - HOUR_HAND_DEGREES_PER_SECOND));
            double degreeDifferencePerSecond = (clock.getTimeslice() * (MINUTE_HAND_DEGREES_PER_SECOND - HOUR_HAND_DEGREES_PER_SECOND))/2;
            checkForAngles(clock, degreeDifference, degreeDifferencePerSecond);
        }
    }

    public static void main(String[] args) {
        Clock militaryTime = checkArguments(args);
        System.out.println("\n" + "Angle: 180 degrees.");
        System.out.println("Timeslice: " + militaryTime.getTimeslice() + " seconds" + "\n");
        iterateThroughTime(militaryTime);
    }
}
