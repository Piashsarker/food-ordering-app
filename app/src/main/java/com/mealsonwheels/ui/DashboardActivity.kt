package com.mealsonwheels.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mealsonwheels.Constants
import com.mealsonwheels.R
import com.mealsonwheels.databinding.ActivityDashboardBinding
import com.mealsonwheels.session.SessionManager
import kotlinx.android.synthetic.main.app_bar_dashboard.view.*


class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var db: FirebaseFirestore
    private lateinit var headerView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        sessionManager = SessionManager(this)
        sessionManager.checkLogin()
        db = FirebaseFirestore.getInstance()
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        setSupportActionBar(binding.appBarDashboard.toolbar)
        sessionManager = SessionManager(this)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        headerView = navView.inflateHeaderView(R.layout.nav_header_dashboard)



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_logout,
                R.id.nav_order_history
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)
        getUserInformation()
    }

    private fun getUserInformation() {
        db.collection(Constants.USERS)
            .whereEqualTo(Constants.UID, sessionManager.getUID())
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    headerView.findViewById<TextView>(R.id.navEmail).text =
                        document.get(Constants.EMAIL).toString()
                    headerView.findViewById<TextView>(R.id.navUserName).text =
                        document.get(Constants.FULL_NAME).toString()
                }
            }
            .addOnFailureListener { exception ->

            }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_logout -> {
                logout()
            }
        }
        return true
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        sessionManager.logoutUser()
    }
}
