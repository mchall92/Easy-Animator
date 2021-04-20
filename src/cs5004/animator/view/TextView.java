package cs5004.animator.view;

import cs5004.animator.model.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** This view can output a text format output. */
public class TextView implements IViewText {
  private String out;
  private Window window;

  @Override
  public void setModel(Window window) {
    this.window = window;
  }

  /**
   * Set the output for TextView.
   * @param out output for TextView.
   */
  @Override
  public void setOutput(String out) {
    this.out = out;
  }

  @Override
  public void writeTxt() {
    if (out.equals("system")) {
      writeSystem();
    } else {
      writeFile();
    }
  }

  /** System.out version of makeVisible */
  private void writeSystem() {
    System.out.print(window.toString());
  }

  /** File version of makeVisible */
  private void writeFile() {
    File file = new File(out);
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(file);
      fileWriter.write(window.toString());
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
