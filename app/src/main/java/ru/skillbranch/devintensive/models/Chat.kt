package ru.skillbranch.devintensive.models

/**
 *Created by Pavel on 30.06.2019.
 */

class Chat(
    val id: String,
    val membars: MutableList<User> = mutableListOf(),
    val messages: MutableList<User> = mutableListOf()
)