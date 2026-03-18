package in.ashokit.mapper;

import in.ashokit.dto.OrderDto;
import in.ashokit.entity.OrderEntity;
import org.modelmapper.ModelMapper;

public class OrderMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static OrderDto toDto(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderDto.class);
    }

    public static OrderEntity toEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, OrderEntity.class);
    }
}
