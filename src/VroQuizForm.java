import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class VroQuizForm extends JFrame {
    private Random random;
    private ImageIcon backgroundImage;
    private JLabel titleLabel;
    private JLabel question1Label;
    private JTextField question1Field;

    private JLabel question2Label;
    private JTextField question2Field;
    private JLabel question3Label;
    private JTextField question3Field;
    private JLabel question4Label;
    private JTextField question4Field;
    private JLabel question5Label;
    private JTextField question5Field;
    private JButton clickButton;
    private int remainingSeconds = 10;
    private boolean victoryConditions = false;


    public VroQuizForm() {// Setting the title of the JFrame
        super("RansomVro");

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingSeconds--;
                if (remainingSeconds >= 0) {
                    clickButton.setText(String.valueOf(remainingSeconds));
                } else {
                    ((Timer) e.getSource()).stop();
                    LossForm lossForm = new LossForm();
                    lossForm.setVisible(true);
                    dispose();
                }
            }
        });

        // Load the background image
        backgroundImage = new ImageIcon(getClass().getResource("/vro2.jpg")); // Provide the path to your image file

        titleLabel = new JLabel("Answer 5 easy questions in 10 seconds!");
        titleLabel.setSize(400, 100);
        Font font = titleLabel.getFont();
        titleLabel.setFont(new Font(font.getFontName(), Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Insets insets = new Insets(0, 100, 0, 100);

        random = new Random();
        int question1A = random.nextInt() * 1000;
        int question1B = random.nextInt() * 1000;
        int question1Answer = (question1A * question1B);

        int question2A = random.nextInt() * 1000;
        int question2B = random.nextInt() * 1000;
        int question2Answer = (question2A * question2B);

        int question3A = random.nextInt() * 1000;
        int question3B = random.nextInt() * 1000;
        int question3Answer = (question3A * question3B);

        int question4A = random.nextInt() * 1000;
        int question4B = random.nextInt() * 1000;
        int question4Answer = (question4A * question4B);

        int question5A = random.nextInt() * 1000;
        int question5B = random.nextInt() * 1000;
        int question5Answer = (question5A * question5B);

        question1Label = new JLabel("Question 1: " + question1A + " * " + question1B + " = ?");
        question1Field = new JTextField();

        question2Label = new JLabel("Question 2: " + question2A + " * " + question2B + " = ?");
        question2Field = new JTextField();

        question3Label = new JLabel("Question 3: " + question3A + " * " + question3B + " = ?");
        question3Field = new JTextField();

        question4Label = new JLabel("Question 4: " + question4A + " * " + question4B + " = ?");
        question4Field = new JTextField();

        question5Label = new JLabel("Question 5: " + question5A + " * " + question5B + " = ?");
        question5Field = new JTextField();

        // Creating a button
        clickButton = new JButton("10");
        clickButton.setBorder(new EmptyBorder(200, 200, 200, 200));
        clickButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((question1Field.getText().equals(String.valueOf(question1Answer))) && (question2Field.getText().equals(String.valueOf(question2Answer))) && (question3Field.getText().equals(String.valueOf(question3Answer))) && (question4Field.getText().equals(String.valueOf(question4Answer))) && (question5Field.getText().equals(String.valueOf(question5Answer)))) {
                    victoryConditions = true;
                }

                if (victoryConditions) {
                    timer.stop();
                    WinForm winForm = new WinForm();
                    winForm.setVisible(true);
                    dispose();
                }
            }
        });

        // Creating a panel for the background image
        JPanel backgroundPanel = new JPanel(new GridLayout(12, 1, 20, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Adding components to the background panel
        backgroundPanel.add(titleLabel);
        backgroundPanel.add(question1Label, BorderLayout.CENTER);
        backgroundPanel.add(question1Field, BorderLayout.CENTER);
        backgroundPanel.add(question2Label, BorderLayout.CENTER);
        backgroundPanel.add(question2Field, BorderLayout.CENTER);
        backgroundPanel.add(question3Label, BorderLayout.CENTER);
        backgroundPanel.add(question3Field, BorderLayout.CENTER);
        backgroundPanel.add(question4Label, BorderLayout.CENTER);
        backgroundPanel.add(question4Field, BorderLayout.CENTER);
        backgroundPanel.add(question5Label, BorderLayout.CENTER);
        backgroundPanel.add(question5Field, BorderLayout.CENTER);
        backgroundPanel.add(clickButton);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right)) ;

        timer.start();

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
        new VroQuizForm();
    }
}