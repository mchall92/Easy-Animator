package cs5004.animator.view;

import cs5004.animator.model.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** This view can output a svg format output. */
public class SvgView implements IViewSVG {
  private Window window;
  private int tempo;
  private String out;

  @Override
  public void setModel(Window window) {
    this.window = window;
  }

  @Override
  public void setOutput(String out) {
    this.out = out;
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public void writeSVG() {
    File file = new File(out);
    try {
      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(window.toSvgString(tempo));
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
