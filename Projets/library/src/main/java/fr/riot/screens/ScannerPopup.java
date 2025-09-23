package fr.riot.screens;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.NotFoundException;
import fr.riot.Main;
import fr.riot.classes.Reservation;
import fr.riot.models.ReservationModel;
import fr.riot.utils.QRUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ScannerPopup extends BasePopup {
    private final Timer timer;
    private final Webcam webcam;

    protected ScannerPopup() {
        super("Scanner QR");

        webcam = Webcam.getDefault();
        webcam.open();

        JLabel image = new JLabel(new ImageIcon(webcam.getImage()));
        image.setHorizontalAlignment(SwingConstants.CENTER);
        image.setBounds(0, 50, getWidth(), 200);
        add(image);

        JLabel res = new JLabel("Aucun QR code trouvé");
        res.setHorizontalAlignment(SwingConstants.CENTER);
        res.setBounds(0, 255, getWidth(), 200);
        add(res);

        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                image.setIcon(new ImageIcon(webcam.getImage()));

                try {
                    Reservation reservation = QRUtils.readReservationCode(webcam);
                    if (reservation == null)
                        throw NotFoundException.getNotFoundInstance();

                    try {
                        ReservationModel.deleteReservation(reservation);
                    } catch (SQLException exception) {
                        throw new RuntimeException(exception);
                    }

                    Main.getReservations().remove(reservation);
                    if (Main.getCurrentMode().equals(Main.Mode.BOOKS))
                        Screen.setModelList();

                    Screen.closePopup();
                    onClose();
                } catch (NotFoundException ignored) {
                    res.setText("Aucun QR code trouvé");
                } finally {
                    revalidate();
                    repaint();
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    @Override
    void onClose() {
        webcam.close();
        timer.stop();
    }
}
