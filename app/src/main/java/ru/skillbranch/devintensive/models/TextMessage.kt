package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

/**
 *Created by Pavel on 30.06.2019.
 */
class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date,
    val text: String?
) : BaseMessage(id, from, chat, date, isIncoming) {
    override fun formatMessage(): String = "${from?.firstName}" +
            " ${if (isIncoming) {"получил"} else {"отправил"}} сообщение \"$text\" ${date.humanizeDiff()}"
}