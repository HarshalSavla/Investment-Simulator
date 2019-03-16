package portfolio.controller;

import java.util.List;
import java.util.Scanner;

import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerView;

/**
 * This class implements the {@link UserOperationsController} interface. The user can view all the
 * stock portfolios they have created.
 */
class ViewAllPortfolios implements UserOperationsController {
  private PortfolioManagerView view;

  /**
   * A package private constructor that takes in the view object containing links to input and
   * output sources as well as a scanner object to parse through the input.
   *
   * @param view the view object containing connections to input and output sources.
   * @param sc   a scanner object to parse through the input.
   */
  ViewAllPortfolios(PortfolioManagerView view, Scanner sc) {
    this.view = view;
  }

  @Override
  public boolean apply(UserOperations userModel) {
    try {
      List<String> portfolioList = userModel.viewAllPortfolios();
      for (String portfolio : portfolioList) {
        view.putOutput(portfolio + "\n");
      }
    } catch (Exception e) {
      view.putOutput("ERROR: " + e.getMessage());
    }
    return true;
  }
}
