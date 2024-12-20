from django.shortcuts import render, redirect
from django.http import HttpResponse
from .forms import ImageForm
from .models import Image
from .digit_recognizer import DigitRecognizer

# Create your views here.
def hello(request):
    return HttpResponse("Hello!")


def index(request):
    if request.method == 'POST':
        form = ImageForm(request.POST, request.FILES)
        if form.is_valid():
            if 'image' in request.FILES:
                image_file = request.FILES['image']
                image = form.save(commit=False)

                predicted_digit = DigitRecognizer.get_predicted_digit(
                    self=DigitRecognizer,
                    img_path=image_file,
                    model_path='../model.keras'
                )

                image.image = image_file

                image.predict = predicted_digit
                # image.digit = 
                image.save()
                ctx = {'image': image, 'predicted_digit': predicted_digit}
                return render(request, 'result.html', ctx)
    form = ImageForm()
    ctx = {'form': form}
    return render(request, 'index.html', ctx)


def result(request, image_id):
    image = Image.objects.get(pk=image_id)
    return render(request, 'result.html', {'image': image})


def update_correctness(request, image_id):
    if request.method == "POST":
        image = Image.objects.get(pk=image_id)
        image.correct = request.POST.get('correct') == 'true'
        if image.correct:
            image.digit = image.predict
        else:
            image.digit = request.POST.get('real_digit')

        image.save()
    return redirect('result', image_id=image_id)