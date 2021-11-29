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
    private BufferedImage controls_img;
    private BufferedImage instruction_img;
    public HelpPanel() {
        //header
        JPanel jPanel = new JPanel();
        instructions = new JLabel("How to play");
        instructions.setFont(new Font("Serif", Font.PLAIN, 24));
//        jPanel.add(instructions, TOP_ALIGNMENT);
//        this.setBorder(BorderFactory.createEmptyBorder(30, 10, 50, 10));

        //image


        try {
            controls_img = ImageIO.read(new File("images/Controls.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }
        try {
            instruction_img = ImageIO.read(new File("images/instructions.png"));
        } catch (IOException ex) {
            // handle exception...
        }


        //buttons
        ret = new JButton("Return to Main Screen");
        start = new JButton("Start");
        ret.setBackground(Color.blue);
        ret.setForeground(Color.white);
        start.setBackground(Color.red);
        start.setForeground(Color.white);


//        this.add(Box.createHorizontalGlue());
        jPanel.add(ret);
//        jPanel.add(Box.createRigidArea(new Dimension(2, 0)));
        jPanel.add(start);
        setLayout(new BorderLayout());
        this.add(instructions, BorderLayout.NORTH);
        add(jPanel,BorderLayout.SOUTH);
//        this.add(ret,BOTTOM_ALIGNMENT);
//        this.add(start,BOTTOM_ALIGNMENT);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(controls_img,50,200,this);
        g.drawImage(instruction_img,50,50,this);

    }

    public JLabel getInstructions() { return instructions; }

    public void setInstructions(JLabel instructions) { this.instructions = instructions; }

    public JButton getRet() { return ret; }

    public void setRet(JButton ret) { this.ret = ret; }

    public JButton getStart() { return start; }

    public void setStart(JButton start) { this.start = start; }

    public BufferedImage getControls_img() { return controls_img; }

    public void setControls_img(BufferedImage controls_img) { this.controls_img = controls_img; }

    public BufferedImage getInstruction_img() { return instruction_img; }

    public void setInstruction_img(BufferedImage instruction_img) { this.instruction_img = instruction_img; }

//    public Container getContentPane() { return contentPane; }
}
