import org.springframework.jdbc.core.JdbcTemplate;

public class MyMain {
    public static void main(String[] args) {
        //pewnie bean byłby dobry
        Offers offers = new Offers();
        OffersDAO offersDAO = new OffersDAO(new JdbcTemplate(DataSourceForJDBC.getDataSource()));

        Scraping.scrap(offers, offersDAO);
        //offers.printOffers();
        offers.numberOfOffers();
    }
}
