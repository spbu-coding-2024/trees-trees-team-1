package test.BRTree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import trees.BRTree

@Tag("BRTree")
@Tag("basic")
class Basic {

    private lateinit var tree: BRTree<Int, Int>

    @Test
    @BeforeEach
    @DisplayName("checking black-red tree initialization")
    fun setup() {
        tree=BRTree()
    }

    @Tag("insert")
    @Test
    @DisplayName("checking insert with left rotation")
    fun insertLeftRot() {
        tree.insert( 5,1)
        tree.insert( 6,1)
        tree.insert( 7,1)
        val expected="6 5 7 null null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Tag("insert")
    @Test
    @DisplayName("checking insert with right rotation")
    fun insertRightRot() {
        tree.insert( 7,1)
        tree.insert( 6,1)
        tree.insert( 5,1)
        val expected="6 5 7 null null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Tag("delete")
    @Test
    @DisplayName("delete non-existing element")
    fun deleteNonExisting() {
        tree.insert(9, 4)
        tree.delete( 8)
        val expected="9 null null "
        assertEquals(expected, tree.printNodes())
    }


    @Tag("find")
    @Test
    @DisplayName("checking find function")
    fun testFind() {
        var values= listOf(5,7,8,9)
        for (i in values)
            tree.insert(i, 0)
        val actual= mutableListOf<Boolean>()
        val expected = listOf(true, true, true, true, false, false)
        for (i in values)
            actual.add(tree.find(i))
        actual.add(tree.find(10))
        actual.add(tree.find(11))
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("checking findParent function")
    fun checkParent() {
        val values= listOf(7,10,5,8,11)
        for (i in values)
            tree.insert(i, 0)
        val expected= listOf(null, 7, 7, 10, 10, null)
        val actual= mutableListOf<Int?>()
        for (i in values)
            actual.add(tree.findParent(i))
        actual.add(tree.findParent(0))
        assertEquals(expected, actual)
    }

    @Test
    @DisplayName("checking peek function")
    fun peek() {
        tree.insert(7,5)
        tree.insert(10,1)
        val expected= listOf(5, 1, null)
        val actual= mutableListOf<Int?>()
        actual.addAll(listOf( tree.peek(7), tree.peek(10), tree.peek(2)))
        assertEquals(expected, actual)
    }
}
