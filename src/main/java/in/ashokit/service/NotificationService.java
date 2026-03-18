package in.ashokit.service;

import in.ashokit.dto.OrderDto;

import java.util.List;

public interface NotificationService {

    // Order Confirmation after payment
    public OrderDto sendOrderConfirmation(OrderDto orderDto);

    // Delivery Updates
    public List<OrderDto> sendDeliveryNotification();

    // Order Cancelled
    public OrderDto cancelledOrderNotification(OrderDto orderDto);

    // Offers
    public void sendOfferNotification();
}
