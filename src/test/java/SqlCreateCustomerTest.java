import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SQL request test with creating customer record")
public class SqlCreateCustomerTest extends BaseTest {

    //  Test data
    private final String customerCreateRequest = """
            INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)
            VALUES (
                'CustomerName_Alexey Navalny',
                'ContactName_Yulia Navalnaya',
                'Address_Pushkina str., Kolotushkina building',
                'City_Moscow',
                'PostalCode_228',
                'Country_Russian Federation'
            );""";

    @Test
    @DisplayName("Add customer record to the SQL database")
    @Description(("Send SQL request to add customer record with provided values and check that record was added"))
    public void addCustomerTest() {
        step("1. Open the SQL requests page", () -> {
            sqlTaskPage.openPage();
        });

        step("2. Run SQL request with adding customer record to the 'Customers' table", () ->
                sqlTaskPage.runSqlRequestWithProvidedQueryString(customerCreateRequest)
        );

        step("3. Check that new customer was added", () -> {
            sqlTaskPage.switchToIFrame();
            assertThat(sqlTaskPage.numberOfResultsText.getOwnText())
                    .isEqualTo("Number of Records: 92");
            assertThat(sqlTaskPage.tableRecords.size() - 1)
                    .isEqualTo(92);
        });
    }
}
