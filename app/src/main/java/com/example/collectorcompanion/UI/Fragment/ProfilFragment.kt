package com.example.collectorcompanion.UI.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.collectorcompanion.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class ProfilFragment : Fragment() {

    lateinit var mGoogleSignInClient : GoogleSignInClient
    lateinit var sign_in_button : SignInButton
    lateinit var imageProfil : CardView
    lateinit var nameProfil : TextView
    lateinit var disconnectionButton: Button
    lateinit var cardDisconnectionButton : CardView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sign_in_button = view.findViewById(R.id.sign_in_button)
        imageProfil = view.findViewById(R.id.profileImageCardView)
        nameProfil = view.findViewById(R.id.profilTextView)
        disconnectionButton = view.findViewById(R.id.button_logout)
        cardDisconnectionButton = view.findViewById(R.id.card_button_logout)

        var account : GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireContext())
        if(account != null){
            sign_in_button.visibility =  View.INVISIBLE
            imageProfil.visibility = View.VISIBLE
            nameProfil.visibility = View.VISIBLE
            cardDisconnectionButton.visibility = View.VISIBLE

            Glide.with(requireContext())
                .load(account.photoUrl).placeholder(R.drawable.ic_baseline_account_box_24).override(400)
                .into(view.findViewById<ImageView>(R.id.profilImageView))
            nameProfil.setText(account.displayName)

            disconnectionButton.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    var gso : GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                    mGoogleSignInClient = GoogleSignIn.getClient(requireContext(),gso)
                    mGoogleSignInClient.signOut()
                    parentFragmentManager.popBackStack()
                    parentFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
                        .replace(R.id.fragment_container, ProfilFragment()).commit()
                }
            })
        }else{
            sign_in_button.visibility =  View.VISIBLE
            imageProfil.visibility = View.INVISIBLE
            cardDisconnectionButton.visibility = View.INVISIBLE
            nameProfil.visibility = View.INVISIBLE
            var gso : GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
            mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(),gso)
            sign_in_button.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    when (v?.id){
                        R.id.sign_in_button->{
                            var signinIntent : Intent = mGoogleSignInClient.signInIntent
                            startActivityForResult(signinIntent,100)
                        }
                    }
                }

            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==100){
            var task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                var account : GoogleSignInAccount? = task.getResult(ApiException::class.java)

                parentFragmentManager.popBackStack()
                parentFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit,R.anim.fragment_fade_enter,R.anim.fragment_fade_exit)
                    .replace(R.id.fragment_container, ProfilFragment()).commit()

            }catch (e: ApiException){
                Log.w("Fail","signInResult:failed code= ${e.statusCode}")
            }
        }
    }
}