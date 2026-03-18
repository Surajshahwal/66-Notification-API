package in.ashokit.mapper;


import in.ashokit.dto.ProductCategoryDto;
import in.ashokit.entity.ProductCategoryEntity;
import org.modelmapper.ModelMapper;

public class ProductCategoryMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProductCategoryDto toDto(ProductCategoryEntity productCategoryEntity) {
        return modelMapper.map(productCategoryEntity, ProductCategoryDto.class);
    }

    public static ProductCategoryEntity toEntity(ProductCategoryDto productCategoryDto) {
        return modelMapper.map(productCategoryDto, ProductCategoryEntity.class);
    }
}
