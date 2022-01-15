#!/usr/bin/env python3

class PriorityQueue:
    __queue = []
    __reverse = False
    __priority = None
    __type = int
    __duplicatesAllowed = False
    def __init__(self, reverse = False, priority = None, type = int, duplicatesAllowed = False):
        if not isinstance(reverse, bool):
            raise TypeError("reverseOrder must be bool. Current type: {0}".format(type(reverse)))
        
        if priority is not None and not callable(priority):
            raise TypeError("priority must be None(default sorting) or a function. Current type: {0}".format(type(priority)))

        if not isinstance(duplicatesAllowed, bool):
            raise TypeError("duplicatesAllowed must be bool. Current type: {0}".format(type(duplicatesAllowed)))
        
        self.__queue = []
        self.__reverse = reverse
        self.__priority = priority
        self.__type = type
        self.__duplicatesAllowed = duplicatesAllowed

    def empty(self):
        return len(self.__queue) == 0

    def enQueue(self, data):
        if not isinstance(data, self.__type):
            raise TypeError("data must be type {0}. Current type: {1}".format(self.__type, type(data)))
        if not self.__duplicatesAllowed:
            if data in self.__queue:
                return
        self.__queue.append(data)

    def deQueue(self):
        if self.empty():
            raise IndexError("Attempted to deQueue from an empty queue")
        self.__queue.sort(reverse = self.__reverse, key = self.__priority)
        return self.__queue.pop(0)

    def clear(self):
        self.__queue.clear()

    def size(self):
        return len(self.__queue)
