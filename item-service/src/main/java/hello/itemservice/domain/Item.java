package hello.itemservice.domain;

import lombok.Data;
//lombok이 자동으로 getter, setter 생성해줌
@Data
public class Item {
    private Long id;
    private String itemName;
    private Integer price;//int가 아닌 Integer를 쓰는 이유는 수량이 없는 상황을 가정해서이다.
    private Integer quantity;

    public Item(){}
    public Item(String itemName, Integer price, Integer Quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = Quantity;
    }
}
