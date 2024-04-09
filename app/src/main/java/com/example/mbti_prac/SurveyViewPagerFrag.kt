package com.example.mbti_prac

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class SurveyViewPagerFrag: Fragment() {
    private var pageType = 0
    private lateinit var questionListPage1 : List<MbtiQuestion>
    private lateinit var questionListPage2 : List<MbtiQuestion>
    private lateinit var questionListPage3 : List<MbtiQuestion>
    private lateinit var questionListPage4 : List<MbtiQuestion>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{ pageType = it.getInt(MBTI_SURVEY_PAGE_TYPE) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_survey, container, false)
        questionListPage1 = listOf(
            MbtiQuestion(getString(R.string.question1_1), getString(R.string.question1_2_answer1), getString(R.string.question1_2_answer2), -1 , -1),
            MbtiQuestion(getString(R.string.question1_2), getString(R.string.question1_2_answer1), getString(R.string.question1_2_answer2), -1 , -1),
            MbtiQuestion(getString(R.string.question1_3), getString(R.string.question1_2_answer1), getString(R.string.question1_2_answer2), -1 , -1)
        )
        questionListPage2 = listOf(
            MbtiQuestion(getString(R.string.question2_1), getString(R.string.question2_2_answer1), getString(R.string.question2_2_answer2), -1 , -1),
            MbtiQuestion(getString(R.string.question2_2), getString(R.string.question2_2_answer1), getString(R.string.question2_2_answer2), -1 , -1),
            MbtiQuestion(getString(R.string.question2_3), getString(R.string.question2_2_answer1), getString(R.string.question2_2_answer2), -1 , -1)
        )
        questionListPage3 = listOf(
            MbtiQuestion(getString(R.string.question3_1), getString(R.string.question3_2_answer1), getString(R.string.question3_2_answer2), -1 , -1),
            MbtiQuestion(getString(R.string.question3_2), getString(R.string.question3_2_answer1), getString(R.string.question3_2_answer2), -1 , -1),
            MbtiQuestion(getString(R.string.question3_3), getString(R.string.question3_2_answer1), getString(R.string.question3_2_answer2), -1 , -1)
        )
        questionListPage4 = listOf(
            MbtiQuestion(getString(R.string.question4_1), getString(R.string.question4_2_answer1), getString(R.string.question4_2_answer2), -1 , -1),
            MbtiQuestion(getString(R.string.question4_2), getString(R.string.question4_2_answer1), getString(R.string.question4_2_answer2), -1 , -1),
            MbtiQuestion(getString(R.string.question4_3), getString(R.string.question4_2_answer1), getString(R.string.question4_2_answer2), -1 , -1)
        )
        val questionListPages = listOf<List<MbtiQuestion>>(
            questionListPage1,
            questionListPage2,
            questionListPage3,
            questionListPage4
            )
        var surveyData = setSurveyQuestions(pageType)

        val adapter = SurveyRecyclerViewAdapter(surveyData)
        val recyclerView: RecyclerView? = view.findViewById(R.id.rv_survey)
        recyclerView?.layoutManager = LinearLayoutManager(view.context)
        recyclerView?.adapter = adapter

        val viewPager : ViewPager2? = activity?.findViewById<ViewPager2>(R.id.viewPager_survey)
        val btn_survey_submit = view.findViewById<Button>(R.id.btn_survey_submit)

        btn_survey_submit.setOnClickListener{
            var goNextPage : Boolean = true
            for(i in questionListPages[pageType]){
                if(i.cnt1 == -1) {
                    Toast.makeText(context, "선택지를 모두 입력해 주세요.", Toast.LENGTH_LONG).show()
                    goNextPage = false
                }
            }
            if(goNextPage){
                val listCnt = listOf(questionListPages[pageType][0].cnt1, questionListPages[pageType][1].cnt1, questionListPages[pageType][2].cnt1)
                if(listCnt.count { it == 1 } >=2) SurveyMbtiResult.result.add(0) else SurveyMbtiResult.result.add(1)
                Toast.makeText(context, "${questionListPages[pageType][0].cnt1}, ${questionListPages[pageType][1].cnt1}, ${questionListPages[pageType][2].cnt1}, ${SurveyMbtiResult.result}", Toast.LENGTH_LONG).show()
            }
            if(pageType == 3 && goNextPage) {
                val intent = Intent(view.context, MbtiResultActivity::class.java)
                intent.putExtra("questionResult", ArrayList(SurveyMbtiResult.result))
                startActivity(intent)
            } else if(goNextPage) {
                viewPager?.currentItem = pageType + 1
            }
        }

        if(pageType == 3) {
            btn_survey_submit.text = "submit"
            adapter.notifyDataSetChanged()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val radioButtonGroup = view.findViewById<RadioGroup>(R.id.rg_survey_question_option_group)
//        val radioButton1 = view.findViewById<RadioButton>(R.id.rb_survey_question_option1)
//        val radioButton2 = view.findViewById<RadioButton>(R.id.rb_survey_question_option1) -> radioButtonGroup.getChild(int index)로 가져올 수 있음.

//        if(radioButtonGroup.checkedRadioButtonId != -1){
//            val radioButton = radioButtonGroup.getChildAt(0) as RadioButton
//            val response = ArrayList<Int>()
//            if(radioButton.isChecked) response.add(1) else response.add(2)
//
//        } else{
//            Toast.makeText(view.context, "모두 입력해 주세요", Toast.LENGTH_SHORT)
//        }
    }
    fun setSurveyQuestions(page: Int): List<MbtiQuestion>{
        when(page){
            0 -> return questionListPage1
            1 -> return questionListPage2
            2 -> return questionListPage3
            else -> return questionListPage4
        }
    }

//    fun setSurveyResult(questionResults : List<List<MbtiQuestion>>){
//        for(i in questionResults.indices){
//            if(questionResults[i][3] > questionResults[i][4]){
//
//            }
//        }
//    }
    companion object{
        const val MBTI_SURVEY_PAGE_TYPE = "pageType"
        fun newInstance(pageType: Int): Fragment{
            val frag = SurveyViewPagerFrag()
            val args = Bundle()

            args.putInt(MBTI_SURVEY_PAGE_TYPE, pageType)
            frag.arguments = args
            return frag
        }
    }
}

class SurveyMbtiResult{
    companion object {
        var result = mutableListOf<Int>()
    }
}