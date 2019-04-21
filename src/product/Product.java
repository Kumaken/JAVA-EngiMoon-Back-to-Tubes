package product;

import java.util.Objects;

/**
 * Parent class of all products
 */
public class Product {
    protected int harga;
    protected String productname;

    public Product() {
        this.harga = 0;
        this.productname = "";
    }

    public Product(int harga, String productname) {
        this.harga = harga;
        this.productname = productname;
    }

    /**
     * Getter for product price
     * @return price of product
     */
    public int getHarga() {
        return harga;
    }

    /**
     * Setter for product price
     * @param harga (price) of product
     */
    public void setHarga(int harga) {
        this.harga = harga;
    }

    /**
     * Getter for product name
     * @return name of product
     */
    public String getProductName() {
        return productname;
    }

    /**
     * Setter for product name
     * @param productname (name of product)
     */
    public void setProductName(String productname) {
        this.productname = productname;
    }

    /**
     * Comparison override to compare product object with
     * another product object
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return harga == product.harga &&
                productname.equals(product.productname);
    }

    /**
     * hashCode as part of comparison override
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(harga, productname);
    }
}
