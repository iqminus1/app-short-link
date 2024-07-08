package uz.pdp.appshortlink.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.appshortlink.entity.template.AbsIntEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Code extends AbsIntEntity {
    private Integer userId;
    private Integer code;

}
