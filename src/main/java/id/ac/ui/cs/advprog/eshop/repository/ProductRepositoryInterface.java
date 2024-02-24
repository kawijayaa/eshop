package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;

public interface ProductRepositoryInterface {
    public void create(Product product);
    public boolean delete(Product product);
    public Product edit(String id, Product product);
    public Product get(String id);
    public Iterator<Product> findAll();
}
