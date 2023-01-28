import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class MarsView {
	JFrame frame;
	ArrayList<Robot> bots;
	JButton nextButton, previouseButton, pauseButton, negateButton, variateButton;
	JScrollPane scroll;
	JTextArea text;
	JTextField testBotTextField;
	int testBot;

	public void setTestSets(ArrayList<Robot> bots) {
		
		this.bots = bots;
		for(Robot r : bots){
			r.setTestIndex(bots.indexOf(r));
		}
		testBot = MarsConstants.TEST_BOT;
		bots.get(testBot).setTest(MarsConstants.TEST, text);
		testBotTextField.setText(" "+testBot+" ");
		
	}

	MarsView(Screen screen) {
		frame = new JFrame();
		JFrame textFrame = new JFrame();
		testBotTextField = new JTextField();
		text = new JTextArea(20, 70);
		scroll = new JScrollPane(text);
		nextButton = new JButton("  >>  ");
		previouseButton = new JButton("  <<  ");
		pauseButton = new JButton("  II  ");
		negateButton = new JButton(" negate force ");
		variateButton = new JButton(" variate force ");
		JPanel buttonPanel = new JPanel(new GridLayout(1,5));

		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		nextButton.addActionListener(new NextButtonListener());
		previouseButton.addActionListener(new PreviouseButtonListener());
		pauseButton.addActionListener(new PauseButtonListener());
		negateButton.addActionListener(new NegateButtonListener());
		variateButton.addActionListener(new VariateButtonListener());
		
		buttonPanel.add(previouseButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(testBotTextField);
		buttonPanel.add(pauseButton);
		buttonPanel.add(negateButton);
		buttonPanel.add(variateButton);

		frame.add(screen, BorderLayout.WEST);
		if (MarsConstants.TEST) {
			frame.add(buttonPanel, BorderLayout.NORTH);
			textFrame.add(scroll, BorderLayout.EAST);
			textFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			textFrame.pack();
			textFrame.setVisible(true);
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private class NextTestBotIndex implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (testBot < (bots.size() - 1)) {
				bots.get(testBot).setTest(false, null);
				Robot r = bots.get(++testBot);
				r.setTest(true, text);
				testBotTextField.setText(""+testBot);
				text.setText(r.getTestOutText());
				frame.repaint();
				
			}
		}

	}

	private class NextButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (testBot < (bots.size() - 1)) {
				bots.get(testBot).setTest(false, null);
				Robot r = bots.get(++testBot);
				r.setTest(true, text);
				testBotTextField.setText(""+testBot);
				text.setText(r.getTestOutText());
				frame.repaint();
			}
		}

	}

	private class PreviouseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (testBot > 0) {
				bots.get(testBot).setTest(false, null);
				bots.get(--testBot).setTest(true, text);
				testBotTextField.setText(""+testBot);
				text.setText("");
				frame.repaint();
			}
		}

	}
	
	private class PauseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Physics.changePause();
		}

	}
	
	private class NegateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Physics.changeForceNegation();
		}

	}
	
	private class VariateButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Physics.changeForceVariation();
		}

	}
}
