package com.aircjm.framework.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisConfig.
 *
 * @author johnniang
 */
@Configuration
@Slf4j
@MapperScan("com.aircjm.project.*.mapper")
public class MyBatisConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}


//{
//    @Autowired
//    private Environment env;
//
//    static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
//
//    public static String setTypeAliasesPackage(String typeAliasesPackage)
//    {
//        ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
//        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
//        List<String> allResult = new ArrayList<String>();
//        try
//        {
//            for (String aliasesPackage : typeAliasesPackage.split(","))
//            {
//                List<String> result = new ArrayList<String>();
//                aliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
//                        + ClassUtils.convertClassNameToResourcePath(aliasesPackage.trim()) + "/" + DEFAULT_RESOURCE_PATTERN;
//                Resource[] resources = resolver.getResources(aliasesPackage);
//                if (resources != null && resources.length > 0)
//                {
//                    MetadataReader metadataReader = null;
//                    for (Resource resource : resources)
//                    {
//                        if (resource.isReadable())
//                        {
//                            metadataReader = metadataReaderFactory.getMetadataReader(resource);
//                            try
//                            {
//                                result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
//                            }
//                            catch (ClassNotFoundException e)
//                            {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//                if (result.size() > 0)
//                {
//                    HashSet<String> hashResult = new HashSet<String>(result);
//                    allResult.addAll(hashResult);
//                }
//            }
//            if (allResult.size() > 0)
//            {
//                typeAliasesPackage = String.join(",", (String[]) allResult.toArray(new String[0]));
//            }
//            else
//            {
//                throw new RuntimeException("mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:" + typeAliasesPackage + "未找到任何包");
//            }
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        return typeAliasesPackage;
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception
//    {
//        String typeAliasesPackage = env.getProperty("mybatis.typeAliasesPackage");
//        String mapperLocations = env.getProperty("mybatis.mapperLocations");
//        String configLocation = env.getProperty("mybatis.configLocation");
//        typeAliasesPackage = setTypeAliasesPackage(typeAliasesPackage);
//        VFS.addImplClass(SpringBootVFS.class);
//
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
//        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
//        return sessionFactory.getObject();
//    }
//}