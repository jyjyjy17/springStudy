package hello.itemservice.repository.JdbcTemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

/**
 * JDBC TEMPLATE
 */
public class JdbcTemplateItemRepositoryV1 implements ItemRepository {

    private final JdbcTemplate template;

    public JdbcTemplateItemRepositoryV1(DataSource datasource) {
        this.template = new JdbcTemplate(datasource);
    }

    @Override
    public Item save(Item item) {
        String sql = "insert into item(item_name,price,quantity) values (?,?,?)";
        //id 값 생성
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            //자동 증가 키
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
        },keyHolder);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {

    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        return null;
    }
}
