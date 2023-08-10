package com.example.lucafate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_account.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment(), AccountInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var accountPresenter = AccountPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        val backBtn = view.findViewById<ImageButton>(R.id.goBackBtn)
        backBtn.setOnClickListener { findNavController().navigate(R.id.action_accountFragment_to_nav_graph) }

        val infoBtn = view.findViewById<ImageButton>(R.id.expandAccountImgBtn)
        val accountInfoView = view.findViewById<LinearLayout>(R.id.accountInfoView)
        infoBtn.setOnClickListener {
        if (accountInfoView.visibility == View.VISIBLE)    {
            accountInfoView.visibility = View.GONE
            infoBtn.setImageResource(android.R.drawable.arrow_down_float)
        }else{
            accountInfoView.visibility = View.VISIBLE
            infoBtn.setImageResource(android.R.drawable.arrow_up_float)
        }
        }

        val infoBtn2 = view.findViewById<ImageButton>(R.id.expandAccountImgBtn2)
        val accountInfoView2 = view.findViewById<LinearLayout>(R.id.accountInfoView2)
        infoBtn2.setOnClickListener {
            if (accountInfoView2.visibility == View.VISIBLE)    {
            accountInfoView2.visibility = View.GONE
            infoBtn2.setImageResource(android.R.drawable.arrow_down_float)
            }else{
            accountInfoView2.visibility = View.VISIBLE
            infoBtn2.setImageResource(android.R.drawable.arrow_up_float)
            }
        }


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun closeView() {
//        accountInfoView2.visibility = View.GONE
//        expandAccountImgBtn2.setImageResource(android.R.drawable.arrow_down_float)
        println("Closing")
    }

    override fun openView() {
//        accountInfoView2.visibility = View.VISIBLE
//        expandAccountImgBtn2.setImageResource(android.R.drawable.arrow_up_float)
        println("Opening")
    }
}