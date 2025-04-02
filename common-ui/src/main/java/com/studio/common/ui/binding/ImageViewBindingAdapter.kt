package com.studio.common.ui.binding

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.studio.common.res.R
import java.text.DecimalFormat

@BindingAdapter("uri")
fun ImageView.loadUrlSafe(uri: Uri?) {
    uri?.let {
        this.load(it) {
            crossfade(true)
            build()
        }
    }
}

@BindingAdapter("url")
fun ImageView.loadUrlSafe(url: String?) {
    url?.let {
        this.load(it) {
            crossfade(true)
            build()
        }
    }
}

@BindingAdapter("imageRes")
fun ImageView.loadImageRes(imageRes: Int?) {
    imageRes?.let {
        this.load(it) {
            crossfade(true)
            placeholder(R.drawable.ic_loading_progressbar)
            build()
        }
    }
}

@BindingAdapter("url", "onStart", "onSuccess", "onError")
fun ImageView.loadUrlSafe(
    url: String?,
    onStart: () -> Unit,
    onSuccess: () -> Unit,
    onError: () -> Unit
) {
    url?.let {
        this.load(it) {
            crossfade(true)
            listener(
                object : ImageRequest.Listener {
                    override fun onStart(request: ImageRequest) {
                        super.onStart(request)
                        onStart.invoke()
                    }

                    override fun onSuccess(
                        request: ImageRequest,
                        result: SuccessResult
                    ) {
                        super.onSuccess(request, result)
                        onSuccess.invoke()
                    }

                    override fun onError(
                        request: ImageRequest,
                        result: ErrorResult
                    ) {
                        super.onError(request, result)
                        onError.invoke()
                    }
                }
            )
            build()
        }
    }
}

@SuppressLint("DiscouragedApi")
@BindingAdapter("imageMenu")
fun ImageView.imageMenu(imageMenu: Int?) {
    val decimalFormat = DecimalFormat("00")
    val imageString = decimalFormat.format(imageMenu)
    val imageRes =
        this.resources.getIdentifier("ic_menu_".plus(imageString), "drawable", this.context.packageName)
    if (imageRes != 0) {
        this.load(imageRes) {
            crossfade(true)
            build()
        }
    }
}
