package org.fiteagle.adapter.common;

import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public interface OpenstackResourceAdapter {
	
	public static final String IMAGE_ID = "image_id";
	public static final String IMAGE_NAME = "image_name";
	public static final String IMAGE_MINDISK = "image_minDisk";
	public static final String IMAGE_CREATED = "image_created";
	public static final String IMAGE_MINRAM = "image_minram";
	public static final String IMAGE_OSEXTIMG_SIZE = "image_OSEXTIMGSIZE";
	public static final String IMAGE_PROGRESS = "image_progress";
	public static final String IMAGE_STATUS= "image_status";
	public static final String IMAGE_UPDATED = "image_updated";
	
	
	public static final String FLAVOR_DISK = "flavor_disk";
	public static final String FLAVOR_ID = "flavor_id";
	public static final String FLAVOR_NAME = "flavor_name";
	public static final String FLAVOR_OSFLAVORACCESSISPUBLIC = "flavor_OSFLAVORACCESSISPUBLIC";
	public static final String FLAVOR_OSFLVDISABLED = "flavor_OSFLVDISABLED";
	public static final String FLAVOR_OSFLVEXTDATAEPHEMERAL = "flavor_OSFLVEXTDATAEPHEMERAL";
	public static final String FLAVOR_RAM = "flavor_ram";
	public static final String FLAVOR_RXTXFACTOR = "flavor_rxtxFactor";
	public static final String FLAVOR_SWAP = "flavor_swap";
	public static final String FLAVOR_VCPUS = "flavor_vcpus";
	
	
	
	public static final String VM_AccessIPv4 = "vm_AccessIPv4";
	public static final String VM_AccessIPv6 = "vm_AccessIPv6";
	public static final String VM_ConfigDrive = "vm_ConfigDrive";
	public static final String VM_Created = "vm_Created";
	public static final String VM_FlavorId = "vm_FlavorId";
	public static final String VM_HostId = "vm_HostId";
	public static final String VM_Id = "vm_Id";
	public static final String VM_ImageId = "vm_ImageId";
	public static final String VM_KeyName = "vm_KeyName";
	public static final String VM_Name= "vm_Name";
	public static final String VM_OSDCFDiskConfig = "vm_OSDCFDiskConfig";
	public static final String VM_OSEXTAZAvailabilityZone = "vm_OSEXTAZAvailabilityZone";
	public static final String VM_OSEXTSTSPowerState= "vm_OSEXTSTSPowerState";
	public static final String VM_OSEXTSTSTaskState = "vm_OSEXTSTSTaskState";
	public static final String VM_Progress = "vm_Progress";
	public static final String VM_Status = "vm_Status";
	public static final String VM_TenantId = "vm_TenantId";
	public static final String VM_UserId= "vm_UserId";
	public static final String VM_Updated = "vm_Updated";
	public static final String VM_OSEXTSTSVmState = "vm_OSEXTSTSVmState";
	public static final String VM_FloatingIP = "floatingIP";
	
	

	
	
	
	

	HashMap<String, String> getImageProperties();

	List<HashMap<String, String>> getFlavorsProperties();
	
	String getId();
	
//	OpenstackResourceAdapter create(String imageId, String flavorId, String vmName, X509Certificate x509Certificate);
//	public OpenstackResourceAdapter create(String imageId, String flavorId, String vmName, String keyPairName, X509Certificate cert);
//	public OpenstackResourceAdapter create(String imageId, String flavorId,
//			String vmName, String keyPairName, X509Certificate cert);
	
	public OpenstackResourceAdapter create(String imageId, String flavorId,
			String vmName, String keyPairName);
	

	HashMap<String, String> getVMProperties();

	String getFlavorId();

	HashMap<String, Object> getProperties();

	void setStatus(ResourceAdapterStatus status);

	void setExpirationTime(Date expirationTime);

	Date getExpirationTime();

	String getImageId();

	void configure(AdapterConfiguration configuration);
	public ResourceAdapterStatus getStatus();

	void setProperties(HashMap<String, Object> properties);

	void release();
	
	String getParentNodeId();
	void setParentNodeId(String nodeId);
	
	public void checkAndSetRAReady();
	
	
	

}
