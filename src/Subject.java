public interface Subject {
    void attach(WordleView v);
    void detach(WordleView v);
    void notifyViews(String wordToCheck);
}