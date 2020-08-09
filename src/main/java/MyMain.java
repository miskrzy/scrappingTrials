

public class MyMain {
    public static void main(String[] args) {
        //pewnie bean byłby dobry
        Offers offers = new Offers();
        Scraping.scrap(offers);
        //offers.printOffers();
        offers.numberOfOffers();
    }
}
