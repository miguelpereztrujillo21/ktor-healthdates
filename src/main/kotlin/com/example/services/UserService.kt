package com.example.services

import com.example.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.Connection

class UserService (private val connection: Connection){
    companion object{
        private const val SELECT_ALL_USERS = "SELECT * from users"
    }

    suspend fun getAllUsers() : ArrayList<User> = withContext(Dispatchers.IO) {
        val statement = connection.prepareStatement(SELECT_ALL_USERS)
        val resultSet = statement.executeQuery()

        val userList = ArrayList<User>()

        while (resultSet.next()) {
            val name = resultSet.getString("user_name")
            val userId= resultSet.getInt("user_id")
            val email = resultSet.getString("email")
            val address = resultSet.getString("address")

            val user = User(userId, name, email, address)
            userList.add(user)
        }

        if (userList.isEmpty()) {
            throw Exception("No results")
        }

        return@withContext userList
    }
}