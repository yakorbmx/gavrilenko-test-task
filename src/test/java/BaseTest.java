import com.codeborne.selenide.Configuration;
import com.testtask.page.SqlTaskPage;
import org.junit.jupiter.api.BeforeAll;


/**
 * Abstract test class with some Selenide parameters and used pages for test framework.
 */
public abstract class BaseTest {

    @BeforeAll
    public static void beforeTests() {
        Configuration.pageLoadStrategy = "none";
        Configuration.browser = "chrome";
    }

    //  Used pages
    protected final SqlTaskPage sqlTaskPage = new SqlTaskPage();
}
