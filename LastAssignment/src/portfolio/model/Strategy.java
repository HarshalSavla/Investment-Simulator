package portfolio.model;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This interface provides the function to emmulate the behavior of investment strategies. An object
 * can be executed for now, and can be extended in future.
 */
public interface Strategy {

  /**
   * Executing the strategy results in a portfolio object which contains the stock objects and their
   * transaction logs for which the particular strategy is called for.
   */
  PortfolioImpl execute();

  /**
   * This function is used to save the contents of a Strategy to a file associated with the
   * FileWriter object.
   *
   * @param fwrite Containing the file object pointing to the respective file to write into.
   */
  void saveToFile(FileWriter fwrite) throws IOException;

}
