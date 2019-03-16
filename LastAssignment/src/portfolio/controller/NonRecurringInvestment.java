package portfolio.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerView;

import static portfolio.controller.PortfolioManagerController.helperGetDate;
import static portfolio.controller.PortfolioManagerController.isInputQuit;

/**
 * This class implements the {@link UserOperationsController} interface. The user can specify the
 * amount they want to invest in a bunch of stocks divided either equally amongst the stocks or in a
 * ratio specified by the user. The user decides whether they want to buy a specific no. of shares
 * of the stock or invest a sum of money towards the stock.
 */
class NonRecurringInvestment implements UserOperationsController {
  private Scanner sc;
  private PortfolioManagerView view;

  /**
   * A package private constructor that takes in the view object containing links to input and
   * output sources as well as a scanner object to parse through the input.
   *
   * @param view the view object containing connections to input and output sources.
   * @param sc   a scanner object to parse through the input.
   */
  NonRecurringInvestment(PortfolioManagerView view, Scanner sc) {
    this.view = view;
    this.sc = sc;
  }

  @Override
  public boolean apply(UserOperations userModel) {
    String portfolio = getPortfolioName();
    Date date = getDate();
    double amount = getStockAmount();
    double commission = getCommission();
    String choice = getChoice();
    HashMap<String, Double> stockWeights = new HashMap<>();
    if (choice.equalsIgnoreCase("N")) {
      double percentSum = 0;
      do {
        view.putOutput("Enter a ticker symbol:\n");
        String sTicker = sc.next();
        double perc = getPercentage(sTicker, percentSum);
        stockWeights.put(sTicker, perc);
        percentSum += perc;
        view.putOutput("The current split is:\n");
        for (String stockName : stockWeights.keySet()) {
          view.putOutput(stockName + ": " + stockWeights.get(stockName) + "\n");
        }
      }
      while (continueOrNot(percentSum));
    }
    try {
      view.putOutput("Please wait while we add the stocks to the portfolio...\n");
      if (choice.equalsIgnoreCase("Y")) {
        userModel.weightedInvestEqual(portfolio, date, amount, commission);
      } else {
        userModel.weightedInvestmentUnequal(portfolio, stockWeights, amount, date, commission);
      }
      view.putOutput("Successfully added the stocks to the portfolio.\n");
    } catch (Exception e) {
      view.putOutput(e.getMessage());
    }
    return true;
  }

  /**
   * This function is used to get portfolio name from the user.
   */
  private String getPortfolioName() {
    view.putOutput("Enter the portfolio name you want to add the stocks to:\n");
    String portfolio = sc.next();
    if (isInputQuit(portfolio)) {
      view.putOutput("Exiting operation.\n");
      System.exit(0);
    }
    return portfolio;
  }

  /**
   * This function is used to fetch percent values from the user. Zero and Negative values aren't
   * allowed.
   */
  private double getPercentage(String sTicker, double percentSum) {
    boolean validPercentage = false;
    double perc = 0;
    while (!validPercentage) {
      view.putOutput("Enter the percentage for " + sTicker + ":\n");
      String percString = sc.next();
      try {
        perc = Double.parseDouble(percString);
        if (perc <= 0) {
          view.putOutput("You must enter a positive percentage amount.\n");
        } else if (perc + percentSum > 100) {
          view.putOutput("Total percentage tally is going to be greater than 100. You " +
                  "have only " + (100 - percentSum) + " left to split amongst more stocks.\n");
        } else {
          validPercentage = true;
        }
      } catch (Exception e) {
        view.putOutput("Please enter a valid positive number.\n");
      }
    }
    return perc;
  }

  /**
   * This function checks whether the percent values add up to 100 or not.
   */
  private boolean continueOrNot(double percentSum) {
    return percentSum != 100;
  }

  private String getChoice() {
    boolean choiceValid = false;
    view.putOutput("Do you want to split money equally amongst the stocks: (Enter Y or N)\n");
    String choice = sc.next();
    if (isInputQuit(choice)) {
      view.putOutput("Exiting operation.\n");
      System.exit(0);
    }

    while (!choiceValid) {
      if (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N")) {
        view.putOutput("Please enter either Y or N\n");
      } else {
        choiceValid = true;
      }
    }
    return choice;
  }

  /**
   * This helper function us used to get the date on which to buy the stocks on from the user.
   */
  private Date getDate() {
    boolean dateValid = false;
    Date d = null;
    while (!dateValid) {
      view.putOutput("What date do you wish to buy the stocks on? (Format: YYYY-MM-DD)\n");
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
   * This helper function is used to get Amount to be invested from the user.
   */
  private double getStockAmount() {
    boolean validNumber = false;
    double value = -1;
    while (!validNumber) {
      view.putOutput("Enter the amount to be invested into stocks:\n");
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
          view.putOutput("ERROR: You have to invest a positive amount of money.\n");
        }
      } catch (Exception e) {
        view.putOutput("ERROR: Please enter a valid amount.\n");
      }
    }
    return value;
  }

  /**
   * This helper function is used to get commission value from the user.
   */
  private double getCommission() {
    boolean commissionValid = false;
    double commission = 0;
    while (!commissionValid) {
      view.putOutput("What commission is being charged to buy shares of each stock? " +
              "(In dollars)\n");
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
}
