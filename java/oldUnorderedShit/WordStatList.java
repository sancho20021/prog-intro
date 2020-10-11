import IntList.IntList;

public class WordStatList {
    private int count;
    private int lastLine;
    private IntList list;

    public WordStatList() {
        count = 0;
        list = new IntList();
        lastLine = -1;
    }

    public void add(int currentLine, int currentPosition) {
        if (currentLine != lastLine) {
            list.add(currentPosition);
            lastLine = currentLine;
        }
        count++;
    }

    @Override
    public String toString() {
        return count + " " + list;
    }
}
