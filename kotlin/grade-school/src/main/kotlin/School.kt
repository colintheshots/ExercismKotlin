class School {
    var db = mutableMapOf<Int, List<String>>()
    fun db() : Map<Int, List<String>> {
        return db.toMap()
    }
    fun grade(int: Int) : List<String> {
        return db.getOrDefault(int, listOf())
    }
    fun add(name: String, grade: Int) {
        db.put(grade, grade(grade).plus(name))
    }
    fun sort() : Map<Int, List<String>> {
        return db.entries.associateBy({it.key}) {it.value.sorted()}.toSortedMap()
    }
}