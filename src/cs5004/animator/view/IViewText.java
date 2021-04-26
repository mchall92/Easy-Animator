package cs5004.animator.view;

/**
 * This is an interface for TextView. It extends IViewCommon to achieve
 * interface segregation.
 */
public interface IViewText extends IViewCommon {
  /**
   * Write an text file if file name is provided, otherwise, print out in System.
   */
  public void writeTxt();

  public void setOutput(String out);
}
