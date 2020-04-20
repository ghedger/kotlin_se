class PlayingField {
    init {
        Size.width = 1
        Size.height = 1
    }
    object Size {
        var width = 1
        var height = 1
        fun changeSize(lwidth: Int, lheight: Int) {
            width = lwidth
            height = lheight
            if (width < 0) width = 0
            if (height < 0) height = 0
        }
    }
}
