package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class ItemValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        //Item class와 타입이 일치하는지만 return  해주면 된다
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item)target;
        //ItemName 값 비어 있는지 확인
        if(!StringUtils.hasText(item.getItemName())){
            errors.rejectValue("itemName","required");
        }
        //price 가격 대 확인
        if(item.getPrice()==null || item.getPrice()<10000 || item.getPrice() >1000000){
            errors.rejectValue("price","range",new Object[]{10000,1000000},null);
        }
        if(item.getQuantity()==null || item.getQuantity()>9999){
            errors.rejectValue("price","max",new Object[]{9999},null);
        }
        if(item.getQuantity()!=null &&item.getPrice()!=null && item.getPrice()*item.getQuantity()<10000){
            errors.reject("totalPriceMin",new Object[]{10000,item.getPrice()*item.getQuantity()},null);
        }
    }
}
