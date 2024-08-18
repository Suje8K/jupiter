package in.sujeetk.jupiter.dtos;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 8965011907233699993L;

    private ResponseMetaDTO responseMeta;

    private T data;

    public ResponseDTO(int status, T data, String message) {
        this.responseMeta = new ResponseMetaDTO(status, message);
        this.data = data;
    }

    public ResponseDTO(int status, T data, String message, boolean success) {
        this.responseMeta = new ResponseMetaDTO(status, message, success);
        this.data = data;
    }

    public ResponseDTO(int status, ErrorDTO errorDTO) {
        this.responseMeta = new ResponseMetaDTO(status, errorDTO);
    }

    public String getErrorDisplay() {
        if (responseMeta == null) {
            return "";
        }

        final ErrorDTO error = responseMeta.getError();
        if (error == null || error.getMessage() == null) {
            return "";
        }

        return error.getCode() + ": " + error.getMessage();
    }
}
