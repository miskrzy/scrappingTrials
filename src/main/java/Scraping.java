import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraping {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MAX_PAGES = 500;

    public static int scrap(OffersDAO offersDAO) {
        try {
            //filtry ustawione na 5 pokoi i Wrocław, co by serwera zbytnio nie kłopotać
            //ilosc wynikow na stronie pokazuje bez ofert promowanych (mimo, ze spelniaja filtr), wiec jest liczba stron*3 mniej niz tu
            Document doc = Jsoup.connect("https://www.otodom.pl/sprzedaz/mieszkanie/wroclaw/?search%5Bfilter_enum_rooms_num%5D%5B0%5D=5&search%5Bregion_id%5D=1&search%5Bcity_id%5D=39&nrAdsPerPage=72").get();

            boolean isPageFound = true;
            int counter = 0;
            while (isPageFound && (++counter)<=MAX_PAGES) {
                Elements articles = doc.select("article");
                for (Element article : articles) {

                    //scrapping
                    String rooms = article.select("li.offer-item-rooms").text();
                    String price = article.select("li.offer-item-price").text();
                    String area = article.select("li.offer-item-area").text();
                    String pricePerArea = article.select("li.offer-item-price-per-m").text();

                    try {
                        //parsing
                        int rooms2 = regIntFind("\\d+", rooms);
                        int price2 = regIntFind("\\d+", price);
                        float area2 = regFloatFind("\\d+,\\d+|\\d+", area);
                        int pricePerArea2 = regIntFind("\\d+", pricePerArea);

                        SingleOffer singleOffer = new SingleOffer(rooms2, price2, area2, pricePerArea2);

                        try {
                            //to db
                            offersDAO.saveOffer(singleOffer);
                            LOGGER.info("An offer has been added to database: " + singleOffer.toString());
                        }catch(Exception e){
                            LOGGER.error("Error with sql-querry or something", e);
                        }

                    }catch(NumberFormatException e){
                        LOGGER.error("Error while parsing strings from html to numbers", e);
                    }
                }

                //następna karta
                Attributes attributes = doc.selectFirst("div.after-offers li.pager-next a").attributes();
                if (attributes.get("class").equals("disabled")) {
                    LOGGER.info("No more pages found");
                    isPageFound = false;
                } else {
                    LOGGER.info("Another page found: "+(counter+1));
                    doc = Jsoup.connect(attributes.get("href")).get();
                }
            }
            return 0;

        } catch (IOException e) {
            LOGGER.error("Error while connecting to page", e);
            return 1;
        } catch (Exception e){
            LOGGER.error("Unknown error", e);
            return 1;
        }
    }

    public static int regIntFind(String regEx, String string) throws NumberFormatException{
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(string);
        String result="";
        while(matcher.find()){
            result+=matcher.group().trim();
        }
        if(result.length()!=0) {
            return Integer.parseInt(result);
        }else return 0;
    }

    public static float regFloatFind(String regEx, String string) throws NumberFormatException{
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(string);
        if(matcher.find()) {
            return Float.parseFloat(matcher.group().trim().replace(",", "."));
        }
        else return 0;
    }

}
