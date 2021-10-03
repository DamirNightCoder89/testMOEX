package com.damirkinapp.histroyservice.model;

import com.damirkinapp.histroyservice.util.CustomSharesDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = CustomSharesDeserializer.class)
public class HistShares implements Serializable {
    private List<HistShare> data;
}
