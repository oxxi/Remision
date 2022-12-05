package hn.com.tigo.remision.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UnitOfMeasurementModel {

    private Long id;
    @NotNull
    @Size(min = 1, max = 50, message = "size must be between 1 and 50")
    private String name;

    @NotNull
    @Size(min = 1, max = 20, message = "size must be between 1 and 20")
    private String unitScalar;

    private String createBy;

    private LocalDateTime createAt;

    private String modifiedBy;

    private LocalDateTime  modifiedAt;

    @NotNull
    @Size(min = 1, max = 1, message = "size must be 1")
    private String status;

}
