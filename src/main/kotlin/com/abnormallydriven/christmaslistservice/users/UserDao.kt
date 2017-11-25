package com.abnormallydriven.christmaslistservice.users

import java.util.concurrent.atomic.AtomicLong

class UserDao {

    private val userIdSequence: AtomicLong = AtomicLong(1)
    private val userStore: MutableMap<Long, User> = mutableMapOf()


    fun createUser(name: String, isNice: Boolean): User {

        val user = User(userIdSequence.getAndIncrement(),
                name,
                isNice)

        userStore.put(user.id, user)

        return user
    }

    fun getUserById(id: Long): User? = userStore[id]

    fun updateUser(updatedUser: User) {
        userStore.put(updatedUser.id, updatedUser)
    }

    fun getAllUsers() : List<User> = userStore.values.toList()

}