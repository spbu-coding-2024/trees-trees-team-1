package test.BSTree

import trees.BSTree
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Tag("BSTree")
@Tag("delete")

class Deletion {
    private var tree = BSTree<Int, String>()

    @Test
    @DisplayName("delete elements from an empty tree")
    fun deleteEmptyTree() {
        tree.delete(9)
        val expected = "null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("delete main root of a tree with 2 children")
    fun deleteMainRoot2() {
        tree.insert(5,"5")
        tree.insert(9, "9")
        tree.insert(2, "2")
        tree.delete(5)
        val expected = "9 2 null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("delete main root of a tree with 1 child")
    fun deleteMainRoot1() {
        tree.insert(5,"5")
        tree.insert(9, "9")
        tree.delete(5)
        val expected = "9 null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("delete root in a balanced tree")
    fun deleteBalanceTree() {
        tree.insert(5,"5")
        tree.insert(9, "9")
        tree.insert(3,"3")
        tree.insert(2,"2")
        tree.insert(4, "4")
        tree.insert(8, "8")
        tree.insert(10, "10")
        tree.delete(9)
        val expected = "5 3 10 2 4 8 null null null null null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("delete root in a unbalanced tree")
    fun deleteUnbalanceTree() {
        tree.insert(5,"5")
        tree.insert(9, "9")
        tree.insert(10,"10")
        tree.insert(22,"22")
        tree.delete(9)
        val expected = "5 null 10 null 22 null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("delete non-existing elements")
    fun deleteNull() {
        tree.insert(5,"5")
        tree.insert(9, "9")
        tree.delete(8)
        val expected = "5 null 9 null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("delete all tree elements")
    fun deleteAll() {
        val key = listOf(1, 2, 3)
        for (i in key)
            tree.insert(i, "1")
        for (i in key)
            tree.delete(i)
        assertEquals("null ", tree.printNodes())
    }

    @Test
    @DisplayName("delete node with 2 children")
    fun deleteNodeWithChildren() {
        tree.insert(5,"5")
        tree.insert(9, "9")
        tree.insert(7, "7")
        tree.insert(11, "11")
        tree.insert(10, "10")
        tree.delete(9)
        val expected = "5 null 10 7 11 null null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("delete node with 1 left child")
    fun deleteNodeWithLeft() {
        tree.insert(5,"5")
        tree.insert(9, "9")
        tree.insert(7, "7")
        tree.delete(9)
        val expected = "5 null 7 null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("delete node with 1 right child")
    fun deleteNodeWithRight() {
        tree.insert(5,"5")
        tree.insert(9, "9")
        tree.insert(11, "11")
        tree.delete(9)
        val expected = "5 null 11 null null "
        assertEquals(expected, tree.printNodes())
    }

}