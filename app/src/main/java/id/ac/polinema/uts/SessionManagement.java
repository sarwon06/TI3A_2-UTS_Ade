package id.ac.polinema.uts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {
    private SharedPreferences mSharedPreferences;
    private  SharedPreferences.Editor mEditor;
    private Context mContext;
    int PRIVATE_MODE;

    private  static final String PREF_NAME = "SharedPrefLatihan";

    private static  final String IS_LOGIN ="IsLoggedIn";

    public static  final String KEY_EMAIL ="email";

    public static  final String Key_PASSWORD = "password";

    public  SessionManagement(Context mContext){
        this.mContext = mContext;
        mSharedPreferences = this.mContext.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        mEditor = mSharedPreferences.edit();

    }
    public void createLoginSession(String email,String password){
        mEditor.putBoolean(IS_LOGIN,true);
        mEditor.putString(KEY_EMAIL,email);
        mEditor.putString(Key_PASSWORD,password);
        mEditor.commit();
    }
    public HashMap<String, String> getUserInformation(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_EMAIL, mSharedPreferences.getString(KEY_EMAIL, null));
        user.put(Key_PASSWORD, mSharedPreferences.getString(Key_PASSWORD, null));
        return user;
    }
    public boolean isLoggedIn(){
        return mSharedPreferences.getBoolean(IS_LOGIN,false);
    }
    public void checkLogin(){
        if(!isLoggedIn()){
            Intent i = new Intent(mContext,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        }
    }
    public void logoutUser(){
        mEditor.clear();
        mEditor.commit();

        Intent i = new Intent(mContext,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }
}
