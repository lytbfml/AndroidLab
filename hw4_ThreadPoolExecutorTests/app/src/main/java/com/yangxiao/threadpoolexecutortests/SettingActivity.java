package com.yangxiao.threadpoolexecutortests;

import android.preference.*;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

public class SettingActivity extends AppCompatPreferenceActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingFragment()).commit();
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	private void setupActionBar() {
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			// Show the Up button in the action bar.
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}
	
	private static Preference.OnPreferenceChangeListener onPreferenceChangeListener = new Preference.OnPreferenceChangeListener() {
		@Override
		public boolean onPreferenceChange(Preference preference, Object value) {
			String stringValue = value.toString();
			
			if(preference instanceof EditTextPreference) {
				preference.setTitle(stringValue);
			}
			return true;
		}
	};
	
	private static void PreferenceSummaryToValue(Preference preference) {
		// Set the listener to watch for value changes.
		preference.setOnPreferenceChangeListener(onPreferenceChangeListener);
		
		// Trigger the listener immediately with the preference's
		// current value.
		onPreferenceChangeListener.onPreferenceChange(preference,
				PreferenceManager
						.getDefaultSharedPreferences(preference.getContext())
						.getString(preference.getKey(), ""));
	}
	
	
	public static class SettingFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			PreferenceManager.setDefaultValues(getActivity(), R.xml.pref_setting, false);
			addPreferencesFromResource(R.xml.pref_setting);
			
			PreferenceSummaryToValue(findPreference("mp_number_tasks"));
			PreferenceSummaryToValue(findPreference("mp_number_threadsPool"));
			PreferenceSummaryToValue(findPreference("mp_number_threadsPool_max"));
			PreferenceSummaryToValue(findPreference("mp_number_queue"));
		}
	}
}
