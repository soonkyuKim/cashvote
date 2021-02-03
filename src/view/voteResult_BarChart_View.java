package view;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.CategoryDataset;

import model.chartModel;

public class voteResult_BarChart_View {

	//���� ������ ���� ���� ���� ����׷���
	
	String chartTitle;
	CategoryDataset dataset;
	CategoryPlot plot;
	
	chartModel charmodel = new chartModel();
	
	public voteResult_BarChart_View(String title, int VOTEserial) {
		dataset = charmodel.JDBC_Bar_dataset(VOTEserial);
		chartTitle = title;
	}
	
	public ChartPanel creatChart() {
		ChartPanel cp;
		
		JFreeChart chart = ChartFactory.createStackedBarChart(chartTitle, null,
				"��ǥ�� ��", dataset, PlotOrientation.VERTICAL, false, false, false);
		
		chart.setBackgroundPaint(Color.white);
		chart.getTitle().setFont(new Font("����", Font.PLAIN, 20));
//		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 15));
		
		
		plot = (CategoryPlot) chart.getPlot();
		plot.getDomainAxis().setTickLabelFont(new Font("����", Font.PLAIN, 15));
		
		plot.getRangeAxis().setLabelFont(new Font("����", Font.BOLD, 12));
		
//		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);
		
		plot.setNoDataMessage("���� ������ ����");
		plot.setNoDataMessageFont(new Font("����", Font.BOLD, 30));
		
		StackedBarRenderer stackedbarrenderer = (StackedBarRenderer) plot.getRenderer();
		
		
		stackedbarrenderer.setDrawBarOutline(false);
		stackedbarrenderer.setMaximumBarWidth(0.10);
		stackedbarrenderer.setItemMargin(0.6);
		
		stackedbarrenderer.setDefaultItemLabelsVisible(true);
		stackedbarrenderer.setDefaultItemLabelFont(new Font("����", Font.PLAIN, 12));
		// �� ��Ʈ���� �� �о ǥ���ǵ���
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER,
				TextAnchor.CENTER, TextAnchor.CENTER, 0.0D);
		stackedbarrenderer.setPositiveItemLabelPositionFallback(position);
		stackedbarrenderer.setDefaultItemLabelGenerator(
				new StandardCategoryItemLabelGenerator(
						"{0}:{2}��({3})", new DecimalFormat("#,##0"), 
						new DecimalFormat("#%")
						));
		
		cp = new ChartPanel(chart);
		cp.setMouseWheelEnabled(false);
		cp.setMouseZoomable(true);
		return cp;
	}
}
