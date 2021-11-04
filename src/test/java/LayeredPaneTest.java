import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LayeredPaneTest {

    public static void main(String[] args) {
        JLabel label = new JLabel("base text");
        label.setBounds(200, 200, 100, 100);
        JLayeredPane pane = new JLayeredPane();
        pane.setBounds(0, 0, 500, 500);
        JPanel pausePanel = new PracticePanel();
        pausePanel.setBounds(200, 200, 200, 200);
        JButton pauseButton = new JButton("pause button");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("pause button press");
                pausePanel.setVisible(false);
            }
        });
        pausePanel.add(pauseButton);
        JButton gameButton = new JButton("game button");
        gameButton.setBounds(200, 200, 50, 50);
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("game button press");
                pausePanel.setVisible(true);
            }
        });
        //pane.add(label, Integer.valueOf(0));
        pane.add(gameButton, Integer.valueOf(1));
        pane.add(pausePanel, Integer.valueOf(2));
        JFrame frame = new JFrame();
        frame.add(pane);
        frame.setLayout(null);
        frame.setSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
