package hn.com.tigo.remision.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GeneralParameter {

    @NonNull
    private String name;
    @NonNull
    private String value;
    @NonNull
    private String description;
    @NonNull
    private String modifiedBy;
    private String modifiedAt;

    private String createdBy;

}
