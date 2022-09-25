import javax.swing.JFrame;
import view.CalculatorScreen;

public class Main {
    public static void main(String[] args) {
        //Create JFrame
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(500, 100);
        window.setResizable(false);

        //Create calculator visual
        var screen = new CalculatorScreen(window);
        screen.init();

        window.pack();
        window.setVisible(true);
    }
}
