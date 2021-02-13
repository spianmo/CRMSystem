package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultMutableTreeNode;

import cn.hutool.core.io.FileUtil;
import compent.JTextFieldHintListener;
import compent.MyJScrollBar;
import compent.NoBorderJFrame;
import compent.RoundBorder;
import entity.Customer;
import entity.Employee;
import entity.Task;
import entity.vo.TradeVo;
import factory.ServiceFactory;
import frame.runnable.OneTalkThread;
import frame.runnable.TimeThread;
import mdlaf.components.label.MaterialLabelUI;
import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 * @ClassName CustomerMainFrame
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
public class AdminMainFrame extends NoBorderJFrame {

    private static final Color COLOR_CYAN = new Color(83, 109, 254);
    private static final Color COLOR_WHITE = new Color(255, 255, 255);
    int xOld = 0;
    int yOld = 0;
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel centerPanel;
    private JButton 员工管理Button;
    private JButton 客户管理Button;
    private JButton 订单管理Button;
    private JButton 查询业绩Button;
    private JPanel tradeManagePanel;
    private JPanel taskManagePanel;
    private JPanel customerManagePanel;
    private JPanel employeeManagePanel;
    private JPanel logoPanel;
    private JPanel containerPanel;
    private JPanel toolbar;
    private JButton minBtn;
    private JButton exitBtn;
    private JLabel mainTitle;
    private JLabel oneTalkLabel;
    private JLabel timeLabel;
    private CardLayout cardLayout;
    private JTextField searchCustomerField;
    private JButton 搜索CustomerButton;
    private JPanel customerTablePanel;
    private JPanel employeeTablePanel;
    private JButton 搜索EmployeeButton;
    private JTextField searchEmployeeField;
    private JPanel feedbackTablePanel;
    private JPanel taskTablePanel;
    private JPanel treePanel;
    private JButton 导出订单Button;
    private JButton 导出员工Button;
    private JButton 导出员工绩效Button;
    private JPanel tradeTablePanel;
    private JButton 添加员工Button;
    private JButton 添加客户Button;

    public AdminMainFrame(String title) {
        this.toFront();
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
        new AdminMainFrame("管理员:shinonon");
    }

    public void initSideTabMenu() {
        cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        centerPanel.add("1", customerManagePanel);
        centerPanel.add("2", employeeManagePanel);
        centerPanel.add("3", tradeManagePanel);
        centerPanel.add("4", taskManagePanel);

        员工管理Button.setIcon(new ImageIcon("img/icon2white.png"));
        客户管理Button.setIcon(new ImageIcon("img/icon1blue.png"));
        订单管理Button.setIcon(new ImageIcon("img/icon3white.png"));
        查询业绩Button.setIcon(new ImageIcon("img/icon4white.png"));
        showCustomers(ServiceFactory.getCustomerServiceInstance().selectAll());
        showTree(ServiceFactory.getEmployeeServiceInstance().selectAllEmployee());
        员工管理Button.addActionListener(e -> {
            员工管理Button.setIcon(new ImageIcon("img/icon1blue.png"));
            客户管理Button.setIcon(new ImageIcon("img/icon2white.png"));
            订单管理Button.setIcon(new ImageIcon("img/icon3white.png"));
            查询业绩Button.setIcon(new ImageIcon("img/icon4white.png"));
            员工管理Button.setForeground(COLOR_CYAN);
            员工管理Button.setBackground(COLOR_WHITE);
            客户管理Button.setForeground(COLOR_WHITE);
            客户管理Button.setBackground(COLOR_CYAN);
            订单管理Button.setForeground(COLOR_WHITE);
            订单管理Button.setBackground(COLOR_CYAN);
            查询业绩Button.setForeground(COLOR_WHITE);
            查询业绩Button.setBackground(COLOR_CYAN);
            cardLayout.show(centerPanel, "2");
            showTree(ServiceFactory.getEmployeeServiceInstance().selectAllEmployee());
            showEmployees(ServiceFactory.getEmployeeServiceInstance().selectAllEmployee());
        });
        客户管理Button.addActionListener(e -> {
            员工管理Button.setIcon(new ImageIcon("img/icon1white.png"));
            客户管理Button.setIcon(new ImageIcon("img/icon2blue.png"));
            订单管理Button.setIcon(new ImageIcon("img/icon3white.png"));
            查询业绩Button.setIcon(new ImageIcon("img/icon4white.png"));
            员工管理Button.setForeground(COLOR_WHITE);
            员工管理Button.setBackground(COLOR_CYAN);
            客户管理Button.setForeground(COLOR_CYAN);
            客户管理Button.setBackground(COLOR_WHITE);
            订单管理Button.setForeground(COLOR_WHITE);
            订单管理Button.setBackground(COLOR_CYAN);
            查询业绩Button.setForeground(COLOR_WHITE);
            查询业绩Button.setBackground(COLOR_CYAN);
            cardLayout.show(centerPanel, "1");
            showCustomers(ServiceFactory.getCustomerServiceInstance().selectAll());
        });
        订单管理Button.addActionListener(e -> {
            员工管理Button.setIcon(new ImageIcon("img/icon1white.png"));
            客户管理Button.setIcon(new ImageIcon("img/icon2white.png"));
            订单管理Button.setIcon(new ImageIcon("img/icon3blue.png"));
            查询业绩Button.setIcon(new ImageIcon("img/icon4white.png"));
            员工管理Button.setForeground(COLOR_WHITE);
            员工管理Button.setBackground(COLOR_CYAN);
            客户管理Button.setForeground(COLOR_WHITE);
            客户管理Button.setBackground(COLOR_CYAN);
            订单管理Button.setForeground(COLOR_CYAN);
            订单管理Button.setBackground(COLOR_WHITE);
            查询业绩Button.setForeground(COLOR_WHITE);
            查询业绩Button.setBackground(COLOR_CYAN);
            cardLayout.show(centerPanel, "3");
            showTrades(ServiceFactory.getTradeServiceInstance().selectAllTradeVo());
        });
        查询业绩Button.addActionListener(e -> {
            员工管理Button.setIcon(new ImageIcon("img/icon1white.png"));
            客户管理Button.setIcon(new ImageIcon("img/icon2white.png"));
            订单管理Button.setIcon(new ImageIcon("img/icon3white.png"));
            查询业绩Button.setIcon(new ImageIcon("img/icon4blue.png"));
            员工管理Button.setForeground(COLOR_WHITE);
            员工管理Button.setBackground(COLOR_CYAN);
            客户管理Button.setForeground(COLOR_WHITE);
            客户管理Button.setBackground(COLOR_CYAN);
            订单管理Button.setForeground(COLOR_WHITE);
            订单管理Button.setBackground(COLOR_CYAN);
            查询业绩Button.setForeground(COLOR_CYAN);
            查询业绩Button.setBackground(COLOR_WHITE);
            cardLayout.show(centerPanel, "4");
            showTasks(ServiceFactory.getTaskServiceInstance().selectAllTask());
        });
    }

    private void showEmployees(List<Employee> employeeList) {
        employeeTablePanel.removeAll();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.setColumnIdentifiers(new String[]{"员工编号", "姓名", "产品类型", "部门ID", "薪水"});
        for (Employee employee : employeeList) {
            Object[] object = new Object[]{employee.getEmployeeId(), employee.getName(), employee.getProduceType(), employee.getDepartmentId(), employee.getSalary()};
            model.addRow(object);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(160);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setPreferredWidth(160);
        table.getColumnModel().getColumn(3).setPreferredWidth(160);
        table.getColumnModel().getColumn(4).setPreferredWidth(160);
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
        employeeTablePanel.add(scrollPane);
        employeeTablePanel.revalidate();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //表格 的rowAtPoint方法返回坐标所在的行号，参数为坐标类型，
                    int i = table.rowAtPoint(e.getPoint());
                    Employee employee = employeeList.get(i);
                    //todo
                }
            }
        });

        //表格内容监听，根据点击的行得到不同的数据
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            try {
                Employee employee = employeeList.get(row);
                System.out.println(employee);
                //todo
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    private void showCustomers(List<Customer> customerList) {
        customerTablePanel.removeAll();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.setColumnIdentifiers(new String[]{"客户编号", "姓名", "住址", "信用值", "手机号"});
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
        customerTablePanel.add(scrollPane);
        customerTablePanel.revalidate();
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

    private void showTrades(List<TradeVo> tradeVoList) {
        tradeTablePanel.removeAll();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.setColumnIdentifiers(new String[]{"订单ID", "订单内容", "订单金额", "订购数量", "交易时间"});
        for (TradeVo trade : tradeVoList) {
            Object[] object = new Object[]{trade.getTradeId(), trade.getProduceName(), trade.getAmount(), trade.getProduceNum(), trade.getTradeTime()};
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
        tradeTablePanel.add(scrollPane);
        tradeTablePanel.revalidate();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //表格 的rowAtPoint方法返回坐标所在的行号，参数为坐标类型，
                    int i = table.rowAtPoint(e.getPoint());
                    TradeVo tradeVo = tradeVoList.get(i);
                    //todo
                }
            }
        });

        //表格内容监听，根据点击的行得到不同的数据
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            try {
                TradeVo tradeVo = tradeVoList.get(row);
                System.out.println(tradeVo);
                //todo
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    private void showTasks(List<Task> taskVoList) {
        taskTablePanel.removeAll();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.setColumnIdentifiers(new String[]{"计划编号", "计划详情", "目标客户人数", "计划状态", "计划时间"});
        //遍历list，生成Object数组，数组中的每一个元素就是一行记录
        for (Task task : taskVoList) {
            Object[] object = new Object[]{task.getTaskId(), task.getTaskDesc(), task.getCustomerNum(), task.getTaskStatus().getDesc(), task.getTaskTime()};
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
                    Task task = taskVoList.get(i);
                    //todo
                }
            }
        });

        //表格内容监听，根据点击的行得到不同的数据
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            try {
                Task task = taskVoList.get(row);
                System.out.println(task);
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
                AdminMainFrame.this.setLocation(xx, yy);
            }
        });

        this.setVisible(true);

        initSideTabMenu();

        minBtn.addActionListener(actionEvent -> {
            this.setExtendedState(JFrame.ICONIFIED);
        });
        exitBtn.addActionListener(actionEvent -> {
            this.dispose();
            System.exit(0);
        });
        initPanelContentCompent();
    }

    public void initPanelContentCompent() {
        searchCustomerField.addFocusListener(new JTextFieldHintListener(searchCustomerField, " 搜索关键字...", Font.PLAIN));
        searchCustomerField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        searchEmployeeField.addFocusListener(new JTextFieldHintListener(searchEmployeeField, " 搜索关键字...", Font.PLAIN));
        searchEmployeeField.setBorder(new RoundBorder(Color.LIGHT_GRAY));
        导出订单Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = FileUtil.appendString(ServiceFactory.getTradeServiceInstance().selectAllTrade().toString(), new File("Trade_admin_export.txt"), StandardCharsets.UTF_8);
                MaterialOptionPane.showMessageDialog(file != null ? "导出成功，已保存到" + file.getAbsolutePath() + "中" : "导出失败");
            }
        });
        导出员工Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = FileUtil.appendString(ServiceFactory.getEmployeeServiceInstance().selectAllEmployee().toString(), new File("Employee_admin_export.txt"), StandardCharsets.UTF_8);
                MaterialOptionPane.showMessageDialog(file != null ? "导出成功，已保存到" + file.getAbsolutePath() + "中" : "导出失败");
            }
        });
        导出员工绩效Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = FileUtil.appendString(ServiceFactory.getTaskServiceInstance().selectAllTask().toString(), new File("Task_admin_export.txt"), StandardCharsets.UTF_8);
                MaterialOptionPane.showMessageDialog(file != null ? "导出成功，已保存到" + file.getAbsolutePath() + "中" : "导出失败");
            }
        });
        添加客户Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MaterialOptionPane.showCustomerAddPanel(new CustomerAddPanel.Callback() {
                    @Override
                    public void onFinish() {
                        AdminMainFrame.this.showCustomers(ServiceFactory.getCustomerServiceInstance().selectAll());
                    }
                });
            }
        });
        添加员工Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MaterialOptionPane.showEmployeeAddPanel(new EmployeeAddPanel.Callback() {
                    @Override
                    public void onFinish() {
                        AdminMainFrame.this.showEmployees(ServiceFactory.getEmployeeServiceInstance().selectAllEmployee());
                    }
                });
            }
        });
        搜索CustomerButton.addActionListener(e -> {
            String searchStr = searchCustomerField.getText();
            if (searchStr.isEmpty() || " 搜索关键字...".equals(searchStr)) {
                MaterialOptionPane.showMessageDialog("搜索关键字不能为空");
                return;
            }
            showCustomers(ServiceFactory.getCustomerServiceInstance().selectCustomerLikely(searchStr));
        });
        搜索EmployeeButton.addActionListener(e -> {
            String searchStr = searchEmployeeField.getText();
            if (searchStr.isEmpty() || " 搜索关键字...".equals(searchStr)) {
                MaterialOptionPane.showMessageDialog("搜索关键字不能为空");
                return;
            }
            showEmployees(ServiceFactory.getEmployeeServiceInstance().selectEmployeeLikely(searchStr));
        });
    }

    private void showTree(List<Employee> employees) {
        treePanel.removeAll();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("CRM ROOT");
        for (Employee employee : employees) {
            DefaultMutableTreeNode group = new DefaultMutableTreeNode(employee.getName());
            root.add(group);
            List<Customer> customerList = ServiceFactory.getCustomerServiceInstance().selectCustomerByEmployeeId(employee.getEmployeeId());
            for (Customer customer : customerList) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(customer.getName());
                group.add(node);
            }
        }
        final JTree tree = new JTree(root);
        tree.setRowHeight(30);
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        treePanel.add(tree, BorderLayout.CENTER);
        treePanel.revalidate();
    }
}

