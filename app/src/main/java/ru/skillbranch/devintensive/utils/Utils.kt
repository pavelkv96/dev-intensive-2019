package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.extensions.TimeUnits

/**
 *Created by Pavel on 29.06.2019.
 */
object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {

        if (!fullName.isNullOrEmpty() && fullName.trim().isNotEmpty()) {
            val split = fullName.split(" ".toRegex(), 2)

            val firstName = split[0].trim()
            var lastName = split.getOrNull(1)
            if (lastName.isNullOrEmpty()) {
                lastName = null
            }else{
                lastName = lastName.trim()
            }

            return firstName to lastName
        }

        return null to null
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName == null && lastName == null) {
            return null
        }

        var firstInitial = ""
        if (firstName != null && firstName.trim().isNotEmpty()) {
            firstInitial = firstName.trim()[0].toString().toUpperCase()
        }

        var lastInitial = ""
        if (lastName != null && lastName.trim().isNotEmpty()) {
            lastInitial = lastName.trim()[0].toString().toUpperCase()
        }

        if (firstInitial.isEmpty() && lastInitial.isEmpty()) {
            return null
        }

        return firstInitial + lastInitial
    }

    fun transliteration(payload: String, divider: String = " "): String {
        if (payload.trim().isEmpty()) {
            return payload
        }

        val list = payload.replace(" ", divider).toCharArray()
        var transliteration = ""

        for (a in list) {
            val isUpper = a.isUpperCase()
            var transliteration1: String = when (a.toLowerCase()) {
                'а' -> "a"
                'б' -> "b"
                'в' -> "v"
                'г' -> "g"
                'д' -> "d"
                'е' -> "e"
                'ё' -> "e"
                'ж' -> "zh"
                'з' -> "z"
                'и' -> "i"
                'й' -> "i"
                'к' -> "k"
                'л' -> "l"
                'м' -> "m"
                'н' -> "n"
                'о' -> "o"
                'п' -> "p"
                'р' -> "r"
                'с' -> "s"
                'т' -> "t"
                'у' -> "u"
                'ф' -> "f"
                'х' -> "h"
                'ц' -> "c"
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh'"
                'ъ' -> ""
                'ы' -> "i"
                'ь' -> ""
                'э' -> "e"
                'ю' -> "yu"
                'я' -> "ya"
                else -> "$a"
            }

            if (transliteration1.isNotEmpty() && isUpper) {
                val first = transliteration1.first()
                transliteration1 = transliteration1.replaceFirst(first, first.toUpperCase())
            }

            transliteration += transliteration1
        }

        return transliteration
    }

    fun getPluralForm(pluralForms: TimeUnits, count: Int): String {
        val pattern = when (pluralForms) {
            TimeUnits.SECOND -> "секунду;секунды;секунд"
            TimeUnits.MINUTE -> "минута;минуты;минут"
            TimeUnits.HOUR -> "час;часа;часов"
            TimeUnits.DAY -> "день;дня;дней"
        }

        val forms = pattern.split(";")
        when (count % 10) {
            1 -> if (count % 100 != 11) return forms[0]
            2, 3, 4 -> if (count % 100 !in 12..14) return forms[1]
        }

        return forms[2]
    }
}