package in.sujeetk.jupiter.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMetaDTO {

    private int status;

    private String message;

    private boolean success = true;

    private ErrorDTO error;

    public ResponseMetaDTO(int status, String message, boolean success) {
        this.status = status;
        this.message = message;
        this.success = success;
    }

    public ResponseMetaDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseMetaDTO(int status, ErrorDTO errorDTO) {
        this.status = status;
        this.error = errorDTO;
        this.success = false;
    }
}
