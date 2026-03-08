package glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import view.ProductsView;
import step.ProductStep;

public class ProductStepDefinition {

    ProductsView productsView = new ProductsView();
    ProductStep productStep = new ProductStep();

    @Given("estoy en la aplicación de SauceLabs")
    public void estoy_en_la_aplicacion_de_saucelabs() {
        Assert.assertTrue("La aplicación no cargó correctamente",
                productsView.isOnProductsScreen());
    }

    @Given("valido que carguen correctamente los productos en la galeria")
    public void valido_carga_productos_en_galeria() {
        Assert.assertTrue("No se cargaron productos en la galería",
                productsView.isOnProductsScreen());
    }

    @When("agrego {int} del siguiente producto {string}")
    public void agrego_unidades_del_producto(int unidades, String producto) {
        productsView.scrollToProduct(producto);
        productsView.tapProductByName(producto);
        for (int i = 0; i < unidades; i++) {
            productsView.tapAddToCart(); // cada clic agrega 1 unidad
        }
    }

    @Then("valido el carrito de compra actualice correctamente para {string} con {int} unidades")
    public void valido_carrito_actualizado_para_producto(String producto, int unidades) {
        productsView.openCart();

        // ✅ Obtenemos el valor real del carrito
        int actualQuantity = productsView.getCartQuantity();

        // ✅ Mensaje más descriptivo en caso de fallo
        Assert.assertEquals("❌ El carrito no se actualizó correctamente para " + producto +
                        ". Se esperaba: " + unidades + " pero el contador mostró: " + actualQuantity,
                unidades, actualQuantity);
    }
}
