package com.example.quizapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_main_game.view.*

class MainActivity : AppCompatActivity(), Communicator{

    @SuppressLint()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startPage = StartPage()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, startPage).commit()
        Toast.makeText(applicationContext, "Firebase connected", Toast.LENGTH_SHORT).show()

    }

    override fun callAbout(userName: String) {
        val bundle = Bundle()
        bundle.putString("username", userName)

        val transaction = this.supportFragmentManager.beginTransaction()
        val about = About()
        about.arguments = bundle

        transaction.replace(R.id.fragment_container, about).addToBackStack(userName).commit()
    }

    override fun goBackToStartPageFromAbout() {
//        val transaction = this.supportFragmentManager.beginTransaction()
//        val startPage = StartPage()
//        transaction.replace(R.id.fragment_container, startPage).commit()
        if(supportFragmentManager.backStackEntryCount > 0){
            supportFragmentManager.popBackStackImmediate()
        }
        else{
            super.onBackPressed();
        }
    }

    override fun callMainGame(userName: String) {
        val bundle = Bundle()
        bundle.putString("usernameMain", userName)

        val transaction = this.supportFragmentManager.beginTransaction()
        val mainGame = MainGame()
        mainGame.arguments = bundle

        transaction.replace(R.id.fragment_container, mainGame).addToBackStack(userName).commit()
    }

    override fun goBackToStartPageFromMain() {
//        val transaction = this.supportFragmentManager.beginTransaction()
//        val startPage = StartPage()
//        transaction.replace(R.id.fragment_container, startPage).commit()
        if(supportFragmentManager.backStackEntryCount > 0){
           supportFragmentManager.popBackStackImmediate()
        }
        else{
            super.onBackPressed();
        }
    }

    override fun callGameResult(correctA: Int, wrongA: Int, totalS: Int) {
        val bundle = Bundle()
        bundle.putString("correctA", correctA.toString())
        bundle.putString("wrongA", wrongA.toString())
        bundle.putString("totalS", totalS.toString())

        val transaction = this.supportFragmentManager.beginTransaction()
        val gameResult = GameResult()
        gameResult.arguments = bundle

        transaction.replace(R.id.fragment_container, gameResult).commit()
    }

    @SuppressLint("SetTextI18n")
    override fun restartGame() {
        checkedQuestion.fill(false)

        val transaction = this.supportFragmentManager.beginTransaction()
        val startPage = StartPage()
        transaction.replace(R.id.fragment_container, startPage).commit()
    }

}