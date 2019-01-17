package com.chaung.jasper.repository;

import com.chaung.jasper.domain.OrderDelivery;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderDelivery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Long>, JpaSpecificationExecutor<OrderDelivery> {

}
