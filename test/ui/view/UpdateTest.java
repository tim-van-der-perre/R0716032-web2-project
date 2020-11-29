package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\timva\\Documents\\UCLL\\belangrijke files\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/R0716032_Project_war_exploded/KotInfo?command=updateNaVraag&naam=Tim&contact=Helene");
    }

    @After
    public void clean() {
        driver.close();
    }


    @Test
    public void Test_datum_leeg_submitten_blijft_op_updaten_en_geeft_foutmelding(){
        WebElement datum = driver.findElement(By.name("datum"));
        datum.clear();
        datum.sendKeys("");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();
        ArrayList<WebElement>lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(heeftWebelementenMetTekst(lis, "Gelieve een datum te selecteren."));
    }

    @Test
    public void Test_datum_correct_submitten_blijft_update_en_gaat_naar_overzicht(){
        WebElement datum = driver.findElement(By.name("datum"));
        datum.clear();
        datum.sendKeys("27-11-2020");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("Hier vind je een klein overzicht van ieders persoonlijke bubbel", driver.findElement(By.tagName("h2")).getText());
    }


    private boolean heeftWebelementenMetTekst(ArrayList<WebElement> elements, String text){
        for (WebElement w: elements){
            if (w.getText().equals(text)){
                return true;
            }
        }
        return false;
    }
}
