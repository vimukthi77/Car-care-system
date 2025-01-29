package Menus;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import View.RippleEffect;
import View.ShadowRenderer;
import java.awt.Cursor;
import java.beans.BeanProperty;

public class GradientButton extends JButton {

    private int round = 10;
    private Color shadowColor = new Color(170, 170, 170);

    private Color startGradientColor = new Color(255, 255, 255);
    private Color endGradientColor = new Color(200, 200, 200);

    private BufferedImage imageShadow;
    private final Insets shadowSize = new Insets(2, 5, 8, 5);
    private final RippleEffect rippleEffect = new RippleEffect(this);

    public GradientButton() {
        setBorder(new EmptyBorder(10, 12, 15, 12));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(startGradientColor);
        setForeground(new Color(80, 80, 80));
        rippleEffect.setRippleColor(new Color(220, 220, 220));
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @BeanProperty(description = "Set the start color of the gradient.")
    public void setStartGradientColor(Color startColor) {
        startGradientColor = startColor;
        setBackground(startGradientColor);
        repaint();
    }

    @BeanProperty(description = "Set the end color of the gradient.")
    public void setEndGradientColor(Color endColor) {
        endGradientColor = endColor;
        repaint();
    }

    @BeanProperty(description = "Set the shadow color.")
    public void setShadowColor(Color color) {
        shadowColor = color;
        createImageShadow();
        repaint();
    }

    public Color getStartGradientColor() {
        return startGradientColor;
    }

    public Color getEndGradientColor() {
        return endGradientColor;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double width = getWidth() - (shadowSize.left + shadowSize.right);
        double height = getHeight() - (shadowSize.top + shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;

        GradientPaint gradient = new GradientPaint(0, 0, startGradientColor, 0, getHeight(), endGradientColor);
        g2.setPaint(gradient);

        g2.drawImage(imageShadow, 0, 0, null);
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
        g2.fill(area);
        rippleEffect.render(graphics, area);
        g2.dispose();
        super.paintComponent(graphics);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createImageShadow();
    }

    private void createImageShadow() {
        int height = getHeight();
        int width = getWidth();
        if (width > 0 && height > 0) {
            imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = imageShadow.createGraphics();
            g2.drawImage(createShadow(height), 0, 0, null);
            g2.dispose();
        }
    }

    private BufferedImage createShadow(int size) {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, round, round));
        g2.dispose();
        return new ShadowRenderer(5, 0.3f, shadowColor).createShadow(img);
    }
}
