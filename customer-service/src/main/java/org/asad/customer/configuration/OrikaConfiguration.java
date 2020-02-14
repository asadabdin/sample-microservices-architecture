package org.asad.customer.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.MapperFactoryBuilder;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;
import org.asad.customer.configuration.properties.OrikaProperties;
import org.asad.customer.mapping.GlobalMapper;
import org.asad.customer.mapping.ObjectMapping;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "orika.enabled", matchIfMissing = true)
public class OrikaConfiguration {


    private static final Type<ObjectMapping> OBJECT_MAPPING_INTERFACE = TypeFactory.valueOf(ObjectMapping.class);
    /**
     * The configuration properties for Orika.
     */
    private final OrikaProperties orikaProperties;
    /**
     * Every Possible mapping defined
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<List<ObjectMapping<?, ?>>> objectMappings;
    /**
     * Every Possible Custom Mapper Defined with annotation {@link GlobalMapper}
     * will only be registered
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<List<CustomMapper<?, ?>>> customMappers;
    /**
     * Every possible Object Converter if it is not available built in
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<List<Converter<?, ?>>> converters;
    /**
     * Every possible Object Filter if it is not available built in
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<List<Filter<?, ?>>> filters;

    /**
     * Creates a {@link MapperFactoryBuilder}.
     * and setting Properties.
     *
     * @return a {@link MapperFactoryBuilder}.
     */
    @Bean
    @ConditionalOnMissingBean
    public MapperFactoryBuilder orikaMapperFactoryBuilder() {
        DefaultMapperFactory.Builder orikaMapperFactoryBuilder = new DefaultMapperFactory.Builder();
        Optional.ofNullable(orikaProperties.getUseBuiltinConverters())
                .ifPresent(orikaMapperFactoryBuilder::useBuiltinConverters);
        Optional.ofNullable(orikaProperties.getUseAutoMapping())
                .ifPresent(orikaMapperFactoryBuilder::useAutoMapping);
        Optional.ofNullable(orikaProperties.getMapNulls())
                .ifPresent(orikaMapperFactoryBuilder::mapNulls);
        Optional.ofNullable(orikaProperties.getDumpStateOnException())
                .ifPresent(orikaMapperFactoryBuilder::dumpStateOnException);
        Optional.ofNullable(orikaProperties.getFavorExtension())
                .ifPresent(orikaMapperFactoryBuilder::favorExtension);
        Optional.ofNullable(orikaProperties.getCaptureFieldContext())
                .ifPresent(orikaMapperFactoryBuilder::captureFieldContext);
        log.debug("Created a MapperFactoryBuilder: [{}]", orikaMapperFactoryBuilder);
        return orikaMapperFactoryBuilder;
    }

    /**
     * Creates a {@link MapperFactory}.
     *
     * @param orikaMapperFactoryBuilder the {@link MapperFactoryBuilder}.
     * @return a {@link MapperFactory}.
     */
    @Bean
    @ConditionalOnMissingBean
    @SuppressWarnings("unchecked")
    public MapperFactory orikaMapperFactory(MapperFactoryBuilder orikaMapperFactoryBuilder) {
        MapperFactory orikaMapperFactory = orikaMapperFactoryBuilder.build();

        //Registering Object Mappings
        objectMappings
                .orElseGet(Collections::emptyList)
                .forEach(objectMapping -> {
                    ClassMapBuilder classMapBuilder = getClassMapBuilder(orikaMapperFactory, objectMapping);
                    objectMapping.classMap(classMapBuilder);
                    orikaMapperFactory.registerClassMap(classMapBuilder);
                });

        //Registering Global Object Custom Mapper
        customMappers
                .filter(customMapper -> customMapper.getClass().isAnnotationPresent(GlobalMapper.class))
                .orElseGet(Collections::emptyList)
                .forEach(orikaMapperFactory::registerMapper);

        //Registering Custom Object Converter if not supported out of the Box
        converters
                .filter(converter -> converter.getClass().isAnnotationPresent(GlobalMapper.class))
                .orElseGet(Collections::emptyList)
                .forEach(orikaMapperFactory.getConverterFactory()::registerConverter);


        log.debug("Created a MapperFactory: [{}]", orikaMapperFactory);
        return orikaMapperFactory;
    }

    /**
     * Creates a {@link MapperFacade}.
     *
     * @param orikaMapperFactory the {@link MapperFactory}.
     * @return a {@link MapperFacade}.
     */
    @Bean
    @ConditionalOnMissingBean
    public MapperFacade orikaMapperFacade(MapperFactory orikaMapperFactory) {
        MapperFacade orikaMapperFacade = orikaMapperFactory.getMapperFacade();
        log.debug("Created a MapperFacade: [{}]", orikaMapperFacade);
        return orikaMapperFacade;
    }

    /**
     * Extract the GenericType from the Implementation of {@link ObjectMapping}
     *
     * @param orikaMapperFactory the {@link MapperFactory}.
     * @param objectMapping      of {@link ObjectMapping}
     * @return object of {@link ClassMapBuilder}
     */
    private ClassMapBuilder getClassMapBuilder(MapperFactory orikaMapperFactory, ObjectMapping objectMapping) {
        Type objectMappingInterface = TypeFactory.valueOf(objectMapping.getClass()).findInterface(OBJECT_MAPPING_INTERFACE);
        return orikaMapperFactory
                .classMap(TypeFactory.valueOf(objectMappingInterface.getActualTypeArguments()[0]),
                        TypeFactory.valueOf(objectMappingInterface.getActualTypeArguments()[1]));
    }
}
