package hn.com.tigo.remision.models;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UnitOfMeasurementModel {

    private Long id;
    @NotNull
    @Size(min = 1, max = 50, message = "size must be between 1 and 50")
    private String name;

    @NotNull
    @Size(min = 1, max = 20, message = "size must be between 1 and 20")
    private String unitScalar;

    private String createdBy;

    private LocalDateTime createdAt;

    private String createdAtString;

    private String modifiedBy;

    private LocalDateTime  modifiedAt;
    private String  modifiedAtString;

    @NotNull
    @Size(min = 1, max = 1, message = "size must be 1")
    private String status;

}
