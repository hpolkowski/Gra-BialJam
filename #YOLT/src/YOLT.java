import javax.swing.*;


public class YOLT {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JScrollPane(new Game("src/plansza01.txt")));
        frame.pack();
        frame.setVisible(true);
        frame.setSize(815, 615);
        frame.setResizable(false);
    }
}
