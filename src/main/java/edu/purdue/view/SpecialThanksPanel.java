package edu.purdue.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
public class SpecialThanksPanel extends JPanel{
    private JLabel dev;
    private JButton ret;
    private JLabel special_thanks;
    public SpecialThanksPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //header
        constraints.insets = new Insets(0, 50, 50, 0);
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        dev = new JLabel("DEVELOPERS");
        this.add(dev,constraints);
        special_thanks = new JLabel("SPECIAL THANKS");


        dev.setFont(new Font("Serif", Font.PLAIN, 18));
        special_thanks.setFont(new Font("Serif", Font.PLAIN, 18));

        //text
        JTextArea txt = new JTextArea();
        JTextArea txt1 = new JTextArea();
        txt.setEditable(false);

        txt.setWrapStyleWord(true);
        txt.setBorder(new EmptyBorder(0,20,10,20));
        txt.setFont(UIManager.getFont("Label.font"));
        txt.setBackground(Color.WHITE);
        txt1.setEditable(false);

        txt1.setWrapStyleWord(true);
        txt1.setBorder(new EmptyBorder(0,10,10,10));
        txt1.setFont(UIManager.getFont("Label.font"));
        txt1.setBackground(Color.WHITE);
//        JScrollPane scr = new JScrollPane(txt);
//        this.add(txt);
//        this.add(scr);
        txt.append("RAN GUO\nMATTHEW DER\nKEVIN SHI\nCHIRAG NATH");
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(txt,constraints);

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(special_thanks,constraints);
        txt1.setText("Sound effects: https://www.epidemicsound.com/track/QTBrZageyY/");
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(txt1,constraints);
        //buttons
        ret = new JButton("Return to Main Screen");

        ret.setBackground(Color.blue);
        ret.setForeground(Color.white);

        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(ret,constraints);

    }

    public JLabel getDev() { return dev; }

    public void setDev(JLabel dev) { this.dev = dev; }

    public JLabel getSpecial_thanks() { return special_thanks; }

    public void setSpecial_thanks(JLabel special_thanks) { this.special_thanks = special_thanks; }

    public JButton getRet() { return ret; }

    public void setRet(JButton ret) { this.ret = ret; }

}
