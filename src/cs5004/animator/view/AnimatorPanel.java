package cs5004.animator.view;

import cs5004.animator.model.IModelView;
import cs5004.animator.model.Image;
import cs5004.animator.model.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * This class represents a Panel to play the animation within it.
 */
public class AnimatorPanel extends JPanel {
  private IModelView viewModel;
  private int time;
  private boolean showId;

  /**
   * Initialize the basic features of the panel.
   */
  public AnimatorPanel() {
    setBackground(Color.WHITE);
    time = 0;
    showId = false;
  }

  /**
   * Change time feature.
   * @param time
   */
  public void updateTime(int time) {
    this.time = time;
  }

  /**
   * Control whether the object id shows
   * @param showId the switch of the Id shower.
   */
  public void showId(boolean showId) {
    this.showId = showId;
  }

  /**
   * Set a model to the view.
   * @param viewModel the model
   */
  public void setViewModel(IModelView viewModel) {
    this.viewModel = viewModel;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (Image img : viewModel.getAllShapeByTic(time)) {
      Color color = new Color(img.getColor().getR(), img.getColor().getG(), img.getColor().getB());
      g2d.setColor(color);
      if (img.getShape() == Shape.Oval) {
        g2d.fillOval(
            img.getPosition().getX(),
            img.getPosition().getY(),
            img.getSize().getFirstArg(),
            img.getSize().getSecondArg());
      }
      if (img.getShape() == Shape.Rectangle) {
        g2d.fillRect(
            img.getPosition().getX(),
            img.getPosition().getY(),
            img.getSize().getFirstArg(),
            img.getSize().getSecondArg());
      }
      if (showId) {
        g2d.setColor(Color.BLACK);
        g2d.drawString(img.getId(), img.getPosition().getX() + img.getSize().getFirstArg() / 3,
            img.getPosition().getY() + img.getSize().getSecondArg() * 4 / 5);
      }
    }
  }

  public IModelView getViewModel() {
    return viewModel;
  }

  public int getTime() {
    return time;
  }

  public boolean isShowId() {
    return showId;
  }
}
