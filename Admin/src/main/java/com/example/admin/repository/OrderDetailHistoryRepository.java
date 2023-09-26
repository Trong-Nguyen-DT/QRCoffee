package com.example.admin.repository;

import com.example.admin.entity.OrderDetailHistoryEntity;
import com.example.admin.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailHistoryRepository extends JpaRepository<OrderDetailHistoryEntity, Long> {

//    @Query("SELECT od.productId, SUM(od.quantity) AS totalQuantity " +
//            "FROM OrderDetailHistoryEntity od " +
//            "GROUP BY od.productId " +
//            "ORDER BY totalQuantity DESC ")
//    List<Object[]> findTop4SellingProducts();
//
//    default List<Object[]> findTop4SellingProductsLimited() {
//        return findTop4SellingProducts().subList(0, 4);
//    }

}
