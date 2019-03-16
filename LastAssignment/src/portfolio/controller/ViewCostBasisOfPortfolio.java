package portfolio.controller;

import java.util.Date;
import java.util.Scanner;

import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerView;

import static portfolio.controller.PortfolioManagerController.helperGetDate;
import static portfolio.controller.PortfolioManagerController.isInputQuit;

/**
 * This class implements the {@link UserOperationsController} interface. The user can view the cost
 * basis of a particular portfolio and a particular date, and also optionally view cost basis for a
 * specific stock in the portfolio.
 */
class ViewCostBasisOfPortfolio implements UserOperationsController {
  private Scanner sc;
  private PortfolioManagerView view;

  /**
   * A package private constructor that takes in the view object containing links to input and
   * output sources as well as a scanner object to parse through the input.
   *
   * @param view the view object containing connections to input and output sources.
   * @param sc   a scanner object to parse through the input.
   */
  ViewCostBasisOfPortfolio(PortfolioManagerView view, Scanner sc) {
    this.view = view;
    this.sc = sc;
  }

  @Override
  public boolean apply(UserOperations userModel) {
    view.putOutput("Enter the portfolio name whose cost basis you wish to find:\n");
    String portfolio = sc.next();
    if (isInputQuit(portfolio)) {
      view.putOutput("Exiting operation.\n");
      System.exit(0);
    }
    Date d = getDate();
    boolean stockNameCheck = false;
    String sTickerSymbol = null;
    while (!stockNameCheck) {
      view.putOutput("Do you wish to calculate cost basis of a particular stock? "
              + "Enter Y or N.\n");
      String ch = sc.next();
      if (isInputQuit(ch)) {
        view.putOutput("Exiting operation.\n");
        System.exit(0);
      }
      if (ch.equalsIgnoreCase("Y")) {
        view.putOutput("Enter stock ticker symbol:\n");
        sTickerSymbol = sc.next();
        if (isInputQuit(sTickerSymbol)) {
          view.putOutput("Exiting operation.\n");
          System.exit(0);
        }
        stockNameCheck = true;
      } else if (ch.equalsIgnoreCase("N")) {
        stockNameCheck = true;
      } else {
        view.putOutput("ERROR: Enter Y or N only.\n");
      }
    }
    performViewCostBasis(userModel, portfolio, sTickerSymbol, d);
    return true;
  }

  /**
   * This function is used to call the respective Amount Invested function in the portfolio from the
   * values fetched by the user.
   */
  private void performViewCostBasis(UserOperations userModel, String portfolio,
                                    String sTickerSymbol, Date d) {
    try {
      view.putOutput("Cost basis of Portfolio is " +
              userModel.amountInvested(portfolio, sTickerSymbol, d));
      if (sTickerSymbol != null) {
        view.putOutput(" for Stock " + sTickerSymbol);
      }
      view.putOutput("\n");
    } catch (Exception e) {
      view.putOutput("ERROR: " + e.getMessage());
    }
  }

  /**
   * This helper function is used to get a valid date object from the user input string in form of
   * YYYY-MM-DD.
   */
  private Date getDate() {
    boolean dateValid = false;
    Date d = null;
    while (!dateValid) {
      view.putOutput("What date do you wish to check cost basis of portfolio on? "
              + "(Format: YYYY-MM-DD)\n");
      String date = sc.next();
      if (isInputQuit(date)) {
        view.putOutput("Exiting operation.\n");
        System.exit(0);
      }
      try {
        d = helperGetDate(date);
        dateValid = true;
      } catch (Exception e) {
        view.putOutput("ERROR: Please re-enter date according to the correct format: "
                + "YYYY-MM-DD.\n");
      }
    }
    return d;
  }
}
