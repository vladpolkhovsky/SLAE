package matrix;

public interface Matrix {
    double get(int i, int j);
    double set(int i, int j, double value);
    int getLineCount();
    int getColumnCount();
}
