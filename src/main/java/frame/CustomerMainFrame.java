package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import compent.MaterialOptionPane;
import compent.MyJScrollBar;
import compent.NoBorderJFrame;
import entity.Customer;
import entity.Produce;
import entity.Trade;
import entity.vo.FeedbackVo;
import entity.vo.TradeVo;
import factory.ServiceFactory;
import frame.runnable.OneTalkThread;
import frame.runnable.TimeThread;
import mdlaf.components.label.MaterialLabelUI;
import sun.swing.table.DefaultTableCellHeaderRenderer;
import utils.SwingUtil;

/**
 * @ClassName CustomerMainFrame
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
public class CustomerMainFrame extends NoBorderJFrame {

    private static final Color COLOR_CYAN = new Color(83, 109, 254);
    private static final Color COLOR_WHITE = new Color(255, 255, 255);
    int xOld = 0;
    int yOld = 0;
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel centerPanel;
    private JButton 基本信息Button;
    private JButton 订购产品Button;
    private JButton 消费记录Button;
    private JButton 产品反馈Button;
    private JPanel feedbackInfoPanel;
    private JPanel tradeInfoPanel;
    private JPanel customerInfoPanel;
    private JPanel employeeInfoPanel;
    private JPanel logoPanel;
    private JPanel containerPanel;
    private JPanel toolbar;
    private JButton minBtn;
    private JButton exitBtn;
    private JLabel mainTitle;
    private JLabel oneTalkLabel;
    private JLabel timeLabel;
    private JScrollPane scrollPanel;
    private JPanel contentPanel;
    private JPanel tablePanel;
    private JPanel tradeTablePanel;
    private CardLayout cardLayout;
    private Customer mCustomer;

    public CustomerMainFrame(String title) {
        this.toFront();
        this.mCustomer = Customer.builder().customerId(1).address("江苏省南京市栖霞区羊山北路一号").name("唐钱进").credit(90).phone("13861948872").employeeId(1).userId(1).build();
        mainTitle.setText("   CRM SYSTEM " + title);
        oneTalkLabel.setText(title);
        OneTalkThread oneTalkThread = new OneTalkThread();
        oneTalkThread.setOneTalkLabel(oneTalkLabel);
        new Thread(oneTalkThread).start();

        TimeThread timeThread = new TimeThread();
        timeThread.setTimeLabel(timeLabel);
        new Thread(timeThread).start();

        initComponent();
    }

    public CustomerMainFrame(String title, Customer customer) {
        this.toFront();
        this.mCustomer = customer;
        mainTitle.setText("   CRM SYSTEM " + title);
        oneTalkLabel.setText(title);
        OneTalkThread oneTalkThread = new OneTalkThread();
        oneTalkThread.setOneTalkLabel(oneTalkLabel);
        new Thread(oneTalkThread).start();

        TimeThread timeThread = new TimeThread();
        timeThread.setTimeLabel(timeLabel);
        new Thread(timeThread).start();

        initComponent();
    }

    public static void main(String[] args) {
        new CustomerMainFrame("客户:唐钱进");
    }

    public void initSideTabMenu() {
        cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        centerPanel.add("1", employeeInfoPanel);
        centerPanel.add("2", customerInfoPanel);
        centerPanel.add("3", tradeInfoPanel);
        centerPanel.add("4", feedbackInfoPanel);

        基本信息Button.setIcon(new ImageIcon("img/icon1blue.png"));
        订购产品Button.setIcon(new ImageIcon("img/icon2white.png"));
        消费记录Button.setIcon(new ImageIcon("img/icon3white.png"));
        产品反馈Button.setIcon(new ImageIcon("img/icon4white.png"));
        基本信息Button.addActionListener(e -> {
            基本信息Button.setIcon(new ImageIcon("img/icon1blue.png"));
            订购产品Button.setIcon(new ImageIcon("img/icon2white.png"));
            消费记录Button.setIcon(new ImageIcon("img/icon3white.png"));
            产品反馈Button.setIcon(new ImageIcon("img/icon4white.png"));
            基本信息Button.setForeground(COLOR_CYAN);
            基本信息Button.setBackground(COLOR_WHITE);
            订购产品Button.setForeground(COLOR_WHITE);
            订购产品Button.setBackground(COLOR_CYAN);
            消费记录Button.setForeground(COLOR_WHITE);
            消费记录Button.setBackground(COLOR_CYAN);
            产品反馈Button.setForeground(COLOR_WHITE);
            产品反馈Button.setBackground(COLOR_CYAN);
            cardLayout.show(centerPanel, "1");
        });
        订购产品Button.addActionListener(e -> {
            基本信息Button.setIcon(new ImageIcon("img/icon1white.png"));
            订购产品Button.setIcon(new ImageIcon("img/icon2blue.png"));
            消费记录Button.setIcon(new ImageIcon("img/icon3white.png"));
            产品反馈Button.setIcon(new ImageIcon("img/icon4white.png"));
            基本信息Button.setForeground(COLOR_WHITE);
            基本信息Button.setBackground(COLOR_CYAN);
            订购产品Button.setForeground(COLOR_CYAN);
            订购产品Button.setBackground(COLOR_WHITE);
            消费记录Button.setForeground(COLOR_WHITE);
            消费记录Button.setBackground(COLOR_CYAN);
            产品反馈Button.setForeground(COLOR_WHITE);
            产品反馈Button.setBackground(COLOR_CYAN);
            cardLayout.show(centerPanel, "2");
            showProduces();
        });
        消费记录Button.addActionListener(e -> {
            基本信息Button.setIcon(new ImageIcon("img/icon1white.png"));
            订购产品Button.setIcon(new ImageIcon("img/icon2white.png"));
            消费记录Button.setIcon(new ImageIcon("img/icon3blue.png"));
            产品反馈Button.setIcon(new ImageIcon("img/icon4white.png"));
            基本信息Button.setForeground(COLOR_WHITE);
            基本信息Button.setBackground(COLOR_CYAN);
            订购产品Button.setForeground(COLOR_WHITE);
            订购产品Button.setBackground(COLOR_CYAN);
            消费记录Button.setForeground(COLOR_CYAN);
            消费记录Button.setBackground(COLOR_WHITE);
            产品反馈Button.setForeground(COLOR_WHITE);
            产品反馈Button.setBackground(COLOR_CYAN);
            cardLayout.show(centerPanel, "3");
            showTrades(ServiceFactory.getTradeServiceInstance().selectTradeByCustomerId(this.mCustomer.getCustomerId()));
        });
        产品反馈Button.addActionListener(e -> {
            基本信息Button.setIcon(new ImageIcon("img/icon1white.png"));
            订购产品Button.setIcon(new ImageIcon("img/icon2white.png"));
            消费记录Button.setIcon(new ImageIcon("img/icon3white.png"));
            产品反馈Button.setIcon(new ImageIcon("img/icon4blue.png"));
            基本信息Button.setForeground(COLOR_WHITE);
            基本信息Button.setBackground(COLOR_CYAN);
            订购产品Button.setForeground(COLOR_WHITE);
            订购产品Button.setBackground(COLOR_CYAN);
            消费记录Button.setForeground(COLOR_WHITE);
            消费记录Button.setBackground(COLOR_CYAN);
            产品反馈Button.setForeground(COLOR_CYAN);
            产品反馈Button.setBackground(COLOR_WHITE);
            cardLayout.show(centerPanel, "4");
            showFeedbacks(ServiceFactory.getFeedbackServiceInstance().selectFeedbackByCustomerId(mCustomer.getCustomerId()));
        });
    }

    public void initComponent() {
        this.setUndecorated(true);
        this.setTitle("MainFrame");
        this.setContentPane(mainPanel);
        this.setLocation(500, 100);
        this.setSize(1080, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOld = e.getX();
                yOld = e.getY();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                CustomerMainFrame.this.setLocation(xx, yy);
            }
        });

        this.setVisible(true);

        initSideTabMenu();

        minBtn.addActionListener(actionEvent -> {
            this.setExtendedState(JFrame.ICONIFIED);
        });
        exitBtn.addActionListener(actionEvent -> {
            this.dispose();
        });
        scrollPanel.getVerticalScrollBar().setUnitIncrement(30);
        scrollPanel.getVerticalScrollBar().setBackground(new Color(255, 255, 255));
        scrollPanel.getVerticalScrollBar().setUI(new MyJScrollBar());
    }

    private void showProduces() {
        contentPanel.removeAll();
        List<Produce> produceList = ServiceFactory.getProduceServiceInstance().selectAllProduce();
        int len = produceList.size();
        int row = len % 3 == 0 ? len / 3 : len / 3 + 1;
        GridLayout gridLayout = new GridLayout(row, 3, 15, 15);
        contentPanel.setLayout(gridLayout);
        List<JLabel> jLabels = new ArrayList<>();
        List<String> jLabelsText = new ArrayList<>();
        for (Produce produce : produceList) {
            JPanel depPanel = new JPanel();
            depPanel.setLayout(new BorderLayout());
            depPanel.setPreferredSize(new Dimension(200, 400));
            TitledBorder border = BorderFactory.createTitledBorder(produce.getName());
            border.setTitleFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
            depPanel.setBorder(border);
            JLabel logoLabel = new JLabel();
            jLabels.add(logoLabel);
            jLabelsText.add("<html><head><meta charset=\"UTF-8\"><title>Title</title></head><body><img src='" + produce.getProduceImg() + "' style='transform:scale(0.5);width:100%;height:auto;'/></body></html>");

            JButton tradeBtn = new JButton("订购");
            tradeBtn.setForeground(Color.WHITE);
            tradeBtn.setFocusPainted(false);
            tradeBtn.setBorderPainted(false);
            tradeBtn.setBackground(new Color(83, 109, 254));
            tradeBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
            tradeBtn.setPreferredSize(new Dimension(135, 30));
            tradeBtn.addActionListener(i -> {
                MaterialOptionPane.showMessageDialog("确认购买商品 " + produce.getName() + " x 1 吗？", new MaterialDialog.Callback() {
                    @Override
                    public void onConfirm() {
                        MaterialOptionPane.showMessageDialog(ServiceFactory.getTradeServiceInstance().insertTrade(
                                Trade.builder()
                                        .customerId(mCustomer.getCustomerId())
                                        .produceId(produce.getProduceId())
                                        .amount(produce.getPrice())
                                        .produceNum(1)
                                        .tradeTime(new Date(System.currentTimeMillis()))
                                        .build()
                        )?"购买成功":"购买失败");
                    }

                    @Override
                    public void onCancel() {
                        MaterialOptionPane.showMessageDialog("交易已被取消");
                    }
                });
            });

            JButton feedbackBtn = new JButton("反馈");
            feedbackBtn.setForeground(Color.WHITE);
            feedbackBtn.setFocusPainted(false);
            feedbackBtn.setBorderPainted(false);
            feedbackBtn.setBackground(new Color(244, 67, 54));
            feedbackBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
            feedbackBtn.setPreferredSize(new Dimension(110, 30));
            feedbackBtn.addActionListener(i -> {
                //todo 反馈Dialog
                MaterialOptionPane.showFeedbackDialog(mCustomer.getCustomerId(),produce.getProduceId(),new MaterialFeedbackDialog.Callback() {
                    @Override
                    public void onSubmitSuccess() {
                        MaterialOptionPane.showMessageDialog("反馈提交成功");
                    }

                    @Override
                    public void onSubmitFailure() {
                        MaterialOptionPane.showMessageDialog("反馈提交失败");
                    }

                    @Override
                    public void onSubmitCanel() {
                        System.out.println("取消反馈");
                    }
                });
            });

            JPanel btnPanel = new JPanel();
            btnPanel.setBackground(new Color(249, 249, 249));
            FlowLayout flowLayout = new FlowLayout();
            flowLayout.setHgap(0);
            flowLayout.setVgap(0);
            btnPanel.setLayout(flowLayout);
            btnPanel.add(tradeBtn);
            btnPanel.add(feedbackBtn);
            depPanel.add(btnPanel, BorderLayout.SOUTH);
            depPanel.add(logoLabel, BorderLayout.CENTER);
            depPanel.setBackground(new Color(249, 249, 249));
            contentPanel.add(depPanel);
            contentPanel.revalidate();
        }
        int count = 0;
        boolean isWeakNet = false;
        for (JLabel j : jLabels) {
            try {
                j.setText(jLabelsText.get(count));
            } catch (Exception e) {
                isWeakNet = true;
                ImageIcon icon = new ImageIcon("img/picgone.png");
                j.setPreferredSize(new Dimension(150, 150));
                j.setIcon(SwingUtil.createAutoAdjustIcon(icon.getImage(), false));
                j.setText("");
            }
            count++;
        }
        if (isWeakNet) {
            System.err.println("当前网络环境：弱网 图片加载失败");
            MaterialOptionPane.showMessageDialog("当前网络环境：弱网 图片加载失败");
        }
    }

    private void showTrades(List<TradeVo> tradeVoList) {
        tradeTablePanel.removeAll();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.setColumnIdentifiers(new String[]{"订单ID", "订单内容", "订单金额", "订购数量", "交易时间"});
        for (TradeVo trade : tradeVoList) {
            Object[] object = new Object[]{trade.getTradeId(),trade.getProduceName(),trade.getAmount(),trade.getProduceNum(),trade.getTradeTime()};
            model.addRow(object);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        //获得表格的表头
        JTableHeader header = table.getTableHeader();
        //表头居中
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        //设置表头字体
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("微软雅黑", Font.PLAIN, 19));
        //设置表格行高
        table.setRowHeight(40);
        //表格内容居中
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        defaultTableCellRenderer.setBackground(Color.WHITE);
        defaultTableCellRenderer.setUI(new MaterialLabelUI());
        defaultTableCellRenderer.setFont(new Font("微软雅黑", Font.PLAIN, 19));
        table.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        //表格加入滚动面板，并设置水平和垂直方向可按需滚动
        JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUI(new MyJScrollBar(new Color(200, 200, 200)));
        scrollPane.getHorizontalScrollBar().setUI(new MyJScrollBar(new Color(200, 200, 200)));
        tradeTablePanel.add(scrollPane);
        tradeTablePanel.revalidate();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //表格 的rowAtPoint方法返回坐标所在的行号，参数为坐标类型，
                    int i = table.rowAtPoint(e.getPoint());
                    TradeVo trade = tradeVoList.get(i);
                    //todo
                }
            }
        });

        //表格内容监听，根据点击的行得到不同的数据
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            try {
                TradeVo trade = tradeVoList.get(row);
                System.out.println(trade);
                //todo
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    private void showFeedbacks(List<FeedbackVo> feedbackList) {
        tablePanel.removeAll();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.setColumnIdentifiers(new String[]{"工单ID", "处理人员", "工单内容", "创建时间", "工单状态"});
        for (FeedbackVo feedback : feedbackList) {
            Object[] object = new Object[]{feedback.getFeedbackId(),feedback.getEmployeeName(),feedback.getContent(),feedback.getCreateTime().toString(),feedback.getDealStatus().getDesc()};
            model.addRow(object);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(340);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        //获得表格的表头
        JTableHeader header = table.getTableHeader();
        //表头居中
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        //设置表头字体
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        header.setFont(new Font("微软雅黑", Font.PLAIN, 19));
        //设置表格行高
        table.setRowHeight(40);
        //表格内容居中
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        defaultTableCellRenderer.setBackground(Color.WHITE);
        defaultTableCellRenderer.setUI(new MaterialLabelUI());
        defaultTableCellRenderer.setFont(new Font("微软雅黑", Font.PLAIN, 19));
        table.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        //表格加入滚动面板，并设置水平和垂直方向可按需滚动
        JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUI(new MyJScrollBar(new Color(200, 200, 200)));
        scrollPane.getHorizontalScrollBar().setUI(new MyJScrollBar(new Color(200, 200, 200)));
        tablePanel.add(scrollPane);
        tablePanel.revalidate();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //表格 的rowAtPoint方法返回坐标所在的行号，参数为坐标类型，
                    int i = table.rowAtPoint(e.getPoint());
                    FeedbackVo feedback = feedbackList.get(i);
                    //todo
                }
            }
        });

        //表格内容监听，根据点击的行得到不同的数据
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            try {
                FeedbackVo  feedback = feedbackList.get(row);
                System.out.println(feedback);
                //todo
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}
