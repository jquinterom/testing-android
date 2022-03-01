package com.example.fundamentosjunit

class Assertions {
    private val user = User("Jhon", 26)
    private var location = "US"

    fun setLocation(location: String) {
        this.location = location
    }

    fun getLuckyNumbers(): Array<Int> {
        return arrayOf(22, 44)
    }

    fun getName(): String {
        return user.name
    }

    fun checkHuman(user: User): Boolean {
        return user.isHuman
    }

    fun checkHuman(user: User? = null): Boolean? {
        if (user == null) return null
        return user.isHuman
    }

    fun isAdult(user: User): Boolean {
        if (!user.isHuman) return true

        return if (location == "US") user.age >= 21
        else
            return user.age >= 18
    }
}