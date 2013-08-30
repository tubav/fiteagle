/*
 * $Id: AVP_Type.java 3615 2011-06-15 10:07:50Z lal $
 *
 * Copyright (C) 2004-2006 FhG Fokus
 *
 * This file is part of Open IMS Core - an open source IMS CSCFs & HSS
 * implementation
 *
 * Open IMS Core is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * For a license to use the Open IMS Core software under conditions
 * other than those described here, or to purchase support for this
 * software, please contact Fraunhofer FOKUS by e-mail at the following
 * addresses:
 *     info@open-ims.org
 *
 * Open IMS Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

package de.fhg.fokus.diameter.DiameterPeer.data.avp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import de.fhg.fokus.diameter.DiameterPeer.data.Vendor_Id_Enum;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.CC.AVP_Final_Unit_Action;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.CC.AVP_Subscription_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.CC.AVP_Subscription_Id_Data;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.CC.AVP_Subscription_Id_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Cx.AVP_Feature_List;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Cx.AVP_Feature_List_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Cx.AVP_Supported_Features;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Gq.AVP_Reservation_Priority;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Gx.AVP_IP_CAN_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Gx.AVP_RAT_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.NAS.AVP_Called_Station_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.NAS.AVP_Framed_IP_Address;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.NAS.AVP_Framed_IPv6_Prefix;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_Application_Provided_Called_Party_Address;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_Application_Server;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_Application_Server_Information;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_Cause_Code;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_IMS_Charging_Identifier;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_IMS_Information;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_Node_Functionality;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_Service_Information;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_Service_Specific_Data;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_Service_Specific_Info;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_Service_Specific_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rf.AVP_User_Session_ID;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_AF_Application_Identifier;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_AF_Charging_Identifier;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Abort_Cause;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Acceptable_Service_Info;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Access_Network_Charging_Address;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Access_Network_Charging_Identifier;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Access_Network_Charging_Identifier_Value;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flow_Description;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flow_Number;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flow_Status;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flow_Usage;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Flows;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Max_Requested_Bandwidth_DL;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Max_Requested_Bandwidth_UL;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Media_Component_Description;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Media_Component_Number;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Media_Sub_Component;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Media_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_RR_Bandwidth;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_RS_Bandwidth;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_SIP_Forking_Indication;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Service_Info_Status;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Service_URN;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.Rx.AVP_Specific_Action;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Accounting_Realtime_Required;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Accounting_Record_Number;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Accounting_Record_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Acct_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Acct_Interim_Interval;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Acct_Multi_Session_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Acct_Session_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Acct_Sub_Session_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Grace_Period;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Request_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Auth_Session_State;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Authorization_Lifetime;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Class;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Destination_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Disconnect_Cause;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_E2E_Sequence;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Error_Message;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Error_Reporting_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Event_Timestamp;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Experimental_Result;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Experimental_Result_Code;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Failed_AVP;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Firmware_Revision;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Host_IP_Address;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Inband_Security_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Multi_Round_Time_Out;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_Realm;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Origin_State_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Product_Name;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Proxy_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Proxy_Info;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Proxy_State;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Re_Auth_Request_Type;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Redirect_Host;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Redirect_Host_Usage;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Redirect_Max_Cache_Time;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Result_Code;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Route_Record;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Session_Binding;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Session_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Session_Server_Failover;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Session_Timeout;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Supported_Vendor_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Termination_Cause;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_User_Name;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.avp.base.AVP_Vendor_Specific_Application_Id;
import de.fhg.fokus.diameter.DiameterPeer.data.codec.AVPDecodeException;

public enum AVP_Type {

	
	/* RFC3588 Diameter Base Protocol */
	
	Vendor_Id					(	266,	true,	Vendor_Id_Enum.None,	AVP_Vendor_Id.class			),
	Firmware_Revision				(	267,	true,	Vendor_Id_Enum.None,	AVP_Firmware_Revision.class		),
	Host_IP_Address					(	257,	true,	Vendor_Id_Enum.None,	AVP_Host_IP_Address.class		),
	Supported_Vendor_Id				(	265,	true,	Vendor_Id_Enum.None,	AVP_Supported_Vendor_Id.class		),
	Product_Name					(	269,	true,	Vendor_Id_Enum.None,	AVP_Product_Name.class			),
	Disconnect_Cause				(	273,	true,	Vendor_Id_Enum.None,	AVP_Disconnect_Cause.class		),
	Origin_Host					(	264,	true,	Vendor_Id_Enum.None,	AVP_Origin_Host.class			),
	Origin_Realm					(	296,	true,	Vendor_Id_Enum.None,	AVP_Origin_Realm.class			),
	Destination_Host				(	293,	true,	Vendor_Id_Enum.None,	AVP_Destination_Host.class		),
	Destination_Realm				(	283,	true,	Vendor_Id_Enum.None,	AVP_Destination_Realm.class		),
	Route_Record					(	282,	true,	Vendor_Id_Enum.None,	AVP_Route_Record.class			),
	Proxy_Info					(	284,	true,	Vendor_Id_Enum.None,	AVP_Proxy_Info.class			),
	Proxy_Host					(	280,	true,	Vendor_Id_Enum.None,	AVP_Proxy_Host.class			),
	Proxy_State					(	 33,	true,	Vendor_Id_Enum.None,	AVP_Proxy_State.class			),
	Auth_Application_Id				(	258,	true,	Vendor_Id_Enum.None,	AVP_Auth_Application_Id.class		),
	Acct_Application_Id				(	259,	true,	Vendor_Id_Enum.None,	AVP_Acct_Application_Id.class		),
	Inband_Security_Id				(	299,	true,	Vendor_Id_Enum.None,	AVP_Inband_Security_Id.class		),
	Vendor_Specific_Application_Id			(	260,	true,	Vendor_Id_Enum.None,	AVP_Vendor_Specific_Application_Id.class),
	Redirect_Host					(	292,	true,	Vendor_Id_Enum.None,	AVP_Redirect_Host.class			),
	Redirect_Host_Usage				(	261,	true,	Vendor_Id_Enum.None,	AVP_Redirect_Host_Usage.class		),
	Redirect_Max_Cache_Time				(	262,	true,	Vendor_Id_Enum.None,	AVP_Redirect_Max_Cache_Time.class	),
	E2E_Sequence					(	300,	true,	Vendor_Id_Enum.None,	AVP_E2E_Sequence.class			),
	Result_Code					(	268,	true,	Vendor_Id_Enum.None,	AVP_Result_Code.class			),
	Error_Message					(	281,	true,	Vendor_Id_Enum.None,	AVP_Error_Message.class			),
	Error_Reporting_Host				(	294,	true,	Vendor_Id_Enum.None,	AVP_Error_Reporting_Host.class		),
	Failed_AVP					(	279,	true,	Vendor_Id_Enum.None,	AVP_Failed_AVP.class			),
	Experimental_Result				(	297,	true,	Vendor_Id_Enum.None,	AVP_Experimental_Result.class		),
	Experimental_Result_Code			(	298,	true,	Vendor_Id_Enum.None,	AVP_Experimental_Result_Code.class	),
	Auth_Request_Type				(	274,	true,	Vendor_Id_Enum.None,	AVP_Auth_Request_Type.class		),
	Session_Id					(	263,	true,	Vendor_Id_Enum.None,	AVP_Session_Id.class			),
	Authorization_Lifetime				(	291,	true,	Vendor_Id_Enum.None,	AVP_Authorization_Lifetime.class	),
	Auth_Grace_Period				(	276,	true,	Vendor_Id_Enum.None,	AVP_Auth_Grace_Period.class		),
	Auth_Session_State				(	277,	true,	Vendor_Id_Enum.None,	AVP_Auth_Session_State.class		),
	Re_Auth_Request_Type				(	285,	true,	Vendor_Id_Enum.None,	AVP_Re_Auth_Request_Type.class		),
	Session_Timeout					(	 27,	true,	Vendor_Id_Enum.None,	AVP_Session_Timeout.class		),
	User_Name					(	  1,	true,	Vendor_Id_Enum.None,	AVP_User_Name.class			),
	Termination_Cause				(	295,	true,	Vendor_Id_Enum.None,	AVP_Termination_Cause.class		),
	Origin_State_Id					(	278,	true,	Vendor_Id_Enum.None,	AVP_Origin_State_Id.class		),
	Session_Binding					(	270,	true,	Vendor_Id_Enum.None,	AVP_Session_Binding.class		),
	Session_Server_Failover				(	271,	true,	Vendor_Id_Enum.None,	AVP_Session_Server_Failover.class	),
	Multi_Round_Time_Out				(	272,	true,	Vendor_Id_Enum.None,	AVP_Multi_Round_Time_Out.class		),
	Class						(	 25,	true,	Vendor_Id_Enum.None,	AVP_Class.class				),
	Event_Timestamp					(	 55,	true,	Vendor_Id_Enum.None,	AVP_Event_Timestamp.class		),
	Accounting_Record_Type				(	480,	true,	Vendor_Id_Enum.None,	AVP_Accounting_Record_Type.class	),
	Acct_Interim_Interval				(	 85,	true,	Vendor_Id_Enum.None,	AVP_Acct_Interim_Interval.class		),
	Accounting_Record_Number			(	485,	true,	Vendor_Id_Enum.None,	AVP_Accounting_Record_Number.class	),
	Acct_Session_Id					(	 44,	true,	Vendor_Id_Enum.None,	AVP_Acct_Session_Id.class		),
	Acct_Multi_Session_Id				(	 50,	true,	Vendor_Id_Enum.None,	AVP_Acct_Multi_Session_Id.class		),
	Acct_Sub_Session_Id				(	287,	true,	Vendor_Id_Enum.None,	AVP_Acct_Sub_Session_Id.class		),
	Accounting_Realtime_Required			(	483,	true,	Vendor_Id_Enum.None,	AVP_Accounting_Realtime_Required.class	),

	
	
	/* RFC 4005 Diameter Network Access Server Application */
	
	Called_Station_Id				(	 39,	true,	Vendor_Id_Enum.None,	AVP_Called_Station_Id.class		),
	Framed_IP_Address				(	  8,	true,	Vendor_Id_Enum.None,	AVP_Framed_IP_Address.class		),
	Framed_IPv6_Prefix				(	 97,	true,	Vendor_Id_Enum.None,	AVP_Framed_IPv6_Prefix.class		),
	
	
	
	/* RFC 4006 Diameter Network Access Server Application */
	
	Final_Unit_Action				(	449,	true,	Vendor_Id_Enum.None,	AVP_Final_Unit_Action.class		),
	Subscription_Id					(	443,	true,	Vendor_Id_Enum.None,	AVP_Subscription_Id.class		),
	Subscription_Id_Type				(	450,	true,	Vendor_Id_Enum.None,	AVP_Subscription_Id_Type.class		),
	Subscription_Id_Data				(	444,	true,	Vendor_Id_Enum.None,	AVP_Subscription_Id_Data.class		),
	
	
	
	/* 3GPP TS 29.212 Policy and Charging Control over Gx/Gxx reference point */
	
	IP_CAN_Type					(  	1027,	true,	Vendor_Id_Enum.TGPP,	AVP_IP_CAN_Type.class			),
	RAT_Type					(  	1032,	true,	Vendor_Id_Enum.None,	AVP_RAT_Type.class			),
	
	
	/* 3GPP TS 29.299 Diameter Charging Application over Rf reference point */
	Service_Information				(	873,	true,	Vendor_Id_Enum.TGPP,	AVP_Service_Information.class		),
	IMS_Information					(	876,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
	
	
	/* 3GPP TS 29.260 IMS_Information AVPs for Rf reference point */
//	Event_Type					(	823,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//		SIP_Method				(	824,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//		Event					(	825,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//		Expires					(	888,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
	Node_Functionality				(	862,	true,	Vendor_Id_Enum.TGPP,	AVP_Node_Functionality.class		),
	User_Session_ID					(	830,	true,	Vendor_Id_Enum.TGPP,	AVP_User_Session_ID.class		),
//	Outgoing_Session_Id				(	2320,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//	Calling_Party_Address				(	831,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//	Called_Party_Address				(	832,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//	Called_Asserted_Identity			(	1250,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//	Requested_Party_Address				(	1251,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//	Time_Stamps					(	833,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//	SIP_Request_Timestamp				(	834,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//	SIP_Request_Timestamp_Fraction			(	2301,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//	SIP_Response_Timestamp				(	835,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//	SIP_Response_Timestamp_Fraction			(	2302,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
	Application_Server_Information			(	850,	true,	Vendor_Id_Enum.TGPP,	AVP_Application_Server_Information.class),
	Application_Server				(	836,	true,	Vendor_Id_Enum.TGPP,	AVP_Application_Server.class		),
	Application_Provided_Called_Party_Address	(	837,	true,	Vendor_Id_Enum.TGPP,	AVP_Application_Provided_Called_Party_Address.class),
//	Inter_Operator_Identifier			(	838,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//	Originating_IOI					(	839,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
//	Terminating_IOI					(	840,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
	IMS_Charging_Identifier				(	841,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Charging_Identifier.class	),
//	Service_Id					(	855,	true,	Vendor_Id_Enum.TGPP,	AVP_IMS_Information.class		),
	Service_Specific_Info				(	1249,	true,	Vendor_Id_Enum.TGPP,	AVP_Service_Specific_Info.class		),
	Service_Specific_Data				(	863,	true,	Vendor_Id_Enum.TGPP,	AVP_Service_Specific_Data.class		),
	Service_Specific_Type				(	1257,	true,	Vendor_Id_Enum.TGPP,	AVP_Service_Specific_Type.class		),
	Cause_Code					(	861,	true,	Vendor_Id_Enum.TGPP,	AVP_Cause_Code.class			),

	
	
	/* 3GPP TS 29.214 Policy and Charging Control over Rx reference point */
	
	Abort_Cause					(	500,	true,	Vendor_Id_Enum.TGPP,	AVP_Abort_Cause.class			),
	Access_Network_Charging_Address			(	501,	true,	Vendor_Id_Enum.TGPP,	AVP_Access_Network_Charging_Address.class),
	Access_Network_Charging_Identifier		(	502,	true,	Vendor_Id_Enum.TGPP,	AVP_Access_Network_Charging_Identifier.class),
	Access_Network_Charging_Identifier_Value	(   	503,	true,	Vendor_Id_Enum.TGPP,	AVP_Access_Network_Charging_Identifier_Value.class),
	Acceptable_Service_Info				(	526,	true,	Vendor_Id_Enum.TGPP,	AVP_Acceptable_Service_Info.class),
	AF_Application_Identifier			(	504,	true,	Vendor_Id_Enum.TGPP,	AVP_AF_Application_Identifier.class),
	AF_Charging_Identifier				(	505,	true,	Vendor_Id_Enum.TGPP,	AVP_AF_Charging_Identifier.class),
	Codec_Data					(	524,	true,	Vendor_Id_Enum.TGPP,	AVP_Framed_IP_Address.class	),
	Flow_Description				(	507,	true,	Vendor_Id_Enum.TGPP,	AVP_Flow_Description.class	),
	Flow_Number					(	509,	true,	Vendor_Id_Enum.TGPP,	AVP_Flow_Number.class		),
	Flows						(	510,	true,	Vendor_Id_Enum.TGPP,	AVP_Flows.class			),
	Flow_Status					(	511,	true,	Vendor_Id_Enum.TGPP,	AVP_Flow_Status.class		),
	Flow_Usage					(	512,	true,	Vendor_Id_Enum.TGPP,	AVP_Flow_Usage.class		),
	Service_URN					(	525,	true,	Vendor_Id_Enum.TGPP,	AVP_Service_URN.class		),
	Specific_Action					(	513,	true,	Vendor_Id_Enum.TGPP,	AVP_Specific_Action.class	),
	Max_Requested_Bandwidth_DL			(	515,	true,	Vendor_Id_Enum.TGPP,	AVP_Max_Requested_Bandwidth_DL.class),
	Max_Requested_Bandwidth_UL			(	516,	true,	Vendor_Id_Enum.TGPP,	AVP_Max_Requested_Bandwidth_UL.class),
	Media_Component_Description			(	517,	true,	Vendor_Id_Enum.TGPP,	AVP_Media_Component_Description.class),
	Media_Component_Number				(	518,	true,	Vendor_Id_Enum.TGPP,	AVP_Media_Component_Number.class),
	Media_Sub_Component				(	519,	true,	Vendor_Id_Enum.TGPP,	AVP_Media_Sub_Component.class	),
	Media_Type					(	520,	true,	Vendor_Id_Enum.TGPP,	AVP_Media_Type.class		),
	RR_Bandwidth					(	521,	true,	Vendor_Id_Enum.TGPP,	AVP_RR_Bandwidth.class		),
	RS_Bandwidth					(	522,	true,	Vendor_Id_Enum.TGPP,	AVP_RS_Bandwidth.class		),
	Service_Info_Status				(	527,	true,	Vendor_Id_Enum.TGPP,	AVP_Service_Info_Status.class	),
	SIP_Forking_Indication				(	523,	true,	Vendor_Id_Enum.TGPP,	AVP_SIP_Forking_Indication.class),
	
	
	
	/* ETSI TS 183.017 Resource and Admission Control: DIAMETER protocol for session based policy set-up
		information exchange between the Application Function (AF)
        and the Service Policy Decision Function (SPDF) */
	
	Reservation_Priority				(	458,	true,	Vendor_Id_Enum.ETSI,	AVP_Reservation_Priority.class	),

	
	
	/* 3GPP TS 29.229 Cx and Dx interfaces based on the Diameter protocol */
	
	Supported_Features				(   628,	false,	Vendor_Id_Enum.TGPP,	AVP_Supported_Features.class	),	
	Feature_List_Id					(   628,	false,	Vendor_Id_Enum.TGPP,	AVP_Feature_List_Id.class	),	
	Feature_List					(   628,	false,	Vendor_Id_Enum.TGPP,	AVP_Feature_List.class		),	
		
	
	;
	
	
	
	
	/** The AVP code */
	public final int code;
		
	/** The AVP Mandatory Flag */
	public final boolean flagMandatory;
	
	/** The Vendor Identifier. Should only be used when the vendor specific flag is set */
	public final long vendorId;
	
	public final Class<?> avpClass;
	
	
	private AVP_Type(int code, boolean flagMandatory, Vendor_Id_Enum vendorId, Class<?> avpClass) 
	{
		this.code = code;
		this.flagMandatory = flagMandatory;
		this.vendorId = vendorId.value;
		this.avpClass = avpClass;
//		for(AVP_Type type:AVP_Type.values()){
//			if (type.code==code && type.vendorId==vendorId.value)
//				throw new RuntimeException("Duplicated AVP Type for code "+code+" and vendor id "+vendorId);			
	}
	
	
	
	private static final Class<?>[] avpParameterTypesLoose = {int.class,boolean.class,int.class,byte[].class};
	private static final Class<?>[] avpParameterTypesTight = {byte[].class};
	 
	public AVP newInstance(byte[] data) throws AVPDecodeException
	{
		AVP x = null;
		Constructor<?> c=null;
		try {
			c = this.avpClass.getConstructor(avpParameterTypesTight);
			x = (AVP) c.newInstance(new Object[] {data});
			return x;
		} catch (NoSuchMethodException e) {
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			c = this.avpClass.getConstructor(avpParameterTypesLoose);
			x = (AVP) c.newInstance(new Object[] {this.code,this.flagMandatory,this.vendorId,data});
			return x;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			x=null;			
		}			
		return x;
	}
	
	public static AVP newInstance(int avpCode,boolean mandatory,long vendor_id,byte[] data) throws AVPDecodeException
	{
		AVP x=null;
		for(AVP_Type type:AVP_Type.values())
			if (type.code==avpCode &&
				type.flagMandatory == mandatory &&
				type.vendorId == vendor_id){
				return type.newInstance(data);			
			}
		x = new AVP(avpCode,mandatory,vendor_id,data);		
		return x;
	}
	
	
	public static AVP_Type getType(AVP avp)
	{		
		for(AVP_Type x:AVP_Type.values())
			if (x.code == avp.code && x.vendorId == avp.vendorId) return x;
		return null;
	}
}

