package portfolio.model;

import java.util.Date;
import java.util.HashMap;

public interface UserOperationsModified {
  /**
   * Buy Stock of a valid stockname for an valid date of a positive non-zero quantity if BuyType is
   * COUNT or positive non-zero amount if BuyType is AMOUNT and add it to a valid existing portfolio
   * name.
   *
   * @param portfolioName valid portfolio name.
   * @param stockName     a valid stock ticker symbol.
   * @param d             valid date.
   * @param value         amount to be invested OR count of stocks to be bought.
   * @param commission    $ commission for transaction.
   * @param buyType       Enum BuyType.AMOUNT if value is amount to be invested and Buytype.COUNT if
   *                      it represents number of stocks to be bought.
   * @throws IllegalArgumentException if the portfolio doesn't exist or any of the arguments are not
   *                                  valid.
   */
  void buyStock(String portfolioName, String stockName, Date d,
                double value, double commission, BuyType buyType);

  /**
   * This is method is used as a weighted Investment for stocks and their unequal %weights for
   * investment. A valid portfolio name is required for which the investment strategy is executed
   * just once. A hashmap containing the key value pair of the stocks and their %weights for
   * investment.
   *
   * @param portfolioName valid portfolio name.
   * @param stockWeights  stockName, % weights.
   * @param value         Amount to be invested.
   * @param date          a valid Date object.
   * @param commission    $ commission per transaction.
   * @throws IllegalArgumentException if the portfolio doesn't exist or any of the arguments are not
   *                                  valid.
   */
  void weightedInvestmentUnequal(String portfolioName, HashMap<String, Double> stockWeights,
                                 double value, Date date, double commission);

  /**
   * This function is used to create a Strategy object of equal types for a valid portfolio name. It
   * takes a startDate and an endDate, followed by the number of days for which the function
   * recurred for. A double value containing the amount and the commission comes next.
   *
   * @param portfolioName Valid portfolio name.
   * @param startDate     a valid date object.
   * @param endDate       a valid date object.
   * @param days          Number of days after which the recurrence occurs.
   * @param amount        Amount to be invested.
   * @param commission    $Commission per transaction.
   * @throws IllegalArgumentException if the portfolio doesn't exist or any of the arguments are not
   *                                  valid.
   */
  void createEqualStrategy(String portfolioName, Date startDate, Date endDate, int days,
                           double amount, double commission);

  /**
   * This is method is used as a weighted Investment for stocks already present in the portfolio
   * with equal weights for investment. A valid portfolio name is required for which the investment
   * strategy is executed just once. A hashmap containing the key value pair of the stocks and their
   * %weights for investment.
   *
   * @param portfolioName valid portfolio name.
   * @param value         Amount to be invested.
   * @param date          a valid Date object.
   * @param commission    $ commission per transaction.
   * @throws IllegalArgumentException if the portfolio doesn't exist or any of the arguments are not
   *                                  valid.
   */
  void weightedInvestEqual(String portfolioName, Date date, double value, double commission);

  /**
   * This function is used to create a Strategy object of unequal type for a valid portfolio name.
   * It takes a startDate and an endDate, followed by the number of days for which the function
   * recurred for. A double value containing the amount and the commission comes next.
   *
   * @param portfolioName Valid portfolio name.
   * @param weights       StockName, %weights.
   * @param startDate     a valid date object.
   * @param endDate       a valid date object.
   * @param days          Number of days after which the recurrence occurs.
   * @param amount        Amount to be invested.
   * @param commission    $Commission per transaction.
   * @throws IllegalArgumentException if the portfolio doesn't exist or any of the arguments are not
   *                                  valid.
   */
  void createUnequalStrategy(String portfolioName, HashMap<String, Double> weights,
                             Date startDate, Date endDate, int days, double amount,
                             double commission);

  /**
   * This function is used to save a portfolio to a given file name in txt format. If the file
   * exists, it will be overwritten.
   *
   * @param pname valid portfolio name
   * @param fname valid file name.
   */
  void savePortfolio(String pname, String fname);

  /**
   * This function is used to save a strategy of Equal type to a given file name in txt format. If
   * the file exists, it will be overwritten.
   *
   * @param startDate  valid date object.
   * @param endDate    valid date object.
   * @param days       Number of days after which recurrence occurs.
   * @param amount     Amount to be invested.
   * @param commission $Commission per transaction.
   * @param fname      String filename of the file to be saved into.
   */
  void saveEqualStrategy(Date startDate, Date endDate, int days,
                         double amount, double commission, String fname);

  /**
   * This function is used to save a strategy of UnEqual type to a given file name in txt format. If
   * the file exists, it will be overwritten.
   *
   * @param weights    valid Hashmap of corresponding ticker symbols and their weights.
   * @param startDate  valid date object.
   * @param endDate    valid date object.
   * @param days       Number of days after which recurrence occurs.
   * @param amount     Amount to be invested.
   * @param commission $Commission per transaction.
   * @param fname      String filename of the file to be saved into.
   */
  void saveUnequalStrategy(HashMap<String, Double> weights,
                           Date startDate, Date endDate, int days, double amount,
                           double commission, String fname);

  /**
   * This function is used to load a Strategy from a valid parsable text file to a given portfolio
   * name.
   *
   * @param fname Valid filename containing the strategy
   * @param pname Valid existing portfolio name.
   */
  void loadStrategy(String fname, String pname);

  /**
   * This function is used to load a Portfolio state from a valid parsable text file to a given
   * portfolio name.
   *
   * @param fname Valid filename containing the strategy
   * @param pname Valid existing portfolio name.
   */
  void loadPortfolio(String fname, String pname);
}

