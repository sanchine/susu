from django.db import models
from django.core.files.storage import FileSystemStorage

fs = FileSystemStorage(location='media/images')

# Create your models here.

class Image(models.Model):
    image = models.ImageField(null=True, blank=True)
    digit = models.IntegerField(null=True, blank=True)
    predict = models.IntegerField(null=True, blank=True)
    correct = models.BooleanField(null=True, blank=True)


    def save(self, *args, **kwargs):
        if self.correct is not None:
            path = f"{self.image.name[0:-4]}"
            fs.save(path, self.image)
        super().save(*args, **kwargs)

    
    def __str__(self):
        return f"Image ID: {self.pk}"
