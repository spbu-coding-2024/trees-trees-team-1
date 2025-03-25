package test.BRTree
import BRTree
<<<<<<< HEAD
<<<<<<< HEAD
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
import org.junit.jupiter.api.*
import kotlin.test.assertEquals



<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
@Tag("BRTree")
@Tag("insert")
class Insert {
    private var tree=BRTree<Int, Int>()

    @Test
    @DisplayName("massive insertion")
    fun checkInsertion() {
        for (i in 1..20)
            tree.insert(i, 0)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="8 4 12 2 6 10 16 1 3 5 7 9 11 14 18 null null null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("red insertion with right black uncle")
    fun blackInsertion1() {
        val values= listOf(5,1,4)
        for (i in values)
            tree.insert(i,0)
        val expected="4 1 5 null null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("red insertion with left black uncle")
    fun blackInsertion2() {
        val values= listOf(5,8,7)
        for (i in values)
            tree.insert(i,0)
        val expected="7 5 8 null null null null "
        assertEquals(expected, tree.printNodes())
    }


=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
        val res="8 4 12 2 6 10 16 1 3 5 7 9 11 14 18 null null null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(tree.printNodes(), res)
    }

<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)

}
