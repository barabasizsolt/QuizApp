package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main_game.view.*
import kotlinx.android.synthetic.main.fragment_start_page.view.*

class StartPage(): Fragment() {
    private lateinit var communicator: Communicator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_start_page, container, false)

        //Populate the questions list from firebase.
        readQuestionsFromFireBase()

        communicator = activity as Communicator

        view.aboutButton.setOnClickListener {
            communicator.callAbout(view.userName.text.toString())
        }

        view.startButton.setOnClickListener {
            communicator.callMainGame(view.userName.text.toString())
        }

        return view
    }
}
