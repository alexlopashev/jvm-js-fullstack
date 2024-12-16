import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul
import react.useEffectOnce
import react.useState

private val scope = MainScope()

val App = FC<Props> {
    var items by useState(emptyList<ExampleItem>())

    useEffectOnce {
        scope.launch {
            items = getItems()
        }
    }

    h1 {
        +"TollBit Fullstack Interview"
    }

    ul {
        items.forEach {
            li {
                key = it.id.toString()
                +"[${it.id}] ${it.name}"
            }
        }
    }
}