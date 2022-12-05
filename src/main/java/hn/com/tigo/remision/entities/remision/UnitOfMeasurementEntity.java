package hn.com.tigo.remision.entities.remision;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @Column(name = "ESTADO")
    private Character estado;
}
