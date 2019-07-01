package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/**
 *Created by Pavel on 29.06.2019.
 */
data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int,
    var respect: Int,
    var lastVisit: String?,
    var isOnline: Boolean
) {

    /*constructor(id: String, firstName: String?, lastName: String?) :
            this(id = id, firstName = firstName, lastName = lastName, avatar = "https://avatar.png")*/

    companion object Factory {
        private var lastId = -1

        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)
            //return User(id = "$lastId", firstName = firstName, lastName = lastName)
            return User.Builder()
                .id("$lastId")
                .firstName(firstName)
                .lastName(lastName)
                .avatar("https://avatar.png")
                .rating(0)
                .respect(0)
                .lastVisit(Date().format())
                .isOnline(true)
                .build()
        }
    }

    class Builder {
        private lateinit var id: String
        private var firstName: String? = null
        private var lastName: String? = null
        private var avatar: String? = null
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: String? = null
        private var isOnline: Boolean = false

        fun id(lastId: String): Builder {
            id = lastId
            return this
        }

        fun firstName(firstName: String?): Builder {
            this.firstName = firstName
            return this
        }

        fun lastName(lastName: String?): Builder {
            this.lastName = lastName
            return this
        }

        fun avatar(avatar: String): Builder {
            this.avatar = avatar
            return this
        }

        fun rating(rating: Int): Builder {
            this.rating = rating
            return this
        }

        fun respect(respect: Int): Builder {
            this.respect = respect
            return this
        }

        fun lastVisit(lastVisit: String?): Builder {
            this.lastVisit = lastVisit
            return this
        }

        fun isOnline(isOnline: Boolean): Builder {
            this.isOnline = isOnline
            return this
        }

        fun build(): User {
            return User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
        }
    }
}