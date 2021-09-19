//package com.example.notes.presentation.setting;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//
//import androidx.lifecycle.ViewModelProviders;
//import androidx.preference.Preference;
//import androidx.preference.PreferenceFragmentCompat;
//
//import com.example.notes.R;
//
//
//public class PreferenceFragmentSettings extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
//
//    private PreferenceFragmentSettingsViewModel mPreferenceFragmentSettingsViewModel;
//    private Preference mPreferenceAccentColor;
//    @Override
//    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
//        addPreferencesFromResource(R.xml.preference_settings);
//        mPreferenceFragmentSettingsViewModel = ViewModelProviders.of(this).get(PreferenceFragmentSettingsViewModel.class);
//
//        mPreferenceAccentColor = findPreference("key");
//        mPreferenceAccentColor.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//                AlertDialogAccentColor alertDialogAccentColor = new AlertDialogAccentColor(getView(), new AlertDialogAccentColor.Callback() {
//                    @Override
//                    public void getColor(int color) {
//                        // TODO: 29/05/2021 preference
//                        mPreferenceFragmentSettingsViewModel.putPrefInt(color);
//                    }
//                });
//
//                alertDialogAccentColor.show(getParentFragmentManager(), "");
//                return true;
//            }
//        });
//
//
//
//
//
//
//
//
//        PreferenceFragmentSettingsView preferenceFragmentSettingsView = new PreferenceFragmentSettingsView(this);
//    }
//
//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        Preference preference = findPreference(key);
//        Log.d("MyLog", "gienhriorheiofjriofjweroi" +preference.getKey() + "      " + key);
//
//        if (preference.getKey().equals(key) && getActivity() != null) {
//            Log.d("MyLog", preference.getKey() + "      " + key);
//            getActivity().recreate();
//        }
//    }
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this::onSharedPreferenceChanged);
//
//    }
//}
