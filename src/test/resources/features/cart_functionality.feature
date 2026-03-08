Feature: Funcionalidad del carrito de compras
  Como usuario de la aplicación
  Quiero agregar productos al carrito
  Para validar que se actualice correctamente

  Background:
    Given estoy en la aplicación de SauceLabs
    And valido que carguen correctamente los productos en la galeria

  Scenario Outline: Agregar productos al carrito
    When agrego <cantidad> del siguiente producto "<producto>"
    Then valido el carrito de compra actualice correctamente para "<producto>" con <cantidad> unidades

    Examples:
      | producto               | cantidad |
      | Sauce Labs Backpack    | 1        |
      | Sauce Labs Bolt T-Shirt| 1        |
      | Sauce Labs Fleece Jacket | 2      |
