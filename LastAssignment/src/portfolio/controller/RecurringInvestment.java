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
 * ratio specified by the user. This can then be implemented as a strategy over a period of time
 * (from a start date to an end date) at an interval specified by the user. The user decides whether
 * they want to buy a specific no. of shares of the stock or invest a sum of money towards the
 * stock. The user can also load a previously saved strategy file into a portfolio.
 */
class RecurringInvestment implements UserOperationsController {
  private Scanner sc;
  private PortfolioManagerView view;

  /**
   * A package private constructor that takes in the view object containing links to input and
   * output sources as well as a scanner object to parse through the input.
   *
   * @param view the view object containing connections to input and output sources.
   * @param sc   a scanner object to parse through the input.
   */
  RecurringInvestment(PortfolioManagerView view, Scanner sc) {
    this.view = view;
    this.sc = sc;
  }

  @Override
  public boolean apply(UserOperations userModel) {
    String ch = getChoiceForStrategy();
    if (ch.equals("1")) {
      String portfolio = getPortfolio();
      double amount = getAmount();
      Date startDate = getStartDate();
      Date endDate = getEndDate(startDate);
      int days = getDays();
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
      double commission = getCommission();
      try {
        view.putOutput("Please wait while we add the strategy to the portfolio...\n");
        if (choice.equalsIgnoreCase("Y")) {
          userModel.createEqualStrategy(portfolio, startDate, endDate, days, amount, commission);
        } else {
          userModel.createUnequalStrategy(portfolio, stockWeights, startDate, endDate, days,
                  amount, commission);
        }
        view.putOutput("Successfully added the strategy to the portfolio.\n");
      } catch (Exception e) {
        view.putOutput(e.getMessage());
      }
    } else if (ch.equals("2")) {
      String fileName = getFileName();
      String portfolio = getPortfolio();
      try {
        userModel.loadStrategy(fileName, portfolio);
        view.putOutput("Load successful. Strategy successfully added to " + portfolio
                + " portfolio.\n");
      } catch (Exception e) {
        view.putOutput("ERROR: " + e.getMessage());
      }
    }
    return true;
  }

  private String getFileName() {
    view.putOutput("Enter file name of strategy to be loaded:\n");
    String fileName = sc.next();
    return fileName;
  }

  private String getChoiceForStrategy() {
    view.putOutput("Would you like to:\n1. Create a new strategy\n2. Load an existing strategy\n");
    String choice = "";
    boolean choiceValid = false;
    while (!choiceValid) {
      choice = sc.next();
      if (!choice.equals("1") && !choice.equals("2")) {
        view.putOutput("ERROR: Please enter either 1 or 2.\n");
      } else {
        choiceValid = true;
      }
    }
    return choice;
  }

  /**
   * This is helper function used to get number of days from the user.
   */
  private int getDays() {
    boolean validNumber = false;
    int days = 0;
    while (!validNumber) {
      view.putOutput("How many days do you want between consecutive investments?\n");
      String daysString = sc.next();
      if (isInputQuit(daysString)) {
        view.putOutput("Exiting operation.\n");
        System.exit(0);
      }
      try {
        days = Integer.parseInt(daysString);
        if (days > 0) {
          validNumber = true;
        } else {
          view.putOutput("ERROR: No. of days must be positive.\n");
        }
      } catch (Exception e) {
        view.putOutput("ERROR: Please enter a valid number of days.\n");
      }
    }
    return days;
  }

  /**
   * This is a helper function used to get valid end Date for the strategy from the user.
   */
  private Date getEndDate(Date startDate) {
    boolean dateValid = false;
    Date endDate = null;
    while (!dateValid) {
      view.putOutput("What is the end date for this strategy? (Format: YYYY-MM-DD)\n");
      String date = sc.next();
      try {
        if (isInputQuit(date)) {
          view.putOutput("Exiting operation.\n");
          System.exit(0);
        }
        endDate = helperGetDate(date);
        if (endDate.after(startDate)) {
          dateValid = true;
        } else {
          view.putOutput("Please enter end date that is after " + startDate + "\n");
        }
      } catch (Exception e) {
        view.putOutput("ERROR: Please re-enter date according to the correct format: "
                + "YYYY-MM-DD.\n");
      }
    }
    return endDate;
  }

  /**
   * This is a helper function used to get valid start date for the strategy from the user.
   */
  private Date getStartDate() {
    boolean dateValid = false;
    Date startDate = null;
    while (!dateValid) {
      view.putOutput("What is the start date for this strategy? (Format: YYYY-MM-DD)\n");
      String date = sc.next();
      try {
        if (isInputQuit(date)) {
          view.putOutput("Exiting operation.\n");
          System.exit(0);
        }
        startDate = helperGetDate(date);
        dateValid = true;
      } catch (Exception e) {
        view.putOutput("ERROR: Please re-enter date according to the correct format: "
                + "YYYY-MM-DD.\n");
      }
    }
    return startDate;
  }

  /**
   * This is a helper function used to get the amount to be invested from the user.
   */
  private double getAmount() {
    double amount = 0;
    boolean validNumber = false;
    while (!validNumber) {
      view.putOutput("Enter the amount to be invested into stocks:\n");
      String amountString = sc.next();
      if (isInputQuit(amountString)) {
        view.putOutput("Exiting operation.\n");
        System.exit(0);
      }
      try {
        amount = Double.parseDouble(amountString);
        if (amount > 0) {
          validNumber = true;
        } else {
          view.putOutput("ERROR: Stock is worth a positive amount of money.\n");
        }
      } catch (Exception e) {
        view.putOutput("ERROR: Please enter a valid amount.\n");
      }
    }
    return amount;
  }

  /**
   * This is a helper function used to get the valid portfolio name from the user.
   */
  private String getPortfolio() {
    view.putOutput("Enter the portfolio name you want to add the strategy to:\n");
    String portfolio = sc.next();
    if (isInputQuit(portfolio)) {
      view.putOutput("Exiting operation.");
      System.exit(0);
    }
    return portfolio;
  }

  /**
   * This is a helper function used to get the commission value from the user.
   */
  private double getCommission() {
    boolean commissionValid = false;
    double commission = 0;
    while (!commissionValid) {
      view.putOutput("What commission is being charged to buy these stocks? (In dollars)\n");
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
   * This helper function is used to ask the user whether he wants equal weight or not for the
   * investment stocks.
   */
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
   * This user function is used to get the total percentage value for the stocks and their percent
   * weights for the user.
   */
  private double getPercentage(String sTicker, double percentSum) {
    boolean validPercentage = false;
    double perc = 0;
    while (!validPercentage) {
      view.putOutput("Enter the percentage for" + sTicker + ":\n");
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
   * This function is used check whether the percent sum has reached 100 or not for the user.
   */
  private boolean continueOrNot(double percentSum) {
    if (percentSum == 100) {
      view.putOutput("You have successfully divided all stocks into a 100% split.\n");
      return false;
    }

    return true;
  }
}
