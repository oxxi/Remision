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
public class VehicleTypesModel {

    private Long id;

    @NotNull
    @Size(min = 1, max = 50, message = "size must be between 1 and 50")
    private String description;

    @NotNull
    @Size(min = 1, max = 50, message = "size must be between 1 and 50")
    private String createdBy;

    private LocalDateTime createdAt;

    private String createdAtString;

    private String modifiedBy;

    private LocalDateTime modifiedAt;

    private String modifiedAtString;

    private String status;
}
