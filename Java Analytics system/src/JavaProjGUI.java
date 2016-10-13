import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;


import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class JavaProjGUI extends JFrame {

	private JFrame frame;
	JPanel JFreePanel2_Panel1 = new JPanel();
	JPanel JFreePanel2_Panel2;
	private JPanel mainPanelJFree;
	private JList<String> JFreelist;
	private JFreeChart c0;
	private JFreeChart c1;
	private JFreeChart c2;
	private ChartPanel chart;
	ChartFromCSV csvJfree = new ChartFromCSV();
	private JPanel JFreePanel3_Panel1;
	private JButton JFreedownload;
	private JPanel JFreePanel1;
	private JPanel JFreePanel2;
	private JPanel JFreePanel3;
	private JTextArea JFreeNotes;
	private JButton JFreeprofile;
	private JButton JFreeback;
	private JTextArea JFreetxtStatistics;
	private JPanel JfreeChartsMainPanel;
	//private CardLayout c;
	
	private static JPanel contentPane;
	private static JPanel panel;
	private static JPanel linearRegKM;
	private static JPanel internalFrame;
	
	private static JComboBox fileName;
	private static DefaultComboBoxModel cbModel;
	
	private static JLabel updateLabel;
	private static JLabel uploadLabel;
	
	private static File temp;
	private static boolean other=false;
    private static boolean fileCheck=false;
    private static Vector files= new Vector();
    private static String bucketName;
    private static String key;
    
    private static AWSCredentials credentials = null;
    private static TransferManager transferManager;
    private static AmazonS3 awsS3;
    
    private static  Upload fileToAWS;
    private static Upload updatedFiletoAWS;
    private static JTextField kmeansEquation;
    
    private static File selectedFile;
    private  static CSV2Arff csv;
    private static File testData;
    private  File testDataClass;
    private static Instances data;
    
    private static boolean isFileSelected= false;
    private static boolean fileAWS=true;
    private static ArffSaver arffformater; 
    private static JPanel kmeansChartPredicted;
    private static JPanel kmeansChartExpected;
    private static JPanel bannerPanel;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaProjGUI frame = new JavaProjGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
		            credentials = new ProfileCredentialsProvider("default").getCredentials();
		        } catch (Exception e) {
		            System.out.println("Credential problems");
		        }
				
				bucketName = "student-info";
				key="Data.csv";
				
		        awsS3 = new AmazonS3Client(credentials);
		       
		        transferManager = new TransferManager(awsS3);
		        
		        ObjectListing objectList= awsS3.listObjects(new ListObjectsRequest()
	                    .withBucketName(bucketName));
		        List <S3ObjectSummary> objDetails= objectList.getObjectSummaries();
		      
		        
		        if(objDetails.isEmpty())
		        	fileCheck=false;
		        else
		        for(S3ObjectSummary objSummary : objDetails)
		        {
		        	System.out.println("Key:"+objSummary.getKey());
		        	files.add(objSummary.getKey());
		        	if(objSummary.getKey().equals(key))
		        	{
		        		fileCheck=true;
		        		files.remove(objSummary.getKey());
		        	}
		        }
		        System.out.println(fileCheck);
		        cbModel= new DefaultComboBoxModel(files);
		        fileName.setModel(cbModel);
			}
			
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public JavaProjGUI() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 901, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		contentPane.add(panel, "name_1448730381604349000");
		panel.setLayout(null);
		
		JPanel awsSelect = new JPanel();
		awsSelect.setBackground(new Color(245, 245, 245));
		awsSelect.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		awsSelect.setBounds(64, 414, 432, 215);
		panel.add(awsSelect);
		awsSelect.setLayout(null);
		
		fileName = new JComboBox();
		fileName.setBounds(26, 21, 193, 88);
		awsSelect.add(fileName);
		
		JPanel bSelectFile = new JPanel();
		bSelectFile.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		bSelectFile.setBounds(31, 121, 184, 50);
		awsSelect.add(bSelectFile);
		bSelectFile.setLayout(null);
		
		JButton selectFile = new JButton("Select File");
		selectFile.setBounds(0, 0, 184, 50);
		bSelectFile.add(selectFile);
		selectFile.setFont(new Font("Superclarendon", Font.BOLD, 14));
		selectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedWriter bwData = null;
				if (!(fileAWS))
				{
					JOptionPane.showMessageDialog(null, "Wait for the upload to finish");
				}
				else
				{
				try
				{
				//	selectedFile = new File("/Users/anmoljain/Desktop/TeamProject/JavaProj 1.0/src/tempChart.csv");
					selectedFile = File.createTempFile("tempChart", ".csv");
					bwData= new BufferedWriter(new FileWriter(selectedFile));
					
					String key2=fileName.getSelectedItem().toString();
				S3Object s3Object = awsS3.getObject(new GetObjectRequest(bucketName, key2));
				InputStream ip= s3Object.getObjectContent();
				BufferedReader readerUser=new BufferedReader(new InputStreamReader(ip));
				
				
					String dataLine =readerUser.readLine();
					while(dataLine!=null)
					{
					
					bwData.write(dataLine);
					bwData.newLine();
					dataLine =readerUser.readLine();
					}
					
					isFileSelected=true;
				}
				
				 catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "NO DATA PRESENT!!!");
				}finally
				{
					try {
						if(bwData!=null)
						bwData.close();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				}
			}
		});
		
		JPanel bSelectAllFiles = new JPanel();
		bSelectAllFiles.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		bSelectAllFiles.setBounds(250, 121, 160, 50);
		awsSelect.add(bSelectAllFiles);
		bSelectAllFiles.setLayout(null);
		
		JButton allFiles = new JButton("Select All Files");
		allFiles.setBounds(0, 0, 160, 50);
		bSelectAllFiles.add(allFiles);
		allFiles.setFont(new Font("Superclarendon", Font.BOLD, 14));
		allFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedWriter bwData = null;
				if (!(fileAWS))
				{
					JOptionPane.showMessageDialog(null, "Wait for the upload to finish");
				}
				else{
				try {
					selectedFile = File.createTempFile("tempChart", ".csv");
					bwData= new BufferedWriter(new FileWriter(selectedFile));
				S3Object object = awsS3.getObject(new GetObjectRequest(bucketName, "Data.csv"));
				InputStream st= object.getObjectContent();
				BufferedReader readerUser=new BufferedReader(new InputStreamReader(st));;
				
				
				String dataLine =readerUser.readLine();
				while(dataLine!=null)
				{
				System.out.println(dataLine);
				bwData.write(dataLine);
				bwData.newLine();
				dataLine =readerUser.readLine();
				
				isFileSelected = true;
				
				}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "NO DATA PRESENT!!!");
				}finally
				{
					try {
						bwData.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			}
		});
		
		JPanel awsUpload = new JPanel();
		awsUpload.setBackground(new Color(245, 245, 245));
		awsUpload.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		awsUpload.setBounds(64, 252, 432, 107);
		panel.add(awsUpload);
		awsUpload.setLayout(null);
		
		JPanel bUploadPanel = new JPanel();
		bUploadPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		bUploadPanel.setBounds(25, 32, 190, 44);
		awsUpload.add(bUploadPanel);
		bUploadPanel.setLayout(null);
		
		JButton upload = new JButton("Upload File");
		upload.setBounds(0, 0, 190, 44);
		bUploadPanel.add(upload);
		upload.setFont(new Font("Superclarendon", Font.BOLD, 14));
		//Image uploadIcon= new ImageIcon(this.getClass().getResource("/uploadIcon.png")).getImage();
		//upload.setIcon(new ImageIcon(uploadIcon));
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             uploadToCloud();
			}
		});
		
		upload.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent keyR)
			{
				if(keyR.getKeyChar()=='U' || keyR.getKeyChar()=='u' )
				uploadToCloud();
			}
			public void keyPressed(KeyEvent keyP)
			{
				
			}
			public void keyTyped(KeyEvent keyT)
			{
				
			}
		});
		
		uploadLabel = new JLabel("       Upload Status");
		uploadLabel.setFont(new Font("Superclarendon", Font.PLAIN, 12));
		uploadLabel.setBounds(253, 19, 139, 28);
		awsUpload.add(uploadLabel);
		
		updateLabel = new JLabel("          Update");
		updateLabel.setFont(new Font("Superclarendon", Font.PLAIN, 12));
		updateLabel.setBounds(263, 59, 129, 22);
		awsUpload.add(updateLabel);
		
		JPanel dashboardPannel = new JPanel();
		dashboardPannel.setBackground(new Color(245, 245, 245));
		dashboardPannel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		dashboardPannel.setBounds(553, 252, 264, 371);
		panel.add(dashboardPannel);
		dashboardPannel.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_6.setBounds(35, 267, 195, 59);
		dashboardPannel.add(panel_6);
		panel_6.setLayout(null);
		
		JButton linearRegression = new JButton("Linear Regression");
		linearRegression.setBounds(0, 0, 195, 59);
		panel_6.add(linearRegression);
		linearRegression.setFont(new Font("Superclarendon", Font.BOLD, 13));
		linearRegression.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				performLR();

			}
		});
		
		JPanel bannerTartanPanel = new JPanel();
		bannerTartanPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		bannerTartanPanel.setBounds(40, 39, 190, 59);
		dashboardPannel.add(bannerTartanPanel);
		bannerTartanPanel.setLayout(null);
		
		JButton bLinearPanel = new JButton("Charts");
		bLinearPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!(isFileSelected))
				{
					JOptionPane.showMessageDialog(null, "Please select a file first");
				}
				else
				{
				try {
					contentPane.removeAll();
			        contentPane.add(JfreeChartsMainPanel);
			        contentPane.repaint();
			        contentPane.revalidate();
			        csvJfree.loadData(selectedFile);
				
			}
				catch(Exception eJf)
				{
					
				}
				}
			}
		});
		bLinearPanel.setBounds(0, 0, 190, 59);
		bannerTartanPanel.add(bLinearPanel);
		bLinearPanel.setFont(new Font("Superclarendon", Font.BOLD, 13));
		
		JPanel bClassification = new JPanel();
		bClassification.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		bClassification.setBounds(35, 155, 190, 59);
		dashboardPannel.add(bClassification);
		bClassification.setLayout(null);
		
		JButton bLinear = new JButton("Classification");
		bLinear.setBounds(0, 0, 190, 59);
		bClassification.add(bLinear);
		bLinear.setFont(new Font("Superclarendon", Font.BOLD, 13));
		bLinear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(isFileSelected))
				{
					JOptionPane.showMessageDialog(null, "Please select a file first");
				}
				else
				{
					File replacedFileClass=null;
				JFileChooser fileChooser = new JFileChooser();
	            int showOpenDialog = fileChooser.showOpenDialog(panel);
	            if (showOpenDialog != JFileChooser.APPROVE_OPTION) 
	            	{
	            	return;
	            	}
			testDataClass = fileChooser.getSelectedFile();
			
			try {
				csv = new CSV2Arff();
				arffformater = csv.CSV2Arff1(selectedFile);
				Classification cJ48 = new Classification();
				replacedFileClass=cJ48.classificationJ48(arffformater, testDataClass);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(null, "Your file has been written to \"" + replacedFileClass.getPath()+"\"");
				}	
			}
		});
		panel.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent keyR)
			{
				if(keyR.getKeyChar()=='L' || keyR.getKeyChar()=='l' )
				performLR();
				
			}
			public void keyPressed(KeyEvent keyP)
			{
				
			}
			public void keyTyped(KeyEvent keyT)
			{
				
			}
		});
		
		bannerPanel = new JPanel();
	
		bannerPanel.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		bannerPanel.setBackground(new Color(245, 245, 245));
		bannerPanel.setBounds(64, 28, 760, 179);
		panel.add(bannerPanel);
		bannerPanel.setLayout(null);
		
		JLabel symbol = new JLabel("");
		Image Symbol= new ImageIcon(this.getClass().getResource("/tartan.png")).getImage();
		symbol.setIcon(new ImageIcon(Symbol));
		symbol.setBounds(535, 21, 206, 141);
		
		bannerPanel.add(symbol);
		
		JLabel bannerPicture = new JLabel("");
		Image MainBanner= new ImageIcon(this.getClass().getResource("/MainBanner.png")).getImage();
		bannerPicture.setIcon(new ImageIcon(MainBanner));
		bannerPicture.setBounds(20, 21, 515, 126);
		bannerPanel.add(bannerPicture);
        
		linearRegKM = new JPanel();
		contentPane.add(linearRegKM, "name_1448876120636915000");
		linearRegKM.setLayout(null);
		
		JPanel lrBorder = new JPanel();
		lrBorder.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lrBorder.setBounds(30, 180, 162, 400);
		linearRegKM.add(lrBorder);
		lrBorder.setLayout(null);
		
		JPanel bLRPanel = new JPanel();
		bLRPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		bLRPanel.setBounds(10, 30, 145, 60);
		lrBorder.add(bLRPanel);
		bLRPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.setBounds(0, 0, 145, 60);
		bLRPanel.add(btnNewButton);
		btnNewButton.setFont(new Font("Superclarendon", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
	            int showOpenDialog = fileChooser.showOpenDialog(panel);
	            if (showOpenDialog != JFileChooser.APPROVE_OPTION) 
	            	{
	            	return;
	            	}
			testData = fileChooser.getSelectedFile();
			Line l;
			try {
				l = new Line();
				JPanel linePlot = l.createChartPanel(data,testData, kmeansEquation, selectedFile);
				internalFrame.add(linePlot, "lineplot");
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		final CardLayout c = new CardLayout(0,0);
		internalFrame = new JPanel();
		internalFrame.setBounds(225,180,600,400);
		linearRegKM.add(internalFrame);
		internalFrame.setLayout(c);
		
		JPanel blank = new JPanel();
		blank.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		internalFrame.add(blank, "name_1449244700518679000");
		blank.setLayout(null);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_1.setBackground(new Color(245, 245, 245));
		panel_1.setBounds(35, 6, 788, 157);
		linearRegKM.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel regressionBanner = new JLabel("");
		Image regressionBannerIcon= new ImageIcon(this.getClass().getResource("/tartan.png")).getImage();
		regressionBanner.setIcon(new ImageIcon(regressionBannerIcon));
		regressionBanner.setBounds(558, 6, 206, 141);
		panel_1.add(regressionBanner);
		
		JLabel regressionBannerTopic = new JLabel("");
		Image regressionBannerText= new ImageIcon(this.getClass().getResource("/MainBanner.png")).getImage();
		regressionBannerTopic.setIcon(new ImageIcon(regressionBannerText));
		regressionBannerTopic.setBounds(31, 6, 515, 126);
		panel_1.add(regressionBannerTopic);
		
		 kmeansChartExpected = new JPanel();
		kmeansChartExpected.setBackground(Color.WHITE);
		internalFrame.add(kmeansChartExpected, "name_1449244700543215000");
		
		 kmeansChartPredicted = new JPanel();
		internalFrame.add(kmeansChartPredicted, "name_1449244700566992000");
		
		JPanel lRtextBox = new JPanel();
		lRtextBox.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lRtextBox.setBounds(226, 604, 549, 44);
		linearRegKM.add(lRtextBox);
		lRtextBox.setLayout(null);
		
		
		kmeansEquation = new JTextField();
		kmeansEquation.setBounds(0, 0, 549, 44);
		lRtextBox.add(kmeansEquation);
		kmeansEquation.setColumns(10);
		final JComboBox regressionOptions = new JComboBox();
		regressionOptions.setBounds(10, 143, 145, 60);
		lrBorder.add(regressionOptions);
		regressionOptions.setModel(new DefaultComboBoxModel(new String[] {"", "Scatter Plot Predicted", "Scatter Plot Expected", "Predicted vs Expected"}));
		
		JPanel bBack = new JPanel();
		bBack.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		bBack.setBounds(10, 284, 145, 60);
		lrBorder.add(bBack);
		bBack.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBounds(0, 0, 145, 60);
		bBack.add(btnNewButton_1);
		
		btnNewButton_1.setFont(new Font("Superclarendon", Font.BOLD, 13));
		regressionOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(regressionOptions.getSelectedItem().toString().equals("Scatter Plot Predicted"))
        		{		
        			c.show(internalFrame, "scatterplot expected");
        	        
        		}
        		else if(regressionOptions.getSelectedItem().toString().equals("Scatter Plot Expected"))
        		{
        			c.show(internalFrame, "scatterplot predicted");
        	        
        		}
        		else if(regressionOptions.getSelectedItem().toString().equals("Predicted vs Expected"))
        		{
        			c.show(internalFrame, "lineplot");
        		}
        		
        	}
	
		});
		 
		
		
		
		
		
		 JfreeChartsMainPanel = new JPanel();
		contentPane.add(JfreeChartsMainPanel, "name_1449225495384860000");
		JfreeChartsMainPanel.setLayout(null);
		
		
		
		JFreePanel2 = new JPanel();
		JFreePanel2.setBackground(SystemColor.info);
		JFreePanel2.setBounds(140, 114, 398, 429);
		JfreeChartsMainPanel.add(JFreePanel2);
		JFreePanel2.setLayout(null);
		
		JFreePanel2_Panel2 = new JPanel();
		JFreePanel2_Panel2.setBounds(10, 208, 378, 210);
		JFreePanel2.add(JFreePanel2_Panel2);
		JFreePanel2_Panel2.setBorder(null);
		JFreePanel2_Panel2.setLayout(new CardLayout(0, 0));
		JFreePanel2_Panel1.setBounds(10, 11, 378, 186);
		JFreePanel2.add(JFreePanel2_Panel1);
		JFreePanel2_Panel1.setBorder(null);
		JFreePanel2_Panel1.setLayout(new CardLayout(0, 0));
		
	
		JfreeChartsMainPanel.setLocation(0, 0);
		JfreeChartsMainPanel.setSize(885,560);
		//frame.getContentPane().add(JfreeChartsMainPanel);
		JfreeChartsMainPanel.setLayout(null);
		
		JFreePanel1 = new JPanel();
		JFreePanel1.setBackground(SystemColor.inactiveCaption);
		JFreePanel1.setBounds(10, 114, 125, 429);
		JfreeChartsMainPanel.add(JFreePanel1);
		JFreePanel1.setLayout(null);
		
		
		 JFreelist = new JList();
		 JFreelist.setVisibleRowCount(4);
		 JFreelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 JFreelist.setModel(new AbstractListModel() {
		 	String[] values = new String[] {"Salary                                                                                  ", "Equity Data                                                                                           ", "GRE               ", "GMAT", "                                                               ", "                                 "};
		 	public int getSize() {
		 		return values.length;
		 	}
		 	public Object getElementAt(int index) {
		 		return values[index];
		 	}
		 });
		 JFreelist.setBounds(10, 54, 105, 99);
		 JFreePanel1.add(JFreelist);
		 JFreelist.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent arg0) {
		 		String s = ((String)JFreelist.getSelectedValue()).trim();
		 		if(s.equals("Salary"))
				{	JFreePanel2_Panel1.removeAll();
				JFreePanel2_Panel2.removeAll();
				JFreePanel3_Panel1.removeAll();
		 			
					JFreeChart c = csvJfree.salaryByMale();
					JFreeChart c1= csvJfree.salaryByFemale();
					
					ChartPanel chart=new ChartPanel(c);
					
					ChartPanel chart1=new ChartPanel(c1);
					JFreeChart c2 = csvJfree.salByGenderByCourse();
					ChartPanel chartPanel_1 = new ChartPanel(c2);
					chartPanel_1.setBounds(548, 114, 337, 264);
					
					JFreePanel3_Panel1.add(chartPanel_1);
					JFreePanel2_Panel1.add(chart);
					JFreePanel2_Panel2.add(chart1);
					JFreePanel2_Panel1.validate();
					
					JFreePanel2_Panel2.validate();
					//mainPanel.add(chartPanel_1);
					JFreePanel3_Panel1.validate();
				}
		 		
		 		else if(s.equals("Equity Data"))
				{	
				JFreePanel2_Panel1.removeAll();
				JFreePanel2_Panel2.removeAll();
				JFreePanel3_Panel1.removeAll();
					c0 = csvJfree.pgUgByDisability();
					c1= csvJfree.pgUgBylowIncome();
					
					ChartPanel chart=new ChartPanel(c0);
					
					ChartPanel chart1=new ChartPanel(c1);
					JFreeChart c2 = csvJfree.equityPerYear();
					ChartPanel chartPanel_1 = new ChartPanel(c2);
					chartPanel_1.setBounds(548, 114, 337, 264);
					JFreePanel3_Panel1.add(chartPanel_1);
					JFreePanel2_Panel1.add(chart);
					JFreePanel2_Panel1.validate();
					JFreePanel2_Panel2.add(chart1);
					JFreePanel2_Panel2.validate();
					
					JFreePanel3_Panel1.validate();
				}
		 		
		 		else if(s.equals("GRE"))
				{	JFreePanel2_Panel1.removeAll();
				JFreePanel2_Panel2.removeAll();
				JFreePanel3_Panel1.removeAll();
		 			
					JFreeChart c = csvJfree.greByMale();
					JFreeChart c1= csvJfree.greByFemale();
					
					ChartPanel chart=new ChartPanel(c);
					
					ChartPanel chart1=new ChartPanel(c1);
					JFreeChart c2 = csvJfree.greByCourse();
					ChartPanel chartPanel_1 = new ChartPanel(c2);
					chartPanel_1.setBounds(548, 114, 337, 264);
					
					JFreePanel3_Panel1.add(chartPanel_1);
					JFreePanel2_Panel1.add(chart);
					JFreePanel2_Panel2.add(chart1);
					JFreePanel2_Panel1.validate();
					
					JFreePanel2_Panel2.validate();
					//mainPanel.add(chartPanel_1);
					JFreePanel3_Panel1.validate();
				}
		 		else if(s.equals("GMAT"))
				{	JFreePanel2_Panel1.removeAll();
				JFreePanel2_Panel2.removeAll();
				JFreePanel3_Panel1.removeAll();
		 			
					JFreeChart c = csvJfree.gmatByMale();
					JFreeChart c1= csvJfree.gmatByFemale();
					
					ChartPanel chart=new ChartPanel(c);
					
					ChartPanel chart1=new ChartPanel(c1);
					JFreeChart c2 = csvJfree.gmatByCourse();
					ChartPanel chartPanel_1 = new ChartPanel(c2);
					chartPanel_1.setBounds(548, 114, 337, 264);
					
					JFreePanel3_Panel1.add(chartPanel_1);
					JFreePanel2_Panel1.add(chart);
					JFreePanel2_Panel2.add(chart1);
					JFreePanel2_Panel1.validate();
					
					JFreePanel2_Panel2.validate();
					//mainPanel.add(chartPanel_1);
					JFreePanel3_Panel1.validate();
				}
		 	}
		 });
		JFreelist.setLayoutOrientation(JList.VERTICAL_WRAP);
		JFreelist.setBackground(UIManager.getColor("Button.background"));
		
		JFreedownload = new JButton("Download");
		JFreedownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFreeChart c1 = csvJfree.equityPerYear();
				JFreeChart c2 = csvJfree.gmatByCourse();
				JFreeChart c3 = csvJfree.gmatByFemale();
				JFreeChart c4 = csvJfree.gmatByMale();
				JFreeChart c5 = csvJfree.greByCourse();
				JFreeChart c6 = csvJfree.greByFemale();
				JFreeChart c7 = csvJfree.greByMale();
				JFreeChart c8 = csvJfree.salaryByMale();
				JFreeChart c9 = csvJfree.pgUgByDisability();
				JFreeChart c10 = csvJfree.pgUgBylowIncome();
				JFreeChart c11 = csvJfree.salByGenderByCourse();
				JFreeChart c12 = csvJfree.salaryByFemale();

				File chart1 = new File("equity_per_year.png");
				File chart2 = new File("gmat_by_course.png");
				File chart3 = new File("gmat_by_female.png");
				File chart4 = new File("gmat_by_male.png");
				File chart5 = new File("gre_by_course.png");
				File chart6 = new File("gre_by_female.png");
				File chart7 = new File("gre_by_male.png");
				File chart8 = new File("sal_by_male.png");
				File chart9 = new File("pgug_by_disability.png");
				File chart10 = new File("pgug_by_low_income.png");
				File chart11 = new File("sal_for_years.png");
				File chart12 = new File("sal_by_female.png");
				
				try {
					ChartUtilities.saveChartAsPNG(chart1, c1, 560, 370);
					ChartUtilities.saveChartAsPNG(chart2, c2, 560, 370);
					ChartUtilities.saveChartAsPNG(chart3, c3, 560, 370);
					ChartUtilities.saveChartAsPNG(chart4, c4, 560, 370);
					ChartUtilities.saveChartAsPNG(chart5, c5, 560, 370);
					ChartUtilities.saveChartAsPNG(chart6, c6, 560, 370);
					ChartUtilities.saveChartAsPNG(chart7, c7, 560, 370);
					ChartUtilities.saveChartAsPNG(chart8, c8, 560, 370);
					ChartUtilities.saveChartAsPNG(chart9, c9, 560, 370);
					ChartUtilities.saveChartAsPNG(chart10, c10, 560, 370);
					ChartUtilities.saveChartAsPNG(chart11, c11, 560, 370);
					ChartUtilities.saveChartAsPNG(chart12, c12, 560, 370);
					JOptionPane jOptionPaneDownload = new javax.swing.JOptionPane();
					jOptionPaneDownload.showMessageDialog(null, "Charts have been saved at blah blah");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		JFreedownload.setBounds(10, 167, 105, 73);
		JFreePanel1.add(JFreedownload);
		
		JFreeprofile = new JButton("Profile Details");
		JFreeprofile.setBounds(10, 252, 105, 73);
		JFreePanel1.add(JFreeprofile);
		
		JFreeback = new JButton("Back");
		JFreeback.setBounds(10, 336, 105, 82);
		JFreePanel1.add(JFreeback);
		
		JFreetxtStatistics = new JTextArea();
		JFreetxtStatistics.setBackground(SystemColor.inactiveCaption);
		JFreetxtStatistics.setText("STATICS BY\r\nCATEGORY:");
		JFreetxtStatistics.setBounds(10, 11, 94, 49);
		JFreePanel1.add(JFreetxtStatistics);
		
		JFreePanel3 = new JPanel();
		JFreePanel3.setBackground(new Color(255, 240, 245));
		JFreePanel3.setBounds(548, 114, 324, 429);
		JfreeChartsMainPanel.add(JFreePanel3);
		JFreePanel3.setLayout(null);
		
		

				
		 JFreeNotes = new JTextArea();
		JFreeNotes.setBounds(13, 306, 295, 111);
		JFreePanel3.add(JFreeNotes);
		JFreeNotes.setTabSize(250);
		JFreeNotes.setRows(250);
		
		JLabel JFreeLabelNotes = new JLabel("Notes:");
		JFreeLabelNotes.setBounds(19, 281, 46, 14);
		JFreePanel3.add(JFreeLabelNotes);
		
		JButton JFreebtnsave = new JButton("Save");
		JFreebtnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text=JFreeNotes.getText();
				String[] textArr=text.split("\\n");
				System.out.println(text);
				try {

				
					File file = new File("./savednotes.txt");

					
					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file);
					BufferedWriter buffOutput = new BufferedWriter(fw);
					for(int i=0;i<textArr.length;i++)
					{	buffOutput.write(textArr[i]);
					buffOutput.newLine();}
					
				buffOutput.close();
				JOptionPane jOptionPaneNotes = new javax.swing.JOptionPane();
				jOptionPaneNotes.showMessageDialog(null, "Notes has been saved at blah blah");
					

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		JFreebtnsave.setBounds(226, 286, 86, 14);
		JFreePanel3.add(JFreebtnsave);
		
		JFreePanel3_Panel1 = new JPanel();
		JFreePanel3_Panel1.setBounds(17, 16, 288, 237);
		JFreePanel3.add(JFreePanel3_Panel1);
		JFreePanel3_Panel1.setBorder(null);
		JFreePanel3_Panel1.setLayout(new CardLayout(0, 0));

	
	}
	
	private static void appendFile(BufferedReader reader) throws IOException {
		
		 BufferedWriter bw= new BufferedWriter(new FileWriter(temp,true));
	
       System.out.println("HELLO");
       String line = reader.readLine();
       System.out.println("Other file: "+ other);
       while (true) {
           if (other)
           {
           	line = reader.readLine();
           	other=false;
           }
           if (line == null) break;
           bw.write(line);
           bw.newLine();
           System.out.println("    " + line); 
           line = reader.readLine();
       }
       System.out.println();
       reader.close();
       bw.close();
       other=true;
   }
	
	public static void uploadToCloud()
	{
		fileAWS=false;
		other=false;
		
		JFileChooser fileChooser = new JFileChooser();
        int showOpenDialog = fileChooser.showOpenDialog(panel);
        if (showOpenDialog != JFileChooser.APPROVE_OPTION) 
        {
        	fileAWS=true;
        	return;
        }
        File fileToUpload = fileChooser.getSelectedFile();
        BufferedReader reader;
        
        
        PutObjectRequest putRequest;
       
       try {
    	   temp = File.createTempFile("temp", ".csv");
    	   if (fileCheck)
    	   {
    		   System.out.println(fileCheck);
    	   System.out.println("Wait for update to happen");
           S3Object s3Object = awsS3.getObject(new GetObjectRequest(bucketName, key));
            System.out.println("Content-Type: "  + s3Object.getObjectMetadata().getContentType());
		
		
		InputStream st= s3Object.getObjectContent();
        reader = new BufferedReader(new InputStreamReader(st));
        appendFile(reader);
    	   }
    	   
    	   System.out.println("Uploading file");
        
    	   putRequest = new PutObjectRequest( bucketName, fileToUpload.getName(), fileToUpload);
    	   fileToAWS = transferManager.upload(putRequest);
            files.add(fileToUpload.getName());
            while(!(fileToAWS.isDone()))
            {
            	uploadLabel.setText(fileToAWS.getProgress().getPercentTransferred()+"%");
            }
 
            uploadLabel.setText("Upload Done");
     //   reader= new BufferedReader(new InputStreamReader (new FileInputStream("/Users/anmoljain/Desktop/TeamProject/trial.csv")));
        
        
        reader= new BufferedReader(new InputStreamReader (new FileInputStream(fileToUpload)));
        appendFile(reader);
        fileToAWS.waitForCompletion();
        
        
        putRequest = new PutObjectRequest( bucketName, key, temp);
        System.out.println("Uploading updated file");
        updatedFiletoAWS = transferManager.upload(putRequest);
        while(!(updatedFiletoAWS.isDone()))
        {
        	updateLabel.setText(updatedFiletoAWS.getProgress().getPercentTransferred()+"%");
        }
        updateLabel.setText("Update Done");
        fileAWS= true;
       // btnBack.setVisible(true);
        
	} catch (IOException | AmazonClientException | InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
        
    System.out.println("Done");
    
    cbModel = new DefaultComboBoxModel( files );
    fileName.setModel(cbModel);
	}
	
	public static void performLR()
	{
		if(!(isFileSelected))
		{
			JOptionPane.showMessageDialog(null, "Please select a file first");
		}
		else
		{
		try {
			csv = new CSV2Arff();
			arffformater = csv.CSV2Arff1(selectedFile);
			Instance1 i= new Instance1();
			
				 data = i.performLinearRegression(arffformater);
				 
				 
				 ScatterPlot1 scp1 = new ScatterPlot1("Expected");
					scp1.pack();
					ScatterPlot2 scp2 = new ScatterPlot2("Predicted");
					scp2.pack();
					ChartPanel cp = null;
					try {
						cp = scp1.ScatterPlot12(data);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ChartPanel cp2 = null;
					try {
						cp2 = scp2.ScatterPlot21(data);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					kmeansChartExpected.add(cp);
					kmeansChartPredicted.add(cp2);
					
		} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		contentPane.removeAll();
        contentPane.add(linearRegKM);
        contentPane.repaint();
        contentPane.revalidate();
		}
		
	}
}
