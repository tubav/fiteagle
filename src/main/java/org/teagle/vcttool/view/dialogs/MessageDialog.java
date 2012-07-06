package org.teagle.vcttool.view.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageDialog {
	
	private String myMessage = "";

	private int result = SWT.CANCEL;
	
	public MessageDialog(Shell shell, String message) 
	{
		this(shell, message, SWT.ICON_INFORMATION);
	}
	
	public MessageDialog(Shell shell, String message, int style) 
	{    
	    myMessage = message;
	    
	    MessageBox messageBox = new MessageBox(shell, style);
	    messageBox.setMessage(myMessage);
	    result = messageBox.open();

//	    display.dispose();
	  }
	
	public void setMessage(String mes){
		this.myMessage = mes;
	}
	
	public int getResult()
	{
		return result;
	}
}
