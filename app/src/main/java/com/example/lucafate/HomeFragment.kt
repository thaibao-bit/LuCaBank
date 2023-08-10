package com.example.lucafate

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var features: List<GridViewModal>
    lateinit var mGridView: GridView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val homeButton = view.findViewById<Button>(R.id.loginButton)
        homeButton.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_loginFragment) }
        val accountTouchView = view.findViewById<RelativeLayout>(R.id.accountTouchView)
        accountTouchView.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_accountActivity) }

        mGridView = view.findViewById<GridView>(R.id.gridViewHomePage)
        features = ArrayList<GridViewModal>()
        features = features + GridViewModal("Chuyển & nhận tiền")
        features = features + GridViewModal("Thanh toán hóa đơn")
        features = features + GridViewModal("Dịch vụ vay và tín dụng")
        features = features + GridViewModal("Dịch vụ tiết kiệm")
        features = features + GridViewModal("Nạp tiền điện thoại")
        features = features + GridViewModal("Mở thẻ")
        features = features + GridViewModal("Thuế & phí dịch vụ công")
        features = features + GridViewModal("Xác thực giao dịch")

        val featureAdapter = GridViewAdapter(features, this.requireActivity())

        mGridView.adapter = featureAdapter
//        var imageButton: ImageButton = findViewById<ImageButton>(R.id.imageButton)
//        imageButton.setOnClickListener { println("Why did you click this button?") }


        return  view
    }
}



data class GridViewModal(
    val featureName: String
)

internal class GridViewAdapter(
    private val featureList: List<GridViewModal>,
    private val context: Context
) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var featureTextView: TextView
    override fun getCount(): Int {
        return featureList.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.feature_item, null)
        }
        featureTextView = convertView!!.findViewById(R.id.textFeatureId)
        featureTextView.setText(featureList.get(position).featureName)
        return convertView
    }

}
