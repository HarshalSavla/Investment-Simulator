package portfolio.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * This class implements the portfolio interface and faciliates the functionalities that can be done
 * on it. It stores every stock object present in the portfolio and can be retrieved for analysis.
 */
public class PortfolioImpl implements Portfolio {
  private static int countStrat = 0;
  private String portfolioId;
  private Map<String, Stock> stocksInPortfolio;
  private Map<String, Strategy> strategies;


  /**
   * This constructor initializes the portfolio object along with it's unqiue portfolio id to
   * recognize it by. It also initializes a hashmap that stores the objects of the Stocks that the
   * portfolio can handle.
   */
  PortfolioImpl(String portfolioId) {
    this.portfolioId = portfolioId;
    this.stocksInPortfolio = new HashMap<>();
    this.strategies = new HashMap<>();
  }

  /**
   * This constructor initializes the portfolio object along with it's unique portfolio id to
   * recognize it by. It also initialises a hashmap that stores the objects of the Stocks that the
   * portfolio can handle. It also sets it contents to those which are mentioned in the file
   * associated with the Scanner object.
   */
  PortfolioImpl(String portfolioId, Scanner sc) {
    this.portfolioId = portfolioId;
    this.stocksInPortfolio = new HashMap<>();
    this.strategies = new HashMap<>();
    try {
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        if (line.equals("START OF STOCKS")) {
          this.readStocks(sc);
          line = sc.nextLine();
        }
        if (line.equals("START OF STRATEGIES")) {
          while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.equals("-STRATEGY")) {
              this.loadStrategy(sc);
            }
            if (line.equals("END OF STRATEGIES")) {
              line = sc.nextLine();
              break;
            }
          }
        }
        if (line.equals("END OF PORTFOLIO")) {
          break;
        }
      }
      sc.close();
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * This is a helper function used to read Stock contents of a portfolio saved in a file.
   */
  private void readStocks(Scanner sc) {
    String lines = sc.nextLine();
    while (!lines.equals("END OF STOCKS")) {
      String ticker = sc.nextLine();
      while (true) {
        lines = sc.nextLine();
        if (lines.equals("END OF STOCK")) {
          lines = sc.nextLine();
          break;
        }
        if (lines.equals("-STOCK")) {
          ticker = sc.nextLine();
        }
        String[] line = lines.split(",");
        Date d = UserOperationsImpl.helperGetDate(line[0]);
        double count = Double.parseDouble(line[1]);
        double commission = Double.parseDouble(line[2]);
        this.buyStock(ticker, d, count, commission, BuyType.COUNT);
      }
    }
  }

  @Override
  public void buyStock(String stockName, Date date, double value,
                       double commission, BuyType buyType) {
    Stock stockObj;
    if (stocksInPortfolio.containsKey(stockName)) {
      stockObj = stocksInPortfolio.get(stockName);

    } else {
      stockObj = new Stock(stockName);
      stocksInPortfolio.put(stockName, stockObj);
    }

    if (buyType == BuyType.COUNT) {
      stockObj.addStocks(date, value, commission);
    } else if (buyType == BuyType.AMOUNT) {
      stockObj.addStocksByAmount(date, value, commission);
    } else {
      throw new IllegalArgumentException("Enter a valid buy type");
    }
  }


  @Override
  public void weightedInvestmentEqual(Date date, double value, double commission) {
    int count = stocksInPortfolio.size();
    value /= count;
    for (Stock stockObj : stocksInPortfolio.values()) {
      stockObj.addStocksByAmount(date, value, commission);
    }
  }

  @Override
  public void weightedInvestmentUnequal(HashMap<String, Double> stockWeights, double value,
                                        Date date, double commission) {
    for (String stockName : stockWeights.keySet()) {
      this.buyStock(stockName, date, value *
              (stockWeights.get(stockName)), commission, BuyType.AMOUNT);
    }
  }


  @Override
  public double getPortfolioValuation(String stockName, Date date) {
    PortfolioImpl p = this.executeAllStrategies();
    if (stockName == null) {
      return p.stocksInPortfolio.values().stream().mapToDouble(a -> a.getValue(date)).sum();
    }
    if (p.stocksInPortfolio.containsKey(stockName)) {
      return p.stocksInPortfolio.get(stockName).getValue(date);
    } else {
      throw new IllegalArgumentException("This stock is not present in this portfolio.\n");
    }

  }

  @Override
  public double getAmountInvested(String stockName, Date date) {
    PortfolioImpl p;
    if (!this.portfolioId.equals("")) {
      p = this.executeAllStrategies();
    } else {
      p = this;
    }
    if (stockName == null) {
      return p.stocksInPortfolio.values().stream().mapToDouble(a -> a.getCostBasis(date)).sum();
    }
    stockName = stockName.toUpperCase();
    if (p.stocksInPortfolio.containsKey(stockName)) {
      return p.stocksInPortfolio.get(stockName).getCostBasis(date);
    } else {
      throw new IllegalArgumentException("This stock is not present in this portfolio.\n");
    }
  }


  @Override
  public HashMap getByDate(Date date) {
    try {
      HashMap<String, Object> data = new HashMap<>();
      Portfolio p = this.executeAllStrategies();
      ArrayList<HashMap<String, Object>> stockData = new ArrayList<>();
      for (Stock s : ((PortfolioImpl) p).stocksInPortfolio.values()) {
        stockData.add(s.getByDate(date));
      }
      data.put("Name", this.portfolioId);
      data.put("StockData", new ArrayList<>(stockData));
      data.put("CostBasis", String.valueOf(p.getAmountInvested(null, date)));
      data.put("Valuation", String.valueOf(p.getPortfolioValuation(null, date)));
      return data;
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }


  @Override
  public String toString() {
    PortfolioImpl p = this.executeAllStrategies();
    StringBuilder sb = new StringBuilder();
    sb.append("PORTFOLIO NAME: " + this.portfolioId + "\n");
    for (Stock s : p.stocksInPortfolio.values()) {
      sb.append(s);
      sb.append("---------------PORTFOLIO END---------------------------\n\n");
    }
    return sb.toString();
  }

  @Override
  public void createUnequalStrategy(HashMap<String, Double> weights, Date startDate, Date endDate,
                                    int days, double amount, double commission) {
    countStrat += 1;
    this.strategies.put(String.valueOf(countStrat + 1),
            new UnequalStrategy(weights, startDate, endDate,
                    days, amount, commission));
  }

  @Override
  public void createEqualStrategy(Date startDate, Date endDate,
                                  int days, double amount, double commission) {
    countStrat += 1;
    this.strategies.put(String.valueOf(countStrat + 1), new EqualStrategy(startDate, endDate, days,
            amount, commission));
  }

  @Override
  public void saveToFile(String fname) {
    File file = new File(fname + ".txt");
    try {
      file.createNewFile();
      FileWriter fwrite = new FileWriter(file);
      fwrite.write("START OF STOCKS\n");
      for (Stock s : this.stocksInPortfolio.values()) {
        s.saveToFile(fwrite);
      }
      fwrite.write("END OF STOCKS\n");
      fwrite.write("START OF STRATEGIES\n");
      for (Strategy s : this.strategies.values()) {
        fwrite.write("-STRATEGY\n");
        s.saveToFile(fwrite);
      }
      fwrite.write("END OF STRATEGIES\n");
      fwrite.write("END OF PORTFOLIO");
      fwrite.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("PARSING ERROR.");
    }
  }

  @Override
  public void saveEqualStrategy(Date startDate, Date endDate, int days, double amount,
                                double commission, String fname) {
    Strategy s = new EqualStrategy(startDate, endDate, days, amount, commission);
    File file = new File(fname + ".txt");
    try {
      FileWriter fwrite = new FileWriter(file);
      s.saveToFile(fwrite);
      fwrite.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveUnEqualStrategy(HashMap<String, Double> weights, Date startDate, Date endDate,
                                  int days, double amount, double commission, String fname) {
    Strategy s = new UnequalStrategy(weights, startDate, endDate, days, amount, commission);
    File file = new File(fname + ".txt");
    try {
      FileWriter fwrite = new FileWriter(file);
      s.saveToFile(fwrite);
      fwrite.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This is a helper function used to execute all the strategies stored in a portfolio. It returns
   * a portfolio object containing the transactions happened on running the strategies.
   */
  private PortfolioImpl executeAllStrategies() {
    PortfolioImpl p = new PortfolioImpl("Shadow");
    if (!this.stocksInPortfolio.isEmpty()) {
      p.combine(this.stocksInPortfolio);
    }
    for (Strategy i : this.strategies.values()) {
      p.combine(i.execute().stocksInPortfolio);
    }
    return p;
  }

  /**
   * This is a helper function used to add days to a date object and return a date object with the
   * updated date.
   */
  private Date addDays(Date startDate, int days) {
    Calendar c = new GregorianCalendar();
    c.setTime(startDate);
    c.add(Calendar.DATE, days);
    return c.getTime();
  }

  /**
   * This is a helper function used to combine two hashmaps for a portfolio. The hashmaps contain
   * the stock data. It is a helper function which is used to combine two portfolios and is not
   * accessible to the controller.
   */
  private void combine(Map<String, Stock> stocksInPortfolio) {
    for (String i : stocksInPortfolio.keySet()) {
      i = i.toUpperCase();
      if (this.stocksInPortfolio.containsKey(i)) {
        this.stocksInPortfolio.get(i).combine(stocksInPortfolio.get(i));
      } else {
        this.stocksInPortfolio.put(i, new Stock(stocksInPortfolio.get(i)));
      }
    }
  }

  @Override
  public void loadStrategy(Scanner sc) {
    try {
      String[] type = sc.nextLine().split(":");
      switch (type[1].trim()) {
        case "EQUAL":
          this.loadEqual(sc);
          break;
        case "UNEQUAL":
          this.loadUnequal(sc);
          break;
        default:
          break;
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("SWITCH PARSING ERROR");
    }
  }

  @Override
  public void performanceGraph(Date sDate, Date eDate, String fname) {
    if (sDate.after(eDate)) {
      throw new IllegalArgumentException("DATES MISMATCHED");
    }
  }

  /**
   * This is a helper function used to fetch data for an equal strategy object from the contents
   * stored in a file.
   */
  private void loadEqual(Scanner sc) {
    Date startDate = getDate(sc);
    Date endDate = getDate(sc);
    int days = getDays(sc);
    Double amount = getAmount(sc);
    Double commission = getAmount(sc);
    this.createEqualStrategy(startDate, endDate, days, amount, commission);
    if (sc.nextLine().equals("END OF STRATEGY")) {
      return;
    } else {
      throw new IllegalArgumentException("PARSING ERROR.");
    }
  }

  /**
   * This is a helper function used to fetch data for an unequal strategy object from the contents
   * stored in a file.
   */
  private void loadUnequal(Scanner sc) {
    Date startDate = getDate(sc);
    Date endDate = getDate(sc);
    int days = getDays(sc);
    double amount = getAmount(sc);
    double commission = getAmount(sc);
    HashMap<String, Double> weights = this.getWeights(sc);
    this.createUnequalStrategy(weights, startDate, endDate, days, amount, commission);
    if (sc.nextLine().equals("END OF STRATEGY")) {
      return;
    } else {
      throw new IllegalArgumentException("PARSING ERROR.");
    }
  }

  /**
   * This is a helper function used to fetch (Ticker,Weight) from the file.
   */
  private HashMap<String, Double> getWeights(Scanner sc) {
    HashMap<String, Double> weights = new HashMap<>();
    for (String a : sc.nextLine().split(":")[1].split(";")) {
      if (a.contains(",")) {
        String[] tokens = a.split(",");
        weights.put(tokens[0], Double.parseDouble(tokens[1]));
      } else {
        break;
      }
    }
    return weights;
  }

  /**
   * This is a helper function used to fetch double amount from a file.
   */
  private Double getAmount(Scanner sc) {
    return Double.parseDouble(sc.nextLine().split(":")[1].trim());
  }

  /**
   * This helper function is used to fetch int amount from the file.
   */
  private int getDays(Scanner sc) {
    return Integer.parseInt(sc.nextLine().split(":")[1].trim());
  }

  /**
   * This helper function is used to fetch Date object from the file.
   */
  private Date getDate(Scanner sc) {
    return UserOperationsImpl.helperGetDate(sc.nextLine().split(":")[1].trim());
  }

  /**
   * This is an inner class which extends the Strategy interface to create strategies which involve
   * unequally distributed investments inside a portfolio for the specified stocks along with their
   * respective weights.
   */
  private class UnequalStrategy implements Strategy {
    HashMap<String, Double> weights;
    Date startDate;
    Date endDate;
    int days;
    double amount;
    double commission;

    /**
     * This is a constructor which initializes the attributes of an unequal strategy object. It
     * takes a startDate and an endDate, followed by the number of days for which the function
     * recurred for. A double value containing the amount and the commission comes next.
     *
     * @param startDate  a valid date object
     * @param endDate    a valid date object
     * @param days       integer number of days
     * @param amount     amount in dollars
     * @param commission commission amount in dollars
     */
    public UnequalStrategy(HashMap<String, Double> weights, Date startDate, Date endDate, int days,
                           double amount, double commission) {
      this.weights = weights;
      this.startDate = startDate;
      this.endDate = endDate;
      this.days = days;
      this.amount = amount;
      this.commission = commission;

    }

    @Override
    public PortfolioImpl execute() {
      PortfolioImpl strategy = new PortfolioImpl("strategy");
      Calendar n = Calendar.getInstance();
      n.setTime(startDate);
      Date startDate1 = n.getTime();
      n.setTime(endDate);
      Date endDate1 = n.getTime();
      while (!startDate1.after(endDate1)) {
        try {
          for (String i : weights.keySet()) {
            strategy.buyStock(i, startDate1, this.amount * (weights.get(i) / 100),
                    commission, BuyType.AMOUNT);
          }
          startDate1 = PortfolioImpl.this.addDays(startDate1, days);
        } catch (Exception e) {
          if (e.getMessage().equals("Market was closed on " + startDate1.toString() + ".\n")) {
            startDate1 = PortfolioImpl.this.addDays(startDate1, 1);
          }
        }
      }
      return strategy;
    }

    @Override
    public void saveToFile(FileWriter fwrite) throws IOException {
      fwrite.write("TYPE: UNEQUAL" + "\n");
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      fwrite.write("START DATE: " + dateFormat.format(this.startDate) + "\n");
      fwrite.write("END DATE: " + dateFormat.format(endDate) + "\n");
      fwrite.write("DAYS: " + days + "\n");
      fwrite.write("AMOUNT: " + this.amount + "\n");
      fwrite.write("COMMISSION: " + this.commission + "\n");
      fwrite.write("WEIGHTS: ");
      for (String a : this.weights.keySet()) {
        fwrite.write(a.toUpperCase() + "," + this.weights.get(a) + ";");
      }
      fwrite.write("\nEND OF STRATEGY\n");
    }
  }

  /**
   * This is an inner class which extends the Strategy interface to create strategies which involve
   * equally distributed investments inside a portfolio for the implied stocks.
   */
  private class EqualStrategy implements Strategy {
    Date startDate;
    Date endDate;
    int days;
    double amount;
    double commission;

    /**
     * This is a constructor which initializes the attributes of an equal strategy object. It takes
     * a startDate and an endDate, followed by the number of days for which the function recurred
     * for. A double value containing the amount and the commission comes next.
     *
     * @param startDate  a valid date object
     * @param endDate    a valid date object
     * @param days       integer number of days
     * @param amount     amount in dollars
     * @param commission commission amount in dollars
     */
    public EqualStrategy(Date startDate, Date endDate, int days, double amount, double commission) {
      this.startDate = startDate;
      this.endDate = endDate;
      this.days = days;
      this.amount = amount;
      this.commission = commission;
    }

    @Override
    public PortfolioImpl execute() {
      PortfolioImpl strategy = new PortfolioImpl("strategy");
      HashMap<String, Date> dates = new HashMap<>();
      for (String i : PortfolioImpl.this.stocksInPortfolio.keySet()) {
        dates.put(i, PortfolioImpl.this.stocksInPortfolio.get(i).getFirstBoughtDate());
      }
      Calendar n = Calendar.getInstance();
      n.setTime(startDate);
      Date startDate1 = n.getTime();
      n.setTime(endDate);
      Date endDate1 = n.getTime();
      while (!startDate1.after(endDate1)) {
        List<String> stocksAsOnDate = dates.keySet().stream()
                .filter(a -> !dates.get(a).after(startDate))
                .collect(Collectors.toList());
        try {
          for (String a : stocksAsOnDate) {
            strategy.buyStock(a, startDate1, this.amount / stocksAsOnDate.size(),
                    this.commission, BuyType.AMOUNT);
          }
          startDate1 = PortfolioImpl.this.addDays(startDate1, this.days);
        } catch (Exception e) {
          if (e.getMessage().equals("Market was closed on " + startDate1.toString() + ".\n")) {
            startDate1 = PortfolioImpl.this.addDays(startDate1, 1);
          }
        }
      }
      return strategy;
    }

    @Override
    public void saveToFile(FileWriter fwrite) throws IOException {
      fwrite.write("TYPE: EQUAL" + "\n");
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      fwrite.write("START DATE: " + dateFormat.format(this.startDate) + "\n");
      fwrite.write("END DATE: " + dateFormat.format(endDate) + "\n");
      fwrite.write("DAYS: " + days);
      fwrite.write("\nAMOUNT: " + Double.valueOf(this.amount) + "\n");
      fwrite.write("COMMISSION: " + Double.valueOf(this.commission) + "\n");
      fwrite.write("END OF STRATEGY\n");
    }

  }


}

