package uz.pdp.appshortlink.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appshortlink.enums.PermissionEnum;
import uz.pdp.appshortlink.enums.RoleTypeEnum;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link uz.pdp.appshortlink.entity.Role}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private RoleTypeEnum type;
    private List<PermissionEnum> permissions;
}