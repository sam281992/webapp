import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
 
import java.io.File;
import java.io.IOException;
 
public class CSV2Arff {
  /**
   * takes 2 arguments:
   * - CSV input file
   * - ARFF output file
 * @throws IOException 
   */
  public ArffSaver CSV2Arff1(File selectedFile) throws IOException {
    // load CSV
    CSVLoader loader = new CSVLoader();
    loader.setSource(selectedFile);
    Instances data = loader.getDataSet();
 
    // save ARFF
    ArffSaver saver = new ArffSaver();
    saver.setInstances(data);
    saver.setFile(new File("/Users/anmoljain/Desktop/TeamProject/JavaProj 3.5/src/regressionTrainFinal.arff"));
    saver.setDestination(new File("/Users/anmoljain/Desktop/TeamProject/JavaProj 3.5/src/regressionTrainFinal.arff"));
   return saver;
    //saver.writeBatch();
  }
}