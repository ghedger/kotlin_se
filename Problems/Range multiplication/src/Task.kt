val lambda = { l: Long, r: Long -> Long
    var p: Long = l
    var first = true
    loop@for (i in l..r) {
        if (first) {
            first = false
            continue@loop
        }
        p *= i
    }
    p
}
