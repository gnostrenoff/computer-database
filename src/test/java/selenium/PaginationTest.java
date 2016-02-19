package selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Ignore
public class PaginationTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPagination() throws Exception {
    driver.get(baseUrl + "/computer-database/dashboard");
    driver.findElement(By.linkText("5")).click();
    assertTrue(isElementPresent(By.linkText("Macintosh LC III")));
    driver.findElement(By.linkText("Previous")).click();
    assertTrue(isElementPresent(By.linkText("Macintosh Quadra")));
    driver.findElement(By.linkText("Next")).click();
    assertTrue(isElementPresent(By.linkText("Commodore PET")));
    driver.findElement(By.xpath("(//a[contains(text(),'50')])[2]")).click();
    assertTrue(isElementPresent(By.linkText("Commodore PET")));  
    driver.findElement(By.linkText("100")).click();
    assertTrue(isElementPresent(By.linkText("Tinkertoy Tic-Tac-Toe Computer")));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
