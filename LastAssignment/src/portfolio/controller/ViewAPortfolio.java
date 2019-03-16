package portfolio.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerView;

import static portfolio.controller.PortfolioManagerController.helperGetDate;
import static portfolio.controller.PortfolioManagerController.isInputQuit;

/**
 * This class implements the {@link UserOperationsController} interface. The user can view a
 * portfolio and see its stock composition.
 */
class ViewAPortfolio implements UserOperationsController {
  private Scanner sc;
  private PortfolioManagerView view;

  /**
   * A package private constructor that takes in the view object containing links to input and
   * output sources as well as a scanner object to parse through the input.
   *
   * @param view the view object containing connections to input and output sources.
   * @param sc   a scanner object to parse through the input.
   */
  ViewAPortfolio(PortfolioManagerView view, Scanner sc) {
    this.view = view;
    this.sc = sc;
  }

  @Override
  public boolean apply(UserOperations userModel) {
    String portfolio = getPortfolioName();
    Date d = getViewPortfolioDate();
    try {
      view.putOutput("Fetching view for the portfolio...\n");
      HashMap portfolioView = userModel.viewPortfolio(portfolio, d);
      if (portfolioView == null) {
        view.putOutput("The portfolio is empty.\n");
      } else {
        StringBuilder pfView = new StringBuilder();
        pfView.append("Portfolio: ").append(portfolioView.get("Name"))
                .append("\n_____________________________________________________________________" +
                        "___________________________________________\n")
                .append("\n\n");
        List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)
                portfolioView.get("StockData");
        for (HashMap<String, Object> stock : list) {
          pfView.append("Ticker: ").append(stock.get("Ticker")).append("\n");
          pfView.append("Amount invested in stock: $").append(stock.get("CostBasis")).append("\n");
          pfView.append("Valuation of stock: $").append(stock.get("Valuation")).append("\n");
          TreeMap<String, String[]> stockData = (TreeMap<String, String[]>)
                  stock.get("TransactionData");
          for (String date : stockData.keySet()) {
            String data = String.format("%-30s \t\t|\t\t%-7s \t\t|\t\t%-7s\n",
                    date, stockData.get(date)[0], stockData.get(date)[1]);
            pfView.append(data);
          }
          pfView.append("_____________________________________________________________________" +
                  "___________________________________________\n");
        }
        pfView.append("\nValuation of portfolio: ").append(portfolioView.get("Valuation"))
                .append("\n").append("Cost Basis of portfolio: ").
                append(portfolioView.get("CostBasis")).append("\n");
        pfView.append("_____________________________________________________________________" +
                "___________________________________________\n");
        view.putOutput(pfView.toString());
      }
    } catch (Exception e) {
      view.putOutput("ERROR: " + e.getMessage());
    }
    return true;
  }

  private String getPortfolioName() {
    view.putOutput("Enter the portfolio you want to view:\n");
    String portfolio = sc.next();
    if (isInputQuit(portfolio)) {
      view.putOutput("Exiting operation.\n");
      System.exit(0);
    }
    return portfolio;
  }

  private Date getViewPortfolioDate() {
    boolean isDateValid = false;
    Date d = null;
    while (!isDateValid) {
      view.putOutput("On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n");
      String date = sc.next();
      if (isInputQuit(date)) {
        view.putOutput("Exiting operation.\n");
        System.exit(0);
      }
      try {
        d = helperGetDate(date);
        isDateValid = true;
      } catch (Exception e) {
        view.putOutput("ERROR: Please re-enter date according to the correct format: "
                + "YYYY-MM-DD.\n");
      }
    }
    return d;
  }
}
