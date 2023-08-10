package com.example.lucafate

import android.view.View
import android.widget.LinearLayout

class AccountPresenter {
    private lateinit var accountInterface: AccountInterface
    fun handleButton (linearLayout: LinearLayout){
        if (linearLayout.visibility == View.VISIBLE)    {
            accountInterface.closeView()
//            accountInfoView.visibility = View.GONE
//            infoBtn.setImageResource(android.R.drawable.arrow_down_float)
        }else{
            accountInterface.openView()
//            accountInfoView.visibility = View.VISIBLE
//            infoBtn.setImageResource(android.R.drawable.arrow_up_float)
        }
    }

    }
