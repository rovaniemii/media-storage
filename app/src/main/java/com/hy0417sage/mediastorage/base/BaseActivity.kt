package com.hy0417sage.mediastorage.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.hy0417sage.mediastorage.util.BackPressUtil

abstract class BaseActivity<VB : ViewBinding>(
    private val inflate: (LayoutInflater) -> VB
) : AppCompatActivity() {

    protected lateinit var binding: VB
    private var backPressHandler: BackPressUtil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        backPressHandler = BackPressUtil(this)
    }

    // 이전 화면으로 돌아가기
    override fun onBackPressed() {
        backPressHandler!!.onBackPressed()
    }
}