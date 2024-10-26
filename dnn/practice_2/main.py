from network import Network, load_data, load_data_wrapper
import os

def beep():
    # while True:
    os.system('play -q -n synth 1 sine 3800')


def main():
    
    tr_d, va_d, te_d = load_data_wrapper()
    net = Network([784, 15, 15, 15, 10])
    net.SGD(tr_d, 30, 10, 1.5, test_data=te_d)
    # print('Hello world!')
    beep()
    beep()
    beep()
    beep()
    beep()




if __name__ == "__main__":
    main()