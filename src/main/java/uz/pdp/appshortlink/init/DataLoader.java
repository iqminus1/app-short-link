package uz.pdp.appshortlink.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.appshortlink.entity.Role;
import uz.pdp.appshortlink.enums.PermissionEnum;
import uz.pdp.appshortlink.enums.RoleTypeEnum;
import uz.pdp.appshortlink.repository.RoleRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        checkUserRole();

        checkAdminRole();
    }

    private void checkAdminRole() {
        Optional<Role> byType = roleRepository.findByType(RoleTypeEnum.ADMIN);
        if (byType.isPresent()) {
            return;
        }

        Role role = new Role("Admin",
                "Admin",
                RoleTypeEnum.ADMIN,
                PermissionEnum.permissionUser());

        roleRepository.save(role);
    }

    private void checkUserRole() {
        Optional<Role> byType = roleRepository.findByType(RoleTypeEnum.USER);
        if (byType.isPresent()) {
            return;
        }

        Role role = new Role("User",
                "User",
                RoleTypeEnum.USER,
                PermissionEnum.permissionUser());

        roleRepository.save(role);
    }


}
