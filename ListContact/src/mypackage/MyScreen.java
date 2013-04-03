package mypackage;

import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.pim.Contact;
import javax.microedition.pim.PIM;
import javax.microedition.pim.PIMException;
import net.rim.blackberry.api.pdap.BlackBerryContact;
import net.rim.blackberry.api.pdap.BlackBerryContactList;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public final class MyScreen extends MainScreen
{
	/**
	 * Creates a new MyScreen object
	 */
	public MyScreen()
	{        
		// Set the displayed title of the screen  
		String homenumber = null;
		String homenumber2 = null;
		String worknumber = null;
		String worknumber2 = null;
		String mobilenumber = null;
		BlackBerryContactList contList;
		try {
			contList = (BlackBerryContactList)PIM.getInstance().openPIMList(PIM.CONTACT_LIST,PIM.READ_ONLY);
			Enumeration er = contList.items();
			while (er.hasMoreElements())
			{
				BlackBerryContact c = (BlackBerryContact)er.nextElement();
				if ((contList.isSupportedField(Contact.NAME)) && (c.countValues(Contact.NAME) > 0))
				{
					String[] name = c.getStringArray(Contact.NAME, 0);
					String firstName = name[Contact.NAME_GIVEN];
					String lastName = name[Contact.NAME_FAMILY];

					//here is the code snippet to iterate all phone nrs of a contact
					if ((contList.isSupportedField(Contact.TEL)) && (c.countValues(Contact.TEL) > 0)) 
					{
						int numValues = 0;
						try {
							numValues = c.countValues(Contact.TEL);
						} catch (Exception localException) {
						}
						for(int i=0;i<numValues;i++)
						{
							if (c.getAttributes(Contact.TEL, i) == BlackBerryContact.ATTR_HOME)
								homenumber = c.getString(Contact.TEL, i);
							if (c.getAttributes(Contact.TEL, i) == BlackBerryContact.ATTR_HOME2)
								homenumber2 = c.getString(Contact.TEL, i);
							if (c.getAttributes(Contact.TEL, i) == BlackBerryContact.ATTR_WORK)
								worknumber = c.getString(Contact.TEL, i);
							if (c.getAttributes(Contact.TEL, i) == BlackBerryContact.ATTR_WORK2)
								worknumber2 = c.getString(Contact.TEL, i);
							if (c.getAttributes(Contact.TEL, i) == BlackBerryContact.ATTR_MOBILE)
								mobilenumber = c.getString(Contact.TEL, i);
						}
//						for(int i=0;i<numValues;i++)
//						{
//							if (c.getAttributes(Contact.TEL, i) == BlackBerryContact.ATTR_HOME2)
//								homenumber2 = c.getString(Contact.TEL, i);
//						}
//						for(int i=0;i<numValues;i++)
//						{
//							if (c.getAttributes(Contact.TEL, i) == BlackBerryContact.ATTR_WORK)
//								worknumber = c.getString(Contact.TEL, i);
//						}
//						for(int i=0;i<numValues;i++)
//						{
//							if (c.getAttributes(Contact.TEL, i) == BlackBerryContact.ATTR_WORK2)
//								worknumber2 = c.getString(Contact.TEL, i);
//						}
//						for (int i = 0; i < numValues; ++i)
//						{
//							if (c.getAttributes(Contact.TEL, i) == BlackBerryContact.ATTR_MOBILE)
//								mobilenumber = c.getString(Contact.TEL, i);
//						}
					}

					add(new LabelField("\n"+firstName+" "+lastName +"  "+"\n"+"Home :"+homenumber+"\n"+"Home2 :"+homenumber2+"\n"+"Work :"+worknumber+"\n"+"Work2 :"+worknumber2+"\n"+"Mobile :"+mobilenumber+"\n"));
				}
			}
		} catch (PIMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	private Vector getList()
//	{
//		Vector getnumbers= new Vector();
//		
//		return getnumbers;    	
//	}
}
