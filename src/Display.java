import javax.swing.*;
import java.awt.*;

public class Display {

    public int screenWidth;

    public int screenHeight;

    public Display() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
    }
    public void show() {
        JFrame frame = createFrame();
        JPanel mainPanel = createMainPanel();
        GridBagConstraints c;



        JPanel leftPanel = createSidePanel();
        c = customizeLeftPanel();

        mainPanel.add(leftPanel, c);

        JPanel rightPanel = createSidePanel();
        c = customizeRightPanel();

        mainPanel.add(rightPanel, c);

        frame.add(mainPanel);
        displayFrame(frame);

    }


    private GridBagConstraints customizeLeftPanel() {
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = screenHeight;
        c.weightx = 0.7;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 3;
        c.gridwidth = 2;

        return c;
    }
    private GridBagConstraints customizeRightPanel() {
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = screenHeight;
        c.weightx = 0.3;
        c.gridx = 3;
        c.gridy = 0;
        c.gridheight = 3;
        c.gridwidth = 1;

        return c;

    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

//        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        return panel;
    }

    private JPanel createSidePanel() {
        JPanel panel = new JPanel();

        JLabel text = new JLabel("testing 123");
        text.setHorizontalAlignment(JLabel.CENTER);
        panel.add(text);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        return panel;
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame("Planner");

        frame.setSize(screenWidth, screenHeight);
        return frame;
    }

    private void displayFrame(JFrame frame) {
//        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
