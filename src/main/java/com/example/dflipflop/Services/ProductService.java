package com.example.dflipflop.Services;

import com.example.dflipflop.Entities.Product;
import com.example.dflipflop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

        @Autowired
        private ProductRepository productRepository;

        public ArrayList<Product> findAll() {

            Iterable<Product> it = productRepository.findAll();

            ArrayList<Product> products = new ArrayList<Product>();
            it.forEach(products::add);

            return products;
        }

        // should we make it return void ?
        public Boolean insert(Product product){
            try {
                productRepository.save(product);
            }
            catch(Exception e){
                return false;
            }
            return true;

        }

        public Long count() {
            return productRepository.count();
        }
}