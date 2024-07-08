package uz.pdp.appshortlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appshortlink.entity.Link;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {
    boolean existsByShortUrl(String string);

    Optional<Link> findByShortUrl(String shortUrl);
}