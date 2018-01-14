class Pangram {
    companion object {
        fun isPangram(input : String) : Boolean {
            return ('a'..'z').mapNotNull {
                if (input.contains(it, true)) null else it
            }.isEmpty()
        }
    }
}