package GenerateProductList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 /**
 @author Rajasri Janakiraman
 **/
public class FindProducts 
{
    public void loadKB()
    {
	    String csvFile = "ProductsListFile/products.csv"; //Default path from where the list of products file will be taken from
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	    ProductKB productKb = ProductKB.instance;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			//Till EOF, read line by line and create products kb
			while ((line = br.readLine()) != null) 
			{
				// use comma as separator
                String[] productData = line.split(cvsSplitBy);
                long pid = Long.parseLong(productData[0]);
                double sp = Double.parseDouble(productData[1]);
                double len = Double.parseDouble(productData[2]);
                double width = Double.parseDouble(productData[3]);
                double ht = Double.parseDouble(productData[4]);
                double weight = Double.parseDouble(productData[5]);
                Product product = new Product(pid, sp, len, width, ht, weight);                    
                productKb.addNewProduct(product);                        
			}
	
		} 
		catch (FileNotFoundException e) {
			System.out.println("Couldn't find the file in the required path");
		} 
		catch (IOException e) {
			System.out.println("Incorrect data format in the product list file");
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Incorrect data in/corrupt product list file");
				}
			}
		}
		//Sorted the product list based on value of product/cu cm
		//This places the products with high probability of getting selected at the top
		//In future, this can be used to improvise the program to attain result even faster. Currently working on this.
	    productKb.sortProductList("volume");
    }
    
    public static void main(String[] args) 
    {
        
        FindProducts fp = new FindProducts();
        fp.loadKB();
        Basket basket = new Basket();
        basket.setLength(45);
        basket.setWidth(30);
        basket.setHeight(35);
        basket.setVolume(basket.getLength()*basket.getWidth()*basket.getHeight());
        //Volume of the basket is calculated and set
        System.out.println("Product Id sum :" + fp.findProductSum(basket));
        
    }
    
    public long findProductSum(Basket basket) {
    
    	int basketWeight = (int) Math.floor(basket.getVolume());   // maximum weight of tote or basket
        
        List<Product> products = ProductKB.instance.getProductList();
        
        int noOfProducts = products.size();  // number of products (type) available in the store. This is not the stock details. Just the type.
        
        double[] price = new double[noOfProducts+1]; 
        double[] volume = new double[noOfProducts+1]; 
        int[] productId = new int[noOfProducts+1];

        for (int n = 1; n <= noOfProducts; n++) 
        {
        	//Selling price of the product
            price[n] = products.get(n-1).getSellingPrice(); 
            //Volume occupied by this product when placed in basket
            volume[n] = (products.get(n-1).getLength()*products.get(n-1).getWidth()*products.get(n-1).getHeight());            
            productId[n] = Integer.parseInt(""+products.get(n-1).getProductId());
        }

        // opt[n][w] = max profit of packing items 1..n with weight limit w
        // sol[n][w] = does opt solution to pack items 1..n with weight limit w include item n?
        int[][] opt = new int[noOfProducts+1][basketWeight+1];
        boolean[][] sol = new boolean[noOfProducts+1][basketWeight+1];

        for (int n = 1; n <= noOfProducts; n++) {
            for (int w = 1; w <= basketWeight; w++) {

                // don't take item n
                int option1 = opt[n-1][w];

                // take item n
                int option2 = Integer.MIN_VALUE;
                if (volume[n] <= w) option2 = (int) (price[n] + opt[n-1][(int) (w-volume[n])]);

                // select better of two options
                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = (option2 > option1);
            }
        }

        // determine which items to take
        boolean[] selectThisProduct = new boolean[noOfProducts+1];
        for (int n = noOfProducts, w = basketWeight; n > 0; n--) 
        {
            if (sol[n][w]) 
            {
            	selectThisProduct[n] = true;  
            	w = (int) (w - volume[n]); 
            }
            else
            { 
            	selectThisProduct[n] = false;                    
            }
        }

        // Display the selected products' detail
        long productIdSum = 0;
        double toteValue = 0;
        System.out.println("P.Id" + "\t" + "Price" + "\t" + "Volume");
        System.out.println("------------------------");
        for (int n = 1; n <= noOfProducts; n++) 
        {
        	if(selectThisProduct[n])
        	{ 
        		productIdSum += productId[n];
        		toteValue += price[n];
        		System.out.println(productId[n] + "\t" + price[n] + "\t" + volume[n]);
        	}
        }
        System.out.println("------------------------");
        System.out.println("Final Tote value: "+ toteValue);
        return productIdSum;
   }


}
