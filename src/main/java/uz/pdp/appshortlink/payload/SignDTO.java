package uz.pdp.appshortlink.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignDTO {
    private String email;
    private String password;
}
