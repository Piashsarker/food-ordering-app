package com.mealsonwheels.session

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.mealsonwheels.ui.auth.MainActivity


class SessionManager(context: Context) {
    // Shared Preferences
    lateinit var pref: SharedPreferences

    // Editor for Shared preferences
    lateinit var editor: SharedPreferences.Editor

    // Context
    lateinit var _context: Context

    // Shared pref mode
    var PRIVATE_MODE = 0

    // Sharedpref file name
    private val PREF_NAME = "mealsonwheels"

    // All Shared Preferences Keys
    private val IS_LOGIN = "IsLoggedIn"

    // User name (make variable public to access from outside)
    val KEY_PASSWORD = "password"

    // Email address (make variable public to access from outside)
    val KEY_EMAIL = "email"
    val KEY_UID = "uid"
    val KEY_DOCUMENT_USER_ID = "document_user_id"

    init {
        this._context = context
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    /**
     * Create login session
     */
    fun createLoginSession(password: String, email: String, uid: String) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true)

        // Storing name in pref
        editor.putString(KEY_PASSWORD, password)

        // Storing email in pref
        editor.putString(KEY_EMAIL, email)

        //storing ui id in pref
        editor.putString(KEY_UID, uid)

        // commit changes
        editor.commit()
    }


    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    fun checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            val i = Intent(_context, MainActivity::class.java)
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            // Add new Flag to start new Activity
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            // Staring Login Activity
            _context.startActivity(i)
        }

    }


    /**
     * Get stored session data
     */
    fun getUserDetails(): HashMap<String, String> {
        val user = HashMap<String, String>()
        // user name
        user[KEY_PASSWORD] = pref.getString(KEY_PASSWORD, null)

        // user email id
        user[KEY_EMAIL] = pref.getString(KEY_EMAIL, null)

        user[KEY_UID] = pref.getString(KEY_UID, null)

        // return user
        return user
    }


    fun getUID(): String {
        return pref.getString(KEY_UID, null)
    }

    /**
     * Clear session details
     */
    fun logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear()
        editor.commit()

        // After logout redirect user to Loing Activity
        val i = Intent(_context, MainActivity::class.java)
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // Add new Flag to start new Activity
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        // Staring Login Activity
        _context.startActivity(i)
    }

    /**
     * Quick check for login
     */
    // Get Login State
    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }

    fun setUserDocumentId(documentId: String) {
        editor.putString(KEY_DOCUMENT_USER_ID, documentId);
        editor.commit()
    }

    fun getUserDocumentId(): String {
        return pref.getString(KEY_DOCUMENT_USER_ID, null)
    }

}