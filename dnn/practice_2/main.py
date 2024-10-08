from network import Network, load_data, load_data_wrapper
# import paramiko


def main():
    tr_d, va_d, te_d = load_data_wrapper()
    net = Network([784, 30, 10])
    net.SGD(tr_d, 30, 10, 3.0, test_data=te_d)
    # print('Hello world!')



if __name__ == "__main__":
    main()