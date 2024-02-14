package ss.mod.demo.api.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Common response format.
 * PageResponse Used for any paginated response.
 *
 * @param <T> - Indicates any type of response
 * @author Sunil Kanzar
 * @since 29th of Dec 2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BodyResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 7137695452659404087L;

    private String message;
    private T body;

    public static <T> BodyResponse<T> of(String message, T body) {
        return new BodyResponse<>(message, body);
    }
}