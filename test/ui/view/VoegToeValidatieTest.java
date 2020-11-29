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

public class VoegToeValidatieTest {
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
    public void formulier_wordt_opnieuw_getoond_met_error_messages_als_alle_velden_leeg_zijn(){
        WebElement kotgenoot = driver.findElement(By.name("naam"));
        kotgenoot.clear();
        kotgenoot.sendKeys("");

        WebElement contact = driver.findElement(By.name("contact"));
        contact.clear();
        contact.sendKeys("");

        WebElement datum = driver.findElement(By.name("datum"));
        datum.clear();
        datum.sendKeys("");

        WebElement knop = driver.findElement(By.id("knopform"));
        knop.submit();

        assertEquals("Mensen toevoegen aan je bubbel", driver.findElement(By.tagName("h2")).getText());
        ArrayList<WebElement>lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(heeftWebelementenMetTekst(lis, "Vul een naam in."));
        assertTrue(heeftWebelementenMetTekst(lis, "Vul een contactnaam in."));
        assertTrue(heeftWebelementenMetTekst(lis, "Vul een datum in."));
    }

    @Test
    public void Test_formulier_wordt_opnieuw_getoond_met_error_message_als_naam_leeg_is(){
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

        ArrayList<WebElement>lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(heeftWebelementenMetTekst(lis, "Vul een naam in."));

    }

    @Test
    public void Test_bestaand_contact_toevoegen_geeft_errormessage(){
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

        WebElement navigatieVoegToeKnop = driver.findElement(By.linkText("Voeg Toe"));
        navigatieVoegToeKnop.click();
        assertEquals("Mensen toevoegen aan je bubbel", driver.findElement(By.tagName("h2")).getText());

        WebElement kotgenoot1 = driver.findElement(By.name("naam"));
        kotgenoot1.clear();
        kotgenoot1.sendKeys("tim");
        WebElement contact1 = driver.findElement(By.name("contact"));
        contact1.clear();
        contact1.sendKeys("geraldine");
        WebElement datum1 = driver.findElement(By.name("datum"));
        datum1.clear();
        datum1.sendKeys("26-11-2020");
        WebElement knop1 = driver.findElement(By.id("knopform"));
        knop1.submit();

        assertEquals("Mensen toevoegen aan je bubbel", driver.findElement(By.tagName("h2")).getText());
        ArrayList<WebElement>lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(heeftWebelementenMetTekst(lis, "Contact is al vernomen!"));

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
