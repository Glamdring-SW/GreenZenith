package com.glamdring.greenZenith.handlers.files;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
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
public class PictureHandler implements Serializable {

    /**
     * The default profile picture of a User.
     */
    private BufferedImage DEFAULT_USER = null;
    /**
     * The default picture of a plant.
     */
    private BufferedImage DEFAULT_PLANT = null;
    /**
     * The default picture of a product.
     */
    private BufferedImage DEFAULT_PRODUCT = null;
    /**
     * A true default for all pictures.
     */
    private BufferedImage DEFAULT_TRUE = null;

    /**
     * Constructs the Picture handler object and asigns the default images a
     * value depending on the image chosen within the proyect files.
     *
     * @throws IOException If it cannot find the default images in the proyect.
     */
    public PictureHandler() throws IOException {
        DEFAULT_USER = ImageIO.read(getClass().getResource("/default_Images/default_profilePicture.png"));
        DEFAULT_PLANT = ImageIO.read(getClass().getResource("/default_Images/default_plantPicture.png"));
        DEFAULT_PRODUCT = ImageIO.read(getClass().getResource("/default_Images/default_productPicture.png"));
        DEFAULT_TRUE = ImageIO.read(getClass().getResource("/default_Images/default_All.png"));
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

    /**
     * Converts a usable image into a Base64 string for easier access and
     * loading with web applications.
     *
     * @param bufferedImage The image to convert.
     * @return A Base64 string that contains the image data.
     */
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

    /**
     * The default User image is applied when a User cannot resolve it's
     * picture.
     *
     * @return The default plant picture for any user.
     */
    public BufferedImage getDEFAULT_USER() {
        return DEFAULT_USER;
    }

    /**
     * The default plant image is applied when a plant cannot resolve it's
     * picture.
     *
     * @return The default plant picture for any plant.
     */
    public BufferedImage getDEFAULT_PLANT() {
        return DEFAULT_PLANT;
    }

    /**
     * The default product image is applied when a product cannot resolve it's
     * picture.
     *
     * @return The default product picture for any product.
     */
    public BufferedImage getDEFAULT_PRODUCT() {
        return DEFAULT_PRODUCT;
    }

    /**
     * The default true is a placeholder image of 512x512 pixels that can be
     * applied to all types of image data that don't have a defined image.
     *
     * @return A placeholder that can be applied to everything.
     */
    public BufferedImage getDEFAULT_TRUE() {
        return DEFAULT_TRUE;
    }

}
