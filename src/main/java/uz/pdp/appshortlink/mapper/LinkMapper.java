package uz.pdp.appshortlink.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import uz.pdp.appshortlink.entity.Link;
import uz.pdp.appshortlink.payload.LinkCrudDTO;
import uz.pdp.appshortlink.payload.LinkDTO;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LinkMapper {
    Link toEntity(LinkCrudDTO linkCrudDTO);
    LinkDTO toDTO(Link link);

    void updateEntity(@MappingTarget Link link, LinkCrudDTO crudDTO);

    List<LinkDTO> toDTO(List<Link> links);
}
