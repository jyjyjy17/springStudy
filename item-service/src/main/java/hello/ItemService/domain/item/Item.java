package hello.ItemService.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data 를 쓰는 것 보댜 아래처럼 필요한 것만 쓰는게 안전하다
@Getter @Setter
public class Item {
    private Long id; // null로 들어갈 가능성을 염두에 두고 Long
    private String itemName;
    private Integer price; //null로 들어갈 가능성을 염두에 두고 Integer로
    private Integer quantity;

    public Item(){}

    public Item(String itemName,int price,int quantity){
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
