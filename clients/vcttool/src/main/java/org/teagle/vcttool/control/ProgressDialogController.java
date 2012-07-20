package org.teagle.vcttool.control;

import org.eclipse.swt.widgets.Display;
import org.teagle.vcttool.app.ProgressException;
import org.teagle.vcttool.app.ProgressJob;
import org.teagle.vcttool.view.ProgressDialogView;
import org.teagle.vcttool.view.VctToolView;


public class ProgressDialogController {
	private Display display;
	private ProgressDialogView dlg;
	private boolean jobReady;
	private ProgressException jobException;
	
	public ProgressDialogController(VctToolView parent, String text)
	{
		display = parent.getShell().getDisplay();
		dlg = new ProgressDialogView(parent.getShell(), text);
	}
	
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
	public void run(ProgressJob runnable) throws ProgressException {

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
