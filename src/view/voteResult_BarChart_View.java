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

	//성별 선택지 선택 내역 누적 막대그래프
	
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
				"투표인 수", dataset, PlotOrientation.VERTICAL, false, false, false);
		
		chart.setBackgroundPaint(Color.white);
		chart.getTitle().setFont(new Font("돋움", Font.PLAIN, 20));
//		chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 15));
		
		
		plot = (CategoryPlot) chart.getPlot();
		plot.getDomainAxis().setTickLabelFont(new Font("돋움", Font.PLAIN, 15));
		
		plot.getRangeAxis().setLabelFont(new Font("돋움", Font.BOLD, 12));
		
//		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);
		
		plot.setNoDataMessage("성별 데이터 없음");
		plot.setNoDataMessageFont(new Font("돋움", Font.BOLD, 30));
		
		StackedBarRenderer stackedbarrenderer = (StackedBarRenderer) plot.getRenderer();
		
		
		stackedbarrenderer.setDrawBarOutline(false);
		stackedbarrenderer.setMaximumBarWidth(0.10);
		stackedbarrenderer.setItemMargin(0.6);
		
		stackedbarrenderer.setDefaultItemLabelsVisible(true);
		stackedbarrenderer.setDefaultItemLabelFont(new Font("돋움", Font.PLAIN, 12));
		// 바 차트보다 라벨 넓어도 표현되도록
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER,
				TextAnchor.CENTER, TextAnchor.CENTER, 0.0D);
		stackedbarrenderer.setPositiveItemLabelPositionFallback(position);
		stackedbarrenderer.setDefaultItemLabelGenerator(
				new StandardCategoryItemLabelGenerator(
						"{0}:{2}명({3})", new DecimalFormat("#,##0"), 
						new DecimalFormat("#%")
						));
		
		cp = new ChartPanel(chart);
		cp.setMouseWheelEnabled(false);
		cp.setMouseZoomable(true);
		return cp;
	}
}
