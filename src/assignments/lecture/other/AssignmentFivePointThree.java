package assignments.lecture.other;

public class AssignmentFivePointThree {
    /**
     * Denna klass innehåller två orelaterade metoder.
     */
        static int absoluteValue(int v) {
            return v < 0 ? -v : v;
        }

        static boolean isLeapYear(int year) {
            return year % 4 == 0;
        }
}
