package hello.itemservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    //sprint container로 사용하는게 아니라 혹시 new로 ItemRepository를 생성하는 경우 대비 해서 static 사용
    private static final Map<Long,Item> store = new HashMap<>();
    //실무에서 multi thread 환경에서 hashMap 사용하면 안된다. ConcurrentHashMap
    //long도 마찬가지로 atomic long 사용해야 함.
    private static long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(),item);
        return item;
    }
    public Item findById(Long id) {
        return store.get(id);
    }
    //Map의 values를 그대로 반환하게 되면 외부(ItemRepository)에서 Map 내 element를 삭제할 수 있게 됩니다.
    // 그런데 강의와 같이 ArrayList로 한 번 감싸게 되면 Map의 values에 변경을 가할 수 없게 됩니다.
    // 이런 이유로 ArrayList로 한 번 감싼 것입니다.
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
    public void clearStore() {
        store.clear();
    }
}
