package com.nordgeo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import javax.servlet.ServletContext;
import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {


    @Bean
    public UrlBasedViewResolver viewResolver() {
        UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
        tilesViewResolver.setViewClass(TilesView.class);


        tilesViewResolver.setContentType("text/html;charset=UTF-8");

        return tilesViewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer(ServletContext servletContext) {
        TilesConfigurer tiles = new TilesConfigurer();
        tiles.setDefinitions("WEB-INF/tiles-def.xml");
        tiles.setServletContext(servletContext);
        return tiles;

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("/resources/", "/WEB_INF");
    }

    /**
     * Make sure dates are serialised in
     * ISO-8601 format instead as timestamps
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonMessageConverter = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = jsonMessageConverter.getObjectMapper();
                objectMapper.disable(
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
                );
                break;
            }
        }
    }
}


