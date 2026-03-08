# Mobile Test Automation Framework

Este proyecto implementa un framework de **automatización de pruebas móviles** utilizando **Java, Appium, Cucumber y JUnit**.  
El objetivo es validar la funcionalidad de la aplicación demo de SauceLabs (Android), incluyendo el flujo de agregar productos al carrito y verificar que se actualice correctamente.

---

## 🚀 Tecnologías utilizadas
- **Java 25**
- **Appium Java Client 8.3.0**
- **Selenium 4.8.3**
- **Cucumber 7.15.0**
- **JUnit 4.13.2**
- **Maven**


📊 Reportes
Al finalizar la ejecución, se generan reportes en:

target/cucumber-report.html

target/cucumber-report.json

✅ Funcionalidades cubiertas
Validar que la aplicación cargue correctamente.

Scroll y selección de productos por nombre.

Agregar productos al carrito (1 o más unidades).

Validar que el contador del carrito se actualice correctamente.

Captura de screenshots en cada step.

Limpieza automática del carrito antes y después de cada escenario.