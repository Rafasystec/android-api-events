package br.com.dbc.application.model

import androidx.annotation.Keep

@Keep
class Event {
    var id          :Long?          = null
    var people      :Array<Any>?    = null
    var date        :Long?          = null
    var description :String?        = null
    var image       :String?        = null
    var longitude   :Double?        = null
    var latitude    :Double?        = null
    var price       :Double?        = null
    var title       :String?        = null
}