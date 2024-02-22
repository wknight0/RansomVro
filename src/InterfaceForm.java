import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class InterfaceForm extends JFrame {
    private ImageIcon backgroundImage;
    private JLabel titleLabel;
    private JTextArea textArea;
    private JTextArea warningArea;
    private JButton clickButton;



    public InterfaceForm() {// Setting the title of the JFrame
        super("RansomVro");

        // Load the background image
        backgroundImage = new ImageIcon(getClass().getResource("/vro.jpg")); // Provide the path to your image file


        titleLabel = new JLabel("RansomVro v1.1.0");
        titleLabel.setSize(400, 100);
        Font font = titleLabel.getFont();
        titleLabel.setFont(new Font(font.getFontName(), Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setHighlighter(null);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText("Vro has confiscated and encrypted all of your files 【≽ܫ≼】. Vro will cast a spell and bring your files back, but only if you fw him and take his little quiz ╰( ⁰ ਊ ⁰ )━☆ﾟ.*･｡ﾟ");
        textArea.setFont(new Font(font.getFontName(), Font.PLAIN, 20));
        textArea.setBorder(new EmptyBorder(20, 20, 20, 20));
        textArea.setOpaque(false);
        textArea.setBackground(new Color(0, 0, 0, 0));

        warningArea = new JTextArea();
        warningArea.setEditable(false);
        warningArea.setFocusable(false);
        warningArea.setHighlighter(null);
        warningArea.setLineWrap(true);
        warningArea.setWrapStyleWord(true);
        warningArea.setText("Take the test by clicking the button. If you close this file, you will lose your data indefinitely.");
        warningArea.setFont(new Font(font.getFontName(), Font.PLAIN, 20));
        warningArea.setBorder(new EmptyBorder(20, 20, 20, 20));
        warningArea.setOpaque(false);
        warningArea.setBackground(new Color(0, 0, 0, 0));

        // Creating a button
        clickButton = new JButton("Take Test");

        clickButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VroQuizForm vroQuizForm = new VroQuizForm();
                vroQuizForm.setVisible(true);
                dispose();
            }
        });

        // Creating a panel for the background image
        JPanel backgroundPanel = new JPanel(new GridLayout(4, 1, 20, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Adding components to the background panel
        backgroundPanel.add(titleLabel);
        backgroundPanel.add(textArea, BorderLayout.CENTER);
        backgroundPanel.add(warningArea, BorderLayout.CENTER);
        backgroundPanel.add(clickButton);

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
        new InterfaceForm();
    }
}