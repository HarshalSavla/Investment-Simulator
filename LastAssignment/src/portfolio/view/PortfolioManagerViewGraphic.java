package portfolio.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The class that implements the {@link IPortfolioManagerView} interface.
 */
public class PortfolioManagerViewGraphic extends JFrame implements ActionListener, ItemListener,
        ListSelectionListener, IPortfolioManagerView {

  private JPanel mainPanel;
  private JButton createPortfolioButton;
  private JTextArea outputWindow;
  private JButton viewPortfolioButton;
  private JButton expandedViewButton;
  private JButton buyStockButton;
  private JButton stockDetailButton;
  private JButton graphPlotButton;
  private JButton getValuationButton;
  private JButton costBasisButton;
  private JButton clearScreenButton;
  private JButton buyOneTimeInvestButton;
  private JButton buyRecurringInvestment;
  private JButton saveStrategyButton;
  private JButton retrieveStrategyButton;
  private JButton retrievePortfolioButton;
  private JButton savePortfolioButton;
  private JButton quitButton;

  /**
   * This constructor initializes and creates the GUI view.
   */
  public PortfolioManagerViewGraphic() {
    super();
    setTitle("GStock");
    Dimension maxScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setSize(maxScreenSize);

    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //text area with a scrollbar
    outputWindow = new JTextArea(32, 20);
    JScrollPane scrollPane = new JScrollPane(outputWindow);
    outputWindow.setLineWrap(true);
    outputWindow.setEditable(false);
    //scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setBorder(BorderFactory.createTitledBorder("Output Window"));
    mainPanel.add(scrollPane);

    row1();
    row2();
    row3();
    row4();
    row5();
    row6();
    row7();
    row8();

  }

  /**
   * Add a panel of buttons to the main panel.
   */
  private void row1() {

    //Button row 1
    JPanel row1 = new JPanel();
    row1.setBorder(BorderFactory.createTitledBorder(""));
    row1.setLayout(new BoxLayout(row1, BoxLayout.PAGE_AXIS));
    mainPanel.add(row1);
    JPanel group1 = new JPanel();
    group1.setLayout(new GridLayout());
    row1.add(group1);

    //Create Portfolio
    createPortfolioButton = new JButton("Create a portfolio");
    createPortfolioButton.setActionCommand("CreatePortfolio");
    group1.add(createPortfolioButton);

    //View Portfolio
    viewPortfolioButton = new JButton("View All portfolio");
    viewPortfolioButton.setActionCommand("ViewAllPortfolio");
    group1.add(viewPortfolioButton);
  }

  /**
   * Add a panel of buttons to the main panel.
   */
  private void row2() {

    //Button row 2
    JPanel row2 = new JPanel();
    row2.setBorder(BorderFactory.createTitledBorder(""));
    row2.setLayout(new BoxLayout(row2, BoxLayout.PAGE_AXIS));
    mainPanel.add(row2);
    JPanel group2 = new JPanel();
    group2.setLayout(new GridLayout());
    row2.add(group2);

    //Expanded View
    expandedViewButton = new JButton("Expanded View Of Portfolio");
    expandedViewButton.setActionCommand("ExpandedViewOfPortfolio");
    group2.add(expandedViewButton);

    //View Valuation
    row2.add(group2);
    getValuationButton = new JButton("View Valuation of Portfolio");
    getValuationButton.setActionCommand("ViewValuation");
    group2.add(getValuationButton);
  }

  /**
   * Add a panel of buttons to the main panel.
   */
  private void row3() {

    //Button row 3
    JPanel row3 = new JPanel();
    row3.setBorder(BorderFactory.createTitledBorder(""));
    row3.setLayout(new BoxLayout(row3, BoxLayout.PAGE_AXIS));
    mainPanel.add(row3);
    JPanel group3 = new JPanel();
    group3.setLayout(new GridLayout());

    //Cost Basis
    row3.add(group3);
    costBasisButton = new JButton("View Cost Basis of Portfolio");
    costBasisButton.setActionCommand("ViewCostBasis");
    group3.add(costBasisButton);

    //Buy Stock
    row3.add(group3);
    buyStockButton = new JButton("Buy A Stock");
    buyStockButton.setActionCommand("BuyAStock");
    group3.add(buyStockButton);
  }

  /**
   * Add a panel of buttons to the main panel.
   */
  private void row4() {

    //Button row 4
    JPanel row4 = new JPanel();
    row4.setBorder(BorderFactory.createTitledBorder(""));
    row4.setLayout(new BoxLayout(row4, BoxLayout.PAGE_AXIS));
    mainPanel.add(row4);
    JPanel group4 = new JPanel();
    group4.setLayout(new GridLayout());

    //Buy a set stocks as one time investment.
    row4.add(group4);
    buyOneTimeInvestButton = new JButton("One Time Investment");
    buyOneTimeInvestButton.setActionCommand("OneTimeInvestment");
    group4.add(buyOneTimeInvestButton);

    //Buy a set stocks as one time investment.
    row4.add(group4);
    buyRecurringInvestment = new JButton("Recurring Investment");
    buyRecurringInvestment.setActionCommand("RecurringInvestment");
    group4.add(buyRecurringInvestment);

  }

  /**
   * Add a panel of buttons to the main panel.
   */
  private void row5() {

    //Button row 5
    JPanel row5 = new JPanel();
    row5.setBorder(BorderFactory.createTitledBorder(""));
    row5.setLayout(new BoxLayout(row5, BoxLayout.PAGE_AXIS));
    mainPanel.add(row5);
    JPanel group5 = new JPanel();
    group5.setLayout(new GridLayout());

    //Buy a set stocks as one time investment.
    row5.add(group5);
    stockDetailButton = new JButton("Get information about Stock from the Stock Market.");
    stockDetailButton.setActionCommand("GetInfo");
    group5.add(stockDetailButton);

    //file open
    row5.add(group5);
    retrieveStrategyButton = new JButton("Retrieve Strategy from file");
    retrieveStrategyButton.setActionCommand("RetrieveStrategyFromFile");
    retrieveStrategyButton.addActionListener(this);
    group5.add(retrieveStrategyButton);
  }

  /**
   * Add a panel of buttons to the main panel.
   */
  private void row6() {

    //Button row 6
    JPanel row6 = new JPanel();
    row6.setBorder(BorderFactory.createTitledBorder(""));
    row6.setLayout(new BoxLayout(row6, BoxLayout.PAGE_AXIS));
    mainPanel.add(row6);
    JPanel group6 = new JPanel();
    group6.setLayout(new GridLayout());

    //file save
    row6.add(group6);
    saveStrategyButton = new JButton("Save Investment Strategy to a file");
    saveStrategyButton.setActionCommand("SaveInvestmentStrategy");
    group6.add(saveStrategyButton);

    //Graph for portfolio performance
    row6.add(group6);
    retrievePortfolioButton = new JButton("Retrieve portfolio from file");
    retrievePortfolioButton.setActionCommand("RetrievePortfolio");
    group6.add(retrievePortfolioButton);
  }

  /**
   * Add a panel of buttons to the main panel.
   */
  private void row7() {

    //Button row 7
    JPanel row7 = new JPanel();
    row7.setBorder(BorderFactory.createTitledBorder(""));
    row7.setLayout(new BoxLayout(row7, BoxLayout.PAGE_AXIS));
    mainPanel.add(row7);
    JPanel group7 = new JPanel();
    group7.setLayout(new GridLayout());

    row7.add(group7);
    savePortfolioButton = new JButton("Save Portfolio to file");
    savePortfolioButton.setActionCommand("SavePortfolio");
    group7.add(savePortfolioButton);

    row7.add(group7);
    clearScreenButton = new JButton("Clear Screen");
    clearScreenButton.setActionCommand("ClearScreen");
    clearScreenButton.setForeground(Color.BLUE);
    group7.add(clearScreenButton);
  }

  /**
   * Add a panel of buttons to the main panel.
   */
  private void row8() {

    JPanel row8 = new JPanel();
    row8.setBorder(BorderFactory.createTitledBorder(""));
    row8.setLayout(new BoxLayout(row8, BoxLayout.PAGE_AXIS));
    mainPanel.add(row8);
    JPanel group8 = new JPanel();
    group8.setLayout(new GridLayout());

    row8.add(group8);
    graphPlotButton = new JButton("Performance graph");
    graphPlotButton.setActionCommand("Plot Graph");
    graphPlotButton.setForeground(Color.green);
    group8.add(graphPlotButton);

    row8.add(group8);
    quitButton = new JButton("Quit");
    quitButton.setActionCommand("quit");
    quitButton.setForeground(Color.RED);
    group8.add(quitButton);
  }

  /**
   * When the action event occurs, that object's <code>actionPerformed</code> method is invoked.
   *
   * @param actionListener ActionListener is notified against ActionEvent.
   */
  public void addActionListener(ActionListener actionListener) {
    createPortfolioButton.addActionListener(actionListener);
    viewPortfolioButton.addActionListener(actionListener);
    expandedViewButton.addActionListener(actionListener);
    buyStockButton.addActionListener(actionListener);
    stockDetailButton.addActionListener(actionListener);
    graphPlotButton.addActionListener(actionListener);
    getValuationButton.addActionListener(actionListener);
    costBasisButton.addActionListener(actionListener);
    clearScreenButton.addActionListener(actionListener);
    buyOneTimeInvestButton.addActionListener(actionListener);
    buyRecurringInvestment.addActionListener(actionListener);
    saveStrategyButton.addActionListener(actionListener);
    retrieveStrategyButton.addActionListener(actionListener);
    savePortfolioButton.addActionListener(actionListener);
    retrievePortfolioButton.addActionListener(actionListener);
    quitButton.addActionListener(actionListener);
  }

  @Override
  public void actionPerformed(ActionEvent argument) {
    // overriding from ActionListener but not implementing
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    // overriding from ActionListener but not implementing
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    // overriding from ActionListener but not implementing
  }

  /**
   * Creates a pop-up dialog which lets the user enter a name to create a portfolio.
   *
   * @return portfolio name.
   */
  @Override
  public String createPortfolio() {
    JTextField pName = new JTextField(5);
    JPanel portfolioPanel = new JPanel();
    portfolioPanel.add(new JLabel("Enter Portfolio Name"));
    portfolioPanel.add(pName);
    JOptionPane optionPane = new JOptionPane(portfolioPanel, JOptionPane.INFORMATION_MESSAGE,
            JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[1];
    String[] type = new String[]{"String"};
    textFields[0] = pName;
    String[] result = openDialog(optionPane, "Create a portfolio", textFields, type);
    return result[0];
  }

  private String[] openDialog(JOptionPane optionPane, String title, JTextField[] textFields,
                              String[] type) {
    JDialog dialog = new JDialog(PortfolioManagerViewGraphic.this, title, true);
    dialog.setContentPane(optionPane);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    addComponentListener(new ComponentAdapter() {
      public void componentShown(ComponentEvent ce) {
        textFields[0].requestFocusInWindow();
      }
    });

    optionPane.addPropertyChangeListener(e -> {
      String prop = e.getPropertyName();
      if (dialog.isVisible()
              && (e.getSource() == optionPane)
              && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
        Object value = optionPane.getValue();
        if (value == JOptionPane.UNINITIALIZED_VALUE) {
          //ignore reset
          return;
        }
        optionPane.setValue(
                JOptionPane.UNINITIALIZED_VALUE);

        if (value.equals(0)) {
          boolean check = false;
          for (int i = 0; i < textFields.length; i++) {
            JTextField textField = textFields[i];
            if (!textValidated(textField, type[i])) {
              textField.selectAll();
              JOptionPane.showMessageDialog(this, "Please re-enter " +
                      "this field correctly!", "Try again", JOptionPane.ERROR_MESSAGE);
              textField.requestFocusInWindow();
              check = true;
              break;
            }
          }
          if (!check) {
            dialog.setVisible(false);
          }
        } else {
          for (JTextField textField : textFields) {
            textField.setText("");
          }
          dialog.setVisible(false);
        }
      }
    });
    dialog.pack();
    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);

    String[] result = new String[textFields.length];
    int i = 0;
    for (JTextField textField : textFields) {
      result[i] = textField.getText();
      i++;
    }
    return result;
  }

  private boolean textValidated(JTextField textField, String type) {
    switch (type) {
      case "Date":
        try {
          Date d = helperGetDate(textField.getText());
          return true;
        } catch (Exception e) {
          return false;
        }
      case "Double":
        try {
          double validNum = Double.parseDouble(textField.getText());
          return validNum > 0;
        } catch (Exception e) {
          return false;
        }
      case "Whole Double": // zero allowed
        try {
          double validNum = Double.parseDouble(textField.getText());
          return validNum >= 0;
        } catch (Exception e) {
          return false;
        }

      case "String Optional":
        return true;
      default: // default is String
        return !textField.getText().trim().isEmpty();
    }
  }

  /**
   * Creates a pop-up dialog which lets the user enter parameters to view the details of a
   * portfolio.
   *
   * @return String array containing portfolio name and date
   */
  @Override
  public String[] expandedViewOfPortfolio() {
    JTextField portfolioName = new JTextField(5);
    JTextField date = new JTextField(5);
    JPanel expandedViewP = new JPanel();
    expandedViewP.add(new JLabel("Enter Portfolio Name"));
    expandedViewP.add(portfolioName);
    expandedViewP.add(Box.createHorizontalStrut(15)); // a spacer
    expandedViewP.add(new JLabel("Enter date in YYYY-MM-DD format"));
    expandedViewP.add(date);
    JOptionPane optionPane = new JOptionPane(expandedViewP, JOptionPane.INFORMATION_MESSAGE,
            JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[2];
    textFields[0] = portfolioName;
    textFields[1] = date;
    String[] type = new String[]{"String", "Date"};
    String[] result = openDialog(optionPane, "Expanded view of a portfolio", textFields, type);
    return result;
  }

  /**
   * Creates a pop-up to display an error message.
   *
   * @param message String containing the error message.
   */
  @Override
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Creates a pop-up that lets the user enter the parameters required to buy a stock.
   *
   * @return a String array containing the parameters entered by the user.
   */
  @Override
  public String[] buyAStock() {
    JTextField portfolioName = new JTextField(5);
    JTextField ticker = new JTextField(5);
    JTextField amount = new JTextField(5);
    JTextField date = new JTextField(5);
    JTextField commission = new JTextField(5);
    JRadioButton dollar = new JRadioButton("$ value");
    JRadioButton quantity = new JRadioButton("# of stocks");
    JPanel buyPanel = new JPanel();
    buyPanel.setLayout(new BoxLayout(buyPanel, BoxLayout.PAGE_AXIS));
    buyPanel.add(new JLabel("Enter Portfolio name"));
    buyPanel.add(portfolioName);
    buyPanel.add(new JLabel("Enter Ticker Code"));
    buyPanel.add(ticker);
    buyPanel.add(new JLabel("Enter Date (YYYY-MM-DD)"));
    buyPanel.add(date);
    buyPanel.add(new JLabel("Enter value to invest"));
    buyPanel.add(amount);
    buyPanel.add(new JLabel("Commission in $"));
    buyPanel.add(commission);
    //Create a radio button group.
    ButtonGroup group = new ButtonGroup();
    group.add(dollar);
    group.add(quantity);
    dollar.setSelected(true);
    buyPanel.add(dollar);
    buyPanel.add(quantity);
    JOptionPane optionPane = new JOptionPane(buyPanel, JOptionPane.INFORMATION_MESSAGE,
            JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[5];
    textFields[0] = portfolioName;
    textFields[1] = ticker;
    textFields[2] = date;
    textFields[3] = amount;
    textFields[4] = commission;
    String[] type = new String[]{"String", "String", "Date", "Double", "Whole Double"};
    String[] result = openDialog(optionPane, "Buy a stock", textFields, type);
    String[] resultFinal = Arrays.copyOf(result, result.length + 1);
    resultFinal[5] = dollar.isSelected() ? dollar.getText() : quantity.getText();
    return resultFinal;
  }

  /**
   * Creates a pop-up that lets the user enter the parameters required to get the cost basis of a
   * portfolio.
   *
   * @return a String array containing the parameters entered by the user.
   */
  @Override
  public String[] getCostBasis() {
    JTextField portfolioNameCB = new JTextField(5);
    JTextField tickerCode = new JTextField(5);
    JTextField dateCB = new JTextField(5);
    JPanel costBasisP = new JPanel();
    costBasisP.setLayout(new BoxLayout(costBasisP, BoxLayout.PAGE_AXIS));
    costBasisP.add(new JLabel("Enter Portfolio Name"));
    costBasisP.add(portfolioNameCB);
    costBasisP.add(new JLabel("Enter Stock name (optional)"));
    costBasisP.add(tickerCode);
    costBasisP.add(Box.createHorizontalStrut(15)); // a spacer
    costBasisP.add(new JLabel("Enter date in YYYY-MM-DD format"));
    costBasisP.add(dateCB);

    JOptionPane optionPane = new JOptionPane(costBasisP, JOptionPane.INFORMATION_MESSAGE,
            JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[3];
    textFields[0] = portfolioNameCB;
    textFields[1] = tickerCode;
    textFields[2] = dateCB;
    String[] type = new String[]{"String", "String Optional", "Date"};
    return openDialog(optionPane, "Cost Basis", textFields, type);
  }

  /**
   * Creates a pop-up that lets the user enter the parameters required to get the valuation of a
   * portfolio.
   *
   * @return a String array containing the parameters entered by the user.
   */
  @Override
  public String[] getValuation() {
    JTextField portfolioName = new JTextField(5);
    JTextField tickerCode = new JTextField(5);
    JTextField date = new JTextField(5);

    JPanel valuationPanel = new JPanel();
    valuationPanel.setLayout(new BoxLayout(valuationPanel, BoxLayout.PAGE_AXIS));
    valuationPanel.add(new JLabel("Enter Portfolio Name"));
    valuationPanel.add(portfolioName);
    valuationPanel.add(new JLabel("Enter Stock name"));
    valuationPanel.add(tickerCode);
    valuationPanel.add(new JLabel("Enter date in YYYY-MM-DD format"));
    valuationPanel.add(date);
    JOptionPane optionPane = new JOptionPane(valuationPanel, JOptionPane.INFORMATION_MESSAGE,
            JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[3];
    textFields[0] = portfolioName;
    textFields[1] = tickerCode;
    textFields[2] = date;
    String[] type = new String[]{"String", "String Optional", "Date"};
    return openDialog(optionPane, "Valuation", textFields, type);
  }

  /**
   * Creates a pop-up that lets the user enter the parameters required to create a one-time
   * investment strategy.
   *
   * @return a String array containing the parameters entered by the user.
   */
  @Override
  public String[] oneTimeStrategy() {
    JTextField portfolioName = new JTextField(5);
    JTextField amount = new JTextField(5);
    JTextField date = new JTextField(5);
    JTextField commission = new JTextField(5);
    JRadioButton equalWeights = new JRadioButton("Equal Weights");
    JRadioButton unequalWeights = new JRadioButton("Unequal Weights");
    JPanel strategyPanel = new JPanel();
    strategyPanel.setLayout(new BoxLayout(strategyPanel, BoxLayout.PAGE_AXIS));
    strategyPanel.add(new JLabel("Enter portfolio name"));
    strategyPanel.add(portfolioName);
    strategyPanel.add(new JLabel("Enter date"));
    strategyPanel.add(date);
    strategyPanel.add(new JLabel("Enter amount to be invested"));
    strategyPanel.add(amount);
    strategyPanel.add(new JLabel("Enter commission"));
    strategyPanel.add(commission);
    //Create a radio button group.
    ButtonGroup group = new ButtonGroup();
    group.add(equalWeights);
    group.add(unequalWeights);
    strategyPanel.add(equalWeights);
    strategyPanel.add(unequalWeights);
    equalWeights.setSelected(true);
    JOptionPane optionPane = new JOptionPane(strategyPanel, JOptionPane.INFORMATION_MESSAGE,
            JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[4];
    textFields[0] = portfolioName;
    textFields[1] = date;
    textFields[2] = amount;
    textFields[3] = commission;
    String[] type = new String[]{"String", "Date", "Double", "Whole Double"};
    String[] result = openDialog(optionPane, "One Time Investment", textFields, type);
    String[] resultFinal = Arrays.copyOf(result, result.length + 1);
    resultFinal[4] = equalWeights.isSelected() ? equalWeights.getText() : unequalWeights.getText();
    return resultFinal;
  }

  /**
   * Creates a pop-up that lets the user enter the parameters required to create a recurring
   * investment strategy.
   *
   * @return a String array containing the parameters entered by the user.
   */
  @Override
  public String[] recurringStrategy() {
    JTextField portfolioName = new JTextField(5);
    JTextField amount = new JTextField(5);
    JTextField startDate = new JTextField(5);
    JTextField endDate = new JTextField(5);
    JTextField commission = new JTextField(5);
    JTextField days = new JTextField(5);
    JRadioButton equalWeights = new JRadioButton("Equal Weights");
    JRadioButton unequalWeights = new JRadioButton("Unequal Weights");
    JPanel recurringStrategyPanel = new JPanel();
    recurringStrategyPanel.setLayout(new BoxLayout(recurringStrategyPanel, BoxLayout.PAGE_AXIS));
    recurringStrategyPanel.add(new JLabel("Enter portfolio name"));
    recurringStrategyPanel.add(portfolioName);
    recurringStrategyPanel.add(new JLabel("Enter start date"));
    recurringStrategyPanel.add(startDate);
    recurringStrategyPanel.add(new JLabel("Enter end date"));
    recurringStrategyPanel.add(endDate);
    recurringStrategyPanel.add(new JLabel("Frequency of buying stock (in days)"));
    recurringStrategyPanel.add(days);
    recurringStrategyPanel.add(new JLabel("Enter amount to be invested"));
    recurringStrategyPanel.add(amount);
    recurringStrategyPanel.add(new JLabel("Enter commission"));
    recurringStrategyPanel.add(commission);
    ButtonGroup group = new ButtonGroup();
    group.add(equalWeights);
    group.add(unequalWeights);
    recurringStrategyPanel.add(equalWeights);
    recurringStrategyPanel.add(unequalWeights);
    equalWeights.setSelected(true);
    JOptionPane optionPane = new JOptionPane(recurringStrategyPanel,
            JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[6];
    textFields[0] = portfolioName;
    textFields[1] = startDate;
    textFields[2] = endDate;
    textFields[3] = days;
    textFields[4] = amount;
    textFields[5] = commission;
    String[] type = new String[]{"String", "Date", "Date", "Double", "Double", "Whole Double"};
    String[] result = openDialog(optionPane, "Recurring Investment", textFields, type);
    String[] resultFinal = Arrays.copyOf(result, result.length + 1);
    resultFinal[6] = equalWeights.isSelected() ? equalWeights.getText() : unequalWeights.getText();
    return resultFinal;
  }

  /**
   * Creates a pop-up that lets the user enter the parameters required to get historical information
   * of a particular stock.
   *
   * @return a String  containing the Ticker code entered by the user.
   */
  @Override
  public String getStockInfo() {
    JTextField tickerCode = new JTextField(5);
    JPanel getInfo = new JPanel();
    getInfo.add(new JLabel("Enter the stock ticker you want information about:"));
    getInfo.add(tickerCode);

    JOptionPane optionPane = new JOptionPane(getInfo, JOptionPane.INFORMATION_MESSAGE,
            JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[1];
    String[] type = new String[]{"String"};
    textFields[0] = tickerCode;
    String[] result = openDialog(optionPane, "Get Stock Information", textFields, type);
    return result[0];
  }

  /**
   * Creates a pop-up that lets the user enter the parameters required to save an investment
   * strategy to a file.
   *
   * @return a String array containing the parameters entered by the user.
   */
  public String[] saveStrategyToFile() {
    JTextField startDate = new JTextField(5);
    JTextField endDate = new JTextField(5);
    JTextField days = new JTextField(5);
    JTextField amount = new JTextField(5);
    JTextField commission = new JTextField(5);
    JTextField fileName = new JTextField(5);
    JRadioButton equalWeights = new JRadioButton("Equal Weights");
    JRadioButton unequalWeights = new JRadioButton("Unequal Weights");
    JPanel saveStrategyPanel = new JPanel();
    saveStrategyPanel.setLayout(new BoxLayout(saveStrategyPanel, BoxLayout.PAGE_AXIS));
    saveStrategyPanel.add(new JLabel("Enter start date in YYYY-MM-DD format"));
    saveStrategyPanel.add(startDate);
    saveStrategyPanel.add(new JLabel("Enter end date in YYYY-MM-DD format"));
    saveStrategyPanel.add(endDate);
    saveStrategyPanel.add(new JLabel("Enter Frequency of buying stocks in Days"));
    saveStrategyPanel.add(days);
    saveStrategyPanel.add(new JLabel("Enter amount to be invested"));
    saveStrategyPanel.add(amount);
    saveStrategyPanel.add(new JLabel("Enter commission amount"));
    saveStrategyPanel.add(commission);
    saveStrategyPanel.add(new JLabel("Enter file name you want to save this strategy as"));
    saveStrategyPanel.add(fileName);
    ButtonGroup group = new ButtonGroup();
    group.add(equalWeights);
    group.add(unequalWeights);
    saveStrategyPanel.add(equalWeights);
    saveStrategyPanel.add(unequalWeights);
    equalWeights.setSelected(true);
    JOptionPane optionPane = new JOptionPane(saveStrategyPanel,
            JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[6];
    textFields[0] = startDate;
    textFields[1] = endDate;
    textFields[2] = days;
    textFields[3] = amount;
    textFields[4] = commission;
    textFields[5] = fileName;
    String[] type = new String[]{"Date", "Date", "Double", "Double", "Whole Double", "String"};
    String[] result = openDialog(optionPane, "Save strategy to file", textFields, type);
    String[] resultFinal = Arrays.copyOf(result, result.length + 1);
    resultFinal[6] = equalWeights.isSelected() ? equalWeights.getText() : unequalWeights.getText();
    return resultFinal;
  }

  /**
   * Creates a pop-up that lets the user enter the parameters required to retrieve a strategy from a
   * file.
   *
   * @return a String array containing the parameters entered by the user.
   */
  public String[] retrieveStrategyFromFile() {
    JTextField portfolioName = new JTextField(5);
    JTextField fileName = new JTextField(5);
    JPanel retrieveStrategyPanel = new JPanel();
    retrieveStrategyPanel.setLayout(new BoxLayout(retrieveStrategyPanel, BoxLayout.PAGE_AXIS));
    retrieveStrategyPanel.add(new JLabel("Enter file name of the stored strategy"));
    retrieveStrategyPanel.add(fileName);
    retrieveStrategyPanel.add(new JLabel("Enter the portfolio name that you want to" +
            " apply the strategy to"));
    retrieveStrategyPanel.add(portfolioName);
    JOptionPane optionPane = new JOptionPane(retrieveStrategyPanel,
            JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[2];
    textFields[0] = fileName;
    textFields[1] = portfolioName;
    String[] type = new String[]{"String", "String"};
    return openDialog(optionPane, "Retrieve strategy", textFields, type);
  }

  /**
   * Creates a pop-up that lets the user enter the parameters required to save an existing portfolio
   * to a file.
   *
   * @return a String array containing the parameters entered by the user.
   */
  public String[] savePortfolio() {
    JTextField portfolioName = new JTextField(5);
    JTextField fileName = new JTextField(5);

    JPanel savePortfolioPanel = new JPanel();
    savePortfolioPanel.setLayout(new BoxLayout(savePortfolioPanel, BoxLayout.PAGE_AXIS));
    savePortfolioPanel.add(new JLabel("Enter the portfolio name that you want to save"));
    savePortfolioPanel.add(portfolioName);
    savePortfolioPanel.add(new JLabel("Enter file name to save this portfolio"));
    savePortfolioPanel.add(fileName);
    JOptionPane optionPane = new JOptionPane(savePortfolioPanel,
            JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[2];
    textFields[0] = portfolioName;
    textFields[1] = fileName;
    String[] type = new String[]{"String", "String"};
    return openDialog(optionPane, "Save portfolio to file", textFields, type);
  }

  /**
   * Creates a pop-up dialog that lets the user enter the parameters required to retrieve a
   * portfolio from a file.
   *
   * @return a String array containing the parameters entered by the user.
   */
  public String[] retrievePortfolio() {
    JTextField portfolioName = new JTextField(5);
    JTextField fileName = new JTextField(5);

    JPanel retrievePortfolioPanel = new JPanel();
    retrievePortfolioPanel.setLayout(new BoxLayout(retrievePortfolioPanel, BoxLayout.PAGE_AXIS));

    retrievePortfolioPanel.add(new JLabel("Enter file name of the stored portfolio"));
    retrievePortfolioPanel.add(fileName);
    retrievePortfolioPanel.add(new JLabel("Enter the portfolio name that you want to" +
            " add the portfolio to"));
    retrievePortfolioPanel.add(portfolioName);

    JOptionPane optionPane = new JOptionPane(retrievePortfolioPanel,
            JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
    JTextField[] textFields = new JTextField[2];
    textFields[0] = fileName;
    textFields[1] = portfolioName;
    String[] type = new String[]{"String", "String"};
    return openDialog(optionPane, "Retrieve portfolio from file", textFields, type);
  }


  /**
   * Creates a pop-up that lets the user enter the parameters required to generate a graph of
   * performance of a particular portfolio.
   */
  public String[] plotGraph() {
    String[] result = new String[4];
    JTextField portfolioName = new JTextField(5);
    JTextField fileName = new JTextField(5);
    JTextField startDate = new JTextField(5);
    JTextField endDate = new JTextField(5);
    JPanel plotGraph = new JPanel();

    plotGraph.setLayout(new BoxLayout(plotGraph, BoxLayout.PAGE_AXIS));
    plotGraph.add(new JLabel("Enter portfolio name"));
    plotGraph.add(portfolioName);
    plotGraph.add(new JLabel("Enter file name"));
    plotGraph.add(fileName);
    plotGraph.add(new JLabel("Enter start date in YYYY-MM-DD format"));
    plotGraph.add(startDate);
    plotGraph.add(new JLabel("Enter end date in YYYY-MM-DD format"));
    plotGraph.add(endDate);
    JOptionPane.showConfirmDialog(PortfolioManagerViewGraphic.this, plotGraph,
            "Portfolio Performance",
            JOptionPane.OK_CANCEL_OPTION);

    result[0] = portfolioName.getText();
    result[1] = fileName.getText();
    result[2] = startDate.getText();
    result[3] = endDate.getText();

    return result;
  }

  /**
   * This method displays a string in the main output window of the GUI.
   *
   * @param s is an output string of any function performed by the model and is sent to the view by
   *          the controller.
   */
  public void displayResult(String s) {
    outputWindow.append(s);
    outputWindow.append("\n--------------------------------------------------------------------\n");
  }

  /**
   * Prints a  message to give a visual cue to that user that an operation is being processed.
   */
  public void loadingMessage() {
    outputWindow.append("Please wait while your request is being processed...\n");
  }

  /**
   * Clears the main output window of the GUI.
   */
  public void clearScreen() {
    outputWindow.setText("");
  }

  /**
   * A helper function for creating an unequal weighted investment . This method recurringly creates
   * a pop-up to get the input parameters from user.
   *
   * @return a String array containing the parameters entered by the user.
   */
  public String[] helperInvestment() {

    String[] result = new String[2];
    JTextField tickerCode = new JTextField(5);
    JTextField percentage = new JTextField(5);
    JPanel getInfo = new JPanel();
    getInfo.add(new JLabel("Enter Stock code"));
    getInfo.add(tickerCode);
    getInfo.add(new JLabel("Percentage to invest"));
    getInfo.add(percentage);
    int tickerC = JOptionPane.showConfirmDialog(PortfolioManagerViewGraphic.this,
            getInfo,
            "One Time Investment", JOptionPane.OK_CANCEL_OPTION);


    result[0] = tickerCode.getText();
    result[1] = percentage.getText();
    return result;

  }

  /**
   * Prompt to ask the user whether to quit the application or not.
   *
   * @return Option selected by the user as an integer.
   */
  public int quit() {

    JPanel quitPanel = new JPanel();
    quitPanel.add(new JLabel("Are you sure you want to quit?\nYou might lose any unsaved work"));

    return JOptionPane.showConfirmDialog(this,
            quitPanel,
            "One Time Investment", JOptionPane.OK_CANCEL_OPTION);
  }

  /**
   * A helper function for creating an unequal weighted investment . This method recurringly creates
   * a pop-up to get the input parameters from user.
   *
   * @param track track the weights of investment amount by user.
   * @return a HashMap of Ticker code and investment weight entered by the user.
   */
  public HashMap<String, Double> helperRecurringInvestment(double track) {
    HashMap<String, Double> result = new HashMap<>();

    JTextField tickerCode = new JTextField(5);
    JTextField percentage = new JTextField(5);
    JTextField remaining = new JTextField(5);
    remaining.setEditable(false);
    JPanel recurringInvestment = new JPanel();
    recurringInvestment.add(new JLabel("Enter Stock code"));
    recurringInvestment.add(tickerCode);
    recurringInvestment.add(new JLabel("Percentage to invest"));
    recurringInvestment.add(percentage);
    recurringInvestment.add(new JLabel("Percentage left invest"));
    recurringInvestment.add(remaining);
    remaining.setText(String.valueOf(track));


    int tickerC = JOptionPane.showConfirmDialog(
            PortfolioManagerViewGraphic.this, recurringInvestment,
            "Get Information about a Stock", JOptionPane.OK_CANCEL_OPTION);
    result.put(tickerCode.getText(), Double.valueOf(percentage.getText()));


    return result;
  }

  /**
   * Creates a pop-up to display an error message.
   */
  public void errorMessage() {
    JPanel errorMessage = new JPanel();
    JOptionPane.showMessageDialog(PortfolioManagerViewGraphic.this,
            "Invalid arguments, please try again");
  }

  /**
   * This helper gets a valid date object from a string.
   */
  private Date helperGetDate(String date) {
    Calendar yyyyDdMm = Calendar.getInstance();
    String[] dates = date.split("-");
    yyyyDdMm.set(Calendar.YEAR, Integer.parseInt(dates[0]));
    yyyyDdMm.set(Calendar.MONTH, Integer.parseInt(dates[1]) - 1);
    yyyyDdMm.set(Calendar.DATE, Integer.parseInt(dates[2]));
    yyyyDdMm.set(Calendar.HOUR_OF_DAY, 15);
    yyyyDdMm.set(Calendar.MINUTE, 59);
    yyyyDdMm.set(Calendar.SECOND, 59);
    yyyyDdMm.set(Calendar.MILLISECOND, 0);
    return yyyyDdMm.getTime();
  }
}