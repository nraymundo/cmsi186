public class ClockSolverAngle {

    public static Clock checkArguments(String[] args) {
        Clock militaryTime = new Clock();
        try {
            if (args.length > 2 || args.length < 1) {
                throw new IllegalArgumentException("Please enter in 1 or 2 arguments.");
            } else if (args.length == 1) {
                if (Double.parseDouble(args[0]) < 0.0 || Double.parseDouble(args[0]) > ClockSolverLine.DEGREES_IN_CIRCLE) {
                    throw new IllegalArgumentException("Error! Enter in one number that is > 0 and < 360.0 as a desired angle.");
                }
            } else if (args.length == 2) {
                if (Double.parseDouble(args[0]) < 0.0 || Double.parseDouble(args[0]) > ClockSolverLine.DEGREES_IN_CIRCLE) {
                    throw new IllegalArgumentException("Error! Enter in one number that is > 0 and < 360.0 as a desired angle.");
                } else if (Double.parseDouble(args[1]) <= 0.0 || Double.parseDouble(args[1]) > ClockSolverLine.THIRTY_MINUTES_AS_SECONDS) {
                    throw new IllegalArgumentException("As a second argument, you have the option of entering a timeslice that is between 1 and 1800 seconds, inclusive. If no timeslice is provided, a timeslice of 60 seconds will be used.");
                } else {
                    militaryTime = new Clock(Double.parseDouble(args[1]));
                }
            }
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Inputs have to be integers.");
        }
        return militaryTime;
    }

    public static void checkForAngles(Clock clock, double degreeDifference, double desiredAngle, double degreeDifferencePerSecond) {
        if (degreeDifference >= ClockSolverLine.DEGREES_IN_CIRCLE) {
            degreeDifference = degreeDifference % ClockSolverLine.DEGREES_IN_CIRCLE;
        }
        if (degreeDifference < (desiredAngle + degreeDifferencePerSecond) && degreeDifference > (desiredAngle - degreeDifferencePerSecond)) {
            System.out.println("Desired angle at " + clock.toString() + "\n");
        }
        if (degreeDifference < ((ClockSolverLine.DEGREES_IN_CIRCLE - desiredAngle) + degreeDifferencePerSecond) && degreeDifference > ((ClockSolverLine.DEGREES_IN_CIRCLE - desiredAngle) - degreeDifferencePerSecond)) {
            System.out.println("Desired angle at " + clock.toString() + "\n");
        }
    }

    public static void iterateThroughTime(Clock clock, double desiredAngle) {
        double degreeDifference = 0;
        while (clock.getHoursPassed() < 12) {
            clock.tick();
            degreeDifference += (clock.getTimeslice() * (ClockSolverLine.MINUTE_HAND_DEGREES_PER_SECOND - ClockSolverLine.HOUR_HAND_DEGREES_PER_SECOND));
            double degreeDifferencePerSecond = (clock.getTimeslice() * (ClockSolverLine.MINUTE_HAND_DEGREES_PER_SECOND - ClockSolverLine.HOUR_HAND_DEGREES_PER_SECOND))/2;
            checkForAngles(clock, degreeDifference, desiredAngle, degreeDifferencePerSecond);
        }
    }

    public static void main(String[] args) {
        Clock militaryTime = checkArguments(args);
        System.out.println("\n" + "Angle inputted: " + args[0] + " degrees.");
        System.out.println("Timeslice: " + militaryTime.getTimeslice() + " seconds" + "\n");
        double desiredAngle = Double.parseDouble(args[0]);
        iterateThroughTime(militaryTime, desiredAngle);
    }
}
