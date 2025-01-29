/*
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menus;

/**
 *
 * @author Oshan
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;
import View.RippleEffect;
import View.ShadowRenderer;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.SwingConstants;



public class ButtonCenterIcon extends JButton {
    
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        createImageShadow();
        repaint();
    }
    
    public void setRippleColor(Color color){ 
        rippleEffect.setRippleColor(color);
    }
    
    public Color getRippleColor(){  
        return rippleEffect.getRippleColor();
    }
    
    
    
    int round=10;
    private Color shadowColor=new Color(170,170,170);
    BufferedImage imageShadow;
    final Insets shadowSize = new Insets(2, 5, 8, 5);
    final RippleEffect rippleEffect = new RippleEffect(this);
    
    public ButtonCenterIcon(){
        
        setBorder(new EmptyBorder(10,12,15,12));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(new Color(255,255,255));
        setForeground(new Color(80,80,80));
        rippleEffect.setRippleColor(new Color(220,220,220));
    
        
    
       
    }

    @Override
    protected void paintComponent(Graphics graphcs) {
        
        Graphics2D g2 = (Graphics2D) graphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double width = getWidth()-(shadowSize.left+shadowSize.right);
        double height = getHeight()-(shadowSize.top+shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;
        g2.drawImage(imageShadow, 0,0,null);
        g2.setColor(getBackground());
        Area area =new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
        // Set the horizontal text position to the bottom
        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(SwingConstants.BOTTOM);

        g2.fill(area);
        rippleEffect.render(graphcs, area);
        g2.dispose();
        super.paintComponent(graphcs); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createImageShadow();
    }

    private void createImageShadow() {
        int height = getHeight();
        int width  = getWidth();
        if(width>0 && height>0){
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
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height,round,round));
        g2.dispose();
        return new ShadowRenderer(5, 0.3f, new Color(50, 50, 50)).createShadow(img);
    }   
}



