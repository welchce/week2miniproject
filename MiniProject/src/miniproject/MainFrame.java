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
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Christopher
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class MainFrame extends JFrame {
    final String WINDOW_TITLE = "Priority Queue";
    final JButton _startButton = new JButton("Start");
    final JButton _executeAllButton = new JButton("Execute All");
    final JButton _insertEvent = new JButton("Insert Event");
    final JButton _executeNext = new JButton("Execute Next");
    PriorityQueue _PQueue;
    JTextArea _executeLog;
    JPanel _questionPanel;
    int _prevPriority=0;

    public MainFrame() {
        this.setTitle(WINDOW_TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.createGUI();
        this.addActions();
        this.pack();
        this.setLocationRelativeTo(null);
        _startButton.requestFocus();
    }

    private void addActions() {
        _startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startExecution();
                executePriorityItem();
            }
        });

        _executeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (!_PQueue.isEmpty()) executePriorityItem();
                stopExecution();
            }
        });

        _insertEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                char name[] = new char[6];
                try {
                    while (name.length > 5)
                        name = JOptionPane.showInputDialog("What is the event name (can only be 5 characters)?").toCharArray();
                    int priority = -1;
                    while (priority < _prevPriority || priority > 200)
                        priority = Integer.valueOf(JOptionPane.showInputDialog("Priority( >= " + _prevPriority + " and <= 200)", _prevPriority));
                    PriorityItem insertItem = new PriorityItem(name, priority);
                    _PQueue.enqueue(insertItem);
                    executePriorityItem();
                } catch (java.lang.Exception e) {
                    System.out.println("User pressed cancel");
                }
            }
        });

        _executeNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!_PQueue.isEmpty()) executePriorityItem();
                else { stopExecution(); }
            }
        });
    }

    private void createGUI() {
        this.setLayout(new BorderLayout());
        JPanel executeStopButtons = new JPanel();
        _executeAllButton.setEnabled(false);
        executeStopButtons.add(_startButton, BorderLayout.WEST);
        executeStopButtons.add(_executeAllButton, BorderLayout.EAST);
        _questionPanel = new JPanel();
        _questionPanel.setLayout(new BorderLayout());
        JLabel execPaused = new JLabel("Would you like to " +
                "insert an additional event or execute the next event?", JLabel.CENTER);
        execPaused.setForeground(Color.RED);
        _questionPanel.add(execPaused, BorderLayout.NORTH);
        JPanel questionButtons = new JPanel();
        questionButtons.add(_insertEvent);
        questionButtons.add(_executeNext);
        _questionPanel.add(questionButtons, BorderLayout.CENTER);
        _executeLog = new JTextArea();
        _executeLog.setEditable(false);
        _executeLog.setLineWrap(true);
        _executeLog.setWrapStyleWord(true);
        _executeLog.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        JScrollPane logScroll = new JScrollPane(_executeLog);
        this.add(_questionPanel, BorderLayout.NORTH);
        this.add(logScroll, BorderLayout.CENTER);
        this.add(executeStopButtons, BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(570,500));
        _questionPanel.setVisible(false);
    }

    private char[] genRandomChars() {
        Random Randy = new Random(System.currentTimeMillis());
        char[] randomChars = new char[5];
        for(int iStr=0; iStr<5;iStr++){
            randomChars[iStr] = (char)(Randy.nextInt(25)+97);
        }
        return randomChars;
    }

    private void printPriorityItem(String step, PriorityItem pItem) {
        String newText, item, priority;
        if (pItem != null) {
            item = String.valueOf(pItem.getItem());
            priority = String.valueOf(pItem.getPriority());
        } else {
            step = "None";
            item = "None";
            priority = "None";
        }
            newText =  "\nStep: " + step +
                       "\nItem: " +item +
                       "\nPriority: " + priority +
                       "\nTotal Items: " + _PQueue.getSize() + "\n";
            _executeLog.setText(_executeLog.getText()+newText);
    }

    private void startExecution() {
        _questionPanel.setVisible(true);
        _executeAllButton.setEnabled(true);
        _startButton.setEnabled(false);
        _executeLog.setText("");
        _PQueue = new PriorityQueue();
        Random Randy = new Random(System.currentTimeMillis());
        for(int i =0;i<20;i++) {
            int randPriority = Randy.nextInt(50);
            _PQueue.enqueue(new PriorityItem(genRandomChars(),randPriority));
        }
    }

    private void stopExecution() {
        _questionPanel.setVisible(false);
        _executeAllButton.setEnabled(false);
        _startButton.setEnabled(true);
        _executeLog.setText(_executeLog.getText()+"-----------------------");
        _executeLog.setText(_executeLog.getText()+"\nEnd Of Queue");
    }

    private void executePriorityItem() {
        _executeLog.setText(_executeLog.getText()+"-----------------------");
        Random Randy = new Random(System.currentTimeMillis());
        PriorityItem executedItem = _PQueue.dequeue();
        _prevPriority = executedItem.getPriority();
        printPriorityItem("Execute Item", executedItem);
        int probPart2 = Math.abs(Randy.nextInt())%10;
        if (probPart2 <= 7) {
            int newPriority;
            String step;
            if(probPart2>=0 && probPart2<=4) {
                newPriority = executedItem.getPriority()+executedItem.getPriority()*3;
                step = "Enqueue Item a";
            } else {
                newPriority = executedItem.getPriority()+executedItem.getPriority()*10;
                step = "Enqueue Item b";
            }
            if (newPriority > 200) newPriority = 200;
            PriorityItem randItem = new PriorityItem(genRandomChars(),newPriority);
            _PQueue.enqueue(randItem);
            printPriorityItem(step, randItem);
        }
        else {
            printPriorityItem("none", null);
        }
    }


}
