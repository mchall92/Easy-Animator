package cs5004.animator.view;

import cs5004.animator.model.Image;
import cs5004.animator.model.Shape;
import cs5004.animator.model.Window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimatorPanel extends JPanel implements ActionListener {
  private Window window;
  private Timer timer;
  private int time;
  private int tempo;

  public AnimatorPanel() {
    this.tempo = 1;
    setBackground(Color.WHITE);
    timer = new Timer((int) 1000 / tempo, this);
  }

  public void setAnimator(Window window) {
    this.window = window;
  }

  /**
   * Change the speed of the animation.
   *
   * @param newTempo newTempo is the new speed for the animation.
   */
  public void setTempo(int newTempo) {
    timer.setDelay((int) 1000/newTempo);
  }

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
    timer.start();
  }

  /**
   * Start the animation.
   */
  public void start() {
    timer.start();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    time += 1;
    repaint();
  }
}
