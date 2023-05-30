package com.learnwithsajib.barta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.learnwithsajib.barta.databinding.ActivityMainBinding

////50
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.root)



        val drawerLayout:DrawerLayout=binding.drawerlayoutid
        val navView:NavigationView=binding.navViewid
        val navController:NavController=findNavController(R.id.fragmentContainerView)

        appBarConfiguration=AppBarConfiguration(
            setOf(
                R.id.online,
                R.id.profile,
                R.id.notification,
                R.id.setting,
            ),drawerLayout
        )


        setupActionBarWithNavController(navController,drawerLayout)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController=findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration)||super.onSupportNavigateUp()
    }


}