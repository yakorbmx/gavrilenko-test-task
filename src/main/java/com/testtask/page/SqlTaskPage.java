package com.testtask.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.testtask.utilities.CustomerDto;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.testtask.utilities.Constants.TASK_URL;
import static java.time.Duration.ofSeconds;

/**
 * SQL requests page.
 */
public class SqlTaskPage {
    //  Elements on the SQL task page
    public final SelenideElement runSqlButton = $x("//button[text()='Run SQL »']");
    public final SelenideElement resultTableIframe = $("#resultSQL > #iframeResultSQL");
    public final SelenideElement resultTable = $("table[class$='notranslate'] tbody");
    public final SelenideElement numberOfResultsText = $x("//div[contains(text(),'Number of Records:')]");
    public final ElementsCollection tableRecords = resultTable.$$("tr");

    /**
     * Open SQL task page via direct link using Task URL.
     *
     * @return opened SQL task Page reference
     */
    public SqlTaskPage openPage() {
        open(TASK_URL);
        //  Next timeout is used to prevent infinite page loading
        runSqlButton.shouldBe(visible, ofSeconds(30));
        return this;
    }

    /**
     * Switch the current context into the page's iframe.
     */
    public void switchToIFrame() {
        resultTableIframe.should(exist, ofSeconds(100)).scrollIntoView("{block: \"center\"}");
        Selenide.switchTo().frame(resultTableIframe);
    }

    /**
     * Clear the request string and populate it with provided.
     *
     * @param sqlString SQL request string
     */
    public void setQueryStringToForm(String sqlString) {
        var script =
                        "    var newQueryString = \"" + sqlString + "\";" +
                        "    var codeMirrorEditor = window.editor;" +
                        "    codeMirrorEditor.setValue(newQueryString);";
        executeJavaScript(script);
    }

    /**
     * Clear the request field, populate it with provided request, click on 'Run SQL' button.
     *
     * @param sqlString SQL request string
     */
    public void runSqlRequestWithProvidedQueryString(String sqlString) {
        setQueryStringToForm(sqlString);
        runSqlButton.click();
    }

    /**
     * Switch to results table iframe, scroll to the searching customer and get text from Address column.
     *
     * @param customersName name of the customer to be searched in the results table
     * @return text from the Address field of the provided customer
     */
    public String findCustomersAddressByName(String customersName) {
        switchToIFrame();
        var customerBlock = resultTable.$(withText(customersName))
                .scrollIntoView("{block: \"center\"}");
        return customerBlock.parent().$x(".//td[4]").getOwnText();
    }

    public CustomerDto collectCustomerDataById(Integer customerId) {
        switchToIFrame();
        var customerBlock = resultTable.$(withText(String.valueOf(customerId)))
                .scrollIntoView("{block: \"center\"}");

        var customer = new CustomerDto();
        customer.customerName = customerBlock.parent().$x(".//td[2]").getOwnText();
        customer.contactName = customerBlock.parent().$x(".//td[3]").getOwnText();
        customer.address = customerBlock.parent().$x(".//td[4]").getOwnText();
        customer.city = customerBlock.parent().$x(".//td[5]").getOwnText();
        customer.postalCode = customerBlock.parent().$x(".//td[6]").getOwnText();
        customer.country = customerBlock.parent().$x(".//td[7]").getOwnText();

        return customer;
    }
}
