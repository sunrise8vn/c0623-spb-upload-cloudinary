package com.cg.repository;

import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;


@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);


    @Query("SELECT SUM (cd.amount) FROM CartDetail AS cd WHERE cd.cart = :cart")
    BigDecimal getSumAmountByCart(@Param("cart") Cart cart);
}
