package uz.pdp.appshortlink.entity.template;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@MappedSuperclass
public abstract class AbsUserAudit {
    @CreatedBy
    @Column(updatable = false)
    private Integer createById;

    @LastModifiedBy
    private Integer updateById;
}
