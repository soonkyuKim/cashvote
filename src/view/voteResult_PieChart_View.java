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
 * (����) ��Ʈ�� �׸��� Ŭ����
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
		chart.getTitle().setFont(new Font("����", Font.BOLD, 20));
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 15));

		plot = (PiePlot) chart.getPlot();

		//���� 50%
		plot.setForegroundAlpha(0.5f);

		//��Ʈ�� �ƹ� �����͵� ���� �� ����ϴ� ����
		plot.setNoDataMessage("������ ����");

        plot.setIgnoreNullValues(true);	//null ���� ��Ʈ�� �׸��� �ʴ´�   
        plot.setIgnoreZeroValues(false);   // 0 ���� ��Ʈ�� ����� �ȴ�
		
        //������Ʈ �� ����
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
	            "{0} : {1}�� ({2})", new DecimalFormat("#,##0"), new DecimalFormat("0.#%"));
		plot.setLabelGenerator(gen);
        
		plot.setLabelFont(new Font("����", Font.PLAIN, 15));
		
		cp = new ChartPanel(chart);
		cp.setMouseWheelEnabled(true);
		cp.setMouseZoomable(true);
		cp.setMouseZoomable(true, true);
		
		return cp;
	}


}
