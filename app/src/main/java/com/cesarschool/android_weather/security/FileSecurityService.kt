package com.cesarschool.android_weather.security

import android.content.Context
import java.io.File
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKeys

class FileSecurityService : FileSecurity {

    override fun createEncrypted(context: Context, fileName: String, fileContent: String) {
        val file = File(context.filesDir, fileName)

        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        if (file.exists()) {
            file.delete()
        }

        val encryptedFile = EncryptedFile.Builder(
            file,
            context,
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        encryptedFile.openFileOutput().use { writer ->
            writer.write(fileContent.toByteArray())
        }
    }

    override fun readEncrypted(context: Context, file: File): String {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

        val encryptedFile = EncryptedFile.Builder(
            file,
            context,
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()

        lateinit var result: String

        encryptedFile.openFileInput().use { inputStream ->
            result = inputStream.readBytes().decodeToString()
        }

        return result
    }
}