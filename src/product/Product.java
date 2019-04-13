package product;

import java.util.Objects;

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

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getProductName() {
        return productname;
    }

    public void setProductName(String productname) {
        this.productname = productname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return harga == product.harga &&
                productname.equals(product.productname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(harga, productname);
    }
}
