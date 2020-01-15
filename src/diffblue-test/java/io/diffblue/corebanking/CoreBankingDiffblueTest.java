package io.diffblue.corebanking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import io.diffblue.corebanking.account.Account;
import io.diffblue.corebanking.client.Client;
import java.util.List;
import org.junit.Test;

public class CoreBankingDiffblueTest {
  @Test
  public void toStringTest() {
    // Arrange, Act and Assert
    assertEquals("", (new CoreBanking()).toString());
  }

  @Test
  public void getClientsTest() {
    // Arrange, Act and Assert
    assertEquals(0, (new CoreBanking()).getClients().size());
  }

  @Test
  public void getAccountsTest() {
    // Arrange, Act and Assert
    assertEquals(0, (new CoreBanking()).getAccounts().size());
  }

  @Test
  public void registerNewClientTest() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act
    coreBanking.registerNewClient(new Client("aaaaa"));

    // Assert
    assertEquals("Client name: aaaaa\n", coreBanking.toString());
  }

  @Test
  public void openNewAccountTest() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();
    Client client = new Client("aaaaa");

    // Act
    Account actualOpenNewAccountResult = coreBanking.openNewAccount(client, 1L);

    // Assert
    Account.AccountStatement accountStatement = actualOpenNewAccountResult.getAccountStatement();
    long actualCurrentBalance = actualOpenNewAccountResult.getCurrentBalance();
    Client actualClient = actualOpenNewAccountResult.getClient();
    Account.AccountState actualAccountState = actualOpenNewAccountResult.getAccountState();
    assertEquals("Current", actualOpenNewAccountResult.getAccountName());
    assertEquals(Account.AccountState.OPEN, actualAccountState);
    assertSame(client, actualClient);
    assertEquals(1L, actualCurrentBalance);
    assertEquals("Account statement empty.", accountStatement.toString());
  }

  @Test
  public void purgeCoreBankingTest() {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act
    coreBanking.purgeCoreBanking();

    // Assert
    assertEquals("", coreBanking.toString());
  }

  @Test
  public void constructorTest() {
    // Arrange, Act and Assert
    assertEquals("", (new CoreBanking()).toString());
  }
}
