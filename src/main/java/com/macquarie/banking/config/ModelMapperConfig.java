
package com.macquarie.banking.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ModelMapperConfig {
    @Primary
    @Bean("modelMapper")
    public ModelMapper ModelMapper() {

        final var mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true);
        return mapper;
    }

    @Bean("skipNullModelMapper")
    public ModelMapper skipNullModelMapper() {
        final var modelMapper = new ModelMapper();

        // Configure ModelMapper
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
