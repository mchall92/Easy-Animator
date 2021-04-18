package cs5004.animator.view;

import cs5004.animator.model.Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 * This class represents a view to show the animation visually.
 */
public class SwingView extends JFrame implements IView {

  private AnimatorPanel animatorPanel;

  /**
   * This method constructs a new SwingView.
   * @param title Title of the frame
   * @param width width of the view
   * @param height height of the view
   * @param tempo speed of the view
   */
  public SwingView(String title, int width, int height, int tempo) {
    super();
    this.setTitle(title);
    this.setSize(width, height);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    animatorPanel = new AnimatorPanel(width, height, tempo);
    animatorPanel.setPreferredSize(new Dimension(width, height));
    this.add(animatorPanel, BorderLayout.CENTER);
    JScrollPane js =
        new JScrollPane(
            animatorPanel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    js.setPreferredSize(new Dimension(720, 450));
    js.getVerticalScrollBar().setBackground(Color.RED);
    js.getHorizontalScrollBar().setBackground(Color.RED);

    this.add(js);

    this.pack();
  }

  /** Make the view visible. Called after the view is constructed */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setAnimator(Window window) {
    animatorPanel.setAnimator(window);
  }
}
