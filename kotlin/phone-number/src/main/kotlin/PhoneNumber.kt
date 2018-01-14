class PhoneNumber(input: String) {
    var number : String? = input.replace("\\D".toRegex(),"")
                .let { when (it.length) {
                        11 -> if (it[0]=='1') it.drop(1) else null
                        10 -> it
                        else -> null
                    }
                }?.takeIf { ('2'..'9').contains(it[0]) && ('2'..'9').contains(it[3]) }
}