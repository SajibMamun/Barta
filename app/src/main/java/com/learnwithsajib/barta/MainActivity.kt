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
import coil.load
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.learnwithsajib.barta.databinding.ActivityMainBinding
import com.learnwithsajib.barta.databinding.NavHeaderBinding

////50
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mAuth: FirebaseAuth
    lateinit var navHeaderBinding: NavHeaderBinding



    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.root)


        val drawerLayout: DrawerLayout = binding.drawerlayoutid
        val navView: NavigationView = binding.navViewid
        val navController: NavController = findNavController(R.id.fragmentContainerView)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.online,
                R.id.profile,
                R.id.notification,
                R.id.setting,
            ), drawerLayout
        )


        setupActionBarWithNavController(navController, drawerLayout)
        navView.setupWithNavController(navController)


        navHeaderBinding = NavHeaderBinding.bind(navView.getHeaderView(0))

        mAuth = FirebaseAuth.getInstance()

        //get data from database
        FirebaseDatabase.getInstance().reference.child("User").child(mAuth.uid.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    var user: User = snapshot.getValue(User::class.java)!!
                    navHeaderBinding.navNametvid.text = user.name
                    navHeaderBinding.navimageid.load(user.profileImgUrl)

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })


    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}