package cs5004.animator.view;

import cs5004.animator.model.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This view can output a svg format output.
 */
public class SvgView implements IView {
    private Window win;
    private int tempo;
    private String out;

    /**
     * Constructor of this view.
     *
     * @param out   String representation of the output target filepath
     * @param tempo The speed of this view
     */
    public SvgView(String out, int tempo) {
        this.tempo = tempo;
        this.out = out;
    }

    @Override
    public void makeVisible() {
        File file = new File(out);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(win.toSvgString(tempo));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAnimator(Window window) {
        this.win = window;
    }
}
