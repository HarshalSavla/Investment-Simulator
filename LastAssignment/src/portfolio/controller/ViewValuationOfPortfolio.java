package portfolio.controller;

import java.util.Date;
import java.util.Scanner;

import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerView;

import static portfolio.controller.PortfolioManagerController.helperGetDate;
import static portfolio.controller.PortfolioManagerController.isInputQuit;

/**
 * This class implements the {@link UserOperationsController} interface. The user can view the
 * valuation of a particular portfolio and a particular date, and also optionally view valuation for
 * a specific stock in the portfolio.
 */
class ViewValuationOfPortfolio implements UserOperationsController {
  private Scanner sc;
  private PortfolioManagerView view;

  /**
   * A package private constructor that takes in the view object containing links to input and
   * output sources as well as a scanner object to parse through the input.
   *
   * @param view the view object containing connections to input and output sources.
   * @param sc   a scanner object to parse through the input.
   */
  ViewValuationOfPortfolio(PortfolioManagerView view, Scanner sc) {
    this.view = view;
    this.sc = sc;
  }

  @Override
  public boolean apply(UserOperations userModel) {
    String portfolio = getPortfolio();
    Date d = getDate();

    boolean stockNameCheck = false;
    String sTickerSymbol = null;
    while (!stockNameCheck) {
      view.putOutput("Do you wish to view valuation of a particular stock? Enter Y or N.\n");
      String ch = sc.next();
      if (isInputQuit(ch)) {
        view.putOutput("Exiting operation.");
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
    try {
      view.putOutput("Amount invested in Portfolio is " +
              userModel.evaluateNet(portfolio, sTickerSymbol, d) + "\n");
    } catch (Exception e) {
      view.putOutput("ERROR: " + e.getMessage());
    }
    return true;
  }

  /**
   * This is a helper function used to get Date from the user.
   */
  private Date getDate() {
    boolean dateValid = false;
    Date d = null;
    while (!dateValid) {
      view.putOutput("What date do you wish to view this valuation for? (Format: YYYY-MM-DD)\n");
      String date = sc.next();
      if (isInputQuit(date)) {
        view.putOutput("Exiting operation.\n");
        System.exit(0);
      }
      try {
        d = helperGetDate(date);
        if (d.after(new Date())) {
          view.putOutput("This date is in the future. There are no stock values for this date!\n");
        } else {
          dateValid = true;
        }
      } catch (Exception e) {
        view.putOutput("ERROR: Please re-enter date according to the correct format: "
                + "YYYY-MM-DD.\n");
      }
    }
    return d;
  }

  /**
   * This is helper function used to get portfolio name from the user.
   */
  private String getPortfolio() {
    view.putOutput("Enter the portfolio name you want to find valuation of:\n");
    String portfolio = sc.next();
    if (isInputQuit(portfolio)) {
      view.putOutput("Exiting operation.\n");
      System.exit(0);
    }
    return portfolio;
  }
}
