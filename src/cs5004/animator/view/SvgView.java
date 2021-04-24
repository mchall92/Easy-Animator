package cs5004.animator.view;

import cs5004.animator.model.IModelView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/** This view can output a svg format output. */
public class SvgView implements IViewSVG {
  private IModelView viewModel;
  private int tempo;
  private String out;

  /**
   * Set an animator model to this view.
   *
   * @param viewModel the animator model (view only)
   */
  @Override
  public void setViewModel(IModelView viewModel) {
    this.viewModel = viewModel;
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
      fileWriter.write(viewModel.toSvgString(tempo));
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
