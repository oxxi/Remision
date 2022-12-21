package hn.com.tigo.remision.models;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModel {

    private Long id;
    @NotNull
    private String licensePlate;
    @NotNull
    private String brand;
    private String model;
    @NotNull
    private String year;
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
