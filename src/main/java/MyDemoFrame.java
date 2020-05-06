import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MyDemoFrame extends JFrame {

    public JButton inputButton;
    public JTextField textField;

    public MyDemoFrame(String title) {
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BorderLayout());

        inputButton = new JButton("Nothing");
        textField = new JTextField("");

        panel.add(textField, BorderLayout.NORTH);
        panel.add(inputButton, BorderLayout.SOUTH);

        textField.getDocument().addDocumentListener(new DateFormatDocumentListener(textField));

        setContentPane(panel);
        setPreferredSize(new Dimension(130, 100));
        pack();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
