import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordleModel implements Subject{
    private List<WordleView> views;
    //tempword will be used to check for double letters. reset each turn
    private String word, tempWord;
    private String[] letterStatus;

    public WordleModel() {
        views = new ArrayList<>();
        letterStatus = new String[5];
        Arrays.fill(letterStatus, "");
        word = "";
    }

    @Override
    public void attach(WordleView v){
        views.add(v);
    }

    @Override
    public void detach(WordleView v){
        views.add(v);
    }
    @Override
    public void notifyViews(String wordToCheck){
        for (WordleView v: views){
            v.update(letterStatus, wordToCheck);
        }
    }

    public void checkPlay(String wordToCheck){
        tempWord = word;
        wordToCheck = wordToCheck.toLowerCase();
        //restart the status of each letter at the start of the turn
        Arrays.fill(letterStatus, "");
        for (int i = 0; i < tempWord.length(); i++) {
            if(checkLetterInSpot(wordToCheck.charAt(i),i)){
                letterStatus[i] = "green";
            } else if(checkLetterInWord(wordToCheck.charAt(i))){
                //make sure that the status hasn't already been set to green already
                if (letterStatus[i].equals(""))
                    letterStatus[i] = "yellow";
            } else {
                letterStatus[i] = "grey";
            }
        }
        notifyViews(wordToCheck);
    }
    private boolean checkLetterInSpot(char c, int index){
        if (c == word.charAt(index)) {
            //replace the capital letter to make sure that we don't count it twice
            tempWord = tempWord.replaceFirst(c+"", Character.toUpperCase(c)+"");
            return true;
        }
        return false;
    }

    private boolean checkLetterInWord(char c){
        if (tempWord.contains(""+c)) {
            //replace the capital letter to make sure that we don't count it twice
            tempWord = tempWord.replaceFirst(c+"", Character.toUpperCase(c)+"");
            return true;
        }
        return false;
    }

    //sets the word we're trying to guess
    public void setWord(String word) {
        this.word = word.toLowerCase();
    }

    //used for test
    public String[] getLetterStatus() {
        return letterStatus;
    }
}