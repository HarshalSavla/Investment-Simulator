package portfolio.view;

import java.util.HashMap;

/**
 * This interface manages the operations of the GUI view in the Profile Manager system. Through its
 * functions the controller {@link portfolio.controller.PortfolioManagerControllerGraphic} class can
 * obtain input and output anything as needed.
 */
public interface IPortfolioManagerView {

  /**
   * Creates a pop-up dialog which lets the user enter a name to create a portfolio.
   *
   * @return portfolio name.
   */
  String createPortfolio();

  /**
   * Creates a pop-up dialog which lets the user enter parameters to view the details of a
   * portfolio.
   *
   * @return String array containing portfolio name and date
   */
  String[] expandedViewOfPortfolio();

  /**
   * Creates a pop-up to display an error message.
   *
   * @param message String containing the error message.
   */
  void showErrorMessage(String message);

  /**
   * Creates a pop-up that lets the user enter the parameters required to buy a stock.
   *
   * @return a String array containing the parameters entered by the user.
   */
  String[] buyAStock();

  /**
   * Creates a pop-up that lets the user enter the parameters required to get the cost basis of a
   * portfolio.
   *
   * @return a String array containing the parameters entered by the user.
   */
  String[] getCostBasis();

  /**
   * Creates a pop-up that lets the user enter the parameters required to get the valuation of a
   * portfolio.
   *
   * @return a String array containing the parameters entered by the user.
   */
  String[] getValuation();

  /**
   * Creates a pop-up that lets the user enter the parameters required to create a one-time
   * investment strategy.
   *
   * @return a String array containing the parameters entered by the user.
   */
  String[] oneTimeStrategy();

  /**
   * Creates a pop-up that lets the user enter the parameters required to create a recurring
   * investment strategy.
   *
   * @return a String array containing the parameters entered by the user.
   */
  String[] recurringStrategy();

  /**
   * Creates a pop-up that lets the user enter the parameters required to get historical information
   * of a particular stock.
   *
   * @return a String  containing the Ticker code entered by the user.
   */
  String getStockInfo();

  /**
   * Creates a pop-up that lets the user enter the parameters required to save an investment
   * strategy to a file.
   *
   * @return a String array containing the parameters entered by the user.
   */
  String[] saveStrategyToFile();


  /**
   * Creates a pop-up that lets the user enter the parameters required to retrieve a strategy from a
   * file.
   *
   * @return a String array containing the parameters entered by the user.
   */
  String[] retrieveStrategyFromFile();

  /**
   * Creates a pop-up that lets the user enter the parameters required to save an existing portfolio
   * to a file.
   *
   * @return a String array containing the parameters entered by the user.
   */
  String[] savePortfolio();

  /**
   * Creates a pop-up dialog that lets the user enter the parameters required to retrieve a
   * portfolio from a file.
   *
   * @return a String array containing the parameters entered by the user.
   */
  String[] retrievePortfolio();

  /**
   * Creates a pop-up that lets the user enter the parameters required to generate a graph of
   * performance of a particular portfolio.
   */
  String[] plotGraph();

  /**
   * This method displays a string in the main output window of the GUI.
   *
   * @param s is an output string of any function performed by the model and is sent to the view by
   *          the controller.
   */
  void displayResult(String s);


  /**
   * Prints a  message to give a visual cue to that user that an operation is being processed.
   */
  public void loadingMessage();

  /**
   * Clears the main output window of the GUI.
   */
  void clearScreen();

  /**
   * A helper function for creating an unequal weighted investment . This method recurringly creates
   * a pop-up to get the input parameters from user.
   *
   * @return a String array containing the parameters entered by the user.
   */
  String[] helperInvestment();

  /**
   * Prompt to ask the user whether to quit the application or not.
   *
   * @return Option selected by the user as an integer.
   */
  public int quit();

  /**
   * A helper function for creating an unequal weighted investment . This method recurringly creates
   * a pop-up to get the input parameters from user.
   *
   * @param track track the weights of investment amount by user.
   * @return a HashMap of Ticker code and investment weight entered by the user.
   */
  HashMap<String, Double> helperRecurringInvestment(double track);

  /**
   * Creates a pop-up to display an error message.
   */
  public void errorMessage();
}
