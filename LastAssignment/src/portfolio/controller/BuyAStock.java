package portfolio.controller;

import java.util.Date;
import java.util.Scanner;

import portfolio.model.BuyType;
import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerView;

import static portfolio.controller.PortfolioManagerController.helperGetDate;
import static portfolio.controller.PortfolioManagerController.isInputQuit;

/**
 * This class implements the {@link UserOperationsController} interface. The user can buy a stock
 * and add it to their portfolio. The user decides whether they want to buy a specific no. of shares
 * of the stock or invest a sum of money towards the stock. Each time a transaction occurs, a
 * commission is charged.
 */
class BuyAStock implements UserOperationsController {
  private Scanner sc;
  private PortfolioManagerView view;

  /**
   * A package private constructor that takes in the view object containing links to input and
   * output sources as well as a scanner object to parse through the input.
   *
   * @param view the view object containing connections to input and output sources.
   * @param sc   a scanner object to parse through the input.
   */
  BuyAStock(PortfolioManagerView view, Scanner sc) {
    this.view = view;
    this.sc = sc;
  }

  @Override
  public boolean apply(UserOperations userModel) {
    String portfolio = getPortfolioName();
    String sTickerSymbol = getTickerSymbol();
    String choice = getChoiceForStockBuyType(sTickerSymbol);
    double value = 0;
    if (choice.equals("1")) {
      value = getStockCount(sTickerSymbol, value);
    } else if (choice.equals("2")) {
      value = getStockAmount(sTickerSymbol, value);
    }
    Date d = getDate();
    if (d == null) {
      return false;
    }
    String time = getTimeOfTransaction();
    if (time.isEmpty()) {
      return true;
    }
    double commission = getCommission();
    executeBuyOperation(userModel, portfolio, sTickerSymbol, d, value, commission, choice);
    return true;
  }

  /**
   * This is the operation used to execute a buy operation for which the required model function is
   * executed.
   */
  private void executeBuyOperation(UserOperations userModel, String portfolio,
                                   String sTickerSymbol, Date d, double value,
                                   double commission, String choice) {
    try {
      view.putOutput("Please wait while we add the stock to the portfolio...\n");
      userModel.buyStock(portfolio, sTickerSymbol, d, value, commission,
              choice.equals("1") ? BuyType.COUNT : BuyType.AMOUNT);
      view.putOutput("\nStock " + sTickerSymbol + " successfully added to Portfolio "
              + portfolio + "\n");

    } catch (Exception e) {
      view.putOutput("ERROR: " + e.getMessage());
    }
  }

  /**
   * This helper function is used to fetch a ticker symbol from the user.
   */
  private String getChoiceForStockBuyType(String sTickerSymbol) {
    view.putOutput("Pick an option from the following:\n1. Enter number of stocks to be bought of "
            + sTickerSymbol + "\n2. Enter amount to be invested in buying stocks of "
            + sTickerSymbol + "\n");
    boolean validChoice = false;
    String choice = "";
    if (isInputQuit(choice)) {
      view.putOutput("Exiting operation.\n");
      System.exit(0);
    }
    while (!validChoice) {
      choice = sc.next();
      if (choice.equals("1") || choice.equals("2")) {
        validChoice = true;
      } else {
        view.putOutput("ERROR: You need to enter either 1 or 2.\n");
      }
    }
    return choice;
  }

  /**
   * This helper function is used to fetch the commission value from the view.
   */
  private double getCommission() {
    boolean commissionValid = false;
    double commission = 0;
    while (!commissionValid) {
      view.putOutput("What commission is being charged to buy this stock? (In dollars)\n");
      String commissionString = sc.next();
      if (isInputQuit(commissionString)) {
        view.putOutput("Exiting operation.\n");
        System.exit(0);
      }
      try {
        commission = Double.parseDouble(commissionString);
        if (commission < 0) {
          view.putOutput("Commission cannot be negative.\n");
        } else {
          commissionValid = true;
        }
      } catch (Exception e) {
        view.putOutput("ERROR: Please enter a valid commission value.\n");
      }
    }
    return commission;
  }

  /**
   * This helper function is used to fetch the time in HH:MM:SS format from the user.
   */
  private String getTimeOfTransaction() {
    view.putOutput("What time do you want to buy this stock at? (Format: HH:MM:SS)\n");
    String time = sc.next();
    if (isInputQuit(time)) {
      view.putOutput("Exiting operation.\n");
      System.exit(0);
    }
    if (!checkValid(time)) {
      view.putOutput("The markets are closed at this time. You cannot buy stocks.\n");
      return "";
    }
    return time;
  }

  /**
   * This helper function is used to fetch the date in YYYY-MM-DD format.
   */
  private Date getDate() {
    boolean dateValid = false;
    Date d = null;
    while (!dateValid) {
      view.putOutput("What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n");
      String date = sc.next();
      try {
        if (isInputQuit(date)) {
          view.putOutput("Exiting operation.\n");
          System.exit(0);
        }
        d = helperGetDate(date);
        dateValid = true;
      } catch (Exception e) {
        view.putOutput("ERROR: Please re-enter date according to the correct format: "
                + "YYYY-MM-DD.\n");
      }
    }
    return d;
  }

  /**
   * This function is used to fetch the amount to be invested from the view.
   */
  private double getStockAmount(String sTickerSymbol, double value) {
    boolean validNumber = false;
    while (!validNumber) {
      view.putOutput("Enter the amount to be invested into stocks of type " + sTickerSymbol
              + ":\n");
      String amountString = sc.next();
      if (isInputQuit(amountString)) {
        view.putOutput("Exiting operation.\n");
        System.exit(0);
      }
      try {
        value = Double.parseDouble(amountString);
        if (value > 0) {
          validNumber = true;
        } else {
          view.putOutput("ERROR: Stock is worth a positive amount of money.\n");
        }
      } catch (Exception e) {
        view.putOutput("ERROR: Please enter a valid amount.\n");
      }
    }
    return value;
  }

  /**
   * This function is used to get the count of the stock to be bought.
   */
  private double getStockCount(String sTickerSymbol, double value) {
    boolean validNumber = false;
    while (!validNumber) {
      view.putOutput("Enter the number of stocks of type " + sTickerSymbol + ":\n");
      String countString = sc.next();
      if (isInputQuit(countString)) {
        view.putOutput("Exiting operation.\n");
        System.exit(0);
      }
      try {
        value = Double.parseDouble(countString);
        if (value > 0) {
          validNumber = true;
        } else {
          view.putOutput("ERROR: You need to buy at least one stock.\n");
        }
      } catch (Exception e) {
        view.putOutput("ERROR: Please enter a valid number of stocks.\n");
      }
    }
    return value;
  }

  /**
   * This function is used to get the Ticker Symbol from the user.
   */
  private String getTickerSymbol() {
    view.putOutput("Enter the ticker symbol of the stock to be bought:\n");
    String sTickerSymbol = sc.next();
    if (isInputQuit(sTickerSymbol)) {
      view.putOutput("Exiting operation.\n");
      System.exit(0);
    }
    return sTickerSymbol;
  }

  /**
   * This function is used get portfolio name from the user.
   */
  private String getPortfolioName() {
    view.putOutput("Enter the portfolio name you want to add the stock to:\n");
    String portfolio = sc.next();
    if (isInputQuit(portfolio)) {
      view.putOutput("Exiting operation.");
      return null;
    }
    return portfolio;
  }

  /**
   * A private helper that checks if the time entered by the user is in the valid format and only
   * between the open hours of the stock market. (9AM to 4PM)
   *
   * @param time A string of the format HH:MM:SS.
   * @return true if the hours are between 09 and 16, minutes range from 0 to 59 and seconds from 0
   *         to 59.
   */
  private boolean checkValid(String time) {
    String[] hms = time.split(":");
    try {
      int h = Integer.parseInt(hms[0]);
      int m = Integer.parseInt(hms[1]);
      int s = Integer.parseInt(hms[2]);
      return (h > 8 && h < 16 && m >= 0 && m < 60 && s >= 0 && s < 60);
    } catch (Exception e) {
      return false;
    }
  }
}
