package es.sgie.back.controller.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoErrorBody {
    private String message;
    private Exception exception;
}
