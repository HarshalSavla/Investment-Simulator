package portfolio.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerView;

/**
 * This class implements the {@link IPortfolioManagerController} interface that handles the
 * operations of the controller in creating and viewing portfolios, buying stocks from the market,
 * viewing information about a stock and performing cost basis and valuation analyses on the
 * portfolios.
 */
public class PortfolioManagerController implements IPortfolioManagerController {
  private Scanner sc;
  private HashMap<Integer, UserOperationsController> operations;
  private UserOperations userModel;
  private PortfolioManagerView userView;

  /**
   * The main controller constructor that wires our model and view to itself and initializes a map
   * of operations that the user can perform along with the object of type {@link
   * UserOperationsController} so as to be able to orchestrate multiple operations in sequence
   * during the running of the program.
   *
   * @param userModel the model passed to the controller containing operations to actually create
   *                  portfolios and add stocks, perform analyses, etc.
   * @param userView  the view passed to the controller containing functions to access the input and
   *                  output sources.
   */
  public PortfolioManagerController(UserOperations userModel, PortfolioManagerView userView) {
    if (userModel == null || userView == null) {
      throw new IllegalArgumentException("Model and view objects can not be null.");
    }
    this.userView = userView;
    this.userModel = userModel;
    this.sc = new Scanner(userView.getInput());
    this.operations = new HashMap<>();
    operations.put(1, new CreatePortfolio(userView, sc));
    operations.put(2, new ViewAllPortfolios(userView, sc));
    operations.put(3, new ViewAPortfolio(userView, sc));
    operations.put(4, new ViewValuationOfPortfolio(userView, sc));
    operations.put(5, new ViewCostBasisOfPortfolio(userView, sc));
    operations.put(6, new BuyAStock(userView, sc));
    operations.put(7, new NonRecurringInvestment(userView, sc));
    operations.put(8, new RecurringInvestment(userView, sc));
    operations.put(9, new SavePortfolio(userView, sc));
    operations.put(10, new SaveStrategy(userView, sc));
    operations.put(11, new GetStockData(userView, sc));
  }

  /**
   * A helper class to check if the user wants to quit the application or not.
   *
   * @param input a string to be checked against.
   * @return true if input is equal to Q, q or quit.
   */
  static boolean isInputQuit(String input) {
    return input.equalsIgnoreCase("Q") || input.equalsIgnoreCase("Quit");
  }

  /**
   * A helper class to check for validity of the date entered by a user and create a valid Date
   * object from it. We are not accounting for time in our portfolio operations so we assume the
   * time to be end of day of the stock market.
   *
   * @param date which is the string version of the date of the format YYYY-MM-DD.
   * @return a valid Date object with the correctly set date.
   */
  static Date helperGetDate(String date) {
    Calendar yyyyDdMm = Calendar.getInstance();
    String[] dates = date.split("-");
    yyyyDdMm.set(Calendar.YEAR, Integer.parseInt(dates[0]));
    yyyyDdMm.set(Calendar.MONTH, Integer.parseInt(dates[1]) - 1);
    yyyyDdMm.set(Calendar.DATE, Integer.parseInt(dates[2]));
    yyyyDdMm.set(Calendar.HOUR_OF_DAY, 15);
    yyyyDdMm.set(Calendar.MINUTE, 59);
    yyyyDdMm.set(Calendar.SECOND, 59);
    yyyyDdMm.set(Calendar.MILLISECOND, 0);
    return yyyyDdMm.getTime();
  }

  @Override
  public void startInvesting() {
    while (true) {
      int choice;
      String choiceString;
      userView.putOutput("\nWelcome to the Investing Manager. Please select one of the following: "
              + "options.\n" +
              "1. Create/Load a Portfolio.\n" +
              "2. View all Portfolios created.\n" +
              "3. Expanded view of a Portfolio.\n" +
              "4. View valuation of Portfolio.\n" +
              "5. View cost basis of Portfolio.\n" +
              "6. Buy a Stock.\n" +
              "7. Buy a set of stocks as a one time investment.\n" +
              "8. Create/Load a Strategy.\n" +
              "9. Save a portfolio to a file.\n" +
              "10. Save a strategy to a file.\n" +
              "11. Get information about Stock from the Stock Market.\n" +
              "Q: To quit at any point.\n");

      choiceString = sc.next();

      if (isInputQuit(choiceString)) {
        break;
      }
      try {
        choice = Integer.parseInt(choiceString);
      } catch (Exception e) {
        userView.putOutput("ERROR: Please select a number from the options provided.\n");
        continue;
      }
      if (operations.containsKey(choice)) {
        UserOperationsController operation = operations.get(choice);
        if (!(operation.apply(userModel))) {
          break;
        }
      } else {
        userView.putOutput("ERROR: Please select a valid option number.\n");
      }
    }
  }
}
