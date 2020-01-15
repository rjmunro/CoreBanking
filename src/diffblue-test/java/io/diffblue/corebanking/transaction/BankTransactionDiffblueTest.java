package io.diffblue.corebanking.transaction;

import static org.junit.Assert.assertEquals;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.Date;
import org.junit.Test;

public class BankTransactionDiffblueTest {
  @Test
  public void executeTransactionTest() throws TransactionException {
    // Arrange
    Date date = new Date(1L);
    Account sourceAcc = new Account(1L, new Client("aaaaa"), 1L);
    BankTransaction bankTransaction = new BankTransaction(1L, date, sourceAcc, new Account(1L, null, 1L));

    // Act
    bankTransaction.executeTransaction();

    // Assert
    String actualToStringResult = bankTransaction.toString();
    assertEquals(Transaction.TransactionState.EXECUTED, bankTransaction.getTransactionState());
    assertEquals(
        "Transaction: | 70.01.01\t| Source: 1\t| Target: 1\t| Amount: 1\t| Balance: 2\t| Transaction state: EXECUTED\t|",
        actualToStringResult);
  }

  @Test
  public void getTargetTest() {
    // Arrange
    Date date = new Date(1L);
    Account sourceAcc = new Account(1L, new Client("aaaaa"), 1L);

    // Act and Assert
    assertEquals("1", (new BankTransaction(1L, date, sourceAcc, new Account(1L, null, 1L))).getTarget());
  }

  @Test
  public void getSourceTest() {
    // Arrange
    Date date = new Date(1L);
    Account sourceAcc = new Account(1L, new Client("aaaaa"), 1L);

    // Act and Assert
    assertEquals("1", (new BankTransaction(1L, date, sourceAcc, new Account(1L, null, 1L))).getSource());
  }

  @Test
  public void constructorTest() {
    // Arrange
    Date date = new Date(1L);

    // Act
    BankTransaction actualBankTransaction = new BankTransaction(1L, date, new Account(1L, new Client("aaaaa"), 1L),
        new Account(1L, new Client("aaaaa"), 1L));

    // Assert
    String actualSource = actualBankTransaction.getSource();
    long actualTransactionAmount = actualBankTransaction.getTransactionAmount();
    String actualToStringResult = actualBankTransaction.toString();
    String actualTarget = actualBankTransaction.getTarget();
    assertEquals(Transaction.TransactionState.NOT_EXECUTED_YET, actualBankTransaction.getTransactionState());
    assertEquals("1", actualTarget);
    assertEquals(
        "Transaction: | 70.01.01\t| Source: 1\t| Target: 1\t| Amount: 1\t| Balance: 0\t| Transaction state: NOT_EXECUTED_YET\t|",
        actualToStringResult);
    assertEquals(1L, actualTransactionAmount);
    assertEquals("1", actualSource);
  }
}
