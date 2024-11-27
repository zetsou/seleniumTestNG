package DemoTestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstAutomatedTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Configuration du driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void returnLandingPage() {
        // Charger la page principale avant chaque test
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @AfterClass
    public void tearDown() {
        // Fermer le navigateur après tous les tests
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testTableSortAndSearch() {
        // Naviguer vers "Table Sort & Search" et rechercher un élément
        driver.findElement(By.linkText("Table Sort & Search")).click();
        driver.findElement(By.xpath("//div[@id='example_filter']//input[@type='search']")).sendKeys("A. Satou");

        // Vérifier que le texte est présent
        WebElement searchResult = driver.findElement(By.xpath("//td[contains(text(),'A. Satou')]"));
        Assert.assertTrue(searchResult.isDisplayed(), "Le résultat 'A. Satou' n'a pas été trouvé !");
    }

    @Test(priority = 1)
    public void bootstrapDatePickers() {
        // Naviguer vers "Bootstrap Date Picker" et saisir une date
        driver.findElement(By.linkText("Bootstrap Date Picker")).click();
        WebElement dateInput = driver.findElement(By.id("birthday"));
        dateInput.sendKeys("10/11/2002");

        // Vérifier que la valeur est saisie correctement
        Assert.assertEquals(dateInput.getAttribute("value"), "2002-11-10", "La date saisie est incorrecte !");
    }

    @Test(priority = 2)
    public void simpleForm() {
        // Naviguer vers "Simple Form Demo", saisir un message et cliquer sur le bouton
        driver.findElement(By.linkText("Simple Form Demo")).click();
        WebElement messageInput = driver.findElement(By.id("user-message"));
        messageInput.sendKeys("Hello, LambdaTest!");
        driver.findElement(By.xpath("//button[contains(text(),'Get Checked Value')]")).click();

        // Vérifier que le message s'affiche correctement
        WebElement displayedMessage = driver.findElement(By.id("message"));
        Assert.assertEquals(displayedMessage.getText(), "Hello, LambdaTest!", "Le message affiché est incorrect !");
    }

    @Test(priority = 3)
    public void radioButton() {
        // Naviguer vers "Radio Buttons Demo" et cocher le bouton radio
        driver.findElement(By.linkText("Radio Buttons Demo")).click();
        WebElement radioButton = driver.findElement(By.xpath("//input[@value='Male' and @name='optradio']"));
        radioButton.click();
        driver.findElement(By.id("buttoncheck")).click();

        // Vérifier que le bon message est affiché
        WebElement resultMessage = driver.findElement(By.className("radiobutton"));
        Assert.assertTrue(resultMessage.getText().contains("Male"), "Le message du bouton radio est incorrect !");
    }

}
