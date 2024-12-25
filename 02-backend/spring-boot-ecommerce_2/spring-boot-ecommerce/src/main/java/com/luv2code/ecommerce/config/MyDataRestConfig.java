package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import jakarta.persistence.metamodel.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {


    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theentityManager){
        entityManager = theentityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions={HttpMethod.PUT,HttpMethod.POST,HttpMethod.DELETE};
        config.getExposureConfiguration()
                .forDomainType(Product.class).withItemExposure((metadata, httpMethods)-> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata,httpMethods)-> httpMethods.disable(theUnsupportedActions));

        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class).withItemExposure((metadata, httpMethods)-> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata,httpMethods)-> httpMethods.disable(theUnsupportedActions));
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));
        //exposeIDs(config);
    }

    private void exposeIDs(RepositoryRestConfiguration config) {
        Class[] classes = entityManager.getMetamodel()
                .getEntities().stream().map(Type::getJavaType).toArray(Class[]::new);
        config.exposeIdsFor(classes);

        /*Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class> classList=new ArrayList<>();
        for (EntityType<?> entityType : entities) {
            classList.add(entityType.getJavaType());
        }
        System.out.println("classList :"+classList);
        Class[] domainTypes=classList.toArray(Class[]::new);
        System.out.println("domainTypes :"+ Arrays.toString(domainTypes));
        config.exposeIdsFor(domainTypes);*/
    }
}



