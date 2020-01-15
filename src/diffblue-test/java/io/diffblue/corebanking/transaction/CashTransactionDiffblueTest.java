package io.diffblue.corebanking.transaction;

import static org.junit.Assert.assertEquals;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.Date;
import org.junit.Test;

public class CashTransactionDiffblueTest {
  @Test
  public void executeTransactionTest() throws TransactionException {
    // Arrange
    Date date = new Date(1L);
    CashTransaction cashTransaction = new CashTransaction(1L, date, new Account(1L, new Client("CASH"), 1L));

    // Act
    cashTransaction.executeTransaction();

    // Assert
    String actualToStringResult = cashTransaction.toString();
    assertEquals(Transaction.TransactionState.EXECUTED, cashTransaction.getTransactionState());
    assertEquals(
        "Transaction: | 70.01.01\t| Source: CASH\t| Target: 1\t| Amount: 1\t| Balance: 2\t| Transaction state: EXECUTED\t|",
        actualToStringResult);
  }

  @Test
  public void getTargetTest() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("1", (new CashTransaction(1L, date, new Account(1L, new Client("CASH"), 1L))).getTarget());
  }

  @Test
  public void getSourceTest() {
    // Arrange
    Date date = new Date(1L);

    // Act and Assert
    assertEquals("CASH", (new CashTransaction(1L, date, new Account(1L, new Client("CASH"), 1L))).getSource());
  }

  @Test
  public void constructorTest() {
    // Arrange
    Date date = new Date(1L);

    // Act
    CashTransaction actualCashTransaction = new CashTransaction(1L, date, new Account(1L, new Client("CASH"), 1L));

    // Assert
    String actualSource = actualCashTransaction.getSource();
    long actualTransactionAmount = actualCashTransaction.getTransactionAmount();
    String actualToStringResult = actualCashTransaction.toString();
    String actualTarget = actualCashTransaction.getTarget();
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualCashTransaction.getTransactionState());
    assertEquals("1", actualTarget);
    assertEquals(
        "Transaction: | 70.01.01\t| Source: CASH\t| Target: 1\t| Amount: 1\t| Balance: 0\t| Transaction state: NOT_EXECUTED_YET\t|",
        actualToStringResult);
    assertEquals(1L, actualTransactionAmount);
    assertEquals("CASH", actualSource);
  }
}
