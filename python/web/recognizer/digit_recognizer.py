import tensorflow as tf
from tensorflow import keras
from keras.models import Sequential
from keras import saving
from PIL import Image

import numpy as np
import cv2


def image_to_tensor(image_path):
    """
    Читает черно-белое изображение, изменяет его размер до 28x28 и преобразует в NumPy массив.

    Args:
        image_path: Путь к изображению.

    Returns:
        NumPy массив размера (1, 28, 28) или None, если произошла ошибка.
    """
    try:
        # Читаем изображение в градациях серого
        img = Image.open(image_path).convert("L") # "L" для grayscale
        if img is None:
            print(f"Ошибка: изображение по пути '{image_path}' не найдено.")
            return None

        # Изменяем размер изображения до 28x28
        img = img.resize((28, 28))

        # Преобразуем в NumPy массив
        img_array = np.array(img)

        # Нормализуем пиксели к диапазону [0, 1]
        img_array = img_array.astype("float32") / 255.0

        # Добавляем размерность канала
        x = np.expand_dims(img_array, axis=0)

        return x
    except Exception as e:
        print(f"Произошла ошибка: {e}")
        return None

class DigitRecognizer(object):

    
    @staticmethod
    def get_predicted_digit(self, img_path, model_path):
        with tf.device('/CPU:0'):
            model = Sequential()

            img = image_to_tensor(image_path=img_path)

            model = saving.load_model(model_path)

            prediction = model.predict(img)

            return np.argmax(prediction)