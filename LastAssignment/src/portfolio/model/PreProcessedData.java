package portfolio.model;

import java.util.Map;
import java.util.TreeMap;

/**
 * This is a helper class used to create processed data object which contain the various values
 * fetched from the data source in an accessible manner.
 */
public class PreProcessedData {
  Map<String, Double> data;

  /**
   * This constructor creates a simple treemap which is used to store th various values associated
   * with a stock in an accessible manner based upon the arguments passed by the Query object.
   */
  PreProcessedData(String open, String high, String low, String close, String volume) {
    data = new TreeMap();
    data.put("open", Double.parseDouble(open));
    data.put("high", Double.parseDouble(high));
    data.put("low", Double.parseDouble(low));
    data.put("close", Double.parseDouble(close));
    data.put("volume", Double.parseDouble(volume));
  }

  /**
   * This function overrides the toString function to return a string containing the contents of the
   * Stock on a particular date only in a formatted manner.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Double d : data.values()) {
      sb.append("\t\t" + String.format("%.2f", d));
    }
    return sb.toString();
  }
}