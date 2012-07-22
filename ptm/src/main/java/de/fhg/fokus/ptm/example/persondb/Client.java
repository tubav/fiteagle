package de.fhg.fokus.ptm.example.persondb;

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



public class Client
{

	/**
	 * @param args
	 * @throws MalformedURLException 
	 * @throws PTMException 
	 */
/*	public static void main(String[] args) throws MalformedURLException, PTMException
	{	
		PTMClient client = new PTMClient(new URL("http://localhost:8000"));
		UUID persondb = new UUID("/persondb/");
		
		HashMap<String, Object> config = new HashMap<String, Object>();
		config.put("city", "Kitchener");
		Resource r = client.addRemoteResource(persondb, "bob", "Person", config);
		System.out.println(r.getUUID());
		for (Map.Entry<String, Object> e : r.getConfiguration().entrySet())
			System.out.println(e.getKey() + " - " + e.getValue());
		Person p = (Person)r;
		System.out.println(p.getName());
		System.out.println(p.getCity());
	}*/
}
