/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package miniproject;
import javax.swing.JFrame;
/**
 *
 * @author Raymond Cox <rj.cox101 at gmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        PriorityQueue awesomeQueue = new PriorityQueue();

        for (int i=0; i<24; i+=4) {
            PriorityItem item = new PriorityItem(String.valueOf(i), i);
            awesomeQueue.enqueue(item);
        }

        awesomeQueue.enqueue(new PriorityItem("SEVEN", 7));
        awesomeQueue.enqueue(new PriorityItem("ONE", 1));
        awesomeQueue.printList();
    }

}
