package com.florisboard.plugin.hangul

/**
 * 자모 단위 백스페이스 처리 로직
 */
class JamoBackspaceHandler {
    
    fun processBackspace(beforeCursor: String): String? {
        if (beforeCursor.isEmpty()) return null
        
        val lastChar = beforeCursor.last()
        
        if (!HangulUtils.isHangulSyllable(lastChar)) {
            return null
        }
        
        val decomposed = HangulUtils.decomposeHangul(lastChar) ?: return null
        val (chosung, jungsung, jongsung) = decomposed
        
        return when {
            jongsung != null -> {
                handleJongsungDelete(chosung, jungsung, jongsung)
            }
            else -> {
                handleJungsungDelete(chosung, jungsung)
            }
        }
    }
    
    private fun handleJongsungDelete(
        chosung: Char,
        jungsung: Char,
        jongsung: Char
    ): String {
        val splitJongsung = HangulUtils.splitDoubleJongsung(jongsung)
        
        return if (splitJongsung != null && splitJongsung.size == 2) {
            HangulUtils.composeHangul(chosung, jungsung, splitJongsung[0]).toString()
        } else {
            HangulUtils.composeHangul(chosung, jungsung, null).toString()
        }
    }
    
    private fun handleJungsungDelete(
        chosung: Char,
        jungsung: Char
    ): String {
        val simplifiedJungsung = simplifyJungsung(jungsung)
        
        return if (simplifiedJungsung != null) {
            HangulUtils.composeHangul(chosung, simplifiedJungsung, null).toString()
        } else {
            chosung.toString()
        }
    }
    
    private fun simplifyJungsung(jungsung: Char): Char? {
        return when (jungsung) {
            'ㅘ' -> 'ㅗ'
            'ㅙ' -> 'ㅗ'
            'ㅚ' -> 'ㅗ'
            'ㅝ' -> 'ㅜ'
            'ㅞ' -> 'ㅜ'
            'ㅟ' -> 'ㅜ'
            'ㅢ' -> 'ㅡ'
            else -> null
        }
    }
}
