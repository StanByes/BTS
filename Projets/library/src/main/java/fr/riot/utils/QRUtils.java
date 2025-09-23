package fr.riot.utils;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import fr.riot.Main;
import fr.riot.classes.Reservation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.UUID;

public class QRUtils {
    public static void generateAndSaveReservation(Reservation reservation) {
        String data = reservation.getBook().getIsbn() + "--_--" + reservation.getId();
        data = new String(data.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

        try {
            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 200, 200);

            String fileName = UUID.randomUUID() + ".png";
            MatrixToImageWriter.writeToPath(matrix, "png", Paths.get("./reservations/" + fileName));
        } catch (WriterException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static Reservation readReservationCode(Webcam webcam) throws NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(
                        new BufferedImageLuminanceSource(
                                webcam.getImage())));
        Result rs = new MultiFormatReader().decode(binaryBitmap);

        String data = rs.getText();
        String[] splitData = data.split("--_--");

        for (Reservation reservation : Main.getReservations())
            if (reservation.getId() == Integer.parseInt(splitData[1]))
                return reservation;

        return null;
    }
}
