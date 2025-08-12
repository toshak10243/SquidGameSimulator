import gui.MainMenu;
import gui.AdminLoginUI;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new AdminLoginUI(); // Pehle Admin Login UI
        });
    }
}
