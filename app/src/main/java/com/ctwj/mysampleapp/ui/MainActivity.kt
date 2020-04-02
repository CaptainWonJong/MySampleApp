package com.ctwj.mysampleapp.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.base.BaseActivity
import com.ctwj.mysampleapp.databinding.ActivityMainBinding
import com.ctwj.mysampleapp.ui.tab.MainFragmentNaviImpl
import com.ctwj.mysampleapp.util.BackPressHandler
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author CaptainWonJong@gmail.com
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main
) {
    override val viewModel: MainViewModel by viewModel()

    private val mainNavigator: MainFragmentNaviImpl by lazy {
        MainFragmentNaviImpl(this)
    }

    private val backPressHandler = BackPressHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }

    override fun observeLiveData() {
        tab_main.selectedTabIndex.observe(this, Observer {
            mainNavigator.changeTab(tab_main.tabs[it])
        })
    }

    override fun onBackPressed() {
        if (isDestroyed) {
            return
        }

        if (mainNavigator.onBackPressed()) {
            tab_main.selectTab(indexOfTab(mainNavigator.getSelectedFragmentTag()))
            return
        }
        backPressHandler.onBackPressed()
    }

    private fun indexOfTab(tabName: String?): Int = with(tab_main.tabs) {
        this.indexOf(find { it.name == tabName })
    }
}