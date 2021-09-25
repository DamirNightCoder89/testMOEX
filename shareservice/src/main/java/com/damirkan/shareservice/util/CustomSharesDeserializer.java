package com.damirkan.shareservice.util;

import com.damirkan.shareservice.model.Share;
import com.damirkan.shareservice.model.Shares;
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

public class CustomSharesDeserializer extends StdDeserializer<Shares> {
    public CustomSharesDeserializer() {
        this(null);
    }

    public CustomSharesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Shares deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<Share> map = new ArrayList<>();
        JsonNode nextElement;
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        System.out.println(node.toString());

        System.out.println("this custom deserialize work");

        Iterator<JsonNode> rootElements = node.elements();
        while (rootElements.hasNext()) {
            nextElement = rootElements.next();
            if(nextElement.has("marketdata")) {
                System.out.println("marketdata true");
                map = StreamSupport.stream(nextElement.path("marketdata").spliterator(), false).map(a -> {
                    return new Share(a.get("BOARDID").asText(), a.get("SECID").asText(), a.get("LAST").asText());
                }).collect(Collectors.toList());
            }
        }

        return map.isEmpty() ? null : new Shares(map);
    }
}
