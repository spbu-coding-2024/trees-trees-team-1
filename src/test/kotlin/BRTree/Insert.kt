package test.BRTree
import BRTree
import org.junit.jupiter.api.*
import kotlin.test.assertEquals



@Tag("BRTree")
@Tag("insert")
class Insert {
    private var tree=BRTree<Int, Int>()

    @Test
    @DisplayName("massive insertion")
    fun checkInsertion() {
        for (i in 1..20)
            tree.insert(i, 0)
        val res="8 4 12 2 6 10 16 1 3 5 7 9 11 14 18 null null null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(tree.printNodes(), res)
    }


}
