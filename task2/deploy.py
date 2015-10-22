import threading
import time
from elevator import elevator
def init_elevator(building_layers):                
    e = elevator(building_layers) 
    t = threading.Thread(target = e.run)	#运行电梯算法
    t.setDaemon(True)	#后台运行
    t.start()
    return (e,t) 

def main():
    myelevator,ctl_thread = init_elevator(19)    #初始化变量
    
    
    while True:
        str=input("Input layer:")
        try:
            layer = int(str)
        except Exception:			#捕捉异常
            if str=='quit':
                myelevator.stop()
                ctl_thread.join()
                break
            else:
                print ('invalid input',str)
                continue
          
        if layer not in range(-2,myelevator.building_layers+1):
            print("input error!")
            continue
        myelevator.push_button(layer)


       
if __name__=='__main__':
    main()
