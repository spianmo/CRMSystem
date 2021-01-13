package frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import compent.JTextFieldHintListener;
import compent.MaterialOptionPane;
import compent.MyJScrollBar;
import compent.NoBorderJFrame;
import compent.RoundBorder;
import entity.Customer;
import entity.Employee;
import entity.Feedback;
import entity.Task;
import entity.vo.FeedbackVo;
import factory.ServiceFactory;
import frame.runnable.OneTalkThread;
import frame.runnable.TimeThread;
import mdlaf.components.label.MaterialLabelUI;
import mdlaf.components.popupmenu.MaterialPopupMenuUI;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 * @ClassName CustomerMainFrame
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
public class EmployeeMainFrame extends NoBorderJFrame {

    private static final Color COLOR_CYAN = new Color(83, 109, 254);
    private static final Color COLOR_WHITE = new Color(255, 255, 255);
    int xOld = 0;
    int yOld = 0;
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel centerPanel;
    private JButton 员工信息Button;
    private JButton 客户信息Button;
    private JButton 计划管理Button;
    private JButton 反馈处理Button;
    private JPanel feedbackDealPanel;
    private JPanel taskManagePanel;
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
    private JPanel tablePanel;
    private CardLayout cardLayout;
    private JTextField searchCustomerField;
    private JButton 搜索CustomerButton;
    private JButton 新增计划Button;
    private JPanel taskTablePanel;
    private JPanel feedbackTablePanel;
    private Employee mEmployee;

    public EmployeeMainFrame(String title, Employee employee) {
        this.toFront();
        this.mEmployee = employee;
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

    public EmployeeMainFrame(String title) {
        this.toFront();
        this.mEmployee = Employee.builder()
                .employeeId(1)
                .departmentId(1)
                .name("kirito")
                .produceType("主机类")
                .salary(new BigDecimal("24000"))
                .userId(1).build();
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
        new EmployeeMainFrame("员工:kirito");
    }

    public void initSideTabMenu() {
        cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        centerPanel.add("1", employeeInfoPanel);
        centerPanel.add("2", customerInfoPanel);
        centerPanel.add("3", taskManagePanel);
        centerPanel.add("4", feedbackDealPanel);

        员工信息Button.setIcon(new ImageIcon("img/icon1blue.png"));
        客户信息Button.setIcon(new ImageIcon("img/icon2white.png"));
        计划管理Button.setIcon(new ImageIcon("img/icon3white.png"));
        反馈处理Button.setIcon(new ImageIcon("img/icon4white.png"));
        员工信息Button.addActionListener(e -> {
            员工信息Button.setIcon(new ImageIcon("img/icon1blue.png"));
            客户信息Button.setIcon(new ImageIcon("img/icon2white.png"));
            计划管理Button.setIcon(new ImageIcon("img/icon3white.png"));
            反馈处理Button.setIcon(new ImageIcon("img/icon4white.png"));
            员工信息Button.setForeground(COLOR_CYAN);
            员工信息Button.setBackground(COLOR_WHITE);
            客户信息Button.setForeground(COLOR_WHITE);
            客户信息Button.setBackground(COLOR_CYAN);
            计划管理Button.setForeground(COLOR_WHITE);
            计划管理Button.setBackground(COLOR_CYAN);
            反馈处理Button.setForeground(COLOR_WHITE);
            反馈处理Button.setBackground(COLOR_CYAN);
            cardLayout.show(centerPanel, "1");
        });
        客户信息Button.addActionListener(e -> {
            员工信息Button.setIcon(new ImageIcon("img/icon1white.png"));
            客户信息Button.setIcon(new ImageIcon("img/icon2blue.png"));
            计划管理Button.setIcon(new ImageIcon("img/icon3white.png"));
            反馈处理Button.setIcon(new ImageIcon("img/icon4white.png"));
            员工信息Button.setForeground(COLOR_WHITE);
            员工信息Button.setBackground(COLOR_CYAN);
            客户信息Button.setForeground(COLOR_CYAN);
            客户信息Button.setBackground(COLOR_WHITE);
            计划管理Button.setForeground(COLOR_WHITE);
            计划管理Button.setBackground(COLOR_CYAN);
            反馈处理Button.setForeground(COLOR_WHITE);
            反馈处理Button.setBackground(COLOR_CYAN);
            cardLayout.show(centerPanel, "2");

            showCustomers(ServiceFactory.getCustomerServiceInstance().selectCustomerByEmployeeId(this.mEmployee.getEmployeeId()));
        });
        计划管理Button.addActionListener(e -> {
            员工信息Button.setIcon(new ImageIcon("img/icon1white.png"));
            客户信息Button.setIcon(new ImageIcon("img/icon2white.png"));
            计划管理Button.setIcon(new ImageIcon("img/icon3blue.png"));
            反馈处理Button.setIcon(new ImageIcon("img/icon4white.png"));
            员工信息Button.setForeground(COLOR_WHITE);
            员工信息Button.setBackground(COLOR_CYAN);
            客户信息Button.setForeground(COLOR_WHITE);
            客户信息Button.setBackground(COLOR_CYAN);
            计划管理Button.setForeground(COLOR_CYAN);
            计划管理Button.setBackground(COLOR_WHITE);
            反馈处理Button.setForeground(COLOR_WHITE);
            反馈处理Button.setBackground(COLOR_CYAN);
            cardLayout.show(centerPanel, "3");
            showTasks(ServiceFactory.getTaskServiceInstance().selectTaskByEmployeeId(this.mEmployee.getEmployeeId()));
        });
        反馈处理Button.addActionListener(e -> {
            员工信息Button.setIcon(new ImageIcon("img/icon1white.png"));
            客户信息Button.setIcon(new ImageIcon("img/icon2white.png"));
            计划管理Button.setIcon(new ImageIcon("img/icon3white.png"));
            反馈处理Button.setIcon(new ImageIcon("img/icon4blue.png"));
            员工信息Button.setForeground(COLOR_WHITE);
            员工信息Button.setBackground(COLOR_CYAN);
            客户信息Button.setForeground(COLOR_WHITE);
            客户信息Button.setBackground(COLOR_CYAN);
            计划管理Button.setForeground(COLOR_WHITE);
            计划管理Button.setBackground(COLOR_CYAN);
            反馈处理Button.setForeground(COLOR_CYAN);
            反馈处理Button.setBackground(COLOR_WHITE);
            cardLayout.show(centerPanel, "4");
            showFeedbacks(ServiceFactory.getFeedbackServiceInstance().selectFeedbackByEmployeeId(this.mEmployee.getEmployeeId()));
        });
    }

    private void showCustomers(List<Customer> customerList) {
        //获得学生列表
        //创建表格对象
        tablePanel.removeAll();
        JTable table = new JTable();
        //创建表格数据模型，并设置给表格
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.setColumnIdentifiers(new String[]{"客户编号", "姓名", "住址", "信用值", "手机号"});
        //遍历list，生成Object数组，数组中的每一个元素就是一行记录
        for (Customer customer : customerList) {
            Object[] object = new Object[]{customer.getCustomerId(), customer.getName(), customer.getAddress(),customer.getCredit(),customer.getPhone()};
            model.addRow(object);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
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
                    Customer customer = customerList.get(i);
                    //todo
                }
            }
        });

        //表格内容监听，根据点击的行得到不同的数据
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            try {
                Customer customer = customerList.get(row);
                System.out.println(customer);
                //todo
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    private void showTasks(List<Task> taskList) {
        //获得学生列表
        //创建表格对象
        taskTablePanel.removeAll();
        JTable table = new JTable();
        //创建表格数据模型，并设置给表格
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.setColumnIdentifiers(new String[]{"计划编号", "计划详情", "目标客户人数", "计划状态", "计划时间"});
        //遍历list，生成Object数组，数组中的每一个元素就是一行记录
        for (Task task : taskList) {
            Object[] object = new Object[]{task.getTaskId(), task.getTaskDesc(), task.getCustomerNum() ,task.getTaskStatus(),task.getTaskTime()};
            model.addRow(object);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
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
        taskTablePanel.add(scrollPane);
        taskTablePanel.revalidate();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //表格 的rowAtPoint方法返回坐标所在的行号，参数为坐标类型，
                    int i = table.rowAtPoint(e.getPoint());
                    Task task = taskList.get(i);

                }
            }
        });

        //表格内容监听，根据点击的行得到不同的数据
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            try {
                Task task = taskList.get(row);
                System.out.println(task);
                //todo
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    private void showFeedbacks(List<FeedbackVo> feedbackList) {
        //获得学生列表
        //创建表格对象
        feedbackTablePanel.removeAll();
        JTable table = new JTable();
        //创建表格数据模型，并设置给表格
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.setColumnIdentifiers(new String[]{"工单ID", "工单内容", "关联产品", "发起客户", "处理状态", "创建时间"});
        //遍历list，生成Object数组，数组中的每一个元素就是一行记录
        for (FeedbackVo feedback : feedbackList) {
            Object[] object = new Object[]{feedback.getFeedbackId(), feedback.getContent(), feedback.getProduceName() ,feedback.getCustomerName(),feedback.getDealStatus().getDesc(),feedback.getCreateTime()};
            model.addRow(object);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(260);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(140);
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
        feedbackTablePanel.add(scrollPane);
        feedbackTablePanel.revalidate();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //表格 的rowAtPoint方法返回坐标所在的行号，参数为坐标类型，
                    int i = table.rowAtPoint(e.getPoint());
                    FeedbackVo feedback = feedbackList.get(i);
                    Feedback feedback1 = ServiceFactory.getFeedbackServiceInstance().selectFeedbackById(feedback.getFeedbackId());
                    JPopupMenu jPopupMenu = new JPopupMenu();
                    jPopupMenu.setUI(new MaterialPopupMenuUI());
                    jPopupMenu.setBorder(new RoundBorder(Color.LIGHT_GRAY));
                    JMenuItem item1 = new JMenuItem("处理中");
                    item1.setFont(new Font("微软雅黑", Font.PLAIN, 19));
                    item1.addActionListener(e1 -> {
                        feedback1.setDealStatus(Feedback.Status.PROCESSING);
                        MaterialOptionPane.showMessageDialog(ServiceFactory.getFeedbackServiceInstance().updateFeedback(feedback1)?"修改状态成功":"修改状态失败");
                        showFeedbacks(ServiceFactory.getFeedbackServiceInstance().selectFeedbackByEmployeeId(mEmployee.getEmployeeId()));

                    });
                    JMenuItem item2 = new JMenuItem("已处理");
                    item2.setFont(new Font("微软雅黑", Font.PLAIN, 19));
                    item2.addActionListener(e2 -> {
                        feedback1.setDealStatus(Feedback.Status.PROCESSED);
                        MaterialOptionPane.showMessageDialog(ServiceFactory.getFeedbackServiceInstance().updateFeedback(feedback1)?"修改状态成功":"修改状态失败");
                        showFeedbacks(ServiceFactory.getFeedbackServiceInstance().selectFeedbackByEmployeeId(mEmployee.getEmployeeId()));

                    });
                    JMenuItem item3 = new JMenuItem("通道关闭");
                    item3.setFont(new Font("微软雅黑", Font.PLAIN, 19));
                    item3.addActionListener(e3 -> {
                        feedback1.setDealStatus(Feedback.Status.CLOSED);
                        MaterialOptionPane.showMessageDialog(ServiceFactory.getFeedbackServiceInstance().updateFeedback(feedback1)?"修改状态成功":"修改状态失败");
                        showFeedbacks(ServiceFactory.getFeedbackServiceInstance().selectFeedbackByEmployeeId(mEmployee.getEmployeeId()));

                    });
                    jPopupMenu.add(item1);
                    jPopupMenu.add(item2);
                    jPopupMenu.add(item3);
                    jPopupMenu.show(table, e.getX(), e.getY());
                }
            }
        });

        //表格内容监听，根据点击的行得到不同的数据
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            try {
                FeedbackVo feedback = feedbackList.get(row);
                System.out.println(feedback);
                //todo
            } catch (Exception e1) {
                e1.printStackTrace();
            }
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
                EmployeeMainFrame.this.setLocation(xx, yy);
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

        initPanelContentCompent();
    }

    public void initPanelContentCompent(){
        searchCustomerField.addFocusListener(new JTextFieldHintListener(searchCustomerField, " 搜索关键字...", Font.PLAIN));
        searchCustomerField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
    }
}
