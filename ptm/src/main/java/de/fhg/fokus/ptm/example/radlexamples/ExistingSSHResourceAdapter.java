package de.fhg.fokus.ptm.example.radlexamples;

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

import java.util.Map;

import de.fhg.fokus.ptm.Identifier;
import de.fhg.fokus.ptm.PTMException;
import de.fhg.fokus.ptm.Resource;
import de.fhg.fokus.ptm.server.AbstractResourceAdapter;
import de.fhg.fokus.ptm.server.GenericResource;
import de.fhg.fokus.ptm.server.Manager;

//This is an example for a generic resource adapter, which manages only one (preexisting)
//resource instance (in contrast to managing a whole type of resources). This in turn means,
//That resources can neither be instantiated or deleted. Only the preexisting instance can 
//be reconfigured. 
//This class can be used either directly or it can be derived from, to implement additional behaviour,
//like sanity checking of config parameters, etc.
//
// The XML to deploy an instance of this adapter would look somewhat like this:
// (note that this is not the config for the actual rubis resource, but for instantiating its RA)
//
// <javaresourceadapter>
//   <adapter_class type="string">de.fhg.fokus.ptm.example.radlexamples.ExistingSSHResourceAdapter</adapter_class>
//   <targetId type="string">/rubos_2</targetId>
//   <admin_ip type="string">##the_ip_of_the_rubos_machine##</admin_ip>
//   <admin_port type="port">22</admin_port>
//   <admin_username type="string">root</admin_username>
//   <admin_password type="string">secret</
// </javaresourceadapter>

public abstract class ExistingSSHResourceAdapter extends AbstractResourceAdapter
{
	private Identifier targetId;
	private String admin_ip,admin_username, admin_pw;
	private int admin_port;
	private Map<String, Object> config;
	
	//When instantiating this adapter it must be given
	//a) the id of the resource it is responsible for (targetId)
	//b) connection information and credentials for ssh access (admin_*)
	public ExistingSSHResourceAdapter(Manager manager, Identifier targetId, String admin_ip, int admin_port, String admin_username, String admin_pw) throws PTMException
	{
		super(null, manager);
		
		//Make sure, we have been given a fully qualified id, i.e. one that describes a resource instance
		if (targetId == null || targetId.isAdapter())
			throw new PTMException("Will only work with preexisting resources. Give me a full id.");
		
		this.targetId = targetId;
		this.admin_ip = admin_ip;
		this.admin_port = admin_port;
		this.admin_username = admin_username;
		this.admin_pw = admin_pw;
		manager.addAdapter(targetId, this);
	}

	@Override
	public Resource addResource(Identifier parentId, String name, String type,
			Map<String, Object> config) throws PTMException
	{
		//Since we have only declared ourselves responsible for a single resource instance,
		//and not for a type of resources, this should never be called.
		throw new PTMException("How the hell did you get here?");
	}

	public void deleteResource(Identifier identifier) throws PTMException
	{
		throw new PTMException("My resource is preexisting and I will not delete it");
	}

	@Override
	public Resource getResource(Identifier identifier) throws PTMException
	{
		//Since we have only declared ourselves responsible for targetId, nothing else should reach us
		assert(identifier.equals(targetId));
		return new GenericResource(this, targetId);
	}

	@Override
	public Resource[] listResources(Identifier parentId, String type)
			throws PTMException
	{
		return new Resource[] { new GenericResource(this, targetId) };
	}
	
	@Override
	public Map<String, Object> getConfiguration(Identifier identifier)
	{
		//Since we have only declared ourselves responsible for targetId, nothing else should reach us
		assert(identifier.equals(targetId));
		
		return config;
	}
	
	@Override
	public void setConfiguration(Identifier identifier, Map<String, Object> config)
	{
		this.config = config;
	}
	
    private void applyConf_SSH()
    {    
		System.out.println("Issue ssh to remoteMachine=" + admin_ip);
        System.out.println("Credentials user=" + admin_username +", pass="+ admin_pw );
        String cmd = "";
        
        	cmd += "ls -la";
        	;     
        	
        //at this point, you would probably get more config parameters out of our config data
        //to puzzle the ssh command together
        //cmd += config.get("whatever")
        
        System.out.println("Executing: " + cmd);
        try{
        	int port=  admin_port;
//            com.uop.ssh.Exec exc = new com.uop.ssh.Exec( config.get("admin"),  config.get("admin_pwd"), config.get("admin_ip"),  port, cmd );
  //      	System.out.println("Remote execution finished"+ exc.toString() );
        }finally{
        }
	}

}
