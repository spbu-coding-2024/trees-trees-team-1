package test.BSTree

import trees.BSTree
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import  kotlin.test.assertEquals

@Tag("BSTree")

class Basic {

    private lateinit var tree: BSTree<Int, String>

    @BeforeEach
    fun setup() {
        tree = BSTree<Int, String>()
    }

    @Tag("find")
    @Test
    @DisplayName("find function")
    fun findElement() {
        tree.insert(4, "4")
        tree.insert(5, "5")
        tree.insert(1, "1")
        val expected = listOf(true, false, true)
        val actual = mutableListOf<Boolean>()
        actual.addLast(tree.find(4))
        actual.addLast(tree.find(10))
        actual.addLast(tree.find(5))
        assertEquals(expected, actual)
    }

    @Tag("peek")
    @Test
    @DisplayName("peek function")
    fun peekValue() {
        tree.insert(4, "4")
        tree.insert(5, "5")
        tree.insert(1, "1")
        val expected = listOf("4", "1", null)
        val actual = mutableListOf<String?>()
        actual.addLast(tree.peek(4))
        actual.addLast(tree.peek(1))
        actual.addLast(tree.peek(10))
        assertEquals(expected, actual)
    }

    @Tag("findParent")
    @Test
    @DisplayName("findParent function")
    fun findParentElements() {
        tree.insert(4, "4")
        tree.insert(5, "5")
        tree.insert(1, "1")
        val expected = listOf(4, null, null)
        val actual = mutableListOf<Int?>()
        actual.addLast(tree.findParent(5))
        actual.addLast(tree.findParent(4))
        actual.addLast(tree.findParent(10))
        assertEquals(expected, actual)
    }

}