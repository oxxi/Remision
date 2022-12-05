package hn.com.tigo.remision.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class VehicleModel {

    private Long id;
    @NotNull
    private String licensePlate;
    @NotNull
    private String brand;
    private String model;
    private String year;
    @NotNull
    private String agencyName;
    private String color;
    @NotNull
    private Long idAgency;
    @NotNull
    private Long vehicleType;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private String createdAtString;
    private LocalDateTime modifiedAt;
    private String modifiedAtString;
    private String status;
    private VehicleTypesModel vehicleTypes;
    private TransportAgencyModel transportAgency;
}
