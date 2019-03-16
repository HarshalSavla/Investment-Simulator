package portfolio.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This interface provides the functionalities that can be performed on a Portfolio object. You can
 * buy stock and add it to the portfolio. You can get the count of a particular stock in the
 * portfolio. A portfolio can be evaluated for a particular date, for a single/all stocks it
 * contains. Amount invested can be found out for the same. A getByDate function returns the
 * portfolio contents as per a given particular date.
 */
public interface Portfolio {

  /**
   * This function buys stock of a valid name for a valid existent date of a valid non-zero positive
   * quantity and adds it to the current portfolio.
   *
   * @param stockName Valid Stock Ticker Name
   * @param date      valid date object.
   * @param count     valid non-zero count
   * @param buyType   type of buying method, either by count or by amount.
   */
  void buyStock(String stockName, Date date, double count, double commission, BuyType buyType);

  /**
   * This function is used to invest once in a portfolio where the value is split equally among it's
   * contents.*
   *
   * @param value      Amount to be invested.
   * @param date       Date of investment.
   * @param commission $Commission per transaction.
   */
  void weightedInvestmentEqual(Date date, double value, double commission);

  /**
   * This function is used to invest once in a portfolio where the value is splitted unequally among
   * the ticker symbol and their respective percent weights provided by the user.
   *
   * @param stockWeights HashMap containing Ticker-Weights Mapping for investment.
   * @param value        Amount to be invested.
   * @param date         Date of investment.
   * @param commision    $Commission per transaction.
   */
  void weightedInvestmentUnequal(HashMap<String, Double> stockWeights, double value,
                                 Date date, double commision);


  /**
   * This function returns a double value of the valuation for the portfolio on a particular date.
   * If the stockName is provided, it calculates for that particular stock, else, it calculates the
   * total valuation for all the stocks in the portfolio.
   *
   * @param stockName Valid StockName.
   * @param date      Valid date object.
   * @return Double value containing the valuation of the portfolio for the respective arguments.
   */
  double getPortfolioValuation(String stockName, Date date);

  /**
   * This function returns the amount invested by the user or the costbasis of the user for the
   * portfolio name on the value of a particular date. If the stockName is provided, it calculates
   * for that particular stock, else, it calculates the total CostBasis for all the stocks in the
   * portfolio.
   *
   * @param stockName Valid StockName.
   * @param date      Valid date object.
   * @return Double value containing the valuation of the portfolio for the respective arguments.
   */
  double getAmountInvested(String stockName, Date date);

  /**
   * Gets a string of the stock data before the particular date provided from it's contents.
   *
   * @param date a valid date object
   * @return String containing the log of the stock it had as on the date.
   */
  HashMap getByDate(Date date);

  /**
   * This function creates an object of unequal strategy class. It takes in a hashmap containing the
   * name of the stocks as the key and their percentage weightage as the value. A startDate and an
   * endDate is also needed followed by the number of days for which the function recurred for. A
   * double value containing the amoung and the commission comes next.
   *
   * @param weights    (StockName, % weight) key value pair
   * @param startDate  a valid date object
   * @param endDate    a valid date object
   * @param days       integer number of days
   * @param amount     amount in dollars
   * @param commission commission amount in dollars
   */
  void createUnequalStrategy(HashMap<String, Double> weights, Date startDate, Date endDate,
                             int days, double amount, double commission);

  /**
   * This function creates an object of unequal strategy class. It takes a startDate and an endDate,
   * followed by the number of days for which the function recurred for. A double value containing
   * the amoung and the commission comes next.
   *
   * @param startDate  a valid date object
   * @param endDate    a valid date object
   * @param days       integer number of days
   * @param amount     amount in dollars
   * @param commission commission amount in dollars
   */
  void createEqualStrategy(Date startDate, Date endDate,
                           int days, double amount, double commission);

  /**
   * This function is used to save the current portfolio to a given valid filename.
   *
   * @param fname String containing absolute path/filename.
   */
  void saveToFile(String fname);

  /**
   * This function is used to save an Equal Strategy to a given valid filename.
   *
   * @param startDate  a Valid Date object.
   * @param endDate    a Valid Date object.
   * @param days       a Valid count of number of days.
   * @param amount     a valid amount to be invested.
   * @param commission $Commission paid per transaction.
   * @param fname      Valid FileName.
   */
  void saveEqualStrategy(Date startDate, Date endDate, int days, double amount, double commission,
                         String fname);


  /**
   * This function is used to save an UnEqual Strategy to a given valid filename.
   *
   * @param weights    a valid Hashmap of ticker and weights.
   * @param startDate  a Valid Date object.
   * @param endDate    a Valid Date object.
   * @param days       a Valid count of number of days.
   * @param amount     a valid amount to be invested.
   * @param commission $Commission paid per transaction.
   * @param fname      Valid FileName.
   */
  void saveUnEqualStrategy(HashMap<String, Double> weights, Date startDate, Date endDate,
                           int days, double amount, double commission, String fname);

  /**
   * This function is used to load a particular strategy to the  current portfolio from a file.
   *
   * @param sc Scanner containing the InputFileStream to the file from which we read.
   */
  void loadStrategy(Scanner sc);

  void performanceGraph(Date sDate, Date eDate, String fname);
}
