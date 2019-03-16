package portfolio.controller;

import java.util.Scanner;

import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerView;

import static portfolio.controller.PortfolioManagerController.isInputQuit;

/**
 * This class implements the {@link UserOperationsController} interface. The user can save a
 * portfolio they have created to a text file.
 */
class SavePortfolio implements UserOperationsController {
  private Scanner sc;
  private PortfolioManagerView view;

  /**
   * A package private constructor that takes in the view object containing links to input and
   * output sources as well as a scanner object to parse through the input.
   *
   * @param view the view object containing connections to input and output sources.
   * @param sc   a scanner object to parse through the input.
   */
  SavePortfolio(PortfolioManagerView view, Scanner sc) {
    this.view = view;
    this.sc = sc;
  }

  @Override
  public boolean apply(UserOperations userModel) {
    String fileName = getString("Enter file name you wish to save this portfolio under:\n");
    String portfolioName = getString("Enter a name for your portfolio:\n");
    try {
      view.putOutput("Saving the portfolio to text file " + fileName + ".txt\n");
      userModel.savePortfolio(portfolioName.trim(), fileName.trim());
      view.putOutput("Successfully saved portfolio to a text file.\n");
    } catch (Exception e) {
      view.putOutput("ERROR: " + e.getMessage());
    }
    return true;
  }

  private String getString(String s) {
    view.putOutput(s);
    boolean stringValid = false;
    String value = "";
    while (!stringValid) {
      value = sc.next();
      if (isInputQuit(value)) {
        view.putOutput("Exiting operation.\n");
        System.exit(0);
      }
      if (!value.isEmpty()) {
        stringValid = true;
      }
    }
    return value;
  }
}
