import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SQL request test with default string, checking address of the customer")
public class SqlDefaultRequestTest extends BaseTest {

    //  Test data
    private final String defaultRequest = "SELECT * FROM Customers;";
    private final String customerName = "Giovanni Rovelli";
    //  There's a weird hack for passing the assertions, should be done with formatting
    private final String customerAddressNotFormatted = "Via Ludovico il Moro 22 ";

    @Test
    @DisplayName("Check customer's address by name")
    @Description("Send SQL request with collecting all data from customers and check that address for concrete customer is valid")
    public void checkCustomerAddressTest() {
        step("1. Open the SQL requests page", () -> {
            sqlTaskPage.openPage();
        });

        step("2. Run SQL request with collecting all data from 'Customers' table", () ->
                sqlTaskPage.runSqlRequestWithProvidedQueryString(defaultRequest)
        );

        step("3. Check that '" + customerName + "' have '" + customerAddressNotFormatted + "' address in the table", () -> {
            var customerAddressInTable = sqlTaskPage.findCustomersAddressByName(customerName);
            assertThat(customerAddressNotFormatted)
                    .isEqualTo(customerAddressInTable);
        });
    }
}
