import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.*;
import java.util.*;

import javafx.util.Pair;

import javax.swing.*;

public class CreatePlot extends ApplicationFrame{
	private DefaultCategoryDataset createDataset(String conceptName, String pathFile) throws UnsupportedEncodingException, FileNotFoundException
	 {
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		 String unicodeString = "utf-8";
		 BufferedReader reader;
		 reader = new BufferedReader(new InputStreamReader(new FileInputStream(pathFile),unicodeString));

		 try{
	            reader.readLine();
	            String currentLine;
	            while ((currentLine = reader.readLine()) != null){
	                String[] substrs = currentLine.split(",");
	                if(substrs[0].equals(conceptName))
	                {
	                	for(int i = 1; i<substrs.length; i++)
	                	{
	                		Integer k = (i-1)*2;
	                		String s = k.toString();
	                		dataset.addValue(Integer.parseInt(substrs[i]), "Concept Size", s);
	                	}
	                	break;
	                }
	            }

	        }
	        catch(IOException exc){
	            throw new NullPointerException("IOException occurred");
	        }
	        catch(Exception exc){
	        	throw new NullPointerException("some error occurred");
	        }
	        finally {
	            try{
	                reader.close();
	            }
	            catch(Exception exc){
	                //do nothing. Let's hope future reads will be just fine.
	            }
	        }
		 return dataset;
	 }
	public static void main( String[ ] args ) throws UnsupportedEncodingException, FileNotFoundException
	{
		CreatePlot chart = new CreatePlot("����������_����", "C:\\result.txt", "Size of Concept" ,"Size of" + " ������������ ������ " + "VS weeks");
		chart.pack( );
		RefineryUtilities.centerFrameOnScreen( chart );
		chart.setVisible( true );
	}

	@Override
	public void windowClosing(WindowEvent event) {
		if(event.getWindow() == this) {
			this.dispose();
		}

	}

	public CreatePlot(String conceptName, String pathFile, String applicationTitle , String chartTitle ) throws UnsupportedEncodingException, FileNotFoundException
	{
		super(applicationTitle);
//		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JFreeChart lineChart = ChartFactory.createLineChart(
				chartTitle,
				"Weeks","Size of Concept",
				createDataset(conceptName, pathFile),
				PlotOrientation.VERTICAL,
				true,true,false
		);
		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 580 , 367 ) );
		setContentPane( chartPanel );
	}
}
