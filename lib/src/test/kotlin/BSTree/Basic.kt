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
        val expected = listOf(false, true, true, false, true, false)
        val actual = mutableListOf<Boolean>()

        actual.addLast(tree.find(4)) // Поиск в пустом дереве

        tree.insert(4, "1")
        actual.addLast(tree.find(4))
        tree.insert(4, "2")
        actual.addLast(tree.find(4)) // Поиск элемента с дублирующим ключом

        tree.insert(5, "5")
        tree.insert(1, "1")
        tree.delete(1)

        actual.addLast(tree.find(10)) // Поиск не существующего элемента
        actual.addLast(tree.find(5)) // Поиск существующего элемента
        actual.addLast(tree.find(1)) // Поиск удаленного элемента

        assertEquals(expected, actual)
    }

    @Tag("peek")
    @Test
    @DisplayName("peek function")
    fun peekValue() {
        val expected = listOf(null, "1", "2", "9", null, null)
        val actual = mutableListOf<String?>()

        actual.addLast(tree.peek(4)) // Значение элемента в пустом дереве

        tree.insert(4, "1")
        actual.addLast(tree.peek(4))
        tree.insert(4, "2")
        actual.addLast(tree.peek(4)) // Значение элемента с дублирующим ключом

        tree.insert(5, "5")
        tree.insert(9, "9")

        actual.addLast(tree.peek(9)) // Значение существующего элемента
        tree.delete(9)

        actual.addLast(tree.peek(9)) // Значение удаленного элемента
        actual.addLast(tree.peek(10)) // Значение не существующего элемента
        assertEquals(expected, actual)
    }

    @Tag("findParent")
    @Test
    @DisplayName("findParent function")
    fun findParentElements() {
        val expected = listOf(null, 4, null, null, null)
        val actual = mutableListOf<Int?>()

        actual.addLast(tree.findParent(5)) // Поиск родителя в пустом дереве

        tree.insert(4, "4")
        tree.insert(5, "5")
        tree.insert(1, "1")

        actual.addLast(tree.findParent(5)) // Поиск существующего родителя

        tree.delete(5)

        actual.addLast(tree.findParent(5)) // Поиск родителя у удаленного элемента
        actual.addLast(tree.findParent(4)) // Поиск родителя у корня дерева
        actual.addLast(tree.findParent(10)) // Поиск родителя у не существующего элемента
        assertEquals(expected, actual)
    }

}