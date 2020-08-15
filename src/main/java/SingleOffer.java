public class SingleOffer {

    private long id;
    private int numberOfRooms;
    private int price;
    private float area;
    private int pricePerArea;

    public SingleOffer(int numberOfRooms, int price, float area, int pricePerArea) {
        this.numberOfRooms = numberOfRooms;
        this.price = price;
        this.area = area;
        this.pricePerArea = pricePerArea;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public int getPrice() {
        return price;
    }

    public float getArea() {
        return area;
    }

    public int getPricePerArea() {
        return pricePerArea;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public void setPricePerArea(int pricePerArea) {
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
