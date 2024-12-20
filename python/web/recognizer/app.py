from flask import Flask, render_template, request, redirect, url_for, jsonify, make_response
from digit_recognizer import DigitRecognizer
import os

app = Flask(__name__)
UPLOAD_FOLDER = 'uploads'  # Создайте папку uploads
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
os.makedirs(UPLOAD_FOLDER, exist_ok=True)

curr_file = ""

@app.route('/', methods=['GET', 'POST'])
def index():
    if request.method == 'POST':
        if 'file' not in request.files:
            return redirect(request.url)
        file = request.files['file']
        if file.filename == '':
            return redirect(request.url)
        filename = os.path.join(app.config['UPLOAD_FOLDER'], file.filename)
        file.save(filename)
        curr_file = filename
        
        response = make_response('New ad has created', 302)
        response.headers["Location"] = "/recognize"
        return response
    
    html = """
        <form method=POST action="/" enctype="multipart/form-data">
            <input type="file"  name="file" value="Select image" id="fileInput" accept="image/*" />
            <input type="submit" value="To recognize" />
        </form>

        <img id="imagePreview" style="display: none; min-width: 100px; max-width: 300px;" />

        <script>
            const fileInput = document.getElementById('fileInput');
            const imagePreview = document.getElementById('imagePreview');

            fileInput.addEventListener('change', () => {
                const file = fileInput.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = (e) => {
                        imagePreview.src = e.target.result;
                        imagePreview.style.display = 'block';
                    }
                    reader.readAsDataURL(file);
                }
            });

        </script>
    """


    return html

@app.get('/recognize')
def recognize():
    print(curr_file)

    result = DigitRecognizer.get_predicted_digit(
        DigitRecognizer,
        img_path=curr_file,
        model_path="model.keras"
    )

    response = make_response('New ad has created', 302)
    response.headers["Location"] = "/validation"
    response.headers["X-RecognizedDigit"] = result
    return response



@app.get("/validation")
def validation_digit():
    # recognition = request.headers.get("X-Recognition")

    html = f"""
        <form method=POST action="/right">
            <input type="submit" value="Yes" />
        </form>

         <form method=POST action="/oops">
            <input type="submit" value="No" />
        </form>

        <img id="imagePreview" src={curr_file} style="display: none; max-width: 300px;" />

        <label>Result: {result}</label>
    """

    return html




if __name__ == '__main__':
    app.run(debug=True)




# from flask import Flask, request, make_response
# from digit_recognizer import DigitRecognizer
# import datetime


# app = Flask(__name__)


# @app.get("/")
# def home():
#     html = """
#         <form method=POST action="/">
#             <input type="file" value="Select image" id="fileInput" accept="image/*" />
#             <input type="submit" value="To recognize" />
#         </form>

#         <img id="imagePreview" style="display: none; min-width: 100px; max-width: 300px;" />

#         <script>
#             const fileInput = document.getElementById('fileInput');
#             const imagePreview = document.getElementById('imagePreview');

#             fileInput.addEventListener('change', () => {
#                 const file = fileInput.files[0];
#                 if (file) {
#                     const reader = new FileReader();
#                     reader.onload = (e) => {
#                         imagePreview.src = e.target.result;
#                         imagePreview.style.display = 'block';
#                     }
#                     reader.readAsDataURL(file);
#                 }
#             });

#         </script>
#     """


#     return html


# @app.post("/")
# def recognize_image():
#     print('Recognizing....')

#     image = request.form.get("fileInput")
#     print("*" * 30)
#     print(image)
#     print("*" * 30)

#     result = DigitRecognizer.get_predicted_digit(
#         DigitRecognizer,
#         img_path=image,
#         model_path="model.keras"
#     )

#     response = make_response('New ad has created', 302)
#     response.headers["Location"] = "/validation"
#     response.headers["X-RecognizedDigit"] = result
#     response.headers["X-ImageDigit"] = image
#     return response


# @app.get("/validation")
# def validation_digit():
#     image = request.headers.get("X-ImageDigit")
#     result = request.headers.get("X-RecognizedDigit")

#     html = f"""
#         <form method=POST action="/right">
#             <input type="submit" value="Yes" />
#         </form>

#          <form method=POST action="/oops">
#             <input type="submit" value="No" />
#         </form>

#         <img id="imagePreview" src={image} style="display: none; max-width: 300px;" />

#         <label>Result: {result}</label>
#     """

#     return html

# app.run()

# # @app.post("/")
# # def create_ad():
# #     new_ad = {
# #         "id": len(ads),
# #         "title": request.form.get("title"),
# #         "description": request.form.get("description"),
# #         "created": datetime.datetime.today().isoformat(),
# #         "owner": request.form.get("owner"),
# #     }

# #     ads.append(new_ad)

# #     response = make_response('New ad has created', 302)
# #     response.headers["Location"] = "/"
# #     return response



# # @app.post("/delete")
# # def delete_ad():
# #     target_id = int(request.form.get("id"))
# #     # print(ads)
# #     for i, ad in enumerate(ads):
# #         if ad['id'] == target_id:
# #             del ads[i]
# #             break

# #     response = make_response('Ad has deleted', 302)
# #     response.headers["Location"] = "/"
# #     return response



# # @app.post("/edit")
# # def edit_ad():
# #     target_id = int(request.form.get("id"))
# #     target_ad = None

# #     for i, ad in enumerate(ads):
# #         if ad['id'] == target_id:
# #             target_ad = ad
            
# #     return f"""
# #         <form action="/edited" method=POST>
# #             <input type="hidden" name="id" value="{target_ad["id"]}" />
# #             <input type="text" name="title" value="{target_ad['title']}" /><br>
# #             <textarea name="description">{target_ad['description']}</textarea><br>
# #             <input type="text" name="owner" value="{target_ad['owner']}" /><br>
# #             <input type="submit" value="Готово" />
# #         </form>
# #     """
    


# # @app.post("/edited")
# # def save_edited_ad():
# #     target_id = int(request.form.get("id"))
# #     target_ad = None

# #     for i, ad in enumerate(ads):
# #         if ad['id'] == target_id:
# #             ad['title'] = request.form.get("title")
# #             ad['description'] = request.form.get("description")
# #             ad['owner'] = request.form.get("owner")
# #             ad['created'] = datetime.datetime.today().isoformat()

# #     response = make_response('Ad has edited', 302)
# #     response.headers["Location"] = "/"
# #     return response