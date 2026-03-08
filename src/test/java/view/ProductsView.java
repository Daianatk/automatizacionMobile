package view;

import driver.MobileDriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;

import java.time.Duration;

public class ProductsView {

    private AppiumDriver driver;

    public ProductsView() {
        this.driver = MobileDriverManager.getDriver();
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),
                this
        );
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Products']")
    @iOSXCUITFindBy(accessibility = "Products")
    private WebElement productsTitle;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/cartBt")
    @iOSXCUITFindBy(accessibility = "Add To Cart")
    private WebElement addToCartButton;

    @AndroidFindBy(accessibility = "Displays number of items in your cart")
    @iOSXCUITFindBy(accessibility = "Cart")
    private WebElement cartIcon;

    public boolean isOnProductsScreen() {
        return productsTitle.isDisplayed();
    }

    public void scrollToProduct(String productName) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().description(\"" + productName + "\"))"
        ));
    }

    // ✅ Tap con scroll + espera explícita
    public void tapProductByName(String productName) {
        scrollToProduct(productName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId(productName)));
        product.click();
    }

    public void scrollToAddToCartButton() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/cartBt\"))"
        ));
    }

    public void tapAddToCart() {
        scrollToAddToCartButton();
        addToCartButton.click();
    }

    public void openCart() {
        cartIcon.click();
    }

    // ✅ Método robusto para evitar errores y manejar un solo producto
    public int getCartQuantity() {
        int retries = 3;
        while (retries > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement cartCounter = wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartTV")
                        )
                );
                String counterText = cartCounter.getText().trim();

                // Si el texto está vacío, significa que hay un solo producto
                if (counterText.isEmpty()) {
                    return 1;
                }
                return Integer.parseInt(counterText);
            } catch (StaleElementReferenceException e) {
                retries--;
                try { Thread.sleep(500); } catch (InterruptedException ignored) {}
            } catch (Exception e) {
                System.out.println("Error obteniendo cantidad del carrito: " + e.getMessage());
                return 0;
            }
        }
        throw new RuntimeException("No se pudo obtener el contador del carrito después de varios intentos");
    }
}
