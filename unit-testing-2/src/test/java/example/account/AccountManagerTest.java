package example.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountManagerTest {

    @Test
    void givenCustomerWithSufficientBalance_whenWithdraw_thenSucceed() {
        // Arrange
        AccountManager am = new AccountManagerImpl();
        Customer c = new Customer();
        c.setBalance(1000);

        // Act
        String result = am.withdraw(c, 500);

        // Assert
        Assertions.assertEquals("success", result);
        Assertions.assertEquals(500, c.getBalance());
    }

    @Test
    void givenCustomerWithoutSufficientBalance_whenWithdraw_thenInsufficientAccountBalance() {
        // Arrange
        AccountManager am =new AccountManagerImpl();
        Customer c=new Customer();

        c.setBalance(400);
        c.setCreditAllowed(false);

        // Act
        String result = am.withdraw(c, 500);

        // Assert
        Assertions.assertEquals("insufficient account balance",result);
    }

    @Test
    void givenCustomerWithoutSufficientBalance_whenWithdraw_thenMaximumCreditExceeded(){
        // Arrange
        AccountManager am =new AccountManagerImpl();
        Customer c=new Customer();
        c.setBalance(400);
        c.setCreditAllowed(true);
        c.setVip(false);


        // Act
        String result = am.withdraw(c, 2000);

        //Assert
        Assertions.assertEquals("maximum credit exceeded",result);
    }

    @Test
    void givenCustomerWithoutSufficientBalance_whenWithdraw_thenSucceed(){
        // Arrange
        AccountManager am =new AccountManagerImpl();
        Customer c=new Customer();
        c.setBalance(400);
        c.setCreditAllowed(true);
        c.setVip(true);


        // Act
        String result = am.withdraw(c, 2000);

        //Assert
        Assertions.assertEquals("success",result);
    }

}
