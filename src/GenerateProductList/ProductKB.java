/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GenerateProductList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Rajasri
 */
/*
 * This class is used to store the details about the product
 */
public class ProductKB {
    private List<Product> products = new ArrayList<Product>();
    
    public static ProductKB instance = new ProductKB();
    
    public List<Product> getProductList()
    {
        return products;
    }
    
    public void loadProductData(List<Product> products)
    {
        this.products = products;
    }
    
    public void addNewProduct(Product product)
    {
         this.products.add(product);
    }
    
    public static Comparator<Product> COMPARE_BY_VOL_VALUE = new Comparator<Product>() {
        public int compare(Product one, Product other) {
            return 
                        (
                        new Double
                        (
                            (other.getSellingPrice()/(other.getLength()*other.getWidth()*other.getHeight()))
                        )
                        )
                        .compareTo
                        (
                        new Double(
                        (
                            one.getSellingPrice()/(one.getLength()*one.getWidth()*one.getHeight()))
                        )
                        );
        }
    };
    
    public void sortProductList(String attribute)
    {
        if(attribute.equalsIgnoreCase("volume"))
        {
            Collections.sort(products, COMPARE_BY_VOL_VALUE);
        }
        else
        {
            System.out.print("Not yet implemented. So list remains");
        }
    }
    
}
