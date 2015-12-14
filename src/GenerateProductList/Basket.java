/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GenerateProductList;

/**
 *
 * @author Rajasri
 */
public class Basket {
    double height;
    double width;
    double length;
    double volume;
    
    public void setHeight(double ht)
    {
    this.height = ht;
    }
    public void setWidth(double wt)
    {
        this.width = wt;
    }
    public void setLength(double ln)
    {
        this.length = ln;
    }
    
    public double getHeight()
    {
        return this.height;
    }
    public double getWidth()
    {
        return this.width;
    }
    public double getLength()
    {
        return this.length;
    }
    
    
    public void setVolume(double vol)
    {
        this.volume = vol;
    }
    
    public double getVolume()
    {
        return this.volume;
    }
}
