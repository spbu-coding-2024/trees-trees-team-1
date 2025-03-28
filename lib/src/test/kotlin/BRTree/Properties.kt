package BRTree

import net.jqwik.api.*
import net.jqwik.api.constraints.UniqueElements
import trees.BRTree
import kotlin.test.assertEquals


class Properties {

    @Provide
    fun generator(): Arbitrary<IntArray> {
        val generator=Arbitraries.integers().between(-1000000, 1000000)
        return generator.array(IntArray::class.java).ofSize(200)
    }

    @Property
    @Label("Invariant of red node parent color")
    fun check_insertion(@ForAll("generator") @UniqueElements list: IntArray) {
        val tree=BRTree<Int, Int>()
        for(i in list)
            tree.insert(i, 0)
        for (i in tree)
            if (tree.getColor(i)==BRTree.RED)
                assertEquals(BRTree.BLACK, tree.getColor(tree.findParent(i)))

        repeat(100) {
            tree.delete(list.random())
            for (i in tree)
                if (tree.getColor(i) == BRTree.RED)
                    assertEquals(BRTree.BLACK, tree.getColor(tree.findParent(i)))
        }
    }



}

