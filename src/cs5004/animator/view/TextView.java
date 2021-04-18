package cs5004.animator.view;

import cs5004.animator.model.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This view can output a text format output.
 */
public class TextView implements IView {
    private String out;
    private Window win;

    /**
     * Constructor of this view.
     *
     * @param out String representation of the output target filepath
     */
    public TextView(String out) {
        this.out = out;
    }

    /**
     * System.out version of makeVisible
     */
    private void makeVisibleSystem() {
        System.out.print(win.toString());
    }

    /**
     * File version of makeVisible
     */
    private void makeVisibleFile() {
        File file = new File(out);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(win.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeVisible() {
        if (out.equals("system")) {
            makeVisibleSystem();
        } else {
            makeVisibleFile();
        }
    }

    @Override
    public void setAnimator(Window window) {
        this.win = window;
    }
}
