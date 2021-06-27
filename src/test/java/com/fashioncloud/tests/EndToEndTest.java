package com.fashioncloud.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fashioncloud.base.BaseClass;
import com.fashioncloud.pages.ToDoListPage;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Rohitsingh Kanwar
 * 
 */
public class EndToEndTest extends BaseClass {

    @Test(priority = 1)
    public void verifyAllImportantElementsOnPage() {

        setLogger(getExtent().startTest("Test 1 : verifyAllImportantElementsOnPage"));

        try {
            ToDoListPage pageobject = new ToDoListPage(getDriver());

            Assert.assertTrue(pageobject.verifyTitleText());
            Assert.assertTrue(pageobject.verifyNumberCounter());
            Assert.assertTrue(pageobject.verifyinputTextBox());
            Assert.assertTrue(pageobject.verifyAddButton());

            BaseClass.getLogger().log(LogStatus.PASS, "All Elements are verified suucessfully on the page!!! ");

            } catch (Exception e) {

            BaseClass.getLogger().log(LogStatus.FAIL, "Test 1  has Failed!!! " + e.toString());
            }

            getExtent().endTest(getLogger());

    }

    @Test(priority = 2)
    public void addItemsToListAndVerify() {

        setLogger(getExtent().startTest("Test 2 : addItemsToListAndVerify"));
        try {

            ToDoListPage pageobject = new ToDoListPage(getDriver());
            pageobject.addToDoItem("Item1");
            pageobject.addToDoItem("Item2");
            pageobject.addToDoItem("Item3");
            pageobject.verifyItemPresent("Item1");
            pageobject.verifyItemPresent("Item2");
            pageobject.verifyItemPresent("Item3");

            BaseClass.getLogger().log(LogStatus.PASS, "All Items are added and verified successfully!!! ");

        } catch (Exception e) {

            BaseClass.getLogger().log(LogStatus.FAIL, "Test 2 has Failed!!! " + e.toString());

        }

        getExtent().endTest(getLogger());
    }

    @Test(priority = 3)
    public void removeItemsFromListAndVerify() {

        setLogger(getExtent().startTest("Test 3 : RemoveItemsFromListAndVerify"));

        try {

            ToDoListPage pageobject = new ToDoListPage(getDriver());
            Assert.assertTrue(pageobject.removeTodoItem());

            BaseClass.getLogger().log(LogStatus.PASS, "All Items are deleted successfully!!! ");

        } catch (Exception e) {

            BaseClass.getLogger().log(LogStatus.FAIL, "Test 3 has Failed!!! " + e.toString());

        }

        getExtent().endTest(getLogger());
    }
}
