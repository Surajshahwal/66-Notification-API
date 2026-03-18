package in.ashokit.repo;

import in.ashokit.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    public List<OrderEntity> findByUserEmail(String email);

    public List<OrderEntity> findByDeliveryDate(LocaldDate deliveryDate);
}
