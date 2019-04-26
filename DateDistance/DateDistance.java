public class DateDistance {

    /**
     * Returns whether the given year is a leap year.
     */
    public static boolean isLeapYear ( long year ) {
        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        } else if (year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the number of days in the given month in the given year.
     */
     public static int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
     public static int[] leapDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

     public static long daysInMonth ( long year, long month ) {
         if (isLeapYear(year)){
             return leapDays[(int)month - 1];
         } else {
             return days[(int)month - 1];
         }
     }

    /**
     * Returns whether the given date is a valid date.
     */
    public static boolean isValidDate ( long month, long day, long year ) {
        return !(month > 12 || month < 1 || day <= 0 || year <= 0 || day > daysInMonth(year, month));
    }

    /**
     * Returns the number of days between the two provided dates, regardless of
     * the order they were provided.
     */
    public static long daysBetween ( long month0, long day0, long year0, long month1, long day1, long year1 ) {
        if (isValidDate(month0, day0, year0) && isValidDate(month1, day1, year1)) {
            int daysBetween = 0;
            long d0 = day0;
            long d1 = day1;
            long m0 = month0;
            long m1 = month1;
            long y0 = year0;
            long y1 = year1;
            if (year1 < year0) {
                d0 = day1;
                d1 = day0;
                m0 = month1;
                m1 = month0;
                y0 = year1;
                y1 = year0;
            }
            while (d0 != d1) {
                daysBetween++;
                d0++;
                if (d0 > daysInMonth(y0, m0)) {
                    m0++;
                    d0 = 1;
                    if (m0 > 12) {
                        m0 = 1;
                    }
                }
            }
            while (m0 != m1) {
                daysBetween += daysInMonth(y0, m0);
                m0++;
                if (m0 > 12) {
                    y0++;
                    m0 = 1;
                }
            }
            while (y0 < y1) {
                y0++;
                if (isLeapYear(y0)) {
                    daysBetween += 366;
                } else {
                    daysBetween += 365;
                }
            }
            return daysBetween;
        } else {
            throw new IllegalArgumentException("Invalid Date");
        }
    }


    /**
     * Returns the day of the week the given date occured on as a String.
     */
    public static String dayOfTheWeek ( long month, long day, long year ) {
        String[] daysInAWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        long numberOfDaysBetween = daysBetween(1, 1, 0001, month, day, year);
        long dayInteger = numberOfDaysBetween % 7;
        String dayString = daysInAWeek[(int)dayInteger];
        return dayString;
    }

    /**
     * Returns the long form of the given date as a String.
     */
    public static String longformDate ( long month, long day, long year ) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String dayOfTheWeekString = dayOfTheWeek(month, day, year);
        String monthOfTheYear = months[(int)month - 1];
        return dayOfTheWeekString + ", " + monthOfTheYear + " " + day + ", " + year;
    }

    public static void main (String[] args) {
        if (args.length != 6) {
            System.out.println("Usage instructions: java DateDistance <month0> <day0> <year0> <month1> <day1> <year1>");
            return;
        }
        try {
            long month0 = Long.parseLong(args[0]);
            long day0 = Long.parseLong(args[1]);
            long year0 = Long.parseLong(args[2]);
            long month1 = Long.parseLong(args[3]);
            long day1 = Long.parseLong(args[4]);
            long year1 = Long.parseLong(args[5]);
            if (isValidDate(month0, day0, year0) == false || isValidDate(month1, day1, year1) == false) {
                System.out.println("Invalid date entered!");
            } else {
                long daysBetweenNumber = daysBetween(month0, day0, year0, month1, day1, year1);
                String dateInWords = longformDate(month0, day0, year0);
                String date1InWords = longformDate(month1, day1, year1);
                System.out.println("There are " + daysBetweenNumber + " days between " + dateInWords + " and " + date1InWords + ".");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("The dates entered are not valid. The dates have to entered as numbers.");
        }
    }
}
