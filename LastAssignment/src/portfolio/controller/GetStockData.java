package portfolio.controller;

import java.util.Scanner;

import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerView;

import static portfolio.controller.PortfolioManagerController.isInputQuit;

/**
 * This class implements the {@link UserOperationsController} interface. The user can get data about
 * a stock from the data source before deciding to buy it.
 */
class GetStockData implements UserOperationsController {
  private Scanner sc;
  private PortfolioManagerView view;

  /**
   * A package private constructor that takes in the view object containing links to input and
   * output sources as well as a scanner object to parse through the input.
   *
   * @param view the view object containing connections to input and output sources.
   * @param sc   a scanner object to parse through the input.
   */
  GetStockData(PortfolioManagerView view, Scanner sc) {
    this.view = view;
    this.sc = sc;
  }

  @Override
  public boolean apply(UserOperations userModel) {
    view.putOutput("Enter the stock ticker you want information about:\n");
    String sTickerName = sc.next();
    if (isInputQuit(sTickerName)) {
      view.putOutput("Exiting operation.\n");
      System.exit(0);
    }
    try {
      view.putOutput(userModel.search(sTickerName));
    } catch (Exception e) {
      view.putOutput(e.getMessage());
    }
    return true;
  }
}
