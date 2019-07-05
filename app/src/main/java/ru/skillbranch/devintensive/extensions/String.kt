package ru.skillbranch.devintensive.extensions

import java.util.regex.Pattern

/**
 *Created by Pavel on 05.07.2019.
 */

fun String.truncate(count: Int = 16): String {
    return if (this.trimEnd().length >= count) {
        this.substring(0, count).trimEnd() + "..."
    } else {
        this.trimEnd()
    }
}

fun String.stripHtml(): String {
    val replaceAll = Pattern.compile("<.+?>|&#?[a-z0-9]*;").matcher(this).replaceAll("")
    return replaceAll.replace("  ", "").trimStart().trimEnd()
}