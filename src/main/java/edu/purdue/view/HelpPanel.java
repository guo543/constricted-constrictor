package edu.purdue.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
public class HelpPanel extends JPanel{
    private JLabel instructions;
    private JButton ret;
    private JButton start;
    private BufferedImage image;
    public HelpPanel() {
        //header
        instructions = new JLabel("How to play");
        this.add(instructions,BOTTOM_ALIGNMENT);


        instructions.setFont(new Font("Serif", Font.PLAIN, 18));
//        instructions.setBounds(300, 220, 500, 60);

//        setLayout(new BorderLayout());

        //text
        JTextArea txt = new JTextArea();
        txt.setEditable(false);
//        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setBorder(new EmptyBorder(0,10,10,10));
        txt.setFont(UIManager.getFont("Label.font"));
        txt.setBackground(Color.WHITE);
        JScrollPane scr = new JScrollPane(txt);
        this.add(txt);
        this.add(scr);
        txt.setText("Use the arrow keys on your keyboard to navigate " +
                "the snake to collect beans and increase your score as you progress. ");

        //buttons
        ret = new JButton("Return to Main Screen");
        start = new JButton("Start");
        ret.setBackground(Color.blue);
        ret.setForeground(Color.white);
        start.setBackground(Color.red);
        start.setForeground(Color.white);

        this.add(ret,BOTTOM_ALIGNMENT);
        this.add(start,BOTTOM_ALIGNMENT);


        //image
        try {
            image = ImageIO.read(new File("images/Controls.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,50,200,this);
    }

    public JLabel getInstructions() { return instructions; }

    public void setInstructions(JLabel instructions) { this.instructions = instructions; }

    public JButton getRet() { return ret; }

    public void setRet(JButton ret) { this.ret = ret; }

    public JButton getStart() { return start; }

    public void setStart(JButton start) { this.start = start; }

    public BufferedImage getImage() { return image; }

    public void setImage(BufferedImage image) { this.image = image; }
}
