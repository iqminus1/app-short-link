package uz.pdp.appshortlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appshortlink.entity.Role;
import uz.pdp.appshortlink.enums.RoleTypeEnum;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByType(RoleTypeEnum type);
}