import org.openqa.selenium.By;

import java.util.ArrayList;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class main {

    public static ArrayList<String> catalogUrl = new ArrayList<>();
    public static ArrayList<String> subCatalogUrl = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        getLinks();

    }

    public static void getLinks() throws InterruptedException {
        WebDriver driver;
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.get("https://www.dns-shop.ru/catalog/");
        ArrayList<WebElement> elements = new ArrayList<>(driver.findElements(By.xpath("//a[@class='subcategory__childs-item']")));
        for (int i = 0; i < elements.size(); i++) {
            if(catalogUrl.contains(elements.get(i).getAttribute("href"))) {
                continue;
            }
            else {
                if (elements.get(i).getAttribute("href").contains("catalog")) {
                    catalogUrl.add(elements.get(i).getAttribute("href"));
                    System.out.println(elements.get(i).getAttribute("href"));
                }
            }
        }
        System.out.println(catalogUrl.size());
        for (int i = 0; i < catalogUrl.size(); i++) {
            Thread.sleep(50);
            driver.get(catalogUrl.get(i));
            elements = new ArrayList<>(driver.findElements(By.xpath("//a[@class='subcategory__item ui-link ui-link_blue']")));
            for(int y = 0 ; y < elements.size(); y++) {
                if (catalogUrl.contains(elements.get(y).getAttribute("href"))) {
                    System.out.println("already exist url " + elements.get(y).getAttribute("href"));
                    continue;
                } else {
                    catalogUrl.add(elements.get(y).getAttribute("href"));
                    System.out.println(elements.get(y).getAttribute("href"));
                    System.out.println(i + "/" + catalogUrl.size());
                }
            }
        }
        System.out.println(catalogUrl.size());
        driver.close();
    }
}