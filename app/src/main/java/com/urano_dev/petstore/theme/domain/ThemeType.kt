package com.urano_dev.petstore.theme.domain

enum class ThemeType {
    DARK,
    LIGHT,
    DEFAULT,
}

fun ThemeType.getNextTheme():ThemeType{
    return when(this){
        ThemeType.DARK->ThemeType.LIGHT
        ThemeType.LIGHT->ThemeType.DARK
        ThemeType.DEFAULT->ThemeType.LIGHT
//        else -> {
//            return ThemeType.DEFAULT
//        }
    }
}