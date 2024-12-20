FROM python:3.12-slim


WORKDIR /app
RUN pip install --upgrade pip
RUN pip install flask gunicorn keras numpy opencv-python
RUN pip install tensorflow


COPY app.py ./
COPY digit_recognizer.py ./
COPY model.keras ./

CMD ["gunicorn", "--bind", "0.0.0.0:5000", "app:app"]

