package com.example.absolutegame.presentation.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.absolutegame.Application
import com.example.absolutegame.databinding.ActivityProfileBinding
//import com.example.absolutegame.di.ViewModelFactory
import com.example.absolutegame.presentation.auth.login.LoginActivity
import com.example.absolutegame.presentation.blur.BlurViewModel
import javax.inject.Inject

class ProfileActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    private var binding: ActivityProfileBinding? = null

    @Inject
    lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        observeLiveData()

        viewModel.loadProfile()
        viewModel.loadProfilePhoto()

        binding?.buttonLogout?.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun observeLiveData() {
        viewModel.username.observe(this, ::handleUsername)
        viewModel.email.observe(this, ::handleEmail)
        viewModel.loading.observe(this, ::handleLoading)
        viewModel.error.observe(this, ::handleError)
        viewModel.logout.observe(this, ::handleLogout)
        viewModel.profilePhoto.observe(this, ::handleProfilePhoto)
    }

    private fun handleUsername(username: String?) {
        binding?.etUsername?.setText(username)
    }

    private fun handleEmail(email: String?) {
        binding?.etEmail?.setText(email)
    }

    private fun handleLoading(isLoading: Boolean) {
        binding?.flipperButton?.displayedChild = if (isLoading) 1 else 0
    }

    private fun handleError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun handleLogout(isLogout: Boolean) {
        if (isLogout) {
            LoginActivity.startActivity(this)
        }
    }

    private fun handleProfilePhoto(profilePhoto: String?) {
        profilePhoto?.let { binding?.ivProfile?.setImageURI(Uri.parse(profilePhoto)) }
    }
}