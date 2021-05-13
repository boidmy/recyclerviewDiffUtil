package com.example.diffutiltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.SimpleItemAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val array: ArrayList<RadioDataModel> = arrayListOf()

        array.add(RadioDataModel("첫번째", key = 0))
        array.add(RadioDataModel("두번째", key = 1))
        array.add(RadioDataModel("세번째", key = 2))
        array.add(RadioDataModel("네번째", key = 3))
        array.add(RadioDataModel("다섯번째", key = 4))

        val inter = object : RadioAdapter.Call {
            override fun callBack(position: Int) {
                val ar = DataManager.data.map {
                    if (it.key == position) {
                        it.copy(selected = it.selected.not())
                    } else {
                        it
                    }
                }
                (mainRv.adapter as RadioAdapter).setData(ar)
            }
        }

        mainRv.adapter = RadioAdapter(inter)
        (mainRv.adapter as RadioAdapter).setData(array)
        (mainRv.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

    }
}