package common;

import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public interface MyListenerAttachable {
    JProgressBar getProgressBar();
    JLabel getLblStatus();
    MyTable getTable();
    void setTask(MyTask task);
    void removeTask();
    void openReadAllWindow(Vector<Object[]> rows);
}
