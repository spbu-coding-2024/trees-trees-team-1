
import nodes.BRNode
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import trees.BRTree
import kotlin.test.assertEquals

class Iterate {
    private lateinit var tree: BRTree<Int, Int>

    @Test
    @DisplayName("empty tree check")
    fun check1() {
        tree=BRTree()
        assertEquals("null ", tree.printNodes())
    }

    @Test
    @DisplayName("has several nodes 1")
    fun check2() {
        tree=BRTree()
        tree.root= BRNode(5,4, null)
        tree.root?.left= BRNode(2,0, tree.root)
        tree.root?.right=BRNode(8,0, tree.root)
        assertEquals("5 2 8 null null null null ", tree.printNodes())
    }


    @Test
    @DisplayName("has several nodes 2")
    fun check3() {
        tree=BRTree()
        tree.root= BRNode(5,4, null)
        tree.root?.left= BRNode(2,0, tree.root)
        tree.root?.right=BRNode(8,0, tree.root)
        tree.root?.left?.left= BRNode(-1, 0, tree.root?.left)
        tree.root?.right?.right=BRNode(12, 0, tree.root?.right)
        assertEquals("5 2 8 -1 null null 12 null null null null ", tree.printNodes())
    }

    @Test
    @DisplayName("has several nodes 3")
    fun check4() {
        tree=BRTree()
        tree.root= BRNode(5,4, null)
        tree.root?.left= BRNode(2,0, tree.root)
        tree.root?.right=BRNode(8,0, tree.root)
        tree.root?.left?.right= BRNode(3, 0, tree.root?.left)
        tree.root?.right?.right=BRNode(12, 0, tree.root?.right)
        tree.root?.right?.left=BRNode(7, 0, tree.root?.right)
        assertEquals("5 2 8 null 3 7 12 null null null null null null ", tree.printNodes())
    }
}
