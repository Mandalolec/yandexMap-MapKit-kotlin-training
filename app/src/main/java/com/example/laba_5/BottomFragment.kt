package com.example.laba_5

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.laba_5.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yandex.mapkit.geometry.Point

private const val COLLAPSED_HEIGHT = 228

class BottomFragment : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetLayoutBinding
    private val dataModel: DataModel by activityViewModels()

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetLayoutBinding.bind(inflater.inflate(R.layout.bottom_sheet_layout, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonsListener()
    }

    override fun onStart() {
        super.onStart()

        val density = requireContext().resources.displayMetrics.density

        dialog?.let {
            val bottomSheet = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)

            behavior.peekHeight = (COLLAPSED_HEIGHT * density).toInt()
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){

                override fun onStateChanged(bottomSheet: View, newState: Int) {

                }

                override fun onSlide(bottomSheet: View, slideOffset: Float){
                    with(binding){
                        if(slideOffset > 0){
                            layoutCollapsed.alpha = 1 - 2 * slideOffset
                            layoutExpanded.alpha = slideOffset * slideOffset

                            if(slideOffset > 0.5){
                                layoutCollapsed.visibility = View.GONE
                                layoutExpanded.visibility = View.VISIBLE
                            }

                            if(slideOffset < 0.5 && binding.layoutExpanded.visibility == View.VISIBLE){
                                layoutCollapsed.visibility = View.VISIBLE
                                layoutExpanded.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            })
        }
    }

    private fun buttonsListener(){
        binding.button1.setOnClickListener{
            dataModel.messageNumberButton.value = 1
            dataModel.messageClickButton.value = true
        }
        binding.button2.setOnClickListener{
            dataModel.messageNumberButton.value = 2
            dataModel.messageClickButton.value = true
        }
        binding.button3.setOnClickListener{
            dataModel.messageNumberButton.value = 3
            dataModel.messageClickButton.value = true
        }
        binding.button4.setOnClickListener {
            dataModel.messageNumberButton.value = 4
            dataModel.messageClickButton.value = true
        }
        binding.button5.setOnClickListener {
            dataModel.messageNumberButton.value = 5
            dataModel.messageClickButton.value = true
        }
        binding.button6.setOnClickListener {
            dataModel.messageNumberButton.value = 6
            dataModel.messageClickButton.value = true
        }
        binding.button7.setOnClickListener {
            dataModel.messageNumberButton.value = 7
            dataModel.messageClickButton.value = true
        }
        binding.button8.setOnClickListener {
            dataModel.messageNumberButton.value = 8
            dataModel.messageClickButton.value = true
        }
    }
}