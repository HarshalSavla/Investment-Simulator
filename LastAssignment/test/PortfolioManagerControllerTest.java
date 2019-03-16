import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import portfolio.controller.IPortfolioManagerController;
import portfolio.controller.PortfolioManagerController;
import portfolio.model.UserOperations;
import portfolio.view.PortfolioManagerView;
import portfolio.view.PortfolioManagerViewImpl;

import static org.junit.Assert.assertEquals;

public class PortfolioManagerControllerTest {
  private UserOperations model;
  private PortfolioManagerView view;
  private StringBuilder log;
  private Reader in;
  private StringBuffer out;

  @Before
  public void setUp() {
    log = new StringBuilder();
    model = new MockUserOperationsImpl(log);
    out = new StringBuffer();
  }

  @Test
  public void testCreatePortfolio() {
    in = new StringReader("1 1 1 xyz q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testDisplayPortfolio() {
    in = new StringReader("1 1 xyz 2 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to view all portfolios.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Model: Getting list of portfolios.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testAddStock() {
    in = new StringReader("1 1 xyz 6 xyz GOOGL 1 3 2011-01-25 12:00:00 3 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to add 3.0 no. of shares belonging to GOOGL to xyz portfolio " +
            "with a commission fee of 3.0on date Tue Jan 25 15:59:59 EST 2011.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of GOOGL\n" +
            "2. Enter amount to be invested in buying stocks of GOOGL\n" +
            "Enter the number of stocks of type GOOGL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock GOOGL successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void addStockInvalidValue() {
    in = new StringReader("1 1 xyz 6 xy GOOGL 1 3 2011-01-25 12:00:00 3 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to add 3.0 no. of shares belonging to GOOGL to xy portfolio with " +
            "a commission fee of 3.0on date Tue Jan 25 15:59:59 EST 2011.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of GOOGL\n" +
            "2. Enter amount to be invested in buying stocks of GOOGL\n" +
            "Enter the number of stocks of type GOOGL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock GOOGL successfully added to Portfolio xy\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testQInBetween() {
    in = new StringReader("1 1 xyz 3 xyz q 1 1 xyzw");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Exiting operation.\n", out.toString());
  }

  @Test
  public void addStockInvalidTickerName() {
    in = new StringReader("1 1 xyz 6 xyz TGTT 1 3 2011-01-25 12:00:00 3 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to add 3.0 no. of shares belonging to TGTT to xyz portfolio with" +
            " a commission fee of 3.0on date Tue Jan 25 15:59:59 EST 2011.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of TGTT\n" +
            "2. Enter amount to be invested in buying stocks of TGTT\n" +
            "Enter the number of stocks of type TGTT:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock TGTT successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void addStockInvalidShareCount() {
    in = new StringReader("1 1 xyz 6 xyz GOOGL 1 -4 5 2011-01-25 12:00:00 3 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to add 5.0 no. of shares belonging to GOOGL to xyz portfolio with " +
            "a commission fee of 3.0on date Tue Jan 25 15:59:59 EST 2011.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of GOOGL\n" +
            "2. Enter amount to be invested in buying stocks of GOOGL\n" +
            "Enter the number of stocks of type GOOGL:\n" +
            "ERROR: You need to buy at least one stock.\n" +
            "Enter the number of stocks of type GOOGL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock GOOGL successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void addStockInvalidDate() {
    in = new StringReader("1 1 xyz 6 xyz GOOGL 1 3 2011-20-10 12:00:00 3 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to add 3.0 no. of shares belonging to GOOGL to xyz portfolio" +
            " with a commission fee of 3.0on date Fri Aug 10 15:59:59 EDT 2012.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of GOOGL\n" +
            "2. Enter amount to be invested in buying stocks of GOOGL\n" +
            "Enter the number of stocks of type GOOGL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock GOOGL successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void addStockInvalidTime() {
    in = new StringReader("1 1 xyz 6 xyz GOOGL 1 3 2011-01-25 24:00:00 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of GOOGL\n" +
            "2. Enter amount to be invested in buying stocks of GOOGL\n" +
            "Enter the number of stocks of type GOOGL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "The markets are closed at this time. You cannot buy stocks.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testGetStockDataInvalid() {
    in = new StringReader("11 GOO q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Searching for ticker symbol: GOO.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the stock ticker you want information about:\n" +
            "Model: Searching for stock data.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testViewAllPortfolios() {
    in = new StringReader("1 1 xyz 1 1 abc 2 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to create a portfolio.\n" +
            "Calling model to view all portfolios.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio abc successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Model: Getting list of portfolios.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testViewAllPortfoliosInvalid() {
    in = new StringReader("1 1 xyz 1 1 abc 1 1 xyz 2 1 1 pqr 2 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to create a portfolio.\n" +
            "Calling model to create a portfolio.\n" +
            "Calling model to view all portfolios.\n" +
            "Calling model to create a portfolio.\n" +
            "Calling model to view all portfolios.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio abc successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Model: Getting list of portfolios.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio pqr successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Model: Getting list of portfolios.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testAddMultipleStocksViewAtEnd() {
    in = new StringReader("1 1 xyz 2 6 xyz AAPL 1 2 2013-05-08 09:00:00 " +
            "6 xyz TGT 1 10 2013-05-10" +
            " 12:00:00 3 6 xyz GOOGL 1 20 2015-01-28 14:00:00 " +
            "3 xyz 2017-08-08 4 xyz 2017-08-08 N " +
            "5 xyz 2017-08-08 N Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
                    "Calling model to view all portfolios.\n" +
                    "Calling model to add 2.0 no. of shares belonging to AAPL to xyz portfolio" +
                    " with a commission fee of 6.0on date Wed May 08 15:59:59 EDT 2013.\n" +
                    "Calling model to create a portfolio.\n" +
                    "Calling model to view portfolio xyz on date Tue Aug 08 15:59:59 EDT 2017.\n" +
                    "Calling model to get net value of stocknull in portfolio xyz on Tue Aug" +
                    " 08 15:59:59 EDT 2017.\n" +
                    "Calling model to calculate cost basis of stock null in portfolio xyzon" +
                    " dateTue Aug 08 15:59:59 EDT 2017.\n",
            log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Model: Getting list of portfolios.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of AAPL\n" +
            "2. Enter amount to be invested in buying stocks of AAPL\n" +
            "Enter the number of stocks of type AAPL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock AAPL successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio 20 successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to find valuation of:\n" +
            "What date do you wish to view this valuation for? (Format: YYYY-MM-DD)\n" +
            "Do you wish to view valuation of a particular stock? Enter Y or N.\n" +
            "Amount invested in Portfolio is 10.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name whose cost basis you wish to find:\n" +
            "What date do you wish to check cost basis of portfolio on? (Format: YYYY-MM-DD)\n" +
            "Do you wish to calculate cost basis of a particular stock? Enter Y or N.\n" +
            "Cost basis of Portfolio is 20.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testAddStocksOfSameTypeViewAtEnd() {
    in = new StringReader("1 1 xyz 2 6 xyz AAPL 1 2 2013-05-08 09:00:00 " +
            "6 xyz AAPL 1 10 2013-05-15 12:00:00 3 " +
            "6 xyz AAPL 1 20 2015-01-28 14:00:00 3 " +
            "3 xyz 2017-08-08 4 xyz 2017-08-08 N" +
            " 5 xyz 2017-08-08 N Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
                    "Calling model to view all portfolios.\n" +
                    "Calling model to add 2.0 no. of shares belonging to AAPL to xyz portfolio" +
                    " with a commission fee of 6.0on date Wed May 08 15:59:59 EDT 2013.\n" +
                    "Calling model to create a portfolio.\n" +
                    "Calling model to view portfolio 3 on date Tue Aug 08 15:59:59 EDT 2017.\n" +
                    "Calling model to get net value of stocknull in portfolio xyz on Tue" +
                    " Aug 08 15:59:59 EDT 2017.\n" +
                    "Calling model to calculate cost basis of stock null in portfolio" +
                    " xyzon dateTue Aug 08 15:59:59 EDT 2017.\n",
            log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Model: Getting list of portfolios.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of AAPL\n" +
            "2. Enter amount to be invested in buying stocks of AAPL\n" +
            "Enter the number of stocks of type AAPL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock AAPL successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio 20 successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "ERROR: Please re-enter date according to the correct format: YYYY-MM-DD.\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to find valuation of:\n" +
            "What date do you wish to view this valuation for? (Format: YYYY-MM-DD)\n" +
            "Do you wish to view valuation of a particular stock? Enter Y or N.\n" +
            "Amount invested in Portfolio is 10.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name whose cost basis you wish to find:\n" +
            "What date do you wish to check cost basis of portfolio on? (Format: YYYY-MM-DD)\n" +
            "Do you wish to calculate cost basis of a particular stock? Enter Y or N.\n" +
            "Cost basis of Portfolio is 20.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testAddSameStockViewInMiddle() {
    in = new StringReader("1 1 xyz 2 6 xyz AAPL 1 2 2013-05-08 " +
            "09:00:00 6 xyz AAPL 1 10 2013-05-15" +
            " 12:00:00 3 6 xyz AAPL 1 20 2015-01-28 14:00:00 3 " +
            "3 xyz 2014-08-08 4 xyz 2014-08-08 N" +
            " 5 xyz 2014-08-08 N Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
                    "Calling model to view all portfolios.\n" +
                    "Calling model to add 2.0 no. of shares belonging to AAPL to xyz portfolio " +
                    "with a commission fee of 6.0on date Wed May 08 15:59:59 EDT 2013.\n" +
                    "Calling model to create a portfolio.\n" +
                    "Calling model to view portfolio 3 on date Fri Aug 08 15:59:59 EDT 2014.\n" +
                    "Calling model to get net value of stocknull in portfolio xyz on Fri Aug 08" +
                    " 15:59:59 EDT 2014.\n" +
                    "Calling model to calculate cost basis of stock null in portfolio xyzon " +
                    "dateFri Aug 08 15:59:59 EDT 2014.\n",
            log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Model: Getting list of portfolios.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of AAPL\n" +
            "2. Enter amount to be invested in buying stocks of AAPL\n" +
            "Enter the number of stocks of type AAPL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock AAPL successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio 20 successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "ERROR: Please re-enter date according to the correct format: YYYY-MM-DD.\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to find valuation of:\n" +
            "What date do you wish to view this valuation for? (Format: YYYY-MM-DD)\n" +
            "Do you wish to view valuation of a particular stock? Enter Y or N.\n" +
            "Amount invested in Portfolio is 10.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name whose cost basis you wish to find:\n" +
            "What date do you wish to check cost basis of portfolio on? (Format: YYYY-MM-DD)\n" +
            "Do you wish to calculate cost basis of a particular stock? Enter Y or N.\n" +
            "Cost basis of Portfolio is 20.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testAddDifferentStockViewInMiddle() {
    in = new StringReader("1 1 xyz " +
            "6 xyz AAPL 1 2 2013-05-08 09:00:00 " +
            "6 xyz GOOGL 1 20 2015-01-28 14:00:00 " +
            "6 xyz TGT 1 10 2013-05-15 12:00:00 3 " +
            "3 xyz 2014-08-08 " +
            "4 xyz 2014-08-08 N " +
            "5 xyz 2014-08-08 N Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
                    "Calling model to add 2.0 no. of shares belonging to AAPL to xyz portfolio" +
                    " with a commission fee of 6.0on date Wed May 08 15:59:59 EDT 2013.\n" +
                    "Calling model to create a portfolio.\n" +
                    "Calling model to view portfolio 3 on date Fri Aug 08 15:59:59 EDT 2014.\n" +
                    "Calling model to get net value of stocknull in portfolio xyz on Fri Aug" +
                    " 08 15:59:59 EDT 2014.\n" +
                    "Calling model to calculate cost basis of stock null in portfolio xyzon " +
                    "dateFri Aug 08 15:59:59 EDT 2014.\n",
            log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of AAPL\n" +
            "2. Enter amount to be invested in buying stocks of AAPL\n" +
            "Enter the number of stocks of type AAPL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock AAPL successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Please enter 1 or 2.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio 10 successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "ERROR: Please re-enter date according to the correct format: YYYY-MM-DD.\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to find valuation of:\n" +
            "What date do you wish to view this valuation for? (Format: YYYY-MM-DD)\n" +
            "Do you wish to view valuation of a particular stock? Enter Y or N.\n" +
            "Amount invested in Portfolio is 10.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name whose cost basis you wish to find:\n" +
            "What date do you wish to check cost basis of portfolio on? (Format: YYYY-MM-DD)\n" +
            "Do you wish to calculate cost basis of a particular stock? Enter Y or N.\n" +
            "Cost basis of Portfolio is 20.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testViewAllPortfoliosEmpty() {
    in = new StringReader("2  q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to view all portfolios.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Model: Getting list of portfolios.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testCostBasisNoStock() {
    in = new StringReader("1 1 xyz 5 xyz 2018-05-05 N Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
                    "Calling model to calculate cost basis of stock null in portfolio" +
                    " xyzon dateSat May 05 15:59:59 EDT 2018.\n",
            log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name whose cost basis you wish to find:\n" +
            "What date do you wish to check cost basis of portfolio on? (Format: YYYY-MM-DD)\n" +
            "Do you wish to calculate cost basis of a particular stock? Enter Y or N.\n" +
            "Cost basis of Portfolio is 20.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testCostBasisOneStock() {
    in = new StringReader("1 1 xyz 6 xyz TGT 1 3 2013-11-11 12:00:00 3 5 xyz 2018-05-05 N " +
            "3 xyz 2018-05-05 Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
                    "Calling model to add 3.0 no. of shares belonging to TGT " +
                    "to xyz portfolio with a " +
                    "commission fee of 3.0on date Mon Nov 11 15:59:59 EST 2013.\n" +
                    "Calling model to calculate cost basis of stock null" +
                    " in portfolio xyzon dateSat " +
                    "May 05 15:59:59 EDT 2018.\n" +
                    "Calling model to view portfolio xyz on date Sat May 05 15:59:59 EDT 2018.\n",
            log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of TGT\n" +
            "2. Enter amount to be invested in buying stocks of TGT\n" +
            "Enter the number of stocks of type TGT:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock TGT successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name whose cost basis you wish to find:\n" +
            "What date do you wish to check cost basis of portfolio on? (Format: YYYY-MM-DD)\n" +
            "Do you wish to calculate cost basis of a particular stock? Enter Y or N.\n" +
            "Cost basis of Portfolio is 20.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testValuationNoStock() {
    in = new StringReader("1 1 xyz 4 xyz 2018-05-05 N Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to get net value of stocknull in portfolio xyz on " +
            "Sat May 05 15:59:59 EDT 2018.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to find valuation of:\n" +
            "What date do you wish to view this valuation for? (Format: YYYY-MM-DD)\n" +
            "Do you wish to view valuation of a particular stock? Enter Y or N.\n" +
            "Amount invested in Portfolio is 10.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testValuationIncorrectInput() {
    in = new StringReader("1 1 xyz 6 xyz TGT 1 3 2013-11-11 12:00:00 3 " +
            "4 abc 2018-05-05 N 3 xyz 2018-05-05 Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
                    "Calling model to add 3.0 no. of shares belonging to TGT to xyz portfolio" +
                    " with a commission fee of 3.0on date Mon Nov 11 15:59:59 EST 2013.\n" +
                    "Calling model to get net value of stocknull in portfolio abc on Sat" +
                    " May 05 15:59:59 EDT 2018.\n" +
                    "Calling model to view portfolio xyz on date Sat May 05 15:59:59 EDT 2018.\n",
            log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of TGT\n" +
            "2. Enter amount to be invested in buying stocks of TGT\n" +
            "Enter the number of stocks of type TGT:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock TGT successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to find valuation of:\n" +
            "What date do you wish to view this valuation for? (Format: YYYY-MM-DD)\n" +
            "Do you wish to view valuation of a particular stock? Enter Y or N.\n" +
            "Amount invested in Portfolio is 10.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testValuationIncorrectInput2() {
    in = new StringReader("1 1 xyz 6 xyz TGT 1 3 2013-11-11 12:00:00 3 " +
            "4 xyz 2018-05-05 Y AAPL 3 xyz 2018-05-05 Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to add 3.0 no. of shares belonging to TGT to xyz portfolio" +
            " with a commission fee of 3.0on date Mon Nov 11 15:59:59 EST 2013.\n" +
            "Calling model to get net value of stockAAPL in portfolio xyz on Sat" +
            " May 05 15:59:59 EDT 2018.\n" +
            "Calling model to view portfolio xyz on date Sat May 05 15:59:59 EDT " +
            "2018.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of TGT\n" +
            "2. Enter amount to be invested in buying stocks of TGT\n" +
            "Enter the number of stocks of type TGT:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock TGT successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to find valuation of:\n" +
            "What date do you wish to view this valuation for? (Format: YYYY-MM-DD)\n" +
            "Do you wish to view valuation of a particular stock? Enter Y or N.\n" +
            "Enter stock ticker symbol:\n" +
            "Amount invested in Portfolio is 10.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testValuationIncorrectInput3() {
    in = new StringReader("1 1 xyz 6 xyz TGT 1 3 2013-11-11 12:00:00 3 " +
            "4 xyz 2018-05-05 P N 3 xyz 2018-05-05 Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to add 3.0 no. of shares belonging to TGT to xyz portfolio" +
            " with a commission fee of 3.0on date Mon Nov 11 15:59:59 EST 2013.\n" +
            "Calling model to get net value of stocknull in portfolio xyz on Sat May" +
            " 05 15:59:59 EDT 2018.\n" +
            "Calling model to view portfolio xyz on date Sat May 05 15:59:59 EDT 2018." +
            "\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of TGT\n" +
            "2. Enter amount to be invested in buying stocks of TGT\n" +
            "Enter the number of stocks of type TGT:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock TGT successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to find valuation of:\n" +
            "What date do you wish to view this valuation for? (Format: YYYY-MM-DD)\n" +
            "Do you wish to view valuation of a particular stock? Enter Y or N.\n" +
            "ERROR: Enter Y or N only.\n" +
            "Do you wish to view valuation of a particular stock? Enter Y or N.\n" +
            "Amount invested in Portfolio is 10.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testCostBasisIncorrectInput1() {
    in = new StringReader("1 1 xyz 6 xyz TGT 1 3 2013-11-11 12:00:00 3 " +
            "5 abc 2018-05-05 N 3 xyz 2018-05-05 Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
                    "Calling model to add 3.0 no. of shares belonging to TGT to xyz portfolio " +
                    "with a commission fee of 3.0on date Mon Nov 11 15:59:59 EST 2013.\n" +
                    "Calling model to calculate cost basis of stock null in portfolio abcon " +
                    "dateSat May 05 15:59:59 EDT 2018.\n" +
                    "Calling model to view portfolio xyz on date Sat May 05 15:59:59 EDT 2018.\n",
            log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of TGT\n" +
            "2. Enter amount to be invested in buying stocks of TGT\n" +
            "Enter the number of stocks of type TGT:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock TGT successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name whose cost basis you wish to find:\n" +
            "What date do you wish to check cost basis of portfolio on? (Format: YYYY-MM-DD)\n" +
            "Do you wish to calculate cost basis of a particular stock? Enter Y or N.\n" +
            "Cost basis of Portfolio is 20.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testCostBasisIncorrectInput2() {
    in = new StringReader("1 1 xyz 6 xyz TGT 1 3 2013-11-11 12:00:00 3 " +
            "5 xyz 2018-05-05 Y AAPL 3 xyz 2018-05-05 Q");

    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
                    "Calling model to add 3.0 no. of shares belonging to TGT to xyz portfolio " +
                    "with a commission fee of 3.0on date Mon Nov 11 15:59:59 EST 2013.\n" +
                    "Calling model to calculate cost basis of stock AAPL in portfolio xyzon " +
                    "dateSat May 05 15:59:59 EDT 2018.\n" +
                    "Calling model to view portfolio xyz on date Sat May 05 15:59:59 EDT 2018.\n",
            log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of TGT\n" +
            "2. Enter amount to be invested in buying stocks of TGT\n" +
            "Enter the number of stocks of type TGT:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock TGT successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name whose cost basis you wish to find:\n" +
            "What date do you wish to check cost basis of portfolio on? (Format: YYYY-MM-DD)\n" +
            "Do you wish to calculate cost basis of a particular stock? Enter Y or N.\n" +
            "Enter stock ticker symbol:\n" +
            "Cost basis of Portfolio is 20.0 for Stock AAPL\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testCostBasisIncorrectInput3() {
    in = new StringReader("1 1 xyz 6 xyz TGT 1 3 2013-11-11 12:00:00 3 " +
            "5 xyz 2018-05-05 P N 3 xyz 2018-05-05 Q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();
    assertEquals("Calling model to create a portfolio.\n" +
                    "Calling model to add 3.0 no. of shares belonging to TGT to xyz portfolio" +
                    " with a commission fee of 3.0on date Mon Nov 11 15:59:59 EST 2013.\n" +
                    "Calling model to calculate cost basis of stock null in portfolio xyzon " +
                    "dateSat May 05 15:59:59 EDT 2018.\n" +
                    "Calling model to view portfolio xyz on date Sat May 05 15:59:59 EDT 2018.\n",
            log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of TGT\n" +
            "2. Enter amount to be invested in buying stocks of TGT\n" +
            "Enter the number of stocks of type TGT:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock TGT successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name whose cost basis you wish to find:\n" +
            "What date do you wish to check cost basis of portfolio on? (Format: YYYY-MM-DD)\n" +
            "Do you wish to calculate cost basis of a particular stock? Enter Y or N.\n" +
            "ERROR: Enter Y or N only.\n" +
            "Do you wish to calculate cost basis of a particular stock? Enter Y or N.\n" +
            "Cost basis of Portfolio is 20.0\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testBuyUsingAmount() {
    in = new StringReader("1 1 xyz 6 xyz tgt 1 2 2000 2018-11-13 12:00:00 3 3" +
            " 3 xyz 2018-11-30 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
                    "Calling model to add 2.0 no. of shares belonging to tgt to xyz portfolio " +
                    "with a commission fee of 3.0on date Tue Nov 13 15:59:59 EST 2018.\n" +
                    "Calling model to view portfolio 3 on date Fri Nov 30 15:59:59 EST 2018.\n",
            log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of tgt\n" +
            "2. Enter amount to be invested in buying stocks of tgt\n" +
            "Enter the number of stocks of type tgt:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "ERROR: Please re-enter date according to the correct format: YYYY-MM-DD.\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock tgt successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "ERROR: Please re-enter date according to the correct format: YYYY-MM-DD.\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testBuyUsingAmountNegativeInput() {
    in = new StringReader("1 1 xyz 6 xyz tgt 2 -10 2000 2018-11-13 12:00:00 3 3" +
            " 3 xyz 2018-11-30 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to add 2000.0 dollars worth of shares belonging to tgt to xyz" +
            " portfolio with a commission fee of 3.0.\n" +
            "Calling model to view portfolio xyz.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of tgt\n" +
            "2. Enter amount to be invested in buying stocks of tgt\n" +
            "Enter the amount to be invested into stocks of type tgt:\n" +
            "ERROR: Stock is worth a positive amount of money.\n" +
            "Enter the amount to be invested into stocks of type tgt:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock tgt successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\nEnter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Model: Getting of portfolio\n" +
            "\nWelcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "", out.toString());
  }

  @Test
  public void testNonRecurringUnequalSplit() {
    in = new StringReader("1 1 xyz 7 xyz 2018-11-13 2000 2 N TGT 60 Y GOOGL 40 3 xyz" +
            " 2018-11-30 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to divide 2000.0 dollars worth of stocks in a ratio in xyz and buy" +
            " them with a commission of 2.0 for each transaction.\n" +
            "Ratio is: {TGT=60.0, GOOGL=40.0}\n" +
            "Calling model to view portfolio xyz.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stocks to:\n" +
            "What date do you wish to buy the stocks on? (Format: YYYY-MM-DD)\n" +
            "Enter the amount to be invested into stocks:\n" +
            "What commission is being charged to buy shares of each stock? (In dollars)\n" +
            "Do you want to split money equally amongst the stocks: (Enter Y or N)\n" +
            "Enter a ticker symbol:\n" +
            "Enter the percentage for TGT:\n" +
            "The current split is:\n" +
            "TGT: 60.0\n" +
            "Do you want to continue? Enter Y or N.\n" +
            "Enter a ticker symbol:\n" +
            "Enter the percentage for GOOGL:\n" +
            "The current split is:\n" +
            "TGT: 60.0\n" +
            "GOOGL: 40.0\n" +
            "Please wait while we add the stocks to the portfolio...\n" +
            "Successfully added the stocks to the portfolio.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Model: Getting of portfolio\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testNonRecurringEqualSplit() {
    in = new StringReader("1 1 xyz 6 xyz GOOGL 2 2000 2018-11-28 12:00:00 3 2 " +
            "6 xyz TGT 2 2000 2018-11-28 12:00:00 3 2" +
            "7 xyz 2018-11-13 2000 2 Y 3 xyz 2018-11-30 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to add 2000.0 dollars worth of shares belonging to GOOGL to xyz" +
            " portfolio with a commission fee of 2.0.\n" +
            "Calling model to add 2000.0 dollars worth of shares belonging to TGT to xyz" +
            " portfolio with a commission fee of 27.0.\n" +
            "Calling model to view all portfolios.\n" +
            "Calling model to view portfolio xyz.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of GOOGL\n" +
            "2. Enter amount to be invested in buying stocks of GOOGL\n" +
            "Enter the amount to be invested into stocks of type GOOGL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock GOOGL successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of TGT\n" +
            "2. Enter amount to be invested in buying stocks of TGT\n" +
            "Enter the amount to be invested into stocks of type TGT:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock TGT successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a valid option number.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Model: Getting list of portfolios.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "ERROR: Please select a number from the options provided.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Model: Getting of portfolio\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testNonRecurringCombo() {
    in = new StringReader("1 1 xyz 7 xyz 2018-11-13 2000 2 N TGT 60 Y GOOGL 40" +
            " 7 xyz 2018-11-14 2000 2 Y 3 xyz 2018-11-30 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to divide 2000.0 dollars worth of stocks in a ratio in xyz and buy " +
            "them with a commission of 2.0 for each transaction.\n" +
            "Ratio is: {TGT=60.0, GOOGL=40.0}\n" +
            "Calling model to divide 2000.0 dollars worth of stocks equally in xyz and buy them" +
            " with a commission of 2.0 for each transaction.\n" +
            "Calling model to view portfolio xyz.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stocks to:\n" +
            "What date do you wish to buy the stocks on? (Format: YYYY-MM-DD)\n" +
            "Enter the amount to be invested into stocks:\n" +
            "What commission is being charged to buy shares of each stock? (In dollars)\n" +
            "Do you want to split money equally amongst the stocks: (Enter Y or N)\n" +
            "Enter a ticker symbol:\n" +
            "Enter the percentage for TGT:\n" +
            "The current split is:\n" +
            "TGT: 60.0\n" +
            "Do you want to continue? Enter Y or N.\n" +
            "Enter a ticker symbol:\n" +
            "Enter the percentage for GOOGL:\n" +
            "The current split is:\n" +
            "TGT: 60.0\n" +
            "GOOGL: 40.0\n" +
            "Please wait while we add the stocks to the portfolio...\n" +
            "Successfully added the stocks to the portfolio.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stocks to:\n" +
            "What date do you wish to buy the stocks on? (Format: YYYY-MM-DD)\n" +
            "Enter the amount to be invested into stocks:\n" +
            "What commission is being charged to buy shares of each stock? (In dollars)\n" +
            "Do you want to split money equally amongst the stocks: (Enter Y or N)\n" +
            "Please wait while we add the stocks to the portfolio...\n" +
            "Successfully added the stocks to the portfolio.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Model: Getting of portfolio\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testRecurringUnequalSplit() {
    in = new StringReader("1 1 xyz 6 xyz GOOGL 2 2000 2018-11-28 12:00:00 3 2" +
            "6 xyz TGT 2 2000 2018-11-28 12:00:00 3 2" +
            "8 1 xyz 20000 2018-11-01 2018-11-15 7 N GOOGL 80 Y" +
            " TGT 20 2 3 xyz 2018-11-30 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to divide 20000.0 dollars worth of stocks in a ratio " +
            "{TGT=20.0, GOOGL=80.0} in xyz and buy them with a commission of 2.0 " +
            "for each transaction.\n" +
            "This is a recurring transaction occurring every 7 days from " +
            "Thu Nov 01 15:59:59 EDT 2018 to Thu Nov 15 15:59:59 EST 2018.\n" +
            "Calling model to view portfolio xyz.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stocks to:\n" +
            "Enter the amount to be invested into stocks:\n" +
            "What is the start date for this strategy? (Format: YYYY-MM-DD)\n" +
            "What is the end date for this strategy? (Format: YYYY-MM-DD)\n" +
            "How many days do you want between consecutive investments?\n" +
            "Do you want to split money equally amongst the stocks: (Enter Y or N)\n" +
            "Enter a ticker symbol:\n" +
            "Enter the percentage forGOOGL:\n" +
            "The current split is:\n" +
            "GOOGL: 80.0\n" +
            "Do you want to continue? Enter Y or N.\n" +
            "Enter a ticker symbol:\n" +
            "Enter the percentage forTGT:\n" +
            "The current split is:\n" +
            "TGT: 20.0\n" +
            "GOOGL: 80.0\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the strategy to the portfolio...\n" +
            "Successfully added the strategy to the portfolio.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Model: Getting of portfolio\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testRecurringEqualSplit() {
    in = new StringReader("1 1 xyz " +
            "6 xyz GOOGL 2 2000 2018-11-28 12:00:00 3 2 6 xyz TGT 2 2000 2018-11-28 12:00:00 3 2" +
            " 8 1 xyz 20000 2018-11-01 2018-11-15 7 Y 2 3 xyz 2018-11-30 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to add 2000.0 dollars worth of shares belonging to GOOGL " +
            "to xyz portfolio with a commission fee of 2.0.\n" +
            "Calling model to add 2000.0 dollars worth of shares belonging to TGT " +
            "to xyz portfolio with a commission fee of 2.0.\n" +
            "Calling model to divide 20000.0 dollars worth of stocks equally  in " +
            "xyz and buy them with a commission of 2.0 for each transaction.\n" +
            "This is a recurring transaction occurring every 7 days from " +
            "Thu Nov 01 15:59:59 EDT 2018 to Thu Nov 15 15:59:59 EST 2018.\n" +
            "Calling model to view portfolio xyz.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of GOOGL\n" +
            "2. Enter amount to be invested in buying stocks of GOOGL\n" +
            "Enter the amount to be invested into stocks of type GOOGL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock GOOGL successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of TGT\n" +
            "2. Enter amount to be invested in buying stocks of TGT\n" +
            "Enter the amount to be invested into stocks of type TGT:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock TGT successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stocks to:\n" +
            "Enter the amount to be invested into stocks:\n" +
            "What is the start date for this strategy? (Format: YYYY-MM-DD)\n" +
            "What is the end date for this strategy? (Format: YYYY-MM-DD)\n" +
            "How many days do you want between consecutive investments?\n" +
            "Do you want to split money equally amongst the stocks: (Enter Y or N)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the strategy to the portfolio...\n" +
            "Successfully added the strategy to the portfolio.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Model: Getting of portfolio\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testRecurringCombo() {
    in = new StringReader("1 1 xyz 8 1 xyz 20000 2018-11-01 2018-11-15 7 N GOOGL 80 Y" +
            " TGT 20 2 8 1 xyz 20000 2018-11-01 2018-11-15 7 Y 2 3 xyz 2018-11-30 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to divide 20000.0 dollars worth of stocks in a ratio " +
            "{TGT=20.0, GOOGL=80.0} in xyz and buy them with a commission of 2.0 " +
            "for each transaction.\n" +
            "This is a recurring transaction occurring every 7 days from " +
            "Thu Nov 01 15:59:59 EDT 2018 to Thu Nov 15 15:59:59 EST 2018.\n" +
            "Calling model to divide 20000.0 dollars worth of stocks equally  in xyz" +
            " and buy them with a commission of 2.0 for each transaction.\n" +
            "This is a recurring transaction occurring every 7 days from " +
            "Thu Nov 01 15:59:59 EDT 2018 to Thu Nov 15 15:59:59 EST 2018.\n" +
            "Calling model to view portfolio xyz.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stocks to:\n" +
            "Enter the amount to be invested into stocks:\n" +
            "What is the start date for this strategy? (Format: YYYY-MM-DD)\n" +
            "What is the end date for this strategy? (Format: YYYY-MM-DD)\n" +
            "How many days do you want between consecutive investments?\n" +
            "Do you want to split money equally amongst the stocks: (Enter Y or N)\n" +
            "Enter a ticker symbol:\n" +
            "Enter the percentage forGOOGL:\n" +
            "The current split is:\n" +
            "GOOGL: 80.0\n" +
            "Do you want to continue? Enter Y or N.\n" +
            "Enter a ticker symbol:\n" +
            "Enter the percentage forTGT:\n" +
            "The current split is:\n" +
            "TGT: 20.0\n" +
            "GOOGL: 80.0\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the strategy to the portfolio...\n" +
            "Successfully added the strategy to the portfolio.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stocks to:\n" +
            "Enter the amount to be invested into stocks:\n" +
            "What is the start date for this strategy? (Format: YYYY-MM-DD)\n" +
            "What is the end date for this strategy? (Format: YYYY-MM-DD)\n" +
            "How many days do you want between consecutive investments?\n" +
            "Do you want to split money equally amongst the stocks: (Enter Y or N)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the strategy to the portfolio...\n" +
            "Successfully added the strategy to the portfolio.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Model: Getting of portfolio\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create a new Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Buy a set of stocks as a recurring investment\n" +
            "9. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testRecurringEqualSplitStartBeforeEnd() {
    in = new StringReader("1 1 xyz 6 xyz GOOGL 1 2 2000 2018-11-28 12:00:00 3 2 " +
            "6 xyz TGT 1 2 2000 2018-11-28 12:00:00 3 2 " +
            "8 1 xyz 20000 2018-11-01 2018-10-05 2018-11-15 7 Y 2 " +
            "3 xyz 2018-11-30 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to add 2.0 no. of shares belonging to GOOGL to xyz portfolio" +
            " with a commission fee of 3.0on date Wed Nov 28 15:59:59 EST 2018.\n" +
            "Calling model to view all portfolios.\n" +
            "Calling model to add 2.0 no. of shares belonging to TGT to xyz portfolio with" +
            " a commission fee of 3.0on date Wed Nov 28 15:59:59 EST 2018.\n" +
            "Calling model to view all portfolios.\n" +
            "Calling model to divide 20000.0 dollars worth of stocks equally  in xyz" +
            " and buy them with a commission of 2.0 for each transaction.\n" +
            "This is a recurring transaction occurring every 7 days from Thu Nov 01 " +
            "15:59:59 EDT 2018 to Thu Nov 15 15:59:59 EST 2018.\n" +
            "Calling model to view portfolio xyz on date Fri Nov 30 15:59:59 EST " +
            "2018.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of GOOGL\n" +
            "2. Enter amount to be invested in buying stocks of GOOGL\n" +
            "Enter the number of stocks of type GOOGL:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "ERROR: Please re-enter date according to the correct format: YYYY-MM-DD.\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock GOOGL successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Model: Getting list of portfolios.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio name you want to add the stock to:\n" +
            "Enter the ticker symbol of the stock to be bought:\n" +
            "Pick an option from the following:\n" +
            "1. Enter number of stocks to be bought of TGT\n" +
            "2. Enter amount to be invested in buying stocks of TGT\n" +
            "Enter the number of stocks of type TGT:\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "ERROR: Please re-enter date according to the correct format: YYYY-MM-DD.\n" +
            "What date do you wish to buy this stock on? (Format: YYYY-MM-DD)\n" +
            "What time do you want to buy this stock at? (Format: HH:MM:SS)\n" +
            "What commission is being charged to buy this stock? (In dollars)\n" +
            "Please wait while we add the stock to the portfolio...\n" +
            "\n" +
            "Stock TGT successfully added to Portfolio xyz\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Model: Getting list of portfolios.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new strategy\n" +
            "2. Load an existing strategy\n" +
            "Enter the portfolio name you want to add the strategy to:\n" +
            "Enter the amount to be invested into stocks:\n" +
            "What is the start date for this strategy? (Format: YYYY-MM-DD)\n" +
            "What is the end date for this strategy? (Format: YYYY-MM-DD)\n" +
            "Please enter end date that is after Thu Nov 01 15:59:59 EDT 2018\n" +
            "What is the end date for this strategy? (Format: YYYY-MM-DD)\n" +
            "How many days do you want between consecutive investments?\n" +
            "Do you want to split money equally amongst the stocks: (Enter Y or N)\n" +
            "What commission is being charged to buy these stocks? (In dollars)\n" +
            "Please wait while we add the strategy to the portfolio...\n" +
            "Successfully added the strategy to the portfolio.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }

  @Test
  public void testRecurringUnequalSplitStartBeforeEnd() {
    in = new StringReader("1 1 xyz 8 1 xyz 20000 2018-11-01 2018-10-05 2018-11-15 " +
            "7 N GOOGL 80 TGT 20 2 3 xyz 2018-11-30 q");
    view = new PortfolioManagerViewImpl(in, out);
    IPortfolioManagerController p = new PortfolioManagerController(model, view);
    p.startInvesting();

    assertEquals("Calling model to create a portfolio.\n" +
            "Calling model to divide 20000.0 dollars worth of stocks in a ratio " +
            "{TGT=20.0, GOOGL=80.0} in xyz and buy them with a commission of 2.0" +
            " for each transaction.\n" +
            "This is a recurring transaction occurring every 7 days from Thu Nov" +
            " 01 15:59:59 EDT 2018 to Thu Nov 15 15:59:59 EST 2018.\n" +
            "Calling model to view portfolio xyz on date Fri Nov 30 15:59:59 EST" +
            " 2018.\n", log.toString());
    assertEquals("\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new portfolio\n" +
            "2. Load an existing portfolio?\n" +
            "Enter a name for your portfolio.\n" +
            "Portfolio xyz successfully created\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Would you like to:\n" +
            "1. Create a new strategy\n" +
            "2. Load an existing strategy\n" +
            "Enter the portfolio name you want to add the strategy to:\n" +
            "Enter the amount to be invested into stocks:\n" +
            "What is the start date for this strategy? (Format: YYYY-MM-DD)\n" +
            "What is the end date for this strategy? (Format: YYYY-MM-DD)\n" +
            "Please enter end date that is after Thu Nov 01 15:59:59 EDT 2018\n" +
            "What is the end date for this strategy? (Format: YYYY-MM-DD)\n" +
            "How many days do you want between consecutive investments?\n" +
            "Do you want to split money equally amongst the stocks: (Enter Y or N)\n" +
            "Enter a ticker symbol:\n" +
            "Enter the percentage forGOOGL:\n" +
            "The current split is:\n" +
            "GOOGL: 80.0\n" +
            "Enter a ticker symbol:\n" +
            "Enter the percentage forTGT:\n" +
            "The current split is:\n" +
            "TGT: 20.0\n" +
            "GOOGL: 80.0\n" +
            "You have successfully divided all stocks into a 100% split.\n" +
            "What commission is being charged to buy these stocks? (In dollars)\n" +
            "Please wait while we add the strategy to the portfolio...\n" +
            "Successfully added the strategy to the portfolio.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n" +
            "Enter the portfolio you want to view:\n" +
            "On which date do you wish to view this Portfolio? (Format: YYYY-MM-DD)\n" +
            "Fetching view for the portfolio...\n" +
            "The portfolio is empty.\n" +
            "\n" +
            "Welcome to the Investing Manager. Please select one of the following: options.\n" +
            "1. Create/Load a Portfolio.\n" +
            "2. View all Portfolios created.\n" +
            "3. Expanded view of a Portfolio.\n" +
            "4. View valuation of Portfolio.\n" +
            "5. View cost basis of Portfolio.\n" +
            "6. Buy a Stock.\n" +
            "7. Buy a set of stocks as a one time investment.\n" +
            "8. Create/Load a Strategy.\n" +
            "9. Save a portfolio to a file.\n" +
            "10. Save a strategy to a file.\n" +
            "11. Get information about Stock from the Stock Market.\n" +
            "Q: To quit at any point.\n", out.toString());
  }
}
