package com.studio.common.ui.share

sealed class ShareUploadState {
    data class Converting(val status: Int) : ShareUploadState()
    data class Progress(val progress: Long) : ShareUploadState()
    data class Success(val eTag: String) : ShareUploadState()
    data class Error(val errorMessage: String) : ShareUploadState()
}
