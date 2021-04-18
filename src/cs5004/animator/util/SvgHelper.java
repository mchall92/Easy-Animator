package cs5004.animator.util;

/**
 * This class stores helper methods to help generate a svg log.
 */
public class SvgHelper {
    /**
     * Generate an assignment format string.
     *
     * @param src    right element of the assignment expression
     * @param target left element of the assignment expression
     * @return assignment expression
     */
    public static String assignment(String src, String target) {
        return target + "=" + "\"" + src + "\"";
    }

    /**
     * Generate a svg <animate\n> tag.
     *
     * @param attributeName The changed attribute
     * @param fromStatus    The status of the attribute before changing
     * @param toStatus      The status of the attribute after changing
     * @param begin         The start time
     * @param end           The end time
     * @return
     */
    public static String animationSvgGenerator(String attributeName, int fromStatus, int toStatus,
                                               double begin, double end) {
        return String.format("<animate attributeName=\"%s\" from=\"%d\"" +
                        " to=\"%d\" begin=\"%.1fs\" " +
                        "end=\"%.1fs\" dur=\"%.1fs\" fill=\"freeze\"/>\n",
                attributeName, fromStatus, toStatus, begin, end, end - begin);
    }
}
