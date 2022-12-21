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
public class ReasonModel {

    private Long id;
    @NotNull
    private String description;
    @NotNull
    private String createdBy;

    private LocalDateTime createdAt;

    private String createdAtString;

    private String modifiedBy;

    private LocalDateTime modifiedAt;

    private String modifiedAtString;
    @NotNull
    @Size(min = 1,max = 1)
    private String status;
}
