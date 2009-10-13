
package miniproject;
import javax.swing.SwingUtilities;

public class Main {

    /**
     * @author Raymond Cox <rj.cox101 at gmail.com>
     *
     * This is the main function of MiniProject.  The main function
     * calls the GUI and runs the program.
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }

}
