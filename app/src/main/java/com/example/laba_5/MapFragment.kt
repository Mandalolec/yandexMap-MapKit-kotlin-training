package com.example.laba_5

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.laba_5.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.ui_view.ViewProvider

class MapFragment : Fragment() {

    lateinit var binding: FragmentMapBinding
    lateinit var mapView: MapView
    private val dataModel: DataModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.setApiKey("55294257-d3f6-4017-b46e-6b5406d2398e")
        MapKitFactory.initialize(this.context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var dataPoint = DataPoints()

        mapView = binding.mapview

        observeClickButton(dataModel, mapView, dataPoint)

        drawMyLocationMark(dataPoint.point1)
        drawMyLocationMark(dataPoint.point2)
        drawMyLocationMark(dataPoint.point3)
        drawMyLocationMark(dataPoint.point4)
        drawMyLocationMark(dataPoint.point5)
        drawMyLocationMark(dataPoint.point6)
        drawMyLocationMark(dataPoint.point7)
        drawMyLocationMark(dataPoint.point8)
    }

    private fun observeClickButton(dataModel: DataModel, mapView: MapView, dataPoint: DataPoints){
        dataModel.messageClickButton.observe(activity as LifecycleOwner){
            if(it == true){
                observeNumberButton(dataModel, mapView, dataPoint)
                dataModel.messageClickButton.value = false
            }
        }
    }

    private fun observeNumberButton(dataModel: DataModel, mapView: MapView, dataPoint: DataPoints){
        dataModel.messageNumberButton.observe(activity as LifecycleOwner){
            when(it){
                1 -> moveCamera(mapView, dataPoint.point1)
                2 -> moveCamera(mapView, dataPoint.point2)
                3 -> moveCamera(mapView, dataPoint.point3)
                4 -> moveCamera(mapView, dataPoint.point4)
                5 -> moveCamera(mapView, dataPoint.point5)
                6 -> moveCamera(mapView, dataPoint.point6)
                7 -> moveCamera(mapView, dataPoint.point7)
                8 -> moveCamera(mapView, dataPoint.point8)
            }
        }
    }

    private fun moveCamera(mapView: MapView, point: Point){
        mapView.map.move(
            CameraPosition(point, 18.0f, 0.0f, 0.0f),
        Animation(Animation.Type.SMOOTH, 2f), null
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun drawMyLocationMark(point: Point){
        val view = View(requireContext()).apply {
            background = requireContext().getDrawable(R.mipmap.ic_launcher_cat_foreground)
        }

        mapView.map.mapObjects.addPlacemark(
            point,
            ViewProvider(view)
        )
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }
}