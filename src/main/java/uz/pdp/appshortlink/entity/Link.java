package uz.pdp.appshortlink.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.appshortlink.entity.template.AbsIntEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@SQLRestriction("deleted=false")
@SQLDelete(sql=("update link set deleted=true where id=?"))
public class Link extends AbsIntEntity {
    @Column(columnDefinition = "text")
    private String destinationUrl;

    private String shortUrl;

    private String name;

    @Column(columnDefinition = "text")
    private String description;
}
