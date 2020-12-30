package compent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/* renamed from: AeroDemo */
public class AeroDemo extends JFrame {
	public static JPanel topPanel = new JPanel();
	private AeroPane aeroPane;
	private List<JButton> buttonList = new ArrayList<>();
	private Font font = new Font("微软雅黑", 1, 18);
	private Random random = new Random();


	public AeroDemo() {
		initSwing();
	}

	private void initSwing() {
		topPanel.setLayout(new BorderLayout());
		topPanel.setBounds(60, 60, 500, 300);
		topPanel.setOpaque(false);
		topPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		getRootPane().getLayeredPane().add(topPanel, JLayeredPane.POPUP_LAYER + 50);
		getContentPane().add(getAeroPane());
		setSize(800, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private AeroPane getAeroPane() {
		if (this.aeroPane == null) {
			this.aeroPane = new AeroPane();
			this.aeroPane.setLayout(null);
			this.aeroPane.add(getButton(true));
			this.aeroPane.add(getButton(false));
			this.aeroPane.add(getButton(false));
			this.aeroPane.add(getButton(true));
			this.aeroPane.add(getButton(false));
			this.aeroPane.add(getButton(false));
			this.aeroPane.add(getButton(true));
			this.aeroPane.add(getButton(false));
			this.aeroPane.add(getButton(true));
			this.aeroPane.add(getButton(false));
			this.aeroPane.add(getButton(false));
			this.aeroPane.add(getButton(true));
			this.aeroPane.add(getButton(false));
		}
		return this.aeroPane;
	}

	private JButton getButton(boolean isRandomColor) {
		JButton b = new JButton();
		b.setBackground(new Color(this.random.nextInt(255), this.random.nextInt(255), this.random.nextInt(255)));
		b.setFont(this.font);
		b.setForeground(Color.WHITE);
		b.setBounds(this.random.nextInt(600), this.random.nextInt(690), 200, 80);
		if (isRandomColor) {
			this.buttonList.add(b);
		}
		return b;
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new AeroDemo().setVisible(true);
		});
	}
}