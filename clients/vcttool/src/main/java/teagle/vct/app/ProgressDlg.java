package teagle.vct.app;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

/**
 * Auto-hiding dialog that notifies the user that a blocking operation is in
 * progress (usually when talking to the repository).
 * 
 * In principle, the usage pattern is: write your blocking code in an anonymous
 * ProgressJob's run() method (a Runnable that can throw exceptions), then pass
 * it to this class. It will show an explanatory dialog, then run the blocking
 * code in a new thread, wait for it to finish, then hide the dialog.
 * 
 * Improvements are:
 * 
 * * you can create just one dialog at startup, then show/hide it instead of
 * creating/destroying it every time. * you can avoid showing it if the
 * operation takes very little (on the order of hundreds of milliseconds) * you
 * can avoid hiding it and showing it between consecutive requests
 * 
 * Except the first one, the rest are transparent.
 * 
 * Exceptions thrown in the threaded code are propagated to the method that
 * passed the ProgressJob instance, but wrapped in a ProgressException.
 */
public class ProgressDlg {
	private final Shell dlg;
	private final Label label;
	private final Display display;

	private boolean jobReady;
	private ProgressException jobException;

	public ProgressDlg(final Shell parent) {
		this(parent, "Operation in progress");
	}

	/**
	 * Create a new progress dialog with an initial notification text.
	 */
	public ProgressDlg(final Shell parent, final String text) {
		this.display = parent.getDisplay();

		this.dlg = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL
				| SWT.RESIZE);
		this.dlg.setLayout(new GridLayout());
		this.dlg.setText("Operation in progress");

		this.label = new Label(this.dlg, SWT.NONE);
		this.label.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		this.label.setText(text);

		final ProgressBar bar = new ProgressBar(this.dlg, SWT.INDETERMINATE);
		bar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		this.dlg.pack();
		this.dlg.open(); // a setVisible(false) is also probably needed here

		final Point p = this.dlg.getSize();
		if (p.x > 400)
			this.dlg.setSize(400, p.y);
	}

	/**
	 * Destroy the progress dialog. You will typically just call this at exit.
	 */
	public void close() {
		// Util.waitShell(dlg);
		this.dlg.close();
	}

	/**
	 * Code that should be started from a thread, that runs a given blocking job
	 * and notifies the UI event loop when the job finished.
	 */
	class RunAndWait implements Runnable {
		private final ProgressJob job;

		public RunAndWait(final ProgressJob job) {
			this.job = job;
		}

		public void run() {
			final ProgressException[] ex_f = { null };

			try {
				this.job.run();
			} catch (final Exception e) {
				ex_f[0] = new ProgressException(e);
			} finally {
				ProgressDlg.this.display.asyncExec(new Runnable() {
					public void run() {
						ProgressDlg.this.jobReady = true;
						ProgressDlg.this.jobException = ex_f[0];
						ProgressDlg.this.display.wake();
					}
				});
			}
		}
	}

	/**
	 * Runs runnable in a new thread, and hides the dlg after the thread exits.
	 * 
	 * @param text
	 *            the notification message to show
	 * @param runnable
	 *            the job to run
	 */
	public void run(final String text, final ProgressJob runnable)
			throws ProgressException {
		this.label.setText(text);
		this.jobReady = false;
		this.jobException = null;

		// to avoid showing the dialog for fast operations, only show this after
		// a short delay
		this.display.timerExec(500, new Runnable() {
			public void run() {
				if (!ProgressDlg.this.jobReady)
					ProgressDlg.this.dlg.setVisible(true);
			}
		});

		new Thread(new RunAndWait(runnable)).start();

		while (!(this.dlg.isDisposed() || this.jobReady))
			if (!this.display.readAndDispatch())
				this.display.sleep();

		if (this.dlg.isDisposed())
			throw new ProgressException("Dialog closed by user.");

		// to avoid repeated show/hide with consecutive operations, only hide
		// this after a short delay
		this.display.timerExec(200, new Runnable() {
			public void run() {
				if (ProgressDlg.this.jobReady)
					ProgressDlg.this.dlg.setVisible(false);
			}
		});

		if (this.jobException != null)
			throw this.jobException;
	}
}
