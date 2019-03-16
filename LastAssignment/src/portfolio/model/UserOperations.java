package portfolio.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * This interface provides the methods that incorporate the functionalities the user uses by passing
 * input from the view. The user can search for a stock from the database, create a portfolio, view
 * his/her created portfolios, view the contents of a single portfolio, buy a stock and add it to
 * his/her particular portfolio.
 *
 * <p>Secondary functionalities include calculating the amount he/she invested in a particular
 * portfolio, for partcular/all stocks for any given date. He/She can also calculate the valuation
 * of a particular portfolio, for particular/all stocks for any given date.</p>
 */
public interface UserOperations extends UserOperationsModified {

  /**
   * Creates a portfolio of a given Unique name.
   *
   * @param portfolioName Unique Portfolio Name
   */
  void createPortfolio(String portfolioName);

  // void sellStock(Stock s, Date d, int count, double sellPrice);

  /**
   * This function provides a view all names of the portfolio/s created by the user.
   *
   * @return String containing portfolio names created till date.
   * @throws IllegalStateException if there are no portfolios.
   */
  List<String> viewAllPortfolios();

  /**
   * This function displays an expanded view of a valid existing portfolio name along with it's
   * stock quantity and costbasis that were bought before a valid date.
   *
   * @return A String containing the portfolio details for each stock contained in it by a date.
   * @throws IllegalArgumentException if the portfolio doesn't exist.
   */
  HashMap viewPortfolio(String portfolioName, Date date);

  /**
   * This function returns a double value of the valuation for a provided valid existing portfolio
   * name on the value of a particular date. If the stockName is provided, it calculates for that
   * particular stock, else, it calculates the total valuation for all the stocks in the portfolio.
   *
   * @return Double value containing the valuation of the portfolio for respective arguments.
   * @throws IllegalArgumentException if the portfolio doesn't exist or any of the arguments are not
   *                                  valid.
   */
  double evaluateNet(String portfolioName, String stockName, Date d);


  /**
   * This function returns the amount invested by the user or the costbasis of the user for a
   * provided valid existing portfolio name on the value of a particular date. If the stockName is
   * provided, it calculates for that particular stock, else, it calculates the total CostBasis for
   * all the stocks in the portfolio.
   *
   * @return Double value containing the invested amount for the portfolio for respective arguments.
   * @throws IllegalArgumentException if the portfolio doesn't exist or any of the arguments are not
   *                                  valid.
   */
  double amountInvested(String portfolioName, String stockName, Date d);

  /**
   * Searches for data about a stock based on the TickerSymbol provided by the user.
   *
   * @param tickerSymbol Valid Ticker symbol for a particular stock.
   * @return String containing the date, values(open,close,low,high) and volume data of the stock.
   * @throws IllegalArgumentException if the ticker symbol is not valid.
   */
  String search(String tickerSymbol);
}
