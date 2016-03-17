package com.gnostrenoff.cdbtests.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

// TODO: Auto-generated Javadoc
/**
 * The Class AddComputerTest.
 */
@Category(IntegrationTest.class)
public class AddComputerIntegrationTest {

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
   * Test add computer.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testAddComputer() throws Exception {
    driver.get(baseUrl + "/computer-database/dashboard");
    driver.findElement(By.id("addComputer")).click();
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    // assertTrue(isElementPresent(By.className("form-group has-error")));

    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys("azerty");
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    assertTrue(isAlertPresent());
    assertEquals("Computer has been successfully created", closeAlertAndGetItsText());

    driver.findElement(By.id("addComputer")).click();
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys("qwerty");
    driver.findElement(By.id("introduced")).clear();
    driver.findElement(By.id("introduced")).sendKeys("2015/01/02");
    driver.findElement(By.id("discontinued")).clear();
    driver.findElement(By.id("discontinued")).sendKeys("2015/03/01");
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    assertTrue(isAlertPresent());
    assertEquals("Computer has been successfully created", closeAlertAndGetItsText());

    driver.findElement(By.id("addComputer")).click();
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys("azerty2");
    new Select(driver.findElement(By.id("company-id")))
        .selectByVisibleText("8- Micro Instrumentation and Telemetry Systems");
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    assertTrue(isAlertPresent());
    assertEquals("Computer has been successfully created", closeAlertAndGetItsText());

    driver.findElement(By.id("addComputer")).click();
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys("qwerty2");
    driver.findElement(By.id("introduced")).clear();
    driver.findElement(By.id("introduced")).sendKeys("2015/01/02");
    driver.findElement(By.id("discontinued")).clear();
    driver.findElement(By.id("discontinued")).sendKeys("2015/01/01");
    new Select(driver.findElement(By.id("company-id")))
        .selectByVisibleText("6- Commodore International");
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    // assertTrue(isElementPresent(By.className("form-group has-error")));

    driver.findElement(By.linkText("Cancel")).click();
    assertTrue(driver.getCurrentUrl().equals("http://localhost:8080/computer-database/dashboard"));
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
