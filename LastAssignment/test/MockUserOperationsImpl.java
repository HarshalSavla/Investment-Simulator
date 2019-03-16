import java.util.Date;
import java.util.HashMap;
import java.util.List;

import portfolio.model.BuyType;
import portfolio.model.UserOperations;

/**
 * A mock model class that implements {@link UserOperations} interface. It helps us test the
 * controller in isolation and log the sequence of all the different operations being performed, as
 * well as checking that the input passed has not been modified in an unexpected way by the
 * controller.
 */
public class MockUserOperationsImpl implements UserOperations {
  private StringBuilder log;

  /**
   * A constructor that sets the following parameters.
   *
   * @param log a StringBuilder object that sets up a way to log all the operations being
   *            performed.
   */
  MockUserOperationsImpl(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void createPortfolio(String portfolioName) {
    log.append("Calling model to create a portfolio.\n");
  }

  @Override
  public List<String> viewAllPortfolios() {
    log.append("Calling model to view all portfolios.\n");
    return null;
  }

  @Override
  public HashMap<String, Object> viewPortfolio(String portfolioName, Date date) {
    log.append("Calling model to view portfolio " + portfolioName + " on date "
            + date.toString() + ".\n");
    return null;
  }

  @Override
  public double evaluateNet(String pName, String stockName, Date d) {
    log.append("Calling model to get net value of stock"
            + stockName + " in portfolio " + pName + " on " + d.toString() + ".\n");
    return 10;
  }

  @Override
  public double amountInvested(String pName, String stockName, Date d) {
    log.append("Calling model to calculate cost basis of stock "
            + stockName + " in portfolio " + pName + "on date" + d.toString() + ".\n");
    return 20;
  }

  @Override
  public String search(String tickerSymbol) {
    log.append("Searching for ticker symbol: " + tickerSymbol + ".\n");
    return "Model: Searching for stock data.\n";
  }

  @Override
  public void buyStock(String portfolioName, String stockName, Date d, double value,
                       double commission, BuyType buyType) {
    if (buyType == BuyType.COUNT) {
      log.append("Calling model to add " + value + " no. of shares belonging to " + stockName
              + " to " + portfolioName + " portfolio with a commission fee of " + commission
              + "on date " + d.toString() + ".\n");
    } else if (buyType == BuyType.AMOUNT) {
      log.append("Calling model to add " + value + " dollars worth of shares belonging to "
              + stockName + " to " + portfolioName + " portfolio with a commission fee of "
              + commission + "on date " + d.toString() + ".\n");
    }
  }

  @Override
  public void weightedInvestEqual(String portfolioName, Date date, double value,
                                  double commission) {
    log.append("Calling model to divide " + value + " dollars worth of stocks equally in "
            + portfolioName + " and buy them with a commission of " + commission
            + " for each transaction on date " + date.toString() + ".\n");
  }

  @Override
  public void weightedInvestmentUnequal(String portfolioName, HashMap<String, Double> stockWeights,
                                        double value, Date date, double commission) {
    log.append("Calling model to divide ").append(value)
            .append(" on date ").append(date.toString())
            .append(" dollars worth of stocks in a ratio in ")
            .append(portfolioName).append(" and buy them with a commission of ")
            .append(commission).append(" for each transaction.\n");
    log.append("Ratio is: ").append(stockWeights.toString()).append("\n");
  }

  @Override
  public void createEqualStrategy(String portfolioName, Date startDate, Date endDate, int days,
                                  double amount, double commission) {
    log.append("Calling model to divide ").append(amount)
            .append(" dollars worth of stocks equally ").append(" in ")
            .append(portfolioName).append(" and buy them with a commission of ")
            .append(commission).append(" for each transaction.\n");
    log.append("This is a recurring transaction occurring every ").append(days)
            .append(" days from ").append(startDate.toString()).append(" to ")
            .append(endDate.toString()).append(".\n");
  }

  @Override
  public void createUnequalStrategy(String portfolioName, HashMap<String, Double> weights,
                                    Date startDate, Date endDate, int days, double amount,
                                    double commission) {
    log.append("Calling model to divide ").append(amount)
            .append(" dollars worth of stocks in a ratio ")
            .append(weights.toString()).append(" in ")
            .append(portfolioName).append(" and buy them with a commission of ")
            .append(commission).append(" for each transaction.\n");
    log.append("This is a recurring transaction occurring every ").append(days)
            .append(" days from ").append(startDate.toString()).append(" to ")
            .append(endDate.toString()).append(".\n");
  }

  @Override
  public void savePortfolio(String pname, String fname) {
    log.append("Saving portfolio " + pname + " to a text file with name " + fname);
  }

  @Override
  public void saveEqualStrategy(Date startDate, Date endDate, int days, double amount,
                                double commission, String fname) {
    log.append("Saving equal strategy to a text file with name " + fname);
  }

  @Override
  public void saveUnequalStrategy(HashMap<String, Double> weights,
                                  Date startDate, Date endDate, int days, double amount,
                                  double commission, String fname) {
    log.append("Saving unequal strategy to a text file with name " + fname);
  }

  @Override
  public void loadStrategy(String fname, String pname) {
    log.append("Loading portfolio " + pname + " from a text file with name " + fname);
  }

  @Override
  public void loadPortfolio(String fname, String pname) {
    log.append("Loading portfolio " + pname + " from a text file with name " + fname);
  }
}
