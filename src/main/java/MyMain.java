import org.springframework.jdbc.core.JdbcTemplate;


public class MyMain {
    public static void main(String[] args) {

        OffersDAO offersDAO = new OffersDAO(new JdbcTemplate(DataSourceForJDBC.getDataSource()));

        Scraping.scrap(offersDAO);

    }


}
