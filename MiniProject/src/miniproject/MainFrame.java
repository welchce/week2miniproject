/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miniproject;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.util.Random;
import java.lang.Math;

/**
 *
 * @author Christopher
 */
public class MainFrame extends JFrame {
    String WINDOW_TITLE = "Priority Queue";
    final JButton _executeButton = new JButton("Execute");
    final JButton _stopButton = new JButton("Stop");
    final JButton _insertEvent = new JButton("Insert Event");
    final JButton _resumeExecution = new JButton("Resume Execution");
    PriorityQueue _PQueue  = new PriorityQueue();
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
        _executeButton.requestFocus();
    }

    private char[] getRandomChars(){
        Random Randy = new Random(System.currentTimeMillis());
        char[] randomCharacters = new char[5];
        for(int iStr=0; iStr<5;iStr++){
            randomCharacters[iStr] = (char)((Math.abs(Randy.nextInt())%25) +97);
        }
        return randomCharacters;
    }

    private void addText(String St) {
        _executeLog.setText(_executeLog.getText() + "\n" + St);
    }
    private void executePriorityQueue() {
        Random Randy = new Random(System.currentTimeMillis());
        for(int i =0;i<20;i++) {
            _PQueue.enqueue(new PriorityItem(getRandomChars(),Math.abs(Randy.nextInt())%50));
        }
        //_PQueue.printList();
        PriorityItem executedItem = _PQueue.dequeue();
        addText("Item Executed: " + String.valueOf(executedItem.getItem()) +
                            "\nPriority: " + String.valueOf(executedItem.getPriority()));
        int probPart2 = Math.abs(Randy.nextInt())%10;
        if(probPart2>=0 && probPart2<=4) {
            PriorityItem RandomlyGeneratedItem = new PriorityItem(getRandomChars(),(executedItem.getPriority()+executedItem.getPriority()*3));
            _PQueue.enqueue(RandomlyGeneratedItem);
            addText("Item Added: " + String.valueOf(RandomlyGeneratedItem.getItem()) +
                            "\nPriority: " + String.valueOf(RandomlyGeneratedItem.getPriority()));
        } else if (probPart2 >= 5 && probPart2 <= 7) {
            PriorityItem RandomlyGeneratedItem = new PriorityItem(getRandomChars(),(executedItem.getPriority()+executedItem.getPriority()*10));
            _PQueue.enqueue(RandomlyGeneratedItem);
            addText("Item Added: " + String.valueOf(RandomlyGeneratedItem.getItem()) +
                            "\nPriority: " + String.valueOf(RandomlyGeneratedItem.getPriority()));
        } else {
            addText("No random event was generated.");
        }
    }

    private void addActions() {
        _executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Execute Worked");
                _stopButton.setEnabled(true);
                _executeButton.setEnabled(false);
                executePriorityQueue();
            }
        });

        _stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Stop Worked");
                _stopButton.setEnabled(false);
                _executeButton.setEnabled(true);
            }
        });

        _insertEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Insert Worked");
            }
        });

        _resumeExecution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Resume Worked");
            }
        });
    }

    private void createGUI() {
        this.setLayout(new BorderLayout());
        JPanel executeStopButtons = new JPanel();
        _stopButton.setEnabled(false);
        executeStopButtons.add(_executeButton, BorderLayout.WEST);
        executeStopButtons.add(_stopButton, BorderLayout.EAST);
        _questionPanel = new JPanel();
        _questionPanel.setLayout(new BorderLayout());
        JLabel execPaused = new JLabel("Execution paused, would you like to " +
                "insert an additional event or resume?", JLabel.CENTER);
        execPaused.setForeground(Color.RED);
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
        //_questionPanel.setVisible(false);
    }
}
