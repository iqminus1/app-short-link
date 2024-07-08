package uz.pdp.appshortlink.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResultDTO<T> {
    private boolean success;
    private T data;
    private String errorMessage;
    private List<FileErrorDTO> fieldErrors;

    public static <T> ApiResultDTO<T> success(T data) {
        ApiResultDTO<T> objectApiResultDTO = new ApiResultDTO<>();
        objectApiResultDTO.setSuccess(true);
        objectApiResultDTO.setData(data);
        return objectApiResultDTO;
    }

    public static ApiResultDTO<?> error(List<FileErrorDTO> fieldErrors) {
        ApiResultDTO<String> apiResultDTO = new ApiResultDTO<>();
        apiResultDTO.setFieldErrors(fieldErrors);
        return apiResultDTO;
    }

    public static ApiResultDTO<?> error(String errorMessage) {
        ApiResultDTO<String> apiResultDTO = new ApiResultDTO<>();
        apiResultDTO.setErrorMessage(errorMessage);
        return apiResultDTO;
    }
}
