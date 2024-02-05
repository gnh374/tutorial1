package id.ac.ui.cs.advprog.eshop.functional;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest(){
        baseUrl=String.format("%s:%d/product/create", testBaseUrl, serverPort);
    }
    @Test
    void PageTitle_iscorrect(ChromeDriver driver) throws Exception{
        driver.get(baseUrl);
        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void addProduct_successed(ChromeDriver driver) throws Exception{
        driver.get(baseUrl);
        WebElement name = driver.findElement(By.id("nameInput"));
        WebElement quantity = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));

        name.sendKeys("new");
        quantity.sendKeys("10");
        submitButton.click();

        String listUrl=String.format("%s:%d/product/list", testBaseUrl, serverPort);
        driver.get(listUrl);

        WebElement table = driver.findElement(By.id("table"));
        WebElement cellInRow1Column1 = table.findElement(By.xpath("//tbody/tr[1]/td[1]"));
        String nameSaved = cellInRow1Column1.getText();
        WebElement cellInRow1Column2 = table.findElement(By.xpath("//tbody/tr[1]/td[2]"));
        String quantitySaved = cellInRow1Column2.getText();
        assertEquals(nameSaved,"new");
        assertEquals(quantitySaved,"10");

    }
}
