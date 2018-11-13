package renetik.android.lang;

import static renetik.android.java.collections.CSListKt.list;
import static renetik.android.lang.CSLang.doLater;
import renetik.android.java.collections.CSList;
import renetik.android.java.callback.CSRun;

public class CSRunConsolidator implements CSRun {

	private final CSList<CSRun> runnables = list();
	private final int miliseconds;
	private boolean isRunning;

	public CSRunConsolidator(int miliseconds) {
		this.miliseconds = miliseconds;
	}

	public void invoke(CSRun runnable) {
		if (isRunning)
			runnables.add(runnable);
		else {
			runnable.run();
			isRunning = true;
			doLater(miliseconds, this);
		}
	}

	@Override
	public void run() {
		if (runnables.getHasItems()) {
			runnables.removeLast().run();
			doLater(miliseconds, this);
		} else isRunning = false;
	}

}