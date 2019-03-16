package portfolio.view;

import java.io.IOException;

/**
 * The class that implements the {@link PortfolioManagerView} interface. It
 */
public class PortfolioManagerViewImpl implements PortfolioManagerView {
  private Readable input;
  private Appendable output;

  /**
   * A constructor to pass to the PortfolioManagerViewImpl class a valid input and output source
   * that can be set in the view so as to allow the controller to read any input and return any
   * output as needed.
   */
  public PortfolioManagerViewImpl(Readable input, Appendable output) {
    this.input = input;
    this.output = output;
  }

  @Override
  public Readable getInput() {
    return this.input;
  }

  @Override
  public void putOutput(String message) {
    try {
      output.append(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
