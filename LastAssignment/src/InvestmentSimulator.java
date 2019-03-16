import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.UIManager;

import portfolio.controller.PortfolioManagerController;
import portfolio.controller.PortfolioManagerControllerGraphic;
import portfolio.model.UserOperations;
import portfolio.model.UserOperationsImpl;
import portfolio.view.PortfolioManagerView;
import portfolio.view.PortfolioManagerViewGraphic;
import portfolio.view.PortfolioManagerViewImpl;


/**
 * Investment simulator class that makes up the JAR file.
 */
public class InvestmentSimulator {
  /**
   * This is the main function which is executed when the JAR file is run. It takes the model and
   * view and passes it to the controller and calls the startInvesting() function to run the
   * program.
   */
  public static void main(String[] args) {
    UserOperations userModel = new UserOperationsImpl();

    if (args.length == 2 && args[0].equals("-view")
            && (args[1].equals("console") || args[1].equals("gui"))) {
      switch (args[1]) {
        case "console":
          PortfolioManagerView userView = new PortfolioManagerViewImpl(
                  new InputStreamReader(System.in), System.out);
          new PortfolioManagerController(userModel, userView)
                  .startInvesting();
          break;
        case "gui":
          PortfolioManagerViewGraphic.setDefaultLookAndFeelDecorated(false);
          PortfolioManagerViewGraphic userView2 = new PortfolioManagerViewGraphic();
          userView2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          userView2.setVisible(true);
          new PortfolioManagerControllerGraphic(userModel, userView2)
                  .startInvesting();
          try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
          break;
        default:
          System.out.println("Incorrect arguments passed.");
          System.exit(-1);
      }
    } else {
      System.out.println("Incorrect arguments passed.\nShould be of the type -view " +
              "\"type-of-view\". The type of view should be either \"console\" or \"gui\"");
      System.exit(-1);
    }
  }
}
