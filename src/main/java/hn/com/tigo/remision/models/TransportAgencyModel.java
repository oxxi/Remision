package hn.com.tigo.remision.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransportAgencyModel {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String rtn;
    @NotNull
    private String address;
    @NotNull
    private String phoneNumber;
    private String createdBy;
    private LocalDateTime createdAt;
    private String modifiedBy;
    private LocalDateTime modifiedAt;

    private String status;
}
