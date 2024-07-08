package uz.pdp.appshortlink.service;

import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.appshortlink.payload.LinkCrudDTO;
import uz.pdp.appshortlink.payload.LinkDTO;

import java.util.List;

public interface LinkService {
    LinkDTO create(LinkCrudDTO crudDTO);

    LinkDTO update(Integer id, LinkCrudDTO crudDTO);

    LinkDTO read(Integer id);

    List<LinkDTO> read();

    void sendRedirect(HttpServletResponse resp, String shortUrl);

    void delete(Integer id);
}
