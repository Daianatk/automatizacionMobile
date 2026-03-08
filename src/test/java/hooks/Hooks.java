package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import driver.MobileDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("=== Starting Scenario: " + scenario.getName() + " ===");
        MobileDriverManager.createDriver();

        // ✅ Espera implícita para todos los elementos
        MobileDriverManager.getDriver()
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    // ✅ Screenshot después de cada step
    @AfterStep
    public void takeScreenshot(Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) MobileDriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Step Screenshot");
        } catch (Exception e) {
            System.out.println("Could not capture screenshot: " + e.getMessage());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("=== Ending Scenario: " + scenario.getName() + " ===");

        // ✅ Captura final solo si el escenario falla
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) MobileDriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            } catch (Exception e) {
                System.out.println("Could not capture failure screenshot: " + e.getMessage());
            }
        }

        MobileDriverManager.quitDriver();
    }
}