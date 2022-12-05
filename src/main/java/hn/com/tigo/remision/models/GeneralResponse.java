package hn.com.tigo.remision.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class GeneralResponse {
    private Long code;
    private String description;
    private Object data;
    private List<GeneralError> errors = new ArrayList<>();

}
/*
* for error, set code with -1
* for success, set code with 1
* */