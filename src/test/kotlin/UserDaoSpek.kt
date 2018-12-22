import com.abnormallydriven.christmaslistservice.users.UserDao
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

class UserDaoSpek : Spek( {

    describe("A UserDao"){
        val objectUnderTest = UserDao()

        it("Should create users with ids"){

            val createdUser = objectUnderTest.createUser("Test", true)
            assertEquals(1, createdUser.id)
            assertEquals("Test", createdUser.name)
            assertEquals(true, createdUser.isNice)

        }

        it("Should allow you to get a user by id"){
            val userById = objectUnderTest.getUserById(1)
            assertNotNull(userById)
            assertEquals(1, userById!!.id)
        }

        it("Should allow you to update a user's name and isNice status"){
            val userById = objectUnderTest.getUserById(1)
            val copiedUser = userById?.copy(userById.id, "Changed", !userById.isNice)

            objectUnderTest.updateUser(copiedUser!!)
            val updatedUser = objectUnderTest.getUserById(copiedUser.id)

            assertEquals(1, updatedUser!!.id)
            assertEquals("Changed", updatedUser.name)
            assertEquals(!userById.isNice, updatedUser.isNice)

        }

        it("Should return a list of all users"){
            objectUnderTest.createUser("User 1", true)
            objectUnderTest.createUser("User 2", false)
            objectUnderTest.createUser("User 3", true)

            val allUsers = objectUnderTest.getAllUsers()

            assertEquals(4, allUsers.size)
        }

    }


})