package test.BRTree

import net.jqwik.api.*
import net.jqwik.api.constraints.UniqueElements
import nodes.BRNode.Companion.BLACK
import nodes.BRNode.Companion.RED
import org.junit.jupiter.api.Assertions.assertEquals
import trees.BRTree


const val SIZE=200
const val MIN=-1000000
const val MAX=1000000

@Tag("BRTree")
class Properties {
    lateinit var tree:BRTree<Int, Int>

    @Provide
    fun generator(): Arbitrary<IntArray> {
        val generator=Arbitraries.integers().between(MIN, MAX)
        return generator.array(IntArray::class.java).ofSize(SIZE)
    }

    fun checkBSfactor(key: Int, lower: Int, upper: Int ) {
        assert(key in (lower + 1)..<upper)
        val (left, right)=tree.getChildren(key)
        if (left!= null)
            checkBSfactor(left, lower, key)
        if (right!= null)
            checkBSfactor(right, key, upper)
    }

    @Property
    @Label("Invariant of red node parent color")
    fun check1(@ForAll("generator") @UniqueElements list: IntArray) {
        tree=BRTree()
        for(i in list)
            tree.insert(i, 0)
        for (i in tree)
            if (tree.getColor(i)==RED)
                assertEquals(BLACK, tree.getColor(tree.findParent(i ?: continue)))
        repeat(100) {
            tree.delete(list.random())
            for (i in tree)
                if (tree.getColor(i) == RED)
                    assertEquals(BLACK, tree.getColor(tree.findParent(i ?: continue)))
        }
    }

    @Property
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

    @Property
    @Label("Invariant of binary-search tree")
    fun check3(@ForAll("generator") @UniqueElements list: IntArray) {
        tree=BRTree()
        for(i in list)
            tree.insert(i, 0)
        var start: Int=Int.MAX_VALUE
        val iterator=tree.iterator()
        if (iterator.hasNext())
            start=iterator.next() ?: return
        checkBSfactor(start,Int.MIN_VALUE, Int.MAX_VALUE)
    }

    @Property
    @Label("Invariant of black root")
    fun check4(@ForAll("generator") @UniqueElements list: IntArray) {
        tree=BRTree()
        for(i in list)
            tree.insert(i, 0)
        val iterator=tree.iterator()
        if (iterator.hasNext())
            assertEquals(BLACK, tree.getColor(iterator.next()))
    }
}
