package uz.pdp.appshortlink.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import uz.pdp.appshortlink.entity.Link;
import uz.pdp.appshortlink.mapper.LinkMapper;
import uz.pdp.appshortlink.payload.LinkCrudDTO;
import uz.pdp.appshortlink.payload.LinkDTO;
import uz.pdp.appshortlink.repository.LinkRepository;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;
    private final Random random;
    private final String LETTER = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private final String DIGITS = "0123456789";


    @Override
    public LinkDTO create(LinkCrudDTO crudDTO) {
        Link link = linkMapper.toEntity(crudDTO);
        link.setShortUrl(generateShortLink());
        linkRepository.save(link);
        return linkMapper.toDTO(link);
    }

    @Override
    @CachePut(cacheNames = "url", key = "#id")
    public LinkDTO update(Integer id, LinkCrudDTO crudDTO) {
        Link link = linkRepository.findById(id).orElseThrow();
        linkMapper.updateEntity(link, crudDTO);
        linkRepository.save(link);
        return linkMapper.toDTO(link);
    }

    @Override
    @Cacheable(cacheNames = "url", key = "#id")
    public LinkDTO read(Integer id) {
        return linkMapper.toDTO(linkRepository.findById(id).orElseThrow());
    }

    @Override
    @Cacheable(cacheNames = "url", key = "#root")
    public List<LinkDTO> read() {
        return linkMapper.toDTO(linkRepository.findAll());
    }

    @Override
    @Cacheable(cacheNames = "url", key = "#shortUrl")
    public void sendRedirect(HttpServletResponse resp, String shortUrl) {
        try {
            Link link = linkRepository.findByShortUrl(shortUrl).orElseThrow();
            resp.sendRedirect(link.getDestinationUrl());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @CacheEvict(cacheNames = "url", key = "#id")
    public void delete(Integer id) {
        linkRepository.deleteById(id);
    }

//    @Scheduled(fixedRate = 20, timeUnit = TimeUnit.MINUTES)
//    @CacheEvict(cacheNames = "url", allEntries = true)
//    public void deleteAllCache() {
//
//    }

    private String generateShortLink() {
        StringBuilder sb = new StringBuilder();
        int digitCount = 0;
        while (sb.length() != 7) {
            boolean isDigit = random.nextBoolean();
            if (digitCount == 2) isDigit = false;

            if (isDigit) {
                sb.append(DIGITS.charAt(random.nextInt(10)));
                digitCount++;
            } else sb.append(LETTER.charAt(random.nextInt(52)));
        }

        if (linkRepository.existsByShortUrl(sb.toString())) {
            return generateShortLink();
        }
        return sb.toString();
    }
}
