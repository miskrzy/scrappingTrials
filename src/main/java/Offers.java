import java.util.LinkedList;
import java.util.List;

public class Offers {
    private List<SingleOffer> allOffers;

    public Offers() {
        allOffers = new LinkedList<SingleOffer>();
    }

    public void addOffer(SingleOffer offer) {
        allOffers.add(offer);
    }

    public void printOffers() {
        for (SingleOffer offer : allOffers) {
            System.out.println(offer);
        }
    }

    public void numberOfOffers() {
        System.out.println(allOffers.size());
    }
}
