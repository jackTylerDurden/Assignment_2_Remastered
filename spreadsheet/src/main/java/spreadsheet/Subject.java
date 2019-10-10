package spreadsheet;
public interface Subject{
    public void register(Cell observer);
    public void notifyObserver();
}