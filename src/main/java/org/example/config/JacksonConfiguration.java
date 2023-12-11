package org.example.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import java.io.IOException;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * jackson null value convert
 * @author jingyang
 * @date 2023.12.11
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.setSerializerProvider(new CustomSerializerProvider());
        return objectMapper;
    }


    // provider null strategy
    public static class CustomSerializerProvider extends DefaultSerializerProvider {

        private static final JsonSerializer<Object> NUMBER_EMPTY = new JsonSerializer<>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeNumber(0);
            }
        };

        private static final JsonSerializer<Object> STRING_EMPTY = new JsonSerializer<>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString("");
            }
        };

        private static final JsonSerializer<Object> OBJECT_EMPTY = new JsonSerializer<>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeEndObject();
            }
        };

        private static final JsonSerializer<Object> LIST_EMPTY = new JsonSerializer<>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeEndArray();
            }
        };

        private static final JsonSerializer<Object> BOOL_EMPTY = new JsonSerializer<>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeBoolean(false);
            }
        };

        // fill with different empty value depend on type
        @Override
        public JsonSerializer<Object> findNullValueSerializer(BeanProperty property) throws JsonMappingException {
            Class<?> type = property.getType().getRawClass();
            if (type.equals(Integer.class) || type.equals(Byte.class) || type.equals(Short.class) || type.equals(Long.class)
              || type.equals(Double.class) || type.equals(Float.class)) {
                return NUMBER_EMPTY;
            } else if (type.equals(String.class)) {
                return STRING_EMPTY;
            } else if (type.equals(Boolean.class)) {
                return BOOL_EMPTY;
            } else if (type.equals(List.class) || type.getName().equals("[Ljava.lang.Object;")) {
                return LIST_EMPTY;
            } else {
                return OBJECT_EMPTY;
            }
        }

        public CustomSerializerProvider() {
            super();
        }

        public CustomSerializerProvider(SerializerProvider provider, SerializationConfig config, SerializerFactory jsf) {
            super(provider, config, jsf);
        }

        @Override
        public DefaultSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
            return new CustomSerializerProvider(this, config, jsf);
        }
    }
}
