package com.gnostrenoff.cdbtests.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

// TODO: Auto-generated Javadoc
/**
 * The Class PaginationTest.
 */
@Ignore
@Category(IntegrationTest.class)
public class PaginationIntegrationTest {

  /** The driver. */
  private WebDriver driver;

  /** The base url. */
  private String baseUrl;

  /** The accept next alert. */
  private boolean acceptNextAlert = true;

  /** The verification errors. */
  private StringBuffer verificationErrors = new StringBuffer();

  /**
   * Sets the up.
   *
   * @throws Exception
   *           the exception
   */
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  /**
   * Test pagination.
   *
   * @throws Exception
   *           the exception
   */
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

  /**
   * Tear down.
   *
   * @throws Exception
   *           the exception
   */
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  /**
   * Checks if is element present.
   *
   * @param by
   *          the by
   * @return true, if is element present
   */
  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  /**
   * Checks if is alert present.
   *
   * @return true, if is alert present
   */
  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  /**
   * Close alert and get its text.
   *
   * @return the string
   */
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
