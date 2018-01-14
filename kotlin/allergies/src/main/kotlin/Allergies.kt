class Allergies(private val score: Int) {
    private val allergens = Allergen.values()
                .filter { score and it.score == it.score }

    fun isAllergicTo(allergen: Allergen) : Boolean {
        return allergens.contains(allergen)
    }

    fun getList() : List<Allergen> {
        return allergens
    }
}