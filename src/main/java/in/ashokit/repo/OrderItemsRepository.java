package in.ashokit.repo;

import in.ashokit.entity.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Integer> {

    public List<OrderItemsEntity> findByOrderOrderId(Integer orderId);
}
