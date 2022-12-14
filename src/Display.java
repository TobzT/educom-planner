import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import models.*;

public class Display {

    public int screenWidth;
    public int screenHeight;
    public JFrame frame;
    public JPanel mainPanel;
    public JPanel leftPanel;
    public JPanel leftTopPanel;
    public JPanel leftBottomPanel;
    public JPanel hour1;
    public JPanel hour2;
    public JPanel hour3;
    public JPanel hour4;
    public JPanel hour5;
    public JPanel hour6;
    public JPanel rightPanel;
    public JComboBox menu;
    public JButton studentButton;
    public JButton teacherButton;
    public JButton applyButton;
    public JLabel leftPanelText;
    public boolean showTeachers = false;

    public Student[] students = {new Student("Student 1"), new Student("Student 2"), new Student("Student 3"), new Student("Student 4"), new Student("Student 5"), new Student("Student 6"), new Student("Student 7"), new Student("Student 8"), new Student("Student 9"), new Student("Student 10")};
    public Teacher[] teachers = {new Teacher("Teacher 1"), new Teacher("Teacher 2"), new Teacher("Teacher 3")};
    public String sampleText = students[0].getName();
    public GridBagConstraints c;
    public Lesson testLesson;

    private MouseAdapter mouseAdapter = getHourMouseListener();

    private int id = 1;

    public Display() {
        setScreenSizeVars();
        frame = createFrame();
        TPanel teacherPanel = createTPanel(id);
        TPanel[] studentPanels = {createTPanel(id), createTPanel(id), createTPanel(id)};
        testLesson = new Lesson(teacherPanel, studentPanels);

    }
    public void show() {
        frame.getContentPane().removeAll();
        frame.repaint();

        mainPanel = createMainPanel();

        leftPanel = createSubPanel();

        // Add stuff to leftPanel here:

            leftTopPanel = createSubPanel();

            // Add stuff to leftTopPanel here
            leftPanelText = new JLabel(sampleText);
            leftPanelText.setFont(new Font("Ariel", Font.PLAIN, 40));
            leftPanelText.setHorizontalAlignment(JLabel.CENTER);
            leftTopPanel.add(leftPanelText);

            // Customize and add leftTopPanel to leftPanel
            c = customizeLeftTopPanel();
            leftPanel.add(leftTopPanel, c);


            leftBottomPanel = createSubPanel();

            // Add stuff to leftBottomPanel
//                createPlanner(); // also adds the planner to the left bottom panel


            generateLessonPanel(testLesson, new Coordinate(0, 0));
            generateLessonPanel(testLesson, new Coordinate(1, 0));
            generateLessonPanel(testLesson, new Coordinate(0, 1));
            // Customize and add leftBottomPanel to leftPanel
            c = customizeLeftBottomPanel();
            leftPanel.add(leftBottomPanel, c);




        // Customize and add leftPanel to mainPanel
        c = customizeLeftPanel();
        mainPanel.add(leftPanel, c);

        rightPanel = createSubPanel();

        // Add stuff to rightPanel here:
            createMenu(); // also adds the menu to the right panel


            applyButton = createButton("Apply", 150, 70);
            applyButton.addActionListener(e -> {
                Person person = (Person) menu.getSelectedItem();
                leftPanelText.setText(person.getName());
            });
            c = customizeApplyButton();
            rightPanel.add(applyButton, c);

            studentButton = createButton("Students", 100, 70);
            studentButton.setBackground(Color.BLUE);
            studentButton.addActionListener(e -> {showTeachers = false; Person person = (Person)menu.getSelectedItem(); sampleText = person.getName(); show();});
            c = customizeStudentButton();
            rightPanel.add(studentButton, c);

            teacherButton = createButton("Teachers", 100, 70);
            teacherButton.setBackground(Color.ORANGE);
            teacherButton.addActionListener(e -> {showTeachers = true; Person person = (Person)menu.getSelectedItem(); sampleText = person.getName(); rightPanel.revalidate(); rightPanel.repaint();});
            c = customizeTeacherButton();
            rightPanel.add(teacherButton, c);



        // Customize and add rightPanel to mainPanel
        c = customizeRightPanel();
        mainPanel.add(rightPanel, c);

        frame.add(mainPanel);
        displayFrame(frame);

    }

    private void generateLessonPanel(Lesson lesson, Coordinate coordinate) { // TODO

        Coordinate[] subCoords = {
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(0, 2)
        };

        JPanel panel = createSubPanel();

        TPanel teacherPanel = createTPanel(id);

        JLabel label = new JLabel(teacherPanel.getLabelText());
        c = customizeTeacherLabel();
        teacherPanel.add(label, c);
        teacherPanel.setBackground(Color.RED);
        teacherPanel.addMouseListener(mouseAdapter);
        c = customizeTeacherPanel();
        panel.add(teacherPanel, c);

        JPanel studentPanel = createSubPanel();



        for(int j = 0; j < 3; j++) {
            TPanel panel1 = createTPanel(id);
            JLabel label1 = new JLabel(panel1.getLabelText());
            c = customizeTeacherLabel();
            panel1.add(label1, c);
            panel1.setBackground(Color.GREEN);
            panel1.addMouseListener(mouseAdapter);
            Coordinate subCoordinate = subCoords[j];
            c = customizeSubStudentPanel(subCoordinate.getX(), subCoordinate.getY());
            studentPanel.add(panel1, c);

        }
        c = customizeStudentPanel();
        panel.add(studentPanel, c);



        c = customizeHour(coordinate.getX(), coordinate.getY());
        leftBottomPanel.add(panel, c);
    }

    private JButton createButton(String text, int width, int height) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));

        return button;
    }
    private JComboBox createComboBox(Person[] arr) {
        JComboBox box = new JComboBox(arr);
        box.setSelectedIndex(0);
        return box;
    }

    private MouseAdapter getHourMouseListener() {
        return new MouseAdapter() {
            private Color background;
//            private Color getBackground() {
//                return background;
//            }
            private void setBackground(Color color) {
                background = color;
            }
            @Override
            public void mousePressed(MouseEvent e) {

                System.out.println("Mouse Pressed");
                TPanel panel = (TPanel) e.getSource();
                setBackground(panel.getBackground());
                panel.setBackground(Color.black);

                panel.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                TPanel panel = (TPanel) e.getSource();
                System.out.println(panel.getId());
                Person person = (Person) menu.getSelectedItem();
                panel.setLabelText(person.getName());
                panel.setBackground(background);
                panel.revalidate();
                panel.repaint();

                System.out.println("Mouse Released");

            }
        };
    }
    private void createPlanner() {
        setId(1);
        hour1 = createSubPanel();
        hour2 = createSubPanel();
        hour3 = createSubPanel();
        hour4 = createSubPanel();
        hour5 = createSubPanel();
        hour6 = createSubPanel();
        JPanel[] panels = {hour1, hour2, hour3, hour4, hour5, hour6};
        Coordinate[] coords = {
                new Coordinate(0, 0),
                new Coordinate(1, 0),
                new Coordinate(0, 1),
                new Coordinate(1, 1),
                new Coordinate(0, 2),
                new Coordinate(1, 2)
        };

        Coordinate[] subCoords = {
                new Coordinate(0, 0),
                new Coordinate(0, 1),
                new Coordinate(0, 2)
        };

        MouseAdapter mouseAdapter = getHourMouseListener();
        for(int i = 0; i < panels.length; i++ ) {
            JPanel panel = panels[i];

            TPanel teacherPanel = createTPanel(id);

            JLabel label = new JLabel(teacherPanel.getIdString());
            c = customizeTeacherLabel();
            teacherPanel.add(label, c);
            teacherPanel.setBackground(Color.RED);
            teacherPanel.addMouseListener(mouseAdapter);
            c = customizeTeacherPanel();
            panel.add(teacherPanel, c);

            JPanel studentPanel = createSubPanel();



            for(int j = 0; j < 3; j++) {
                TPanel panel1 = createTPanel(id);
                JLabel label1 = new JLabel(panel1.getIdString());
                c = customizeTeacherLabel();
                panel1.add(label1, c);
                panel1.setBackground(Color.GREEN);
                panel1.addMouseListener(mouseAdapter);
                Coordinate coordinate = subCoords[j];
                c = customizeSubStudentPanel(coordinate.getX(), coordinate.getY());
                studentPanel.add(panel1, c);

            }
            c = customizeStudentPanel();
            panel.add(studentPanel, c);

            Coordinate coord = coords[i];

            c = customizeHour(coord.getX(), coord.getY());
            leftBottomPanel.add(panel, c);
        }
    }

    private GridBagConstraints customizeTeacherLabel() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        return c;
    }
    private GridBagConstraints customizeTeacherPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 3;
        return c;
    }
    private GridBagConstraints customizeStudentPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 1;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 3;
        return c;
    }
    private GridBagConstraints customizeSubStudentPanel(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.3333;
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 3;
        c.gridheight = 1;
        return c;
    }
    private GridBagConstraints customizeLeftTopPanel(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.15;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        return c;
    }
    private GridBagConstraints customizeLeftBottomPanel(){
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.85;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.gridheight = 2;
        return c;
    }
    private GridBagConstraints customizeHour(int gridx, int gridy) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.weighty = 0.333333;
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        return c;
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

    private TPanel createTPanel(int id) {
        JLabel label = new JLabel("empty");
        TPanel panel = new TPanel(id, label);
        setId(getId() + 1);
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        return panel;
    }

    private JPanel createSubPanel() {
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
        screenHeight = screenSize.height - 40;
    }

    private int getId() {
        return this.id;
    }

    private void setId(int id) {
        this.id = id;
    }
}
