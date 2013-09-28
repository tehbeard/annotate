package com.tehbeard.annotations;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.*;
import javax.lang.model.element.*;
import javax.tools.StandardLocation;
import javax.tools.Diagnostic.Kind;

import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription;



@SuppressWarnings("restriction")
@SupportedAnnotationTypes("me.tehbeard.BeardAch.dataSource.json.help.ComponentHelpDescription")
public class BeardAchHelpProcessor extends AbstractProcessor {
	

	public void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		Set<? extends Element> eles = roundEnv.getElementsAnnotatedWith(ComponentHelpDescription.class);
		for(Element ele  : eles){
		    ComponentHelpDescription c = ele.getAnnotation(ComponentHelpDescription.class);
			processingEnv.getMessager().printMessage(Kind.NOTE, "Creating help file for " + getPackage(ele));
			
			Writer output = null;
			try {
				 output = openFile("editor/help/" + c.type().toString().toLowerCase() + "/" + ele.getSimpleName() + ".txt");
				 output.write("Name: " + c.name());
				 output.write("\n");
				 output.write("Description:\n");
				 output.write(c.description());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
			    try {
                    output.close();
                } catch (IOException e) {
                }
			}
		}
		 
		return false;
	}
	
	
	private String getPackage(Element ele){
		return ((PackageElement)ele.getEnclosingElement()).getQualifiedName() + "." + ele.getSimpleName();
	}
	
	private Writer openFile(String fileName) throws IOException{
	    return processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT,"",fileName).openWriter();
	}

}