import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class main {

    private static List<WebElement> SubCat;

    public static void main(String[] args) {
        WebDriver driver;
        Actions action;
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://www.dns-shop.ru/catalog/");
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='subcategory__item subcategory__item_with-childs']"));
        System.out.println(elements.get(0).getAttribute("innerHTML"));
        System.out.println(elements.get(0).getAttribute("outherHTML"));
        System.out.println(elements.size());
   }
}
