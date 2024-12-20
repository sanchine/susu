from digit_recognizer import DigitRecognizer


result = DigitRecognizer.get_predicted_digit(
    DigitRecognizer,
    img_path="digits/digit_0.jpg",
    model_path="model.keras"
)

print(result)