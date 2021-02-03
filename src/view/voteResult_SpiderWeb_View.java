package view;

import java.awt.Font;
import java.text.DecimalFormat;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;

import model.chartModel;

public class voteResult_SpiderWeb_View {
/*
 * ���ɴ� �� ������ ��Ʈ�� �׸��� Ŭ����
 * 
 */
	String chartTitle;
	CategoryDataset dataset;
	SpiderWebPlot plot;
	
	chartModel charmodel = new chartModel();
	
	public voteResult_SpiderWeb_View(String title, int VoteSerial) {
		dataset = charmodel.JDBC_Spi_dataset(VoteSerial);
		chartTitle = title;
	}
	
	public ChartPanel creatChart() {
		ChartPanel cp;
		plot = new SpiderWebPlot(dataset);
		plot.setInteriorGap(0.20);
		
		plot.setNoDataMessage("������ ����");
		
		plot.getBaseSeriesOutlineStroke();
		
		CategoryItemLabelGenerator gen = new StandardCategoryItemLabelGenerator("{0} : {1} ({2})", new DecimalFormat("#,##0"), new DecimalFormat("0.#%"));
		plot.setLabelGenerator(gen);
		plot.setLabelGenerator(new StandardCategoryItemLabelGenerator());
		
		
		plot.setLabelFont(new Font("����ü", Font.BOLD, 15));
		
		JFreeChart chart = new JFreeChart(chartTitle, new Font("����ü", Font.BOLD, 20), plot, true);
		LegendTitle legend = new LegendTitle(plot);
		chart.getLegend().setItemFont(new Font("����ü", Font.BOLD, 15));
		

		cp = new ChartPanel(chart);
		cp.setMouseWheelEnabled(true);
		cp.setMouseZoomable(true, true);
		
		return cp;		
	}
}
