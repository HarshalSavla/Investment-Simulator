package portfolio.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements the UserOperations interface and overrides it's methods to facilitate the
 * creation of the portfolio objects and various functionalites that comes along with it.
 */
public class UserOperationsImpl implements UserOperations {
  private HashMap<String, Portfolio> portfolioList;

  /**
   * This constructor initialises the HashMap which can store various portfolio objects created by
   * the user.
   */
  public UserOperationsImpl() {
    this.portfolioList = new HashMap<>();
  }

  /**
   * This is a static methods which converts a string ("yyyy-MM--dd") and returns a date object for
   * it.
   *
   * @param date String of the format yyyy-MM-dd
   * @return A Date object for the respective string.
   */
  static Date helperGetDate(String date) {
    Calendar yyyyDdMm = Calendar.getInstance();
    String[] dates = date.split("-");
    yyyyDdMm.set(Calendar.YEAR, Integer.parseInt(dates[0]));
    yyyyDdMm.set(Calendar.MONTH, Integer.parseInt(dates[1]) - 1);
    yyyyDdMm.set(Calendar.DATE, Integer.parseInt(dates[2]));
    yyyyDdMm.set(Calendar.HOUR_OF_DAY, 15);
    yyyyDdMm.set(Calendar.MINUTE, 59);
    yyyyDdMm.set(Calendar.SECOND, 59);
    yyyyDdMm.set(Calendar.MILLISECOND, 0);
    return yyyyDdMm.getTime();
  }

  @Override
  public void createPortfolio(String portfolioName) {
    if (portfolioName == null) {
      throw new IllegalArgumentException("Null Portfolio name!\n");
    }
    portfolioName = portfolioName.toUpperCase();
    if (this.portfolioList.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio of same name already exists.\n");
    } else {
      portfolioList.put(portfolioName,
              new PortfolioImpl(portfolioName));
    }
  }

  @Override
  public void buyStock(String portfolioName, String stockName, Date d,
                       double value, double commission, BuyType buyType) {
    stockName = stockName.toUpperCase();
    if (commission < 0) {
      throw new IllegalArgumentException("Commission cannot be negative");
    }
    if (portfolioName == null || stockName == null || d == null) {
      throw new IllegalArgumentException("Null value passed!\n");
    }
    if (this.portfolioList.containsKey(portfolioName.toUpperCase())) {
      Portfolio workOn = portfolioList.get(portfolioName.toUpperCase());
      workOn.buyStock(stockName, d, value, commission, buyType);
    } else {
      throw new IllegalArgumentException("This portfolio does not exist.\n");
    }
  }

  @Override
  public List<String> viewAllPortfolios() {
    List listOfPortfolios = new ArrayList<String>();
    for (String portfolioName : this.portfolioList.keySet()) {
      listOfPortfolios.add(portfolioName);
    }

    if (listOfPortfolios.isEmpty()) {
      throw new IllegalStateException("You have not created any portfolios!\n");
    }
    return listOfPortfolios;
  }

  @Override
  public HashMap viewPortfolio(String portfolioName, Date date) {
    portfolioName = portfolioName.toUpperCase();
    if (this.portfolioList.containsKey(portfolioName)) {
      HashMap ret = portfolioList.get(portfolioName).getByDate(date);
      if (0 == Double.parseDouble((String) ret.get("CostBasis"))) {
        ret = null;
      }
      return ret;
    } else {
      throw new IllegalArgumentException("Portfolio of same name already exists.\n");
    }
  }

  @Override
  public double evaluateNet(String pName, String stockName, Date d) {
    if (pName == null || d == null) {
      throw new IllegalArgumentException("Null value passed!\n");
    }
    pName = pName.toUpperCase();
    if (this.portfolioList.containsKey(pName)) {
      return this.portfolioList.get(pName).getPortfolioValuation(stockName, d);
    } else {
      throw new IllegalArgumentException("Portfolio of this name does not exist.\n");
    }

  }

  @Override
  public double amountInvested(String pName, String stockName, Date d) {
    if (pName == null || d == null) {
      throw new IllegalArgumentException("Null value passed!\n");
    }
    pName = pName.toUpperCase();
    if (!this.portfolioList.containsKey(pName)) {
      throw new IllegalArgumentException("Portfolio doesn't exists.\n");
    } else {
      return portfolioList.get(pName).getAmountInvested(stockName, d);
    }
  }

  @Override
  public String search(String tickerSymbol) {
    Query q = new Query(tickerSymbol);
    return q.toString();
  }

  @Override
  public void weightedInvestEqual(String portfolioName, Date date,
                                  double value, double commission) {
    portfolioName = portfolioName.toUpperCase();
    if (!this.portfolioList.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio doesn't exists\n");
    }
    this.portfolioList.get(portfolioName)
            .weightedInvestmentEqual(date, value, commission);
    return;
  }

  @Override
  public void weightedInvestmentUnequal(String portfolioName, HashMap<String, Double> stockWeights,
                                        double value, Date date, double commission) {
    portfolioName = portfolioName.toUpperCase();
    if (!this.portfolioList.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio doesn't exists\n");
    }
    this.portfolioList.get(portfolioName)
            .weightedInvestmentUnequal(stockWeights, value, date, commission);
  }

  @Override
  public void createEqualStrategy(String portfolioName, Date startDate, Date endDate, int days,
                                  double amount, double commission) {
    portfolioName = portfolioName.toUpperCase();
    if (!this.portfolioList.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio doesn't exist.\n");
    }
    this.portfolioList.get(portfolioName).createEqualStrategy(startDate,
            endDate, days, amount, commission);
  }

  @Override
  public void createUnequalStrategy(String portfolioName, HashMap<String, Double> weights,
                                    Date startDate, Date endDate, int days, double amount,
                                    double commission) {
    portfolioName = portfolioName.toUpperCase();
    if (!this.portfolioList.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio doesn't exist.\n");
    }
    this.portfolioList.get(portfolioName)
            .createUnequalStrategy(weights, startDate, endDate, days, amount, commission);
  }

  @Override
  public void savePortfolio(String pname, String fname) {
    pname = pname.toUpperCase();
    if (pname == null || fname == null) {
      throw new IllegalArgumentException("Name can't be null");
    }
    if (!this.portfolioList.containsKey(pname)) {
      throw new IllegalArgumentException("Portfolio doesn't exist");
    }
    this.portfolioList.get(pname).saveToFile(fname);
  }

  @Override
  public void saveEqualStrategy(Date startDate, Date endDate, int days,
                                double amount, double commission, String fname) {
    Portfolio p = new PortfolioImpl("");
    p.saveEqualStrategy(startDate, endDate, days, amount, commission, fname);
  }

  @Override
  public void saveUnequalStrategy(HashMap<String, Double> weights,
                                  Date startDate, Date endDate, int days, double amount,
                                  double commission, String fname) {
    Portfolio p = new PortfolioImpl("");
    p.saveUnEqualStrategy(weights, startDate, endDate, days, amount, commission, fname);
  }


  @Override
  public void loadStrategy(String fname, String pname) {
    pname = pname.toUpperCase();
    if (pname == null || fname == null) {
      throw new IllegalArgumentException("Name can't be null");
    }
    if (!this.portfolioList.containsKey(pname)) {
      throw new IllegalArgumentException("Portfolio doesn't exist");
    }
    File f = new File(fname + ".txt");
    if (!f.exists()) {
      throw new IllegalArgumentException("FILE NOT FOUND");
    } else {
      Scanner sc = null;
      try {
        sc = new Scanner(new FileInputStream(f));
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
      Portfolio p = this.portfolioList.get(pname);
      p.loadStrategy(sc);
      this.portfolioList.replace(pname, p);
    }
  }


  @Override
  public void loadPortfolio(String fname, String pname) {
    pname = pname.toUpperCase();
    if (pname == null || fname == null) {
      throw new IllegalArgumentException("Name can't be null");
    }
    File f = new File(fname + ".txt");
    if (!f.exists()) {
      throw new IllegalArgumentException("FILE NOT FOUND");
    } else {
      Scanner sc = null;
      try {
        sc = new Scanner(new FileInputStream(f));
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
      if (this.portfolioList.containsKey(pname)) {
        this.portfolioList.remove(pname);
      }
      this.portfolioList.put(pname, new PortfolioImpl(pname, sc));
      sc.close();
    }
  }

  /**
   * This function assists in generation of a performance graph for an overview of the portfolio.
   */
  public void perFormanceGraph(String pname, String fname, String startDate, String endDate) {
    pname = pname.toUpperCase();
    if (this.portfolioList.containsKey(pname)) {
      Date sDate = UserOperationsImpl.helperGetDate(startDate);
      Date eDate = UserOperationsImpl.helperGetDate(endDate);
      this.portfolioList.get(pname).performanceGraph(sDate, eDate, fname);
    } else {
      throw new IllegalArgumentException("Portfolio doesn't exist.");
    }
  }

}