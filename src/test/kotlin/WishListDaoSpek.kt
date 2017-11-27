import com.abnormallydriven.christmaslistservice.users.UserDao
import com.abnormallydriven.christmaslistservice.wishlists.WishListDao
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class WishListDaoSpek : Spek({

    given("A WishListDao") {
        val wishListDao = WishListDao(UserDao())


        on("adding items to an invalid wishlist") {
            var hadException = false

            try {
                wishListDao.addItem(-1, "anything")
            } catch (e: Exception) {
                hadException = true
            }
            it("should throw a runtime exception") {
                assertTrue { hadException }
            }
        }

    }

})