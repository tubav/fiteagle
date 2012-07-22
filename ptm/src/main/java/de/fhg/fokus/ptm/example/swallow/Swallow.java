package de.fhg.fokus.ptm.example.swallow;

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

import java.util.HashMap;
import java.util.Map;

import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.server.AbstractResource;
import de.fhg.fokus.ptm.server.BasicAbstractResourceAdapter;

//This time we implement a distinct class for our resource, 
//since we want it to have some specific functionalities
public class Swallow extends AbstractResource 
{
	//Obviously, the one thing swallows absolutely shine in is carrying coconuts
	private Resource coconut;
	
	//counter, from which we will generate names. 
	//Obviously, in real life, this would have to be persisted somehow
	public static int counter = 0;
	
	public Swallow(BasicAbstractResourceAdapter adapter, Resource coconut)
		throws PTMException 
	{
		super(Integer.toString(counter++), adapter);
		if (coconut == null)
			//If no coconut has been provided, create one.
			//parameters are: parent, name, typename, config, owner
			coconut = this.getClient().addResource(this.getParent(), null, "coconut", null, this);
		else
			//indicate, that this Swallow instance uses that coconut and would like for it to keep existing
			coconut.acquire(this);
		
		this.coconut = coconut;
	}

	@Override
	public void _delete() throws PTMException 
	{
		//indicate, that we will not use this coconut any longer
		//if we held the last reference to it, the coconut will be deleted as well
		coconut.release(this);
	}

	@Override
	public Map<String, Object> _getConfiguration() throws PTMException 
	{
		//calculate our airspeed based on the weight of the coconut we carry
		int airspeed = (Integer)coconut.getAttribute("weight") ^ 42;
		
		Map<String, Object> config = new HashMap<String, Object>();
		config.put("coconut", coconut);
		config.put("airspeed", airspeed);
		return config;
	}

	@Override
	public void _setAttribute(String name, Object value) throws PTMException 
	{
		throw new PTMException("Sorry, swallows are read only");
	}

	public void fly()
	{
		//this method is exported and can be used by other resources / resource adapters
	}
}
