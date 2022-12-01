import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

public class Display {

    public int screenWidth;
    public int screenHeight;
    public JFrame frame;
    public JPanel mainPanel;
    public JPanel leftPanel;
    public JPanel rightPanel;
    public JComboBox menu;
    public JButton studentButton;
    public JButton teacherButton;
    public JButton applyButton;
    public JLabel leftPanelText;
    public boolean showTeachers = false;

    public String[] students = {"Student 1", "Student 2", "Student 3", "Student 4", "Student 5", "Student 6", "Student 7", "Student 8", "Student 9", "Student 10"};
    public String[] teachers = {"Teacher 1", "Teacher 2", "Teacher 3"};
    public String sampleText = students[0];

    public Display() {
        setScreenSizeVars();
        frame = createFrame();
    }
    public void show() {
        frame.getContentPane().removeAll();
        frame.repaint();

        mainPanel = createMainPanel();
        GridBagConstraints c;

        leftPanel = createSidePanel();

        // Add stuff to leftPanel here:

        leftPanelText = new JLabel(sampleText);
        leftPanelText.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        leftPanelText.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(leftPanelText);

        // Customize and add leftPanel to mainPanel
        c = customizeLeftPanel();
        mainPanel.add(leftPanel, c);

        rightPanel = createSidePanel();

        // Add stuff to rightPanel here:
        createMenu(); // also adds the menu to the right panel

        applyButton = createButton("Test", 100, 70);
        applyButton.addActionListener(e -> leftPanelText.setText(menu.getSelectedItem().toString()));
        c = customizeApplyButton();
        rightPanel.add(applyButton, c);

        studentButton = createButton("Students", 100, 70);
        studentButton.addActionListener(e -> {showTeachers = false; sampleText = menu.getSelectedItem().toString(); show();});
        c = customizeStudentButton();
        rightPanel.add(studentButton, c);

        teacherButton = createButton("Teachers", 100, 70);
        teacherButton.addActionListener(e -> {showTeachers = true; sampleText = menu.getSelectedItem().toString(); show();});
        c = customizeTeacherButton();
        rightPanel.add(teacherButton, c);



        // Customize and add rightPanel to mainPanel
        c = customizeRightPanel();
        mainPanel.add(rightPanel, c);

        frame.add(mainPanel);
        displayFrame(frame);

    }

    private JButton createButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));

        return button;
    }
    private JComboBox createComboBox(String[] arr) {
        JComboBox box = new JComboBox(arr);
        box.setSelectedIndex(0);
        return box;
    }
    private GridBagConstraints customizeStudentButton() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        return c;
    }

    private GridBagConstraints customizeTeacherButton() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        return c;
    }


    private GridBagConstraints customizeApplyButton() {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.3;
        c.weighty = 0.8;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 3;
        return c;
    }
    private GridBagConstraints customizeMenu() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.8;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 3;

        return c;
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
        JPanel panel = new JPanel(new GridBagLayout());
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

    private void createMenu() {
        GridBagConstraints c = customizeMenu();
        if(showTeachers) {
            menu = createComboBox(teachers);

        } else {
            menu = createComboBox(students);
        }
        menu.setMaximumRowCount(10);
        rightPanel.add(menu, c);
    }

    private void setScreenSizeVars() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
    }
}
