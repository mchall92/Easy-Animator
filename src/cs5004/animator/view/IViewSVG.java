package cs5004.animator.view;

/**
 * This is an interface for SVGView. It extends IViewCommon to achieve
 * interface segregation.
 */
public interface IViewSVG extends IViewCommon {

  /**
   * Writes an SVG file.
   */
  public void writeSVG();
}
