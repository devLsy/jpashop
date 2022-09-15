package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;
    
    // 상품 저장
    public void save(Item iTem) {
        if(iTem.getId() == null) {
            em.persist(iTem);
        } else {
            em.merge(iTem);
        }
    }

    // 상품 단건 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    // 상품 목록 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
