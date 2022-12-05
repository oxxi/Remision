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

    @Column(name = "PLACA", length = 10, updatable = false)
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        VehicleModel model = new VehicleModel();
        model.setId(this.getId());
        model.setModel(this.getModel());
        model.setLicensePlate(this.getLicensePlate());
        model.setBrand(this.getBrand());
        model.setYear(this.getYear());
        model.setAgencyName(this.getTransportAgencyEntity().getName());
        model.setColor(this.getColor());
        model.setIdAgency(this.getIdTransportAgency());
        model.setVehicleType(this.getIdVehicleType());
        model.setTransportAgency(this.getTransportAgencyEntity().entityToModel());
        model.setVehicleTypes(this.getVehicleTypeEntity().entityToModel());
        model.setCreatedAt(this.getCreatedAt());
        model.setModifiedAt(this.getModifiedAt());
        model.setStatus(this.getStatus());
        model.setCreatedAtString(this.getCreatedAt() == null? null: this.getCreatedAt().format(formatter));
        model.setModifiedAtString(this.getModifiedAt() == null? null : this.getModifiedAt().format(formatter));

        return  model;
    }
}
