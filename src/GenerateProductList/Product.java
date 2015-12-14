/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GenerateProductList;

import java.util.Comparator;

/**
 *
 * @author Rajasri
 */
public class Product {
    
    public Product(long pid, double sp, double len, double wid, double ht, double wt)
    {
        this.productId = pid;
        this.sellingPrice = sp;
        this.length = len;//in cms
        this.width = wid;//in cms
        this.height = ht;//in cms
        this.weight = wt;//in KG
    }
    
    private long productId;
    private double sellingPrice;
    private double length;//in cms
    private double width;//in cms
    private double height;//in cms
    private double weight;//in KG???
    
    public long getProductId()
    {
        return this.productId;
    }
    
    public double getSellingPrice()
    {
        return this.sellingPrice;
    }
    
    public double getLength()
    {
        return this.length;
    }
    
    public double getWidth()
    {
        return this.width;
    }
    
    public double getHeight()
    {
        return this.height;
    }
    
    public double getWeight()
    {
        return this.weight;
    }
    
    public void setProductId(long id)
    {
        this.productId = id;
    }
    
    public void setSellingPrice(double price)
    {
        this.sellingPrice = price;
    }
    
    public void setLength(double length)
    {
        this.length = length;
    }
    
    public void setWidth(double width)
    {
        this.width = width;
    }
    
    public void setHeight(double height)
    {
        this.height = height;
    }
    
    public void setWeight(double weight)
    {
        this.weight = weight;
    } 
    
    
    

}



