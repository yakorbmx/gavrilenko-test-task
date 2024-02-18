import com.testtask.utilities.CustomerDto;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SQL request test with customer update")
public class SqlUpdateCustomerTest extends BaseTest {
    private CustomerDto customerDataInitial;
    private CustomerDto customerDataUpdated;

    //  Test data
    private final String defaultRequest = "SELECT * FROM Customers;";
    private final String customerUpdateRequest = """
            UPDATE Customers
            SET\s
                CustomerName = 'New CustomerName',
                ContactName = 'New ContactName',
                Address = 'New Address',
                City = 'New City',
                PostalCode = 'New PostalCode',
                Country = 'New Country'
            WHERE customerId = 1;""";

    @Test
    @DisplayName("Update customer record via SQL request")
    @Description("Send SQL request to update existing customer")
    public void updateCustomerTest() {
        step("1. Open the SQL requests page", () -> {
            sqlTaskPage.openPage();
        });

        step("2. Run default SQL request to collect data from the first customer", () ->
                sqlTaskPage.runSqlRequestWithProvidedQueryString(defaultRequest)
        );

        step("3. Collect initial data from the first customer", () -> {
            customerDataInitial = sqlTaskPage.collectCustomerDataById(1);
        });

        step("4. Run SQL request to update the first customer", () ->
                sqlTaskPage.runSqlRequestWithProvidedQueryString(customerUpdateRequest)
        );

        step("3. Collect initial data from the first customer and verify that record was updated", () -> {
            customerDataUpdated = sqlTaskPage.collectCustomerDataById(1);

            //  There might be more assertions for concrete fields
            assertThat(customerDataInitial)
                    .isNotEqualTo(customerDataUpdated);
        });
    }
}
