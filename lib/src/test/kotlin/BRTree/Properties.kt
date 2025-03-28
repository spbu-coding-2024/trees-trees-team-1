package BRTree

import net.jqwik.api.*
import net.jqwik.api.constraints.UniqueElements
import trees.BRTree
import nodes.BRNode.Companion.RED
import nodes.BRNode.Companion.BLACK
import kotlin.test.assertEquals

const val SIZE=200
const val MIN=-1000000
const val MAX=1000000

class Properties {
    private lateinit var tree:BRTree<Int, Int>

    @Provide
    fun generator(): Arbitrary<IntArray> {
        val generator=Arbitraries.integers().between(MIN, MAX)
        return generator.array(IntArray::class.java).ofSize(SIZE)
    }

    @Property
    @Label("Invariant of red node parent color")
    fun check1(@ForAll("generator") @UniqueElements list: IntArray) {
        tree=BRTree()
        for(i in list)
            tree.insert(i, 0)
        for (i in tree)
            if (tree.getColor(i)==RED)
                assertEquals(BLACK, tree.getColor(tree.findParent(i)))

        repeat(100) {
            tree.delete(list.random())
            for (i in tree)
                if (tree.getColor(i) == RED)
                    assertEquals(BLACK, tree.getColor(tree.findParent(i)))
        }
    }

    @Property()
    @Label("Invariant of black path")
    fun check2(@ForAll("generator") @UniqueElements list: IntArray) {
        tree=BRTree()
        for(i in list)
            tree.insert(i, 0)
        val result = mutableMapOf<Int?, Int?>()
        for (i in tree) {
            if (tree.isRoot(i ?: continue))
                result[i]= 1
            val children = tree.getChildren(i)
            if (children.first != null)
                result[children.first]= if (tree.getColor(children.first)==BLACK) result[i]?.plus(1) ?: 1 else result[i]
            if (children.second != null)
                result[children.second]= if (tree.getColor(children.second)==BLACK) result[i]?.plus(1) ?: 1 else result[i]
        }
        var check=0
        for (i in result.keys) {
            if (i?.let { tree.isLeaf(it) } == true)
                if (check==0)
                    check=result[i] ?: return
                else
                    assertEquals(check, result[i])
        }
    }
}

