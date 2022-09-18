package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    // 주문 등록
    public void save(Order order) {
        em.persist(order);
    }

    // 주문 단건 조회
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // 주문 목록 조회
    public List<Order> findAll(OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m";
        return em.createQuery(jpql, Order.class)
                .setMaxResults(1000)
                .getResultList();
    }
}
