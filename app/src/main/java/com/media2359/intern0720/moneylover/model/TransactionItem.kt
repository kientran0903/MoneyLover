package com.media2359.intern0720.moneylover.model

import com.media2359.intern0720.moneylover.TransactionModel

class TransactionItem : TransactionModel() {
    var id: Int = 0
    var createAt: String = ""
    var updateAt: String = ""
    var userId: Int = 0
    var type: String = ""
    var category: String = ""
    var amount: Int = 0
    var description: String = ""
    var date: String = ""
}