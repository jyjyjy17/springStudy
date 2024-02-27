package hello.ItemService.web.basic;

import hello.ItemService.domain.item.Item;
import hello.ItemService.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller()
@RequestMapping("basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item itemByID = itemRepository.findById(itemId);
        model.addAttribute("item",itemByID);
        return "basic/item";
    }
    @GetMapping("/add")
    public String addItem() {
        return "basic/addForm";
    }
    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, Model model) {
        Item ItemById = itemRepository.findById(itemId);
        model.addAttribute("item",ItemById);
        return "basic/editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId,@ModelAttribute Item item,RedirectAttributes redirectAttributes) {
        itemRepository.update(itemId, item);
        redirectAttributes.addAttribute("itemId", itemId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }
}
