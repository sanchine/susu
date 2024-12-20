from keras.models import Sequential
from keras import saving

import numpy as np
import cv2


class DigitRecognizer(object):

    
    @staticmethod
    def get_predicted_digit(self, img_path, model_path):
        model = Sequential()

        img=cv2.imread(img_path, cv2.IMREAD_UNCHANGED)
        img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        img_data = (255.0-img.reshape(-1))/255

        x=np.expand_dims(img_data,axis=0)


        model = saving.load_model(model_path)


        prediction = model.predict(x)

        return np.argmax(prediction)