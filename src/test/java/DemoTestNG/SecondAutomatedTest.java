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

public class SecondAutomatedTest {
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
    public void checkBox() {
        // Vérifie que l'âge n'est pas coché avant de le cocher la checkbox
        driver.findElement(By.linkText("Checkbox Demo")).click();
        WebElement singleCheckbox = driver.findElement(By.id("isAgeSelected"));
        if (!singleCheckbox.isSelected()) {
            singleCheckbox.click();
        }

        // Vérifier que la checkbox est cochée
        Assert.assertTrue(singleCheckbox.isSelected(), "La checkbox n'a pas été cochée !");
    }

    @Test(priority = 1)
    public void dragAndDropSlider() throws InterruptedException {
        // Déplacer le slider
        driver.findElement(By.linkText("Drag & Drop Sliders")).click();
        WebElement slider = driver.findElement(By.xpath("(//input[@type='range'])[1]"));

        // Récupérer la valeur initiale
        String initialSliderValue = slider.getAttribute("value");
        int initialValue = Integer.parseInt(initialSliderValue);

        // Effectuer le déplacement du slider
        Actions action = new Actions(driver);
        action.clickAndHold(slider).moveByOffset(50, 0).release().perform();
        Thread.sleep(1000);

        // Récupérer la nouvelle valeur
        String updatedSliderValue = slider.getAttribute("value");
        int updatedValue = Integer.parseInt(updatedSliderValue);

        // Vérifier que la nouvelle valeur est pas supérieure à l'ancienne
        Assert.assertTrue(updatedValue > initialValue,
                "La valeur du slider (" + updatedValue + ") est supérieure à l'ancienne (" + initialValue + ") !");
    }

    public void testRadioButtons() {
        driver.findElement(By.linkText("Radio Buttons Demo")).click();
        driver.findElement(By.xpath("//input[@value='Other']")).click();

    }
}
