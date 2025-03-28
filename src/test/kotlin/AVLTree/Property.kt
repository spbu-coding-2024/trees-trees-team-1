package test.AVLTree

import AVLTree

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.DisplayName

import kotlin.random.Random
import kotlin.math.abs

@Tag("AVLTree")
@Tag("PropertyBased")

class PropertyBasedTests {

	private fun checkBSTProperty(node: AVLNode<Int, Int>?, min: Int = Int.MIN_VALUE, 
	max: Int = Int.MAX_VALUE ): Boolean {
		if (node == null) return true
		if (node.key <= min || node.key >= max) return false

		return checkBSTProperty(node.left, min, node.key) && checkBSTProperty(node.right, node.key, max)
	}


	private fun checkBalanceProperty(node: AVLNode<Int, Int>?) : Boolean {
		if (node == null) return true
		val leftHeight = node.left?.height ?: 0
		val rightHeight = node.right?.height ?: 0
		
		return (abs(leftHeight - rightHeight) <= 1 && checkBalanceProperty(node.left)
		 && checkBalanceProperty(node.right))
	}


	private fun checkCountNodes(node: AVLNode<Int, Int>?): Int {
		if (node == null) return 0
		val countLeft = checkCountNodes(node.left)
		val countRight = checkCountNodes(node.right)
		return 1 + countLeft + countRight
	}
}