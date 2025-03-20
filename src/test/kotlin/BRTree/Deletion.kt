package test.BRTree

import BRTree
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
@Tag("BRTree")
@Tag("delete")

class Deletion {
    private var tree = BRTree<Int, Int>()

<<<<<<< HEAD
<<<<<<< HEAD
    @Test
    @DisplayName("delete all tree elements")
    fun deleteAll() {
<<<<<<< HEAD
        val values= listOf(5,6,7)
        for (i in values)
            tree.insert(i, 0)
        for (i in values)
            tree.delete(i)
=======
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)

    fun setup() {

    }

    @Test
    @DisplayName("delete all tree elements")
    fun deleteAll() {
<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
        tree.insert( 5,1)
        tree.insert( 6,1)
        tree.insert(7,1)
        tree.delete( 5)
        tree.delete( 6)
        tree.delete( 7)
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> ed2657a (final tests)
        assertEquals("null ", tree.printNodes())
=======
        assertEquals(tree.printNodes(), "null ")
>>>>>>> 91d29c7 (remake project test files structure)
=======
        assertEquals(tree.printNodes(), "null ")
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("left node deletion with black brother and nephews")
    fun Deletion1() {
<<<<<<< HEAD
<<<<<<< HEAD
        val values= listOf(5,4,7,6)
        for (i in values)
            tree.insert(i, 0)
        tree.delete( 4)
        val expected="6 5 7 null null null null "
        assertEquals(expected, tree.printNodes() )
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
        tree.insert(5,0)
        tree.insert( 4,0)
        tree.insert(7,0)
        tree.insert( 6,0)
        tree.delete( 4)
        val res="6 5 7 null null null null "
        assertEquals(res, tree.printNodes() )
<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("right node deletion with black brother and nephews")
    fun Deletion2() {
<<<<<<< HEAD
<<<<<<< HEAD
        var values= listOf(6,7,4,5)
        for (i in values)
            tree.insert(i, 0)
        tree.delete( 7)
        val expected="5 4 6 null null null null "
        assertEquals(expected, tree.printNodes())
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
        tree.insert( 6,0)
        tree.insert( 7,0)
        tree.insert( 4,0)
        tree.insert(5,0)
        tree.delete( 7)
        val res="5 4 6 null null null null "
        assertEquals(res, tree.printNodes())
<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("right node deletion with black brother and left red nephew")
    fun Deletion3() {
<<<<<<< HEAD
<<<<<<< HEAD
        var values= listOf(10,11,7,8,6,5)
        for (i in values)
            tree.insert(i, 0)
        tree.delete(8)
        val expected="10 6 11 5 7 null null null null null null "
        assertEquals(expected, tree.printNodes())
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
        tree.insert( 10,0)
        tree.insert(11,0)
        tree.insert(7,0)
        tree.insert(8,0)
        tree.insert(6,0)
        tree.insert(5,0)
        tree.delete(8)
        val res="10 6 11 5 7 null null null null null null "
        assertEquals(res, tree.printNodes())
<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("root deletion")
    fun rootDeletion() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete( 8)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="7 4 12 2 6 10 16 1 3 5 null 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(expected, tree.printNodes())
=======
        val res="7 4 12 2 6 10 16 1 3 5 null 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
=======
        val res="7 4 12 2 6 10 16 1 3 5 null 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("red list deletion")
    fun redListDeletion() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete(5)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="8 4 12 2 6 10 16 1 3 null 7 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(expected, tree.printNodes())
=======
        val res="8 4 12 2 6 10 16 1 3 null 7 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
=======
        val res="8 4 12 2 6 10 16 1 3 null 7 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("black list deletion")
    fun blackListDeletion() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete(11)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="8 4 16 2 6 12 18 1 3 5 7 10 14 17 19 null null null null null null null null 9 null 13 15 null null null 20 null null null null null null null null "
        assertEquals(expected, tree.printNodes())
=======
        val res="8 4 16 2 6 12 18 1 3 5 7 10 14 17 19 null null null null null null null null 9 null 13 15 null null null 20 null null null null null null null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
=======
        val res="8 4 16 2 6 12 18 1 3 5 7 10 14 17 19 null null null null null null null null 9 null 13 15 null null null 20 null null null null null null null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("black node deletion1")
    fun BlackDeletion1() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete(16)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="8 4 12 2 6 10 15 1 3 5 7 9 11 14 18 null null null null null null null null null null null null 13 null 17 19 null null null null null 20 null null "
        assertEquals(expected, tree.printNodes())
=======
        val res="8 4 12 2 6 10 15 1 3 5 7 9 11 14 18 null null null null null null null null null null null null 13 null 17 19 null null null null null 20 null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
=======
        val res="8 4 12 2 6 10 15 1 3 5 7 9 11 14 18 null null null null null null null null null null null null 13 null 17 19 null null null null null 20 null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("black node deletion2")
    fun BlackDeletion2() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete(6)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="8 4 12 2 5 10 16 1 3 null 7 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(expected, tree.printNodes())
=======
        val res="8 4 12 2 5 10 16 1 3 null 7 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
=======
        val res="8 4 12 2 5 10 16 1 3 null 7 9 11 14 18 null null null null null null null null null null 13 15 17 19 null null null null null null null 20 null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("red node deletion")
    fun RedDeletion() {
        for (i in 1..20)
            tree.insert(i, 0)
        tree.delete(12)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="8 4 16 2 6 11 18 1 3 5 7 10 14 17 19 null null null null null null null null 9 null 13 15 null null null 20 null null null null null null null null "
        assertEquals(expected, tree.printNodes())
=======
        val res="8 4 16 2 6 11 18 1 3 5 7 10 14 17 19 null null null null null null null null 9 null 13 15 null null null 20 null null null null null null null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
=======
        val res="8 4 16 2 6 11 18 1 3 5 7 10 14 17 19 null null null null null null null null 9 null 13 15 null null null 20 null null null null null null null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("right black node deletion with red brother")
    fun BlackDeletion3() {
        var values= listOf(7,5,10,2,8,6,13,12,1,4,3)
        for (i in values)
            tree.insert(i, 0)
        tree.delete(6)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="7 2 10 1 4 8 13 null null 3 5 null null 12 null null null null null null null "
        assertEquals(expected, tree.printNodes())
=======
        val res="7 2 10 1 4 8 13 null null 3 5 null null 12 null null null null null null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
=======
        val res="7 2 10 1 4 8 13 null null 3 5 null null 12 null null null null null null null "
        assertEquals(tree.printNodes(), res)
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("left black node deletion with red brother")
    fun BlackDeletion4() {
        var values= listOf(7,5,10,2,8,6,13,1,12,15,14)
        for (i in values)
            tree.insert(i, 0)
        tree.delete(8)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="7 5 13 2 6 10 15 1 null null null null 12 14 null null null null null null null "
        assertEquals(expected, tree.printNodes() )
    }

    @Test
    @DisplayName("red deletion with red substitution")
    fun RedDeletion4() {
        var values= listOf(20,10,30,5,15,25,8)
        for (i in values)
            tree.insert(i, 0)
        tree.delete(10)
        val expected="20 8 30 5 15 25 null null null null null null null "
        assertEquals(expected, tree.printNodes() )
    }

    @Test
    @DisplayName("black deletion with red substitution")
    fun RedDeletion5() {
        var values= listOf(20,10,30,5,15,25,8)
        for (i in values)
            tree.insert(i, 0)
        tree.delete(30)
        val expected="20 10 25 5 15 null null null 8 null null null null "
        assertEquals(expected, tree.printNodes() )
    }
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
        val res="7 5 13 2 6 10 15 1 null null null null 12 14 null null null null null null null "
        assertEquals(res, tree.printNodes() )
    }

<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
}
