package test.AVLTree

import AVLTree

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Tag
import kotlin.test.assertEquals

@Tag("AVLTree")
@Tag("insert")

class Insert {
	private lateinit var tree: AVLTree<Int, Int>

	@BeforeEach
	fun setup() {
		tree = AVLTree()
	}

	@Test
	@DisplayName("Checking insert into empty tree should be root")
	fun insertIntoEmptyTree() {
		tree.insert(10, 1)
		val expected = "10 null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Checking insert with left rotation")
	fun insertWithLeftRotation() {
		tree.insert(10, 1)
		tree.insert(20, 2)
		tree.insert(30, 3)
		val expected = "20 10 30 null null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Checking insert with right rotation")
	fun insertWithRightRotation() {
		tree.insert(30, 3)
		tree.insert(20, 2)
		tree.insert(10, 1)
		val expected = "20 10 30 null null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Checking insert with left-right rotation")
	fun insertWithLeftRightRotation() {
		tree.insert(30, 3)
		tree.insert(10, 1)
		tree.insert(20, 2)
		val expected = "20 10 30 null null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Checking insert with right-left rotation")
	fun insertWithRightLeftRotation() {
		tree.insert(10, 1)
		tree.insert(30, 3)
		tree.insert(20, 2)
		val expected = "20 10 30 null null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Insert duplicate keys should be update value")
	fun insertDuplicateKeys() {
		tree.insert(10, 100)
		tree.insert(10, 200)
		assertEquals(200, tree.peek(10))
		val expected = "10 null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("checking insert consecutive numbers")
	fun insertSortedSequence() {
		val values = listOf(1, 2, 3, 4, 5, 6)
		for (i in values) {
			tree.insert(i, i)
		}
		val expected = "4 2 5 1 3 null 6 null null null null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Checking insert negative keys")
	fun insertNegativeKeys() {
		tree.insert(-5, 1)
		tree.insert(-3, 1)
		tree.insert(-10, 1)
		val expected = "-5 -10 -3 null null null null "
		assertEquals(expected, tree.printNodes())
	}

	@Test
	@DisplayName("Checking insert zero, positive, negative keys")
	fun insertZeroPositiveNegative() {
		tree.insert(0, 1)
		tree.insert(1, 1)
		tree.insert(-1, 1)
		val expected = "0 -1 1 null null null null "
		assertEquals(expected, tree.printNodes())
	}
}