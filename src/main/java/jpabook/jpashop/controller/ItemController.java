package jpabook.jpashop.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ItemController {

    @Resource
    ItemService itemService;

    // 상품 구매 폼
    @GetMapping("/items/new")
    public String createform(Model model) {
        model.addAttribute("form", new Bookform());
        return "items/createItemForm";
    }

    // 상품 구매 처리
    @PostMapping("/items/new")
    public String create(Bookform form) {
        Book book = new Book();
        // set으로 설정하는 것 보다는 createBook() 이런식으로 메서드를 만들어서 값을 세팅하는 방법을 매우 권장!
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        itemService.saveItem(book);
        return "redirect:/";
    }
      
    // 상품 목록
    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }
    
    // 상품 수정 폼
    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        Bookform form = new Bookform();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    // 상품 수정 처리
    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") Bookform form, @PathVariable Long itemId) {
        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }
}
