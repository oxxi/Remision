package hn.com.tigo.remision.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseManagerModel {


    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String dni;

    private String createdBy;

    private LocalDateTime createdAt;

    private String createdAtString;

    private String modifiedBy;

    private LocalDateTime modifiedAt;

    private String modifiedAtString;

    @NotNull
    @Size(min = 1, max = 1)
    private String status;


}
