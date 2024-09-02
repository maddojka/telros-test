package com.soroko.telrostest.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class ImageTest {

    @Test
    @DisplayName("Check image is OK")
    public void Image_isOk() {
        Image image = new Image();
        image.setId(1);
        image.setName("image01");
    }
}
