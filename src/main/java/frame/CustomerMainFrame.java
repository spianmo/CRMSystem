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
import entity.Customer;
import frame.runnable.OneTalkThread;
import frame.runnable.TimeThread;

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
    private Customer mCustomer;

    public CustomerMainFrame(String title) {
        this.toFront();
        this.mCustomer = Customer.builder()
                .customerId(1)
                .address("江苏省南京市栖霞区羊山北路一号")
                .name("唐钱进")
                .credit(90)
                .phone("13861948872")
                .employeeId(1)
                .userId(1)
                .build();
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
    public CustomerMainFrame(String title,Customer customer) {
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
        new CustomerMainFrame("");
    }

    public void initSideTabMenu() {
        cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        centerPanel.add("1", employeeInfoPanel);
        centerPanel.add("2", customerInfoPanel);
        centerPanel.add("3", taskManagePanel);
        centerPanel.add("4", feedbackDealPanel);

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
    }
}
