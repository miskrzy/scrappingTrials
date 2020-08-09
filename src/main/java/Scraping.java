import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Scraping {

    private static final Logger LOGGER = LogManager.getLogger();

    public static int scrap(Offers offers) {
        try {
            //filtry ustawione na 5 pokoi i Wrocław, co by serwera zbytnio nie kłopotać
            //ilosc wynikow na stronie pokazuje bez ofert promowanych (mimo, ze spelniaja filtr), wiec jest liczba stron*3 mniej niz tu
            Document doc = Jsoup.connect("https://www.otodom.pl/sprzedaz/mieszkanie/wroclaw/?search%5Bfilter_enum_rooms_num%5D%5B0%5D=5&search%5Bregion_id%5D=1&search%5Bcity_id%5D=39&nrAdsPerPage=72").get();

            while(true) {
                Elements articles = doc.select("article");
                for (Element article : articles) {
                    //poki co jako stringi
                    //na razie tyle, pewnie jakies id oferty gdzies tam jest
                    String rooms = article.select("li.offer-item-rooms").text();
                    String price = article.select("li.offer-item-price").text();
                    String area = article.select("li.offer-item-area").text();
                    String pricePerArea = article.select("li.offer-item-price-per-m").text();

                    LOGGER.info("An offer has been added");
                    offers.addOffer(new SingleOffer(rooms, price, area, pricePerArea));
                }

                //następna karta
                Attributes attributes = doc.selectFirst("div.after-offers li.pager-next a").attributes();
                if(attributes.get("class").equals("disabled")){
                    LOGGER.info("No more pages found");
                    break;
                }else{
                    LOGGER.info("Another page found");
                    doc = Jsoup.connect(attributes.get("href")).get();
                }
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return 1;
        }
    }
}
