package org.jc.swing;

/**
 * Copyright (c) 2006, Sun Microsystems, Inc
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   * Neither the name of the TimingFramework project nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import org.jc.image.DropShadowPanel;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import org.jdesktop.animation.timing.TimingController;
import org.jdesktop.animation.timing.TimingTarget;

/**
 * @author Shannon Hickey
 */
public class CoolButton extends JButton implements TimingTarget {
    private static boolean REPAINT_SHADOW = true;

    private static final Color COLOR1 = new Color(125, 161, 237);
    private static final Color COLOR2 = new Color(91, 118, 173);

    private int style;
    private float pct;
    private boolean forward;

    private TimingController cont = new TimingController(200, this);

    private final MouseAdapter MLISTENER = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent me) {
            cont.stop();
            forward = true;
            cont.start();
        }

        @Override
        public void mouseExited(MouseEvent me) {
            cont.stop();
            forward = false;
            cont.start();
        }
    };

    public void begin() {
    }

    public void end() {
        if (forward) {
            setForeground(Color.WHITE);
            pct = 1.0f;
        } else {
            setForeground(COLOR2);
            pct = 0.0f;
        }
        Container p = getParent();
        if (p instanceof DropShadowPanel && REPAINT_SHADOW) {
            ((DropShadowPanel)p).propertyChange(null);
        } else {
            repaint();
        }
    }

    public void timingEvent(long cycleElapsedTime, long totalElapsedTime, float fraction) {
        if (forward) {
            pct = fraction;
            int r = COLOR2.getRed() + (int) ((Color.WHITE.getRed() - COLOR2.getRed()) * pct);
            int g = COLOR2.getGreen() + (int) ((Color.WHITE.getGreen() - COLOR2.getGreen()) * pct);
            int b = COLOR2.getBlue() + (int) ((Color.WHITE.getBlue() - COLOR2.getBlue()) * pct);
            setForeground(new Color(r, g, b));
        } else {
            pct = 1.0f - fraction;
            int r = COLOR2.getRed() + (int) ((Color.WHITE.getRed() - COLOR2.getRed()) * pct);
            int g = COLOR2.getGreen() + (int) ((Color.WHITE.getGreen() - COLOR2.getGreen()) * pct);
            int b = COLOR2.getBlue() + (int) ((Color.WHITE.getBlue() - COLOR2.getBlue()) * pct);
            setForeground(new Color(r, g, b));
        }
        Container p = getParent();
        if (p instanceof DropShadowPanel && REPAINT_SHADOW) {
            ((DropShadowPanel)p).propertyChange(null);
        } else {
             //repaint();
        }
    }

    public CoolButton() {
    }

    public CoolButton(int style) {
        super();
        this.style = style;
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(COLOR2);
        setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addMouseListener(MLISTENER);
        //setFont(getFont().deriveFont(Font.BOLD));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();

        int h = getHeight();
        int w = getWidth();

        float tran = 0.1f + pct * 0.9f;

        GradientPaint GP = new GradientPaint(0, 0, COLOR1, 0, h, COLOR2, true);
        g2d.setPaint(GP);


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint p1;
        GradientPaint p2;

        if (getModel().isPressed()) {
            p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1, new Color(100, 100, 100));
            p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3, new Color(255, 255, 255, 100));
        } else {
            p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1, new Color(0, 0, 0));
            p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0, h - 3, new Color(0, 0, 0, 50));
        }

        if (style == 0) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, tran));
            RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1, h - 1, 20, 20);
            Shape clip = g2d.getClip();
            g2d.clip(r2d);
            g2d.fillRect(0, 0, w, h);
            g2d.setClip(clip);
            g2d.setPaint(p1);
            g2d.drawRoundRect(0, 0, w - 1, h - 1, 20, 20);
            g2d.setPaint(p2);
            g2d.drawRoundRect(1, 1, w - 3, h - 3, 18, 18);
            g2d.dispose();
        } else if (style == 1) {
            RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1 + 20, h - 1, 20, 20);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, tran));
            Shape clip = g2d.getClip();
            g2d.clip(r2d);
            g2d.fillRect(0, 0, w, h);
            g2d.setClip(clip);
            g2d.setPaint(p1);
            g2d.drawRoundRect(0, 0, w - 1 + 20, h - 1, 20, 20);
            g2d.setPaint(p2);
            g2d.drawRoundRect(1, 1, w - 3 + 20, h - 3, 18, 18);
            g2d.setPaint(p1);
            g2d.drawLine(w - 1, 1, w - 1, h);
            g2d.setPaint(p2);
            g2d.drawLine(w - 2, 2, w - 2, h - 1);
            g2d.dispose();
        } else if (style == 2) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, tran));
            g2d.fillRect(0, 0, w, h);
            g2d.setPaint(p1);
            g2d.drawRect(-5, 0, w - 1 + 10, h - 1);
            g2d.setPaint(p2);
            g2d.drawRect(-5, 1, w - 3 + 10, h - 3);
            g2d.setPaint(p1);
            g2d.drawLine(0, 0, 0, h);
            g2d.drawLine(w - 1, 1, w - 1, h);
            g2d.setPaint(p2);
            g2d.drawLine(1, 2, 1, h);
            g2d.drawLine(w - 2, 2, w - 2, h - 1);
            g2d.dispose();
        } else if (style == 3) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, tran));
            RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(-20, 0, w - 1 + 20, h - 1, 20, 20);
            Shape clip = g2d.getClip();
            g2d.clip(r2d);
            g2d.fillRect(0, 0, w, h);
            g2d.setClip(clip);
            g2d.setPaint(p1);
            g2d.drawRoundRect(-20, 0, w - 1 + 20, h - 1, 20, 20);
            g2d.setPaint(p2);
            g2d.drawRoundRect(1 - 20, 1, w - 3 + 20, h - 3, 18, 18);
            g2d.setPaint(p1);
            g2d.drawLine(0, 1, 0, h);
            g2d.setPaint(p2);
            g2d.drawLine(1, 2, 1, h - 1);
            g2d.dispose();
        }

        super.paintComponent(g);
    }
}
