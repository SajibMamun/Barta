package com.learnwithsajib.barta

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener


import com.learnwithsajib.barta.ModelClass.User
import com.learnwithsajib.barta.databinding.FragmentOnlineBinding
import com.learnwithsajib.barta.databinding.FragmentProfileBinding


class Profile : Fragment() {


    //////Img uri catch
    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!

            } else if (resultCode == ImagePicker.RESULT_ERROR) {

            } else {

            }
        }


    lateinit var binding: FragmentProfileBinding
    lateinit var mAuth: FirebaseAuth
    var firebaseUser: FirebaseUser? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        mAuth = FirebaseAuth.getInstance()


        //get data from database
        FirebaseDatabase.getInstance().reference.child("User").child(mAuth.uid.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    var user: User = snapshot.getValue(User::class.java)!!

                    fun String.toEditable(): Editable =
                        Editable.Factory.getInstance().newEditable(this)

                    binding.Nameetid.text = user.name.toEditable()
                    binding.emailetid.text = user.email.toEditable()
                    binding.contactetid.text = user.contact.toEditable()
                    binding.Passwordetid.text = user.password.toEditable()
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        //






        binding.pickimagebtn.setOnClickListener {
            requestPermissions()
        }






        return binding.root


    }


    private fun requestPermissions() {

        Dexter.withContext(requireActivity())
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                    // this method is called when all permissions are granted
                    if (multiplePermissionsReport.areAllPermissionsGranted()) {

                        pickAnImage()

                    }


                    // check for permanent denial of any permission
                    if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied) {


                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    list: List<PermissionRequest?>?,
                    permissionToken: PermissionToken
                ) {
                    // this method is called when user grants some permission and denies some of them.
                    permissionToken.continuePermissionRequest()
                }
            }).withErrorListener { error: DexterError? ->
                // we are displaying a toast message for error message.

            } // below line is use to run the permissions on same thread and to check the permissions
            .onSameThread().check()
    }


    fun pickAnImage() {
        ImagePicker.with(requireActivity())
            .crop()
            .compress(500)         //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                200,
                200
            )  //Final image resolution will be less than 1080 x 1080(Optional)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

}