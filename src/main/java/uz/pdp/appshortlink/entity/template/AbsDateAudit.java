package uz.pdp.appshortlink.entity.template;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class AbsDateAudit extends AbsUserAudit {
    @CreatedDate
    @Column(updatable = false)
    private Timestamp createAt;

    @LastModifiedDate
    private Timestamp updateAt;
}
