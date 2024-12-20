import tensorflow as tf
from tensorflow import keras
from keras.models import Sequential
from keras import saving
from PIL import Image
from io import BytesIO

import numpy as np
import cv2


def image_to_tensor(image_path):

    try:
        # Читаем изображение в градациях серого
        img = Image.open(BytesIO(image_path.read())).convert("L")
        if img is None:
            print(f"Ошибка: изображение по пути '{image_path}' не найдено.")
            return None

        img_array = np.array(img).flatten()
        img_array = (np.expand_dims(img_array,0))
        return img_array

    except Exception as e:
        print(f"Произошла ошибка: {e}")
        return None

class DigitRecognizer(object):

    
    @staticmethod
    def get_predicted_digit(self, img_path, model_path):
        with tf.device('/CPU'):
            model = Sequential()

            img = image_to_tensor(image_path=img_path)

            model = saving.load_model(model_path)

            prediction = model.predict(img)

            return np.argmax(prediction)