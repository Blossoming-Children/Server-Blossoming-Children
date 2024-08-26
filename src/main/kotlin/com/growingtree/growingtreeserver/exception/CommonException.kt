package com.growingtree.growingtreeserver.exception

import com.growingtree.growingtreeserver.exception.messages.ErrorMessage

class CommonException(val errorMessage: ErrorMessage) : RuntimeException()
