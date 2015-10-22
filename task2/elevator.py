import time
from myque import myque
class elevator:
    global a 
    a = [0 for x in range(0,21)]
    def __init__(self,layers): #初始化函数
        self.building_layers = layers
        self.direction = 'up'
        self.cur_layer = 1
        self.up_queue = myque()        
        self.down_queue = myque(True)       
        self.switcher = 'open'              
    def stay_at(self,a):
        max = a[0]
        i = 0
        index = 0
        while i < 21:
            if max < a[i]:
                max = a[i]
                index = i
            i += 1
        return index
    def stop(self):        
        self.switcher='stop'
        
    def push_button(self,layer,direction=None):
        if self.cur_layer>layer:            
            self.down_queue.insert(layer)
        elif self.cur_layer<layer:         
            self.up_queue.insert(layer)
        else:
            if self.direction=='up':
                self.down_queue.insert(layer)
            else:
                self.up_queue.insert(layer)
        a[layer] += 1
    def queue_is_empty(self):
        if self.up_queue.empty() and self.down_queue.empty():
            return True
        else:
           return False
    def handle_queue(self,direction):
        self.direction = direction
        if direction == 'up':
            inc = 1
        else:
            inc = -1                
        que = getattr(self , direction + '_queue')  #返回对象的引用
        while que.length():
            while self.cur_layer != que.front():
                print
                print ('电梯在',self.cur_layer,"楼")
                time.sleep(1)
                self.cur_layer += inc
            print ('电梯到了',self.cur_layer,"楼")
            que.pop_front()         


    def run(self):  #运行
        while self.switcher=='open':            
            if self.up_queue.empty() and self.down_queue.empty():
                """elevator now is waiting, stop at a layer""" 
                              
                j = self.stay_at(a)
                nowlayer = self.cur_layer
                if(nowlayer != j):
                    print (j)
                    self.push_button(j)
                time.sleep(3)
                continue                        
            """go up"""
            self.handle_queue('up')            
            """go down""" 
            self.handle_queue('down') 