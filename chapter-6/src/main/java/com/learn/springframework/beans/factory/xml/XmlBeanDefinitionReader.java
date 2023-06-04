package com.learn.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.learn.springframework.beans.BeanException;
import com.learn.springframework.beans.PropertyValue;
import com.learn.springframework.beans.factory.config.BeanDefinition;
import com.learn.springframework.beans.factory.config.BeanReference;
import com.learn.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.learn.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.learn.springframework.core.io.Resource;
import com.learn.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author meiguangya
 * @date 2023/4/10 8:29 下午
 * @description TODO
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {


    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        try {
            try (InputStream is = resource.getInputStream()) {
                doLoadBeanDefinitions(is);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeanException("读取xml文件失败", e);
        }
    }


    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeanException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanDefinitions(InputStream is) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(is);
        Element root = doc.getDocumentElement();

        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {

            // 不是element退出
            if (!(childNodes.item(i) instanceof Element)) {
                continue;
            }

            // 不是bean退出
            if (!StrUtil.equals("bean", childNodes.item(i).getNodeName())) {
                continue;
            }

            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // 获取class
            Class<?> clz = Class.forName(className);
            // 优先级 id>name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clz.getSimpleName());
            }

            // 构造BeanDefinition
            BeanDefinition beanDefinition = new BeanDefinition(clz);

            NodeList beanPropertyList = bean.getChildNodes();
            for (int j = 0; j < beanPropertyList.getLength(); j++) {

                if (!(bean.getChildNodes().item(j) instanceof Element)) {
                    continue;
                }

                if (!StrUtil.equals("property", bean.getChildNodes().item(j).getNodeName())) {
                    continue;
                }

                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String ref = property.getAttribute("ref");

                Object value;
                if (StrUtil.isNotEmpty(ref)) {
                    value = new BeanReference(ref);
                } else {
                    value = attrValue;
                }

                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            if (getBeanDefinitionRegistry().containsBeanDefinition(beanName)) {
                throw new BeanException("bean name " + beanName + "重复定义");
            }

            getBeanDefinitionRegistry().registryBeanDefinition(beanName, beanDefinition);

        }

    }

}
