package com.example.bhakti.Activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.bhakti.Fragments.AartiFragment
import com.example.bhakti.Fragments.BookFragment
import com.example.bhakti.R
import com.example.bhakti.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.no_internet_connection.view.*


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var contentBinding: ActivityMainBinding
    private lateinit var signInClint: GoogleSignInClient
    private val RC_SIGN_IN = 4

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
//        window.statusBarColor = Color.TRANSPARENT
//        supportFragmentManager.beginTransaction().replace(R.id.mainActivity, HomeFragment()).commit()



        /****************** manage appBarLayout ************************************************/

        //Step 1
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.OpenDrawer, R.string.CloseDrawer)
        contentBinding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        toggle.drawerArrowDrawable.barThickness = 10f
        toggle.drawerArrowDrawable.barLength = 70f
        toggle.drawerArrowDrawable.color = (R.color.black)
        contentBinding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    drawerFragment(BookFragment())
                }
                R.id.aarti -> {
                    drawerFragment(AartiFragment())
                }
                R.id.chalisa -> {

                }
                R.id.invitation -> {

                }
                R.id.logIn -> {
                    signIn()
                }
                R.id.logOut -> {
                    signOut()
                }
                R.id.contact -> {

                }

            }

            contentBinding.drawerLayout.closeDrawer(GravityCompat.START)

           true
        }


        /*** Hadder implimentation ********/
        /*****Google sign in ********/

        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .build()

        signInClint = GoogleSignIn.getClient(this, gso)


        val hadder = contentBinding.navigationView.getHeaderView(0)
        val button = hadder.findViewById<Button>(R.id.loginButton)
        button.setOnClickListener{
            signIn()
        }

        

        // home user image clickListener
        userHomePic.setOnClickListener{
            val currentUser = FirebaseAuth.getInstance().currentUser
            if(currentUser == null){
                signIn()
            }
        }



        // check internet connection
        if (!checkInternet(this)) {
            showInternetDialog()
            Toast.makeText(this,"no internet connection", Toast.LENGTH_SHORT).show()
        } else {
            try {
                replaceFragment()
            } catch (e: Exception) {
                Log.d("TAG", "Error")
            }

        }


    }

    private fun signOut(){
        val user = FirebaseAuth.getInstance()
        signInClint.signOut()
        user.signOut()
        finish();
        startActivity(intent)
    }


    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = FirebaseAuth.getInstance().currentUser
        updateUI(currentUser)
    }

    private fun signIn() {
       val signInIntent: Intent = signInClint.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    @RequiresApi(Build.VERSION_CODES.R)
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            }catch (e: Exception){
                Log.d(TAG, "onActivityResult: "+e.message)
                e.printStackTrace()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.R)
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "Login Success")
                    val user = FirebaseAuth.getInstance().currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {

        if (user != null) {
            val hadder = contentBinding.navigationView.getHeaderView(0)
            val button = hadder.findViewById<Button>(R.id.loginButton)
            val name = hadder.findViewById<TextView>(R.id.name)
            val userPic = hadder.findViewById<CircleImageView>(R.id.userPic)
            val userHomePic = findViewById<CircleImageView>(R.id.userHomePic)
            button.visibility = GONE
            name.visibility = VISIBLE
            name.text = user.displayName
            Glide.with(this).load(user.photoUrl).into(userPic)
            Glide.with(this).load(user.photoUrl).into(userHomePic)
        }




    }

    private fun showInternetDialog() {
        val builder = AlertDialog.Builder(this).create()
        builder.setCancelable(false)
        val view: View = LayoutInflater.from(this).inflate(R.layout.no_internet_connection, findViewById(R.id.no_interner_layout))
        view.try_again.setOnClickListener{
            if (!checkInternet(this)) {
                showInternetDialog()
            }
            else{
                replaceFragment()
                builder.dismiss()
            }
        }
        builder.setView(view)
        builder.show()
    }

    private fun replaceFragment() {

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController
//        val fm = supportFragmentManager
//        val ft = fm.beginTransaction()
//        ft.add(R.id.container, fragment)
//        ft.commit()
    }

    private fun drawerFragment(fragment: Fragment) {

//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.fragment) as NavHostFragment
//        navController = navHostFragment.navController
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(R.id.container, fragment)
        ft.commit()
    }



    // Check internet connection
    private fun checkInternet(context: Context): Boolean {
        val connec = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return wifi!!.isConnected || mobile!!.isConnected
    }

    /*********************************** back press handle *************************************************/

//    var count = 0
    override fun onBackPressed() {

        if(contentBinding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            contentBinding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }

//        count++
//        if(count <= 1) {
//            Toast.makeText(this, "Press Back again to Exit", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            this.finish()
//            super.onBackPressed()
//        }
//
//
    }


}