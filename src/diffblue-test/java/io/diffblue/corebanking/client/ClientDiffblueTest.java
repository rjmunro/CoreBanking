package io.diffblue.corebanking.client;

import static org.junit.Assert.assertEquals;
import io.diffblue.corebanking.account.Account;
import java.util.List;
import org.junit.Test;

public class ClientDiffblueTest {
  @Test
  public void toStringTest() {
    // Arrange, Act and Assert
    assertEquals("Client name: \n", (new Client("")).toString());
  }

  @Test
  public void getAccountsTest() {
    // Arrange, Act and Assert
    assertEquals(0, (new Client("")).getAccounts().size());
  }

  @Test
  public void getClientNameTest() {
    // Arrange, Act and Assert
    assertEquals("", (new Client("")).getClientName());
  }

  @Test
  public void constructorTest() {
    // Arrange and Act
    Client actualClient = new Client("");

    // Assert
    String actualClientName = actualClient.getClientName();
    assertEquals("", actualClientName);
    assertEquals("Client name: \n", actualClient.toString());
  }
}
