 class MinesweeperBoard (private val input : List<String>) : List<String> {

     fun withNumbers() : List<String> {
         val numbersList = mutableListOf<String>()
         input.mapIndexed { rowIndex, string ->
             var rowString = ""
             string.mapIndexed { colIndex, char ->
                 rowString+=(when (char) {
                     '*' -> '*'
                     else -> countAdjacentMines(rowIndex, colIndex)
                 })
             }
             numbersList.add(rowString)
         }
         return numbersList
     }

     private fun countAdjacentMines(rowIndex: Int, colIndex: Int) : Char {
         val numMines = listOf(
                 Pair(rowIndex-1, colIndex-1), Pair(rowIndex, colIndex-1),
                 Pair(rowIndex+1, colIndex-1), Pair(rowIndex-1, colIndex),
                 Pair(rowIndex+1, colIndex), Pair(rowIndex-1, colIndex+1),
                 Pair(rowIndex, colIndex+1), Pair(rowIndex+1, colIndex+1))
                 .count {
                     if (it.first in 0 until input.size && it.second in 0 until input[0].length) {
                         input[it.first][it.second] == '*'
                     } else false
                 }
         return if (numMines != 0) numMines.toString()[0] else ' '
     }

     override val size: Int
         get() = input.size
     override fun contains(element: String): Boolean = input.contains(element)
     override fun containsAll(elements: Collection<String>): Boolean = input.containsAll(elements)
     override fun get(index: Int): String = input[index]
     override fun indexOf(element: String): Int = input.indexOf(element)
     override fun isEmpty(): Boolean = input.isEmpty()
     override fun iterator(): Iterator<String> = input.iterator()
     override fun lastIndexOf(element: String): Int = input.lastIndexOf(element)
     override fun listIterator(): ListIterator<String> = input.listIterator()
     override fun listIterator(index: Int): ListIterator<String> = input.listIterator(index)
     override fun subList(fromIndex: Int, toIndex: Int): List<String> = input.subList(fromIndex, toIndex)
}