package hello.ItemService.domain.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Slf4j
public class ItemRepository {
    //뭘로 저장하지?
    private static final Map<Long, Item> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(0L); //생성과 동시에 초기화 돼야 함
    public Item save(Item item){
        item.setId(sequence.addAndGet(1));

        log.info(String.valueOf(item.getId()));
        store.put(item.getId(), item);
        return item;
    }
    //검색(상세용 findById)
    public Item findById(Long id){
        return store.get(id);
    }

    //검색(목록용 findAll)
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    //수정
    public void update(Long itemId, Item updateParam) {
        Item itemById = this.findById(itemId);
        //update에서 id는 사용되지 않는데, 이 때 다른 개발자가 의문을 가질 수 있다. 따라서 updateItemDTO와 같이 따로 만드는것도 좋다.
        itemById.setItemName(updateParam.getItemName());
        itemById.setPrice(updateParam.getPrice());
        itemById.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
