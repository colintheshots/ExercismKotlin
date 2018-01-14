object BinarySearch {
    fun search(intList: List<Int>, target: Int) : Int {
        return searchImpl(intList, 0, intList.size - 1, target)
    }

    fun searchImpl(intList: List<Int>, left: Int, right: Int, target: Int) : Int {
        if (right >= left) {
            val pivot = (right - left) / 2 + left
            when {
                target == intList[pivot] -> return pivot
                target > intList[pivot] -> return searchImpl(intList, pivot+1, right, target)
                target < intList[pivot] -> return searchImpl(intList, left, pivot-1, target)
            }
        }
        return -1
    }
}