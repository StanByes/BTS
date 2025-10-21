package fr.riot.ap2.components;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import fr.riot.ap2.classes.PopupValidation;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Scanner extends JFrame {
    private final Webcam webcam;
    private final PopupValidation<String> callback;

    public Scanner(PopupValidation<String> callback) {
        super("Scanner");
        this.callback = callback;

        // Manage Webcam OPEN/CLOSE //
        this.webcam = Webcam.getDefault();
        this.webcam.open();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                disposed(false);
            }
        });

        setSize(800, 400);
        setLocation(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, getWidth(), getHeight());

        JLabel image = new JLabel(new ImageIcon(""));
        image.setHorizontalAlignment(SwingConstants.CENTER);
        image.setBounds(0, 0, getWidth(), 200);
        panel.add(image);

        JLabel res = new JLabel("Aucun QR code trouvé");
        res.setHorizontalAlignment(SwingConstants.CENTER);
        res.setBounds(0, 250, getWidth(), 65);
        panel.add(res);

        Timer timer = new Timer(1, null);
        timer.addActionListener(e -> {
            image.setIcon(new ImageIcon(webcam.getImage()));

            try {
                String code = readCode();
                timer.stop();

                dispose();
                disposed(true);
                callback.validate(code);
            } catch (NotFoundException ignored) {
                res.setText("Aucun QR code trouvé");
            } finally {
                revalidate();
                repaint();
            }
        });
        timer.setRepeats(true);
        timer.start();

        getContentPane().add(panel);
    }

    public void disposed(boolean validated) {
        if (!validated)
            callback.cancel();

        webcam.close();
    }

    private String readCode() throws NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(
                        new BufferedImageLuminanceSource(
                                webcam.getImage())));
        Result rs = new MultiFormatReader().decode(binaryBitmap);
        return rs.getText();
    }
}
