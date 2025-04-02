package com.studio.common.ui.base

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.studio.common.res.R
import com.studio.common.ui.constant.isOpenDialog
import com.studio.common.ui.databinding.ViewProgressDialogBinding
import com.studio.common.ui.navigator.NavigationCommand
import com.studio.common.ui.navigator.NavigatorImpl
import com.studio.common.ui.widget.CustomToast
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseBindingDialogFragment<VM : BaseViewModel, DB : ViewDataBinding> :
    DialogFragment() {
    protected abstract val viewModel: VM
    private var minding: DB? = null
    protected val binding get() = minding!!
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
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(keyDATA)
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

    protected open fun initViewInstance() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isOpenDialog = true
        setStyle(STYLE_NORMAL, R.style.CustomDialogTheme)
        navigator.setContext(requireContext())
        viewModel.setNavigator(navigator)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        minding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        minding?.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(getViewModelBindingVariable(), viewModel)
        }
        initViewInstance()
        initView()
        return binding.root
    }

    fun getNestedNavController() =
        ((parentFragment)?.parentFragment)?.findNavController()
            ?: parentFragment?.findNavController()

    private fun getRootNestedNavController() = parentFragment?.parentFragment?.parentFragment?.findNavController()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        viewModel.loadingProgress.observe(viewLifecycleOwner) {
            Timber.e("--- loadingProgress: $it")
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        }
        viewModel.navigateEvent.observeSingle(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                it.let { command ->
                    navigate(command)
                }
            }
        }
        viewModel.toastMessage.observeSingle(viewLifecycleOwner) {
            CustomToast.make(requireView(), requireActivity(), it).show()
        }
    }

    private fun navigate(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.To -> {
                with(findNavController()) {
                    val actionId = command.directions.actionId
                    val action =
                        currentDestination?.getAction(actionId) ?: graph.getAction(actionId)
                    if (action != null && currentDestination?.id != action.destinationId) {
                        navigate(command.directions)
                    }
                }
            }

            is NavigationCommand.BackWithData -> {
                with(findNavController()) {
                    previousBackStackEntry?.savedStateHandle?.set(command.key, command.value)
                    popBackStack()
                }
            }

            is NavigationCommand.BackTo -> {
                with(findNavController()) {
                    popBackStack(
                        command.destinationId,
                        command.inclusive
                    )
                }
            }

            is NavigationCommand.BackToWithData -> {
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

            else -> {
                Timber.e("--- unknown define command")
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        Timber.e("onDismiss")
        super.onDismiss(dialog)
    }

    override fun onDestroyView() {
        isOpenDialog = false
        minding = null
        super.onDestroyView()
    }
}
