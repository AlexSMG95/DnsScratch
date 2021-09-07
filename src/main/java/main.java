import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
       WebDriverManager.chromedriver().setup();
       ChromeOptions options = new ChromeOptions();
       options.setHeadless(false);
       driver = new ChromeDriver(options);
       //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
       List<WebElement> catalog = null;
       List<WebElement> subCatalog = null;
       driver.get("https://www.dns-shop.ru/catalog/");
       ArrayList<WebElement> elements = new ArrayList<>(driver.findElements(By.xpath("//div[@class='subcategory__item subcategory__item_with-childs']")));
       for (int i = 0; i < elements.size(); i++) {
           catalog = elements.get(i).findElements(By.xpath("//a[@class='subcategory__childs-item']"));
       }
       for (int i = 0; i < catalog.size(); i++) {
           System.out.println("[INFO] колличество ссылок catalog " + i + " " + catalog.get(i).getAttribute("href"));
           catalogUrl.add(catalog.get(i).getAttribute("href"));

       }
       int count = 0;
       for (int a = 0; a < catalogUrl.size(); a++) {
           Thread.sleep(50);
           driver.get(catalogUrl.get(a));
           subCatalog = new ArrayList<>(driver.findElements(By.xpath("//a[@class='subcategory__item ui-link ui-link_blue']")));
           for (int i = 0; i < subCatalog.size(); i++) {
               count++;
               System.out.println("[INFO] колличество ссылок " + count +" " + subCatalog.get(i).getAttribute("href"));
               catalogUrl.add(subCatalog.get(i).getAttribute("href"));
           }
       }
       driver.close();
   }
}

//List<WebElement> elements = driver.findElements(By.xpath("//ul[@class='subcategory__childs-list']"));
//        driver.get("https://www.dns-shop.ru/catalog/e3d826d63bb17fd7/texnika-dlya-kuxni/");
//        List<WebElement> elements = driver.findElements(By.xpath("//a[@class='subcategory__item ui-link ui-link_blue']"));
//        System.out.println(elements.get(0).getAttribute("href"));
//        System.out.println(elements.size());
