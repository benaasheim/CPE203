import java.util.*;
public class Session
{
    private List<String> viewedProducts;
    private Integer avgViewedPrice;
    private List<String> boughtnam;
    private List<Integer> boughtnum;
    private Map<String, Integer> pricediffs;

    public Session(){
        this.avgViewedPrice = avgViewedPrice;
        this.viewedProducts = viewedProducts;
        this.boughtnam = boughtnam;
        this.boughtnum = boughtnum;
        this.pricediffs = pricediffs;

    }
    public List<String> getViewedProducts(){return this.viewedProducts;}
    public List<String> getBoughtnam(){return this.boughtnam;}
    public void newView(int price, String productnum){
        this.viewedProducts.add(productnum);
        this.avgViewedPrice = ((this.avgViewedPrice * (this.viewedProducts.size()-1)) + price)/this.viewedProducts.size();
    }

    public void newBuy(int price, String productnum){
        this.boughtnam.add(productnum);
        this.boughtnum.add(price);
    }
    public void writeDiffs(){
        for (int i = 0; i < this.boughtnam.size(); i++){
            this.pricediffs.put(this.boughtnam.get(i), (this.boughtnum.get(i) - this.avgViewedPrice));
        }
    }
    public Map<String, Integer> getPricediffs(){
        return this.pricediffs;
    }
}
