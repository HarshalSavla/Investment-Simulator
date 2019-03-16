package portfolio.controller;

import java.util.Scanner;

import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerView;

import static portfolio.controller.PortfolioManagerController.isInputQuit;

/**
 * This class implements the {@link UserOperationsController} interface. The user can create a new
 * portfolio or load an existing portfolio to start buying stocks and analysing different parameters
 * based on the stock market fluctuation.
 */
public class CreatePortfolio implements UserOperationsController {
  private Scanner sc;
  private PortfolioManagerView view;

  /**
   * A package private constructor that takes in the view object containing links to input and
   * output sources as well as a scanner object to parse through the input.
   *
   * @param view the view object containing connections to input and output sources.
   * @param sc   a scanner object to parse through the input.
   */
  CreatePortfolio(PortfolioManagerView view, Scanner sc) {
    this.view = view;
    this.sc = sc;
  }

  @Override
  public boolean apply(UserOperations userModel) {
    String ch = getChoice();
    if (ch.equals("1")) {
      try {
        String portfolioName = getPortfolioName();
        userModel.createPortfolio(portfolioName);
        view.putOutput("Portfolio " + portfolioName + " successfully created\n");
      } catch (Exception e) {
        view.putOutput("Portfolio of same name already exists.\n");
      }
    } else if (ch.equals("2")) {
      view.putOutput("Enter file name of portfolio to be loaded:\n");
      String fileName = sc.next();
      String portfolioName = getPortfolioName();
      try {
        userModel.loadPortfolio(fileName, portfolioName);
        view.putOutput("Load successful. Portfolio " + portfolioName
                + " added to your portfolios.\n");
      } catch (Exception e) {
        view.putOutput("ERROR: " + e.getMessage());
      }
    }
    return true;
  }

  private String getPortfolioName() {
    view.putOutput("Enter a name for your portfolio.\n");
    String portfolioName = sc.next();
    if (isInputQuit(portfolioName)) {
      view.putOutput("Exiting operation.\n");
      System.exit(0);
    }
    return portfolioName;
  }

  private String getChoice() {
    view.putOutput("Would you like to:\n1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n");
    boolean checkValid = false;
    String ch = "";
    while (!checkValid) {
      ch = sc.next();
      if (!ch.equals("1") && !ch.equals("2")) {
        view.putOutput("Please enter 1 or 2.\n");
      } else {
        checkValid = true;
      }
    }
    return ch;
  }
}
