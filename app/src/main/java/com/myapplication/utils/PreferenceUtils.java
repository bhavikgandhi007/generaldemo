package com.myapplication.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;


/**
 * Store all values in a PreferenceUtils.
 */
public class PreferenceUtils {
    public static final String PREF_FILE_DEFAULT = "pref_file_default";
    public static final String APP_FIRST_LAUNCH = "app_first_launch";

    public static final String PREF_TOKEN = "PREF_TOKEN";
    private Context mContext;

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    public PreferenceUtils(Context c) {
        mContext = c;
        mPrefs = mContext.getSharedPreferences(PREF_FILE_DEFAULT,
                Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor editor() {
        if (mEditor == null) {
            mEditor = mPrefs.edit();
        }
        return mEditor;
    }

    public String getString(int keyResId) {
        return mPrefs.getString(mContext.getString(keyResId), null);
    }

    /**
     * Get a string preference
     *
     * @param keyResId
     * @param defResId the res id of the string that should be returned if the
     *                 preference was not set (note that this should be the resid of
     *                 a string, not the resid of a preference key)
     * @return
     */
    public String getString(int keyResId, int defResId) {
        final String key = mContext.getString(keyResId);
        return (mPrefs.contains(key)) ? mPrefs.getString(key, null) : mContext
                .getString(defResId);
    }

    /**
     * Put a String by resource id
     *
     * @param keyResId   the key of the preference
     * @param valueResId the res id of the value string
     */
    public SharedPreferences.Editor putString(int keyResId, int valueResId) {
        final SharedPreferences.Editor editor = editor();
        editor.putString(mContext.getString(keyResId),
                mContext.getString(valueResId));
        return editor;
    }


    public SharedPreferences.Editor putString(int keyResId, String valueResId) {
        final SharedPreferences.Editor editor = editor();
        editor.putString(mContext.getString(keyResId), valueResId);
        return editor;
    }

    public SharedPreferences.Editor putBoolean(String key, boolean valueResId) {
        final SharedPreferences.Editor editor = editor();
        editor.putBoolean(key, valueResId);
        return editor;
    }


    /**
     * Get a string by res id
     *
     * @param keyResId
     * @return
     */

    public SharedPreferences.Editor put(int keyResId, Object value) {
        final String key = mContext.getString(keyResId);
        if (key == null) {
            throw new IllegalArgumentException(
                    "No resource matched key resource id");
        }
        final SharedPreferences.Editor editor = editor();
        if (value instanceof String)
            editor.putString(key, (String) value);
        else if (value instanceof Integer)
            editor.putInt(key, (Integer) value);
        else if (value instanceof Boolean)
            editor.putBoolean(key, (Boolean) value);
        else if (value instanceof Float)
            editor.putFloat(key, (Float) value);
        else if (value instanceof Long)
            editor.putLong(key, (Long) value);
        else
            throw new IllegalArgumentException("Unknown data type");
        return editor;
    }


    /**
     * Get a string preference
     *
     * @param keyResId
     * @param defValue The string to return if the preference was not set
     * @return
     */
    public String getString(int keyResId, String defValue) {
        return mPrefs.getString(mContext.getString(keyResId), defValue);
    }

    public String getStringnew(String keyResId) {
        return mPrefs.getString(keyResId, null);
    }


    public boolean getBoolean(int keyResId, Boolean defaultValue) {
        final Boolean result = getBooleanOrNull(keyResId);
        return result != null ? result : defaultValue;
    }

    public boolean getBoolean(String keyResId, Boolean defaultValue) {
        return (mPrefs.contains(keyResId)) ? mPrefs.getBoolean(keyResId, false) : false;
    }

    private Boolean getBooleanOrNull(int keyResId) {
        final String key = mContext.getString(keyResId);
        return (mPrefs.contains(key)) ? mPrefs.getBoolean(key, false) : null;
    }

    public int getInteger(int keyResId, int defaultValue) {
        final int result = getIntegerOrNull(keyResId);
        return result != 0 ? result : defaultValue;
    }

    private int getIntegerOrNull(int keyResId) {
        final String key = mContext.getString(keyResId);
        return (mPrefs.contains(key)) ? mPrefs.getInt(key, 0) : 0;
    }


    public boolean getBooleanNew1(String key, boolean defaultValue) {
        return mPrefs.getBoolean(key, false);
    }

    /**
     * After applying, call {@link #editor()} again.
     */
    public void apply() {
        apply(editor());
        mEditor = null;
    }

    @SuppressLint("NewApi")
    public static final void apply(SharedPreferences.Editor editor) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit();
        } else {
            editor.apply();
        }
    }



    public  String isTokenAvailable(Context context) {
        return mPrefs.getString(PREF_TOKEN, null);
    }

}
