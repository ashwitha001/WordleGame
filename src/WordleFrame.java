import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//this would be the observer because it is waiting on the model to update it, needs to subscribe
public class WordleFrame extends JFrame implements WordleView{
    private JButton[][] gridButtons;
    private JTextField guessTextField;
    private JPanel gridPanel, guessPanel;
    private int numPlay; //keep track of the number of guesses

    public WordleFrame(){
        super("Wordle");
        numPlay = 0;

        WordleModel model = new WordleModel();
        model.attach(this);
        WordleController controller = new WordleController(model);

        setLayout(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(5,5));
        gridButtons = new JButton[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JButton b = new JButton();
                b.addActionListener(controller);
                b.setEnabled(false);
                gridPanel.add(b);
                gridButtons[i][j] = b;
            }
        }
        gridPanel.setPreferredSize(new Dimension(300,300));

        guessPanel = new JPanel();
        guessTextField = new JTextField();
        guessTextField.addActionListener(controller);
        guessTextField.setPreferredSize(new Dimension(390,30));
        guessPanel.add(guessTextField);

        add(gridPanel, BorderLayout.CENTER);
        add(guessPanel, BorderLayout.SOUTH);

        this.setSize(400,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void update(String[] letterStatus, String word) {
        for (int i = 0; i < letterStatus.length; i++) {
            gridButtons[numPlay][i].setText(word.charAt(i)+"");
            switch (letterStatus[i]) {
                case "green" -> gridButtons[numPlay][i].setBackground(new Color(16, 166, 16));
                case "yellow" -> gridButtons[numPlay][i].setBackground(new Color(190, 170, 36));
                case "grey" -> gridButtons[numPlay][i].setBackground(new Color(128, 133, 127));
            }
        }

        Set<String> status = new HashSet<>(Arrays.asList(letterStatus));
        if ((status.size() == 1) && (letterStatus[0].equals("green"))) {
            JOptionPane.showMessageDialog(this, "Congrats, you won!");
            dispose();
        } else {
            if (numPlay == 4) {
                JOptionPane.showMessageDialog(this, "boo, you lost!");
                dispose();
            }
        }
        numPlay++;
    }
    public static void main(String[] args) {
        WordleFrame f = new WordleFrame();
    }
}