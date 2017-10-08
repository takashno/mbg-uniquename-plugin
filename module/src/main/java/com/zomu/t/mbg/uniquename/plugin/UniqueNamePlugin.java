package com.zomu.t.mbg.uniquename.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;
import java.util.stream.Collectors;

public class UniqueNamePlugin extends PluginAdapter {

    /**  */
    private static final String uniqueNamePatternParameter = "uniqueNamePattern";

    /**
     * ユニーク名パターン.
     */
    private String uniqueNamePattern = "{schema}.{table}.{method}";


    @Override
    public boolean validate(List<String> warnings) {
        if (properties.getProperty(uniqueNamePatternParameter) != null) {
            uniqueNamePattern = properties.getProperty(uniqueNamePatternParameter);
        }
        return true;
    }


    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        for (int i = 0;i< element.getElements().size();i++) {
            Element e = element.getElements().get(i);
            System.out.println(e.getFormattedContent(0));
            if (e.getFormattedContent(0).startsWith("select")) {
                System.out.println(TextElement.class.cast(e).getContent());
                TextElement te = new TextElement(e.getFormattedContent(0).replace("select","select /* sss */ "));
                element.getElements().remove(i);
                element.getElements().set(i,te);
            }
            System.out.println("--");
        }
        return true;
    }
}