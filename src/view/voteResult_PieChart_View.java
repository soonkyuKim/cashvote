package view;

import java.awt.Font;
import java.text.DecimalFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

import model.chartModel;

public class voteResult_PieChart_View {
/*
 * (파이) 차트를 그리는 클래스
 */
 
	String chartTitle;
	PieDataset dataset;
	PiePlot plot;
	chartModel charmodel = new chartModel();
	
	public voteResult_PieChart_View() {
	}
	
	public voteResult_PieChart_View(String title, int VoteSerial) {
		dataset = charmodel.JDBC_Pie_dataset(VoteSerial);
		chartTitle = title;
	}

	public ChartPanel createChart() {

		ChartPanel cp;
		
		JFreeChart chart = ChartFactory.createPieChart(chartTitle, // chart title
				dataset, // data
				true, // include legend
				true, false);
		chart.getTitle().setFont(new Font("돋움", Font.BOLD, 20));
		chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 15));

		plot = (PiePlot) chart.getPlot();

		//투명도 50%
		plot.setForegroundAlpha(0.5f);

		//차트에 아무 데이터도 없을 때 출력하는 문장
		plot.setNoDataMessage("데이터 없음");

        plot.setIgnoreNullValues(true);	//null 값은 차트에 그리지 않는다   
        plot.setIgnoreZeroValues(false);   // 0 값은 차트에 언급은 된다
		
        //파이차트 라벨 형식
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
	            "{0} : {1}명 ({2})", new DecimalFormat("#,##0"), new DecimalFormat("0.#%"));
		plot.setLabelGenerator(gen);
        
		plot.setLabelFont(new Font("돋움", Font.PLAIN, 15));
		
		cp = new ChartPanel(chart);
		cp.setMouseWheelEnabled(true);
		cp.setMouseZoomable(true);
		cp.setMouseZoomable(true, true);
		
		return cp;
	}


}
