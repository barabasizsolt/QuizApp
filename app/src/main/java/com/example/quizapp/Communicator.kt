package com.example.quizapp

import android.widget.Button

interface Communicator {
    //About fragment
    fun callAbout(userName:String)
    fun goBackToStartPageFromAbout()

    //MainGame fragment
    fun callMainGame(userName: String)
    fun goBackToStartPageFromMain()

    //GameResult fragment
    fun callGameResult(correctA:Int, wrongA:Int, totalS:Int)
    fun restartGame()
}