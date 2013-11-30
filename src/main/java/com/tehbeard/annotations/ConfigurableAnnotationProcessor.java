package com.tehbeard.annotations;

import com.tehbeard.beardach.annotations.Configurable;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.tools.StandardLocation;
import javax.tools.Diagnostic.Kind;




@SuppressWarnings("restriction")
@SupportedAnnotationTypes("com.tehbeard.beardach.annotations.Configurable")
public class ConfigurableAnnotationProcessor extends AbstractProcessor {
	
	private Writer classOut = null;
	
	public void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		
		try {
			classOut = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, "", "components.txt", (Element[])null).openWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		Set<? extends Element> eles = roundEnv.getElementsAnnotatedWith(Configurable.class);
		for(Element ele  : eles){
		    
		    if(!Utils.typeImplements(processingEnv,ele.asType(),"com.tehbeard.beardach.datasource.configurable.IConfigurable")){
		        processingEnv.getMessager().printMessage(Kind.NOTE,"Class " + ele.asType() + " does not implement IConfigurable");
		        throw new RuntimeException("Class " + ele.asType() + " does not implement IConfigurable");
		    }
		   
			Configurable c = ele.getAnnotation(Configurable.class);
                        if(c == null){
                            processingEnv.getMessager().printMessage(Kind.ERROR,"Failure" + ele.getSimpleName() +"@" + getPackage(ele) + " has a null Configurable tag error!");
                        }
                        
			processingEnv.getMessager().printMessage(Kind.NOTE, "Found configurable: " + c.tag());
                        processingEnv.getMessager().printMessage(Kind.NOTE, "on " + ele.getSimpleName() + " @ " + getPackage(ele));
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

}