package uz.pdp.appshortlink.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileErrorDTO {
    private String fieldName;
    private String errorMessage;
}
