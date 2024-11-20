package com.growingtree.growingtreeserver.exception

import com.growingtree.growingtreeserver.exception.messages.ErrorMessage

class CustomException(val errorMessage: ErrorMessage) : RuntimeException()
