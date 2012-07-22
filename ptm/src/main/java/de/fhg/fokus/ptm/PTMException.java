package de.fhg.fokus.ptm;

/*
Copyright (C) 2010 FhG Fokus

This file is part of the open source Teagle implementation.

Licensed under the Apache License, Version 2.0 (the "License"); 

you may not use this file except in compliance with the License. 

You may obtain a copy of the License at 



http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 

distributed under the License is distributed on an "AS IS" BASIS, 

WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 

See the License for the specific language governing permissions and 

limitations under the License. 

For further information please contact teagle@fokus.fraunhofer.de
*/

public class PTMException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PTMException(String msg)
	{
		super(msg);
	}
	
	public PTMException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

	public PTMException(Throwable e)
	{
		super(e);
	}
}
