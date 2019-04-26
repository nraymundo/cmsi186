public class Clock {
    private double seconds, timesliceInSeconds;
    private int hours, minutes;
    /**
     * Constructs a Clock with a 60.0 second timeslice.
     */
    public Clock() {
        this.timesliceInSeconds = 60.0;
        this.seconds = 0.0;
        this.hours = 0;
        this.minutes = 00;
    }

    /**
     * Constructs a Clock with the given timeslice in seconds.
     *
     * @param timeslice                  the number of seconds the Clock will
     *                                   tick forward by in each tick increment
     * @throws IllegalArgumentException  if the timeslice isn't positive and
     *                                   greater than 1800.0 seconds
     */
    public Clock(double timeslice) {
        if (timeslice <= 0 || timeslice > 1800.0) {
            throw new IllegalArgumentException("Timeslice has to be a positive number and less than or equal to 1800.0");
        }
        this.timesliceInSeconds = timeslice;
        this.seconds = 0.0;
        this.hours = 0;
        this.minutes = 00;
    }

    /**
     * Returns the number of hours simulated by the Clock since its
     * construction. For example, if 8156.6 seconds have passed, getHoursPassed
     * returns 2.
     *
     * @return the number of hours simulated
     */
    public int getHoursPassed() {
        return this.hours;
    }

    /**
     * Returns the number of minutes (excluding hours) simulated by the Clock
     * since its construction. For example, if 8156.6 seconds have passed,
     * getMinutesPassed returns 15.
     *
     * @return the number of minutes simulated
     */
    public int getMinutesPassed() {
        return this.minutes;
    }

    /**
     * Returns the number of seconds (excluding minutes) simulated by the Clock
     * since its construction. For example, if 8156.6 seconds have passed,
     * getSecondsPassed returns 56.6.
     *
     * @return the number of minutes simulated
     */
    public double getSecondsPassed() {
        return this.seconds;
    }

    /**
     * Returns the timeslice that the Clock ticks forward by in seconds.
     *
     * @return the Clock's timeslice
     */
    public double getTimeslice() {
        return this.timesliceInSeconds;
    }

    /**
     * Ticks the Clock forward once, increasing the time passed by the
     * timeslice.
     */
    public void tick() {
        this.seconds += timesliceInSeconds;
        if (this.seconds >= 60) {
            this.minutes += this.seconds / 60;
            this.seconds = this.seconds % 60;
        }
        if (this.minutes >= 60) {
            this.hours += this.minutes / 60;
            this.minutes = this.minutes % 60;
        }
    }

    /**
     * Returns a String representation of the Clock, showing the time passed in
     * military time and hh:mm:ss.s format.
     *
     * @return a String representation of the Clock
     */
    public String toString() {
        String hoursString = String.format("%02d", getHoursPassed());
        String minutesString = String.format("%02d", getMinutesPassed());
        String secondsString = getSecondsPassed() + "";
        String militaryClockString = hoursString + ":" + minutesString + ":" + secondsString;
        return militaryClockString;
    }
}
