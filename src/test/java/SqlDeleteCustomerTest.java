import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SQL request test with customer deleting")
public class SqlDeleteCustomerTest extends BaseTest {

    //  Test data
    private final String defaultRequest = "SELECT * FROM Customers;";
    private final String customerDeleteRequest = """
            DELETE FROM Customers
            WHERE CustomerID = 12;""";

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

        step("3. Check that initial state of the customers is default", () -> {
            sqlTaskPage.switchToIFrame();
            assertThat(sqlTaskPage.numberOfResultsText.getOwnText())
                    .isEqualTo("Number of Records: 91");
            assertThat(sqlTaskPage.tableRecords.size() - 1)
                    .isEqualTo(91);
        });

        step("4. Run SQL request to update the first customer", () ->
                sqlTaskPage.runSqlRequestWithProvidedQueryString(customerDeleteRequest)
        );

        step("5. Check that the customer was deleted from the table", () -> {
            sqlTaskPage.switchToIFrame();
            assertThat(sqlTaskPage.numberOfResultsText.getOwnText())
                    .isEqualTo("Number of Records: 90");
            assertThat(sqlTaskPage.tableRecords.size() - 1)
                    .isEqualTo(90);
            //  TODO add assertions for concrete customer
        });
    }
}
