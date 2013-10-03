package com.tehbeard.twobyfour;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import javax.tools.Diagnostic.Kind;

import org.bukkit.configuration.serialization.SerializableAs;

import com.tehbeard.twobyfour.annotations.PlankLabel;


@SuppressWarnings("restriction")
@SupportedAnnotationTypes("com.tehbeard.twobyfour.annotations.PlankLabel")
public class PlankProcessor extends AbstractProcessor {


    private Writer planks = null;

    private Writer serializers = null;

    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        try {
            planks = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT , "", "planks.txt", (Element[])null).openWriter();
            serializers = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT , "", "serialize.txt", (Element[])null).openWriter();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
            RoundEnvironment roundEnv) {

        Set<? extends Element> eles = roundEnv.getElementsAnnotatedWith(PlankLabel.class);
        for(Element ele  : eles){
            PlankLabel c = ele.getAnnotation(PlankLabel.class);
            processingEnv.getMessager().printMessage(Kind.NOTE, "Plank found: " + c.id());
            try {
                planks.write(getPackage(ele) + "\n");
                planks.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }

        eles = roundEnv.getElementsAnnotatedWith(SerializableAs.class);
        for(Element ele  : eles){
            try {
                serializers.write(getPackage(ele) + "\n");
                serializers.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }

        return false;
    }

    private String getPackage(Element ele){
        StringBuilder sb = new StringBuilder();
        Element point = ele;
        
        while(point != null){
            sb.insert(0,"." + point.getSimpleName());
            point = point.getEnclosingElement();
            if(point instanceof PackageElement){
                sb.insert(0, ((PackageElement)point).getQualifiedName());
                point = null;
            }
        }
        return sb.toString();
    }

}
