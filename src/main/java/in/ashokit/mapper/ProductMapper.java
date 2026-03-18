package in.ashokit.mapper;

import in.ashokit.dto.ProductDto;
import in.ashokit.entity.ProductEntity;
import org.modelmapper.ModelMapper;

public class ProductMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProductDto toDto(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDto.class);
    }

    public static ProductEntity toEntity(ProductDto productDto) {
        return modelMapper.map(productDto, ProductEntity.class);
    }
}
