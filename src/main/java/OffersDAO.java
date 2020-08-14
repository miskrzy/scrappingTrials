import org.springframework.jdbc.core.JdbcTemplate;

public class OffersDAO {

    private JdbcTemplate jdbcTemplate;

    public OffersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveOffer(SingleOffer singleOffer){
        String sql="insert into offers (number_of_rooms, price, area, price_per_m) values (\'"
                +singleOffer.getNumberOfRooms()+"\', \'"
                +singleOffer.getPrice()+"\', \'"
                +singleOffer.getArea()+"\', \'"
                +singleOffer.getPricePerArea()+"\');";

        return jdbcTemplate.update(sql);
    }
}
