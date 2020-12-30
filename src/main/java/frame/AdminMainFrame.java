package frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import compent.NoBorderJFrame;
import frame.runnable.OneTalkThread;
import frame.runnable.TimeThread;

/**
 * @ClassName CustomerMainFrame
 * @Description TODO
 * @Author Finger
 * @Date 12/29/2020
 **/
public class AdminMainFrame extends NoBorderJFrame {

    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel centerPanel;
    private JButton 员工管理Button;
    private JButton 客户管理Button;
    private JButton 订单管理Button;
    private JButton 查询业绩Button;
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
    private CardLayout cardLayout;

    private static final Color COLOR_CYAN = new Color(83, 109, 254);
    private static final Color COLOR_WHITE = new Color(255, 255, 255);

    public AdminMainFrame(String title){
        this.toFront();
        mainTitle.setText("   CRM System "+title);
        oneTalkLabel.setText(title);
        OneTalkThread oneTalkThread = new OneTalkThread();
        oneTalkThread.setOneTalkLabel(oneTalkLabel);
        new Thread(oneTalkThread).start();

        TimeThread timeThread = new TimeThread();
        timeThread.setTimeLabel(timeLabel);
        new Thread(timeThread).start();

        initComponent();
    }

    int xOld = 0;
    int yOld = 0;

    public void initSideTabMenu(){
        cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        centerPanel.add("1", employeeInfoPanel);
        centerPanel.add("2", customerInfoPanel);
        centerPanel.add("3", taskManagePanel);
        centerPanel.add("4", feedbackDealPanel);

        员工管理Button.setIcon(new ImageIcon("img/icon1blue.png"));
        客户管理Button.setIcon(new ImageIcon("img/icon2white.png"));
        订单管理Button.setIcon(new ImageIcon("img/icon3white.png"));
        查询业绩Button.setIcon(new ImageIcon("img/icon4white.png"));
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
            cardLayout.show(centerPanel, "1");
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
            cardLayout.show(centerPanel, "2");
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
        });
    }

    public static void main(String[] args) {
        new AdminMainFrame("");
    }
}
