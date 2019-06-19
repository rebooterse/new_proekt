package models;


public class Clothes {
    private int clotheImage;
    private String shopName,price, address;


    public Clothes(int clotheImage, String shopName, String price, String address) {
        this.clotheImage = clotheImage;
        this.shopName = shopName;
        this.price = price;
        this.address = address;
    }


    public String getShopName() {
        return shopName;
    }

    public String getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public int getClotheImage() {
        return clotheImage;
    }
}
