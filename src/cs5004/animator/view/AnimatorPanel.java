package cs5004.animator.view;

import cs5004.animator.model.Image;
import cs5004.animator.model.Shape;
import cs5004.animator.model.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class AnimatorPanel extends JPanel {
  private Window window;
  private int time;


  public AnimatorPanel() {
    setBackground(Color.WHITE);
    time = 0;
  }

  public void setAnimator(Window window) {
    this.window = window;
  }

  public void updateTime(int time) {
    this.time = time;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    for (Image img : window.getAllShapeByTic(time)) {
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
    }
  }
}
