package com.tehbeard.twobyfour;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.StandardLocation;

import com.tehbeard.twobyfour.annotations.PlankLabel;


@SuppressWarnings("restriction")
@SupportedAnnotationTypes("com.tehbeard.twobyfour.annotations.PlankLabel")
public class PlankProcessor extends AbstractProcessor {
	
	
	private Writer planks = null;
	
	public PlankProcessor(){
		super();
		
		try {
			planks = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT , "", "planks.txt", (Element[])null).openWriter();
		} catch (IOException e) {
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
			//planks.write(ele.getSimpleName());
			
		}
		 
		return false;
	}

}
