package com.portal.plugin.wcm;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import com.ibm.portal.ListModel;
import com.ibm.workplace.wcm.api.plugin.textprovider.TextProvider;

public class CustomTextProviderPlugin implements TextProvider{
	   
	   private static final String BUNDLE_PROPERTIES = "custom.properties";

	   /** The text provider title */
	   public static final String PROVIDER_TITLE = "Custom Text Provider";
	   
	   /** The unique text provider name */
	   public static final String PROVIDER_NAME = "com.portal.plugin.wcm.CustomTextProviderPlugin";
	   
	   /** A brief description of this text provider */
	   private static final String PROVIDER_DESCRIPTION = "Custom Text Provider";
	   
	   /** The resource bundle used to lookup strings */
	   private static final String RESOURCE_BUNDLE_NAME = "custom";
	   	   
	   
	   @Override
	   public String getProviderName()
	   {
	      return PROVIDER_NAME;
	   }

	   @Override
	   public String getTitle(Locale p_displayLocale)
	   {
	      // Perform any localization for the plug-in title here
	      return PROVIDER_TITLE;
	   }
	   
	   @Override
	   public String getDescription(Locale p_displayLocale)
	   {
	      return PROVIDER_DESCRIPTION;
	   }

	   @Override
	   public String getString(String p_key, Locale p_displayLocale)
	   {
	      String value;
	   
	      try
	      {
	         ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);
	         value = bundle.getString(p_key);
	      }
	      catch (MissingResourceException e)
	      {
	         // The bundle or key was not found. Return null.
	         value = null;
	      }
	      return value;
	   }

	   @Override
	   public Collection<String> getProviderKeys()
	   {
	      Collection<String> keys = null;
	      try
	      {
	         LinkedProperties props = new LinkedProperties();
	         props.load(getClass().getClassLoader().getResourceAsStream(BUNDLE_PROPERTIES));
	         
	         keys = props.getKeySet();
	      }
	      catch (IOException e)
	      {
	         // The bundle was not found. Return null.
	         e.printStackTrace();
	      }
	      
	      return keys;
	   }


	   @Override
	   public boolean isShownInAuthoringUI()
	   {
	      return true;
	   }
	   
	   /** Used to provide the properties in order */
	   private class LinkedProperties extends Properties {

	      /** Keys */
	      private final LinkedHashSet<String> keys = new LinkedHashSet<String>();
	    
	      /**
	       * @return An ordered set of keys
	       */
	      public Set<String> getKeySet()
	      {
	         return keys;
	      }

	      @Override
	      public Object put(Object key, Object value) {
	          keys.add((String) key);
	          return super.put(key, value);
	      }
	  }

	@Override
	public ListModel<Locale> getLocales() {
		// TODO Auto-generated method stub
		return null;
	}
}
