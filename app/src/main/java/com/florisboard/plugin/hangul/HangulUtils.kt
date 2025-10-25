package com.florisboard.plugin.hangul

/**
 * 한글 유니코드 분해/조합 유틸리티
 */
object HangulUtils {
    private val CHOSUNG = charArrayOf(
        'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 
        'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    )
    
    private val JUNGSUNG = charArrayOf(
        'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 
        'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'
    )
    
    private val JONGSUNG = charArrayOf(
        '\u0000', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 
        'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 
        'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    )
    
    private val DOUBLE_JONGSUNG = mapOf(
        'ㄳ' to listOf('ㄱ', 'ㅅ'),
        'ㄵ' to listOf('ㄴ', 'ㅈ'),
        'ㄶ' to listOf('ㄴ', 'ㅎ'),
        'ㄺ' to listOf('ㄹ', 'ㄱ'),
        'ㄻ' to listOf('ㄹ', 'ㅁ'),
        'ㄼ' to listOf('ㄹ', 'ㅂ'),
        'ㄽ' to listOf('ㄹ', 'ㅅ'),
        'ㄾ' to listOf('ㄹ', 'ㅌ'),
        'ㄿ' to listOf('ㄹ', 'ㅍ'),
        'ㅀ' to listOf('ㄹ', 'ㅎ'),
        'ㅄ' to listOf('ㅂ', 'ㅅ')
    )
    
    private const val HANGUL_BASE = 0xAC00
    private const val HANGUL_END = 0xD7A3
    private const val JUNGSUNG_COUNT = 21
    private const val JONGSUNG_COUNT = 28
    
    fun isHangulSyllable(char: Char): Boolean {
        return char.code in HANGUL_BASE..HANGUL_END
    }
    
    fun decomposeHangul(char: Char): Triple<Char, Char, Char?>? {
        if (!isHangulSyllable(char)) return null
        
        val code = char.code - HANGUL_BASE
        val chosungIndex = code / (JUNGSUNG_COUNT * JONGSUNG_COUNT)
        val jungsungIndex = (code % (JUNGSUNG_COUNT * JONGSUNG_COUNT)) / JONGSUNG_COUNT
        val jongsungIndex = code % JONGSUNG_COUNT
        
        return Triple(
            CHOSUNG[chosungIndex],
            JUNGSUNG[jungsungIndex],
            if (jongsungIndex == 0) null else JONGSUNG[jongsungIndex]
        )
    }
    
    fun composeHangul(chosung: Char, jungsung: Char, jongsung: Char? = null): Char {
        val chosungIndex = CHOSUNG.indexOf(chosung)
        val jungsungIndex = JUNGSUNG.indexOf(jungsung)
        val jongsungIndex = if (jongsung == null) 0 else JONGSUNG.indexOf(jongsung)
        
        val code = HANGUL_BASE + 
                   (chosungIndex * JUNGSUNG_COUNT * JONGSUNG_COUNT) +
                   (jungsungIndex * JONGSUNG_COUNT) +
                   jongsungIndex
        
        return code.toChar()
    }
    
    fun splitDoubleJongsung(jongsung: Char): List<Char>? {
        return DOUBLE_JONGSUNG[jongsung]
    }
}
