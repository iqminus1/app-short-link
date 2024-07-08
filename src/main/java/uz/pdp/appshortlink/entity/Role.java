package uz.pdp.appshortlink.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.type.SqlTypes;
import uz.pdp.appshortlink.entity.template.AbsIntEntity;
import uz.pdp.appshortlink.enums.PermissionEnum;
import uz.pdp.appshortlink.enums.RoleTypeEnum;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "roles")
@SQLRestriction("deleted=false")
@SQLDelete(sql = ("update roles set deleted=true where id=?"))
public class Role extends AbsIntEntity {
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    private RoleTypeEnum type;

    @Enumerated(EnumType.STRING)
//    @ElementCollection
    @JdbcTypeCode(SqlTypes.ARRAY)
    private List<PermissionEnum> permissions;
}
