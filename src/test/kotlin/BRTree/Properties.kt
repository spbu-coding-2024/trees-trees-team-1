package BRTree

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Tag
import trees.BRTree
import java.util.*
import kotlin.random.Random

@Tag("properties")
@Tag("BRTree")
class Properties {
    private var tree= BRTree<Int, Int>()



    @RepeatedTest(1)
    @DisplayName("check for sequence of red nodes")
    fun check_insertion() {
        var list= Vector<Int>()
        for (i in 0..10) {
            val t=Random.nextInt(0, 100)
            if (!list.contains(t))
                list.addLast(t)
        }
        for (i in list)
            tree.insert(i, 0)
        for (i in tree) {
            if (tree.getColor(i)==BRTree.RED)
                assertEquals(BRTree.BLACK ,tree.getColor(tree.findParent(i)))
        }
        println(list)
        while (list.isNotEmpty()) {
            var t = list.random()
            println(t)
            list.remove(t)
            tree.delete(t)
            println(tree.printNodes())
            for (i in tree)
                print("${tree.getColor(i)} ")
            println()
            for (i in tree) {
                if (tree.getColor(i)==BRTree.RED)
                    assertEquals(BRTree.BLACK ,tree.getColor(tree.findParent(i)))
            }
        }

    }

}

