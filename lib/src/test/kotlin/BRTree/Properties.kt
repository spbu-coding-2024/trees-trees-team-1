package BRTree

import net.jqwik.api.Property
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

    @Property
    @RepeatedTest(25)
    @DisplayName("check for sequence of red nodes")
    fun check_insertion() {
        var list= Vector<Int>()
        for (i in 0..1000) {
            val t=Random.nextInt(-1000000, 1000000)
            if (!list.contains(t))
                list.add(t)
        }
        for (i in list)
            tree.insert(i, 0)
        for (i in tree) {
            if (tree.getColor(i)==BRTree.RED)
                assertEquals(BRTree.BLACK ,tree.getColor(tree.findParent(i)))
        }
        while (list.isNotEmpty()) {
            var t = list.random()
            list.remove(t)
            tree.delete(t)
            for (i in tree) {
                if (tree.getColor(i)==BRTree.RED)
                    assertEquals(BRTree.BLACK ,tree.getColor(tree.findParent(i)))
            }
        }

    }

}

