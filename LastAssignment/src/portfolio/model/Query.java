package portfolio.model;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import static portfolio.model.UserOperationsImpl.helperGetDate;

/**
 * This is a helper class which is used to run query on the dataset and fetch data of the respective
 * stock for which the query object is created for. It can also be used to get the closing value of
 * the stock for a particular date. It also returns the string containing a cleaned up processed
 * data for the query run.
 */
public class Query {
  final static private String API_KEY = "L0VX4LKISL2LV3JJ";
  private static SortedMap<String, TreeMap<Date, PreProcessedData>> queries = new TreeMap<>();
  private SortedMap<Date, PreProcessedData> data;

  /**
   * It initiazes the query object and runs a query on the database to fetch data. It cleans the
   * fetched data and created various processedData object and stores them in a hashmap sorted as
   * per the data value of the stock.
   */
  Query(String key) {
    key = key.toUpperCase();
    data = new TreeMap<>();
    InputStream in = null;
    URL url = null;

    if (queries.containsKey(key)) {
      this.data = queries.get(key);
    } else {
      try {
        url = new URL("https://www.alphavantage"
                + ".co/query?function=TIME_SERIES_DAILY"
                + "&outputsize=full"
                + "&symbol"
                + "=" + key.toUpperCase() + "&apikey=" + API_KEY + "&datatype=csv");
      } catch (MalformedURLException e) {
        throw new RuntimeException("the alphavantage API has either changed or "
                + "no longer works");
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
      }

      try {
        in = url.openStream();
        Scanner sc = new Scanner(in);
        sc.nextLine();
        while (sc.hasNextLine()) {
          String line = sc.nextLine();
          String[] data = line.split(",");
          this.data.put(helperGetDate(data[0]),
                  new PreProcessedData(data[1], data[2], data[3], data[4], data[5]));
        }
        queries.put(key, new TreeMap<>(this.data));
        in.close();
      } catch (Exception e) {
        throw new IllegalArgumentException("Stock Not Found: " + key + "\n");
      }
    }
  }

  /**
   * This is a helper function used to return the data stored in the query object.
   */
  SortedMap<Date, PreProcessedData> getData() {
    return this.data;
  }

  /**
   * This function returns the double closing value of the stock for a particular date unless the
   * market is closed.
   *
   * @param d valid date object
   */
  Double getValue(Date d) {
    try {
      return this.data.get(d).data.get("close");
    } catch (Exception e) {
      throw new IllegalArgumentException("Market was closed on " + d.toString() + ".\n");
    }
  }

  /**
   * Overrides the toString function so that the Query results are presented in a formatted manner.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("DATE\t\t\tOPEN\t\tHIGH\t\tLOW\t\tCLOSE\t\tVOLUME");
    sb.append("\n--------------------------------------------------" +
            "------------------------------------------------------\n");

    for (Date d : this.data.keySet()) {
      sb.append(new SimpleDateFormat("yyyy-MM-dd").format(d) + this.data.get(d) + "\n");
    }
    return sb.toString();
  }


}
