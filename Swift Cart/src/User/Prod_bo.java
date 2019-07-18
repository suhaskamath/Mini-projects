package User;

public class Prod_bo {
    private String pname;
    private float price,qty,tprice;

    public float getPrice() {
        return price;
    }

    public String getPname() {
        return pname;
    }

    public float getQty() {
        return qty;
    }

    public float getTprice() {
        return tprice;
    }
     public Prod_bo(String pname,Float price,Float qty,Float tprice){
        this.price=price;
        this.tprice=tprice;
        this.pname=pname;
        this.qty=qty;
     }
}

