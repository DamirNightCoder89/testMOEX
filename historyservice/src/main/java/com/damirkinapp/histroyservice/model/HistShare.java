package com.damirkinapp.histroyservice.model;

import com.damirkinapp.histroyservice.util.CustomSharesDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonDeserialize(using = CustomSharesDeserializer.class)
public class HistShare implements Serializable {

    private static final long serialVersionUID = 1L;

    private String boardId;
    private String ticker;
    private String tradeDte;
    private Double value;
    private Double open;
    private Double low;
    private Double high;

}
