import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class main {

    public static ArrayList<String> listUrl = new ArrayList<>();
    public static void main(String[] args) {
        listUrl.add("https://www.dns-shop.ru");
        for (int i =0 ; i< getLinks().size(); i++ ){
            System.out.println(getLinks().get(i));
        }

        }

   public static ArrayList getLinks(){
       WebDriver driver;
       WebDriverManager.chromedriver().setup();
       ChromeOptions options = new ChromeOptions();
       options.setHeadless(false);
       driver = new ChromeDriver(options);
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       int error = 0;
           for (int i = 0; i < listUrl.size(); i++) {
               try {
                   driver.get(listUrl.get(i));
                   ArrayList<WebElement> elements = new ArrayList<>(driver.findElements(By.tagName("a")));
                   for (int y = 0; y < elements.size(); y++) {
                       if (elements.get(y).getAttribute("href") != null && elements.get(y).getAttribute("href").contains("dns")) {
                           if (elements.get(y).getAttribute("href").contains("product") || elements.get(y).getAttribute("href").contains("catalog")) {
                               if (listUrl.contains(elements.get(y).getAttribute("href"))) {
                                   Thread.sleep(50);
                                   System.out.println("Уже существует");
                                   continue;
                               } else {
                                   Thread.sleep(50);
                                   listUrl.add(elements.get(y).getAttribute("href"));
                                   System.out.println("Текущий шаг " + i);
                                   System.out.println("Всего шагов " + listUrl.size());
                                   System.out.println("Размер Элементов " + elements.size());
                                   System.out.println("Колличество ошибок " + error);
                                   System.out.println(elements.get(y).getAttribute("href"));
                               }
                           }
                       }
                   }
                }catch (StaleElementReferenceException | InterruptedException ex){
                   error++;
                   System.out.println(ex);
               }
       }

       for (int x = 0; x < listUrl.size(); x++) {
           System.out.println(listUrl.get(x));
       }
       driver.close();
       return listUrl;
   }
}

//List<WebElement> elements = driver.findElements(By.xpath("//ul[@class='subcategory__childs-list']"));
//        driver.get("https://www.dns-shop.ru/catalog/e3d826d63bb17fd7/texnika-dlya-kuxni/");
//        List<WebElement> elements = driver.findElements(By.xpath("//a[@class='subcategory__item ui-link ui-link_blue']"));
//        System.out.println(elements.get(0).getAttribute("href"));
//        System.out.println(elements.size());
