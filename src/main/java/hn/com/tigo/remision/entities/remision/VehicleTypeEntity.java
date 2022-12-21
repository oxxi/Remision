package hn.com.tigo.remision.entities.remision;


import hn.com.tigo.remision.models.VehicleTypesModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Table(name = "TIPO_VEHICULO")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Slf4j
public class VehicleTypeEntity implements Serializable {

    @Id
    @Column(name = "ID",insertable = false,updatable = false)
    private Long id;

    @Column(name = "DESCRIPCION",length =50)
    private String description;

    @Column(name = "USUARIO_CREA", length = 50)
    private String createBy;

    @Column(name = "FECHA_CREA")
    private LocalDateTime createdAt;

    @Column(name = "USUARIO_EDITA")
    private String modifiedBy;

    @Column(name = "FECHA_EDITA")
    private LocalDateTime modifiedAt;

    @Column(name = "ESTADO", length = 1)
    private String status;


    @OneToMany(mappedBy = "vehicleTypeEntity",targetEntity = VehicleEntity.class, fetch = FetchType.EAGER)
    private Set<VehicleEntity> vehicleEntity;


    public VehicleTypesModel entityToModel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        VehicleTypesModel model = new VehicleTypesModel();
        model.setId(this.getId());
        model.setDescription(this.getDescription());
        model.setCreatedBy(this.getCreateBy());
        model.setCreatedAt(this.getCreatedAt());
        model.setModifiedBy(this.getModifiedBy());
        model.setModifiedAt( this.getModifiedAt());
        model.setStatus(this.getStatus());

        try{
            model.setModifiedAtString(this.getModifiedAt() == null ? null:this.getModifiedAt().format(formatter));
            model.setCreatedAtString(this.getCreatedAt() == null ?  null : this.getCreatedAt().format(formatter));
        }catch (Exception e){
            log.info(e.getMessage());
        }

        return model;
    }

}
