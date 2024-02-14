package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import  java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class ProductRepository {
    private long idCounter=0;
    private  List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        product.setProductId(Long.toString(idCounter++));
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public void edit(Product editProduct){
        int counter =0;
        for(Product product:productData){
            if(product.getProductId().equals(editProduct.getProductId())){
                productData.set(counter,editProduct);
            }
            counter++;
        }
    }
    public Product delete(String id){
        for (int i=0; i<productData.size(); i++){
            if (productData.get(i).getProductId().equals(id)){
                return productData.remove(i);
            }
        }
        return null;
    }

    public Product  getProductById(String id){
        for (int i=0; i<productData.size(); i++){
            if (productData.get(i).getProductId().equals(id)){
                return productData.get(i);
            }
        }
        return null;
    }
}
