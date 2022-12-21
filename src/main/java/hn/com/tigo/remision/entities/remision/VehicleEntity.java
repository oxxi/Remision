package hn.com.tigo.remision.entities.remision;


import hn.com.tigo.remision.models.VehicleModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "VEHICULO")
@Getter
@Setter
@ToString
public class VehicleEntity implements Serializable {

    @Id
    @Column(name = "ID",insertable = false,updatable = false)
    private Long id;

    @Column(name = "PLACA", length = 10, nullable = false)
    private String licensePlate;

    @Column(name = "ID_TIPO_VEHICULO", nullable = false)
    private Long idVehicleType;

    @Column(name = "ID_AGENCIA_TRANSPORTE",nullable = false)
    private Long idTransportAgency;

    @Column(name = "MARCA")
    private String brand;

    @Column(name = "MODELO")
    private String model;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "AZO")
    private String year;

    @Column(name = "USUARIO_CREA", length = 50)
    private String createdBy;

    @Column(name = "FECHA_CREA")
    private LocalDateTime createdAt;

    @Column(name = "USUARIO_EDITA",length = 50)
    private String modifiedBy;

    @Column(name = "FECHA_EDITA")
    private LocalDateTime modifiedAt;

    @Column(name = "ESTADO", length = 1)
    private String status;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = VehicleTypeEntity.class,  optional = false)
    @JoinColumn(name = "ID_TIPO_VEHICULO",referencedColumnName = "ID", nullable = false, updatable = false, insertable = false)
    private VehicleTypeEntity vehicleTypeEntity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TransportAgencyEntity.class,  optional = false)
    @JoinColumn(name = "ID_AGENCIA_TRANSPORTE",referencedColumnName = "id",nullable = false, updatable = false, insertable = false)
    private TransportAgencyEntity transportAgencyEntity;



    public VehicleModel entityToModel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setId(this.getId());
        vehicleModel.setModel(this.getModel());
        vehicleModel.setLicensePlate(this.getLicensePlate());
        vehicleModel.setBrand(this.getBrand());
        vehicleModel.setYear(this.getYear());
        vehicleModel.setAgencyName(this.getTransportAgencyEntity().getName());
        vehicleModel.setCreatedBy(this.getCreatedBy());
        vehicleModel.setModifiedBy(this.getModifiedBy());
        vehicleModel.setColor(this.getColor());
        vehicleModel.setIdAgency(this.getIdTransportAgency());
        vehicleModel.setVehicleType(this.getIdVehicleType());
        vehicleModel.setTransportAgency(this.getTransportAgencyEntity().entityToModel());
        vehicleModel.setVehicleTypes(this.getVehicleTypeEntity().entityToModel());
        vehicleModel.setCreatedAt(this.getCreatedAt());
        vehicleModel.setModifiedAt(this.getModifiedAt());
        vehicleModel.setStatus(this.getStatus());
        try
        {
            vehicleModel.setCreatedAtString(this.getCreatedAt() == null? null: this.getCreatedAt().format(formatter));
            vehicleModel.setModifiedAtString(this.getModifiedAt() == null? null : this.getModifiedAt().format(formatter));
        }catch (Exception e){

            vehicleModel.setCreatedAtString(this.getCreatedAt() == null? null: this.getCreatedAt().format(formatterDate));
            vehicleModel.setModifiedAtString(this.getModifiedAt() == null? null : this.getModifiedAt().format(formatterDate));
        }

        return  vehicleModel;
    }
}
