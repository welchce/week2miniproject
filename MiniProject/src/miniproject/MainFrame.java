/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miniproject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.BorderFactory;

/**
 *
 * @author Christopher
 */
public class MainFrame extends JFrame {
    String WINDOW_TITLE = "Priority Queue";
    JButton _executeButton, _stopButton, _insertEvent, _resumeExecution;
    JTextArea _executeLog;
    JPanel _questionPanel;
    public MainFrame()
    {
        this.setTitle(WINDOW_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.createGUI();
        this.addActions();
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void addActions() {
        
    }

    private void createGUI() {
        this.setLayout(new BorderLayout());
        JPanel executeStopButtons = new JPanel();
        _executeButton = new JButton("Execute");
        _stopButton = new JButton("Stop");
        _stopButton.setEnabled(false);
        executeStopButtons.add(_executeButton, BorderLayout.WEST);
        executeStopButtons.add(_stopButton, BorderLayout.EAST);
        _questionPanel = new JPanel();
        _questionPanel.setLayout(new BorderLayout());
        JLabel execPaused = new JLabel("Execution paused, would you like to " +
                "insert an additional event or resume?", JLabel.CENTER);
        execPaused.setForeground(Color.RED);
        _insertEvent = new JButton("Insert Event");
        _resumeExecution = new JButton("Resume");
        _questionPanel.add(execPaused, BorderLayout.NORTH);
        JPanel questionButtons = new JPanel();
        questionButtons.add(_insertEvent);
        questionButtons.add(_resumeExecution);
        _questionPanel.add(questionButtons, BorderLayout.CENTER);
        _executeLog = new JTextArea();
        _executeLog.setEditable(false);
        _executeLog.setLineWrap(true);
        _executeLog.setWrapStyleWord(true);
        _executeLog.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        this.add(_questionPanel, BorderLayout.NORTH);
        this.add(_executeLog, BorderLayout.CENTER);
        this.add(executeStopButtons, BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(570,500));
        _executeLog.setText("So I was thinking that we could hide/show the above questionPanel as needed. " +
                "\nWhen you click resume it hides and continues" +
                "\nWhen you click insert event it pops up an option pane, inserts a new event and continues" +
                "\nI haven't added click actions to any of the buttons, if you need help doing that just ask");

        //_questionPanel.setVisible(false);
    }
}
