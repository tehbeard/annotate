package com.tehbeard.annotations;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.tools.StandardLocation;
import javax.tools.Diagnostic.Kind;

import com.tehbeard.twobyfour.annotations.PlankLabel;

import me.tehbeard.BeardAch.dataSource.configurable.Configurable;


@SuppressWarnings("restriction")
@SupportedAnnotationTypes("me.tehbeard.BeardAch.dataSource.configurable.Configurable")
public class TwoByFourAnnotationProcessor extends AbstractProcessor {
	
	private Writer classOut = null;
	
	public void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		
		try {
			classOut = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, "", "planks.txt", null).openWriter();
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
			Configurable c = ele.getAnnotation(Configurable.class);

			processingEnv.getMessager().printMessage(Kind.NOTE, "Found plank: " + c.tag() + " on " + ele.getSimpleName() + " @ " + getPackage(ele));
			try {
				classOut.write(getPackage(ele) + "\n");
				classOut.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
		return false;
	}
	
	
	private String getPackage(Element ele){
		return ((PackageElement)ele.getEnclosingElement()).getQualifiedName() + "." + ele.getSimpleName();
	}
	
	
	public static void main(String[] args){
	    Object o =new Object(){
            
           public String toString(){
               return "hello World";
           }
        };
	    System.out.println((String)o);
	}

}