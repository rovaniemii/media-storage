package com.hy0417sage.mediastorage.main

import android.os.Bundle
import androidx.activity.compose.setContent
import com.hy0417sage.core.ui.BaseActivity
import com.hy0417sage.mediastorage.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    { ActivityMainBinding.inflate(it) }
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainNavScreen()
        }
    }
}