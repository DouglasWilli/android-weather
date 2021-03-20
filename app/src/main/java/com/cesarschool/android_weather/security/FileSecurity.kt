package com.cesarschool.android_weather.security

import android.content.Context
import java.io.File

interface FileSecurity {

    fun createEncrypted(context: Context, fileName: String, fileContent: String)
    fun readEncrypted(context: Context, file: File): String
}