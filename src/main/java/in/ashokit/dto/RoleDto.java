package in.ashokit.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RoleDto {

    private Integer roleId;
    private String name;
}
