package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_game_result.view.*

class GameResult : Fragment() {
    private lateinit var communicator: Communicator
    var rightAns: String? = ""
    var wrongAns: String? = ""
    var totalSc: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_result, container, false)

        communicator = activity as Communicator

        rightAns = arguments?.getString("correctA")
        view.userCorrectAns.text = rightAns
        wrongAns = arguments?.getString("wrongA")
        view.userWrongAns.text = wrongAns
        totalSc = arguments?.getString("totalS")
        view.userTotalScore.text = totalSc

        view.restartButton.setOnClickListener {
            communicator.restartGame()
        }

        return view
    }
}