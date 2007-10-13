package nl.gridshore.paymentsample.domain;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 29-aug-2007
 * Time: 21:22:43
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseOrder extends BaseDomain {
    private Payment payment;
    private String productName;
    private int amountOfProducts;
    private BigDecimal priceOfProduct;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmountOfProducts() {
        return amountOfProducts;
    }

    public void setAmountOfProducts(int amountOfProducts) {
        this.amountOfProducts = amountOfProducts;
    }

    public BigDecimal getPriceOfProduct() {
        return priceOfProduct;
    }

    public void setPriceOfProduct(BigDecimal priceOfProduct) {
        this.priceOfProduct = priceOfProduct;
    }
}
