package test.BRTree

import BRTree
import org.junit.jupiter.api.*
import kotlin.test.assertEquals

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
        val answer="6 5 7 null null null null "
        assertEquals(tree.printNodes(), answer)
    }

    @Tag("insert")
    @Test
    @DisplayName("checking insert with right rotation")
    fun insertRightRot() {
        tree.insert( 7,1)
        tree.insert( 6,1)
        tree.insert( 5,1)
        val answer="6 5 7 null null null null "
        assertEquals(tree.printNodes(), answer)
    }

    @Tag("delete")
    @Test
    @DisplayName("delete non-existing element")
    fun deleteNonExisting() {
        tree.insert(9, 4)
        tree.delete( 8)
        val answer="9 null null "
        assertEquals(tree.printNodes(), answer)
    }

    @Tag("find")
    @Test
    @DisplayName("checking find function")
    fun testFind() {
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
    }

    @Test
    @DisplayName("checking findParent function")
    fun checkParent() {
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
    }

    @Test
    @DisplayName("checking peek function")
    fun peek() {
        tree.insert(7,5)
        tree.insert(10,1)
        val result= listOf(5, 1, null)
        val answer= mutableListOf<Int?>()
        answer.addAll(listOf( tree.peek(7), tree.peek(10), tree.peek(2)))
        assertEquals(result, answer)
    }


}