package utils;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WhatsAppAutomation {
    public void Useseleniumautomation(String aiGenMessage) throws InterruptedException {
        Sendwishes sendwishes = new Sendwishes();

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://web.whatsapp.com/");
        Thread.sleep(50000); // Wait for QR code scan

        System.out.println("Checking if WhatsApp Web is open...");

        for (Map.Entry<String, String> groupEntry : sendwishes.getGroupList().entrySet()) {
            String groupName = groupEntry.getKey();
            String country = groupEntry.getValue();

            String messageToSend = sendwishes.getMessageForGroup(groupName, country);

            WebElement searchBox = driver.findElement(By.xpath(
                    "(//p[contains(@class, 'selectable-text') and contains(@class, 'copyable-text')])[1]"));
            searchBox.click();
            searchBox.clear();
            searchBox.sendKeys(groupName);
            Thread.sleep(2000);
            searchBox.sendKeys(Keys.ENTER);
            Thread.sleep(2000);

            WebElement messageBox = driver.findElement(By.xpath(
                    "(//p[contains(@class, 'selectable-text') and contains(@class, 'copyable-text')])[2]"));
            messageBox.click();
            messageBox.sendKeys(messageToSend +"\n" + aiGenMessage);
            messageBox.sendKeys(Keys.ENTER);

            System.out.println("Message sent to group: " + groupName);
            Thread.sleep(3000);
        }

        System.out.println("All messages sent successfully!");
        Thread.sleep(5000);
        driver.quit();
    }
}
