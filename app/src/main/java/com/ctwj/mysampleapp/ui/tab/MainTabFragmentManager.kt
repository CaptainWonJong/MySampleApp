package com.ctwj.mysampleapp.ui.tab

import android.text.TextUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import timber.log.Timber
import java.lang.ref.WeakReference

/**
 * @author CaptainWonJong@gmail.com
 */
class MainTabFragmentManager(
    private val manager: FragmentManager,
    private val containerResId: Int,
    rootFragment: MainStackFragment?,
    private val callback: FragmentStackCallback?,
    private val lifecycle: Lifecycle
) : FragmentManager.OnBackStackChangedListener, LifecycleObserver {

    private val pendingTaskQueueUntilStarted = mutableListOf<TaskType>()
    private inline val isLifecycleAtLeastStarted
        get() = lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)

    private var recentBackStackEntryCount: Int = 0
    private var rootFragmentToken: String? = null

    private val isFragmentsEmpty: Boolean
        get() = manager.fragments.isEmpty()

    val currentFragment: MainStackFragment?
        get() = checkValid(manager.findFragmentById(containerResId))

    val backStackFragment: MainStackFragment?
        get() {
            val previousIndex = manager.backStackEntryCount - 1
            if (previousIndex < 0) {
                return null
            }

            val previousIndexKey = previousIndex.toString()
            return if (!TextUtils.isEmpty(previousIndexKey)) {
                checkValid(manager.findFragmentByTag(previousIndexKey))
            } else null
        }

    val backStackFragmentsCount: Int
        get() = manager.backStackEntryCount

    init {
        init(rootFragment)
    }

    private fun init(rootFragment: MainStackFragment?) {
        rootFragment?.let {
            rootFragmentToken = it.token
            manager.beginTransaction().apply {
                replace(containerResId, rootFragment, rootFragmentToken)
                commitAllowingStateLoss()
            }
        }
        manager.addOnBackStackChangedListener(this)
        recentBackStackEntryCount = manager.backStackEntryCount

        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        loop@ while (pendingTaskQueueUntilStarted.isNotEmpty()) {
            val task = pendingTaskQueueUntilStarted.first()
            pendingTaskQueueUntilStarted.removeAt(0)

            when (task) {
                is TaskType.AddFragment -> addFragment(task.fragment.get() ?: continue@loop)
                is TaskType.ReplaceFragment -> replaceFragment(task.fragment.get() ?: continue@loop)
                is TaskType.ReloadFragment -> reloadContentFragment(task.fragment.get() ?: continue@loop)
                is TaskType.PopBackStack -> popBackStackFragment()
                is TaskType.ClearBackStack -> clearBackStack()
            }
        }
    }

    override fun onBackStackChanged() {
        val currentBackStackEntryCount = manager.backStackEntryCount
        if (!isFragmentsEmpty) {
            val curFragment = checkValid(manager.findFragmentById(containerResId))
            if (callback != null && curFragment != null && recentBackStackEntryCount > currentBackStackEntryCount) {
                callback.onFragmentBackStackPopped(curFragment)
            }
        }
        recentBackStackEntryCount = currentBackStackEntryCount
    }

    fun release() {
        manager.removeOnBackStackChangedListener(this)
        lifecycle.removeObserver(this)
        pendingTaskQueueUntilStarted.clear()
    }

    @Deprecated("Use replaceFragment()")
    fun addFragment(fragment: MainStackFragment) {
        val token: String? = currentFragment?.token

        if (!isLifecycleAtLeastStarted) {
            pendingTaskQueueUntilStarted.add(TaskType.AddFragment(WeakReference(fragment)))
            return
        }

        try {
            manager.popBackStackImmediate(token, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } catch (ex: Exception) { Timber.e(ex) }

        if (TextUtils.equals(fragment.token, token)) {
            reloadContentFragment(fragment)
            return
        }

        manager.beginTransaction().apply {
            add(containerResId, fragment, calculateKeyIndex())
            addToBackStack(token)
            commitAllowingStateLoss()
        }
    }

    fun replaceFragment(fragment: MainStackFragment) {
        val token = currentFragment?.token

        if (!isLifecycleAtLeastStarted) {
            pendingTaskQueueUntilStarted.add(TaskType.ReplaceFragment(WeakReference(fragment)))
            return
        }

        try {
            manager.popBackStackImmediate(token, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } catch (ex: Exception) { Timber.e(ex) }

        if (TextUtils.equals(fragment.token, token)) {
            reloadContentFragment(fragment)
            return
        }

        manager.beginTransaction().apply {
            replace(containerResId, fragment, calculateKeyIndex())
            addToBackStack(token)
            commitAllowingStateLoss()
        }
    }

    fun removeFragment(fragment: MainStackFragment) = with(manager.beginTransaction()) {
        remove(fragment)
        commitAllowingStateLoss()
    }

    private fun calculateKeyIndex(): String = (manager.backStackEntryCount + 1).toString()

    fun getFragmentAt(index: Int): MainStackFragment? {
        return manager.findFragmentByTag(index.toString()) as? MainStackFragment
    }

    fun getFragmentByTag(tag: String): MainStackFragment? {
        return checkValid(manager.findFragmentByTag(tag))
    }

    private fun checkValid(fragment: Fragment?): MainStackFragment? {
        return if (fragment is MainStackFragment) {
            fragment
        } else null
    }

    fun popBackStackFragment() {
        if (!isLifecycleAtLeastStarted) {
            pendingTaskQueueUntilStarted.add(TaskType.PopBackStack)
            return
        }

        try {
            manager.popBackStackImmediate()
        } catch (ex: Exception) { Timber.e(ex) }
    }

    fun clearBackStack() {
        if (!isLifecycleAtLeastStarted) {
            pendingTaskQueueUntilStarted.add(TaskType.ClearBackStack)
            return
        }

        try {
            manager.popBackStackImmediate(rootFragmentToken, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } catch (ex: Exception) { Timber.e(ex) }
    }

    private fun reloadContentFragment(newFragment: MainStackFragment) {
        checkValid(manager.findFragmentById(containerResId)) ?: return

        if (!isLifecycleAtLeastStarted) {
            pendingTaskQueueUntilStarted.add(TaskType.ReloadFragment(WeakReference(newFragment)))
            return
        }
        manager.popBackStackImmediate()
        manager.beginTransaction()
            .add(containerResId, newFragment, calculateKeyIndex())
            .addToBackStack(newFragment.token)
            .commitAllowingStateLoss()
    }

    interface FragmentStackCallback {
        fun onFragmentBackStackPopped(fragment: Fragment)
    }

    sealed class TaskType {
        data class AddFragment(val fragment: WeakReference<MainStackFragment>) : TaskType()
        data class ReplaceFragment(val fragment: WeakReference<MainStackFragment>) : TaskType()
        data class ReloadFragment(val fragment: WeakReference<MainStackFragment>) : TaskType()
        object PopBackStack : TaskType()
        object ClearBackStack : TaskType()
    }
}
