package test.BSTree

import trees.BSTree
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Tag("BSTree")
@Tag("insert")

class Insert {
    private var tree = BSTree<Int, String>()

    @Test
    @DisplayName("Massive insertion")
    fun insertMassive() {
        for (i in 1..5)
            tree.insert(i, "1")
        val expected = "1 null 2 null 3 null 4 null 5 null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("insert the first element")
    fun insertNew() {
        tree.insert(4, "First")
        val expected = "4 null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("insert left and right elements")
    fun insertLeftRight() {
        tree.insert(4, "4")
        tree.insert(9, "9")
        tree.insert(1, "1")
        val expected = "4 1 9 null null null null "
        assertEquals(expected, tree.printNodes())

    }

    @Test
    @DisplayName("insert element with a key already in the tree")
    fun insertDoubleKey() {
        tree.insert(4, "4")
        tree.insert(9, "9")
        tree.insert(9, "1")
        val expected = "1"
        assertEquals(expected, tree.peek(9))

    }
}


