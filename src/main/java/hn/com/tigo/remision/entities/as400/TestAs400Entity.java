package hn.com.tigo.remision.entities.as400;


import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
public class TestAs400Entity implements Serializable {
    private static final long serialVersionUID = 1L;


    @Column(name = "COL1")
    private BigDecimal column1;

    @Column(name = "COL2")
    private String column2;

    @Column(name = "COL3")
    private String column3;
}
