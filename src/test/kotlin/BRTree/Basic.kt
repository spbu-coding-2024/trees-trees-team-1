package test.BRTree

import BRTree
<<<<<<< HEAD
<<<<<<< HEAD
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Tag("BRTree")

=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
import org.junit.jupiter.api.*
import kotlin.test.assertEquals

@Tag("BRTree")
@Tag("basic")
<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
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
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="6 5 7 null null null null "
        assertEquals(expected, tree.printNodes())
=======
        val answer="6 5 7 null null null null "
        assertEquals(tree.printNodes(), answer)
>>>>>>> 91d29c7 (remake project test files structure)
=======
        val answer="6 5 7 null null null null "
        assertEquals(tree.printNodes(), answer)
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Tag("insert")
    @Test
    @DisplayName("checking insert with right rotation")
    fun insertRightRot() {
        tree.insert( 7,1)
        tree.insert( 6,1)
        tree.insert( 5,1)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="6 5 7 null null null null "
        assertEquals(expected, tree.printNodes())
=======
        val answer="6 5 7 null null null null "
        assertEquals(tree.printNodes(), answer)
>>>>>>> 91d29c7 (remake project test files structure)
=======
        val answer="6 5 7 null null null null "
        assertEquals(tree.printNodes(), answer)
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Tag("delete")
    @Test
    @DisplayName("delete non-existing element")
    fun deleteNonExisting() {
        tree.insert(9, 4)
        tree.delete( 8)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected="9 null null "
        assertEquals(expected, tree.printNodes())
    }


=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
        val answer="9 null null "
        assertEquals(tree.printNodes(), answer)
    }

<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
    @Tag("find")
    @Test
    @DisplayName("checking find function")
    fun testFind() {
<<<<<<< HEAD
<<<<<<< HEAD
        var values= listOf(5,7,8,9)
        for (i in values)
            tree.insert(i, 0)
        val actual= mutableListOf<Boolean>()
        val expected = listOf(true, true, true, true, false, false)
        for (i in values)
            actual.addLast(tree.find(i))
        actual.addLast(tree.find(10))
        actual.addLast(tree.find(11))
        assertEquals(expected, actual)
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
        tree.insert(5,0)
        tree.insert(7,0)
        tree.insert(8,0)
        tree.insert(9,0)
        val answers= mutableListOf<Boolean>()
        val real = listOf(true, true, true, true, false, false)
        answers.addLast(tree.find( 5))
        answers.addLast(tree.find( 7))
        answers.addLast(tree.find(9))
        answers.addLast(tree.find(8))
        answers.addLast(tree.find(10))
        answers.addLast(tree.find(11))
        assertEquals(answers, real)
<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("checking findParent function")
    fun checkParent() {
<<<<<<< HEAD
<<<<<<< HEAD
        val values= listOf(7,10,5,8,11)
        for (i in values)
            tree.insert(i, 0)
        val expected= listOf(null, 7, 7, 10, 10, null)
        val actual= mutableListOf<Int?>()
        for (i in values)
            actual.addLast(tree.findParent(i))
        actual.addLast(tree.findParent(0))
        assertEquals(expected, actual)
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
        tree.insert(7,5)
        tree.insert(10,1)
        tree.insert(5,1)
        tree.insert(8, 1)
        tree.insert(11,1)
        val result= listOf(null, 7, 7, 10, 10, null)
        val answer= mutableListOf<Int?>()
        answer.addLast(tree.findParent(7))
        answer.addLast(tree.findParent(10))
        answer.addLast(tree.findParent(5))
        answer.addLast(tree.findParent(8))
        answer.addLast(tree.findParent(11))
        answer.addLast(tree.findParent(2))
        assertEquals(result, answer)
<<<<<<< HEAD
>>>>>>> 91d29c7 (remake project test files structure)
=======
>>>>>>> 91d29c7 (remake project test files structure)
    }

    @Test
    @DisplayName("checking peek function")
    fun peek() {
        tree.insert(7,5)
        tree.insert(10,1)
<<<<<<< HEAD
<<<<<<< HEAD
        val expected= listOf(5, 1, null)
        val actual= mutableListOf<Int?>()
        actual.addAll(listOf( tree.peek(7), tree.peek(10), tree.peek(2)))
        assertEquals(expected, actual)
    }
}
=======
=======
>>>>>>> 91d29c7 (remake project test files structure)
        val result= listOf(5, 1, null)
        val answer= mutableListOf<Int?>()
        answer.addAll(listOf( tree.peek(7), tree.peek(10), tree.peek(2)))
        assertEquals(result, answer)
    }


<<<<<<< HEAD
}
>>>>>>> 91d29c7 (remake project test files structure)
=======
}
>>>>>>> 91d29c7 (remake project test files structure)
