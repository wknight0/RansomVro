import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class LossForm extends JFrame {
    private ImageIcon backgroundImage;
    private JLabel titleLabel;
    private int remainingSeconds = 3;

    public LossForm() {// Setting the title of the JFrame
        super("RansomVro");

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingSeconds--;
                if (remainingSeconds <= 0) {
                    ((Timer) e.getSource()).stop();
                    dispose();
                }
            }
        });
        timer.start();

        // Load the background image
        backgroundImage = new ImageIcon(getClass().getResource("/vro3.jpg")); // Provide the path to your image file

        // Create and set properties for text label
        titleLabel = new JLabel("You do not fw vro (◕︵◕)");
        titleLabel.setSize(400, 100);
        Font font = titleLabel.getFont();
        titleLabel.setFont(new Font(font.getFontName(), Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Creating a panel for the background image
        JPanel backgroundPanel = new JPanel(new GridLayout(1, 1, 20, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Adding components to the background panel
        backgroundPanel.add(titleLabel);

        pack();

        // Adding the background panel to the frame
        add(backgroundPanel);

        // Setting the default close operation
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Making the frame non-resizable
        setResizable(false);

        // Set the size of the frame
        setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // Making the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Creating an instance of CustomJFrameWithBackground
        new LossForm();
    }
}