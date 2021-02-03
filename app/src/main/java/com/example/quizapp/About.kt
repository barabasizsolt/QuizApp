package com.example.quizapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_about.view.*
import kotlinx.android.synthetic.main.fragment_start_page.view.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class About: Fragment() {
    private lateinit var communicator: Communicator
    var displayFullName: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        displayFullName = arguments?.getString("username")
        val uName = "Hello $displayFullName!"
        view.userNameAbout.text = uName

        communicator = activity as Communicator
        view.backButtonAbout.setOnClickListener {
            communicator.goBackToStartPageFromAbout()
        }

        view.showAbout.setOnClickListener {
            var string: String? = ""
            val stringBuilder = StringBuilder()
            val `is`: InputStream = this.resources.openRawResource(R.raw.input)
            val reader = BufferedReader(InputStreamReader(`is`))
            while (true) {
                try {
                    if (reader.readLine().also { string = it } == null) break
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                stringBuilder.append(string).append("\n")
                aboutInfo.text = stringBuilder
            }
            `is`.close()
        }

        return view
    }
}