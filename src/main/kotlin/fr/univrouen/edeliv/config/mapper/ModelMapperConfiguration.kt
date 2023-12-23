package fr.univrouen.edeliv.config.mapper

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * Configure the ModelMapper instance.
 */
@Configuration
class ModelMapperConfiguration {

    @Bean
    fun modelMapper(): ModelMapper {
        val mapper =  ModelMapper();

        return mapper
    }

}