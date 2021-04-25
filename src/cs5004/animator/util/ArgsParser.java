package cs5004.animator.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This class represents an ArgParser which parses the input from the user in command line
 * interface.
 */
public class ArgsParser {

  /**
   * Parse the input from user.
   *
   * @param args input from user
   * @return A hashmap which contains information a view needs
   */
  public static HashMap<String, String> parse(String[] args) {
    HashMap<String, String> map = new HashMap<>();
    String fileName = null;
    String view = null;
    String speed = null;
    String out = null;

    // get args for in, out, view and speed
    for (int i = 0; i < args.length; i += 2) {
      String tmp = args[i];
      if (tmp.equals("-in")) {
        fileName = args[i + 1];
      } else if (tmp.equals("-view")) {
        view = args[i + 1];
      } else if (tmp.equals("-out")) {
        out = args[i + 1];
      } else if (tmp.equals("-speed")) {
        speed = args[i + 1];
      } else {
        throw new IllegalArgumentException();
      }
    }

    // verify fileName
    if (fileName == null || fileName.equals("")) {
      throw new IllegalArgumentException();
    }
    map.put("fileName", fileName);

    // verify view
    HashSet<String> legalViews = new HashSet<>();
    legalViews.addAll(Arrays.asList("svg", "visual", "text", "playback"));

    if (legalViews.contains(view)) {
      map.put("view", view);
    } else {
      throw new IllegalArgumentException("View not legal");
    }

    // verify speed
    if (speed == null) {
      if (view.equals("svg") || view.equals("visual") || view.equals("playback")) {
        speed = "1";
      }
    }
    int parseSpeed = Integer.parseInt(speed);
    if (parseSpeed <= 0) {
      throw new IllegalArgumentException("Speed not legal");
    }
    map.put("speed", speed);

    // verify out
    // view will only be either visual, text, playback or svg
    if (view.equals("visual") || view.equals("playback")) {
      if (out == null) {
        map.put("out", null);
      } else {
        throw new IllegalArgumentException();
      }
    } else if (view.equals("svg")) {
      // for svg, output has to be specified and should be a valid file that ends with .svg
      if (out != null && out.length() > 4 && out.endsWith(".svg")) {
        map.put("out", out);
      } else {
        throw new IllegalArgumentException();
      }
    } else {
      // for text
      if (out == null) {
        map.put("out", "system");
      } else if (out.length() == 0) {
        throw new IllegalArgumentException();
      } else {
        map.put("out", out);
      }
    }
    return map;
  }
}
