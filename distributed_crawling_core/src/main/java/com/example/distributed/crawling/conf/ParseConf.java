package com.example.distributed.crawling.conf;

import org.apache.commons.configuration2.*;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.builder.FileBasedBuilderProperties;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 本类是解析配置文件类
 */
public class ParseConf {
    private static final Logger log = LoggerFactory.getLogger(ParseConf.class);

    private static final Configurations configurations = new Configurations();

    /**
     * 根据配置文件的全路径名来解析文件
     * @param
     */
    public static Object parseFile(String filePath){
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));
        if(filePath.endsWith(".properties")){
            log.info("解析的文件是：" + fileName + ".properties");
            try {
                PropertiesConfiguration propertiesConfiguration = configurations.properties(filePath);
                return propertiesConfiguration;
            } catch (ConfigurationException e) {
                log.info(e.getClass().getSimpleName() + ", 异常的原因：" + e.getMessage());
            }
        }else if (filePath.endsWith("xml")){
            log.info("解析的文件是：" + fileName + ".xml");
            try {
                XMLConfiguration xmlConfiguration = configurations.xml(filePath);
                return xmlConfiguration;
            } catch (ConfigurationException e) {
                log.info(e.getClass().getSimpleName() + ", 异常的原因：" + e.getMessage());
            }
        }else if (filePath.endsWith("yaml")){
            log.info("解析的文件是：" + fileName + ".yaml");
            YAMLConfiguration yamlConfiguration = new YAMLConfiguration();
            try {
                yamlConfiguration.read(new FileReader(filePath));
                return yamlConfiguration;
            } catch (ConfigurationException | FileNotFoundException e) {
                log.info(e.getClass().getSimpleName() + ", 异常的原因：" + e.getMessage());
            }
        }else if (filePath.endsWith("")){
            fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            log.info("解析的文件是：" + fileName);
            //BufferedReader reader = new BufferedReader(new FileReader(filePath));
            //reader.readLine();

        }
        return null;
    }

    public static void main(String[] args) {
        ParseConf.parseFile("conf.properties");
    }
}
