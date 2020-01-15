package io.diffblue.corebanking.datamanagement;

import static org.junit.Assert.assertEquals;
import io.diffblue.corebanking.CoreBanking;
import io.diffblue.corebanking.client.Client;
import io.diffblue.corebanking.transaction.TransactionException;
import java.util.List;
import org.junit.Test;

public class ReadFromDBDiffblueTest {
  @Test
  public void readFromDBTest2() throws TransactionException {
    // Arrange
    CoreBanking coreBanking = new CoreBanking();

    // Act
    ReadFromDB.readFromDB(coreBanking);

    // Assert
    List<Client> clients = coreBanking.getClients();
    Client getResult = clients.get(0);
    Client getResult1 = clients.get(1);
    Client getResult2 = clients.get(2);
    String actualClientName = getResult.getClientName();
    String actualClientName1 = getResult2.getClientName();
    assertEquals("Jane Robbins", getResult1.getClientName());
    assertEquals("Emily Simmons", actualClientName1);
    assertEquals("John Field", actualClientName);
  }

  @Test
  public void readFromDBTest() throws TransactionException {
    // Arrange, Act and Assert
    List<Client> clients = ReadFromDB.readFromDB().getClients();
    Client getResult = clients.get(0);
    Client getResult1 = clients.get(1);
    Client getResult2 = clients.get(2);
    String actualClientName = getResult.getClientName();
    String actualClientName1 = getResult2.getClientName();
    assertEquals("Jane Robbins", getResult1.getClientName());
    assertEquals("Emily Simmons", actualClientName1);
    assertEquals("John Field", actualClientName);
  }
}
