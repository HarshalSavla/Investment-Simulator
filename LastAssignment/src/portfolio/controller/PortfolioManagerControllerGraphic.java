package portfolio.controller;

import static portfolio.controller.PortfolioManagerController.helperGetDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import portfolio.ButtonListener;
import portfolio.model.BuyType;
import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerViewGraphic;

/**
 * This class implements the {@link IPortfolioManagerController} interface that handles the
 * operations of the controller in creating and viewing portfolios, buying stocks from the market,
 * viewing information about a stock and performing cost basis and valuation analyses on the
 * portfolios, but from a GUI instead of a text-based console.
 */
public class PortfolioManagerControllerGraphic implements IPortfolioManagerController {

  private UserOperations userModel;
  private PortfolioManagerViewGraphic userView;

  /**
   * A controller for the GUI implementation of the application. This sets the user model and the
   * view.
   *
   * @param userModel the model of the application.
   * @param userView  the view of the application.
   */
  public PortfolioManagerControllerGraphic(UserOperations userModel,
      PortfolioManagerViewGraphic userView) {
    if (userModel == null || userView == null) {
      throw new IllegalArgumentException("Model and view objects can not be null.");
    }
    this.userView = userView;
    this.userModel = userModel;

  }

  @Override
  public void startInvesting() {
    configureButtonListener();
  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("CreatePortfolio", () -> {
      String portfolioName = userView.createPortfolio();
      if (!portfolioName.isEmpty()) {
        try {
          userModel.createPortfolio(portfolioName.toUpperCase());
          userView.displayResult("Portfolio " + portfolioName + " created successfully.");
        } catch (Exception e) {
          userView.showErrorMessage(e.getMessage());
        }
      }
    });

    buttonClickedMap.put("ViewAllPortfolio", () -> {
      try {
        List<String> allPortfolios = userModel.viewAllPortfolios();
        StringBuilder s = new StringBuilder();
        for (String portfolio : allPortfolios) {
          s.append(portfolio + "\n");
        }
        userView.displayResult(s.toString());
      } catch (Exception e) {
        userView.showErrorMessage(e.getMessage());
      }
    });

    buttonClickedMap.put("ExpandedViewOfPortfolio", () -> {
      String[] result = userView.expandedViewOfPortfolio();
      if (!isResultEmpty(result)) {
        try {
          HashMap<String, Object> portfolioView = userModel.viewPortfolio(result[0].toUpperCase(),
              helperGetDate(result[1]));
          if (portfolioView == null) {
            userView.displayResult("The portfolio is empty.\n");
          } else {
            StringBuilder pfView = new StringBuilder();
            pfView.append("Portfolio: ").append(portfolioView.get("Name"))
                .append("_____________________________________________________________________" +
                    "___________________________________________\n")
                .append("\n\n");
            List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)
                portfolioView.get("StockData");
            for (HashMap<String, Object> stock : list) {
              pfView.append("Ticker: ").append(stock.get("Ticker")).append("\n");
              pfView.append("Amount invested in stock: $").append(stock.get("CostBasis"))
                  .append("\n");
              pfView.append("Valuation of stock: $").append(stock.get("Valuation")).append("\n");
              TreeMap<String, String[]> stockData = (TreeMap<String, String[]>)
                  stock.get("TransactionData");
              for (String date : stockData.keySet()) {
                String data = String.format("%-30s \t\t|\t\t%-7s \t\t|\t\t%-7s\n", date,
                    stockData.get(date)[0], stockData.get(date)[1]);
                pfView.append(data);
              }
              pfView.append("__________________________________________________________________" +
                  "______________________________________________\n");
            }
            pfView.append("\nValuation of portfolio: ").append(portfolioView.get("Valuation"))
                .append("\n").append("Cost Basis of portfolio: ").
                append(portfolioView.get("CostBasis")).append("\n");
            pfView.append("_____________________________________________________________________" +
                "___________________________________________\n");

            userView.displayResult(pfView.toString());
          }
        } catch (Exception e) {
          userView.showErrorMessage(e.getMessage());
        }
      }
    });

    buttonClickedMap.put("BuyAStock", () -> {
      userView.loadingMessage();
      String result[] = userView.buyAStock();
      if (!isResultEmpty(result)) {
        try {
          if (result[5].equals("$ value")) {
            userModel.buyStock(result[0].toUpperCase(), result[1].toUpperCase(),
                helperGetDate(result[2]),
                Double.parseDouble(result[3]), Double.parseDouble(result[4]), BuyType.AMOUNT);
          } else if (result[5].equals("# of stocks")) {
            userModel.buyStock(result[0].toUpperCase(), result[1].toUpperCase(),
                helperGetDate(result[2]),
                Double.parseDouble(result[3]), Double.parseDouble(result[4]), BuyType.COUNT);
          }
          userView.displayResult("Stock for " + result[0] + " bought successfully\n");
        } catch (Exception e) {
          userView.showErrorMessage(e.getMessage());
        }
      } else {
        userView.displayResult("Operation aborted!\n");
      }
    });

    buttonClickedMap.put("GetInfo", () -> {
      String result = userView.getStockInfo();
      if (!result.trim().isEmpty()) {
        try {
          String stockInfo = userModel.search(result.toUpperCase());
          userView.displayResult(stockInfo);
        } catch (Exception e) {
          userView.showErrorMessage(e.getMessage());
        }
      } else {
        userView.displayResult("Operation aborted!\n");
      }
    });

    buttonClickedMap.put("ViewValuation", () -> {
      userView.loadingMessage();
      String result[] = userView.getValuation();
      if (!isResultEmpty(result)) {
        try {
          double valuation = userModel.evaluateNet(result[0].toUpperCase(),
              result[1].toUpperCase(), helperGetDate(result[2]));
          userView.displayResult("Valuation of portfolio: " + result[0] + " = "
              + String.valueOf(valuation));
        } catch (Exception e) {
          userView.showErrorMessage(e.getMessage());
        }
      } else {
        userView.displayResult("Operation aborted!\n");
      }
    });


    buttonClickedMap.put("ViewCostBasis", () -> {
      userView.loadingMessage();
      String result[] = userView.getCostBasis();
      if (!isResultEmpty(result)) {
        try {
          double costBasis = userModel.amountInvested(result[0].toUpperCase(),
              result[1].isEmpty() ? null : result[1].toUpperCase(), helperGetDate(result[2]));
          userView.displayResult("Cost basis of portfolio: " + result[0] + " = "
              + String.valueOf(costBasis));
        } catch (Exception e) {
          userView.showErrorMessage(e.getMessage());
        }
      } else {
        userView.displayResult("Operation aborted!\n");
      }
    });

    buttonClickedMap.put("OneTimeInvestment", () -> {
      userView.loadingMessage();
      String[] result = userView.oneTimeStrategy();
      HashMap<String, Double> percentageValues = new HashMap<>();
      if (!isResultEmpty(result)) {
        try {
          if (result[4].equals("Equal Weights")) {
            userModel.weightedInvestEqual(result[0].toUpperCase(), helperGetDate(result[1]),
                Double.valueOf(result[2]), Double.valueOf(result[3]));
            userView.displayResult("Successfully implemented the one time investment.\n");
          } else if (result[4].equals("Unequal Weights")) {
            double percent = 0;
            while (percent != 100.0) {
              if (percent > 100.0 || percent < 0.0) {
                userView.errorMessage();
                percent = 0;
              }
              HashMap<String, Double> percentageValueCurrent = new HashMap<>();
              percentageValueCurrent = userView.helperRecurringInvestment(100 - percent);
              for (String stockTicker : percentageValueCurrent.keySet()) {
                percentageValues.put(stockTicker, percentageValueCurrent.get(stockTicker));
              }
              percent = percentageValues.values().stream().
                  mapToDouble(value -> value).sum();
            }
            userModel.weightedInvestmentUnequal(result[0].toUpperCase(), percentageValues,
                Double.valueOf(result[2]),
                helperGetDate(result[1]), Double.valueOf(result[3]));
            userView.displayResult("Successfully implemented the one time investment.\n");
          }
        } catch (Exception e) {
          userView.showErrorMessage(e.getMessage());
        }
      } else {
        userView.displayResult("Operation aborted!\n");
      }
    });

    buttonClickedMap.put("RecurringInvestment", () -> {
      userView.loadingMessage();
      String[] result = userView.recurringStrategy();
      HashMap<String, Double> percentageValues = new HashMap<>();
      if (!isResultEmpty(result)) {
        try {
          if (result[6].equals("Equal Weights")) {
            userModel.createEqualStrategy(result[0].toUpperCase(), helperGetDate(result[1]),
                helperGetDate(result[2]), Integer.valueOf(result[3]), Double.valueOf(result[4]),
                Double.valueOf(result[5]));
          } else if (result[6].equals("Unequal Weights")) {
            double percent = 0;
            while (percent != 100.0) {
              if (percent > 100.0 || percent < 0.0) {
                userView.errorMessage();
                percent = 0;
              }
              HashMap<String, Double> percentageValueCurrent = new HashMap<>();
              percentageValueCurrent = userView.helperRecurringInvestment(100 - percent);
              for (String stockTicker : percentageValueCurrent.keySet()) {
                percentageValues.put(stockTicker, percentageValueCurrent.get(stockTicker));
              }
              percent = percentageValues.values().stream().
                  mapToDouble(value -> value).sum();
            }
            userModel.createUnequalStrategy(result[0].toUpperCase(), percentageValues,
                helperGetDate(result[1]), helperGetDate(result[2]), Integer.valueOf(result[3]),
                Double.valueOf(result[4]), Double.valueOf(result[5]));
          }
          userView.displayResult("Stock for " + result[0] + " bought successfully");
        } catch (Exception e) {
          userView.showErrorMessage(e.getMessage());
        }
      } else {
        userView.displayResult("Operation aborted!\n");
      }
    });

    buttonClickedMap.put("SaveInvestmentStrategy", () -> {
      userView.loadingMessage();
      String[] result = userView.saveStrategyToFile();
      HashMap<String, Double> percentageValues = new HashMap<>();
      if (!isResultEmpty(result)) {
        try {
          if (result[6].equals("Equal Weights")) {
            userModel.saveEqualStrategy(helperGetDate(result[0]),
                helperGetDate(result[1]), Integer.valueOf(result[2]), Double.valueOf(result[3]),
                Double.valueOf(result[4]), result[5]);
          } else if (result[6].equals("Unequal Weights")) {
            double percent = 0;
            while (percent != 100.0) {
              if (percent > 100.0 || percent < 0.0) {
                userView.errorMessage();
                percent = 0;
              }
              HashMap<String, Double> percentageValueCurrent = new HashMap<>();
              percentageValueCurrent = userView.helperRecurringInvestment(100 - percent);
              for (String stockTicker : percentageValueCurrent.keySet()) {
                percentageValues.put(stockTicker, percentageValueCurrent.get(stockTicker));
              }
              percent = percentageValues.values().stream().
                  mapToDouble(value -> value).sum();
            }
            userModel.saveUnequalStrategy(percentageValues, helperGetDate(result[0]),
                helperGetDate(result[1]),
                Integer.valueOf(result[2]), Double.valueOf(result[3]),
                Double.valueOf(result[4]), result[5]);
          }
          userView.displayResult("Strategy created successfully, file saved as " + result[5]);
        } catch (Exception e) {
          userView.displayResult(e.getMessage());
        }
      } else {
        userView.displayResult("Operation aborted!\n");
      }
    });

    buttonClickedMap.put("RetrieveStrategyFromFile", () -> {
      String[] result = userView.retrieveStrategyFromFile();
      if (!isResultEmpty(result)) {
        try {
          userModel.loadStrategy(result[0], result[1].toUpperCase());
        } catch (Exception e) {
          userView.showErrorMessage(e.getMessage());
        }
      } else {
        userView.displayResult("Operation aborted!\n");
      }
    });

    buttonClickedMap.put("SavePortfolio", () -> {
      String[] result = userView.savePortfolio();
      if (!isResultEmpty(result)) {
        try {
          userModel.savePortfolio(result[0].toUpperCase(), result[1]);
        } catch (Exception e) {
          userView.showErrorMessage(e.getMessage());
        }
      } else {
        userView.displayResult("Operation aborted!\n");
      }
    });

    buttonClickedMap.put("RetrievePortfolio", () -> {
      String[] result = userView.retrievePortfolio();
      if (!isResultEmpty(result)) {
        try {
          userModel.loadPortfolio(result[0], result[1].toUpperCase());

          userView.displayResult("Portfolio Successfully Retrieved");
        } catch (Exception e) {
          userView.errorMessage();
        }
      } else {
        userView.displayResult("Operation aborted!\n");
      }
    });

    buttonClickedMap.put("quit", () -> {
      if (userView.quit() == 0) {
        System.exit(0);
      } else {
        return;
      }
    });

    buttonClickedMap.put("ClearScreen", () -> userView.clearScreen());

    buttonClickedMap.put("Plot Graph", () -> userView.plotGraph());

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.userView.addActionListener(buttonListener);
  }

  private boolean isResultEmpty(String[] result) {
    for (String r : result) {
      if (r.trim().equals("$ value") || r.trim().equals("# of stocks") ||
          r.trim().equals("Equal Weights") || r.trim().equals("Unequal Weights")) {
        continue;
      } else if (!r.trim().isEmpty()) {
        return false;
      }
    }
    return true;
  }
}