package Menus;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class custom_textfield extends JTextField{
    
    private Color backgroundColor = Color.WHITE;
    private final Icon iconSearch;
    private Color searchColor = new Color (3,175,255);

    public custom_textfield(){
    
        
        setBackground(new Color(255,255,255));
        setOpaque(false);
        setBorder(new EmptyBorder(10,10,10,50));
        setFont(new java.awt.Font("Montserrat", 0, 12));
        setSelectionColor(Color.black);
        iconSearch = new ImageIcon(getClass().getResource("/icons/search_1.png"));
    }
    
    @Override
    protected void paintComponent(Graphics grphcs){
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);    //  For smooth line
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); //  For smooth image
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, width, height, height, height);
        super.paintComponent(grphcs);
        
        int marginButton = 5;
        int buttonSize = height - marginButton * 2;
        GradientPaint gra = new GradientPaint(0, 0, new Color(255, 255, 255), width, 0, searchColor);
        g2.setPaint(gra);
        g2.fillOval(width - height + 3, marginButton, buttonSize, buttonSize);
        
        //button icon
        int marginImage = 5;
        int imageSize = buttonSize - marginButton * 2;
        Image image = ((ImageIcon) iconSearch).getImage();
        g2.drawImage(image, width - height + marginImage + 4, marginButton + marginImage, imageSize, imageSize, null);
        g2.dispose();
    }
    
    
        
}

