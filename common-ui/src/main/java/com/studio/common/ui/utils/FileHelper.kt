package com.studio.common.ui.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.studio.common.ui.utils.FileHelper.Companion.MAX_SUB_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.RandomAccessFile
import kotlin.math.ceil

class FileHelper {
    companion object {
        const val MAX_SUB_SIZE = 5 * 1024 * 1024 // 5MB

        fun deleteFile(
            context: Context,
            path: String
        ) {
            try {
                Timber.e("deleteFile: $path")
                val file = File(path)
                file.delete()
                if (file.exists()) {
                    file.canonicalFile.delete()
                    if (file.exists()) {
                        context.deleteFile(file.name)
                    }
                }
            } catch (e: Exception) {
                Timber.e("deleteFile: ${e.message}")
                e.printStackTrace()
            }
        }

        fun copyFile(
            sourceFile: File,
            destinationDir: File
        ): Boolean {
            if (!sourceFile.exists() || !sourceFile.isFile || !sourceFile.canRead()) {
                return false
            }
            if (!destinationDir.exists()) {
                destinationDir.mkdirs()
            }
            val destinationFile = File(destinationDir, sourceFile.name)
            return try {
                FileInputStream(sourceFile).use { inputStream ->
                    FileOutputStream(destinationFile).use { outputStream ->
                        val buffer = ByteArray(4096)
                        var bytesRead: Int
                        while (inputStream.read(buffer).also { bytesRead = it } >= 0) {
                            outputStream.write(buffer, 0, bytesRead)
                        }
                    }
                }
                true
            } catch (e: IOException) {
                Timber.e("error: ${e.message}")
                e.printStackTrace()
                false
            }
        }

        suspend fun copyFileFromContentUriToDocumentsDirInIO(
            context: Context,
            sourceUri: Uri,
            fileName: String
        ): File? =
            withContext(Dispatchers.IO) {
                copyFileFromContentUriToDocumentsDir(context, sourceUri, fileName)
            }

        private fun copyFileFromContentUriToDocumentsDir(
            context: Context,
            sourceUri: Uri,
            fileName: String
        ): File? {
            try {
                val inputStream = context.contentResolver.openInputStream(sourceUri)
                val destinationDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                val destinationFile = File(destinationDir, fileName)
                val outputStream = FileOutputStream(destinationFile)
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (inputStream?.read(buffer).also { bytesRead = it!! } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }
                outputStream.close()
                inputStream?.close()

                // Optionally delete the source file
                val sourceFile = File(sourceUri.path)
                try {
                    deleteFileFromUri(context, sourceUri)
                } catch (e: Exception) {
                    Timber.e("delete path: ${sourceUri.path}  ${sourceFile.exists()}")
                }
                if (sourceFile.exists()) {
                    val deleted = sourceFile.delete()
                    if (!deleted) {
                        Timber.e("delete error: ${sourceUri.path}")
                    }
                }

                return destinationFile
            } catch (e: IOException) {
                e.printStackTrace()
                // Handle the exception
                return null
            }
        }

        fun deleteFileFromUri(
            context: Context,
            fileUri: Uri
        ): Boolean {
            val contentResolver: ContentResolver = context.contentResolver

            val deletedRows = contentResolver.delete(fileUri, null, null)

            return deletedRows > 0
        }
    }
}

fun File.toByteArray(): ByteArray {
    val input = FileInputStream(this)
    val buffer = ByteArray(1024)
    val bytesList = mutableListOf<ByteArray>()
    var bytesRead: Int
    while (input.read(buffer).also { bytesRead = it } != -1) {
        val readBytes = buffer.copyOfRange(0, bytesRead)
        bytesList.add(readBytes)
    }

    input.close()
    val byteArray = bytesList.fold(ByteArray(0)) { acc, bytes -> acc + bytes }
    return byteArray
}

fun createByteArrayListFromFile(file: File): List<ByteArray> {
    val byteArrayList = mutableListOf<ByteArray>()

    val randomAccessFile = RandomAccessFile(file, "r")

    var currentPosition = 0L
    val fileLength = randomAccessFile.length()

    while (currentPosition < fileLength) {
        val remainingBytes = fileLength - currentPosition
        val currentChunkSize =
            if (remainingBytes < MAX_SUB_SIZE) remainingBytes.toInt() else MAX_SUB_SIZE
        val byteArray = ByteArray(currentChunkSize)
        randomAccessFile.seek(currentPosition)
        randomAccessFile.read(byteArray)

        byteArrayList.add(byteArray)
        currentPosition += currentChunkSize
    }

    randomAccessFile.close()

    return byteArrayList
}

fun isFileGreaterThan5MB(file: File): Boolean {
    val fileSize = file.length()
    val fiveMB = MAX_SUB_SIZE // 5MB in bytes

    return fileSize > fiveMB
}

suspend fun processFileInChunks(
    file: File,
    desiredIndexChunk: Int,
    processChunk: suspend (indexChunk: Int, chunk: ByteArray) -> Unit
) {
    val randomAccessFile = RandomAccessFile(file, "r")

    val startPosition = desiredIndexChunk * MAX_SUB_SIZE.toLong()
    val endPosition = startPosition + MAX_SUB_SIZE - 1
    randomAccessFile.seek(startPosition)

    val byteArray =
        if (endPosition < randomAccessFile.length()) {
            val chunkBytes = ByteArray(MAX_SUB_SIZE)
            randomAccessFile.read(chunkBytes)
            chunkBytes
        } else {
            val remainingBytes = randomAccessFile.length() - startPosition
            val remainingChunkSize = remainingBytes.coerceAtMost(MAX_SUB_SIZE.toLong()).toInt()
            val remainingChunkBytes = ByteArray(remainingChunkSize)
            randomAccessFile.read(remainingChunkBytes)
            remainingChunkBytes
        }

    processChunk(desiredIndexChunk, byteArray)
    withContext(Dispatchers.IO) {
        randomAccessFile.close()
    }
}

fun getFileIndexChunkCount(file: File): Int {
    val fileSize = file.length()
    return ceil(fileSize.toDouble() / MAX_SUB_SIZE).toInt()
}
