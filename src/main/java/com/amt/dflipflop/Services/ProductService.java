package com.amt.dflipflop.Services;

import com.amt.dflipflop.Entities.Product;
import com.amt.dflipflop.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {

        @Autowired
        private ProductRepository productRepository;

        public ArrayList<Product> getAll() {

            Iterable<Product> it = productRepository.findAll();

            ArrayList<Product> products = new ArrayList<Product>();
            it.forEach(products::add);

            return products;
        }

        public Product get(Integer id) {
            Optional<Product> product = productRepository.findById(id);
            return product.orElse(null);
        }

        // should we make it return void ?
        public Boolean insert(Product product){
            productRepository.save(product);
            /*try {
                productRepository.save(product);
            }
            catch(Exception e){
                return false;
            }*/
            return true;
        }

        public Long count() {
            return productRepository.count();
        }
}