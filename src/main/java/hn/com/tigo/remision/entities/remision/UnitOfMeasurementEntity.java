package hn.com.tigo.remision.entities.remision;


import hn.com.tigo.remision.models.UnitOfMeasurementModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "UNIDAD_MEDIDA")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UnitOfMeasurementEntity implements Serializable {

    @Id
    @Column(name = "ID",insertable = false,updatable = false)
    private Long id;

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name = "UNIDAD_ESCALAR", length = 20)
    private String unidadEscalar;

    @Column(name = "USUARIO_CREA", length = 50)
    private String usuarioCrea;

    @Column(name = "FECHA_CREA")
    private LocalDateTime fechaCreado;

    @Column(name = "USUARIO_EDITA", length = 50)
    private String usuarioEdita;

    @Column(name = "FECHA_EDITA")
    private LocalDateTime fechaEdita;

    @Column(name = "ESTADO",length = 1)
    private String estado;


    public UnitOfMeasurementModel entityToModel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        UnitOfMeasurementModel model = new UnitOfMeasurementModel();
        model.setId(this.getId());
        model.setName(this.getNombre());
        model.setStatus(this.getEstado());
        model.setUnitScalar(this.getUnidadEscalar());
        model.setCreatedBy(this.getUsuarioCrea());
        model.setModifiedBy(this.getUsuarioEdita());
        model.setCreatedAt(this.getFechaCreado());
        model.setCreatedAtString(this.getFechaCreado().format(formatter));
        model.setModifiedAt(this.getFechaEdita());
        model.setModifiedAtString(this.getFechaEdita()== null ? null : this.getFechaEdita().format(formatter));
        return model;
    }


}
