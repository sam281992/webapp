

import weka.classifiers.trees.J48;
//import required classes
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;

import java.io.*;
public class Classification {
  
  public File classificationJ48(ArffSaver saver, File fileClassi) throws Exception {
	  
	  /*CSVLoader loader = new CSVLoader();
	  loader.setSource(new File("C:/Users/jay/Desktop/DATA final.csv"));
	  Instances data = loader.getDataSet();//get instances object

	  // save ARFF
	  ArffSaver saver = new ArffSaver();
	  saver.setInstances(data);//set the dataset we want to convert
	  //and save as ARFF
	  saver.setFile(new File("C:/Users/jay/Desktop/trainjay.arff"));
	  saver.writeBatch();*/
	  
	  
	  //BufferedReader br = new BufferedReader(new FileReader("/Users/anmoljain/Desktop/TeamProject/JavaProj 3.0/src/regressionTrainfinal.arff"));
	 // Instances train = new Instances(saver);
	  Instances train = saver.getInstances(); 
	  train.setClassIndex(5);
	  
	 // CSV2Arff csv = new CSV2Arff();
	 // ArffSaver sas1 = csv.CSV2Arff1(fileClassi);
		
	  BufferedReader br1 = new BufferedReader(new FileReader(fileClassi));
	  Instances test = new Instances(br1);
	  test.setClassIndex(5);
	  
	 // br.close();
	  
	  J48 tree = new J48();
	  tree.buildClassifier(train);
	  Instances labelled = new Instances(test);
	  double clsLabel;
	  for(int i = 0; i< test.numInstances(); i++)
	  {  
		 clsLabel = tree.classifyInstance(test.instance(i));
		 //System.out.println(clsLabel);
		 labelled.instance(i).setClassValue(clsLabel);
	  }
	 
	  BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/anmoljain/Desktop/TeamProject/JavaProj 3.5/src/classificationAfterJ48.arff"));
	  bw.write(labelled.toString());
	  bw.close();
	  
	  ArffLoader loader1 = new ArffLoader();
	  File f = new File("/Users/anmoljain/Desktop/TeamProject/JavaProj 3.5/src/classificationAfterJ48.arff");
	  loader1.setSource(f);
	  Instances data1 = loader1.getDataSet();//get instances object

	  // save CSV
	  CSVSaver saver1 = new CSVSaver();
	  saver1.setInstances(data1);//set the dataset we want to convert
	  //and save as CSV
	  File replacedFileClass=new File("/Users/anmoljain/Desktop/TeamProject/JavaProj 3.5/src/reproducedFileJ48.csv");
	  saver1.setFile(replacedFileClass);
	  saver1.writeBatch();
	  return replacedFileClass;
	  
	  
  }
} 