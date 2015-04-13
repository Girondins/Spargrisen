package Spargrisen;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class CategoryGUI extends JFrame {
	private String total = "2300kr \n";

	private JSlider foodSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);
	private JSlider rentSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);;
	private JSlider carSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);;
	private JSlider nöjeSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);;
	private JSlider gameSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);;
	private JSlider movieSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);;
	private JSlider booksSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 0);;

	private JLabel TOTlbl = new JLabel("Totalt: ");
	private JLabel moneyLbl = new JLabel(total);

	private JLabel foodlbl = new JLabel("MAT");
	private JLabel rentlbl = new JLabel("Hyra");
	private JLabel carlbl = new JLabel("Bil");
	private JLabel nˆjelbl = new JLabel("Nˆje");
	private JLabel gamelbl = new JLabel("Spel");
	private JLabel movielbl = new JLabel("Film");
	private JLabel bookslbl = new JLabel("Bˆcker");

	public CategoryGUI() {
		
		JPanel p = new JPanel();
		JPanel total = new JPanel();

		total.setLayout(new GridLayout(1, 2));
		p.setLayout(new GridLayout(15, 1));

		foodSlider.setMajorTickSpacing(3);
		foodSlider.setPaintLabels(true);
		
		rentSlider.setMajorTickSpacing(3);
		rentSlider.setPaintLabels(true);
		carSlider.setMajorTickSpacing(3);
		carSlider.setPaintLabels(true);
		nöjeSlider.setMajorTickSpacing(3);
		nöjeSlider.setPaintLabels(true);
		gameSlider.setMajorTickSpacing(3);
		gameSlider.setPaintLabels(true);
		movieSlider.setMajorTickSpacing(3);
		movieSlider.setPaintLabels(true);
		

		total.add(TOTlbl);
		total.add(moneyLbl);
		p.add(total);
		p.add(foodlbl);
		p.add(foodSlider);
		p.add(rentlbl);
		p.add(rentSlider);
		p.add(carlbl);
		p.add(carSlider);
		p.add(nˆjelbl);
		p.add(nöjeSlider);
		p.add(gamelbl);
		p.add(gameSlider);
		p.add(movielbl);
		p.add(movieSlider);
		p.setVisible(true);
		startFrame(p);

	}

	public void startFrame(JPanel p) {
		JFrame frame = new JFrame("BUDGET APPLIKATION");
		frame.add(p);
		frame.setLocationRelativeTo(null);
		frame.setPreferredSize(new Dimension(300,416)); // galaxy S4 screen size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

	public static void main(String[] args) {
		new CategoryGUI();
	}

}

// frame.setExtendedState(JFrame.MAXIMIZED_BOTH); / olika mobiler olika sk‰rmar
// gˆr framen lika stor som displayen
// frame.setResizable(false);
 
