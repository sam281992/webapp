

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class Instance1 {

	 Instances       instTrain;
	 Instances		 instTest;
     Instances       data;
     Remove          remove;
	
	public Instances performLinearRegression(ArffSaver arffFormatter) throws Exception{
		
		instTrain = arffFormatter.getInstances();
		//instTrain   = new Instances(new BufferedReader(new FileReader("/Users/anmoljain/Desktop/TeamProject/JavaProj 3.0/src/regressionTrainFinal.arff")));
        // instTest = new Instances(new BufferedReader(new FileReader("/Users/samyakmehta/Downloads/Java project documents/SamyakMockdata/test.arff")));
         remove = new Remove();
         remove.setAttributeIndices("4,24");
         remove.setInvertSelection(new Boolean("True").booleanValue());
         remove.setInputFormat(instTrain);
         data = Filter.useFilter(instTrain, remove);
		return data;
		
	}
	
	public Instances performLinearRegression(File f) throws Exception{
		
		//instTrain   = new Instances(new BufferedReader(new FileReader("/Users/samyakmehta/Downloads/Java project documents/SamyakMockdata/mockdatacombine.arff")));
        
		instTest = new Instances(new BufferedReader(new FileReader(f)));
        remove = new Remove();
        remove.setAttributeIndices("4,24");
        remove.setInvertSelection(new Boolean("True").booleanValue());
        remove.setInputFormat(instTest);
        data = Filter.useFilter(instTest, remove);
		return data;
		
	}
	
}
