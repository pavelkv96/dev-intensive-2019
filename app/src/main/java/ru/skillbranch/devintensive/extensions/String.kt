package ru.skillbranch.devintensive.extensions

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

fun String.stripHtml() = replace("<[^>]*>".toRegex(), "").replace("\\s+".toRegex(), " ")