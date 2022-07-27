package com.example.bachelor_app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bachelor_app.util.Constants
import com.example.bachelor_app.util.Logger


abstract class BaseFragment : Fragment() {

    private val BASE = javaClass.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Logger.info(Constants.LIFECYCLE_TAG, "$BASE.onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Logger.info(Constants.LIFECYCLE_TAG, "$BASE.onCreate()")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Logger.info(Constants.LIFECYCLE_TAG, "$BASE.onViewCreated()")
    }

    override fun onStart() {
        super.onStart()

        Logger.info(Constants.LIFECYCLE_TAG, "$BASE.onStart()")
    }

    override fun onResume() {
        super.onResume()

        Logger.info(Constants.LIFECYCLE_TAG, "$BASE.onResume()")
    }

    override fun onPause() {
        super.onPause()

        Logger.info(Constants.LIFECYCLE_TAG, "$BASE.onPause()")
    }

    override fun onStop() {
        super.onStop()

        Logger.info(Constants.LIFECYCLE_TAG, "$BASE.onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Logger.info(Constants.LIFECYCLE_TAG, "$BASE.onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()

        Logger.info(Constants.LIFECYCLE_TAG, "$BASE.onDestroy()")
    }
}
