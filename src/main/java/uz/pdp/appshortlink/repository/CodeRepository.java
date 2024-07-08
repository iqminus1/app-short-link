package uz.pdp.appshortlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appshortlink.entity.Code;

public interface CodeRepository extends JpaRepository<Code, Integer> {
}