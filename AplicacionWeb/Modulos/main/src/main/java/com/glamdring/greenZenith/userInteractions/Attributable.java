package com.glamdring.greenZenith.userInteractions;

import java.awt.image.BufferedImage;

public interface Attributable {
    int getId();

    String getName();

    BufferedImage getPicture();

    void setName(String name);

    void setPicture(BufferedImage picture);
}
