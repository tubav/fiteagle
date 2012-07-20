package org.teagle.vcttool.control;

import org.eclipse.swt.widgets.Display;
import org.teagle.vcttool.app.ProgressException;
import org.teagle.vcttool.app.ProgressJob;
import org.teagle.vcttool.view.ProgressDialogView;
import org.teagle.vcttool.view.VctToolView;

public class ProgressDialogController {
	private final Display display;
	private final ProgressDialogView dlg;
	private boolean jobReady;
	private ProgressException jobException;

	public ProgressDialogController(final VctToolView parent, final String text) {
		this.display = parent.getShell().getDisplay();
		this.dlg = new ProgressDialogView(parent.getShell(), text);
	}

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
				ProgressDialogController.this.display.asyncExec(new Runnable() {
					public void run() {
						ProgressDialogController.this.jobReady = true;
						ProgressDialogController.this.jobException = ex_f[0];
						ProgressDialogController.this.display.wake();
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
	public void run(final ProgressJob runnable) throws ProgressException {

		this.jobReady = false;
		this.jobException = null;

		// to avoid showing the dialog for fast operations, only show this after
		// a short delay
		this.display.timerExec(500, new Runnable() {
			public void run() {
				if (!ProgressDialogController.this.jobReady)
					ProgressDialogController.this.dlg.setVisible(true);
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
				if (ProgressDialogController.this.jobReady)
					ProgressDialogController.this.dlg.setVisible(false);
			}
		});

		if (this.jobException != null)
			throw this.jobException;
	}
}
