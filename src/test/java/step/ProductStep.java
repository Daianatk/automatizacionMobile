package step;

import view.ProductsView;
import io.appium.java_client.AppiumBy;
import driver.MobileDriverManager;

public class ProductStep {

    ProductsView productsView = new ProductsView();

    public void tapProductByName(String productName) {
        productsView.tapProductByName(productName);
    }

    public void tapButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Add to cart")) {
            productsView.tapAddToCart();
        }
    }

    public void openCart() {
        productsView.openCart();
    }

    public boolean isProductVisible(String productName) {
        return MobileDriverManager
                .getDriver()
                .findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + productName + "']"))
                .isDisplayed();
    }

    public void scrollToProduct(String productName) {
        productsView.scrollToProduct(productName);
    }

    // ✅ Ahora usamos el método getCartQuantity para obtener el valor real del carrito
    public int getCartQuantity() {
        return productsView.getCartQuantity();
    }

    public boolean isCartUpdatedCorrectly(int unidades) {
        return getCartQuantity() == unidades;
    }
}
