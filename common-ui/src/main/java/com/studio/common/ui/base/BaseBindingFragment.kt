package com.studio.common.ui.base

import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.studio.common.ui.constant.isBackToRootBottomTab
import com.studio.common.ui.databinding.ViewProgressDialogBinding
import com.studio.common.ui.navigator.NavigationCommand
import com.studio.common.ui.navigator.NavigatorImpl
import com.studio.common.ui.utils.Listener
import com.studio.common.ui.utils.isDeveloperOptionsEnabled
import com.studio.common.ui.utils.isGooglePlayServicesAvailable
import com.studio.common.ui.utils.isProxySet
import com.studio.common.ui.utils.isRunningInWiFiDebug
import com.studio.common.ui.utils.isUsbDebuggingEnabled
import com.studio.common.ui.utils.requestIntegrityTokenStandard
import com.studio.common.ui.widget.CustomToast
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseBindingFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {
    protected abstract val viewModel: VM
    private var mBinding: DB? = null
    protected val binding get() = mBinding!!
    private val navigator by lazy { NavigatorImpl() }
    private val progressDialogBinding: ViewProgressDialogBinding by lazy {
        ViewProgressDialogBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private val progressDialog: Dialog by lazy {
        Dialog(requireContext()).apply {
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setContentView(progressDialogBinding.root)
            setCancelable(false)
        }
    }

    fun <T> navigatePopBack(
        keyDATA: String,
        unit: (data: T) -> Unit
    ) {
        findNavController()
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<T>(keyDATA)
            ?.observe(viewLifecycleOwner) {
                findNavController().currentBackStackEntry?.savedStateHandle?.remove<T>(keyDATA)
                unit(it)
            }
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewModelBindingVariable(): Int

    protected abstract fun initView()

    protected abstract fun initViewModel()

    protected open fun initViewInstance() {
        if (!isGooglePlayServicesAvailable(requireContext())) {
            Timber.e("--- Stop all app no GMS")
        } else if (isDeveloperOptionsEnabled(requireContext())) {
            Timber.e("--- Stop all app debug mode")
        } else if (isUsbDebuggingEnabled(requireContext())) {
            Timber.e("--- Stop all app usb debug")
        } else if (isRunningInWiFiDebug(requireContext())) {
            Timber.e("--- Stop all app wifi debug")
        } else if (isProxySet()) {
            Timber.e("--- Stop all app proxy")
        }
        requestIntegrityTokenStandard(
            requireContext(),
            634495574905,
            object : Listener {
                override fun onStart() {
                    Timber.d("--- onStart")
                }

                override fun onSuccess(token: String) {
                    Timber.e("--- verify token with BE: $token")
                }

                override fun onFail(exception: Exception) {
                    Timber.e("--- Stop all app ${exception.hashCode()}")
                }

                override fun onCompleted() {
                    Timber.d("--- onCompleted")
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(getViewModelBindingVariable(), viewModel)
        }
        initViewInstance()
        initView()
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        navigator.setContext(requireContext())
        viewModel.setNavigator(navigator)
        initViewModel()
        viewModel.loadingProgress.observe(viewLifecycleOwner) {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        }
        viewModel.toastMessage.observeSingle(viewLifecycleOwner) {
            CustomToast.make(requireView(), requireActivity(), it).show()
        }
        viewModel.navigateEvent.observeSingle(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                it.let { command ->
                    navigate(command)
                }
            }
        }
    }

    private fun getNestedNavController() =
        ((parentFragment)?.parentFragment)?.findNavController()
            ?: parentFragment?.findNavController()

    private fun getRootNestedNavController() = parentFragment?.parentFragment?.parentFragment?.findNavController()

    fun navigate(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To -> {
                with(findNavController()) {
                    val actionId = command.directions.actionId
                    val action =
                        currentDestination?.getAction(actionId) ?: graph.getAction(actionId)
                    if (action != null && currentDestination?.id != action.destinationId) {
                        navigate(command.directions)
                    } else {
                        Timber.e("--- unknown action: $action")
                    }
                }
            }

            is NavigationCommand.ParentTo -> {
                getRootNestedNavController()?.run {
                    isBackToRootBottomTab = false
                    val actionId = command.directions.actionId
                    val action =
                        currentDestination?.getAction(actionId) ?: graph.getAction(
                            actionId
                        )
                    if (action != null && currentDestination?.id != action.destinationId) {
                        navigate(command.directions)
                    } else {
                        Timber.e("--- unknown action: $action")
                    }
                }
            }

            is NavigationCommand.BackTo -> {
                try {
                    with(findNavController()) {
                        popBackStack(
                            command.destinationId,
                            command.inclusive
                        )
                    }
                } catch (e: Exception) {
                    Timber.e(
                        "NavigationCommand.BackTo Exception: destinationId: ${command.destinationId} message:${e.message} "
                    )
                }
            }

            is NavigationCommand.BackToWithData -> {
                try {
                    with(findNavController()) {
                        getBackStackEntry(command.destinationId).savedStateHandle.set(
                            command.key,
                            command.value
                        )
                        popBackStack(
                            command.destinationId,
                            command.inclusive
                        )
                    }
                } catch (e: Exception) {
                    Timber.e(
                        "NavigationCommand.BackToWithData Exception: destinationId: ${command.destinationId} message:${e.message} "
                    )
                }
            }

            is NavigationCommand.BackWithData -> {
                try {
                    with(findNavController()) {
                        previousBackStackEntry?.savedStateHandle?.set(command.key, command.value)
                        popBackStack()
                    }
                } catch (e: Exception) {
                    Timber.e("NavigationCommand.BackToWithData Exception: ${e.message} ")
                }
            }

            is NavigationCommand.Back -> {
                with(findNavController()) { popBackStack() }
            }

            is NavigationCommand.ToRoot -> {
                findNavController().popBackStack(
                    findNavController().graph.startDestinationId,
                    false
                )
            }

            is NavigationCommand.DeepLinkTo -> {
                try {
                    isBackToRootBottomTab = false
                    findNavController().navigate(command.uri, command.navOptions)
                } catch (deepLinkNotFound: IllegalArgumentException) {
                    Timber.e("--- Error: ${deepLinkNotFound.message}")
                }
            }

            is NavigationCommand.ParentDeepLinkTo -> {
                try {
                    isBackToRootBottomTab = false
                    getNestedNavController()?.navigate(command.uri, command.navOptions)
                } catch (deepLinkNotFound: IllegalArgumentException) {
                    Timber.e("--- Error: ${deepLinkNotFound.message}")
                }
            }

            else -> {
                Timber.e("--- unknown define command")
            }
        }
    }

    override fun onDestroyView() {
        mBinding = null
        progressDialog.dismiss()
        super.onDestroyView()
    }

    fun isGooglePlayServicesAvailable(): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(requireActivity())
        if (resultCode != ConnectionResult.SUCCESS) {
            Timber.e("--- Error google service No Available")
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(this, resultCode, 2404).apply {
                    this?.setCancelable(false)
                    this?.show()
                }
            }
        }
        return resultCode == ConnectionResult.SUCCESS
    }

    private fun checkPermission(permission: String): Boolean =
        ContextCompat.checkSelfPermission(
            requireActivity(),
            permission
        ) != PackageManager.PERMISSION_DENIED
}
