package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository implements ProductRepositoryInterface {
    private List<Product> productData = new ArrayList<>();

    public void create(Product product) {
        productData.add(product);
    }

    public boolean delete(Product product) {
        return productData.remove(product);
    }

    public Product edit(String id, Product product) {
        Iterator<Product> products = findAll();

        int index = 0;
        for (; products.hasNext(); index++) {
            Product currentProduct = products.next();
            if (currentProduct.getProductId().equals(id)) {
                product.setProductId(currentProduct.getProductId());
                return productData.set(index, product);
            }
        }

        return null;
    }

    public Product get(String id) {
        Iterator<Product> products = findAll();

        while (products.hasNext()) {
            Product currentProduct = products.next();
            if (currentProduct.getProductId().equals(id)) {
                return currentProduct;
            }
        }
        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
}
