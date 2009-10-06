/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miniproject;
import javax.swing.*;
/**
 *
 * @author Christopher
 */
public class GUI {
    public static void main(String[] args)
    {
        JFrame mainFrame = new JFrame("MiniProject");
        mainFrame.show();
        mainFrame.setLayout(null);

        // Implemented to either initially execute or stop events
        JButton btnExecute = new JButton("Execute");
        JButton btnStop = new JButton("Stop");

        // Implemented to ask user for new event
        JLabel lblNewEvent = new JLabel("Would you like to enter a new event?");
        JButton btnNewEventYes = new JButton("Yes");
        JButton btnNewEventNo = new JButton("No");

        // Labels for when an event is executed, it will do four things
        JLabel lblStep1Character = new JLabel("");
        JLabel lblStep1Priority = new JLabel("");
        JLabel lblStep2Action = new JLabel("");
        JLabel lblStep2Character = new JLabel("");
        JLabel lblStep2Priority = new JLabel("");
        
        String[] possiblePriorities = new String[201];
        int dummyPR = 0;
        for(int i = dummyPR; i <= 200; i++)
        {
            possiblePriorities[i-dummyPR] = String.valueOf(i);
        }
        JComboBox ddbPriorities = new JComboBox(possiblePriorities);
        ddbPriorities.setSelectedIndex(0);
    }
}
