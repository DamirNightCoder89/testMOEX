package com.damirkinapp.histroyservice.util;

import com.damirkinapp.histroyservice.model.HistShare;
import com.damirkinapp.histroyservice.model.HistShares;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CustomSharesDeserializer extends StdDeserializer<HistShares> {
    public CustomSharesDeserializer() {
        this(null);
    }

    public CustomSharesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public HistShares deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<HistShare> histShareList = new ArrayList<>();
        JsonNode nextElement;
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        System.out.println(node.toString());

        System.out.println("this custom deserialize work");

        Iterator<JsonNode> rootElements = node.elements();
        while (rootElements.hasNext()) {
            nextElement = rootElements.next();
            if(nextElement.has("history")) {
                histShareList = StreamSupport.stream(nextElement.path("history").spliterator(), false).map(a -> {
                    return new HistShare(a.get("BOARDID").asText(),
                            a.get("SECID").asText(), a.get("TRADEDATE").asText(),
                            a.get("VALUE").asDouble(), a.get("OPEN").asDouble(),
                            a.get("LOW").asDouble(), a.get("HIGH").asDouble());
                }).collect(Collectors.toList());
            }
        }
        return histShareList.isEmpty() ? null : new HistShares(histShareList);
    }
}
