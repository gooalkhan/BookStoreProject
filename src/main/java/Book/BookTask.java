package Book;

import java.sql.Date;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

import common.MyTask;

public class BookTask extends MyTask {
	LinkedBlockingQueue<Object[]> pipe;
	Vector<Object[]> result;
	int goal;
	String condition;
	String searchWord;
	Thread dbThread;
	int limit;

	public BookTask(String condition, String searchWord) {
		this(condition, searchWord, Integer.MAX_VALUE);
	}

	public BookTask(String condition, String searchWord, int limit) {
		this.pipe = new LinkedBlockingQueue<>();
		this.result = new Vector<Object[]>();
		this.condition = condition;
		this.searchWord = searchWord;
		this.limit = limit;
	}

	@Override
	protected Vector<Object[]> doInBackground() throws Exception {
		double counter = 0;
		int progress = 0;
		setProgress(progress);

		this.dbThread = new Thread(new BookSearchThread(this, this.condition, this.searchWord, this.limit));
		dbThread.start();

		loop: while (true) {
			Object[] obj = pipe.take();

			if (obj == null) {
				continue;
			}

			if (obj[0] instanceof common.PoisonPill) {
				break loop;
			} else {
				counter++;
				progress = (int) ((counter / (double) goal) * 100);
				// System.out.println(progress);
				setProgress(progress);

				String isbn = (String) obj[0];
				String title = (String) obj[1];
				String author = (String) obj[2];
				String publisher = (String) obj[3];
				Date pdate = (Date) obj[4];
				String description = (String) obj[5];
				int discount = (int) obj[6];
				String pic = (String) obj[7];
				// System.out.println("adding row :%d".formatted(counter));
				Thread.sleep(1);
				result.add(new Object[] { isbn, title, author, publisher, pdate, description, discount, pic });
			}
		}
		dbThread.join();
		return result;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public LinkedBlockingQueue<Object[]> getQueue() {
		return this.pipe;
	}

	@Override
	protected void done() {
		if (dbThread != null && dbThread.isAlive()) {
			dbThread.interrupt();
			// TODO Auto-generated method stub
			// System.out.println("work done");
		}
		dbThread=null;
	}
}