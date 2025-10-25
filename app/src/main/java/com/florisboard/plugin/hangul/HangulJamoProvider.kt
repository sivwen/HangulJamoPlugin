package com.florisboard.plugin.hangul

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.os.Bundle

/**
 * ContentProvider를 통한 플러그인 메타데이터 제공
 */
class HangulJamoProvider : ContentProvider() {
    
    companion object {
        const val AUTHORITY = "com.florisboard.plugin.hangul.provider"
        const val PATH_PLUGIN_INFO = "plugin_info"
        
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, PATH_PLUGIN_INFO, 1)
        }
    }
    
    override fun onCreate(): Boolean = true
    
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            1 -> {
                MatrixCursor(arrayOf("name", "version", "enabled")).apply {
                    addRow(arrayOf(
                        "HangulJamoBackspace",
                        "1.0.0",
                        1
                    ))
                }
            }
            else -> null
        }
    }
    
    override fun call(method: String, arg: String?, extras: Bundle?): Bundle? {
        return when (method) {
            "handleBackspace" -> {
                val beforeCursor = arg ?: return null
                val handler = JamoBackspaceHandler()
                val result = handler.processBackspace(beforeCursor)
                
                Bundle().apply {
                    putString("result", result)
                }
            }
            else -> null
        }
    }
    
    override fun getType(uri: Uri): String? = null
    override fun insert(uri: Uri, values: ContentValues?): Uri? = null
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int = 0
}
