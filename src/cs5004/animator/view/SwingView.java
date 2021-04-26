package cs5004.animator.view;

import cs5004.animator.model.IModelView;

import cs5004.animator.view.viewpanels.AnimatorPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 * This class represents a view to show the animation visually.
 */
public class SwingView extends JFrame implements IViewVisual {

  private AnimatorPanel animatorPanel;


  /**
   * This method constructs a new SwingView.
   */
  public SwingView() {
    super();
    this.setSize(720, 450);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    // add Animator Panel
    animatorPanel = new AnimatorPanel();
    animatorPanel.setPreferredSize(new Dimension(720, 450));
    this.add(animatorPanel, BorderLayout.CENTER);

    // add Scroll bars for animator panel
    JScrollPane js = new JScrollPane(
        animatorPanel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    js.setPreferredSize(new Dimension(720, 450));
    this.add(js);
  }

  /**
   * Set the frame name to file name.
   *
   * @param fileName is the name of the input file.
   */
  @Override
  public void setFileName(String fileName) {
    this.setTitle(fileName);
  }

  /** Make the view visible. Called after the view is constructed */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void repaint(int time) {
    animatorPanel.updateTime(time);
    animatorPanel.repaint();
  }

  /**
   * Set an animator model to this view.
   *
   * @param viewModel the animator model (view only)
   */
  @Override
  public void setViewModel(IModelView viewModel) {
    animatorPanel.setViewModel(viewModel);
  }
}
