Assignment 10 changes:
Changes made in portfolio.model package:
Added functions to retrieve and save portfolios and strategies and appropriate helpers.
Modified the return types of functions to migrate display logic to view.

Changes made in portfolio.controller package:
Added corresponding controller functions to our Command design pattern to save strategies and
portfolios. Included the load portfolio/strategy in the create portfolio and strategy options
respectively.
Added a new implementation of the controller interface PortfolioManagerControllerGraphic which implements
all the button listeners of the GUI view.

Changes made in portfolio.view package:
Added a new view PortfolioManagerInterfaceViewGraphic and view interface IPortfolioManagerView
for the GUI.

Modified InvestmentSimulator file to run our program using the updated JAR run command for either
gui or console views.


Assignment 9 changes:
Changes made in portfolio.model package:
* Strategy interface in order to be able to execute a strategy on a portfolio.
* Implementation of Strategy interface as inner classes in PortfolioImpl: UnequalStrategy and
    EqualStrategy whose objects represent the respective recurring strategies.
* Modified UserOperations to extend the UserOperationsModified interface which contains new/updated
functions introduced in Assignment 9.
buyStock: Updated buyStock fn. to accept commission value and BuyType enum which determines
    if the stock value represents an amount(in dollars) or no. of shares of stock (count).
weightedInvestEqual: New function to do a one-time weighted investment amongst stocks, amount split
    equally amongst stocks on a particular date.
weightedInvestmentUnequal: New function to do a one-time weighted investment amongst stocks, amount
    split unequally amongst stocks on a particular date.
createEqualStrategy: New function to create a recurring weighted investment amongst stocks, amount
    split equally amongst stocks from a start date to end date at user specified intervals.
createUnequalStrategy: New function to create a recurring weighted investment amongst stocks, amount
    split unequally amongst stocks from a start date to end date at user specified intervals.
* Updated the Query file to now accept inputs from the AlphaVantage API instead of text files.

Changes made in portfolio.controller package:
* Modified BuyAStock file to incorporate a way to either buy a stock to the portfolio by specifying
an amount to invest towards a stock or no. of shares of the stock to be bought. Also added a way to
take commission amount for the transaction and pass it to the model. Finally, added private helper
functions to make readability easier.
* Added a new file: NonRecurringInvestment
This provides controller with functionality to ask user to input a list of stocks and divide a sum
of money amongst these stocks either equally or in a user-provided ratio - as a one time investment.
If the stocks are not present in the portfolio already, they get added automatically to it.
Commission fee is charged on every transaction made.
* Added a new file: RecurringInvestment
This provides controller with functionality to ask user to input a list of stocks and divide a sum
of money amongst these stocks either equally or in a user-provided ratio - as a recurring
investment from user-provided start date to end date at a specific interval of days. Commission fee
is charged on every transaction made.
If the stocks are not present in the portfolio already, they get added automatically to it.

Changes made in portfolio.view package:
NO CHANGE to existing files PortfolioManagerView and PortfolioManagerViewImpl.

Changes made in test folder
Mock Model: MockUserOperationsImpl
    Added mock methods for updated UserOperations interface.
Model test file: UserOperationsTest
    Added tests to test the newly added and updated functions in UserOperations interface.
Controller test file: PortfolioManagerControllerTest
    Added tests to test the newly added operations in RecurringInvestment, NonRecurringInvestment
    and BuyAStock
Model-Controller test file: UserOpModelPortfolioMgrControllerTest
    Added tests to test the model-controller combination.

The Portfolio management system has a portfolio package which consists of 3 main packages: model,
view and controller.


-------------MODEL----------------
The model consists of a main interface UserOperations which is implemented by UserOperationsImpl
class. Main functionalities of this sub-system include creating a portfolio of stocks, buying
stocks and adding them to a portfolio. The structure of the model classes is as follows: A user
has many portfolios (defined in the Portfolio interface), which in turn each contain many stocks
that are then defined by the Stock class. We use Hashmaps to store stock name and stock object,
and portfolio name and portfolio object respectively. Buying stocks can be done in multiple ways -
either simply buy by specifying the no. of shares of a stock to be bought, or the amount to be
invested, or adding a bunch of stocks at once by splitting total amount to be invested either in
an equal or unequal ratio. This can even be implemented as a recurring strategy at a specified
interval between a range of dates.
It also contains a Query class that standardized input from various sources into a format accepted
by our system. We had extracted data from text files whose file name is the ticker
symbol and contents include the date, opening price of a stock, the low and high prices, the
closing price of the stock for that day and the volume of shares traded worldwide. (You can
view this in the datasets folder). Now we are getting our data from an AlphaVantage API.

-------------VIEW----------------
Since our application is a text based file, the view in our system is more of a stub that
connects to the input and output sources which can then be accessed by the controller to receive
and send data through the functions defined in the PortfolioManagerView view interface. It is
implemented by the PortfolioManagerViewImpl class.

-------------CONTROLLER----------------
The controller is responsible for talking to both the model and view subsystems. We have our main
IPortfolioManagerController controller interface implemented by class PortfolioManagerController.
We also use the command design pattern to split up the different operations that can be performed
by calling the model functions from the controller and generalise calling any of the operations.
It is done by creating an interface UserOperationsController and then implementing different
classes to do different operations. Finally storing an (index, object) pair in a hash table in
our main controller enables us to use this to call that particular class object specific to the
operation.

The main() function that the JAR file runs resides in the InvestmentSimulator file.

