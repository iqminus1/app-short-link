package uz.pdp.appshortlink.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appshortlink.payload.LinkCrudDTO;
import uz.pdp.appshortlink.payload.LinkDTO;
import uz.pdp.appshortlink.service.LinkService;
import uz.pdp.appshortlink.utils.AppConstants;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(AppConstants.BASE_PATH_V1 + "/link")
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;

    @PostMapping
    public LinkDTO create(@RequestBody LinkCrudDTO linkCrudDTO) {
        return linkService.create(linkCrudDTO);
    }

    @GetMapping("/read")
    public List<LinkDTO> read() {
        return linkService.read();
    }


    @GetMapping("/read/{id}")
    public LinkDTO read(@PathVariable Integer id) {
        return linkService.read(id);
    }

    @PutMapping("/{id}")
    public LinkDTO update(@PathVariable Integer id, @RequestBody LinkCrudDTO crudDTO) {
        return linkService.update(id, crudDTO);
    }

    @GetMapping("/{shortUrl}")
    public void sendRedirect(@PathVariable String shortUrl, HttpServletResponse resp) {
        linkService.sendRedirect(resp, shortUrl);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        linkService.delete(id);
    }
}
