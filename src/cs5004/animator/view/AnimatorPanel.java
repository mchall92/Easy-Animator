package cs5004.animator.view;

import cs5004.animator.model.Image;
import cs5004.animator.model.Shape;
import cs5004.animator.model.Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
  private ControlBarPanel controlBarPanel;
  private boolean isLoop;

  public AnimatorPanel() {
    this.tempo = 1;
    this.isLoop = false;
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

  /**
   * Start the animation.
   */
  public void start() {
    controlBarPanel.playingView(isLoop);
    timer.start();
  }

  public void pause() {
    controlBarPanel.pausingView(isLoop);
    timer.stop();
  }

  /**
   * Set controlBarPanel for animatorPanel to display proper icons.
   * @param controlBarPanel controlBarPanel displays icons for control.
   */
  public void setControlBar(ControlBarPanel controlBarPanel) {
    this.controlBarPanel = controlBarPanel;
  }

  /**
   * Return true if timer/animation is playing.
   * @return true if timer/animation is playing.
   */
  public boolean isPlaying() {
    return timer.isRunning();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    time += 1;
    if (time > window.getEndTime()) {
      controlBarPanel.pausingView(isLoop);
      timer.stop();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException interruptedException) {
        interruptedException.printStackTrace();
      }
    }
    repaint();
  }
}
