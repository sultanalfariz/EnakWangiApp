package dev.app.enak.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import dev.app.enak.Model.CekIdResource;
import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Model.GetUserResource;


public class Prefs {

    public static final String USER_SESSION = "user_session";
    public static final String USER_PASSWORD = "user_pass";
    public static final String PETUGAS_SESSION = "petugas_session";
    public static final String PETUGAS_PASSWORD = "petugas_pass";

    public static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void putUser(Context context, String key, GetUserResource user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        putString(context, key, json);
    }

    public static void putIdentitas(Context context, String key, CekIdResource user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        putString(context, key, json);
    }

    public static void putPetugas(Context context, String key, GetPetugasResource petugas) {
        Gson gson = new Gson();
        String json = gson.toJson(petugas);
        putString(context, key, json);
    }

    public static CekIdResource getIdentitas(Context context, String key) {
        Gson gson = new Gson();
        String json = getString(context, key);
        CekIdResource user = gson.fromJson(json, CekIdResource.class);
        return user;
    }

    public static GetUserResource getUser(Context context, String key) {
        Gson gson = new Gson();
        String json = getString(context, key);
        GetUserResource user = gson.fromJson(json, GetUserResource.class);
        return user;
    }

    public static GetPetugasResource getPetugas(Context context, String key) {
        Gson gson = new Gson();
        String json = getString(context, key);
        GetPetugasResource petugas = gson.fromJson(json, GetPetugasResource.class);
        return petugas;
    }

    public static void putString(Context context, String key, String value) {
        getSharedPreference(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        return getSharedPreference(context).getString(key, null);
    }

    public static void clear(Context context) {
        getSharedPreference(context).edit().clear().apply();
    }
}
