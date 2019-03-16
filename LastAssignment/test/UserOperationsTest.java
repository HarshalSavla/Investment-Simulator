import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import portfolio.model.BuyType;
import portfolio.model.UserOperations;
import portfolio.model.UserOperationsImpl;

/**
 * Testing the User Operations model.
 */
public class UserOperationsTest {
  // create portfolio... already exists, nulls
  // view all portfolios...

  UserOperations uo = new UserOperationsImpl();

  @Test
  public void testCreatePortfolio() {
    uo.createPortfolio("abc");
    assertEquals("ABC\n", uo.viewAllPortfolios());
  }

  @Test
  public void testCreatePortfolio2() {
    uo.createPortfolio("abc");
    uo.createPortfolio("xyz");
    assertEquals("ABC\nXYZ\n", uo.viewAllPortfolios());
  }

  @Test
  public void testCreatePortfolioNull() {
    try {
      uo.createPortfolio(null);
      fail();
    } catch (Exception e) {
      assertEquals("Null Portfolio name!\n", e.getMessage());
    }
  }

  @Test
  public void testCreatePortfolioDuplicate() {
    try {
      uo.createPortfolio("Abc");
      uo.createPortfolio("abc");
      fail();
    } catch (Exception e) {
      assertEquals("Portfolio of same name already exists.\n", e.getMessage());
    }
  }


  // buy stock pname, sname, date, count... nulls
  // view portfolio sname date... nulls
  /*
  1.null values
  2. negative count. zero count.
  3. holdiays.
  4. wrong portfolio
  5. wrong stock.
  6. Multiple stocks, same, different.
  7. view empty, before a date, after a date, in between a date. single stock, multiple stock.
   */
  @Test
  public void buyStockNull1() {
    uo.createPortfolio("Abc");
    try {
      uo.buyStock(null, "GOOGL", helperGetDate("2011-01-25"),
          100, 10, BuyType.COUNT);
      fail();
    } catch (Exception e) {
      assertEquals("Null value passed!\n", e.getMessage());
    }
  }

  @Test
  public void buyStockNull2() {
    uo.createPortfolio("Abc");
    try {
      uo.buyStock("Abc", null, helperGetDate("2011-01-25"),
          100, 10, BuyType.COUNT);
      fail();
    } catch (Exception e) {
      assertEquals("Null value passed!\n", e.getMessage());
    }
  }

  @Test
  public void buyStockNull3() {
    uo.createPortfolio("Abc");
    try {
      uo.buyStock("Abc", "GOOGL", null, 100,
          10, BuyType.COUNT);
      fail();
    } catch (Exception e) {
      assertEquals("Null value passed!\n", e.getMessage());
    }
  }

  @Test
  public void buyStockZero() {
    uo.createPortfolio("Abc");
    try {
      uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-25"),
          0, 10, BuyType.COUNT);
      fail();
    } catch (Exception e) {
      assertEquals("Only positive non-Zero number of stocks allowed\n", e.getMessage());
    }
  }

  @Test
  public void buyStockHoliday() {
    uo.createPortfolio("Abc");
    try {
      uo.buyStock("Abc", "GOOGL", helperGetDate("2011-12-25"),
          100, 10, BuyType.COUNT);
      fail();
    } catch (Exception e) {
      assertEquals("Market was closed on Sun Dec 25 15:59:59 EST 2011.\n", e.getMessage());
    }
  }

  @Test
  public void buyStockWrongPortfolioName() {
    uo.createPortfolio("Abc");
    try {
      uo.buyStock("XYZ", "GOOGL", helperGetDate("2011-01-25"),
          100, 10, BuyType.COUNT);
      fail();
    } catch (Exception e) {
      assertEquals("This portfolio does not exist.\n", e.getMessage());
    }
  }

  @Test
  public void buyStockWrongStockName() {
    uo.createPortfolio("Abc");
    try {
      uo.buyStock("Abc", "DUNDERMIFLIN", helperGetDate("2011-01-25"),
          100, 10, BuyType.COUNT);
      fail();
    } catch (Exception e) {
      assertEquals("Stock Not Found\n", e.getMessage());
    }
  }

  @Test
  public void buyStock() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2018-11-26"), 1065.94,
        10, BuyType.AMOUNT);
    assertEquals("STOCK TICKER NAME: GOOGL\n"
            + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
            + "Mon Nov 26 15:59:59 EST 2018\t||\t\t\t1055.94\t\t\t||\t\t\t1.0\n"
            + "------------------------------------------------------------------------------------"
            + "------------------------------------------------------------------------------------"
            + "------------------------------------------",
        uo.viewPortfolio("Abc", helperGetDate("2018-11-30")));
  }

  @Test
  public void buyStockViewBeforeDate() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("\n\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "-------------------------------------------------------------------" +
            "-----------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2011-01-20")));
  }

  @Test
  public void buyTwoStocksSameType() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 2000,
        10, BuyType.AMOUNT);
    uo.buyStock("Abc", "GOOGL", helperGetDate("2013-01-22"), 1000,
        10, BuyType.AMOUNT);
    assertEquals("\n"
            + "\n"
            + "STOCK TICKER NAME: GOOGL\n"
            + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
            + "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t3.252537469558537\n"
            + "Tue Jan 22 15:59:59 EST 2013\t||\t\t\t702.87\t\t\t||\t\t\t1.4085108199240257\n"
            + "-------------------------------------------------------------------------------"
            + "-------------------------------------------------------------------------------"
            + "----------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2017-01-20")));
  }

  @Test
  public void buyTwoStocksSameTypeViewMiddle() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "GOOGL", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    assertEquals("\n\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "-------------------------------------------------------------------" +
            "-----------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2012-01-20")));
  }

  @Test
  public void buyTwoStocksDifferentType() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "AAPL", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    assertEquals("86417.0",
        String.valueOf(uo.evaluateNet("Abc", null,
            helperGetDate("2017-01-20"))));
  }

  @Test
  public void buyTwoStocksDifferentTypeViewMiddle() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "TSLA", helperGetDate("2016-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "AAPL", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    assertEquals("3261.6",
        String.valueOf(uo.evaluateNet("Abc", "AAPL",
            helperGetDate("2015-01-20"))));
    assertEquals("0.0",
        String.valueOf(uo.evaluateNet("Abc", "TSLA"
            , helperGetDate("2015-01-20"))));
  }

  @Test(expected = IllegalArgumentException.class)
  //Stock not found because query cant be run more than 5 times a minute.
  public void buyMultipleStocks() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "AAPL", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "GOOGL", helperGetDate("2016-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "TGT", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "GOOGL", helperGetDate("2014-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "BBBY", helperGetDate("2018-01-22"), 30,
        10, BuyType.COUNT);
    assertEquals("\n" +
            "\n" +
            "STOCK TICKER NAME: TGT\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Tue Jan 22 15:59:59 EST 2013\t||\t\t\t61.49\t\t\t||\t\t\t30.0\n" +
            "-----------------------------------------------------------------------------------" +
            "-----------------------------------------------------------------------------------" +
            "--------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "Tue Jan 21 15:59:59 EST 2014\t||\t\t\t1163.7\t\t\t||\t\t\t100.0\n" +
            "Thu Jan 21 15:59:59 EST 2016\t||\t\t\t726.67\t\t\t||\t\t\t100.0\n" +
            "---------------------------------------------------------------------------" +
            "---------------------------------------------------------------------------" +
            "------------------------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: AAPL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Tue Jan 22 15:59:59 EST 2013\t||\t\t\t504.77\t\t\t||\t\t\t30.0\n" +
            "-----------------------------------------------------------------------------------" +
            "-----------------------------------------------------------------------------------" +
            "--------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: BBBY\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t23.84\t\t\t||\t\t\t30.0\n" +
            "---------------------------------------------------------------------------------" +
            "---------------------------------------------------------------------------------" +
            "------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2018-11-15")));
  }


  @Test(expected = IllegalArgumentException.class)
  public void buyMultipleStocksViewMiddle() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "AAPL", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "GOOGL", helperGetDate("2016-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "TGT", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "GOOGL", helperGetDate("2014-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "BBBY", helperGetDate("2018-01-22"), 30,
        10, BuyType.COUNT);
    assertEquals("\n" +
            "\n" +
            "STOCK TICKER NAME: TGT\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Tue Jan 22 15:59:59 EST 2013\t||\t\t\t61.49\t\t\t||\t\t\t30.0\n" +
            "-----------------------------------------------------------------------------" +
            "-----------------------------------------------------------------------------" +
            "--------------------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "Tue Jan 21 15:59:59 EST 2014\t||\t\t\t1163.7\t\t\t||\t\t\t100.0\n" +
            "----------------------------------------------------------------------------" +
            "-----------------------------------------------------------------------------" +
            "---------------------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: AAPL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Tue Jan 22 15:59:59 EST 2013\t||\t\t\t504.77\t\t\t||\t\t\t30.0\n" +
            "------------------------------------------------------------------------------" +
            "------------------------------------------------------------------------------" +
            "------------------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: BBBY\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "-------------------------------------------------------------------------------" +
            "-------------------------------------------------------------------------------" +
            "----------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2014-11-15")));
  }


  @Test
  public void evalStockNullpName() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("61183.00000000001",
        String.valueOf(uo.evaluateNet("Abc", null,
            helperGetDate("2011-01-22"))));
    try {
      uo.evaluateNet(null, "GOOGL", helperGetDate("2012-01-21"));
    } catch (Exception e) {
      assertEquals("Null value passed!\n", e.getMessage());
    }
  }

  @Test
  public void evalStockWrongpName() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("61183.00000000001",
        String.valueOf(uo.evaluateNet("Abc", null,
            helperGetDate("2011-01-22"))));
    try {
      uo.evaluateNet("XYZ", "GOOGL", helperGetDate("2012-01-21"));
    } catch (Exception e) {
      assertEquals("Portfolio of this name does not exist.\n", e.getMessage());
    }
  }

  @Test
  public void evalStockWrongsName() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("61183.00000000001",
        String.valueOf(uo.evaluateNet("Abc", null,
            helperGetDate("2011-01-22"))));
    try {
      uo.evaluateNet("Abc", "TGT", helperGetDate("2012-01-21"));
    } catch (Exception e) {
      assertEquals("This stock is not present in this portfolio.\n", e.getMessage());
    }
  }

  @Test
  public void evalStockNulldate() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("61183.00000000001",
        String.valueOf(uo.evaluateNet("Abc", null,
            helperGetDate("2011-01-22"))));
    try {
      uo.evaluateNet("Abc", "GOOGL", null);
    } catch (Exception e) {
      assertEquals("Null value passed!\n", e.getMessage());
    }
  }

  @Test
  public void valStockNullpName() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("\n\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "-------------------------------------------------------------------" +
            "-----------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2011-01-22")));
    try {
      uo.amountInvested(null, "GOOGL", helperGetDate("2012-01-21"));
    } catch (Exception e) {
      assertEquals("Null value passed!\n", e.getMessage());
    }
  }

  @Test
  public void valStockWrongpName() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("\n\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "-------------------------------------------------------------------" +
            "-----------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2011-01-22")));
    try {
      uo.evaluateNet("XYZ", "GOOGL", helperGetDate("2012-01-21"));
    } catch (Exception e) {
      assertEquals("Portfolio of this name does not exist.\n", e.getMessage());
    }
  }

  @Test
  public void valStockWrongsName() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("\n\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "-------------------------------------------------------------------" +
            "-----------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2011-01-22")));
    try {
      uo.evaluateNet("Abc", "TGT", helperGetDate("2012-01-21"));
    } catch (Exception e) {
      assertEquals("This stock is not present in this portfolio.\n", e.getMessage());
    }
  }

  @Test
  public void valStockNulldate() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("\n\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "-------------------------------------------------------------------" +
            "-----------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2011-01-22")));
    try {
      uo.amountInvested("Abc", "GOOGL", null);
    } catch (Exception e) {
      assertEquals("Null value passed!\n", e.getMessage());
    }
  }

  @Test
  public void evalSingle() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("\n\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "-------------------------------------------------------------------" +
            "-----------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2011-01-22")));
    assertEquals(58599.0, uo.evaluateNet("Abc", "GOOGL",
        helperGetDate("2012-01-22")), 0.001);
  }

  @Test
  public void valSingle() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("\n\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "-------------------------------------------------------------------" +
            "-----------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2011-01-22")));
    assertEquals(61193.000, uo.amountInvested("Abc", "GOOGL",
        helperGetDate("2012-01-22")), 0.001);
  }

  @Test
  public void evalHoliday() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("\n\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "-------------------------------------------------------------------" +
            "-----------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2011-01-22")));
    assertEquals(70950.0, uo.evaluateNet("Abc", "GOOGL",
        helperGetDate("2012-12-25")), 0.001);
  }

  @Test
  public void valHoliday() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    assertEquals("\n\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "-------------------------------------------------------------------" +
            "-----------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2011-01-22")));
    assertEquals(61193.00, uo.amountInvested("Abc", "GOOGL",
        helperGetDate("2012-12-25")), 0.001);
  }

  @Test
  public void evalNullStockName() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "AAPL", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    assertEquals("\n" +
            "\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "---------------------------------------------------------------------" +
            "---------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: AAPL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "-----------------------------------------------------------------" +
            "--------------------------------------------------------------------" +
            "--------------------------------------------------------------------" +
            "---------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2012-01-20")));
    assertEquals(106885.999, uo.evaluateNet("Abc", null,
        helperGetDate("2017-12-25")), 0.01);
  }

  @Test
  public void valNullStockName() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "AAPL", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    assertEquals("\n" +
            "\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "---------------------------------------------------------------------" +
            "---------------------------------------------------------------------" +
            "------------------------------------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: AAPL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "-----------------------------------------------------------------" +
            "--------------------------------------------------------------------" +
            "--------------------------------------------------------------------" +
            "---------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2012-01-20")));
    assertEquals(76346.1, uo.amountInvested("Abc", null,
        helperGetDate("2017-12-25")), 0.01);
  }

  @Test
  public void evalStockMultipleDates() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "AAPL", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "GOOGL", helperGetDate("2016-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "TGT", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "GOOGL", helperGetDate("2014-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "BBBY", helperGetDate("2018-01-22"), 30,
        10, BuyType.COUNT);
    assertEquals("\n" +
            "\n" +
            "STOCK TICKER NAME: TGT\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Tue Jan 22 15:59:59 EST 2013\t||\t\t\t61.49\t\t\t||\t\t\t30.0\n" +
            "-----------------------------------------------------------------------------------" +
            "-----------------------------------------------------------------------------------" +
            "--------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "Tue Jan 21 15:59:59 EST 2014\t||\t\t\t1163.7\t\t\t||\t\t\t100.0\n" +
            "Thu Jan 21 15:59:59 EST 2016\t||\t\t\t726.67\t\t\t||\t\t\t100.0\n" +
            "-----------------------------------------------------------------------------------" +
            "-----------------------------------------------------------------------------------" +
            "--------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: AAPL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Tue Jan 22 15:59:59 EST 2013\t||\t\t\t504.77\t\t\t||\t\t\t30.0\n" +
            "-----------------------------------------------------------------------------------" +
            "-----------------------------------------------------------------------------------" +
            "--------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: BBBY\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t23.84\t\t\t||\t\t\t30.0\n" +
            "---------------------------------------------------------------------------------" +
            "---------------------------------------------------------------------------------" +
            "------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2018-11-15")));
    assertEquals(0.0, uo.amountInvested("Abc", null,
        helperGetDate("2010-12-25")), 0.01);
    assertEquals(78170.8, uo.amountInvested("Abc", null,
        helperGetDate("2013-12-25")), 0.01);
    assertEquals(267207.8, uo.amountInvested("Abc", null,
        helperGetDate("2017-12-25")), 0.01);
    assertEquals(0.0, uo.amountInvested("Abc", "GOOGL",
        helperGetDate("2010-12-25")), 0.01);
    assertEquals(61183.0, uo.amountInvested("Abc", "GOOGL",
        helperGetDate("2013-12-25")), 0.01);
    assertEquals(250220.0, uo.amountInvested("Abc", "GOOGL",
        helperGetDate("2017-12-25")), 0.01);
  }

  @Test
  public void valStockMultipleDates() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2011-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "AAPL", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "GOOGL", helperGetDate("2016-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "TGT", helperGetDate("2013-01-22"), 30,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "GOOGL", helperGetDate("2014-01-21"), 100,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "BBBY", helperGetDate("2018-01-22"), 30,
        10, BuyType.COUNT);
    assertEquals("\n" +
            "\n" +
            "STOCK TICKER NAME: TGT\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Tue Jan 22 15:59:59 EST 2013\t||\t\t\t61.49\t\t\t||\t\t\t30.0\n" +
            "-----------------------------------------------------------------------------------"
            + "-----------------------------------------------------------------------------------"
            + "--------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: GOOGL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Fri Jan 21 15:59:59 EST 2011\t||\t\t\t611.83\t\t\t||\t\t\t100.0\n" +
            "Tue Jan 21 15:59:59 EST 2014\t||\t\t\t1163.7\t\t\t||\t\t\t100.0\n" +
            "Thu Jan 21 15:59:59 EST 2016\t||\t\t\t726.67\t\t\t||\t\t\t100.0\n" +
            "-----------------------------------------------------------------------------------"
            + "-----------------------------------------------------------------------------------"
            + "--------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: AAPL\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Tue Jan 22 15:59:59 EST 2013\t||\t\t\t504.77\t\t\t||\t\t\t30.0\n" +
            "-----------------------------------------------------------------------------------"
            + "-----------------------------------------------------------------------------------"
            + "--------------------------------------------\n" +
            "\n" +
            "\n" +
            "\n" +
            "STOCK TICKER NAME: BBBY\n" +
            "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n" +
            "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t23.84\t\t\t||\t\t\t30.0\n"
            + "---------------------------------------------------------------------------------"
            + "---------------------------------------------------------------------------------"
            + "------------------------------------------------\n\n",
        uo.viewPortfolio("Abc", helperGetDate("2018-11-15")));
    assertEquals(0.0, uo.evaluateNet("Abc", null,
        helperGetDate("2010-12-25")), 0.01);
    assertEquals(130065.4, uo.evaluateNet("Abc", null,
        helperGetDate("2013-12-25")), 0.01);
    assertEquals(327869.99, uo.evaluateNet("Abc", null,
        helperGetDate("2017-12-25")), 0.01);
    assertEquals(0.0, uo.evaluateNet("Abc", "GOOGL",
        helperGetDate("2010-12-25")), 0.01);
    assertEquals(111183.99, uo.evaluateNet("Abc", "GOOGL",
        helperGetDate("2013-12-25")), 0.01);
    assertEquals(320657.99, uo.evaluateNet("Abc", "GOOGL",
        helperGetDate("2017-12-25")), 0.01);
  }


  // search string... null, wrong, correct.
  @Test //(expected = NullPointerException.class)
  public void testSearchNull() {
    try {
      uo.search(null);
      fail();
    } catch (Exception e) {
      assertEquals("Stock Not Found\n", e.getMessage());
    }
  }

  @Test
  public void testSearchDoesntExist() {
    try {
      uo.search("DUNDERMIFLIN");
      fail();
    } catch (Exception e) {
      assertEquals("Stock Not Found\n", e.getMessage());
    }
  }

  @Test
  public void testWeightedInvestmentEqual() {
    uo.createPortfolio("Abc");
    uo.buyStock("Abc", "GOOGL", helperGetDate("2018-01-22"), 1,
        10, BuyType.COUNT);
    uo.buyStock("Abc", "AAPL", helperGetDate("2018-01-22"), 1,
        10, BuyType.COUNT);

    uo.weightedInvestEqual("Abc", helperGetDate("2018-1-23"), 2000,
        100);
    assertEquals("\n"
            + "\n"
            + "STOCK TICKER NAME: GOOGL\n"
            + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
            + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t1164.16\t\t\t||\t\t\t1.0\n"
            + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t1176.17\t\t\t||\t\t\t0.765195507452154\n"
            + "-----------------------------------------------------" +
            "-----------------------------------"
            + "------------------------------------------------------" +
            "----------------------------------"
            + "----------------------------------\n"
            + "\n"
            + "\n"
            + "\n"
            + "STOCK TICKER NAME: AAPL\n"
            + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
            + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t177.0\t\t\t||\t\t\t1.0\n"
            + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t177.04\t\t\t||\t\t\t5.08359692724808\n"
            + "----------------------------------------------------------------------"
            + "----------------------------------------------------------------------"
            + "----------------------------------------------------------------------\n\n"
        , uo.viewPortfolio("Abc", helperGetDate("2018-2-2")));
  }

  @Test
  public void testWeightedInvestmentEqualFANG() {
    uo.createPortfolio("FANG");
    uo.buyStock("FANG", "FB", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "AAPL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "NFLX", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "GOOGL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    try {
      TimeUnit.SECONDS.sleep(45);
    } catch (InterruptedException e) {

    }

    uo.weightedInvestEqual("FANG", helperGetDate("2018-1-23"), 10000,
        10);
    assertEquals(
        "\n"
            + "\n"
            + "STOCK TICKER NAME: NFLX\n"
            + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
            + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t227.58\t\t\t||\t\t\t0.4394059231918446\n"
            + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t250.29\t\t\t||\t\t\t9.94845978664749\n"
            + "-----------------------------------------------------------------------------------"
            + "-----------------------------------------------------------------------------------"
            + "--------------------------------------------\n"
            + "\n"
            + "\n"
            + "\n"
            + "STOCK TICKER NAME: GOOGL\n"
            + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
            + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t1164.16\t\t\t||\t\t\t0.08589884551951621\n"
            + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t1176.17\t\t\t||\t\t\t2.1170409039509592\n"
            + "-----------------------------------------------------------------------------------"
            + "-----------------------------------------------------------------------------------"
            + "--------------------------------------------\n"
            + "\n"
            + "\n"
            + "\n"
            + "STOCK TICKER NAME: AAPL\n"
            + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
            + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t177.0\t\t\t||\t\t\t0.5649717514124294\n"
            + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t177.04\t\t\t||\t\t\t14.064618165386355\n"
            + "-----------------------------------------------------------------------------------"
            + "-----------------------------------------------------------------------------------"
            + "--------------------------------------------\n"
            + "\n"
            + "\n"
            + "\n"
            + "STOCK TICKER NAME: FB\n"
            + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
            + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t185.37\t\t\t||\t\t\t0.5394616173059287\n"
            + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t189.35\t\t\t||\t\t\t13.150250858199103\n"
            + "-----------------------------------------------------------------------------------"
            + "-----------------------------------------------------------------------------------"
            + "--------------------------------------------\n"
            + "\n", uo.viewPortfolio("FANG", helperGetDate("2018-2-2")));
  }


  @Test
  public void testWeightedInvestmentEqualNull() {
    uo.createPortfolio("FANG");
    try {
      uo.buyStock("FANG", "AAPL", helperGetDate("2018-2-2"), 100,
          20, null);
    } catch (IllegalArgumentException e) {
      assertEquals("Enter a valid buy type", e.getMessage());
    }

  }

  @Test
  public void testWeightedInvestmentEqualOneStock() {
    uo.createPortfolio("Apple");
    uo.buyStock("Apple", "AAPL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.weightedInvestEqual("Apple", helperGetDate("2018-01-23"), 2000,
        0);
    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t177.0\t\t\t||\t\t\t0.5649717514124294\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t177.04\t\t\t||\t\t\t11.296882060551289\n"
        + "--------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "-------------------------------------\n"
        + "\n", uo.viewPortfolio("Apple", helperGetDate("2018-01-24")));
  }

  @Test
  public void testWeightedInvestmentUnequal() {
    uo.createPortfolio("FANG");
    uo.buyStock("FANG", "FB", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "AAPL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "NFLX", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "GOOGL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    HashMap<String, Double> unequalSplit = new HashMap<>();
    unequalSplit.put("FB", 10.0);
    unequalSplit.put("AAPL", 20.0);
    unequalSplit.put("NFLX", 30.0);
    unequalSplit.put("GOOGL", 40.0);

    try {
      TimeUnit.SECONDS.sleep(45);
    } catch (InterruptedException e) {

    }

    uo.weightedInvestmentUnequal("FANG", unequalSplit, 10000,
        helperGetDate("2018-01-23"), 10);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t227.58\t\t\t||\t\t\t0.4394059231918446\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t250.29\t\t\t||\t\t\t1198.5696591953335\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t1164.16\t\t\t||\t\t\t0.08589884551951621\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t1176.17\t\t\t||\t\t\t340.0783900286523\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t177.0\t\t\t||\t\t\t0.5649717514124294\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t177.04\t\t\t||\t\t\t1129.631721644826\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t185.37\t\t\t||\t\t\t0.5394616173059287\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t189.35\t\t\t||\t\t\t528.0697121732242\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n", uo.viewPortfolio("FANG", helperGetDate("2018-01-25")));
  }


  @Test
  public void testWeightedInvestmentUnequalEmptyPortfolio() {
    uo.createPortfolio("FANG");
    HashMap<String, Double> unequalSplit = new HashMap<>();
    unequalSplit.put("FB", 10.0);
    unequalSplit.put("AAPL", 20.0);
    unequalSplit.put("NFLX", 30.0);
    unequalSplit.put("GOOGL", 40.0);

    uo.weightedInvestmentUnequal("FANG", unequalSplit, 10000,
        helperGetDate("2018-01-23"), 10);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t250.29\t\t\t||\t\t\t1198.5696591953335\n"
        + "---------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "-----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t1176.17\t\t\t||\t\t\t340.0783900286523\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t177.04\t\t\t||\t\t\t1129.631721644826\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t189.35\t\t\t||\t\t\t528.0697121732242\n"
        + "---------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "-----------------------------------\n"
        + "\n", uo.viewPortfolio("FANG", helperGetDate("2018-01-25")));
  }

  @Test
  public void testWeightedInvestmentUnequalFANGPortfolio() {

    uo.createPortfolio("FANG");
    uo.buyStock("FANG", "FB", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "AAPL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "NFLX", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "GOOGL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    try {
      TimeUnit.SECONDS.sleep(45);
    } catch (InterruptedException e) {

    }
    HashMap<String, Double> unequalSplit = new HashMap<>();
    unequalSplit.put("FB", 10.0);
    unequalSplit.put("AAPL", 20.0);
    unequalSplit.put("NFLX", 30.0);
    unequalSplit.put("GOOGL", 40.0);

    uo.weightedInvestmentUnequal("FANG", unequalSplit, 10000,
        helperGetDate("2018-01-23"), 10);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t227.58\t\t\t||\t\t\t0.4394059231918446\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t250.29\t\t\t||\t\t\t1198.5696591953335\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t1164.16\t\t\t||\t\t\t0.08589884551951621\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t1176.17\t\t\t||\t\t\t340.0783900286523\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t177.0\t\t\t||\t\t\t0.5649717514124294\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t177.04\t\t\t||\t\t\t1129.631721644826\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t185.37\t\t\t||\t\t\t0.5394616173059287\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t189.35\t\t\t||\t\t\t528.0697121732242\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n", uo.viewPortfolio("FANG", helperGetDate("2018-01-25")));
  }


  @Test
  public void testWeightedInvestmentUnequalAddFANG() {
    uo.createPortfolio("FANG");
    HashMap<String, Double> unequalSplit = new HashMap<>();
    unequalSplit.put("FB", 10.0);
    unequalSplit.put("AAPL", 20.0);
    unequalSplit.put("NFLX", 30.0);
    unequalSplit.put("GOOGL", 40.0);

    uo.weightedInvestmentUnequal("FANG", unequalSplit, 10000,
        helperGetDate("2018-01-23"), 10);

    try {
      TimeUnit.SECONDS.sleep(45);
    } catch (InterruptedException e) {

    }

    uo.buyStock("FANG", "FB", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "AAPL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "NFLX", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "GOOGL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t227.58\t\t\t||\t\t\t0.4394059231918446\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t250.29\t\t\t||\t\t\t1198.5696591953335\n"
        + "---------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "-----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t1164.16\t\t\t||\t\t\t0.08589884551951621\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t1176.17\t\t\t||\t\t\t340.0783900286523\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t177.0\t\t\t||\t\t\t0.5649717514124294\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t177.04\t\t\t||\t\t\t1129.631721644826\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t185.37\t\t\t||\t\t\t0.5394616173059287\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t189.35\t\t\t||\t\t\t528.0697121732242\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n", uo.viewPortfolio("FANG", helperGetDate("2018-01-25")));
  }

  @Test
  public void testEqualAndUnequal() {
    uo.createPortfolio("FANG");
    HashMap<String, Double> unequalSplit = new HashMap<>();
    unequalSplit.put("FB", 10.0);
    unequalSplit.put("AAPL", 20.0);
    unequalSplit.put("NFLX", 30.0);
    unequalSplit.put("GOOGL", 40.0);

    uo.weightedInvestmentUnequal("FANG", unequalSplit, 10000,
        helperGetDate("2018-01-23"), 10);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t250.29\t\t\t||\t\t\t1198.5696591953335\n"
        + "--------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "-------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t1176.17\t\t\t||\t\t\t340.0783900286523\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t177.04\t\t\t||\t\t\t1129.631721644826\n"
        + "----------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "-----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t189.35\t\t\t||\t\t\t528.0697121732242\n"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------\n"
        + "\n", uo.viewPortfolio("FANG",
        helperGetDate("2018-01-24")));

    try {
      TimeUnit.SECONDS.sleep(30);
    } catch (InterruptedException e) {

    }

    uo.weightedInvestEqual("FANG", helperGetDate("2018-01-24"),
        10000, 10);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t250.29\t\t\t||\t\t\t1198.5696591953335\n"
        + "Wed Jan 24 15:59:59 EST 2018\t||\t\t\t261.3\t\t\t||\t\t\t9.529276693455797\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t1176.17\t\t\t||\t\t\t340.0783900286523\n"
        + "Wed Jan 24 15:59:59 EST 2018\t||\t\t\t1171.29\t\t\t||\t\t\t2.125861229925979\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t177.04\t\t\t||\t\t\t1129.631721644826\n"
        + "Wed Jan 24 15:59:59 EST 2018\t||\t\t\t174.22\t\t\t||\t\t\t14.292274136149695\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t189.35\t\t\t||\t\t\t528.0697121732242\n"
        + "Wed Jan 24 15:59:59 EST 2018\t||\t\t\t186.55\t\t\t||\t\t\t13.347627981774323\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n\n", uo.viewPortfolio("FANG", helperGetDate("2018-01-25")));
  }

  @Test
  public void testBuyOtherThanFangEqualWeights() {
    uo.createPortfolio("FANGPlus");

    uo.buyStock("FANGPlus", "FB", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANGPlus", "AAPL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANGPlus", "NFLX", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANGPlus", "GOOGL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);

    try {
      TimeUnit.SECONDS.sleep(45);
    } catch (InterruptedException e) {

    }

    uo.weightedInvestEqual("FANGPlus", helperGetDate("2018-01-24"),
        10000, 10);

    uo.buyStock("FANGPlus", "TSLA", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t227.58\t\t\t||\t\t\t0.4394059231918446\n"
        + "Wed Jan 24 15:59:59 EST 2018\t||\t\t\t261.3\t\t\t||\t\t\t9.529276693455797\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t1164.16\t\t\t||\t\t\t0.08589884551951621\n"
        + "Wed Jan 24 15:59:59 EST 2018\t||\t\t\t1171.29\t\t\t||\t\t\t2.125861229925979\n"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t177.0\t\t\t||\t\t\t0.5649717514124294\n"
        + "Wed Jan 24 15:59:59 EST 2018\t||\t\t\t174.22\t\t\t||\t\t\t14.292274136149695\n"
        + "---------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "-----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: TSLA\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t351.56\t\t\t||\t\t\t0.2844464671748777\n"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t185.37\t\t\t||\t\t\t0.5394616173059287\n"
        + "Wed Jan 24 15:59:59 EST 2018\t||\t\t\t186.55\t\t\t||\t\t\t13.347627981774323\n"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------\n"
        + "\n", uo.viewPortfolio("FANGPlus",
        helperGetDate("2018-01-25")));

  }

  @Test
  public void testBuyOtherThanFangUnequalWeights() {
    uo.createPortfolio("FANG");
    HashMap<String, Double> unequalSplit = new HashMap<>();
    unequalSplit.put("FB", 10.0);
    unequalSplit.put("AAPL", 20.0);
    unequalSplit.put("NFLX", 30.0);
    unequalSplit.put("GOOGL", 40.0);

    uo.weightedInvestmentUnequal("FANG", unequalSplit, 10000,
        helperGetDate("2018-01-23"), 10);

    uo.buyStock("FANG", "TSLA", helperGetDate("2018-01-22"), 100,
        0, BuyType.COUNT);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t250.29\t\t\t||\t\t\t1198.5696591953335\n"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t1176.17\t\t\t||\t\t\t340.0783900286523\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t177.04\t\t\t||\t\t\t1129.631721644826\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: TSLA\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t351.56\t\t\t||\t\t\t100.0\n"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Jan 23 15:59:59 EST 2018\t||\t\t\t189.35\t\t\t||\t\t\t528.0697121732242\n"
        + "--------------------------------------------------------------------------------------"
        + "--------------------------------------------------------------------------------------"
        + "--------------------------------------\n"
        + "\n", uo.viewPortfolio("FANG", helperGetDate("2018-01-25")));

  }

  @Test
  public void testEqualsStrategy() {
    uo.createPortfolio("FANG");
    uo.buyStock("FANG", "FB", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "AAPL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "NFLX", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "GOOGL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);

    try {
      TimeUnit.SECONDS.sleep(45);
    } catch (InterruptedException e) {

    }
    uo.createEqualStrategy("FANG", helperGetDate("2018-11-1"),
        helperGetDate("2018-11-28"), 7, 1000, 10);
    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t227.58\t\t\t||\t\t\t0.4394059231918446\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t317.38\t\t\t||\t\t\t0.7561913164030499\n"
        + "Thu Nov 08 15:59:59 EST 2018\t||\t\t\t317.92\t\t\t||\t\t\t1.5098137896326118\n"
        + "Thu Nov 15 15:59:59 EST 2018\t||\t\t\t290.06\t\t\t||\t\t\t1.6548300351651382\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t258.82\t\t\t||\t\t\t0.9272853720732556\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t1164.16\t\t\t||\t\t\t0.08589884551951621\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t1085.98\t\t\t||\t\t\t0.22099854509291147\n"
        + "Thu Nov 08 15:59:59 EST 2018\t||\t\t\t1094.63\t\t\t||\t\t\t0.21925216739902978\n"
        + "Thu Nov 15 15:59:59 EST 2018\t||\t\t\t1071.05\t\t\t||\t\t\t0.44815834928341347\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t1030.1\t\t\t||\t\t\t0.23298708863217166\n"
        + "---------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "-----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t177.0\t\t\t||\t\t\t0.5649717514124294\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t222.22\t\t\t||\t\t\t1.080010800108001\n"
        + "Thu Nov 08 15:59:59 EST 2018\t||\t\t\t208.49\t\t\t||\t\t\t1.1511343469710777\n"
        + "Thu Nov 15 15:59:59 EST 2018\t||\t\t\t191.41\t\t\t||\t\t\t1.2538529857374223\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t172.29\t\t\t||\t\t\t1.3930001741250218\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t185.37\t\t\t||\t\t\t0.5394616173059287\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t151.75\t\t\t||\t\t\t1.5815485996705108\n"
        + "Thu Nov 08 15:59:59 EST 2018\t||\t\t\t147.87\t\t\t||\t\t\t1.6230472712517752\n"
        + "Thu Nov 15 15:59:59 EST 2018\t||\t\t\t143.85\t\t\t||\t\t\t1.6684045881126173\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t131.73\t\t\t||\t\t\t1.821908449100433\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n", uo.viewPortfolio("FANG", helperGetDate("2018-11-29")));

  }

  @Test
  public void testEqualsStrategyBeforeEndDate() {
    uo.createPortfolio("FANG");
    uo.buyStock("FANG", "FB", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "AAPL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "NFLX", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "GOOGL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);

    try {
      TimeUnit.SECONDS.sleep(45);
    } catch (InterruptedException e) {

    }
    uo.createEqualStrategy("FANG", helperGetDate("2018-11-1"),
        helperGetDate("2018-11-28"), 10, 1000, 10);
    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t227.58\t\t\t||\t\t\t0.4394059231918446\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t317.38\t\t\t||\t\t\t0.7561913164030499\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t294.07\t\t\t||\t\t\t0.8161322134185738\n"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t1164.16\t\t\t||\t\t\t0.08589884551951621\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t1085.98\t\t\t||\t\t\t0.22099854509291147\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t1049.36\t\t\t||\t\t\t0.22871083326980257\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t177.0\t\t\t||\t\t\t0.5649717514124294\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t222.22\t\t\t||\t\t\t1.080010800108001\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t194.17\t\t\t||\t\t\t1.2360302827419272\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t185.37\t\t\t||\t\t\t0.5394616173059287\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t151.75\t\t\t||\t\t\t1.5815485996705108\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t141.55\t\t\t||\t\t\t1.695513952666902\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n", uo.viewPortfolio("FANG", helperGetDate("2018-11-14")));

  }

  @Test
  public void testUnEqualStrategy() {
    uo.createPortfolio("FANG");
    HashMap<String, Double> unequalSplit = new HashMap<>();
    unequalSplit.put("FB", 10.0);
    unequalSplit.put("AAPL", 20.0);
    unequalSplit.put("NFLX", 30.0);
    unequalSplit.put("GOOGL", 40.0);

    uo.createUnequalStrategy("FANG", unequalSplit, helperGetDate("2018-11-1"),
        helperGetDate("2018-11-28"), 10, 10000, 10);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t317.38\t\t\t||\t\t\t1.0187577457096646\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t294.07\t\t\t||\t\t\t2.199022908377824\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t258.82\t\t\t||\t\t\t2.498518919197383\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t1085.98\t\t\t||\t\t\t0.22099854509291147\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t1049.36\t\t\t||\t\t\t0.22871083326980257\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t1030.1\t\t\t||\t\t\t0.23298708863217166\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t222.22\t\t\t||\t\t\t2.205022050220502\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t194.17\t\t\t||\t\t\t2.523561827264768\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t172.29\t\t\t||\t\t\t2.8440420221719194\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t151.75\t\t\t||\t\t\t6.523887973640857\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t141.55\t\t\t||\t\t\t6.993995054750971\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t131.73\t\t\t||\t\t\t7.515372352539286\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n", uo.viewPortfolio("FANG", helperGetDate("2018-11-29")));
  }

  @Test
  public void testUnEqualStrategyBeforeEndDate() {
    uo.createPortfolio("FANG");
    HashMap<String, Double> unequalSplit = new HashMap<>();
    unequalSplit.put("FB", 10.0);
    unequalSplit.put("AAPL", 20.0);
    unequalSplit.put("NFLX", 30.0);
    unequalSplit.put("GOOGL", 40.0);

    uo.createUnequalStrategy("FANG", unequalSplit, helperGetDate("2018-11-1"),
        helperGetDate("2018-11-28"), 10, 10000, 10);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t317.38\t\t\t||\t\t\t1.0187577457096646\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t294.07\t\t\t||\t\t\t1.099511454188912\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t1085.98\t\t\t||\t\t\t0.22099854509291147\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t1049.36\t\t\t||\t\t\t0.22871083326980257\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t222.22\t\t\t||\t\t\t2.205022050220502\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t194.17\t\t\t||\t\t\t2.523561827264768\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t151.75\t\t\t||\t\t\t6.523887973640857\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t141.55\t\t\t||\t\t\t6.993995054750971\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n\n", uo.viewPortfolio("FANG", helperGetDate("2018-11-14")));
  }

  @Test
  public void testEqualStrategyWithAddStock() {
    uo.createPortfolio("FANG");
    uo.buyStock("FANG", "FB", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "AAPL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "NFLX", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);
    uo.buyStock("FANG", "GOOGL", helperGetDate("2018-01-22"), 100,
        0, BuyType.AMOUNT);

    try {
      TimeUnit.SECONDS.sleep(45);
    } catch (InterruptedException e) {

    }
    uo.createEqualStrategy("FANG", helperGetDate("2018-11-1"),
        helperGetDate("2018-11-28"), 7, 1000, 10);
    uo.buyStock("FANG", "TSLA", helperGetDate("2018-10-22"), 100,
        0, BuyType.AMOUNT);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t227.58\t\t\t||\t\t\t0.4394059231918446\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t317.38\t\t\t||\t\t\t1.1973029176381624\n"
        + "Thu Nov 08 15:59:59 EST 2018\t||\t\t\t317.92\t\t\t||\t\t\t0.5976346250629089\n"
        + "Fri Nov 09 15:59:59 EST 2018\t||\t\t\t303.47\t\t\t||\t\t\t0.6260915411737569\n"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t1164.16\t\t\t||\t\t\t0.08589884551951621\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t1085.98\t\t\t||\t\t\t0.3499143630637765\n"
        + "Fri Nov 09 15:59:59 EST 2018\t||\t\t\t1077.02\t\t\t||\t\t\t0.1764126942860857\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t177.0\t\t\t||\t\t\t0.5649717514124294\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t222.22\t\t\t||\t\t\t1.7100171001710016\n"
        + "Fri Nov 09 15:59:59 EST 2018\t||\t\t\t204.47\t\t\t||\t\t\t0.9292316721279406\n"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------------------------------------------------------------"
        + "----------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: TSLA\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Oct 22 15:59:59 EDT 2018\t||\t\t\t260.95\t\t\t||\t\t\t0.38321517532094274\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t344.28\t\t\t||\t\t\t1.1037527593818985\n"
        + "Fri Nov 09 15:59:59 EST 2018\t||\t\t\t350.51\t\t\t||\t\t\t0.5420672734016148\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Mon Jan 22 15:59:59 EST 2018\t||\t\t\t185.37\t\t\t||\t\t\t0.5394616173059287\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t151.75\t\t\t||\t\t\t1.2520593080724876\n"
        + "Fri Nov 09 15:59:59 EST 2018\t||\t\t\t144.96\t\t\t||\t\t\t1.3107064017660044\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n", uo.viewPortfolio("FANG", helperGetDate("2018-11-14")));
  }

  @Test
  public void testUnequalWithAddStock() {
    uo.createPortfolio("FANG");
    HashMap<String, Double> unequalSplit = new HashMap<>();
    unequalSplit.put("FB", 10.0);
    unequalSplit.put("AAPL", 20.0);
    unequalSplit.put("NFLX", 30.0);
    unequalSplit.put("GOOGL", 40.0);

    uo.createUnequalStrategy("FANG", unequalSplit, helperGetDate("2018-11-1"),
        helperGetDate("2018-11-28"), 10, 10000, 10);

    uo.buyStock("FANG", "TSLA", helperGetDate("2018-11-20"), 100,
        0, BuyType.COUNT);

    assertEquals("\n"
        + "\n"
        + "STOCK TICKER NAME: NFLX\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t317.38\t\t\t||\t\t\t1.0187577457096646\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t294.07\t\t\t||\t\t\t1.099511454188912\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t258.82\t\t\t||\t\t\t1.2492594595986914\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: GOOGL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t1085.98\t\t\t||\t\t\t0.22099854509291147\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t1049.36\t\t\t||\t\t\t0.22871083326980257\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t1030.1\t\t\t||\t\t\t0.23298708863217166\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: AAPL\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t222.22\t\t\t||\t\t\t2.205022050220502\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t194.17\t\t\t||\t\t\t2.523561827264768\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t172.29\t\t\t||\t\t\t2.8440420221719194\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: TSLA\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Tue Nov 20 15:59:59 EST 2018\t||\t\t\t347.49\t\t\t||\t\t\t100.0\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n"
        + "\n"
        + "\n"
        + "STOCK TICKER NAME: FB\n"
        + "DATE\t\t\t\t||\t\t\tCOST BASIS\t\t||\t\t\tNO. OF SHARES\n"
        + "Thu Nov 01 15:59:59 EDT 2018\t||\t\t\t151.75\t\t\t||\t\t\t6.523887973640857\n"
        + "Mon Nov 12 15:59:59 EST 2018\t||\t\t\t141.55\t\t\t||\t\t\t6.993995054750971\n"
        + "Fri Nov 23 15:59:59 EST 2018\t||\t\t\t131.73\t\t\t||\t\t\t7.515372352539286\n"
        + "---------------------------------------------------------------------------------------"
        + "---------------------------------------------------------------------------------------"
        + "------------------------------------\n"
        + "\n\n", uo.viewPortfolio("FANG", helperGetDate("2018-11-28")));

  }

  @Test
  public void testSavePortfolio() {
    uo.createPortfolio("B");
    uo.savePortfolio("B", "savedPortfolio2");
    uo.buyStock("B", "AAPL", helperGetDate("2018-11-12"), 1000,
        1, BuyType.AMOUNT);
    uo.savePortfolio("B", "savedPortfolio2");
    uo.loadPortfolio("savedPortfolio", "testPortfolio");
    assertEquals("START OF STOCKS\n"
        + "-STOCK\n"
        + "AAPL\n"
        + "2018-11-12,5.144976051913273,1.0\n"
        + "END OF STOCK\n"
        + "END OF STOCKS\n"
        + "START OF STRATEGIES\n"
        + "END OF STRATEGIES\n"
        + "END OF PORTFOLIO", helperDisplay(uo.viewPortfolio("testPortfolio",
        helperGetDate("2018-11-13"))));

  }

  @Test
  public void testRetrievePortfolio() {
    uo.loadPortfolio("test1", "testPortfolio");
    assertEquals("Portfolio: TESTPORTFOLIO\n"
        + "_______________________________________________________________________________________"
        + "_________________________\n"
        + "\n"
        + "\n"
        + "Ticker: AAPL\n"
        + "Amount invested in stock: $1999.9999999999998\n"
        + "Valuation of stock: $1958.2373178142864\n"
        + "2018 November 12, Monday       \t\t|\t\t194.17  \t\t|\t\t10.186949580264717\n"
        + "_______________________________________________________________________________________"
        + "_________________________\n"
        + "\n"
        + "Valuation of portfolio: 1958.2373178142864\n"
        + "Cost Basis of portfolio: 1999.9999999999998\n"
        + "_______________________________________________________________________________________"
        + "_________________________\n", helperDisplay(uo.viewPortfolio("testPortfolio",
        helperGetDate("2018-11-13"))));

  }


  @Test
  public void testRetrieveStrategy() {
    uo.createPortfolio("testPortfolio");
    uo.savePortfolio("testPortfolio", "portfolioX");
    uo.loadStrategy("STRATA1", "testPortfolio");
    uo.savePortfolio("testPortfolio", "Strategy1");
    String st = new String("");
    try {
      FileReader fr = new FileReader("Strategy1.txt");
      Scanner sc = new Scanner(fr);
      while (sc.hasNext()) {
        st += sc.nextLine() + "\n";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    assertTrue(st.contains(
        "TYPE: EQUAL\n"
            + "START DATE: 2018-10-01\n"
            + "END DATE: 2018-12-05\n"
            + "DAYS: 10\n"
            + "AMOUNT: 1000.0\n"
            + "COMMISSION: 10.0\n"
            + "END OF STRATEGY"));
  }

  @Test
  public void testSaveStrategy() {
    uo.createPortfolio("testPortfolio");
    uo.saveEqualStrategy(helperGetDate("2018-11-12"), helperGetDate("2018-11-15"), 2,
        100, 1, "equalStrategy");
    String st = new String("");
    try {
      FileReader fr = new FileReader("equalStrategy.txt");
      Scanner sc = new Scanner(fr);
      while (sc.hasNext()) {
        st += sc.nextLine() + "\n";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    assertTrue(st.contains(
        "TYPE: EQUAL\n"
            + "START DATE: 2018-11-12\n"
            + "END DATE: 2018-11-15\n"
            + "DAYS: 2\n"
            + "AMOUNT: 100.0\n"
            + "COMMISSION: 1.0\n"
            + "END OF STRATEGY"));
  }


  private String helperDisplay(HashMap<String, Object> portfolioView) {
    StringBuilder pfView = new StringBuilder();
    pfView.append("Portfolio: ").append(portfolioView.get("Name"))
        .append("\n_____________________________________________________________________" +
            "___________________________________________\n")
        .append("\n\n");
    List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)
        portfolioView.get("StockData");
    for (HashMap<String, Object> stock : list) {
      pfView.append("Ticker: ").append(stock.get("Ticker")).append("\n");
      pfView.append("Amount invested in stock: $").append(stock.get("CostBasis")).append("\n");
      pfView.append("Valuation of stock: $").append(stock.get("Valuation")).append("\n");
      TreeMap<String, String[]> stockData = (TreeMap<String, String[]>)
          stock.get("TransactionData");
      for (String date : stockData.keySet()) {
        String data = String.format("%-30s \t\t|\t\t%-7s \t\t|\t\t%-7s\n",
            date, stockData.get(date)[0], stockData.get(date)[1]);
        pfView.append(data);
      }
      pfView.append("_____________________________________________________________________" +
          "___________________________________________\n");
    }
    pfView.append("\nValuation of portfolio: ").append(portfolioView.get("Valuation"))
        .append("\n").append("Cost Basis of portfolio: ").
        append(portfolioView.get("CostBasis")).append("\n");
    pfView.append("_____________________________________________________________________" +
        "___________________________________________\n");
    return pfView.toString();
  }


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
