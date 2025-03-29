package test.BRTree

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import trees.BRTree

import kotlin.test.assertEquals

@Tag("BRTree")
@Tag("delete")

class Deletion {
    private var tree = BRTree<Int, Int>()

    @Test
    @DisplayName("delete all tree elements")
    fun deleteAll() {
        val values= listOf(5,6,7)
        for (i in values)
            tree.insert(i, 0)
        for (i in values)
            tree.delete(i)
        assertEquals("null ", tree.printNodes())
    }

    @Test
    @DisplayName("left node deletion with black brother and nephews")
    fun deletion1() {
        val values= listOf(5,4,7,6)
        for (i in values)
            tree.insert(i, 0)
        tree.delete( 4)
        val expected="6 5 7 null null null null "
        assertEquals(expected, tree.printNodes() )
    }

    @Test
    @DisplayName("right node deletion with black brother and nephews")
    fun deletion2() {
        var values= listOf(6,7,4,5)
        for (i in values)
            tree.insert(i, 0)
        tree.delete( 7)
        val expected="5 4 6 null null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("right node deletion with black brother and left red nephew")
    fun deletion3() {
        var values= listOf(10,11,7,8,6,5)
        for (i in values)
            tree.insert(i, 0)
        tree.delete(8)
        val expected="10 6 11 5 7 null null null null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("root deletion")
    fun rootDeletion() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete( 8)
        val expected="7 4 12 2 6 10 16 1 3 5 null 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("red list deletion1")
    fun redListDeletion() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete(5)
        val expected="8 4 12 2 6 10 16 1 3 null 7 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("black list deletion1")
    fun blackDeletion1() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete(11)
        val expected="8 4 16 2 6 12 18 1 3 5 7 10 14 17 19 null null null null null null null null 9 null 13 15 null null null 20 null null null null null null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("black node deletion2")
    fun blackDeletion2() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete(16)
        val expected="8 4 12 2 6 10 15 1 3 5 7 9 11 14 18 null null null null null null null null null null null null 13 null 17 19 null null null null null 20 null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("black node deletion4")
    fun blackDeletion4() {
        var values= listOf(10,5,20,15,25,23,17,28,12)
        for (i in values)
            tree.insert(i, 0)
        tree.delete(5)
        val expected = "20 15 25 10 17 23 28 null 12 null null null null null null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("black node deletion3")
    fun blackDeletion3() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete(6)
        val expected="8 4 12 2 5 10 16 1 3 null 7 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("red node deletion2")
    fun redDeletion() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete(12)
        val expected="8 4 16 2 6 11 18 1 3 5 7 10 14 17 19 null null null null null null null null 9 null 13 15 null null null 20 null null null null null null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("right black node deletion with red brother")
    fun blackDeletion5() {
        var values= listOf(7,5,10,2,8,6,13,12,1,4,3)
        for (i in values)
            tree.insert(i, 0)
        tree.delete(6)
        val expected="7 2 10 1 4 8 13 null null 3 5 null null 12 null null null null null null null "
        assertEquals(expected, tree.printNodes())
    }

    @Test
    @DisplayName("left black node deletion with red brother")
    fun blackDeletion6() {
        var values= listOf(7,5,10,2,8,6,13,1,12,15,14)
        for (i in values)
            tree.insert(i, 0)
        tree.delete(8)
        val expected="7 5 13 2 6 10 15 1 null null null null 12 14 null null null null null null null "
        assertEquals(expected, tree.printNodes() )
    }

    @Test
    @DisplayName("red deletion with red substitution")
    fun redDeletion4() {
        var values= listOf(20,10,30,5,15,25,8)
        for (i in values)
            tree.insert(i, 0)
        tree.delete(10)
        val expected="20 8 30 5 15 25 null null null null null null null "
        assertEquals(expected, tree.printNodes() )
    }

    @Test
    @DisplayName("black deletion with red substitution")
    fun redDeletion5() {
        var values= listOf(20,10,30,5,15,25,8)
        for (i in values)
            tree.insert(i, 0)
        tree.delete(30)
        val expected="20 10 25 5 15 null null null 8 null null null null "
        assertEquals(expected, tree.printNodes() )
    }
}
