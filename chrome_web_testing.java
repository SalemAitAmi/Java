package lab05;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

//Testing elements from UVic webpages using chromedriver
public class UVicTest {

    WebDriver browser;

    @BeforeEach
    public void setUp() {
        // Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\salem\\OneDrive\\Desktop\\chromedriver.exe");
        browser = new ChromeDriver();

        browser.manage().window().maximize();
    }

    @AfterEach
    public void cleanUp() {
        browser.quit();
    }


    //See if the page loads
    @Test
    public void PageLoads() {
        browser.get("https://www.uvic.ca");
        assertEquals("Home - University of Victoria", browser.getTitle());
    }

    //Confirm search bar element
    @Test
    public void containsSearchBar() {
        browser.get("https://www.uvic.ca");
        WebElement inputBox = browser.findElement(By.xpath("/html/body/header/div[1]/div[2]/div/div/div[2]/a[1]/i"));
        assertTrue(inputBox.isEnabled());
    }

    //Ensure search bar appears by clicking on the button
    @Test
    public void searchBarAppears() {
        browser.get("https://www.uvic.ca");
        WebElement searchBarBtn = browser.findElement(By.xpath("/html/body/header/div[1]/div[2]/div/div/div[2]/a[1]/i"));
        WebElement searchBar = browser.findElement(By.name("q"));
        searchBarBtn.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.visibilityOf(searchBar));
        assertTrue(searchBar.isDisplayed());
    }

    //See if we can type into the search bar
    @Test
    public void cscTypedIntoSearchBar(){
        browser.get("https://www.uvic.ca");
        WebElement searchBarBtn = browser.findElement(By.xpath("/html/body/header/div[1]/div[2]/div/div/div[2]/a[1]/i"));
        WebElement searchBar = browser.findElement(By.name("q"));
        searchBarBtn.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys("csc");
        assertEquals("csc", searchBar.getAttribute("value"));
    }

    //Launch a search by pressing the search button
    @Test
    public void searchLaunchedByBtn(){
        browser.get("https://www.uvic.ca");
        WebElement searchBarBtn = browser.findElement(By.xpath("/html/body/header/div[1]/div[2]/div/div/div[2]/a[1]/i"));
        WebElement searchBar = browser.findElement(By.name("q"));
        searchBarBtn.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys("csc");
        WebElement launchSearch = browser.findElement(By.xpath("/html/body/header/div[1]/div[1]/div/div/form/div/button/i"));
        launchSearch.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.titleIs("Search - University of Victoria"));
        assertEquals("Search - University of Victoria", browser.getTitle());
    }

    //Launch a search by pressing enter
    @Test
    public void searchLaunchedByEnter(){
        browser.get("https://www.uvic.ca");
        WebElement searchBarBtn = browser.findElement(By.xpath("/html/body/header/div[1]/div[2]/div/div/div[2]/a[1]/i"));
        WebElement searchBar = browser.findElement(By.name("q"));
        searchBarBtn.click();
        new WebDriverWait(browser, 5).until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys("csc");
        searchBar.sendKeys("\n");
        new WebDriverWait(browser, 5).until(ExpectedConditions.titleIs("Search - University of Victoria"));
        assertEquals("Search - University of Victoria", browser.getTitle());
    }

    //Confirm that uvic phone number is correct
    @Test
    public void findAndConfirmPhoneNumber(){
        browser.get("https://www.uvic.ca");
        WebElement phoneNumber = browser.findElement(By.xpath("/html/body/footer/div/div[3]/div/div/div[2]/div/div[1]/ul/li[1]/a"));
        assertEquals("\u200B1-250-721-7211", phoneNumber.getAttribute("text"));
    }

}
