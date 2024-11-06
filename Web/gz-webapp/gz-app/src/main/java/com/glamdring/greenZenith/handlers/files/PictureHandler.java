package com.glamdring.greenZenith.handlers.files;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;

import javax.imageio.ImageIO;

/**
 * Handles the internal control and transformations of different image files.
 *
 * @author Glamdring (Σxz)
 * @version 0.0.1
 * @since 0.1
 */
public class PictureHandler {

    /**
     * The default profile picture of a User.
     */
    public BufferedImage DEFAULT_USER = null;
    /**
     * The default picture of a plant.
     */
    public BufferedImage DEFAULT_PLANT = null;
    /**
     * The default picture of a product.
     */
    public BufferedImage DEFAULT_PRODUCT = null;
    /**
     * A true default for all pictures.
     */
    public BufferedImage DEFAULT_TRUE = null;

    /**
     * Constructs the Picture handler object and asigns the default images a
     * value depending on the image chosen within the proyect files.
     */
    public PictureHandler() {
        try {
            DEFAULT_USER = ImageIO.read(new File("../../../../../../resources/default_profilePicture.png"));
            DEFAULT_PLANT = ImageIO.read(new File("../../../../../../resources/default_plantPicture.png"));
            DEFAULT_PRODUCT = ImageIO.read(new File("../../../../../../resources/default_productPicture.png"));
            DEFAULT_TRUE = ImageIO.read(new File("../../../../../../resources/default_All.png"));
        } catch (IOException e) {
        }
    }

    /**
     * Converts any blob to a usable image object.
     *
     * @param blobImage A Binary Large Object from the database that represents
     * an image file.
     * @return The resulting image file in a usable format.
     */
    public BufferedImage convertBlobToBufferedImage(Blob blobImage) {
        try {
            return ImageIO.read(blobImage.getBinaryStream());
        } catch (IOException | SQLException e) {
            return DEFAULT_TRUE;
        }
    }

    /**
     * Converts any usable image object into a Binary Large Object, requires a
     * connection to create BLOBs.
     *
     * @param bufferedImage The image to convert.
     * @param connection The connection that provides access to the creation of
     * blobs.
     * @return A Binary Large Object with the image file data.
     */
    public Blob convertBufferedImageToBlob(BufferedImage bufferedImage, Connection connection) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Blob blob = null;
        try {
            ImageIO.write(bufferedImage, "png", baos);
            blob = connection.createBlob();
            blob.setBytes(1, baos.toByteArray());
            return blob;
        } catch (IOException | SQLException e) {
            return blob;
        }
    }

    public String convertBufferedImageToBase64(BufferedImage bufferedImage) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            byte[] imageInByteArray = baos.toByteArray();
            baos.close();
            String b64 = jakarta.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);
            return b64;
        } catch (IOException e) {
            return null;
        }
    }
}
