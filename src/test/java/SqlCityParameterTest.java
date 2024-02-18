import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SQL request test with city parameter")
public class SqlCityParameterTest extends BaseTest {

    //  Test data
    private final String londonCitySqlSearchRequest = "SELECT * FROM Customers WHERE City = 'London'";
    private final Integer londonRecordsSize = 6;

    @Test
    @DisplayName("Check amount of customers with city = London")
    @Description("Send SQL request with collecting all data from customers in London and check quantity of records")
    public void checkAmountForOneCityTest() {
        step("1. Open the SQL requests page", () -> {
            sqlTaskPage.openPage();
        });

        step("2. Run SQL request with collecting all data from 'Customers' table", () ->
                sqlTaskPage.runSqlRequestWithProvidedQueryString(londonCitySqlSearchRequest)
        );

        step("3. Check that there are only " + londonRecordsSize + " records in the result table", () -> {
            sqlTaskPage.switchToIFrame();
            assertThat(sqlTaskPage.numberOfResultsText.getOwnText())
                    .isEqualTo("Number of Records: " + londonRecordsSize);
            assertThat(sqlTaskPage.tableRecords.size() - 1)
                    .isEqualTo(londonRecordsSize);
        });
    }
}
