

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ChartFromCSV {

	static ArrayList<Integer> id = new ArrayList<Integer>();
	static ArrayList<String> edu = new ArrayList<String>();//
	static ArrayList<Integer> courseCompln = new ArrayList<Integer>();
	static ArrayList<Double> gpa = new ArrayList<Double>();
	static ArrayList<String> course_info = new ArrayList<String>();
	static ArrayList<String> pgUg = new ArrayList<String>();
	static ArrayList<String> fieldOfEdu = new ArrayList<String>();
	static ArrayList<String> countryOfBirth = new ArrayList<String>();
	static ArrayList<String> basisOfAdm = new ArrayList<String>();
	static ArrayList<Integer> age = new ArrayList<Integer>();
	static ArrayList<String> gender = new ArrayList<String>();
	static ArrayList<String> citizenship = new ArrayList<String>();
	static ArrayList<String> locOfTermRes = new ArrayList<String>();//
	static ArrayList<String> locOfRes = new ArrayList<String>();
	static ArrayList<String> typeOfAtt = new ArrayList<String>();//
	static ArrayList<String> modeOfAttdnce = new ArrayList<String>();
	static ArrayList<String> lang = new ArrayList<String>();
	static ArrayList<String> ctryOfBirth = new ArrayList<String>();
	static ArrayList<Integer> gre = new ArrayList<Integer>(); //
	static ArrayList<Integer> gmat = new ArrayList<Integer>();
	static ArrayList<Integer> sat = new ArrayList<Integer>(); //

	static ArrayList<String> equityData = new ArrayList<String>();

	static ArrayList<Integer> sal = new ArrayList<Integer>(); //
	static ArrayList<Integer> yearOfArrival = new ArrayList<Integer>();

	void loadData(File f) {

		try {

			InputStream in = new FileInputStream(f);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder out = new StringBuilder();
			String line;

			reader.readLine();
			while ((line = reader.readLine()) != null) {
				out.append(line);
				String[] arr = line.split(",");

				int i = 0;

				id.add(Integer.parseInt(arr[i]));
				edu.add(arr[i + 1]);
				courseCompln.add(Integer.parseInt(arr[i + 2]));
				gpa.add(Double.parseDouble(arr[i + 3]));
				course_info.add(arr[i + 4]);
				pgUg.add(arr[i + 5]);
				fieldOfEdu.add(arr[i + 6]);
				countryOfBirth.add(arr[i + 7]);
				basisOfAdm.add(arr[i + 8]);
				age.add(Integer.parseInt(arr[i + 9]));
				gender.add(arr[i + 10]);
				citizenship.add(arr[i + 11]);
				locOfTermRes.add(arr[i + 12]);
				locOfRes.add(arr[i + 13]);
				typeOfAtt.add(arr[i + 14]);
				modeOfAttdnce.add(arr[i + 15]);
				ctryOfBirth.add(arr[i + 16]);
				lang.add(arr[i + 17]);
				yearOfArrival.add(Integer.parseInt(arr[i + 18]));
				gre.add(Integer.parseInt(arr[i + 19]));
				gmat.add(Integer.parseInt(arr[i + 20]));
				sat.add(Integer.parseInt(arr[i + 21]));

				equityData.add(arr[i + 22]);
				sal.add(Integer.parseInt(arr[i + 23]));

			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		
	

	}

	JFreeChart pgUgBylowIncome() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		int[] equity = new int[2];
		for (int i = 0; i < id.size(); i++) {
			if ((pgUg.get(i).equals("Postgraduate"))&&(equityData.get(i).equals("low-income"))) {
				equity[0]++;
				

			} else if ((pgUg.get(i).equals("Undergraduate"))&&(equityData.get(i).equals("low-income"))) {
				equity[1]++;

			} 

		}

		dataset.setValue("Postgraduate", equity[0]);
		dataset.setValue("Undergraduate", equity[1]);
		

		JFreeChart chart = ChartFactory.createPieChart3D("PG and UG students under low-income Equity Data Category", // chart
																							// title
				dataset, // data
				true, // include legend
				true, false);
		

			return chart;
	}

	
	JFreeChart pgUgByDisability() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		int[] equity = new int[2];
		for (int i = 0; i < id.size(); i++) {
			if ((pgUg.get(i).equals("Postgraduate"))&&(equityData.get(i).equals("Disability"))) {
				equity[0]++;
				

			} else if ((pgUg.get(i).equals("Undergraduate"))&&(equityData.get(i).equals("Disability"))) {
				equity[1]++;

			} 

		}

		dataset.setValue("Postgraduate", equity[0]);
		dataset.setValue("Undergraduate", equity[1]);
		

		JFreeChart chart = ChartFactory.createPieChart3D("PG and UG students: Category: disability ", // chart
																							// title
				dataset, // data
				true, // include legend
				true, false);
		

			return chart;
	}


	JFreeChart salaryByMale() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		int[] male = new int[2];
		for (int i = 0; i < id.size(); i++) {
			if ((sal.get(i)>=75000)&&(gender.get(i).equals("Male"))) {
				male[0]++;
				

			} else if ((sal.get(i)<=75000)&&(gender.get(i).equals("Male"))) {
				male[1]++;

			} 

		}

		dataset.setValue("Salary above average", male[0]);
		dataset.setValue("Salary below average", male[1]);
		
		System.out.println(dataset);

		JFreeChart chart = ChartFactory.createPieChart3D("Salary statistics of male students  ", 
				dataset, // data
				true, // include legend
				true, false);


			return chart;
	}

	JFreeChart salaryByFemale() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		int[] female = new int[2];
		for (int i = 0; i < id.size(); i++) {
			if ((sal.get(i)>=75000)&&(gender.get(i).equals("Female"))) {
				female[0]++;
				

			} else if ((sal.get(i)<=75000)&&(gender.get(i).equals("Female"))) {
				female[1]++;

			} 

		}

		dataset.setValue("Salary above average ", female[0]);
		dataset.setValue("Salary below average", female[1]);
		

		JFreeChart chart = ChartFactory.createPieChart3D("Salary statistics of female students  ", // chart
																							// title
				dataset, // data
				true, // include legend
				true, false);
		

			return chart;
	}

	
	
	JFreeChart greByFemale() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		int totalGREScore=0;
		int greFemale=0;
		int[] female = new int[2];
		for (int i = 0; i < id.size(); i++) {
			if (gre.get(i)!=0) {
				totalGREScore+=gre.get(i);
				greFemale++;
				

			} 
			
			
			

		}

		int AverageGREScore=totalGREScore/greFemale;
		for(int i=0;i<id.size();i++)
		{
		 if ((gre.get(i)<=AverageGREScore)&&(gender.get(i).equals("Female"))) {
			female[0]++;

		} 
		 else if ((gre.get(i)>=AverageGREScore)&&(gender.get(i).equals("Female")))
		 {
			 female[1]++;
		 }
		}
		dataset.setValue("GRE below average ", female[0]);
		dataset.setValue("GRE above average", female[1]);
		

		JFreeChart chart = ChartFactory.createPieChart3D("GRE statistics of female students  ", // chart
																							// title
				dataset, // data
				true, // include legend
				true, false);
		

			return chart;
	}

	
	
	JFreeChart greByMale() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		int totalGREScore=0;
		int greMale=0;
		int[] male = new int[2];
		for (int i = 0; i < id.size(); i++) {
			if (gre.get(i)!=0) {
				totalGREScore+=gre.get(i);
				greMale++;
				

			} 
			
			
			

		}

		int AverageGREScore=totalGREScore/greMale;
		for(int i=0;i<id.size();i++)
		{
		 if ((gre.get(i)<=AverageGREScore)&&(gender.get(i).equals("Male"))) {
			male[0]++;

		} 
		 else if ((gre.get(i)>=AverageGREScore)&&(gender.get(i).equals("Male")))
		 {
			 male[1]++;
		 }
		}
		dataset.setValue("GRE below average ", male[0]);
		dataset.setValue("GRE above average", male[1]);
		

		JFreeChart chart = ChartFactory.createPieChart3D("GRE statistics of male students  ", // chart
																							// title
				dataset, // data
				true, // include legend
				true, false);
		

			return chart;
	}

	
	JFreeChart gmatByFemale() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		int totalGMATScore=0;
		int gmatFemale=0;
		int[] female = new int[2];
		for (int i = 0; i < id.size(); i++) {
			if (gmat.get(i)!=0) {
				totalGMATScore+=gmat.get(i);
				gmatFemale++;
				

			} 
			
			
			

		}

		int AverageGREScore=totalGMATScore/gmatFemale;
		for(int i=0;i<id.size();i++)
		{
		 if ((gmat.get(i)<=AverageGREScore)&&(gender.get(i).equals("Female"))) {
			female[0]++;

		} 
		 else if ((gmat.get(i)>=AverageGREScore)&&(gender.get(i).equals("Female")))
		 {
			 female[1]++;
		 }
		}
		dataset.setValue("GMAT below average ", female[0]);
		dataset.setValue("GMAT above average", female[1]);
		

		JFreeChart chart = ChartFactory.createPieChart3D("GMAT statistics of female students  ", // chart
																							// title
				dataset, // data
				true, // include legend
				true, false);
		

			return chart;
	}

	JFreeChart gmatByMale() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		int totalGMATScore=0;
		int gmatMale=0;
		int[] male = new int[2];
		for (int i = 0; i < id.size(); i++) {
			if (gmat.get(i)!=0) {
				totalGMATScore+=gmat.get(i);
				gmatMale++;
				

			} 
			
			
			

		}

		int AverageGREScore=totalGMATScore/gmatMale;
		for(int i=0;i<id.size();i++)
		{
		 if ((gmat.get(i)<=AverageGREScore)&&(gender.get(i).equals("Male"))) {
			male[0]++;

		} 
		 else if ((gmat.get(i)>=AverageGREScore)&&(gender.get(i).equals("Male")))
		 {
			 male[1]++;
		 }
		}
		dataset.setValue("GMAT below average ", male[0]);
		dataset.setValue("GMAT above average", male[1]);
		

		JFreeChart chart = ChartFactory.createPieChart3D("GMAT statistics of male students  ", // chart
																							// title
				dataset, // data
				true, // include legend
				true, false);
		

			return chart;
	}

	
	

	void studentsByMode() {
		DefaultCategoryDataset categoryDataSet = new DefaultCategoryDataset();

		int[] countryMode = new int[15];

		for (int i = 0; i < citizenship.size(); i++) {

			if ((citizenship.get(i).equals("Argentina")) && (modeOfAttdnce.get(i).equals("multi-modal"))) {
				countryMode[0]++;

			} else if (citizenship.get(i).equals("Uk") && modeOfAttdnce.get(i).equals("multi-modal")) {
				countryMode[1]++;

			} else if (citizenship.get(i).equals("USA") && modeOfAttdnce.get(i).equals("multi-modal")) {
				countryMode[2]++;

			} else if (citizenship.get(i).equals("China") && modeOfAttdnce.get(i).equals("multi-modal")) {
				countryMode[3]++;

			} else if (citizenship.get(i).equals("India") && modeOfAttdnce.get(i).equals("multi-modal")) {
				countryMode[4]++;

			}

			if (citizenship.get(i).equals("Argentina") && modeOfAttdnce.get(i).equals("internal")) {
				countryMode[5]++;

			} else if (citizenship.get(i).equals("Uk") && modeOfAttdnce.get(i).equals("internal")) {
				countryMode[6]++;

			} else if (citizenship.get(i).equals("USA") && modeOfAttdnce.get(i).equals("internal")) {
				countryMode[7]++;

			} else if (citizenship.get(i).equals("China") && modeOfAttdnce.get(i).equals("internal")) {
				countryMode[8]++;

			} else if (citizenship.get(i).equals("India") && modeOfAttdnce.get(i).equals("internal")) {
				countryMode[9]++;

			}
			if (citizenship.get(i).equals("Argentina") && modeOfAttdnce.get(i).equals("external")) {
				countryMode[10]++;

			} else if (citizenship.get(i).equals("Uk") && modeOfAttdnce.get(i).equals("external")) {
				countryMode[11]++;

			} else if (citizenship.get(i).equals("USA") && modeOfAttdnce.get(i).equals("external")) {
				countryMode[12]++;

			} else if (citizenship.get(i).equals("China") && modeOfAttdnce.get(i).equals("external")) {
				countryMode[13]++;

			} else if (citizenship.get(i).equals("India") && modeOfAttdnce.get(i).equals("external")) {
				countryMode[14]++;

			}

		}

		categoryDataSet.setValue(countryMode[0], "Argentina", "multi-modal");
		categoryDataSet.setValue(countryMode[1], "Uk", "multi-modal");
		categoryDataSet.setValue(countryMode[2], "USA", "multi-modal");
		categoryDataSet.setValue(countryMode[3], "China", "multi-modal");
		categoryDataSet.setValue(countryMode[4], "India", "multi-modal");
		categoryDataSet.setValue(countryMode[5], "Argentina", "internal");
		categoryDataSet.setValue(countryMode[6], "Uk", "internal");
		categoryDataSet.setValue(countryMode[7], "USA", "internal");
		categoryDataSet.setValue(countryMode[8], "China", "internal");
		categoryDataSet.setValue(countryMode[9], "India", "internal");
		categoryDataSet.setValue(countryMode[10], "Crotia", "external");
		categoryDataSet.setValue(countryMode[11], "Uk", "external");
		categoryDataSet.setValue(countryMode[12], "USA", "external");
		categoryDataSet.setValue(countryMode[13], "China", "external");
		categoryDataSet.setValue(countryMode[14], "India", "external");

		JFreeChart chart = ChartFactory.createBarChart("STUDENTS PER COUNTRY PER MODE OF ATTENDANCE", // chart
																										// title
				"Mode of attendance by country", "Number of students", categoryDataSet, PlotOrientation.VERTICAL, // data
				true, // include legend
				true, false);

		chart.setBackgroundPaint(Color.white);
		// get a reference to the plot for further customisation...
		// final CategoryPlot plot = chart.getCategoryPlot();
		// CategoryItemRenderer renderer = new CustomRenderer();
		// plot.setRenderer(renderer);



	}

	JFreeChart salByGenderByCourse() {
		
		int totalMISMMale = 0;
		
		int totalMSPPMMale = 0;
		int totalMSITMale = 0;
		
		int mismMale = 0;
		
		int msppmMale = 0;
		int msitMale = 0;
	int totalMISMFemale = 0;
		
		int totalMSPPMFemale = 0;
		int totalMSITFemale = 0;
		
		int mismFemale = 0;
		
		int msppmFemale = 0;
		int msitFemale = 0;
		DefaultCategoryDataset categoryDataSet = new DefaultCategoryDataset();

		for (int i = 0; i < id.size(); i++) {
			  if (course_info.get(i).equals("MISM") && (gender.get(i).equals("Male"))) {
				mismMale++;
				totalMISMMale += sal.get(i);
			}  else if (course_info.get(i).equals("MSPPM") &&(gender.get(i).equals("Male"))) {
				msppmMale++;
				totalMSPPMMale += sal.get(i);
			} else if (course_info.get(i).equals("MSIT") && (gender.get(i).equals("Male"))) {
				msitMale++;
				totalMSITMale += sal.get(i);
			}
			  if (course_info.get(i).equals("MISM") && (gender.get(i).equals("Female"))) {
					mismFemale++;
					totalMISMFemale += sal.get(i);
				}  else if (course_info.get(i).equals("MSPPM") && (gender.get(i).equals("Female"))) {
					msppmFemale++;
					totalMSPPMFemale += sal.get(i);
				} else if (course_info.get(i).equals("MSIT") && (gender.get(i).equals("Female"))) {
					msitFemale++;
					totalMSITFemale += sal.get(i);
				}

		}

		int avgMISMMale = totalMISMMale / mismMale;
		int avgMISMFemale = totalMISMFemale / mismFemale;
		int avgMSITMale = totalMSITMale / msitMale;
		int avgMSITFemale = totalMSITFemale / msitFemale;
		int avgMSPPMMale = totalMSPPMMale / msppmMale;
		int avgMSPPMFemale = totalMSPPMFemale / msppmFemale;

		categoryDataSet.setValue(avgMISMMale, "Male", "MISM");
		categoryDataSet.setValue(avgMISMFemale, "Female", "MISM");
		categoryDataSet.setValue(avgMSITMale, "Male", "MSIT");
		categoryDataSet.setValue(avgMSITFemale, "Female", "MSIT");
		categoryDataSet.setValue(avgMSPPMMale, "Male", "MSPPM");
		categoryDataSet.setValue(avgMSPPMFemale, "Female", "MSPPM");

		JFreeChart chart = ChartFactory.createBarChart3D("Average Salary  per course", // chart
																					// title
				"Courses", "Average Salary", categoryDataSet, PlotOrientation.VERTICAL, // data
				true, // include legend
				true, false);

		chart.setBackgroundPaint(Color.white);
		
		

		return chart;

	}

	JFreeChart gmatByCourse() {
		int totalBIDAGre = 0;
		int totalMISMGre = 0;
		int totalMSCSGre = 0;
		int totalMSPPMGre = 0;
		int totalMSITGre = 0;
		int bida = 0;
		int mism = 0;
		int mscs = 0;
		int msppm = 0;
		int msit = 0;

		DefaultCategoryDataset categoryDataSet = new DefaultCategoryDataset();

		for (int i = 0; i < gmat.size(); i++) {
			if (course_info.get(i).equals("BIDA") && (gmat.get(i) != 0)) {
				bida++;
				totalBIDAGre += gmat.get(i);
			} else if (course_info.get(i).equals("MISM") && (gmat.get(i) != 0)) {
				mism++;
				totalMISMGre += gmat.get(i);
			} else if (course_info.get(i).equals("MSCS") && (gmat.get(i) != 0)) {
				mscs++;
				totalMSCSGre += gmat.get(i);
			} else if (course_info.get(i).equals("MSPPM") && (gmat.get(i) != 0)) {
				msppm++;
				totalMSPPMGre += gmat.get(i);
			} else if (course_info.get(i).equals("MSIT") && (gmat.get(i) != 0)) {
				msit++;
				totalMSITGre += gmat.get(i);
			}

		}

		int avgBIDAGre = totalBIDAGre / bida;
		int avgMISMGre = totalMISMGre / mism;
		int avgMSCSGre = totalMSCSGre / mscs;
		int avgMSPPMGre = totalMSPPMGre / msppm;
		int avgMSITGre = totalMSITGre / msit;

		categoryDataSet.setValue(avgBIDAGre, "GMAT", "BIDA");
		categoryDataSet.setValue(avgMISMGre, "GMAT", "MISM");
		categoryDataSet.setValue(avgMSCSGre, "GMAT", "MSCS");
		categoryDataSet.setValue(avgMSPPMGre, "GMAT", "MSPPM");
		categoryDataSet.setValue(avgMSITGre, "GMAT", "MSIT");

		JFreeChart chart = ChartFactory.createBarChart3D("Average GMAT per course", // chart
																					// title
				"Courses", "GMAT", categoryDataSet, PlotOrientation.VERTICAL, // data
				true, // include legend
				true, false);

		chart.setBackgroundPaint(Color.white);
		
		


		return chart;

	}
	
	
	JFreeChart greByCourse() {
		int totalBIDAGre = 0;
		int totalMISMGre = 0;
		int totalMSCSGre = 0;
		int totalMSPPMGre = 0;
		int totalMSITGre = 0;
		int bida = 0;
		int mism = 0;
		int mscs = 0;
		int msppm = 0;
		int msit = 0;

		DefaultCategoryDataset categoryDataSet = new DefaultCategoryDataset();

		for (int i = 0; i < id.size(); i++) {
			if (course_info.get(i).equals("BIDA") && (gre.get(i) != 0)) {
				bida++;
				totalBIDAGre += gre.get(i);
			} else if (course_info.get(i).equals("MISM") && (gre.get(i) != 0)) {
				mism++;
				totalMISMGre += gre.get(i);
			} else if (course_info.get(i).equals("MSCS") && (gre.get(i) != 0)) {
				mscs++;
				totalMSCSGre += gre.get(i);
			} else if (course_info.get(i).equals("MSPPM") && (gre.get(i) != 0)) {
				msppm++;
				totalMSPPMGre += gre.get(i);
			} else if (course_info.get(i).equals("MSIT") && (gre.get(i) != 0)) {
				msit++;
				totalMSITGre += gre.get(i);
			}

		}

		int avgBIDAGre = totalBIDAGre / bida;
		int avgMISMGre = totalMISMGre / mism;
		int avgMSCSGre = totalMSCSGre / mscs;
		int avgMSPPMGre = totalMSPPMGre / msppm;
		int avgMSITGre = totalMSITGre / msit;

		categoryDataSet.setValue(avgBIDAGre, "GRE", "BIDA");
		categoryDataSet.setValue(avgMISMGre, "GRE", "MISM");
		categoryDataSet.setValue(avgMSCSGre, "GRE", "MSCS");
		categoryDataSet.setValue(avgMSPPMGre, "GRE", "MSPPM");
		categoryDataSet.setValue(avgMSITGre, "GRE", "MSIT");

		JFreeChart chart = ChartFactory.createBarChart3D("Average GRE per course", // chart
																					// title
				"Courses", "GRE", categoryDataSet, PlotOrientation.VERTICAL, // data
				true, // include legend
				true, false);

		chart.setBackgroundPaint(Color.white);
		
		


		return chart;

	}
	
	
	
	JFreeChart equityPerYear() {
		int dis2012 = 0;
		int dis2013 = 0;
		int dis2014 = 0;
		int low2012 = 0;
		int low2013 = 0;
		int low2014 = 0;
		int wom2012 = 0;
		int wom2013 = 0;
		int wom2014 = 0;
		int rr2012 = 0;
		int rr2013 = 0;
		int rr2014 = 0;

		for (int i = 0; i < equityData.size(); i++) {

			if ((equityData.equals("Disability")) && (yearOfArrival.get(i) == 2012)) {

				dis2012++;
			} else if ((equityData.get(i).equals("Disability")) && (yearOfArrival.get(i) == 2013)) {
				dis2013++;
			} else if ((equityData.get(i).equals("Disability")) && (yearOfArrival.get(i) == 2014)) {
				dis2014++;
			} else if ((equityData.get(i).equals("low-income")) && (yearOfArrival.get(i) == 2012)) {
				low2012++;
			} else if ((equityData.get(i).equals("low-income")) && (yearOfArrival.get(i) == 2013)) {
				low2013++;
			} else if ((equityData.get(i).equals("low-income")) && (yearOfArrival.get(i) == 2014)) {
				low2014++;
			} else if ((equityData.get(i).equals("Women in non-traditional areas")) && (yearOfArrival.get(i) == 2012)) {
				wom2012++;
			} else if ((equityData.get(i).equals("Women in non-traditional areas")) && (yearOfArrival.get(i) == 201)) {
				wom2013++;
			} else if ((equityData.get(i).equals("Women in non-traditional areas")) && (yearOfArrival.get(i) == 2014)) {
				wom2014++;
			} else if ((equityData.get(i).equals("Regional/Remote")) && (yearOfArrival.get(i) == 2012)) {
				rr2012++;
			} else if ((equityData.get(i).equals("Regional/Remote")) && (yearOfArrival.get(i) == 2013)) {
				rr2013++;
			} else if ((equityData.get(i).equals("Regional/Remote")) && (yearOfArrival.get(i) == 2014)) {
				rr2014++;
			}

		}

		String[] userName = new String[] { "Disability", "low-income", "Women in non-traditional areas",
				"Regional/Remote" };

		String[] appname = new String[] { "2012", "2013", "2014" };
		double[][] data = new double[][] { { dis2012, dis2013, dis2014 }, { low2012, low2013, low2014 },
				{ wom2012, wom2013, wom2014 }, { rr2012, rr2013, rr2014 } };
		CategoryDataset categoryDataSet = DatasetUtilities.createCategoryDataset(userName, appname, data);
		JFreeChart chart = ChartFactory.createStackedBarChart3D("Equity per year", // chart
																					// title
				"Year", "Number of students", categoryDataSet, PlotOrientation.VERTICAL, // data
				true, // include legend
				true, false);

return chart;
	}

	
	
	
	

	
}
