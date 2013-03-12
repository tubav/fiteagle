package org.fiteagle.delivery.xmlrpc.util;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import redstone.xmlrpc.XmlRpcCustomSerializer;
import redstone.xmlrpc.XmlRpcException;
import redstone.xmlrpc.XmlRpcMessages;
import redstone.xmlrpc.XmlRpcSerializer;
import redstone.xmlrpc.util.Base64;

public class FixedSerializer extends XmlRpcSerializer {

	@Override
	public void serialize(
	        Object value,
	        Writer writer )
	        throws XmlRpcException, IOException
	    {
	        writer.write( "<value>" );

	        if ( value instanceof String || value instanceof Character )
	        {
	            writer.write( "<string>" );

	            String string = value.toString();
	            int length = string.length();

	            for ( int i = 0; i < length; ++i )
	            {
	                char c = string.charAt( i );

	                switch( c )
	                {
	                    case '<' :
	                        writer.write( "&lt;" );
	                        break;

	                    case '&' :
	                        writer.write( "&amp;" );
	                        break;

	                    default :
	                        writer.write (c);
	                }
	            }

	            writer.write( "</string>" );
	        }
	        else if ( value instanceof Integer ||
	                  value instanceof Short   ||
	                  value instanceof Byte )
	        {
	            writer.write( "<int>" );
	            writer.write( value.toString() );
	            writer.write( "</int>" );
	        }
	        else if ( value instanceof Double ||
	                  value instanceof Float )
	        {
	            writer.write( "<double>" );
	            writer.write( value.toString() );
	            writer.write( "</double>" );
	        }
	        else if ( value instanceof Boolean )
	        {
	            writer.write( "<boolean>" );
	            writer.write( ( ( Boolean ) value ).booleanValue() == true ? "1" : "0" );
	            writer.write( "</boolean>" );
	        }
	        else if ( value instanceof java.util.Calendar )
	        {
	            writer.write( "<dateTime.iso8601>" );

	            synchronized( dateFormatter )
	            {
	                writer.write( dateFormatter.format( ( ( Calendar ) value ).getTime() ) );
	            }

	            writer.write( "</dateTime.iso8601>" );
	        }
	        else if ( value instanceof java.util.Date )
	        {
	            writer.write( "<dateTime.iso8601>" );

	            synchronized( dateFormatter )
	            {
	                writer.write( dateFormatter.format( ( Date ) value ) );
	            }

	            writer.write( "</dateTime.iso8601>" );
	        }
	        else if ( value instanceof byte[] )
	        {
	            writer.write( "<base64>" );
	            writer.write( Base64.encode( ( byte[] ) value ) );
	            writer.write( "</base64>" );
	        }
	        else
	        {
	            // Value was not of basic type, see if there's a custom serializer
	            // registered for it.

	            for ( int i = 0; i < customSerializers.size(); ++i )
	            {
	                XmlRpcCustomSerializer serializer = ( XmlRpcCustomSerializer ) customSerializers.get( i );
	                
	                if ( serializer.getSupportedClass().isInstance( value ) )
	                {
	                    serializer.serialize( value, writer, this );
	                    writer.write( "</value>" );
	                    return;
	                }
	            }

	            throw new XmlRpcException(
	                XmlRpcMessages.getString( "XmlRpcSerializer.UnsupportedType" ) + value.getClass() );
	        }

	        writer.write( "</value>" );
	    }
	
	 /** Date formatter shared by all XmlRpcValues */
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat( "yyyyMMdd'T'HH:mm:ss" );
}
