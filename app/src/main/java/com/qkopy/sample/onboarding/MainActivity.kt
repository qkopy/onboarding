package com.qkopy.sample.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.qkopy.onboarding.OnBoardActivity

class MainActivity : AppCompatActivity() {

    private val onBoard = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivityForResult(Intent(this, OnBoardActivity::class.java), onBoard)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == onBoard && resultCode == Activity.RESULT_OK && data?.getBooleanExtra(
                "onboard",
                false
            ) == true
        ) {
            Toast.makeText(this@MainActivity, "Redirect to wherever you want", Toast.LENGTH_LONG).show()
        }
    }
}
