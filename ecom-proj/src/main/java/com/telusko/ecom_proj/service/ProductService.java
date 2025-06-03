package com.telusko.ecom_proj.service;

import com.telusko.ecom_proj.model.Product;
import com.telusko.ecom_proj.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private Product product;

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product pro){

        return repo.save(pro);
    }

    public Product upDateProduct(int id, Product product) {
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    /*   public Product addProduct(Product pro, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(pro);
    }*/


}
