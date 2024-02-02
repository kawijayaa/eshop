package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    private int id = 0;

    @Override
    public Product create(Product product) {
        product.setProductId(Integer.toString(++id));

        productRepository.create(product);
        return product;
    }

    @Override
    public boolean delete(int id) {
        Product product = null;

        Iterator<Product> products = productRepository.findAll();

        while (products.hasNext()) {
            Product current_product = products.next();
            if (current_product.getProductId().equals(Integer.toString(id))) {
                product = current_product;
                break;
            }
        }

        return product != null && productRepository.delete(product);
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }
}
