package cs5004.animator.view;

import cs5004.animator.model.IModelView;
import cs5004.animator.model.Image;
import cs5004.animator.model.Shape;
import cs5004.animator.model.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class AnimatorPanel extends JPanel {
  private IModelView viewModel;
  private int time;
  private boolean showId;

  public AnimatorPanel() {
    setBackground(Color.WHITE);
    time = 0;
    showId = false;
  }

  public void updateTime(int time) {
    this.time = time;
  }

  public void showId(boolean showId) {
    this.showId = showId;
  }

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
}
