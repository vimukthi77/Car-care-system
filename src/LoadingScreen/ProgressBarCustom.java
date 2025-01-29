package LoadingScreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class ProgressBarCustom extends JProgressBar {

    private Color colorString = new Color(0, 0, 0);

    public Color getColorString() {
        return colorString;
    }

    public void setColorString(Color colorString) {
        this.colorString = colorString;
    }

    public ProgressBarCustom() {
        setPreferredSize(new Dimension(100, 10)); // Adjust the height as needed
        setBackground(new Color(77, 77, 77));
        setForeground(new Color(190, 190, 190));
        setUI(new BasicProgressBarUI() {

            @Override
            protected void paintString(Graphics grphcs, int i, int i1, int i2, int i3, int i4, Insets insets) {
                grphcs.setColor(getColorString());
                super.paintString(grphcs, i, i1, i2, i3, i4, insets);
            }

            @Override
            protected void paintIndeterminate(Graphics g, JComponent c) {
                if (!(g instanceof Graphics2D)) {
                    return;
                }

                Graphics2D g2d = (Graphics2D) g;

                // Enable antialiasing for smoother edges
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int barRectWidth = progressBar.getWidth();
                int barRectHeight = progressBar.getHeight();

                int arcWidth = barRectHeight / 2;   // Adjust as needed
                int arcHeight = barRectHeight / 2;  // Adjust as needed

                // Fill the rounded rectangle for indeterminate progress
                g2d.setColor(progressBar.getForeground());
                g2d.fillRoundRect(10, 10, barRectWidth, barRectHeight, arcWidth, arcHeight);

                // Paint the text
                if (progressBar.isStringPainted()) {
                    paintString(g2d, 0, 0, barRectWidth, barRectHeight, 0, progressBar.getInsets());
                }
            }
        });
    }
}
