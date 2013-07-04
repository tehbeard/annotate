package com.tehbeard.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;

import org.bukkit.configuration.file.YamlConfiguration;


@SuppressWarnings("restriction")
@SupportedAnnotationTypes("com.tehbeard.utils.PluginMod")
public class PluginProcessor extends AbstractProcessor{

    private Writer yaml;
    YamlConfiguration yc = new YamlConfiguration();


    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        try {
            yaml = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT , "", "plugin.yml", (Element[])null).openWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundenv) {

        Set<? extends Element> eles = roundenv.getElementsAnnotatedWith(PluginMod.class);
        for(Element ele : eles){
            System.out.println(getFullClass(ele));

                PluginMod mod = ele.getAnnotation(PluginMod.class);

                
                yc.set("main" , getFullClass(ele));
                yc.set("name" , mod.name());
                yc.set("version" , mod.version());


        }
        
        if(roundenv.processingOver()){
            try {
                yaml.write(yc.saveToString());
                yaml.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }

        return false;
    }

    private String getFullClass(Element ele){
        return ((PackageElement)ele.getEnclosingElement()).getQualifiedName() + "." + ele.getSimpleName();
    }

}
