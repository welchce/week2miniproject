
package miniproject;
import javax.swing.SwingUtilities;

public class Main {

    /**
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
