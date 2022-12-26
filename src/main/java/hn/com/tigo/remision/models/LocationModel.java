package hn.com.tigo.remision.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationModel {

    private Long id;
    @NotNull
    private int shortCode;
    @NotNull
    @Size(min = 1, max = 100, message = "size must be between 1 and 100")
    private String fullAddress;

    private String createdBy;
    private String createdAt;
    private String modifiedBy;
    private String modifiedAt;
    private String status;

}
