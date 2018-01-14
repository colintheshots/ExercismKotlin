class Matrix (coordValues: List<List<Int>>) {
    var saddlePoints: Set<MatrixCoordinate> = coordValues.foreach2d()
}

fun List<List<Int>>.foreach2d() : Set<MatrixCoordinate> {
    val transposed = this.transpose()
    var rowCandidates = setOf<MatrixCoordinate>()
    var colCandidates = setOf<MatrixCoordinate>()
    this.mapIndexed { rowNum, row ->
        val maxCol = row.max()
        row.mapIndexed { colNum, colValue ->
            if (colValue == maxCol) {
                colCandidates = colCandidates.plus(MatrixCoordinate(rowNum, colNum))
                val column = transposed[colNum]
                val maxRow = column.min()
                column.mapIndexed {
                    rowIndex, rowValue ->
                    if (rowValue == maxRow) rowCandidates = rowCandidates.plus(MatrixCoordinate(rowIndex, colNum))
                }
            }
        }
    }
    return rowCandidates.intersect(colCandidates)
}

fun List<List<Int>>.transpose() : Array<Array<Int>> {
    val transposed = Array(this[0].size) { Array(this.size, {0})}
    this.mapIndexed {
        rowNumber: Int, row: List<Int> ->
            row.mapIndexed { columnNumber, value -> transposed[columnNumber][rowNumber] = value }
    }
    return transposed
}