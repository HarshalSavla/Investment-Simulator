INVESTMENT SIMULATOR

-Notes:-
** Run the jar file by saying java -jar Assignment10.jar -view "console". for Console Interface.
** Run the jar file by saying java -jar Assignment10.jar -view "gui". for GUI interface.
1. Without creating a portfolio, you can't perform anything besides searching for a Stock.
2. Portfolio names case independent and the default case in our project is Uppercase.
3. Upon passing an invalid date string, the Java Calendar interface will consider the nearest
   valid date and add the difference of the number of days to it.
For eg, 35th January 2017 is basically 31st January + 4 days. i.e. 4th February.
	29th February 2017 is basically 1st March.


- When you run the JAR file, the following commands will turn up.

*****************************************************************************************
Welcome to the Investing Manager. Please select one of the following: options.          *
1. Create/Load a Portfolio.								*
2. View all Portfolios created.								*
3. Expanded view of a Portfolio.							*
4. View valuation of Portfolio.								*
5. View cost basis of Portfolio.							*
6. Buy a Stock.										*
7. Buy a set of stocks as a one time investment.					*
8. Create/Load a Strategy.								*
9. Save a portfolio to a file.								*
10. Save a strategy to a file.								*
11. Get information about Stock from the Stock Market.					*
Q: To quit at any point.                                              			*
*****************************************************************************************

Note:- You can quit anytime by passing Q from anytime within the function.

From the mainscreen, you can't proceed further unless you press a valid command.
Upon pressing invalid option, you'll be asked to press a valid option again.


The following options can be pressed:-

CONSOLE 	|| 	GUI
--------------------------------------------------
Press '1'	|| 	Create/Load a Portfolio
--------------------------------------------------
You'll be asked to pass a valid string for portfolio name. Portfolio names are case-insensitive and
unique. You can create a blank portfolio or retrieve the contents of a portfolio saved in a file.


Press '2'	||	View all Portfolios created
---------------------------------------------------
No input will be required, all the created portfolio's names will be displayed.


Press '3'	||	Expanded view of a Portfolio
-----------------------------------------------------
You'll be asked to pass a valid portfolio name, followed by a valid date for which you want to see
the portfolio contents.
If the stocks present in the portfolio are bought after that date, their logs won't be displayed.


Press '4'	||	View Valuation of Portfolio
---------------------------------------------------------
You'll be asked to pass a valid portfolio name, followed by a valid date for which you want to find
the value of your portfolio.
If the markets are closed on that day, it will evaluate your portfolio based on the closest previous
date available from the database.
Then, you'll be asked to pick whether you want to evaluate a particular stock or the whole database.


Press '5'	||	View costBasis of Portfolio
----------------------------------------------------------
You'll be asked to pass a valid portfolio name, followed by a valid date for which you want to find
the amount invested in your portfolio.
Then, you'll be asked to pick whether you want to evaluate a particular stock or the whole database.
It will print out the value you invested as on that day. Stocks bought after that date will not add
up in your investment total.


Press '6'	||	Buy a Stock
----------------------------------------------------------
First, you'll be asked how you want to buy the stock, either using number of stocks, or by providing
a fixed value.
You'll be asked to pass a valid portfolio name, followed by a valid ticker symbol of the stock you
want to buy from. Then you'll be asked to enter the quantity of the stocks you want to buy from
followed by a date on which you're buying those stocks on. The market closes at 15:59:59 and opens
at 09:00:00. So enter you buying time as per the 24-hr Clock.
If the market was closed, it will show you that the market was closed on that day.


Press '7'	||	Buy a set of Stocks as a one Time Investment
--------------------------------------------------------------------------
First you'll be asked to pass valid portfolio name, followed by a date you wish to invest once on.
Next you'll be asked to pass a valid amount followed by the commission charged. Next, you'll be
asked whether you want to split the money equally in the portfolio or not. If you select equally,
no ticker symbol can be passed. If you select yes, then you need to provide a ticker symbol followed
by it's weight. 


Press '8'	||	Create/Load a Strategy
--------------------------------------------------------------------------
First you'll be asked to pass valid portfolio name, followed by a date you wish to start investing on.
Next you'll be asked to pass a valid amount followed by the commission charged.
Next comes the date you want to stop this recurring investment strategy on,
followed by the number of days between two consecutive investments.
Next, you'll be asked whether you want to split the money equally in the portfolio or not. If you select equally,
no ticker symbol can be passed. If you select yes, then you need to provide a ticker symbol followed
by it's weights. The respective strategy will be created thus.
You'll also have a option in the beginning to skip every step and load a strategy instead by providing the filename of that portfolio.


Press '9'	||	Save a Portfolio to a file
-----------------------------------------------------------
You'll be asked to provide a valid portfolio name followed by a valid filename in which the portfolio data will be saved.


Press '10'	||	Save a Strategy to a file
------------------------------------------------------------
You'll be asked to provide a valid filename followed by a valid set of arguments required to create a strategy as mentioned in Create / Load a strategy section.

Press '11'	||	Get Information about Stock
----------------------------------------------------------------
You'll be asked to pass a valid ticker symbol to display the last 20 years worth of data of the
respective stock.
	