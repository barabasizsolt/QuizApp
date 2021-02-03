package com.example.quizapp

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*

data class Question(
    var text: String = "",
    var answers: MutableList<String> = mutableListOf(),
    var rightAnswer: String = ""
)

val questions: MutableList<Question> = mutableListOf()

//FireBase reference.
lateinit var database: DatabaseReference
//Helper array to check if a question was submitted or not
val checkedQuestion = mutableListOf<Boolean>()

fun readQuestionsFromFireBase(){
    database = FirebaseDatabase.getInstance("https://quizapp-57f14.firebaseio.com/").reference

    for (key in 0..9) {
        val qst = Question()
        qst.answers.add("")
        qst.answers.add("")
        qst.answers.add("")
        qst.answers.add("")
        questions.add(qst)

        database.child(key.toString()).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                questions[key].apply{
                    text =  snapshot.child("question").value.toString()
                }
                //questions[key].text = snapshot.child("question").value.toString()
                questions[key].answers[0] = (snapshot.child("ans1").value.toString())
                questions[key].answers[1] = (snapshot.child("ans2").value.toString())
                questions[key].answers[2] = (snapshot.child("ans3").value.toString())
                questions[key].answers[3] = (snapshot.child("ans4").value.toString())
                questions[key].rightAnswer = snapshot.child("rans").value.toString()
            }
            @SuppressLint("SetTextI18n")
            override fun onCancelled(error: DatabaseError) {
                val toast = Toast.makeText(StartPage().context, "Error in database", Toast.LENGTH_LONG)
                toast.show()
            }
        })
    }

    //Helper array, subQuestion array init
    for (i in 0 until questions.size) {
        checkedQuestion.add(false)
    }
}