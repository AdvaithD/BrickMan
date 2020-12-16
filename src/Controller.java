import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Controller{
    JFrame mainFrame = new JFrame();
    WelcomeScreen initScreen = new WelcomeScreen();
    JPanel panelCont = new JPanel();
    GameModel gamePlay = new GameModel();


    public Controller() {
        mainFrame.add(gamePlay); //
        mainFrame.setBounds(10, 10, 695, 650); // coordinate bounds for the object
        mainFrame.setTitle("Brick Man");
        mainFrame.setResizable(false); // make it non resizeable so the box is already sized
        mainFrame.setVisible(true); // make frame visible
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Controller();
            }
        });
    }
}
