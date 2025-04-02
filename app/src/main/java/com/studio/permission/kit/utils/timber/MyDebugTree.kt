package com.studio.permission.kit.utils.timber

import android.content.Context
import android.util.Log
import androidx.annotation.Nullable
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class MyDebugTree
    @Inject
    constructor(
        var context: Context?,
        var fileLoggingEnabled: Boolean
    ) : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String? =
            String.format(
                "(%s:%s)",
                element.fileName,
                element.lineNumber
            )

        override fun log(
            priority: Int,
            tag: String?,
            message: String,
            t: Throwable?
        ) {
            super.log(priority, tag, message, t)
            try {
                if (fileLoggingEnabled && priority == Log.ERROR) {
                    val fileNameTimeStamp: String =
                        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                    val logTimeStamp: String =
                        SimpleDateFormat("MM dd yyyy 'at' hh:mm:ss:SSS aaa", Locale.getDefault())
                            .format(Date())
                    val fileName = "$fileNameTimeStamp.html"
                    // Create file
                    val file: File? = generateFile(context, fileName)
                    // If file created or exists save logs
                    if (file != null) {
                        val writer = FileWriter(file, true)
                        writer
                            .append(
                                "<p style=\"background:lightgray;\"><strong " +
                                    "style=\"background:lightblue;\">  "
                            ).append(logTimeStamp)
                            .append(" :  </strong><strong>  ")
                            .append(tag)
                            .append("</strong> - ")
                            .append(message)
                            .append("</p>")
                        writer.flush()
                        writer.close()
                    }
                }
            } catch (e: Exception) {
            }
        }

        fun deleteFileLogFolder(context: Context?): Boolean = deleteLogFolder(context)

        fun deleteLogFileByFileName(
            context: Context?,
            fileName: String
        ): Boolean? = deleteLogFile(context, fileName)

        companion object {
            // Helper method to create file
            @Nullable
            private fun generateFile(
                context: Context?,
                fileName: String
            ): File? {
                var file: File? = null
                // creating log folder inside data/data/mypackagename/files/
                val root = File(context?.filesDir?.absolutePath, "log")
                var dirExists = true
                if (!root.exists()) {
                    dirExists = root.mkdirs()
                }
                if (dirExists) {
                    file = File(root, fileName)
                }
                return file
            }

            // Helper method to delete already created file
            @Nullable
            private fun deleteLogFile(
                context: Context?,
                fileName: String
            ): Boolean? {
                var file: File? = null
                // creating log folder inside data/data/mypackagename/files/
                val root = File(context?.filesDir?.absolutePath, "log")
                var dirExists = true
                if (!root.exists()) {
                    dirExists = root.mkdirs()
                }
                if (dirExists) {
                    file = File(root, fileName)
                }
                return file?.delete()
            }

            // Helper method to delete log folder
            @Nullable
            private fun deleteLogFolder(context: Context?): Boolean {
                // creating log folder inside data/data/mypackagename/files/
                val root = File(context?.filesDir?.absolutePath, "log")
                return root.delete()
            }
        }
    }
