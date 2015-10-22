import threading
class myque:
    def __init__(self,reverse=False):
        self.mode = reverse
        self.buf = []
        self.lock = threading.Lock()
    
    def insert(self,object):
        self.lock.acquire()
        self.buf.append(object)
        self.buf.sort(reverse = self.mode)
        self.lock.release()
    
    def front(self):
        return self.buf[0]
        
        
    def pop_front(self):
        self.lock.acquire()
        self.buf.pop(0)
        self.lock.release()
    
    def length(self):
        self.lock.acquire()
        size = len(self.buf)
        self.lock.release()
        return size
    
    def empty(self):
        self.lock.acquire()
        size = len(self.buf)
        self.lock.release()
        return size==0