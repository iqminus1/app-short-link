package uz.pdp.appshortlink.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appshortlink.enums.PermissionEnum;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link uz.pdp.appshortlink.entity.Role}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCrudDTO implements Serializable {
    private String name;
    private String description;
    private List<PermissionEnum> permissions;
}