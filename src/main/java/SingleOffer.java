public class SingleOffer {

    private long id;
    private String numberOfRooms;
    private String price;
    private String area;
    private String pricePerArea;

    public SingleOffer(String numberOfRooms, String price, String area, String pricePerArea) {
        this.numberOfRooms = numberOfRooms;
        this.price = price;
        this.area = area;
        this.pricePerArea = pricePerArea;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public String getPrice() {
        return price;
    }

    public String getArea() {
        return area;
    }

    public String getPricePerArea() {
        return pricePerArea;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPricePerArea(String pricePerArea) {
        this.pricePerArea = pricePerArea;
    }

    @Override
    public String toString() {
        return "SingleOffer{" +
                "id=" + id +
                ", numberOfRooms='" + numberOfRooms + '\'' +
                ", price='" + price + '\'' +
                ", area='" + area + '\'' +
                ", pricePerArea='" + pricePerArea + '\'' +
                '}';
    }
}
