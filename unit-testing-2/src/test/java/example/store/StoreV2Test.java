package example.store;

import example.account.AccountManager;
import example.account.AccountManagerImpl;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class StoreV2Test {

    @Test
    void test() {
        // Arrange
        AccountManager accountManager = mock(AccountManager.class);
        when(accountManager.withdraw(any(), anyInt())).thenReturn("success");
        Store store = new StoreImpl(accountManager);
        Product product = new Product();
        product.setQuantity(4);
        Customer customer = new Customer();

        // Act
        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(3, product.getQuantity());
    }

    @Test
    void setProductEqualToZeroShouldOutputProductOutOfStock(){
        //Arrange
        Store store=new StoreImpl(new AccountManagerImpl());
        Product product=new Product();
        Customer customer=new Customer();
        customer.setBalance(500);
        product.setQuantity(0);

        //Act
        RuntimeException ex=Assertions
                .assertThrows(RuntimeException.class, ()-> store.buy(product, customer));

        //Assert
        Assertions.assertEquals("Product out of stock", ex.getMessage());
    }

    @Test
    void givingCustomerStatusShouldFailed(){
        // Arrange
        AccountManager accountManager=mock(AccountManager.class);
        when(accountManager.withdraw(any(),anyInt())).thenReturn("failed");
        Store store= new StoreImpl(accountManager);
        Product product=new Product();
        product.setQuantity(4);
        Customer customer=new Customer();

        // Act

        RuntimeException ex=Assertions
                .assertThrows(RuntimeException.class,() -> store.buy(product,customer));

        // Assert
        Assertions.assertEquals("Payment failure: failed", ex.getMessage());

    }



}
