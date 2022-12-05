package hn.com.tigo.remision.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class MotoristModel {

    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String lastName;
    @NonNull
    private String licenseNumber;
    @NonNull
    private Long idTransportAgency;
    private String transportAgencyName;
    @NonNull
    private String rtn;
    @NonNull
    private String createdBy;
    private String createdAt;
    private String modifiedBy;
    private String modifiedAt;


}
