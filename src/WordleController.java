import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//controller sends info to the Subject, which will then update the views
public class WordleController implements ActionListener {
    private WordleModel model;
    private String word;

    public WordleController(WordleModel model) {
        this.model = model;
        while ((word == null) || word.equals("") || (word.length() != 5) || (containsNums(word))) {
            word = JOptionPane.showInputDialog(null, "Please enter 5 letter word to be guessed.", "Input", JOptionPane.QUESTION_MESSAGE);
        }
        model.setWord(word);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField text = (JTextField) e.getSource();
        String wordToCheck = text.getText();
        text.setText("");
        if (wordToCheck.length() == 5) {
            model.checkPlay(wordToCheck);
        } else {
            JOptionPane.showMessageDialog(null, "please enter 5 letter words");
        }
    }

    private boolean containsNums(String word) {
        for(int i=0; i < word.length(); i++) {
            if (Character.isDigit(word.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}