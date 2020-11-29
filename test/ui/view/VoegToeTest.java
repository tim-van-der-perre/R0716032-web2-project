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

public class VoegToeTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\timva\\Documents\\UCLL\\belangrijke files\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/R0716032_Project_war_exploded/formulier.jsp");
    }

    @After
    public void clean() {
        driver.close();
    }


    @Test
    public void Test_Leeg_formulier_submitten_blijft_op_formulier(){
        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();
        assertEquals("Mensen toevoegen aan je bubbel", driver.findElement(By.tagName("h2")).getText());
    }
    @Test
    public void Test_lege_naam_blijft_op_formulier(){
        WebElement kotgenoot = driver.findElement(By.name("naam"));
        kotgenoot.clear();
        kotgenoot.sendKeys("");

        WebElement contact = driver.findElement(By.name("contact"));
        contact.clear();
        contact.sendKeys("geraldine");

        WebElement datum = driver.findElement(By.name("datum"));
        datum.clear();
        datum.sendKeys("26-11-2020");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("Mensen toevoegen aan je bubbel", driver.findElement(By.tagName("h2")).getText());

    }@Test
    public void Test_leeg_contact_blijft_op_formulier(){
        WebElement kotgenoot = driver.findElement(By.name("naam"));
        kotgenoot.clear();
        kotgenoot.sendKeys("tim");

        WebElement contact = driver.findElement(By.name("contact"));
        contact.clear();
        contact.sendKeys("");

        WebElement datum = driver.findElement(By.name("datum"));
        datum.clear();
        datum.sendKeys("26-11-2020");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("Mensen toevoegen aan je bubbel", driver.findElement(By.tagName("h2")).getText());

    }@Test
    public void Test_lege_datum_blijft_op_formulier(){
        WebElement kotgenoot = driver.findElement(By.name("naam"));
        kotgenoot.clear();
        kotgenoot.sendKeys("tim");

        WebElement contact = driver.findElement(By.name("contact"));
        contact.clear();
        contact.sendKeys("geraldine");

        WebElement datum = driver.findElement(By.name("datum"));
        datum.clear();
        datum.sendKeys("");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("Mensen toevoegen aan je bubbel", driver.findElement(By.tagName("h2")).getText());

    }

    @Test
    public void Test_Correcte_invulvelden_gaan_naar_ovezicht(){
        WebElement kotgenoot = driver.findElement(By.name("naam"));
        kotgenoot.clear();
        kotgenoot.sendKeys("tim");

        WebElement contact = driver.findElement(By.name("contact"));
        contact.clear();
        contact.sendKeys("geraldine");

        WebElement datum = driver.findElement(By.name("datum"));
        datum.clear();
        datum.sendKeys("26-11-2020");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("Hier vind je een klein overzicht van ieders persoonlijke bubbel", driver.findElement(By.tagName("h2")).getText());
    }

    @Test
    public void Test_Correcte_invulvelden_voegen_contact_toe(){
        WebElement kotgenoot = driver.findElement(By.name("naam"));
        kotgenoot.clear();
        kotgenoot.sendKeys("tim");

        WebElement contact = driver.findElement(By.name("contact"));
        contact.clear();
        contact.sendKeys("geraldine");

        WebElement datum = driver.findElement(By.name("datum"));
        datum.clear();
        datum.sendKeys("26-11-2020");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        ArrayList<WebElement>tds = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertTrue(heeftWebelementenMetTekst(tds, "geraldine"));

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
