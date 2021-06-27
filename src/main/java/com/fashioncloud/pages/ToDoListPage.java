package com.fashioncloud.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToDoListPage {

    WebDriver driver;

    public ToDoListPage(WebDriver driver) {

        this.driver = driver;
        // This initElements method will intitialize all WebElements
        PageFactory.initElements(this.driver, this);
    }

    /**
     * All WebElements are identified by @FindBy annotation
     */

    @FindBy(xpath = "//input[@placeholder='Get Milk']")
    private WebElement inputTextBox;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement buttonAdd;

    @FindBy(xpath = "//span[@class='label label-info ng-binding']")
    private WebElement numberCounter;

    @FindBy(xpath = "//h1[text()='Simple ToDo List ']")
    private WebElement titleText;

    @FindBy(xpath = "//div[@class='col-sm-4 col-sm-offset-4']//div//label")
    private WebElement listOfItemsAdded;

    /**
     * methods to verify all important elements on first page.
     * 
     * @return
     **/
    public boolean verifyTitleText() {

        return isElementPresent(titleText);
    }

    public boolean verifyNumberCounter() {

        return isElementPresent(numberCounter);
    }

    public boolean verifyinputTextBox() {

        return isElementPresent(inputTextBox);
    }

    public boolean verifyAddButton() {

        return isElementPresent(buttonAdd);
    }

    /**
     * method to add item in to do list
     *
     */
    public void addToDoItem(String item) {
        inputTextBox.sendKeys(item);
        buttonAdd.click();
    }

    /**
     * method to verify if items are present in the list or not.
     *
     */
    public boolean verifyItemPresent(String item) {
        boolean isPresent = false;
        List<WebElement> listOfAddedElements = driver
                .findElements(By.xpath("//div[@class='col-sm-4 col-sm-offset-4']//div//label"));
        for (WebElement element : listOfAddedElements) {
            if (element.getText().equalsIgnoreCase(item))
                isPresent = true;
        }
        return isPresent;
    }

    /**
     * method to remove items from the list and verify
     *
     */
    public boolean removeTodoItem() {
        List<WebElement> listOfAddedElementsBeforeDeletion = driver
                .findElements(By.xpath("//div[@class='col-sm-4 col-sm-offset-4']//div//label"));
        int countOfItemsToBeDeleted = listOfAddedElementsBeforeDeletion.size();

        for (int i = countOfItemsToBeDeleted; i > 0; i--) {

            driver.findElement(By.xpath("//div[@class='col-sm-4 col-sm-offset-4']//div[" + (i) + "]//label"))
                    .click();

        }

        List<WebElement> listOfAddedElementsAfterDeletion = driver
                .findElements(By.xpath("//div[@class='col-sm-4 col-sm-offset-4']//div//label"));

        return listOfAddedElementsAfterDeletion.isEmpty();

    }

    public boolean isElementPresent(WebElement element) {

        return element.isDisplayed();

    }

}
