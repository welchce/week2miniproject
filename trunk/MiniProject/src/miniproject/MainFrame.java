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
 */
public class MainFrame extends JFrame {
    String WINDOW_TITLE = "Priority Queue";
    final JButton _executeButton = new JButton("Execute");
    final JButton _stopButton = new JButton("Stop");
    final JButton _insertEvent = new JButton("Insert Event");
    final JButton _continueExecution = new JButton("Continue Execution");
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
        _executeButton.requestFocus();
    }

    private void addActions() {
        _executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _stopButton.setEnabled(true);
                _executeButton.setEnabled(false);
                _executeLog.setText("");
                createPriorityQueue();
                executePriorityItem();
            }
        });

        _stopButton.addActionListener(new ActionListener() {
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
                    while (priority < _prevPriority)
                        priority = Integer.valueOf(JOptionPane.showInputDialog("Priority( >= " + _prevPriority + ")", _prevPriority));
                    PriorityItem insertItem = new PriorityItem(name, priority);
                    _PQueue.enqueue(insertItem);
                    executePriorityItem();
                } catch (java.lang.Exception e) {
                    System.out.println("Pressed cancel");
                }
            }
        });

        _continueExecution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executePriorityItem();
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
        JLabel execPaused = new JLabel("Would you like to " +
                "insert an additional event or continue?", JLabel.CENTER);
        execPaused.setForeground(Color.RED);
        _questionPanel.add(execPaused, BorderLayout.NORTH);
        JPanel questionButtons = new JPanel();
        questionButtons.add(_insertEvent);
        questionButtons.add(_continueExecution);
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
        char[] randomCharacters = new char[5];
        for(int iStr=0; iStr<5;iStr++){
            randomCharacters[iStr] = (char)((Math.abs(Randy.nextInt())%25) +97);
        }
        return randomCharacters;
    }

    private void printPriorityItem(String step, PriorityItem pItem) {
        String newText = "Step: " + step +
                         "\nItem: " + String.valueOf(pItem.getItem()) +
                         "\nPriority: " + String.valueOf(pItem.getPriority()+"\n");
        _executeLog.setText(_executeLog.getText()+newText);
    }

    private void createPriorityQueue() {
        _PQueue = new PriorityQueue();
        Random Randy = new Random(System.currentTimeMillis());
        for(int i =0;i<20;i++) {
            int randPriority = Math.abs(Randy.nextInt())%50;
            _PQueue.enqueue(new PriorityItem(genRandomChars(),randPriority));
        }
    }

    private void stopExecution() {
        _stopButton.setEnabled(false);
        _executeButton.setEnabled(true);
        _questionPanel.setVisible(false);
        _executeLog.setText(_executeLog.getText()+"End of queue");
    }

    private void executePriorityItem() {
        if (!_PQueue.isEmpty()) {
            _questionPanel.setVisible(false);
            Random Randy = new Random(System.currentTimeMillis());
            PriorityItem executedItem = _PQueue.dequeue();
            printPriorityItem("Executed item", executedItem);
            int probPart2 = Math.abs(Randy.nextInt())%10;
            if (probPart2 <= 7) {
                int newPriority;
                String step;
                if(probPart2>=0 && probPart2<=4) {
                    newPriority = executedItem.getPriority()+executedItem.getPriority()*3;
                    step = "a";
                } else {
                    newPriority = executedItem.getPriority()+executedItem.getPriority()*10;
                    step = "b";
                }
                if (newPriority > 200) newPriority = 200;
                PriorityItem RandomlyGeneratedItem = new PriorityItem(genRandomChars(),newPriority);
                printPriorityItem(step, RandomlyGeneratedItem);
                _PQueue.enqueue(RandomlyGeneratedItem);
            }
            else {
                _executeLog.setText(_executeLog.getText()+"step: do nothing\n");
            }
            _executeLog.setText(_executeLog.getText()+"-----------------------\n");
            _prevPriority = executedItem.getPriority();
            _questionPanel.setVisible(true);
        } else {
            stopExecution();
        }
    }


}
