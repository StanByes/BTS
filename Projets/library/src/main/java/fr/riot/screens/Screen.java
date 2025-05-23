package fr.riot.screens;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import fr.riot.Main;
import fr.riot.Main.Mode;
import fr.riot.classes.Book;
import fr.riot.classes.Client;
import fr.riot.classes.ListItems;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class Screen {

	private static JFrame frame;
	private static JLayeredPane layeredPane;
	private static JPanel contentPane;
	private static DefaultListModel<ListItems> defaultListModel;
	private static JList list;

	private static JButton switcher;
	private static JButton reservations;
	private static JButton createReservation;
	private static JButton create;
	
	private static BasePopup popup;
	
	public Screen() {
		initialize();
	}

	private void initialize() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		frame = new JFrame("Bibliothèque");
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane);

		contentPane = new JPanel();
		contentPane.setLocation(0, 0);
		contentPane.setSize(screen.width, screen.height);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		reservations = new JButton("Liste des emprunts");
		reservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPopup(new ReservationsPopup());
			}
		});
		reservations.setBounds(screen.width / 4, 11, 150, 23);
		contentPane.add(reservations);

		createReservation = new JButton("Emprunter");
		createReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPopup(new CreateReservationPopup());
			}
		});
		createReservation.setBounds((int) (screen.width / 1.5), 11, 150, 23);
		contentPane.add(createReservation);

		create = new JButton("Ajouter un livre");
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPopup(new CreationPopup());
			}
		});
		create.setBounds(screen.width - 160, 11, 150, 23);
		contentPane.add(create);

		JLabel title = new JLabel("Liste des Livres");
		title.setBounds(0, 65, screen.width, 30);
		title.setFont(new Font("Tahoma", Font.PLAIN, 27));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(title);

		switcher = new JButton("Clients");
		switcher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switcher.setText(Main.getCurrentMode().getDisplayName());

				if (Main.getCurrentMode().equals(Mode.BOOKS))
					Main.setCurrentMode(Mode.CLIENTS);
				else
					Main.setCurrentMode(Mode.BOOKS);
				
				setModelList();

				String name = Main.getCurrentMode().getDisplayName();
				name = name.toLowerCase();
				name = name.substring(0, name.length() - 1);
				create.setText("Ajouter un " + name);
				title.setText("Liste des " + Main.getCurrentMode().getDisplayName());
			}
		});
		switcher.setBounds(10, 10, 150, 23);
		contentPane.add(switcher);

		setModelList();
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(10, 200, screen.width - 20, screen.height - 280);
		contentPane.add(scrollPane);

		layeredPane.add(contentPane, JLayeredPane.DEFAULT_LAYER);
		frame.setVisible(true);
	}
	
	public static void setModelList() {
		if (list == null) {
			defaultListModel = new DefaultListModel<>();
			list = new JList(defaultListModel);
			list.setFixedCellHeight(75);
		} else {
			defaultListModel.clear();
		}

		if (Main.getCurrentMode().equals(Mode.BOOKS)) {
			list.setCellRenderer(new BookRender());

			for (Book book : Main.getBooks())
				defaultListModel.addElement(book);
		} else {
			list.setCellRenderer(new ClientRender());

			for (Client client : Main.getClients())
				defaultListModel.addElement(client);
		}
	}
	
	public static void openPopup(BasePopup popup) {
		switcher.setEnabled(false);
		create.setEnabled(false);
		reservations.setEnabled(false);
		createReservation.setEnabled(false);

		Screen.popup = popup;
		
		layeredPane.add(popup, JLayeredPane.POPUP_LAYER);
		layeredPane.revalidate();
		layeredPane.repaint();
	}
	
	public static void closePopup() {
		switcher.setEnabled(true);
		create.setEnabled(true);
		reservations.setEnabled(true);
		createReservation.setEnabled(true);
		
		layeredPane.remove(popup);
		layeredPane.revalidate();
		layeredPane.repaint();
	}
}
