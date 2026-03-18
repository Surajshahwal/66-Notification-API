package in.ashokit.dto;

import lombok.Data;

import java.util.List;


@Data
public class PurchaseOrderRequestDto {

    private UserDto user;

    private ShippingAddressDto shippingAddress;

    private OrderDto order;

    private List<OrderItemDto> orderItems;
}

