package common;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class MyTaskListener implements PropertyChangeListener {
	private MyListenerAttachable window;
	private MyTask task;
	private MyTable table;
	private JLabel lblStatus;
	private JProgressBar progressBar;
	private JDialogType t;

	public MyTaskListener(MyListenerAttachable window, MyTask task, JDialogType t) {
		this.window = window;
		this.task = task;
		this.table = window.getTable();
		this.lblStatus = window.getLblStatus();
		this.progressBar = window.getProgressBar();
		this.t = t;
	}

	public MyTaskListener(MyListenerAttachable window, MyTask task) {
		this(window, task, null);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String name = evt.getPropertyName();
		if (name.equals("progress")) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
			lblStatus.setText(String.format("Completed %d%% of task.\n", task.getProgress()));
		} else if ("state".equals(name)) {
			if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
				if (task.isCancelled()) {
					lblStatus.setText("작업이 취소되었습니다");
					window.removeTask();
				} else {
					if (t != null & t == JDialogType.ReadAll) {
						try {
						Vector<Object[]> v = task.get();
						lblStatus.setText("%d개의 작업이 완료되었습니다".formatted(v.size()));
						window.openReadAllWindow(v);
						window.removeTask();
						} catch (InterruptedException | ExecutionException e1) {
							e1.printStackTrace();
						}
					} else {
						try {
							Vector<Object[]> v = task.get();
							table.addRows(v);
							lblStatus.setText("%d개의 작업이 완료되었습니다".formatted(v.size()));
							window.removeTask();
						} catch (InterruptedException | ExecutionException e1) {
							// TODO Auto-generated catch block
							lblStatus.setText("에러가 발생하였습니다");
							e1.printStackTrace();
						}
					}
				}
			}
		}
	}

}
