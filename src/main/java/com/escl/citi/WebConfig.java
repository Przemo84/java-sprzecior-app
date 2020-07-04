package com.escl.citi;

import com.escl.citi.security.AuthManager;
import com.escl.citi.validation.validator.PasswordChangeVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {


    @Autowired
    private AuthManager authManager;

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


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PasswordChangeVerifier(authManager)).addPathPatterns("/admin/**");
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    TargetAction targetAction = handlerMethod.getMethod().getAnnotation(TargetAction.class);
                    if (targetAction == null) {
                        return true;
                    }

                    request.getSession().setAttribute("redirect_url1", request.getRequestURL());
                    request.getSession().setAttribute("redirect_url12", request.getRequestURI());
                    request.getSession().setAttribute("redirect_url22", request.getQueryString());

                    HttpSession session = request.getSession();
                    session.setAttribute("attributeName", "adsas");

                }

                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws IOException {
                if (handler instanceof HandlerMethod) {
                    try {
                        String uri = request.getSession().getAttribute("redirect_url1").toString();

                        String query = "";

                        if (request.getSession().getAttribute("redirect_url22") != null) {
                            query = request.getSession().getAttribute("redirect_url22").toString();
                        }

                        MultiValueMap<String, String> params = ServletUriComponentsBuilder
                                .fromUri(URI.create(uri + "?" + query))
                                .build()
                                .getQueryParams();

                        View view = modelAndView.getView();

                        if (view instanceof RedirectView) {
                            params.forEach((key, value) -> {
                                if (value != null && !value.isEmpty()) {
                                    if (key.equals("sort") || key.equals("how") || key.equals("size") || key.equals("page")) {
                                        ((RedirectView) view).addStaticAttribute(key, value.get(0));
                                    }
                                }
                            });
                        }

                    } catch (NullPointerException e) {
                        // redirect_url1 is missing, do nothing
                    }
                }
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

            }
        }).addPathPatterns("/admin/**");
    }
}


