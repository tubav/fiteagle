package de.fhg.fokus.ptm.marshaller;

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

import java.util.HashSet;
import java.util.Set;

import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.Owners;
import de.fhg.fokus.ptm.PTMException;

public class RemoteOwners implements Owners {
	private int anonymous;
	private String[] outsideOwners;
	private Identifier[] strongOwners, weakOwners;
		
	public RemoteOwners(Object answer)
		throws PTMException
	{	
		if (answer == null)
			throw new PTMException("Received no answer for owners");
		
		Object[] outside,  strong, weak;
		try
		{
			Object[] a = (Object[])answer;
			if (a.length < 4)
				throw new PTMException("Illegal owners answer length: " + a);
			anonymous = (Integer)a[0];
			outside = (Object[])a[1];
			strong = (Object[])a[2];
			weak = (Object[])a[3];
		}
		catch (ClassCastException e)
		{
			throw new PTMException("Illegal owners answer: " + answer, e);
		}
		
		if (outside == null || strong == null || weak == null)
			throw new PTMException("Received null in owners result");
			
		String s = null;
		Set<Object> s1 = new HashSet<Object>(), s2 = new HashSet<Object>();
		
		try
		{
			outsideOwners = new String[outside.length];
			
			for (int i = 0; i < outside.length; ++i)
			{
				s = checkString((String)outside[i]);
				check(s, s1, null);
				outsideOwners[i] = s;
			}
			
			s1.clear();
			strongOwners = new Identifier[strong.length];
			for (int i = 0; i < outside.length; ++i)
			{
				s = checkString((String)strong[i]);
				Identifier id = new Identifier(s);
				check(id, s1, null);
				strongOwners[i] = id;
			}
			
			weakOwners = new Identifier[weak.length];
			for (int i = 0; i < outside.length; ++i)
			{
				s = checkString((String)weak[i]);
				Identifier id = new Identifier(s);
				check(id, s2, s1);
				weakOwners[i] = id;
			}
		}
		catch (ClassCastException e)
		{
			throw new PTMException("Illegal type in owners: " + s, e);
		}		
	}
	
	private String checkString(String o) throws PTMException
	{
		if (o == null || "".equals(o))
			throw new PTMException("Empty string in owners result");
		return o;
	}
	
	private void check(Object o, Set<Object> os, Set<Object> os2) throws PTMException
	{
		if (os.contains(o))	
			throw new PTMException("Already have: " + o);
		if(os2 != null && os2.contains(o))
			throw new PTMException("Already have: " + o);
		os.add(o);
	}
	
	@Override
	public int getAnnonymousOwners() {
		return anonymous;
	}
	
	@Override
	public String[] getOutsideOwners() {
		return outsideOwners;
	}
	
	@Override
	public Identifier[] getStrongOwners() 
	{
		return strongOwners;
	}

	@Override
	public Identifier[] getWeakOwners() {
		return weakOwners;
	}
	
	@Override
	public boolean hasStrongOwners() {
		return  (getAnnonymousOwners() > 0 || getOutsideOwners().length > 0 || getStrongOwners().length > 0);
	}

}
