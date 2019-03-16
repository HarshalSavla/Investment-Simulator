package portfolio.view;

/**
 * This interface manages the operations of the view in the Profile Manager system. Through its
 * functions the controller {@link portfolio.controller.PortfolioManagerController} class can obtain
 * input and output anything as needed.
 */
public interface PortfolioManagerView {
  /**
   * This function can be used to get input from the user.
   *
   * @return a valid input stream from the source to the caller.
   */
  Readable getInput();

  /**
   * This function  can be used to provide output to the user in String format.
   *
   * @param message a string message that is displayed to the user.
   */
  void putOutput(String message);
}
