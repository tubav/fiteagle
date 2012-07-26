package org.teagle.vcttool.app;

import org.easymock.EasyMock;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Shell;
import org.junit.Assert;
import org.teagle.vcttool.view.CommandListener;
import org.teagle.vcttool.view.IVctToolView;
import org.teagle.vcttool.view.VctToolView;

public class VctToolAppTest {

	private IVctToolView view;

	//@Before
	public void setup() {
		//@todo: to be finished
		
		view = EasyMock.createMock(VctToolView.class);
		// view.getShell().open();

		EasyMock.expect(view.getSelectionPane()).andStubReturn(
				EasyMock.createMock(CTabFolder.class));
		EasyMock.expect(view.getShell()).andReturn(
				EasyMock.createMock(Shell.class));
		
		EasyMock.replay(view);
		
		//view.getShell();
		//EasyMock.expectLastCall();
		
		view.addCommandListener(EasyMock.isA(CommandListener.class));
		EasyMock.expectLastCall();
	}

	//@Test
	public void testVctToolApp() {
		final VctToolApp app = new VctToolApp(null);
		Assert.assertNotNull(app);
		app.start(view);
	}

}
