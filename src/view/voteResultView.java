package view;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;

import model.userModel;
import model.voteModel;
import model.voteResultModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.Renderer;

import java.awt.FlowLayout;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.PieSectionEntity;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.ui.Layer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.CategoryDataset;

import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.StrokeBorder;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class voteResultView extends JPanel {
//   JTextField tf_summary;
   JTextArea ta_summary;
   JTextArea ta_vote_content;
   
   //[테이블]투표결과 뷰 내 테이블
   JTable table_myFINvote;   
   
   //[테이블]투표결과 뷰 내 테이블 새로고침
   JButton btn_table_renew;
   
   //[테이블]'AbstractTableModel' 상속받은 클래스
   voteFinishTableModel vofintable;   
   voteOptionTableModel voopttable;
   
   //TODO [그래프] 차트 그리는 클래스 
   voteResult_PieChart_View drawPieChart;
   voteResult_SpiderWeb_View drawSpiChart;
   voteResult_BarChart_View drawBarChart;
   
   //[그래프] 투표결과 뷰에서 그래프가 삽입될 J판넬, ChartPanel 
   JPanel pl_graph_pie;
   JPanel pl_graph_spider;
   JPanel pl_graph_stacked;
   
   ChartPanel pnlPieChart=null;
   ChartPanel pnlSpiChart;
   ChartPanel pnlBarChart;
   
   voteResultModel model;
   
//   PiePlot plot;
   Comparable lastKey =null;
   int lastSeries =-1;
   
   //[테이블] 선택지 내용이 들어갈 테이블
   JTable table_option;
   
   /**
    * Create the panel.
    */
   public voteResultView() {
      
      try {
         model = new voteResultModel();
         System.out.println("유저 투표결과 DB 연결 성공");
      } catch (Exception e) {
         JOptionPane.showConfirmDialog(null, "유저 투표결과 DB연결 실패" + e.getMessage());
      }
//추상 테이블 상속하는 
      vofintable = new voteFinishTableModel();
      voopttable = new voteOptionTableModel();
      
      setLayout(null);
      
      JPanel panel = new JPanel();
      panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
      panel.setBounds(12, 8, 386, 166);
      add(panel);
      panel.setLayout(null);
      
      table_myFINvote = new JTable(vofintable);
      JScrollPane scrollPane_1 = new JScrollPane(table_myFINvote);
      scrollPane_1.setBounds(12, 45, 363, 109);
      panel.add(scrollPane_1);

//TODO [테이블] 결과뷰 생성자 함수에 투표종료테이블 붙이기
      selectTable();
      
      JPanel panel_3_1 = new JPanel();
      panel_3_1.setBackground(Color.DARK_GRAY);
      panel_3_1.setBounds(12, 10, 132, 23);
      panel.add(panel_3_1);
      panel_3_1.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("나의투표리스트");
      lblNewLabel.setForeground(Color.WHITE);
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setBounds(0, 1, 132, 23);
      panel_3_1.add(lblNewLabel);
      
      btn_table_renew = new JButton("\uC0C8\uB85C\uACE0\uCE68");
      btn_table_renew.setFont(new Font("돋움", Font.PLAIN, 13));
      btn_table_renew.setBounds(284, 12, 91, 23);
      panel.add(btn_table_renew);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      scrollPane.setBounds(12, 38, 381, 118);
      
      
      JPanel panel_2 = new JPanel();
      panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
      panel_2.setBounds(410, 8, 611, 393);
      add(panel_2);
      panel_2.setLayout(null);
      
      JPanel panel_3_2 = new JPanel();
      panel_3_2.setBackground(Color.DARK_GRAY);
      panel_3_2.setBounds(12, 10, 132, 23);
      panel_2.add(panel_3_2);
      panel_3_2.setLayout(null);
      
      JLabel lblNewLabel_2 = new JLabel("결과분석");
      lblNewLabel_2.setForeground(Color.WHITE);
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setBounds(0, 1, 132, 23);
      panel_3_2.add(lblNewLabel_2);
      
      JDesktopPane desktopPane = new JDesktopPane();
      desktopPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
      desktopPane.setBounds(12, 43, 587, 343);
      panel_2.add(desktopPane);
      
      JInternalFrame IntFra_Pie = new JInternalFrame("총 집계", true, false, true, true);
      desktopPane.setLayer(IntFra_Pie, 0);
      IntFra_Pie.setFrameIcon(new ImageIcon(voteResultView.class.getResource("/image/chart_PIE_icon.png")));
      IntFra_Pie.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//      IntFra_Pie.setIcon(true);
      IntFra_Pie.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
      IntFra_Pie.setBounds(141, 12, 408, 329);
      desktopPane.add(IntFra_Pie);
      IntFra_Pie.getContentPane().setLayout(new BorderLayout(0, 0));
      
      pl_graph_pie = new JPanel();
      IntFra_Pie.getContentPane().add(pl_graph_pie, BorderLayout.CENTER);
      pl_graph_pie.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
      pl_graph_pie.setLayout(new BorderLayout(0, 0));
      
      JInternalFrame IntFra_Spi = new JInternalFrame("나이대 별 선택", true, false, true, true);
      desktopPane.setLayer(IntFra_Spi, 0);
      IntFra_Spi.setFrameIcon(new ImageIcon(voteResultView.class.getResource("/image/chart_Spider_icon.png")));
//      IntFra_Spi.setIcon(true);
      IntFra_Spi.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
      IntFra_Spi.setBounds(12, 12, 262, 240);
      desktopPane.add(IntFra_Spi);
      
      pl_graph_spider = new JPanel();
      IntFra_Spi.getContentPane().add(pl_graph_spider, BorderLayout.CENTER);
      pl_graph_spider.setLayout(new BorderLayout(0, 0));
      
      JInternalFrame IntFra_Stac = new JInternalFrame("성별 당 선택");
      desktopPane.setLayer(IntFra_Stac, 0);
      IntFra_Stac.setFrameIcon(new ImageIcon(voteResultView.class.getResource("/image/chart_BAR_icon.png")));
      IntFra_Stac.setResizable(true);
      IntFra_Stac.setIconifiable(true);
      IntFra_Stac.setMaximizable(true);
      IntFra_Stac.setBounds(12, 113, 262, 228);
      desktopPane.add(IntFra_Stac);
      
      pl_graph_stacked = new JPanel();
      IntFra_Stac.getContentPane().add(pl_graph_stacked, BorderLayout.CENTER);
      pl_graph_stacked.setLayout(new BoxLayout(pl_graph_stacked, BoxLayout.X_AXIS));
      IntFra_Stac.setVisible(true);
      IntFra_Spi.setVisible(true);
      IntFra_Pie.setVisible(true);
      
      JPanel panel_5 = new JPanel();
      panel_5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
      panel_5.setBounds(12, 186, 386, 215);
      add(panel_5);
      panel_5.setLayout(null);
      
      JPanel panel_6 = new JPanel();
      panel_6.setBackground(Color.DARK_GRAY);
      panel_6.setBounds(12, 10, 132, 23);
      panel_5.add(panel_6);
      panel_6.setLayout(null);
      
      JLabel lblNewLabel_1 = new JLabel("투표상세정보");
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1.setForeground(Color.WHITE);
      lblNewLabel_1.setBounds(0, 1, 132, 23);
      panel_6.add(lblNewLabel_1);
      
      JLabel lblNewLabel_5 = new JLabel("\uD22C\uD45C \uB0B4\uC6A9");
      lblNewLabel_5.setBounds(12, 41, 77, 15);
      panel_5.add(lblNewLabel_5);
      
      ta_vote_content = new JTextArea();
      ta_vote_content.setEditable(false);
      ta_vote_content.setLineWrap(true);
      ta_vote_content.setFont(new Font("Monospaced", Font.PLAIN, 12));
      ta_vote_content.setText("\uC644\uB8CC \uD22C\uD45C \uB9AC\uC2A4\uD2B8\uB97C \uC120\uD0DD\uD558\uC138\uC694");
      ta_vote_content.setBounds(12, 60, 145, 90);
      panel_5.add(ta_vote_content);
      
      JLabel lblNewLabel_6 = new JLabel("\uC120\uD0DD\uC9C0 \uB0B4\uC6A9");
      lblNewLabel_6.setBounds(169, 41, 77, 15);
      panel_5.add(lblNewLabel_6);
      
      JLabel lblNewLabel_8 = new JLabel("\uC694\uC57D");
      lblNewLabel_8.setBounds(12, 175, 50, 15);
      panel_5.add(lblNewLabel_8);
      
//      tf_summary = new JTextField();
//      tf_summary.setBounds(59, 165, 350, 30);
//      panel_5.add(tf_summary);
//      tf_summary.setColumns(10);
      
      table_option = new JTable(voopttable);
      JScrollPane scrollPane_2 = new JScrollPane(table_option);
      scrollPane_2.setBounds(169, 60, 207, 90);
      panel_5.add(scrollPane_2);
      
      ta_summary = new JTextArea();
      ta_summary.setLineWrap(true);
      ta_summary.setEditable(false);
      ta_summary.setBounds(57, 160, 319, 49);
      panel_5.add(ta_summary);
      
//이벤트 메서드 실행      
      eventProc();
   }
   
// [테이블] 생성자 함수에 포함시키기 위한 메서드: 투표완료 쿼리
// + 투표종료 탭 눌리자마자 테이블에 리스트 출력하게 하는 메서드
   public void selectTable(){
      ArrayList list = model.voteFINlist();
      vofintable.data = list;
      table_myFINvote.setModel(vofintable);
      vofintable.fireTableDataChanged();   //실시간 변동 반영
   }

   // [선택지들] 테이블 항목 선택되었을 때 선택지 정보와 선택자 수 반환하는 메서드
   public void optionTableModel(int serial) {
      ArrayList lists = model.findVoteInfo(serial);
      voopttable.dataa = lists;
      table_option.setModel(voopttable);
      voopttable.fireTableDataChanged();
   }
   
   // [테이블] 투표 종료된 리스트를 테이블로 만들 때 테이블의 헤더와 구조
   class voteFinishTableModel extends AbstractTableModel {
      ArrayList data = new ArrayList();
      String[] title = { "일련번호", "투표 분류", "투표 제목", "투표 등록일", "투표인 수" };

      public int getColumnCount() {
         return title.length;
      }

      public int getRowCount() {
         return data.size();
      }

      public Object getValueAt(int row , int col) {
         // 값 하나씩 얻어노는 메소드
         ArrayList temp = (ArrayList) data.get(row);
         // get은 오브젝트형으로 변환해줘서 강제 형변화 필요
         return temp.get(col);
      }

      public String getColumnName(int col) {
         // 컬럼 이름 붙이는 이너클래스 매서드
         return title[col];
      }

   }

   //[테이블_선택지] 투표종료 리스트 눌렀을 때 선택지 테이블 헤더와 구조
   class voteOptionTableModel extends AbstractTableModel{
      ArrayList dataa = new ArrayList();
      String[] titlee = { "선택지번호", "내용", "투표인 수" };

      public int getColumnCount() {
         return titlee.length;
      }

      public int getRowCount() {
         return dataa.size();
      }

      public Object getValueAt(int row , int col) {
         ArrayList temp = (ArrayList) dataa.get(row);
         return temp.get(col);
      }

      public String getColumnName(int col) {
         return titlee[col];
      }
   }

   
// TODO 이벤트 붙이기
   public void eventProc() {
      // 1. [테이블] 마우스로 투표 완료 항목 선택했을 때
      table_myFINvote.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            int row = table_myFINvote.getSelectedRow();
            int col = 0;
            int voteSerial = (int) (table_myFINvote.getValueAt(row, col));
            int vote_count = (int) (table_myFINvote.getValueAt(row, 4));
            
            // 선택한 행의 일련번호로 옵션 내용과 선택한 사람 수 반환
            optionTableModel(voteSerial);
            // 선택한 행의 일련번호로 투표 내용 작성 (일단 한 줄 String으로)
            ta_vote_content.setText(model.findVoteContent(voteSerial));
            
            // 선택한 행의 요약문을 작성
            ta_summary.setText(model.Prod_summary(vote_count, model.findRangePop(voteSerial), 95));
            
            
            // [그래프] (이벤트 수행 전에 그래프가 있다면) 그래프 일단 지우기
            if ( (pnlPieChart != null)||(pnlBarChart != null)||(pnlSpiChart != null) ) {
               pl_graph_pie.remove(pnlPieChart);
               pl_graph_spider.remove(pnlSpiChart);
               pl_graph_stacked.remove(pnlBarChart);
            }

            // [그래프] 선택한 항목에 대한 그래프 작도
            String title = (String) (table_myFINvote.getValueAt(row, 2));
            
            // [그래프] 파이차트 객체 생성
            drawPieChart = new voteResult_PieChart_View(title, voteSerial);
            pnlPieChart = drawPieChart.createChart();
            
            // [그래프] 방사형 차트 객체 생성
            drawSpiChart = new voteResult_SpiderWeb_View(title, voteSerial);
            pnlSpiChart = drawSpiChart.creatChart();
            
            // [그래프] 누적막대그래프 객체 생성
            drawBarChart = new voteResult_BarChart_View(title, voteSerial);
            pnlBarChart = drawBarChart.creatChart();
            
            // 스윙 판넬에 붙이고 활성화
            pl_graph_pie.add(pnlPieChart);
            pnlPieChart.validate();
            
            pl_graph_spider.add(pnlSpiChart);
            pnlSpiChart.validate();
            
            pl_graph_stacked.add(pnlBarChart);
            pnlBarChart.validate();
            
            // TODO [선택지 테이블 이벤트] 선택지 클릭했을 때 차드들의 해당 부분 하이라이트
            table_option.addMouseListener(new MouseAdapter() {

               @Override
               public void mouseClicked(MouseEvent e) {
//                  super.mouseClicked(e);
                  int row_o = table_option.getSelectedRow();
                  
                  String voteOptContent = (String) (table_option.getValueAt(row_o, 1));
                     //선택지 중 하나를 클릭하면 vote_content 내용이 참조된다
                                    
                  if (voteOptContent != null) {
                     
                     // 원래 페인트 값, 스트로크 굵기 저장해놓음
                     Paint pie_last_paint = drawPieChart.plot.getSectionOutlinePaint(voteOptContent);
                     Stroke pie_last_stroke = drawPieChart.plot.getSectionOutlineStroke(voteOptContent);
                     
                     Paint spi_last_paint = drawSpiChart.plot.getSeriesOutlinePaint(row_o);
                     Stroke spi_last_stroke = drawSpiChart.plot.getSeriesOutlineStroke(row_o);
                     
                     // 바 차트용 강조 마커 
                     
                     // 직전에 하이라이트된 차트 항목이 있다면 초기화
                     if (lastKey != null ||lastSeries != -1) {
                        drawPieChart.plot.setExplodePercent(lastKey, 0);
                        drawPieChart.plot.setSectionOutlinePaint(lastKey, pie_last_paint);
                        drawPieChart.plot.setSectionOutlineStroke(lastKey, pie_last_stroke);
                        
                        drawBarChart.plot.clearDomainMarkers();
                        
                        drawSpiChart.plot.setSeriesOutlineStroke(lastSeries, spi_last_stroke);
                        drawSpiChart.plot.setSeriesOutlinePaint(lastSeries, spi_last_paint);
                     }
                     //1. 파이 차트의 section이 하이라이트 되게
                     drawPieChart.plot.setExplodePercent(voteOptContent, 0.10);
                     drawPieChart.plot.setSectionOutlinePaint(voteOptContent, Color.BLACK);
                     drawPieChart.plot.setSectionOutlineStroke(voteOptContent, new BasicStroke(3f));
                     
                     
                     //2. Stacked bar 차트의 컬럼이 하이라이트 되게
                     CategoryMarker marker = new CategoryMarker (voteOptContent, new Color(0, 0, 255, 25), new BasicStroke(1.0F));
                     marker.setDrawAsLine(false);
                     marker.setAlpha(1.0F);
                     marker.setLabel(voteOptContent);
                     marker.setLabelFont(new Font("돋움", 0, 11));
                     marker.setLabelTextAnchor(TextAnchor.TOP_CENTER);
                     marker.setLabelOffset(
                           new RectangleInsets(2.0D, 5.0D, 2.0D, 5.0D));
                     drawBarChart.plot.addDomainMarker(marker, Layer.BACKGROUND);
                     
                     //3. Spider 차트의 영역이 하이라이트 되게
                     drawSpiChart.plot.setSeriesOutlineStroke(row_o, new BasicStroke(3f));
//                     drawSpiChart.plot.setSeriesOutlinePaint(row_o, Color.BLACK);
                     
                     //전역변수에 Comparable Key 값 저장
                     lastKey = voteOptContent;
                     lastSeries = row_o;
                  }
               }
               
            });
            
            // 생성된 판넬 객체에 이벤트 붙이기
//            pnlPieChart.addChartMouseListener(new ChartMouseListener() {
//
//               public void chartMouseMoved(ChartMouseEvent event) {
//                  // 마우스가 파이 섹션 위로 올라갔을 때 섹션 빠져나오기
//                  PiePlot plot = drawPieChart.plot;
//                  ChartEntity entity = event.getEntity();
//                  if (entity instanceof PieSectionEntity) {
//                     PieSectionEntity psec = (PieSectionEntity) entity;
//                     if (entity != null) {
//                        if (lastKey != null) {
//                           plot.setExplodePercent(lastKey, 0);
//                        }
//                        Comparable key = psec.getSectionKey();
//                        plot.setExplodePercent(key, 0.10);
//                        lastKey = key;
//                     }
//                  }
//               }
//
//               public void chartMouseClicked(ChartMouseEvent ceven)  {
//                  // 마우스가 파이 섹션 클릭했을 때 ...
//                  // ChartEntity entity = ceven.getEntity();
//                  // if (entity != null) {
//                  // System.out.println("Mouse clicked: " + entity.toString());
//                  // } else {
//                  // System.out.println("Mouse clicked: null entity.");
//               }
//            });

         }

      });

      //2. [테이블] 테이블 새로고침 버튼 선택했을 때
      btn_table_renew.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            selectTable();
         }
      });
   }
}