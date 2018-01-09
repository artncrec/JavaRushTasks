package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int r, int c, Color desiredColor) {
        if (c >= image.length || c < 0 || r >= image[0].length || r < 0 || image[c][r] == desiredColor)
            return false;
        else {
            if (desiredColor != null) {
                paintPixel(image, r, c, desiredColor, image[c][r]);
                return true;
            } else return false;
        }
    }

    private void paintPixel(Color[][] image, int r, int c, Color desiredColor, Color currentColor) {
        if (c >= image.length || c < 0 || r >= image[0].length || r < 0 || image[c][r] == desiredColor)
            return;
        if (image[c][r] == currentColor) {
            image[c][r] = desiredColor;
            paintPixel(image, r - 1, c, desiredColor, currentColor);
            paintPixel(image, r + 1, c, desiredColor, currentColor);
            paintPixel(image, r, c - 1, desiredColor, currentColor);
            paintPixel(image, r, c + 1, desiredColor, currentColor);
        }
    }
}
