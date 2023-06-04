package com.learn.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meiguangya
 * @date 2023/4/4 11:18 上午
 * @description TODO
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addProperty(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValueByName(String name) {
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(name)) {
                return propertyValue;
            }
        }
        return null;
    }


}
