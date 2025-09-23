package fr.riot.screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public abstract class BasePopup extends JPanel {
	protected BasePopup(String title) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		setBounds(screen.width / 4, screen.height / 4, screen.width / 2, screen.height / 2);
		setLayout(null);
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black));

		JLabel titleLabel = new JLabel(title);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		titleLabel.setBounds(0, 35, getWidth(), 25);
		add(titleLabel);

		JButton close = new JButton("\u274c");
		close.setBounds(getWidth() - 55, 5, 50, 50);
		close.setForeground(Color.red);
		close.setBackground(null);
		close.setBorder(null);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Screen.closePopup();
                onClose();
			}
		});
		add(close);
	}

    abstract void onClose();
}
