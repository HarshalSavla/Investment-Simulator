package portfolio.controller;

/**
 * The main controller interface that contains operations to manage communication between the view
 * {@link portfolio.view.PortfolioManagerView } and the model {@link
 * portfolio.model.UserOperations}.
 */
public interface IPortfolioManagerController {
  /**
   * This function handles the controllers main duties by showing the user a list of operations they
   * can perform while creating various stock portfolios and passing control to and from the model
   * and view.
   */
  void startInvesting();
}
