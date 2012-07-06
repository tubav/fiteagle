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
 * Auto-hiding dialog that notifies the user that a blocking operation
 * is in progress (usually when talking to the repository).
 * 
 * In principle, the usage pattern is: write your blocking code in
 * an anonymous ProgressJob's run() method (a Runnable that can throw 
 * exceptions), then pass it to this class. It will show an explanatory
 * dialog, then run the blocking code in a new thread, wait for it to 
 * finish, then hide the dialog.
 * 
 * Improvements are: 
 *  
 *   * you can create just one dialog at startup, then show/hide it 
 *     instead of creating/destroying it every time.
 *   * you can avoid showing it if the operation takes very little (on
 *     the order of hundreds of milliseconds)
 *   * you can avoid hiding it and showing it between consecutive requests
 *   
 * Except the first one, the rest are transparent.
 * 
 * Exceptions thrown in the threaded code are propagated to the method that
 * passed the ProgressJob instance, but wrapped in a ProgressException.
 */
public class ProgressDlg 
{
	private Shell dlg;
	private Label label;
	private Display display;
	
	private boolean jobReady;
	private ProgressException jobException;

	public ProgressDlg(Shell parent) 
	{
		this(parent, "Operation in progress");
	}
	
	/** 
	 * Create a new progress dialog with an initial notification text.
	 */
	public ProgressDlg(Shell parent, String text) 
	{
		display = parent.getDisplay();
		
		dlg = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
		dlg.setLayout(new GridLayout());
		dlg.setText("Operation in progress");
		
		label = new Label(dlg, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		label.setText(text);
		
		ProgressBar bar = new ProgressBar(dlg, SWT.INDETERMINATE);
		bar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		dlg.pack();
		dlg.open(); // a setVisible(false) is also probably needed here 
		
		Point p = dlg.getSize();
		if (p.x > 400) 
			dlg.setSize(400, p.y);
	}

	/**
	 * Destroy the progress dialog. You will typically just call this at exit.
	 */
	public void close() {
		//Util.waitShell(dlg);
		dlg.close();
	}

	/**
	 * Code that should be started from a thread, that runs a given blocking
	 * job and notifies the UI event loop when the job finished.
	 */
	class RunAndWait implements Runnable {
		private ProgressJob job;
		
		public RunAndWait(ProgressJob job) {
			this.job = job;
		}
		
		public void run() {
			final ProgressException [] ex_f = {null};
			
			try {
				job.run();
			} catch (Exception e) {
				ex_f[0] = new ProgressException(e);
			} finally {
				display.asyncExec(new Runnable() {
					public void run() {
						jobReady = true;
						jobException = ex_f[0];
						display.wake();
					}
				});
			}
		}
	}
	
	/**
	 * Runs runnable in a new thread, and hides the dlg after the thread exits.
	 * @param text the notification message to show
	 * @param runnable the job to run
	 */
	public void run(String text, ProgressJob runnable) throws ProgressException {
		label.setText(text);
		jobReady = false;
		jobException = null;

		// to avoid showing the dialog for fast operations, only show this after a short delay
		display.timerExec(500, new Runnable() {
			public void run() { if (! jobReady) dlg.setVisible(true); }
		});

		new Thread(new RunAndWait(runnable)).start();

		while (! (dlg.isDisposed() || jobReady))
			if (! display.readAndDispatch()) 
				display.sleep();
		
		if (dlg.isDisposed())
			throw new ProgressException("Dialog closed by user.");
		
		// to avoid repeated show/hide with consecutive operations, only hide this after a short delay
		display.timerExec(200, new Runnable() {
			public void run() {	if (jobReady) dlg.setVisible(false); }
		});
		
		if (jobException!=null)
			throw jobException;
	}
}
