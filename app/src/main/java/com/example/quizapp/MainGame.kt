package com.example.quizapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import kotlinx.android.synthetic.main.fragment_main_game.*
import kotlinx.android.synthetic.main.fragment_main_game.view.*

class MainGame : Fragment() {
    private lateinit var communicator: Communicator
    var displayFullName: String? = ""

    private var nextCtrQuestion = 1
    private var prevCtrQuestion = 0
    private var questionIndex = 0
    private val message = "Pick an answer!"
    private val subM = "This question was submitted!"
    private var wrongA = 0
    private var rightA = 0
    private var totalScore = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_game, container, false)

        //Shows the users name
        displayFullName = arguments?.getString("usernameMain")
        val uName = "Hello $displayFullName!"
        view.userNameMain.text = uName

        //Navigate back to the
        communicator = activity as Communicator
        view.backButtonMain.setOnClickListener {
            for (i in 0 until questions.size) {
                checkedQuestion.add(false)
            }
            questions.removeAll{true}
            communicator.goBackToStartPageFromMain()
        }

        //Navigate to results
        view.finishButton.setOnClickListener {
            for (i in 0 until questions.size) {
                checkedQuestion.add(false)
            }
            questions.removeAll{true}
            communicator.callGameResult(rightA, wrongA, totalScore)
        }

        val cBox1 = view.findViewById<CheckBox>(R.id.checkBox1)
        val cBox2 = view.findViewById<CheckBox>(R.id.checkBox2)
        val cBox3 = view.findViewById<CheckBox>(R.id.checkBox3)
        val cBox4 = view.findViewById<CheckBox>(R.id.checkBox4)

        //Initial state
        //---------------------------------------------------//
        //Shuffling the answers
        questions.forEach { it.answers.shuffle() }

        view.score.text = totalScore.toString()
        view.questionViewe.text = questions[questionIndex].text;

        view.ans1.text = questions[questionIndex].answers[0]
        view.ans2.text = questions[questionIndex].answers[1]
        view.ans3.text = questions[questionIndex].answers[2]
        view.ans4.text = questions[questionIndex].answers[3]

        view.userInfo.text = message
        //---------------------------------------------------//

        //Show the next question
        view.nextQuestionButton.setOnClickListener {
            //Showing the actual points
            view.score.text = totalScore.toString()

            if (nextCtrQuestion < questions.size) {
                prevCtrQuestion = nextCtrQuestion - 1

                view.questionViewe.text = questions[nextCtrQuestion].text
                //questions[nextCtrQuestion].answers.shuffle()
                view.ans1.text = questions[nextCtrQuestion].answers[0]
                view.ans2.text = questions[nextCtrQuestion].answers[1]
                view.ans3.text = questions[nextCtrQuestion].answers[2]
                view.ans4.text = questions[nextCtrQuestion].answers[3]

                view.ans1.setBackgroundColor(Color.parseColor("#5c5174"))
                view.ans2.setBackgroundColor(Color.parseColor("#5c5174"))
                view.ans3.setBackgroundColor(Color.parseColor("#5c5174"))
                view.ans4.setBackgroundColor(Color.parseColor("#5c5174"))
                view.ans1.setTextColor(Color.parseColor("#ff33b5e5"))
                view.ans2.setTextColor(Color.parseColor("#ff33b5e5"))
                view.ans3.setTextColor(Color.parseColor("#ff33b5e5"))
                view.ans4.setTextColor(Color.parseColor("#ff33b5e5"))
                view.userInfo.setTextColor(Color.parseColor("#FFFFFF"))
                submitButton.isEnabled = true

                if (checkedQuestion[nextCtrQuestion]) {
                    cBox1.isEnabled = false
                    cBox2.isEnabled = false
                    cBox3.isEnabled = false
                    cBox4.isEnabled = false

                    view.userInfo.setTextColor(Color.parseColor("#FF0000"))
                    view.userInfo.text = subM
                    submitButton.isEnabled = false
                } else {
                    cBox1.isChecked = false
                    cBox2.isChecked = false
                    cBox3.isChecked = false
                    cBox4.isChecked = false

                    cBox1.isEnabled = true
                    cBox2.isEnabled = true
                    cBox3.isEnabled = true
                    cBox4.isEnabled = true

                    view.userInfo.text = message
                }
                if (questionIndex != questions.size - 1) {
                    nextCtrQuestion++
                    questionIndex++
                }
            }
        }

        //Show the previous question
        view.prevQuestionButton.setOnClickListener {
            //Showing the actual points
            view.score.text = totalScore.toString()

            if (prevCtrQuestion >= 0) {
                nextCtrQuestion = prevCtrQuestion + 1

                view.questionViewe.text = questions[prevCtrQuestion].text
                //questions[prevCtrQuestion].answers.shuffle()
                view.ans1.text = questions[prevCtrQuestion].answers[0]
                view.ans2.text = questions[prevCtrQuestion].answers[1]
                view.ans3.text = questions[prevCtrQuestion].answers[2]
                view.ans4.text = questions[prevCtrQuestion].answers[3]

                view.ans1.setBackgroundColor(Color.parseColor("#5c5174"))
                view.ans2.setBackgroundColor(Color.parseColor("#5c5174"))
                view.ans3.setBackgroundColor(Color.parseColor("#5c5174"))
                view.ans4.setBackgroundColor(Color.parseColor("#5c5174"))
                view.ans1.setTextColor(Color.parseColor("#ff33b5e5"))
                view.ans2.setTextColor(Color.parseColor("#ff33b5e5"))
                view.ans3.setTextColor(Color.parseColor("#ff33b5e5"))
                view.ans4.setTextColor(Color.parseColor("#ff33b5e5"))
                view.userInfo.setTextColor(Color.parseColor("#FFFFFF"))
                submitButton.isEnabled = true

                if (checkedQuestion[prevCtrQuestion]) {
                    cBox1.isEnabled = false
                    cBox2.isEnabled = false
                    cBox3.isEnabled = false
                    cBox4.isEnabled = false

                    view.userInfo.setTextColor(Color.parseColor("#FF0000"))
                    view.userInfo.text = subM
                    submitButton.isEnabled = false
                } else {
                    cBox1.isChecked = false
                    cBox2.isChecked = false
                    cBox3.isChecked = false
                    cBox4.isChecked = false

                    cBox1.isEnabled = true
                    cBox2.isEnabled = true
                    cBox3.isEnabled = true
                    cBox4.isEnabled = true

                    view.userInfo.text = message
                }
                if (questionIndex != 0) {
                    prevCtrQuestion--
                    questionIndex--
                }
            }
        }


        //Choosing the right answer
        cBox1.setOnClickListener {
            if (cBox1.isChecked) {
                cBox2.isChecked = false
                cBox3.isChecked = false
                cBox4.isChecked = false
            }
        }
        cBox2.setOnClickListener {
            if (cBox2.isChecked) {
                cBox1.isChecked = false
                cBox3.isChecked = false
                cBox4.isChecked = false
            }
        }
        cBox3.setOnClickListener {
            if (cBox3.isChecked) {
                cBox2.isChecked = false
                cBox1.isChecked = false
                cBox4.isChecked = false
            }
        }
        cBox4.setOnClickListener {
            if (cBox4.isChecked) {
                cBox2.isChecked = false
                cBox3.isChecked = false
                cBox1.isChecked = false
            }
        }

        //Submitting the question
        view.submitButton.setOnClickListener {
            val good = "Your answer is correct!"
            val bad = "Your answer is not correct!"
            checkedQuestion[questionIndex] = true
            when {
                view.checkBox1.isChecked -> {
                    if (view.ans1.text == questions[questionIndex].rightAnswer) {
                        view.ans1.setBackgroundColor(Color.parseColor("#008000"))
                        view.ans1.setTextColor(Color.parseColor("#FFFFFF"))
                        view.userInfo.text = good

                        totalScore += 10
                        rightA++
                        view.score.text = totalScore.toString()

                        view.submitButton.isEnabled = false
                    } else {
                        view.ans1.setBackgroundColor(Color.parseColor("#FF0000"))
                        view.ans1.setTextColor(Color.parseColor("#FFFFFF"))
                        view.userInfo.text = bad

                        wrongA++

                        view.submitButton.isEnabled = false
                        //Megmutatni a helyes valaszt
                        when {
                            view.ans2.text == questions[questionIndex].rightAnswer -> {
                                view.ans2.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans2.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                            view.ans3.text == questions[questionIndex].rightAnswer -> {
                                view.ans3.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans3.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                            view.ans4.text == questions[questionIndex].rightAnswer -> {
                                view.ans4.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans4.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                        }

                    }
                    cBox2.isEnabled = false
                    cBox3.isEnabled = false
                    cBox4.isEnabled = false
                }
                view.checkBox2.isChecked -> {
                    if (view.ans2.text == questions[questionIndex].rightAnswer) {
                        view.ans2.setBackgroundColor(Color.parseColor("#008000"))
                        view.ans2.setTextColor(Color.parseColor("#FFFFFF"))
                        view.userInfo.text = good

                        totalScore += 10
                        rightA++
                        view.score.text = totalScore.toString()

                        view.submitButton.isEnabled = false
                    } else {
                        view.ans2.setBackgroundColor(Color.parseColor("#FF0000"))
                        view.ans2.setTextColor(Color.parseColor("#FFFFFF"))
                        view.userInfo.text = bad

                        wrongA++

                        view.submitButton.isEnabled = false
                        //Megmutatni a helyes valaszt
                        when {
                            view.ans1.text == questions[questionIndex].rightAnswer -> {
                                view.ans1.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans1.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                            view.ans3.text == questions[questionIndex].rightAnswer -> {
                                view.ans3.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans3.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                            view.ans4.text == questions[questionIndex].rightAnswer -> {
                                view.ans4.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans4.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                        }
                    }
                    cBox1.isEnabled = false
                    cBox3.isEnabled = false
                    cBox4.isEnabled = false
                }
                view.checkBox3.isChecked -> {
                    if (view.ans3.text == questions[questionIndex].rightAnswer) {
                        view.ans3.setBackgroundColor(Color.parseColor("#008000"))
                        view.ans3.setTextColor(Color.parseColor("#FFFFFF"))
                        view.userInfo.text = good

                        totalScore += 10
                        rightA++
                        view.score.text = totalScore.toString()

                        view.submitButton.isEnabled = false
                    } else {
                        view.ans3.setBackgroundColor(Color.parseColor("#FF0000"))
                        view.ans3.setTextColor(Color.parseColor("#FFFFFF"))
                        view.userInfo.text = bad

                        wrongA++

                        view.submitButton.isEnabled = false
                        //Megmutatni a helyes valaszt
                        when {
                            view.ans2.text == questions[questionIndex].rightAnswer -> {
                                view.ans2.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans2.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                            view.ans1.text == questions[questionIndex].rightAnswer -> {
                                view.ans1.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans1.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                            view.ans4.text == questions[questionIndex].rightAnswer -> {
                                view.ans4.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans4.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                        }
                    }
                    cBox2.isEnabled = false
                    cBox1.isEnabled = false
                    cBox4.isEnabled = false
                }
                view.checkBox4.isChecked -> {
                    if (view.ans3.text == questions[questionIndex].rightAnswer) {
                        view.ans4.setBackgroundColor(Color.parseColor("#008000"))
                        view.ans4.setTextColor(Color.parseColor("#FFFFFF"))
                        view.userInfo.text = good

                        totalScore += 10
                        rightA++
                        view.score.text = totalScore.toString()

                        view.submitButton.isEnabled = false
                    } else {
                        view.ans4.setBackgroundColor(Color.parseColor("#FF0000"))
                        view.ans4.setTextColor(Color.parseColor("#FFFFFF"))
                        view.userInfo.text = bad

                        wrongA++
                        //Megmutatni a helyes valaszt

                        view.submitButton.isEnabled = false
                        when {
                            view.ans2.text == questions[questionIndex].rightAnswer -> {
                                view.ans2.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans2.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                            view.ans3.text == questions[questionIndex].rightAnswer -> {
                                view.ans3.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans3.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                            view.ans1.text == questions[questionIndex].rightAnswer -> {
                                view.ans1.setBackgroundColor(Color.parseColor("#008000"))
                                view.ans1.setTextColor(Color.parseColor("#FFFFFF"))
                            }
                        }
                    }
                    cBox2.isEnabled = false
                    cBox3.isEnabled = false
                    cBox1.isEnabled = false
                }
                else -> view.userInfo.text = message
            }
        }

        return view
    }
}