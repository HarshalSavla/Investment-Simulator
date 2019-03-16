package portfolio.model;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * This class contains the data of the log of the stocks that are bought and added to a particular
 * portfolio. You can get the total count of the stocks that bought, get the cost at which they were
 * bought on a particular date, get the value what the stocks might have on a particular date, add
 * log of the stocks that are bought.
 */
class Stock {

  private String tickerSymbol;
  private SortedMap<Date, Double> dateCountBuy;
  private SortedMap<Date, Double> dateCostBuy;
  private SortedMap<Date, Double> dateCommission;

  /**
   * This constructor initliazes the stock object which is bought by the user and set's it's unique
   * ticker name. It also initializes two SortedMaps which contain the logs of the costbasis for the
   * bought stock on a particular date as well the count of the stock bought on the particular
   * date.
   */
  Stock(String tickerName) {
    this.tickerSymbol = tickerName;
    this.dateCountBuy = new TreeMap<>();
    this.dateCostBuy = new TreeMap<>();
    this.dateCommission = new TreeMap<>();
    //    this.dateCountSell = new TreeMap<>();
    //    this.dateCostSell = new TreeMap<>();
  }

  /**
   * This is copy constructor used to create a stock object based on the contents of another stock
   * object.
   */
  Stock(Stock other) {
    this.tickerSymbol = other.tickerSymbol;
    this.dateCommission = new TreeMap<>(other.dateCommission);
    this.dateCostBuy = new TreeMap<>(other.dateCostBuy);
    this.dateCountBuy = new TreeMap<>(other.dateCountBuy);
  }

  /**
   * This function returns the total count of the quantity of the stocks it has.
   *
   * @return long value containting the total count of the stocks.
   */
  double getCount() {
    return this.dateCountBuy.values().stream().flatMapToDouble(a -> DoubleStream.of(a)).sum();
  }

  /**
   * This function returns the costBasis at which a particular Stock was bought for.
   *
   * @param d Date at which the stock was bought at.
   * @return double value of the cost basis of the cost.
   */
  double getCostBasis(Date d) {
    return this.dateCostBuy.keySet().stream()
            .filter(a -> !a.after(d))
            .mapToDouble(x -> (this.dateCommission.get(x)) + (this.dateCostBuy.get(x) *
                    this.dateCountBuy.get(x))).sum();
  }

  /**
   * Returns the total value of stock based on the number of quantity it was held at and the value
   * it had as on a given valid date.
   *
   * @param d Date object at which the user wants to find the cost.
   * @return double value of the total value held by the stock.
   */
  double getValue(Date d) {
    Double stockValue = 0.0;
    try {
      stockValue = new Query(this.tickerSymbol).getValue(d);

    } catch (Exception e) {
      SortedMap<Date, PreProcessedData> data = new Query(this.tickerSymbol).getData();
      List<Date> listOfDatesBefore = data.keySet().stream().filter(a -> !a.after(d)).
              collect(Collectors.toList());
      Date exactDate = listOfDatesBefore.get(listOfDatesBefore.size() - 1);
      stockValue = data.get(exactDate).data.get("close");
    } finally {
      return this.dateCountBuy.keySet().stream()
              .filter(a -> !a.after(d))
              .mapToDouble(b -> this.dateCountBuy.get(b)).sum() * stockValue;
    }

  }

  /**
   * This function adds the quantity and the costbasis of the stock it was bought for on a
   * particular date to it's hashmap.
   *
   * @param d                Valid date D on which markets are open.
   * @param noOfSharesBought Quantity of the stock bought as on that day.
   * @param commission       Commission to be paid for the transaction.
   */
  void addStocks(Date d, double noOfSharesBought, double commission) {

    if (noOfSharesBought <= 0) {
      throw new IllegalArgumentException("Only positive non-Zero number of stocks allowed\n");
    }
    Double closingPrice = new Query(this.tickerSymbol).getValue(d);
    addEntryToStockTable(d, noOfSharesBought, commission, closingPrice);
    return;
  }

  /**
   * his function adds the quantity and the costbasis of the stock it was bought for on a particular
   * date to it's hashmap.
   *
   * @param d                  Valid Date D on which markets are open.
   * @param amountToBeInvested Amount to be invested in the stock.
   * @param commission         Commision to be paid for the transaction.
   */
  void addStocksByAmount(Date d, Double amountToBeInvested, double commission) {
    Double closingPrice = new Query(this.tickerSymbol).getValue(d);
    Double noOfSharesBought = 0.0;
    if (amountToBeInvested < commission) {
      throw new IllegalArgumentException("Commission fees exceeds the amount to be invested for "
              + this.tickerSymbol);
    }
    if (amountToBeInvested <= 0) {
      throw new IllegalArgumentException("Only positive non-Zero amount of money can be" +
              " invested.");
    }

    noOfSharesBought = (amountToBeInvested - commission) / closingPrice;
    addEntryToStockTable(d, noOfSharesBought, commission, closingPrice);
    return;
  }

  /**
   * This is a helper function used to combine two same stock objects. It's not accessible to the
   * controller.
   */
  private void addEntryToStockTable(Date d, double numOfStocks, double commission,
                                    double closingPrice) {
    if (this.dateCountBuy.containsKey(d)) {
      this.dateCountBuy.replace(d, this.dateCountBuy.get(d) + numOfStocks);
      this.dateCommission.replace(d, this.dateCommission.get(d) + commission);
    } else {
      this.dateCountBuy.put(d, numOfStocks);
      this.dateCostBuy.put(d, closingPrice);
      this.dateCommission.put(d, commission);
    }
    return;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n\nSTOCK TICKER NAME: " + this.tickerSymbol + "\n");
    sb.append("DATE\t\t\t\t||\t\t\tCOST BASIS\t\t\t||\t\t\tO. OF SHARES\n");
    for (Date d : this.dateCostBuy.keySet()) {
      sb.append(d.toString() + "\t||\t\t\t" + this.dateCostBuy.get(d) + "\t\t\t\t||\t\t\t" +
              this.dateCountBuy.get(d) + "\n");
    }
    sb.append("------------------------------------------------------------------------------\n\n");
    return sb.toString();
  }

  /**
   * Returns the string contents of the log stored in the hashmap upto a particular date passed by
   * the user.
   *
   * @param date Valid date object D
   * @return HashMap containing the transaction log for each stock along with other information.
   */
  HashMap getByDate(Date date) {
    HashMap<String, Object> data = new HashMap<>();
    DateFormat dateFormat = new SimpleDateFormat("yyyy MMMM dd, EEEE");
    data.put("Ticker", this.tickerSymbol);
    TreeMap<String, String[]> transacData = new TreeMap<>();
    for (Date d : this.dateCostBuy.keySet()) {
      if (!d.after(date)) {
        transacData.put(dateFormat.format(d), new String[]{this.dateCostBuy.get(d).toString(),
                this.dateCountBuy.get(d).toString()});
      }
    }
    data.put("TransactionData", transacData);
    data.put("CostBasis", String.valueOf(this.getCostBasis(date)));
    data.put("Valuation", String.valueOf(this.getValue(date)));
    return data;
  }

  /**
   * This is a helper function used to return the date on which the stock was first bought on.
   */
  Date getFirstBoughtDate() {
    return this.dateCostBuy.firstKey();
  }

  /**
   * This is a helper function used to combine two stock objects of the same ticker symbol.
   */
  void combine(Stock other) {
    for (Date d : other.dateCountBuy.keySet()) {
      if (this.dateCountBuy.containsKey(d)) {
        this.dateCountBuy.replace(d, this.dateCountBuy.get(d) + other.dateCountBuy.get(d));
        this.dateCommission.replace(d, this.dateCommission.get(d) + other.dateCommission.get(d));
      } else {
        this.dateCountBuy.put(d, other.dateCountBuy.get(d));
        this.dateCostBuy.put(d, other.dateCostBuy.get(d));
        this.dateCommission.put(d, other.dateCommission.get(d));
      }
    }
  }

  /**
   * This function is used to save contents to a file in a formatted manner.
   */
  void saveToFile(FileWriter fwrite) throws IOException {
    fwrite.write("-STOCK\n");
    fwrite.write(this.tickerSymbol + "\n");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    for (Date d : this.dateCountBuy.keySet()) {
      fwrite.write(dateFormat.format(d) + "," + this.dateCountBuy.get(d) + ","
              + this.dateCommission.get(d) + "\n");
    }
    fwrite.write("END OF STOCK\n");
    return;
  }
}
