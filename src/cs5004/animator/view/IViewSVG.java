package cs5004.animator.view;

/**
 * This is an interface for SVGView. It extends IViewCommon to achieve
 * interface segregation.
 */
public interface IViewSVG extends IViewCommon {

  /**
   * Set output destination.
   * @param output output is the output destination.
   */
  public void setOutput(String output);

  /**
   * Set tempo of the animation.
   * @param tempo tempo is the speed fo the animation.
   */
  public void setTempo(int tempo);

  /**
   * Writes an SVG file.
   */
  public void writeSVG();
}
