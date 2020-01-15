package io.diffblue.corebanking.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.BankTransaction;
import java.util.Date;
import org.junit.Test;

public class AccountDiffblueTest {
  @Test
  public void equalsTest() {
    // Arrange, Act and Assert
    assertFalse((new Account(1L, new Client("Current"), 1L)).equals("Current"));
  }

  @Test
  public void toStringTest() {
    // Arrange, Act and Assert
    assertEquals(
        "Account: | Acc #: 1\t | Acc name: Current\t | Acc holder: Current\t | Acc balance: 1\t | Acc state: OPEN\t |\nAccount statement empty.",
        (new Account(1L, new Client("Current"), 1L)).toString());
  }

  @Test
  public void addTransactionTest() throws AccountException {
    // Arrange
    Client client = new Client("Current");
    Account account = new Account(1L, client, 1L);
    Date date = new Date(1L);

    // Act
    account.addTransaction(new BankTransaction(1L, date, account, new Account(1L, client, 1L)));

    // Assert
    assertEquals(
        "Transaction: | 70.01.01\t| Source: 1\t| Target: 1\t| Amount: 1\t| Balance: 0\t| Transaction state: NOT_EXECUTED_YET\t|\n",
        account.getAccountStatement().toString());
  }

  @Test
  public void closeAccountTest() throws AccountException {
    // Arrange
    Account account = new Account(1L, new Client("Current"), 1L);

    // Act
    account.closeAccount();

    // Assert
    assertEquals(Account.AccountState.CLOSED, account.getAccountState());
  }

  @Test
  public void getAccountStateTest() {
    // Arrange, Act and Assert
    assertEquals(Account.AccountState.OPEN, (new Account(1L, new Client("Current"), 1L)).getAccountState());
  }

  @Test
  public void setAccountNameTest() throws AccountException {
    // Arrange
    Account account = new Account(1L, new Client("Current"), 1L);

    // Act
    account.setAccountName("Current");

    // Assert
    assertEquals("Current", account.getAccountName());
  }

  @Test
  public void getAccountNameTest() {
    // Arrange, Act and Assert
    assertEquals("Current", (new Account(1L, new Client("Current"), 1L)).getAccountName());
  }

  @Test
  public void takeFromBalanceTest() throws AccountException {
    // Arrange
    Account account = new Account(1L, new Client("Current"), 1L);

    // Act
    account.takeFromBalance(1L);

    // Assert
    assertEquals(0L, account.getCurrentBalance());
  }

  @Test
  public void addToBalanceTest() throws AccountException {
    // Arrange
    Account account = new Account(1L, new Client("Current"), 1L);

    // Act
    account.addToBalance(1L);

    // Assert
    assertEquals(2L, account.getCurrentBalance());
  }

  @Test
  public void getCurrentBalanceTest() {
    // Arrange, Act and Assert
    assertEquals(1L, (new Account(1L, new Client("Current"), 1L)).getCurrentBalance());
  }

  @Test
  public void getClientTest() {
    // Arrange
    Client client = new Client("Current");

    // Act and Assert
    assertSame(client, (new Account(1L, client, 1L)).getClient());
  }

  @Test
  public void getAccountNumberTest() {
    // Arrange, Act and Assert
    assertEquals(1L, (new Account(1L, new Client("Current"), 1L)).getAccountNumber());
  }

  @Test
  public void constructorTest() {
    // Arrange and Act
    Account actualAccount = new Account(1L, new Client("Current"), 1L);

    // Assert
    String actualToStringResult = actualAccount.toString();
    Account.AccountStatement accountStatement = actualAccount.getAccountStatement();
    long actualCurrentBalance = actualAccount.getCurrentBalance();
    Account.AccountState actualAccountState = actualAccount.getAccountState();
    String actualAccountName = actualAccount.getAccountName();
    assertEquals(
        "Account: | Acc #: 1\t | Acc name: Current\t | Acc holder: Current\t | Acc balance: 1\t | Acc state: OPEN\t |\nAccount statement empty.",
        actualToStringResult);
    assertEquals(1L, actualAccount.getAccountNumber());
    assertEquals("Current", actualAccountName);
    assertEquals(Account.AccountState.OPEN, actualAccountState);
    assertEquals(1L, actualCurrentBalance);
    assertEquals("Account statement empty.", accountStatement.toString());
  }
}
