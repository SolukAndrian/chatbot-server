package com.epam.chatbotserver.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.context.annotation.Bean;

/**
 * Created by Victor on 05.07.2018.
 */
public class ObjMapperFactory {


    /**
     * Method returns new object mapper
     * "@Bean" - for  dependency injection see http://www.baeldung.com/inversion-control-and-dependency-injection-in-spring
     * for details
     * @return
     */
    @Bean
    public static ObjectMapper getMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }


}
