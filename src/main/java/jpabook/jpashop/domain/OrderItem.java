package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    @OneToMany
    private ITem iTem;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격

    private int count;      // 주문 수량

}
